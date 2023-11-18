package com.example.gasip.college.model;

import com.example.gasip.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "college")
public class College extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collegeId;
    @Column(nullable = false)
    private String collegeName;
}
