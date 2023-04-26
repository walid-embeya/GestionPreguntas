package es.mdef.gestionpreguntas.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import net.bytebuddy.implementation.bind.annotation.Super;

@Entity
@DiscriminatorValue("A")
public class Administrador extends Usuario {
	private String telefono;

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Role getRole() {
		return Role.administrador;
	}

	@Override
	public String toString() {
		return "Administrador [Nombre = " + getNombre() + ", Username = " + getUsername() + ", telefono = " + telefono + "]";
	}
}
