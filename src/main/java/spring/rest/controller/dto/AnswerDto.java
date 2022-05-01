package spring.rest.controller.dto;

import spring.rest.model.Answer;

import java.time.LocalDateTime;

public class AnswerDto {
    private final Long id;
    private final String message;
    private final LocalDateTime createDate;
    private final String author;

    public AnswerDto(Answer answer) {
        this.id = answer.getId();
        this.message = answer.getMessage();
        this.createDate = answer.getCreateDate();
        this.author = answer.getAuthor().getName();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public String getNomoAutor() {
        return author;
    }
}
