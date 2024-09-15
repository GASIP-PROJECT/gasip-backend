package com.example.gasip.boardReport.controller;

import com.example.gasip.boardReport.dto.BoardReportRequest;
import com.example.gasip.boardReport.service.BoardReportService;
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
@RequestMapping("boards/report")
public class BoardReportController {
    private final BoardReportService boardReportService;

    @PostMapping
    public HttpResponseEntity.ResponseResult<?> insert(@RequestBody @Valid BoardReportRequest boardReportRequest,
                                                       @AuthenticationPrincipal MemberDetails memberDetails) throws  Exception {
        boardReportService.insert(boardReportRequest, memberDetails);
        return success();
    }

    @DeleteMapping
    public HttpResponseEntity.ResponseResult<?> delete(@RequestBody @Valid BoardReportRequest boardReportRequest,
                                                       @AuthenticationPrincipal MemberDetails memberDetails) throws  Exception {
        boardReportService.delete(boardReportRequest, memberDetails);
        return success();
    }
}
