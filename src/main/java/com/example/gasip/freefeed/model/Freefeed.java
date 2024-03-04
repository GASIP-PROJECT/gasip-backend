package com.example.gasip.freefeed.model;

import com.example.gasip.global.entity.BaseTimeEntity;
import com.example.gasip.member.model.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "freefeed")
@SuperBuilder
@DynamicInsert
public class Freefeed extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "게시글 ID")
    private Long feedId;
    @Column(nullable = false,length = 500)
    @Schema(description = "게시글 내용")
    private String feed;
    @Column(nullable = false)
    @Schema(description = "게시글 조회수")
    @ColumnDefault("0")
    private Long clickCount;
    @Column(nullable = false)
    @Schema(description = "게시글 좋아요")
    @ColumnDefault("0")
    private Long likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
