package spring.rest.controller.form;

import spring.rest.model.Topic;
import spring.rest.repository.TopicRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizacaoTopicoForm {

    @NotNull @NotEmpty
    private String titulo;

    @NotNull @NotEmpty
    private String mensagem;

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

    public Topic atualizar(Long id, TopicRepository topicRepository) {
        Topic topic = topicRepository.getById(id);
        topic.setTitle(this.titulo);
        topic.setMessage(this.mensagem);

        return topic;
    }
}
