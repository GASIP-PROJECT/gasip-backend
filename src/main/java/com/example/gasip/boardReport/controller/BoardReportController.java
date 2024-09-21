package com.example.gasip.boardReport.controller;

import com.example.gasip.boardReport.dto.BoardReportRequest;
import com.example.gasip.boardReport.dto.BoardReportResponse;
import com.example.gasip.boardReport.service.BoardReportService;
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
@RequestMapping("boards/report")
public class BoardReportController {
    private final BoardReportService boardReportService;

    @PostMapping
    @Operation(summary = "게시글 신고", description = "부적절한 게시글을 신고기능을 통해 신고할 수 있습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BoardReportResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<?> insert(@RequestBody @Valid BoardReportRequest boardReportRequest,
                                    @AuthenticationPrincipal MemberDetails memberDetails) throws  Exception {
        BoardReportResponse response = boardReportService.insert(boardReportRequest, memberDetails);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiUtils.success(response)
                );
    }

    @DeleteMapping
    @Operation(summary = "게시글 신고 취소", description = "신고한 게시글의 신고를 취소할 수 있습니다. (Admin 전용 기능입니다. 일반 사용자는 사용 불가합니다.)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BoardReportResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<?> delete(@RequestBody @Valid BoardReportRequest boardReportRequest,
                                                       @AuthenticationPrincipal MemberDetails memberDetails) throws  Exception {

        BoardReportResponse response = boardReportService.delete(boardReportRequest, memberDetails);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiUtils.success(response)
                );
    }
}
