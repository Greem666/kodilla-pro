package com.kodillapro.ex4_1.controller;

import com.kodillapro.ex4_1.domain.BookDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/bookFlux")
public class FluxController {

    @GetMapping(value = "getBooks", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<BookDto> getBooks() {
        return Flux
                .just(
                        new BookDto("Author1", "Title1", 2001),
                        new BookDto("Author2", "Title2", 2002),
                        new BookDto("Author3", "Title3", 2003),
                        new BookDto("Author4", "Title4", 2004)
                )
                .log();
    }
}
