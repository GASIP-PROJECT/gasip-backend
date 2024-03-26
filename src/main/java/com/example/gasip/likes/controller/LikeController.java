package com.example.gasip.likes.controller;

import com.example.gasip.global.entity.HttpResponseEntity;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.likes.dto.LikeRequestDto;
import com.example.gasip.likes.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.example.gasip.global.entity.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("boards/likes")
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    public HttpResponseEntity.ResponseResult<?> insert(@RequestBody @Valid LikeRequestDto likeRequestDto,
                                                       @AuthenticationPrincipal MemberDetails memberDetails) throws Exception {
        likeService.insert(likeRequestDto, memberDetails);
        return success();
    }

    @DeleteMapping
    public HttpResponseEntity.ResponseResult<?> delete(@RequestBody @Valid LikeRequestDto likeRequestDto,
                                                       @AuthenticationPrincipal MemberDetails memberDetails) {
        likeService.delete(likeRequestDto, memberDetails);
        return success();
    }


    @PostMapping("/{postId}")
    public HttpResponseEntity.ResponseResult<?> insert(@RequestBody @Valid LikeRequestDto likeRequestDto) throws Exception {
        likeService.addLikeWithoutMember(likeRequestDto);
        return success();
    }
//    @PostMapping("/{postId}")
//    public ResponseEntity<?> insertWithoutMem(@PathVariable Long postId) throws Exception {
//        return ResponseEntity
//                .ok()
//                .body(
//                    ApiUtils.success(likeService.insertLikeWithoutMember(postId))
//                );
//    }
}

