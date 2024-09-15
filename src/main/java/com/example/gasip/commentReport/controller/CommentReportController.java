package com.example.gasip.commentReport.controller;

import com.example.gasip.commentReport.dto.CommentReportRequestDto;
import com.example.gasip.commentReport.service.CommentReportService;
import com.example.gasip.global.entity.HttpResponseEntity;
import com.example.gasip.global.security.MemberDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.example.gasip.global.entity.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("comments/report")
public class CommentReportController {
    private final CommentReportService commentReportService;

    @PostMapping
    public HttpResponseEntity.ResponseResult<?> inset(@RequestBody @Valid CommentReportRequestDto commentReportRequestDto,
                                                      @AuthenticationPrincipal MemberDetails memberDetails) throws Exception {
        commentReportService.commentReportInsert(commentReportRequestDto, memberDetails);
        return success();
    }

    @DeleteMapping
    public HttpResponseEntity.ResponseResult<?> delete(@RequestBody @Valid CommentReportRequestDto commentReportRequestDto,
                                                      @AuthenticationPrincipal MemberDetails memberDetails) throws Exception {
        commentReportService.commentReportDelete(commentReportRequestDto, memberDetails);
        return success();
    }

}
