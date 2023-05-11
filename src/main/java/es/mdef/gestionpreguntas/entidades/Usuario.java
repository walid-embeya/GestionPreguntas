package es.mdef.gestionpreguntas.entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.Transient;


public class Usuario implements UserDetails {
	private static final long serialVersionUID = 1L;
	public static enum Role {
		administrador,
		noAdministrador
	}
	
	private Long id;
	private String nombre;
	private String username;
	private String password;
	List<Pregunta> preguntas;
	
	
	
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
		
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Role getRole() {
		return null;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", username=" + username + ", contraseña="
				+ password + "]";
	}
	
	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<SimpleGrantedAuthority>(
				Arrays.asList(new SimpleGrantedAuthority(getRole().toString()))
				);
	}

}
