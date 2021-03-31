package com.kodillapro.ex4_3.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SubExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String status;

    @OneToMany(targetEntity = Student.class, mappedBy = "subExercise")
    private Set<Student> students = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public SubExercise(Long id, String name, String status, Exercise exercise) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.exercise = exercise;
    }
}
