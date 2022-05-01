package spring.rest.controller.dto;

import spring.rest.model.Topic;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class TopicDto {

    private final Long id;
    private final String title;
    private final String message;
    private final LocalDateTime createDate;

    public TopicDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.createDate = topic.getCreateDate();
    }

    public static Page<TopicDto> converter(Page<Topic> topics) {
        return topics.map(TopicDto::new);
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
}
