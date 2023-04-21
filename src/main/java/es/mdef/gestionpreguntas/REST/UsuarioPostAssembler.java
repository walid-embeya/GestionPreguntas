package es.mdef.gestionpreguntas.REST;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpreguntas.entidades.Usuario;

@Component
public class UsuarioPostAssembler implements RepresentationModelAssembler<Usuario, UsuarioPostModel> {

	@Override
	public UsuarioPostModel toModel(Usuario entity) {
		//UsuarioModel model = EntityModel.of(entity);
		UsuarioPostModel model = new UsuarioPostModel();
		model.setNombre(entity.getNombre());
		model.setUsername(entity.getUsername());
		model.setContraseña(entity.getContraseña());
		model.setRole(entity.getRole());
	
		model.add(
				linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				);
		
		return model;
	}

	public Usuario toEntity(UsuarioPostModel model) {
		Usuario usuario = new Usuario();	
		usuario.setNombre(model.getNombre());
		usuario.setUsername(model.getUsername());
		usuario.setContraseña(model.getContraseña());
		usuario.setRole(model.getRole());
		
		return usuario;
	}
	
//	public Usuario toEntity(UsuarioPostModel model) {
//		Usuario usuario = new Usuario();	
//		usuario.setNombre(model.getNombre());
//		usuario.setUsername(model.getUsername());
//		usuario.setContraseña(model.getContraseña());
//		usuario.setRole(model.getRole());
//		
//		return usuario;
//	}

}