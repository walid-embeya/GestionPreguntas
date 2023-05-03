package es.mdef.gestionpreguntas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionpreguntas.entidades.Familia;

public interface FamiliaRepositorio extends JpaRepository<Familia, Long> {

}
