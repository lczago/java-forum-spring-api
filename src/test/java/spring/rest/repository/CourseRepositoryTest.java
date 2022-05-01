package spring.rest.repository;

import spring.rest.model.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    void findByNome() {
        String nomeCurso = "Java Spring";
        Course course = repository.findByName(nomeCurso);
        Assertions.assertNotNull(course);
        Assertions.assertEquals(nomeCurso , course.getName());

    }
}