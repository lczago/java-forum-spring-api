package spring.rest.controller.dto;

import spring.rest.model.TopicStatus;
import spring.rest.model.Topic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TopicDetailsDto {
    private final Long id;
    private final String title;
    private final String message;
    private final LocalDateTime createDate;
    private final String author;
    private final TopicStatus status;

    private final List<AnswerDto> answers;

    public TopicDetailsDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.createDate = topic.getCreateDate();
        this.author = topic.getAuthor().getName();
        this.status = topic.getStatus();
        this.answers = new ArrayList<>();
        this.answers.addAll(topic.getRespostas().stream().map(AnswerDto::new).toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public String getAuthor() {
        return author;
    }

    public TopicStatus getStatus() {
        return status;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }
}
