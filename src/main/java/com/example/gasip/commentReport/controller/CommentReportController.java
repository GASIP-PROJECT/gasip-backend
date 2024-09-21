package com.example.gasip.commentReport.controller;

import com.example.gasip.commentReport.dto.CommentReportRequest;
import com.example.gasip.commentReport.dto.CommentReportResponse;
import com.example.gasip.commentReport.service.CommentReportService;
import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "차단 및 신고 기능", description = "차단, 신고와 관련된 API 입니다")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("comments/report")
public class CommentReportController {
    private final CommentReportService commentReportService;

    @PostMapping
    @Operation(summary = "댓글 신고", description = "부적절한 댓글울 신고기능을 통해 신고할 수 있습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentReportResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<?> inset(@RequestBody @Valid CommentReportRequest commentReportRequest,
                                   @AuthenticationPrincipal MemberDetails memberDetails) throws Exception {

        CommentReportResponse response = commentReportService.commentReportInsert(commentReportRequest, memberDetails);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiUtils.success(response)
                );
    }

    @DeleteMapping
    @Operation(summary = "신고 취소", description = "신고한 댓글의 신고를 취소할 수 있습니다. (Admin 전용 기능입니다. 일반 사용자는 사용 불가합니다.)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentReportResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<?> delete(@RequestBody @Valid CommentReportRequest commentReportRequest,
                                                      @AuthenticationPrincipal MemberDetails memberDetails) throws Exception {

        CommentReportResponse response = commentReportService.commentReportDelete(commentReportRequest, memberDetails);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiUtils.success(response)
                );
    }

}
