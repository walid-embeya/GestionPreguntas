package es.mdef.gestionpreguntas.REST;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import es.mdef.gestionpreguntas.GestionpreguntasApplication;
import es.mdef.gestionpreguntas.entidades.Usuario;
import es.mdef.gestionpreguntas.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	private final UsuarioRepositorio repositorio;
	private final UsuarioAssembler assembler;
    private final UsuarioListaAssembler listaAssembler;
    private final Logger log;
	
    UsuarioController(UsuarioRepositorio repositorio, UsuarioAssembler assembler, UsuarioListaAssembler listaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		log = (Logger) GestionpreguntasApplication.log;
	}
    
    @GetMapping("{id}")
	public EntityModel<Usuario> one(@PathVariable Long id) {
		Usuario usuario = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
		log.info("Recuperado " + usuario);
		
		return assembler.toModel(usuario);
	}
    
	@PostMapping
	public EntityModel<Usuario> add(@RequestBody UsuarioModel model) {
		Usuario usuario = repositorio.save(assembler.toEntity(model));
		log.info("Añadido " + usuario);
		return assembler.toModel(usuario);
	}
		
    @GetMapping
	public CollectionModel<UsuarioListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}

    @PutMapping("{id}")
	public EntityModel<Usuario> edit(@PathVariable Long id, @RequestBody UsuarioModel model) {
		Usuario usuario = repositorio.findById(id).map(user -> {
			user.setNombre(model.getNombre());
			user.setUsername(model.getUsername());
			user.setContraseña(model.getContraseña());
			return repositorio.save(user);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
		log.info("Actualizado " + usuario);
		
		return assembler.toModel(usuario);
	}
    
}
