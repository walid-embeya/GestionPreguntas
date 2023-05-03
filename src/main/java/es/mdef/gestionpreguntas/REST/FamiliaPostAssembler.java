package es.mdef.gestionpreguntas.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import es.mdef.gestionpreguntas.entidades.Familia;


@Component
public class FamiliaPostAssembler implements RepresentationModelAssembler<Familia, FamiliaPostModel> {

	@Override
	public FamiliaPostModel toModel(Familia entity) {
		FamiliaPostModel model = new FamiliaPostModel();
		model.setEnunciado(entity.getEnunciado());
		
		model.add(
				linkTo(methodOn(FamiliaController.class).one(entity.getId())).withSelfRel()
				);
		
		return model;
	}

	public Familia toEntity(FamiliaPostModel model) {
		Familia familia = new Familia();	
		
		
		familia.setEnunciado(model.getEnunciado());
		//familia.setUsername(model.getUsername());
		
		return familia;
	}


}