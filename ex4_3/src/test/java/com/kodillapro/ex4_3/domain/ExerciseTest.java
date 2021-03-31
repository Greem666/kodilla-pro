package com.kodillapro.ex4_3.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExerciseTest {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    public void shouldSaveAndReadExercise() {
        // Given
        List<Long> savedExercises = insertExampleData();
        EntityManager em = emf.createEntityManager();

        // When
        System.out.println("****************** BEGIN OF FETCHING *******************");
        System.out.println("*** STEP 1 – query for invoices ***");

        TypedQuery<Exercise> query =
                em.createQuery(
                        "from Exercise "
                                + " where id in (" + exerciseIds(savedExercises) + ")",
                        Exercise.class);
        List<Exercise> exercises = query.getResultList();

        for (Exercise exercise : exercises) {
            System.out.println("*** STEP 2 – read data from exercise ***");
            System.out.println(exercise);
            System.out.println("*** STEP 3 – read the exercise sub-exercises ***");
            System.out.println(exercise.getSubExercises());

            for (SubExercise subExercise : exercise.getSubExercises()) {
                System.out.println("*** STEP 4 – read the sub-exercise ***");
                System.out.println(subExercise);
                System.out.println("*** STEP 5 – read the student from sub-exercise ***");
                System.out.println(subExercise.getStudents());
            }

        }

        // Then
        // ---------
        System.out.println("****************** END OF FETCHING *******************");
    }

    @Test
    public void shouldSaveAndReadExerciseWithEntityGraph() {
        // Given
        List<Long> savedExercises = insertExampleData();
        EntityManager em = emf.createEntityManager();

        // When
        System.out.println("****************** BEGIN OF FETCHING *******************");
        System.out.println("*** STEP 1 – query for invoices ***");

        TypedQuery<Exercise> query =
                em.createQuery(
                        "from Exercise "
                                + " where id in (" + exerciseIds(savedExercises) + ")",
                        Exercise.class);

        EntityGraph<Exercise> eg = em.createEntityGraph(Exercise.class);
        eg.addSubgraph("subExercises");
        eg.addSubgraph("students");
        query.setHint("javax.persistence.fetchgraph", eg);

        List<Exercise> exercises = query.getResultList();

        for (Exercise exercise : exercises) {
            System.out.println("*** STEP 2 – read data from exercise ***");
            System.out.println(exercise);
            System.out.println("*** STEP 3 – read the exercise sub-exercises ***");
            System.out.println(exercise.getSubExercises());

            for (SubExercise subExercise : exercise.getSubExercises()) {
                System.out.println("*** STEP 4 – read the sub-exercise ***");
                System.out.println(subExercise);
            }

            for (Student student: exercise.getStudents()) {
                System.out.println("*** STEP 5 – read the student from exercise ***");
                System.out.println(student);
            }

        }

        // Then
        // ---------
        System.out.println("****************** END OF FETCHING *******************");
    }

    private List<Long> insertExampleData() {
        Exercise ex1 = new Exercise(null, "Exercise 1", "WIP");
        Exercise ex2 = new Exercise(null, "Exercise 2", "Done");
        SubExercise ex1SubEx1 = new SubExercise(null, "SubExercise 1", "Done", ex1);
        SubExercise ex2SubEx1 = new SubExercise(null, "SubExercise 1", "Not started", ex2);
        Student st1 = new Student(null, "Name 1", "Surname 1", ex1, ex1SubEx1);
        Student st2 = new Student(null, "Name 2", "Surname 2", ex2, ex2SubEx1);
        ex1.getStudents().add(st1);
        ex1.getSubExercises().add(ex1SubEx1);
        ex2.getStudents().add(st2);
        ex2.getSubExercises().add(ex2SubEx1);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ex1);
        em.persist(ex2);
        em.persist(ex1SubEx1);
        em.persist(ex2SubEx1);
        em.persist(st1);
        em.persist(st2);
        em.flush();
        em.getTransaction().commit();
        em.close();

        return Arrays.asList(ex1.getId(), ex2.getId());
    }

    private String exerciseIds(List<Long> exerciseIds) {
        return exerciseIds.stream()
                .map(n -> "" + n)
                .collect(Collectors.joining(","));
    }

}