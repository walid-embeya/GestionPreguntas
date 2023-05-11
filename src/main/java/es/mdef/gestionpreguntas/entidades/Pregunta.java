package es.mdef.gestionpreguntas.entidades;



public class Pregunta {

	private Long id;
	private String enunciado;

	private Usuario usuario;

	private Familia familia;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
		
	public Familia getFamilia() {
		return familia;
	}
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	@Override
	public String toString() {
		return "Pregunta [id=" + id + ", enunciado=" + enunciado + "]";
	}
	
}
