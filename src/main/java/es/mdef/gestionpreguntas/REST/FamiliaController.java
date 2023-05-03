package es.mdef.gestionpreguntas.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import es.mdef.gestionpreguntas.GestionpreguntasApplication;
import es.mdef.gestionpreguntas.entidades.Familia;
import es.mdef.gestionpreguntas.entidades.Pregunta;
import es.mdef.gestionpreguntas.repositorios.FamiliaRepositorio;


@RestController
@RequestMapping("/familias")
public class FamiliaController {
	
	private final FamiliaRepositorio repositorio;
	private final FamiliaAssembler assembler;
    private final FamiliaListaAssembler listaAssembler;
    private final FamiliaPostAssembler postAssembler;
    private final PreguntaListaAssembler preguntaListaAssembler;
    private final Logger log;
	
    
    
    public FamiliaController(FamiliaRepositorio repositorio, FamiliaAssembler assembler, FamiliaListaAssembler listaAssembler,
			 FamiliaPostAssembler postAssembler, PreguntaListaAssembler preguntaListaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.postAssembler = postAssembler;
		this.preguntaListaAssembler = preguntaListaAssembler;
		log = (Logger) GestionpreguntasApplication.log;
	}

	@GetMapping("{id}")
	public FamiliaModel one(@PathVariable Long id) {
		Familia familia = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "familia"));
		log.info("Recuperado " + familia);
		
		return assembler.toModel(familia);
	}
    
    @GetMapping
   	public CollectionModel<FamiliaListaModel> all() {
   		return listaAssembler.toCollection(repositorio.findAll());
   	}
    
    @GetMapping("{id}/preguntas")
	public CollectionModel<PreguntaListaModel> preguntas(@PathVariable Long id) {
		List<Pregunta> preguntas = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "familia"))
				.getPreguntas();
		return CollectionModel.of(
				preguntas.stream().map(preg -> preguntaListaAssembler.toModel(preg)).collect(Collectors.toList()),
				linkTo(methodOn(FamiliaController.class).preguntas(id)).withSelfRel()
				);
	}
    
    @PostMapping
	public FamiliaModel add(@RequestBody FamiliaPostModel model) {
		Familia familia = repositorio.save(postAssembler.toEntity(model));
		log.info("Añadido " + familia);
		return assembler.toModel(familia);
	}
    
    @PutMapping("{id}")
	public FamiliaModel edit(@PathVariable Long id, @RequestBody FamiliaPostModel model) {
		 Familia familia = repositorio.findById(id).map(fam -> {
			fam.setEnunciado(model.getEnunciado());
			return repositorio.save(fam);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "familia"));
		log.info("Actualizado " + familia);
		return assembler.toModel(familia);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrado familia " + id);
		repositorio.deleteById(id);
	}
    
}
