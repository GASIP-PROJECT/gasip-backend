package com.example.gasip.commentLikes.controller;

import com.example.gasip.commentLikes.dto.CommentLikesRequestDto;
import com.example.gasip.commentLikes.repository.CommentLikesRepository;
import com.example.gasip.commentLikes.service.CommentLikesService;
import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.entity.HttpResponseEntity;
import com.example.gasip.global.security.MemberDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.example.gasip.global.entity.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("comments/likes")
public class CommentLikesController {
    private final CommentLikesService commentLikesService;
    private final CommentLikesRepository commentLikesRepository;

    @PostMapping
    public HttpResponseEntity.ResponseResult<?> insert(@RequestBody @Valid CommentLikesRequestDto commentLikesRequestDto,
                                                       @AuthenticationPrincipal MemberDetails memberDetails) throws Exception {
        commentLikesService.insertComment(commentLikesRequestDto, memberDetails);
        return success();
    }

    @DeleteMapping
    public HttpResponseEntity.ResponseResult<?> delete(@RequestBody @Valid CommentLikesRequestDto commentLikesRequestDto,
                                                       @AuthenticationPrincipal MemberDetails memberDetails) {
        commentLikesService.deleteComment(commentLikesRequestDto, memberDetails);
        return success();
    }

    @GetMapping("/isLike/{commentId}")
    public ResponseEntity<?> isLike(@PathVariable Long commentId,
                                    @AuthenticationPrincipal MemberDetails memberDetails) {

        return ResponseEntity
                .ok()
                .body(
                        ApiUtils.success(
                                commentLikesService.isCommentLikes(commentId, memberDetails.getId())
                        )
                );
    }
}
