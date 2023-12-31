package com.codingdojo.cynthia.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingdojo.cynthia.modelos.Usuario;
import com.codingdojo.cynthia.servicios.Servicios;

@RestController
public class ControladorAPI {
	
	@Autowired
	private Servicios servicio;
	
	@GetMapping("/api/usuarios")
	public List<Usuario> apiMuestraUsuarios() {
		return servicio.todosUsuarios();
	}
	
	@PostMapping("/api/usuarios")
	public Usuario apiCrearUsuario(@RequestParam("nombre") String nombre,
								   @RequestParam("apellido") String apellido,
								   @RequestParam("email") String email,
								   @RequestParam("password") String password) {
		
		
		Usuario nuevoUsuario = new Usuario(nombre, apellido, email, password);
		return servicio.guardarUsuario(nuevoUsuario);
		
	}
	
	// /nombre/Elena
	//@GetMapping("/nombre/{name}
	//public void funcion(@PathVariable("name") String nombre) {
	//Recibiste nombre Cynthia
	//}
	
	
	@DeleteMapping("/api/usuarios/{id}") // /api/usuarios/3
	public void apiBorrar(@PathVariable("id") Long id) {
		//id = 1
		servicio.borrarUsuario(id);
	}
	
	@GetMapping("/api/usuarios/{id}") // /api/usuarios/3
	public Usuario apiMostrar(@PathVariable("id") Long id) {
		return servicio.buscarUsuario(id);
	}
	
	@PutMapping("/api/usuarios/{id}") // /api/usuarios/3
	public Usuario apiEditar(@PathVariable("id") Long id,
							@RequestParam("nombre") String nombre,
							@RequestParam("apellido") String apellido,
							@RequestParam("email") String email,
							@RequestParam("password") String password) {
		
		Usuario usuarioActualizado = new Usuario(id, nombre, apellido, email, password);
		return servicio.guardarUsuario(usuarioActualizado);
		
	}
	
	
	
}
