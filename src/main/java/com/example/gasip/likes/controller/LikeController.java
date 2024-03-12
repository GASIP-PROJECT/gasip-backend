package com.example.gasip.likes.controller;

import com.example.gasip.global.entity.HttpResponseEntity;
import com.example.gasip.likes.dto.LikeRequestDto;
import com.example.gasip.likes.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.example.gasip.global.entity.HttpResponseEntity.success;

@Tag(name = "Like Controller", description = "게시글 좋아요와 관련된 API입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("boards/likes")
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    @Operation(summary = "좋아요 요청", description = "게시글에 좋아요를 요청합니다.", tags = { "Like Controller" })
    @Parameter(name = "memberId", description = "로그인 되어있는 사용자 계정의 Id")
    @Parameter(name = "postId", description = "좋아요를 누를 게시글의 postId")
    public HttpResponseEntity.ResponseResult<?> insert(@RequestBody @Valid LikeRequestDto likeRequestDto) throws Exception {
        likeService.insert(likeRequestDto);
        return success();
    }

    @DeleteMapping
    @Operation(summary = "좋아요 취소 요청", description = "게시글에 누른 좋아요를 취소합니다.", tags = { "Like Controller" })
    @Parameter(name = "memberId", description = "로그인 되어있는 사용자 계정의 Id")
    @Parameter(name = "postId", description = "좋아요를 취소할 게시글의 postId")
    public HttpResponseEntity.ResponseResult<?> delete(@RequestBody @Valid LikeRequestDto likeRequestDto) {
        likeService.delete(likeRequestDto);
        return success();
    }
}
