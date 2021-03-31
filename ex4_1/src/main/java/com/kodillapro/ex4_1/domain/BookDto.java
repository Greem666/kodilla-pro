package com.kodillapro.ex4_1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class BookDto {
    private String title;
    private String author;
    private int publicationYear;
}
