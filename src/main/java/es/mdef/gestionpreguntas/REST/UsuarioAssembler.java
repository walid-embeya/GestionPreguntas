package es.mdef.gestionpreguntas.REST;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpreguntas.entidades.Usuario;

@Component
public class UsuarioAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

	@Override
	public EntityModel<Usuario> toModel(Usuario entity) {
		EntityModel<Usuario> model = EntityModel.of(entity);
		model.add(
				linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				);
		
		return model;
	}

	public Usuario toEntity(UsuarioModel model) {
		Usuario usuario = new Usuario();	
		usuario.setNombre(model.getNombre());
		usuario.setUsername(model.getUsername());
		usuario.setContraseña(model.getContraseña());
		
		return usuario;
	}

}
