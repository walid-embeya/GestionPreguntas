package es.mdef.gestionpreguntas.REST;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpreguntas.entidades.Administrador;
import es.mdef.gestionpreguntas.entidades.NoAdministrador;
import es.mdef.gestionpreguntas.entidades.Usuario;

@Component
public class UsuarioListaAssembler implements RepresentationModelAssembler<Usuario, UsuarioListaModel>{

	@Override
	public UsuarioListaModel toModel(Usuario entity) {
		UsuarioListaModel model = new UsuarioListaModel();
		model.setNombre(entity.getNombre());
		
		switch (entity.getRole()) {
		case administrador: {
			Administrador adm = (Administrador) entity;
			model.setRole(adm.getRole());
			break;
		}
		case noAdministrador: {
			NoAdministrador noAdm = (NoAdministrador) entity;
			model.setRole(noAdm.getRole());
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
	
	public CollectionModel<UsuarioListaModel> toCollection(List<Usuario> lista) {
		CollectionModel<UsuarioListaModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);
		collection.add(
				linkTo(methodOn(UsuarioController.class).all()).withRel("usuarios")
				);
		
		return collection;
	}

}
