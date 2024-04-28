package com.example.gasip.board.service;


import com.example.gasip.board.dto.*;
import com.example.gasip.board.model.Board;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.comment.dto.CommentReadResponse;
import com.example.gasip.comment.model.Comment;
import com.example.gasip.comment.repository.CommentRepository;
import com.example.gasip.commentLikes.repository.CommentLikesRepository;
import com.example.gasip.global.constant.ErrorCode;
import com.example.gasip.global.exception.BoardNotFoundException;
import com.example.gasip.global.exception.InvaildWritterException;
import com.example.gasip.global.exception.MemberNotFoundException;
import com.example.gasip.global.exception.ProfessorNotFoundException;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.likes.repository.LikeRepository;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import com.example.gasip.professor.model.Professor;
import com.example.gasip.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ProfessorRepository professorRepository;
    private final RedisViewCountService redisViewCountService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final CommentLikesRepository commentLikesRepository;

    // TODO 인증 받지 않은 유저도 열람 가능하도록 수정
    @Transactional
    public List<BoardReadResponse> findAllByOrderByRegDateDesc(Pageable pageable, MemberDetails memberDetails) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(() -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));

        Page<Board> boards = boardRepository.findAllByOrderByRegDateDesc(pageable);
        for (Board board : boards) {
            if (likeRepository.findByMemberAndBoard(member, board).isEmpty()) {
                board.updateLike(false);
            } else {
                board.updateLike(true);
            }
        }
        return boards.stream()
                .map(BoardReadResponse::fromEntity)
                .collect(Collectors.toList());
