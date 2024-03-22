package com.example.gasip.grade.model;

import com.example.gasip.global.entity.BaseTimeEntity;
import com.example.gasip.member.model.Member;
import com.example.gasip.professor.model.Professor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Grade extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;
    private int gradepoint;
    @ManyToOne
    private Professor professor;
    @ManyToOne
    private Member member;
}
