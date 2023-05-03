package es.mdef.gestionpreguntas.entidades;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="FAMILIAS")
public class Familia extends es.mdef.support.Familia {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//private String enunciado;     ///// se hereda de la clase FamiliaImpl
	
	@OneToMany(mappedBy = "familia")
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
		return (long) getPreguntas().size();
	}
	@Override
	public String toString() {
		return "Familia [id = " + id + ", Enunciado = " + getEnunciado() + ", Tamano = " + getTamano() + " preguntas" + "]";
	}
	
	

	
}
