package es.mdef.gestionpreguntas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionpreguntas.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	//List<Usuario> findUsuarioById(Long id);

}
