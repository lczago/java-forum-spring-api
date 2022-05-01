package spring.rest.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String message;
	@ManyToOne
	private Topic topic;
	private LocalDateTime createDate = LocalDateTime.now();
	@ManyToOne
	private Users author;
	private Boolean solution = false;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (id == null) {
			return other.id == null;
		} else return id.equals(other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String mensagem) {
		this.message = mensagem;
	}

	public Topic getTopico() {
		return topic;
	}

	public void setTopico(Topic topic) {
		this.topic = topic;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Users getAuthor() {
		return author;
	}

	public void setAuthor(Users author) {
		this.author = author;
	}

	public Boolean getSolution() {
		return solution;
	}

	public void setSolution(Boolean solution) {
		this.solution = solution;
	}

}
