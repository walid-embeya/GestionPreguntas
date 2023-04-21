package es.mdef.gestionpreguntas.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionpreguntas.entidades.NoAdministrador.Departamento;
import es.mdef.gestionpreguntas.entidades.NoAdministrador.Tipo;
import es.mdef.gestionpreguntas.entidades.Usuario.Role;

@Relation(itemRelation = "usuario")
public class UsuarioModel extends RepresentationModel<UsuarioModel> {
	private String nombre;
	private String username;
	// private String contraseña;
	private Role role;
	private String telefono;
	private Tipo tipo;
	private Departamento departamento;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
//	public String getContraseña() {
//		return contraseña;
//	}
//	public void setContraseña(String contraseña) {
//		this.contraseña = contraseña;
//	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "UsuarioModel [nombre=" + nombre + ", username=" + username + ", role=" + role + "]";
	}

}
