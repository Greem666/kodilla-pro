package com.kodillapro.ex1_2;

import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PersonProcessor implements ItemProcessor<PersonDate, PersonAge> {
    @Override
    public PersonAge process(PersonDate item) throws Exception {
        String[] dateOfBirthParts = item.getDob().split("/");
        LocalDate dateOfBirth = LocalDate.of(
                Integer.parseInt(dateOfBirthParts[2]),
                Integer.parseInt(dateOfBirthParts[1]),
                Integer.parseInt(dateOfBirthParts[0]));
        return new PersonAge(
                item.getName(),
                item.getSurname(),
                (int)ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now())
        );
    }
}
