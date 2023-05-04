package es.mdef.gestionpreguntas.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpreguntas.entidades.Familia;


@Component
public class FamiliaAssembler implements RepresentationModelAssembler<Familia, FamiliaModel> {
	
	@Override
	public FamiliaModel toModel(Familia entity) {
		FamiliaModel model = new FamiliaModel();
		
		model.setEnunciado(entity.getEnunciado());
		model.setTamano(entity.getTamano());
		
		model.add(linkTo(methodOn(FamiliaController.class).one(entity.getId())).withSelfRel() );
		model.add(linkTo(methodOn(FamiliaController.class).preguntas(entity.getId())).withRel("ListaPreguntas"));
		
		return model;
	}
	
	public Familia toEntity(FamiliaModel model) {
		Familia familia = new Familia();

		familia.setEnunciado(model.getEnunciado());
		//familia.set(model.getUsername());

		return familia;
	}

}
