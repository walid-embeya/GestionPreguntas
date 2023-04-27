package es.mdef.gestionpreguntas.REST;

import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import es.mdef.gestionpreguntas.GestionpreguntasApplication;
import es.mdef.gestionpreguntas.entidades.Pregunta;
import es.mdef.gestionpreguntas.repositorios.PreguntaRepositorio;


@RestController
@RequestMapping("/preguntas")
public class PreguntaController {
	private final PreguntaRepositorio repositorio;
	private final PreguntaAssemler assembler;
	private final PreguntaListaAssembler listaAssembler;
	private final PreguntaPostAssembler postAssembler;
	private final Logger log;
	
	public PreguntaController(PreguntaRepositorio repositorio, PreguntaAssemler assembler, PreguntaListaAssembler listaAssembler,
			PreguntaPostAssembler postAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.postAssembler = postAssembler;
		log = (Logger) GestionpreguntasApplication.log;
	}
	
	@GetMapping("{id}")
	public PreguntaModel one(@PathVariable Long id) {
		Pregunta pregunta = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "pregunta"));
		log.info("Recuperado " + pregunta);
		return assembler.toModel(pregunta);
	}
	
	@GetMapping
	public CollectionModel<PreguntaListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}
	
//	@GetMapping("porEstado")
//	public CollectionModel<PedidoListaModel> pedidosPorEstado(@RequestParam PedidoEstado estado) {
//		return listaAssembler.toCollection(
//				repositorio.findPedidoByEstado(estado)
//				);
//	}
	
	@PostMapping
	public PreguntaModel add(@RequestBody PreguntaPostModel model) {
		Pregunta pregunta = repositorio.save(postAssembler.toEntity(model));
		log.info("Añadido " + pregunta);
		return assembler.toModel(pregunta);
	}
	
//	@PutMapping("{id}")
//	public EntityModel<Pedido> edit(@PathVariable Long id, @RequestBody PedidoModel model) {
//		Pedido pedido = repositorio.findById(id).map(ped -> {
//			ped.setDescripcion(model.getDescripcion());
//			ped.setEstado(model.getEstado());
//			return repositorio.save(ped);
//		})
//		.orElseThrow(() -> new RegisterNotFoundException(id, "pedido"));
//		log.info("Actualizado " + pedido);
//		return assembler.toModel(pedido);
//	}
//	
//	@DeleteMapping("{id}")
//	public void delete(@PathVariable Long id) {
//		log.info("Borrado pedido " + id);
//		repositorio.deleteById(id);
//	}
//	
	
	
}
