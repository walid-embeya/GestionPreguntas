package es.mdef.gestionpreguntas.entidades;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

public class Familia extends es.mdef.support.Familia {
	
	private Long id;
	
	List<Pregunta> preguntas;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	public Long getTamano() {
		return (long) ((getPreguntas() == null) ? 0 : getPreguntas().size()); 
	}
	
	@Override
	public String toString() {
		return "Familia [id = " + id + ", Enunciado = " + getEnunciado() + ", Tamano = " + getTamano() + " preguntas" + "]";
	}
	
	

	
}