//
//        return boardRepository.findAllByOrderByRegDateDesc(pageable)
//                .stream()
//                .map(BoardReadResponse::fromEntity)
//                .collect(Collectors.toList());
    }


    @Transactional
    public BoardCreateResponse createBoard(BoardCreateRequest boardCreateRequest, MemberDetails memberDetails, Long profId) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));
        Professor professor = professorRepository.findById(profId).orElseThrow(
            () -> new ProfessorNotFoundException(ErrorCode.NOT_FOUND_PROFESSOR));
        Board board = boardRepository.save(boardCreateRequest.toEntity(professor,member));
        return BoardCreateResponse.fromEntity(board);
    }
    // TODO LIKE 테이블 join해서 queryDSL 쓰는게 빠른지 비교
    @Transactional
    public List<BoardReadResponse> findBoardByProfessor(MemberDetails memberDetails,Long profId, Pageable pageable) {
        Professor professor = professorRepository.findById(profId).orElseThrow(
            () -> new ProfessorNotFoundException(ErrorCode.NOT_FOUND_PROFESSOR));
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));
        Page<Board> boards = boardRepository.findAllByProfessorOrderByRegDateDesc(professor, pageable);
        for (Board board : boards) {
            if (likeRepository.findByMemberAndBoard(member, board).isEmpty()) {
                board.updateLike(false);
            } else {
                board.updateLike(true);
            }
        }
        return boards.stream()
            .map(BoardReadResponse::fromEntity)
            .collect(Collectors.toList());
    }

    // TODO 자식 댓글 isCommentLike 칼럼 값 들어오도록 설정
    @Transactional
    public OneBoardReadResponse findBoardById(Long postId, MemberDetails memberDetails) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER)
        );
        Board board = insertView(postId, member);

        List<Comment> comments = commentRepository.findAllByBoard(board);
        List<CommentReadResponse> commentList;
        for (Comment comment : comments) {
            if (commentLikesRepository.findByMemberAndCommentAndBoard(member, comment ,board).isEmpty()) {
                comment.updateCommentLike(false);
            } else {
                comment.updateCommentLike(true);
            }
        }
        commentList = comments.stream()
                .map(CommentReadResponse::fromEntity)
                .collect(Collectors.toList());

        Boolean likes = likeRepository.existsByBoard_PostIdAndMember_MemberId(postId, memberDetails.getId());

        return OneBoardReadResponse.fromEntity(board, commentList, likes);
    }
    @Transactional
    public BoardUpdateResponse editBoard(MemberDetails memberDetails, Long boardId, BoardUpdateRequest boardUpdateRequest) {
        Board board = validatedBoardWritterEqualMember(memberDetails, boardId);
        board.updateBoard(boardUpdateRequest.getContent());
        return BoardUpdateResponse.fromEntity(board);
    }
    @Transactional
    public String deleteBoard(MemberDetails memberDetails,Long boardId) {
        validatedBoardWritterEqualMember(memberDetails, boardId);
        boardRepository.deleteById(boardId);
        return boardId + "번 게시글이 삭제되었습니다.";
    }
    @Transactional
    public List<BoardReadResponse> findBestBoard(MemberDetails memberDetails,Pageable pageable) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(
            () -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER)
        );
        List<BoardReadResponse> boardReadResponses = boardRepository.findBestBoard(pageable);
        List<BoardReadResponse> boardReadResponseList = new ArrayList<>();
        for (BoardReadResponse boardReadResponse : boardReadResponses) {
            Board board = boardRepository.getReferenceById(boardReadResponse.getPostId());

            if (likeRepository.findByMemberAndBoard(member, board).isEmpty()) {
                board.updateLike(false);
            } else {
                board.updateLike(true);
            }

            boardReadResponseList.add(BoardReadResponse.fromEntity(board));
        }
        return boardReadResponseList;
    }

    private Board validatedBoardWritterEqualMember(MemberDetails memberDetails, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoundException(ErrorCode.NOT_FOUND_BOARD));
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(() -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));
        if (!member.getMemberId().equals(board.getMember().getMemberId())) {
            throw new InvaildWritterException(ErrorCode.INVALID_WRITTER);
        }
        return board;
    }
    @Transactional
    public Board insertView(Long postId,Member member) {
        String viewCount = redisViewCountService.getData(String.valueOf(member.getMemberId()));
        Board board = boardRepository.findById(postId)
            .orElseThrow(() -> new BoardNotFoundException(ErrorCode.NOT_FOUND_BOARD));
        if (viewCount == null) {
            redisViewCountService.setDateExpire(String.valueOf(member.getMemberId()), postId + "_", calculateTimeOut(5));
            redisViewCountService.addViewCountInRedis(postId);
        } else {
            String[] strArray = viewCount.split("_");
            List<String> redisBoardList = Arrays.asList(strArray);

            boolean isview = false;
            if (!redisBoardList.isEmpty()) {
                for (String redisPostId : redisBoardList) {
                    if (String.valueOf(postId).equals(redisPostId)) {
                        isview = true;
                        break;
                    }
                }
                // 근데 없다면,사용자Id를 key로 가지는 곳에 게시글Id를 추가함 + 게시글 조회수 +1
                if (!isview) {
                    String alreadyView = postId + "_";
                    redisViewCountService.addBoardId(String.valueOf(member.getMemberId()),alreadyView);
                    redisViewCountService.addViewCountInRedis(postId);
                }
            }
        }
        return board;
    }
    public static long calculateTimeOut(int time) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.truncatedTo(ChronoUnit.MINUTES).plusMinutes(time);
        return ChronoUnit.SECONDS.between(now, midnight);
    }


    @Scheduled(cron = "0 * * * * *",zone = "Asia/Seoul")
    @Transactional
    public void combineViewCount() {
        List<String> viewCountList = redisViewCountService.deleteViewCountInRedis();
        for (String key : viewCountList) {
            Board board = boardRepository.getReferenceById(Long.valueOf(key));
            board.increaseView(Long.valueOf(redisViewCountService.getAndDeleteData(key)));
        }
    }

    /**
     * 게시글 검색 기능
     */
    // TODO 비로그인 유저도 열람 가능하도록 수정
    @Transactional
    public List<BoardReadResponse> findByContentContainingOrderByRegDateDesc(String content, MemberDetails memberDetails , Pageable pageable) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(() -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));

        Page<Board> boards = boardRepository.findByContentContainingOrderByRegDateDesc(content, pageable);
        for (Board board : boards) {
            if (likeRepository.findByMemberAndBoard(member, board).isEmpty()) {
                board.updateLike(false);
            } else {
                board.updateLike(true);
            }
        }
        return boards.stream()
                .map(BoardReadResponse::fromEntity)
                .collect(Collectors.toList());
//
//        return boardRepository.findByContentContainingOrderByRegDateDesc(content, pageable)
//                .stream()
//                .map(BoardReadResponse::fromEntity)
//                .collect(Collectors.toList());
    }

    /**
     * 교수 이름으로 게시글 검색
     */
    // TODO 비로그인 유저도 사용할 수 있도록 변경.
    @Transactional
    public List<BoardReadResponse> findByProfNameLike(String profName, MemberDetails memberDetails) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(() -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));

        List<Board> boards = boardRepository.findByProfessorProfNameLike(profName);
        for (Board board : boards) {
            if (likeRepository.findByMemberAndBoard(member, board).isEmpty()) {
                board.updateLike(false);
            } else {
                board.updateLike(true);
            }
        }
        return boards.stream()
                .map(BoardReadResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     *
     */
    // TODO 사용하는 API 인지 확인
    @Transactional
    public List<BoardProfessorReadResponse> findBoarByProfessor(Long profId, Pageable pageable) {
        return boardRepository.findBoarByProfessor(profId);
    }

}


