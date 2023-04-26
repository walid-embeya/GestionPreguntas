package es.mdef.gestionpreguntas.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("N")
public class NoAdministrador extends Usuario {

	public static enum Tipo {
		alumno,
		docente,
		administración
	}
	
	public static enum Departamento {
		EMIES,
		CCESP
	}
	
	private Tipo tipo;
	private Departamento departamento;
	
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
	
	public Role getRole() {
		return Role.noAdministrador;
	}
	
	@Override
	public String toString() {
		return "NoAdministrador [Nombre = " + getNombre() + ", Username = " + getUsername() + ", Tipo = " + tipo + ", departamento=" + departamento + "]";
	}
	

}
