package es.mdef.gestionpreguntas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionpreguntas.entidades.Pregunta;

public interface PreguntaRepositorio extends JpaRepository<Pregunta, Long> {

}
