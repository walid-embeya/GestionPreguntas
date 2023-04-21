package es.mdef.gestionpreguntas.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "usuarios")
public class UsuarioListaModel extends RepresentationModel<UsuarioListaModel> {
	private String nombre;
//	private String username;
//	private String contraseña;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getContraseña() {
//		return contraseña;
//	}
//	public void setContraseña(String contraseña) {
//		this.contraseña = contraseña;
//	}

	@Override
	public String toString() {
		return "UsuarioModel [nombre=" + nombre + "]";
	}}
