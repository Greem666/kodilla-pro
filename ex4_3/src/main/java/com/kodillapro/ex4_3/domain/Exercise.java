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
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String status;

    @OneToMany(targetEntity = Student.class, mappedBy = "exercise")
    private Set<Student> students = new HashSet<>();

    @OneToMany(targetEntity = SubExercise.class, mappedBy = "exercise")
    private Set<SubExercise> subExercises = new HashSet<>();

    public Exercise(Long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
