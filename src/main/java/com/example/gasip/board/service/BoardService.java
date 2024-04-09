package com.example.gasip.board.service;


import com.example.gasip.board.dto.*;
import com.example.gasip.board.model.Board;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.global.constant.ErrorCode;
import com.example.gasip.global.exception.BoardNotFoundException;
import com.example.gasip.global.exception.InvaildWritterException;
import com.example.gasip.global.exception.MemberNotFoundException;
import com.example.gasip.global.exception.ProfessorNotFoundException;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import com.example.gasip.professor.model.Professor;
import com.example.gasip.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    @Transactional
    public BoardCreateResponse createBoard(BoardCreateRequest boardCreateRequest, MemberDetails memberDetails, Long profId) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(() -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));
        Professor professor = professorRepository.findById(profId).orElseThrow(() -> new ProfessorNotFoundException(ErrorCode.NOT_FOUND_PROFESSOR));
        Board board = boardRepository.save(boardCreateRequest.toEntity(professor,member));
        return BoardCreateResponse.fromEntity(board);
    }

    @Transactional
    public BoardReadResponse findBoardId(Long postId,MemberDetails memberDetails) {
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(() -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));
        insertView(postId,member);
        Board board = boardRepository.findById(postId).orElseThrow(() -> new BoardNotFoundException(ErrorCode.NOT_FOUND_BOARD));
        return BoardReadResponse.fromEntity(board);
    }
    @Transactional
    public BoardReadResponse findBoardIdWithOutMember(Long boardId) {
        return addViewWithoutMember(boardId);
    }
    @Transactional
    public BoardUpdateResponse editBoard(MemberDetails memberDetails,Long boardId,BoardUpdateRequest boardUpdateRequest) {
        Board board = validatedBoardWritter(memberDetails, boardId);
        board.updateBoard(boardUpdateRequest.getContent());
        return BoardUpdateResponse.fromEntity(board);
    }
    @Transactional
    public String deleteBoard(MemberDetails memberDetails,Long boardId) {
        validatedBoardWritter(memberDetails, boardId);
        boardRepository.deleteById(boardId);
        return boardId + "번 게시글이 삭제되었습니다.";
    }
    @Transactional
    public List<BoardReadResponse> findBestBoard(Long profId, Pageable pageable) {
        Professor professor = professorRepository.getReferenceById(profId);
        return boardRepository.findByProfessorOrderByLikeCountDescClickCountDesc(professor, pageable)
            .stream()
            .map(BoardReadResponse::fromEntity)
            .collect(Collectors.toList());
    }

    private Board validatedBoardWritter(MemberDetails memberDetails, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoundException(ErrorCode.NOT_FOUND_BOARD));
        Member member = memberRepository.findById(memberDetails.getId()).orElseThrow(() -> new MemberNotFoundException(ErrorCode.NOT_FOUND_MEMBER));
        if (!member.getMemberId().equals(board.getMember().getMemberId())) {
            throw new InvaildWritterException(ErrorCode.INVALID_WRITTER);
        }
        return board;
    }
    @Transactional
    public void insertView(Long postId,Member member) {
        String viewCount = redisViewCountService.getData(String.valueOf(member.getMemberId()));
        Board board = boardRepository.findById(postId)
            .orElseThrow(() -> new NotFoundException("Could not found board id : " + postId));
        if (viewCount == null) {
            redisViewCountService.setDateExpire(String.valueOf(member.getMemberId()), postId + "_", calculateTimeOut(5));
            boardRepository.addViewCount(board);
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
                if (!isview) {
                    viewCount += postId + "_";
                    redisViewCountService.setDateExpire(String.valueOf(member.getMemberId()),viewCount,calculateTimeOut(5));
                    boardRepository.addViewCount(board);
                }
            }
        }
    }
    public static long calculateTimeOut(int time) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.truncatedTo(ChronoUnit.MINUTES).plusMinutes(time);
        return ChronoUnit.SECONDS.between(now, midnight);
    }

    @Transactional
    public BoardReadResponse addViewWithoutMember(Long boardId) {
        Board board = boardRepository.getReferenceById(boardId);
        redisViewCountService.addViewCountInRedis(boardId);
        return BoardReadResponse.fromEntity(board);
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
    @Transactional
    public List<BoardReadResponse> findByContentContaining(String content, Pageable pageable) {
        return boardRepository.findByContentContaining(content, pageable)
                .stream()
                .map(BoardReadResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 교수 이름으로 게시글 검색
     */
    @Transactional
    public List<BoardReadResponse> findByProfNameLike(String profName) {
        return boardRepository.findByProfNameLike(profName);
    }
}


