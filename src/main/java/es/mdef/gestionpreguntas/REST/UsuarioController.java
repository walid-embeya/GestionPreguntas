package es.mdef.gestionpreguntas.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import es.mdef.gestionpreguntas.GestionpreguntasApplication;
import es.mdef.gestionpreguntas.entidades.Administrador;
import es.mdef.gestionpreguntas.entidades.NoAdministrador;
import es.mdef.gestionpreguntas.entidades.Pregunta;
import es.mdef.gestionpreguntas.entidades.Usuario;
import es.mdef.gestionpreguntas.repositorios.UsuarioRepositorio;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	private final UsuarioRepositorio repositorio;
	private final UsuarioAssembler assembler;
    private final UsuarioListaAssembler listaAssembler;
    private final UsuarioPostAssembler postAssembler;
    private final PreguntaListaAssembler preguntaListaAssembler;
    private final Logger log;
	
    UsuarioController(UsuarioRepositorio repositorio, UsuarioAssembler assembler, UsuarioListaAssembler listaAssembler, 
    		UsuarioPostAssembler postAssembler, PreguntaListaAssembler preguntaListaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.postAssembler = postAssembler;
		this.preguntaListaAssembler = preguntaListaAssembler;
		log = (Logger) GestionpreguntasApplication.log;
	}
    
    @GetMapping("{id}")
	public UsuarioModel one(@PathVariable Long id) {
		Usuario usuario = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
		log.info("Recuperado " + usuario);
		
		return assembler.toModel(usuario);
	}
    
    @GetMapping
	public CollectionModel<UsuarioListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}
    
	@PostMapping
	public UsuarioModel add(@Valid @RequestBody UsuarioPostModel model) {
		if (model.getPassword()!= null)
			model.setPassword(new BCryptPasswordEncoder().encode(model.getPassword()));
		Usuario usuario = repositorio.save(postAssembler.toEntity(model));
		log.info("Añadido " + usuario);
		return assembler.toModel(usuario);
	}
		
    @PutMapping("{id}")
    public UsuarioModel edit(@PathVariable Long id, @RequestBody UsuarioPutModel model) {
		Usuario usuario = repositorio.findById(id).map(user -> {
			
			user.setNombre(model.getNombre());
			user.setUsername(model.getUsername());
			
			user.setAccountNonExpired(model.isAccountNonExpired());
			user.setAccountNonLocked(model.isAccountNonLocked());
			user.setCredentialsNonExpired(model.isCredentialsNonExpired());
			user.setEnabled(model.isEnabled());
			
			switch (model.getRole()) {
			case administrador: {
				Administrador admin = (Administrador) user;
				admin.setTelefono(model.getTelefono());
				break;
			}
			case noAdministrador: {
				NoAdministrador noAdm = (NoAdministrador) user;
				model.setTipo(noAdm.getTipo());
				model.setDepartamento(noAdm.getDepartamento());
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + user.getRole());
			}
			
			return repositorio.save(user);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
		log.info("Actualizado " + usuario);
		
		return assembler.toModel(usuario);
	}
    
    @GetMapping("{id}/preguntas")
	public CollectionModel<PreguntaListaModel> preguntas(@PathVariable Long id) {
		List<Pregunta> preguntas = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"))
				.getPreguntas();
		return CollectionModel.of(
				preguntas.stream().map(preg -> preguntaListaAssembler.toModel(preg)).collect(Collectors.toList()),
				linkTo(methodOn(UsuarioController.class).preguntas(id)).withSelfRel()
				);
	}
	
    @PatchMapping("{id}/cambiarContrasena")
	public UsuarioModel edit(@PathVariable Long id, @RequestBody String newPassword) {
		Usuario nuevoUsuario = repositorio.findById(id).map(user -> {
			user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
			return repositorio.save(user);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "empleado"));
		log.info("Actualizado " + nuevoUsuario);
		return assembler.toModel(nuevoUsuario);
	}
	
  
    @DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrado usuario " + id);
		repositorio.deleteById(id);
	}
   
}
