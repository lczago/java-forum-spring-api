package spring.rest.controller;


import spring.rest.controller.form.AtualizacaoTopicoForm;
import spring.rest.controller.form.TopicoForm;
import spring.rest.controller.dto.TopicDetailsDto;
import spring.rest.controller.dto.TopicDto;
import spring.rest.model.Topic;
import spring.rest.repository.CourseRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.rest.repository.TopicRepository;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicRepository topicRepository;

    private final CourseRepository courseRepository;

    public TopicController(TopicRepository topicRepository, CourseRepository courseRepository) {
        this.topicRepository = topicRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    @Cacheable(value = "TopicsList")
    public Page<TopicDto> page(@RequestParam(required = false) String courseName,
                               @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable)
    {

        Page<Topic> topics;
        if (courseName == null) {
            topics = topicRepository.findAll(pageable);
        } else {
            topics = topicRepository.findByCourseName(courseName, pageable);
        }
        return TopicDto.converter(topics);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "TopicsList", allEntries = true)
    @SecurityRequirement(name = "Token")
    public ResponseEntity<TopicDto> register(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicoForm.converter(courseRepository);
        topicRepository.save(topic);

        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicDto((topic)));
    }

    @GetMapping("/{id}")
    @Transactional
    @CacheEvict(value = "TopicsList", allEntries = true)
    public ResponseEntity<TopicDetailsDto> details(@PathVariable("id") Long code) {
        Optional<Topic> topic = topicRepository.findById(code);
        return topic.map(value -> ResponseEntity.ok(new TopicDetailsDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // on swagger, by default, page sorting is set as String, it needs to be changed to id.
    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "TopicsList", allEntries = true)
    @SecurityRequirement(name = "Token")
    public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Optional<Topic> optional = topicRepository.findById(id);
        if (optional.isPresent()) {
            Topic topic = form.atualizar(id, topicRepository);
            return ResponseEntity.ok(new TopicDto(topic));
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "Token")
    public ResponseEntity<TopicDto> remove(@PathVariable Long id) {
        Optional<Topic> optional = topicRepository.findById(id);
        if (optional.isPresent()) {
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
