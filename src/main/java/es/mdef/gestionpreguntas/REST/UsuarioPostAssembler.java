package es.mdef.gestionpreguntas.REST;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpreguntas.entidades.Administrador;
import es.mdef.gestionpreguntas.entidades.NoAdministrador;
import es.mdef.gestionpreguntas.entidades.Usuario;

@Component
public class UsuarioPostAssembler implements RepresentationModelAssembler<Usuario, UsuarioPostModel> {

	@Override
	public UsuarioPostModel toModel(Usuario entity) {
		UsuarioPostModel model = new UsuarioPostModel();
		model.setNombre(entity.getNombre());
		model.setUsername(entity.getUsername());
		model.setPassword(entity.getPassword());
		model.setRole(entity.getRole());
	
		switch (entity.getRole()) {
		case administrador: {
			Administrador adm = (Administrador) entity;
			model.setTelefono(adm.getTelefono());
			break;
		}
		case noAdministrador: {
			NoAdministrador noAdm = (NoAdministrador) entity;
			model.setTipo(noAdm.getTipo());
			model.setDepartamento(noAdm.getDepartamento());
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + entity.getRole());
		}
		
		model.add(
				linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				);
		
		return model;
	}

	public Usuario toEntity(UsuarioPostModel model) {
		Usuario usuario;	
		
		switch (model.getRole()) {
		case administrador: {
			Administrador adm = new Administrador();
			adm.setTelefono(model.getTelefono());
			usuario = adm;
			break;
		}
		case noAdministrador: {
			NoAdministrador noAdm = new NoAdministrador();
			noAdm.setTipo(model.getTipo());
			noAdm.setDepartamento(model.getDepartamento());
			usuario = noAdm;
			break;
		}
		default:
			//throw new IllegalArgumentException("Unexpected value: " + entity.getRole());
			usuario = new Usuario();
		}
		
		usuario.setNombre(model.getNombre());
		usuario.setUsername(model.getUsername());
		
		return usuario;
	}


}
