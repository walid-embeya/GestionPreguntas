package es.mdef.gestionpreguntas.REST;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import es.mdef.gestionpreguntas.entidades.Usuario;

@Component
public class UsuarioListaAssembler implements RepresentationModelAssembler<Usuario, UsuarioListaModel>{

	@Override
	public UsuarioListaModel toModel(Usuario entity) {
		UsuarioListaModel model = new UsuarioListaModel();
		model.setNombre(entity.getNombre());
		model.add(
				//linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				);
		
		return model;
	} 
	
	public CollectionModel<UsuarioListaModel> toCollection(List<Usuario> lista) {
		CollectionModel<UsuarioListaModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);
		collection.add(
			//	linkTo(methodOn(UsuarioController.class).all()).withRel("usuarios")
				);
		return collection;
	}

}
