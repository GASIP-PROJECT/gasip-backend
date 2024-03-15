package com.example.gasip.board.service;


import com.example.gasip.board.dto.*;
import com.example.gasip.board.model.Board;
import com.example.gasip.board.repository.BoardRepository;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import com.example.gasip.professor.model.Professor;
import com.example.gasip.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ProfessorRepository professorRepository;
    private final RedisViewCountService redisViewCountService;
    private final RedissonClient redissonClient;

    @Transactional
    public BoardCreateResponse createBoard(BoardCreateRequest boardCreateRequest, MemberDetails memberDetails, Long profId) {
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        Professor professor = professorRepository.findById(profId).orElseThrow(IllegalArgumentException::new);
        Board board = boardRepository.save(boardCreateRequest.toEntity(professor,member));
        return BoardCreateResponse.fromEntity(board);
    }

    @Transactional(readOnly = true)
    public List<BoardReadResponse> findAllBoard(Pageable pageable) {
        return boardRepository.findAllByOrderByRegDateDesc(pageable)
            .stream()
            .map(BoardReadResponse::fromEntity)
            .collect(Collectors.toList());
    }
    @Transactional
    public BoardDetailResponse findById(Long postId) {
        Board board = boardRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        return BoardDetailResponse.fromEntity(board);
    }

    @Transactional
    public BoardReadResponse findBoardId(Long postId,MemberDetails memberDetails) {
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        insertView(postId,member);
        Board board = boardRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        return BoardReadResponse.fromEntity(board);
    }
    @Transactional
    public BoardReadResponse findBoardIdWithOutMember(Long postId) {
        Board board = insertViewWithoutMember(postId);
        return BoardReadResponse.fromEntity(board);
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
        Board board = validateBoardEmpty(boardId);
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        if (!member.getMemberId().equals(board.getMember().getMemberId())) {
            throw new IllegalArgumentException();
        }
        return board;
    }

    private Board validateBoardEmpty(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                IllegalArgumentException::new
        );
    }

    /**
     *
     * 조회수
     */
    @Transactional
    public void insertView(Long postId,Member member) {
        String viewCount = redisViewCountService.getData(String.valueOf(member.getMemberId()));
        Board board = boardRepository.findById(postId)
            .orElseThrow(() -> new NotFoundException("Could not found board id : " + postId));
        if (viewCount == null) {
            redisViewCountService.setDateExpire(String.valueOf(member.getMemberId()), postId + "_", calculateTimeUntil5Minutes());
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
                    redisViewCountService.setDateExpire(String.valueOf(member.getMemberId()),viewCount,calculateTimeUntil5Minutes());
                    boardRepository.addViewCount(board);
                }
            }
        }
    }
    public static long calculateTimeUntil5Minutes() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.truncatedTo(ChronoUnit.MINUTES).plusMinutes(5);
        return ChronoUnit.SECONDS.between(now, midnight);
    }

    @Transactional
    public Board insertViewWithoutMember(Long postId) {
        RLock lock = redissonClient.getLock(String.format("click:%d", postId));
        Board board;
        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);
            if (!available) {
                System.out.println("redisson getLock timeout");
            }
            board = boardRepository.getReferenceById(postId);
            board.increaseView();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }

        return board;
    }
}


