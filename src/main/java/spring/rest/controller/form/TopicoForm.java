package spring.rest.controller.form;

import com.sun.istack.NotNull;
import spring.rest.model.Course;
import spring.rest.model.Topic;
import spring.rest.repository.CourseRepository;

import javax.validation.constraints.NotEmpty;

public class TopicoForm {

    @NotNull @NotEmpty
    private String titulo;
    @NotNull
    private String mensagem;
    @NotNull
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topic converter(CourseRepository repository) {
        Course course = repository.findByName(nomeCurso);
        return new Topic(titulo, mensagem, course);
    }
}
