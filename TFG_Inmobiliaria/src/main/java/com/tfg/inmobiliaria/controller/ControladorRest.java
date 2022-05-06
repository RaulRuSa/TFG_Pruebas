package com.tfg.inmobiliaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.inmobiliaria.beansentity.Favorito;
import com.tfg.inmobiliaria.beansentity.Inmueble;
import com.tfg.inmobiliaria.beansentity.Usuario;
import com.tfg.inmobiliaria.modelo.dao.IntFavoritoDao;
import com.tfg.inmobiliaria.modelo.dao.IntInmuebleDao;
import com.tfg.inmobiliaria.modelo.dao.IntUsuarioDao;

@RestController
@RequestMapping("/rest/inmobiliaria")
public class ControladorRest {

	@Autowired
	private IntUsuarioDao usuarioRepo; 
	
	@Autowired
	private IntInmuebleDao inmuebleRepo;
	
	@Autowired
	private IntFavoritoDao favoritoRepo;
	
	//SERVICIO REST PARA USUARIOS
	
	@GetMapping("/buscarUsuario/{username}")
	public ResponseEntity<Usuario> procesarBuscarUsuario(@PathVariable ("username") String username){
		Usuario usuario = usuarioRepo.findById(username);
		return usuario != null?
				new ResponseEntity<Usuario>(usuario, HttpStatus.OK):
				new ResponseEntity<Usuario>(usuario, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/modificarUsuario")
	public ResponseEntity<Usuario> procesarModificarUsuario(@RequestBody Usuario usuario){
		return (usuarioRepo.modificarUsuario(usuario))?
			new ResponseEntity<Usuario>(usuario, HttpStatus.OK):
			new ResponseEntity<Usuario>(usuario, HttpStatus.NOT_MODIFIED);
	}
	
	@DeleteMapping("/borrarUsuario/{username}")
	public ResponseEntity<Usuario> procesarBorrarUsuario(@PathVariable ("username") String username){
		Usuario usuario = usuarioRepo.findById(username);
		return (usuarioRepo.borrarUsuario(username))?
			new ResponseEntity<Usuario>(usuario, HttpStatus.OK):
			new ResponseEntity<Usuario>(usuario, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/buscarFavoritosUsuario/{username}")
	public ResponseEntity<List<Favorito>> procesarBuscarFavoritosUsuario(@PathVariable ("username") String username){
		List<Favorito> listaFavoritos = favoritoRepo.findByUsername(username);
		if(listaFavoritos != null && !listaFavoritos.isEmpty())
			return new ResponseEntity<List<Favorito>>(listaFavoritos, HttpStatus.OK);
		else
			return new ResponseEntity<List<Favorito>>(listaFavoritos, HttpStatus.NO_CONTENT);
	}
	
	//SERVICIO REST PARA INMUEBLES
	
	@GetMapping("/buscarInmueble/{idInmueble}")
	public ResponseEntity<Inmueble> procesarBuscarInmueble(@PathVariable ("idInmueble") int idInmueble){
		Inmueble inmueble = inmuebleRepo.findById(idInmueble);
		return inmueble != null ?
			new ResponseEntity<Inmueble>(inmueble, HttpStatus.OK):
			new ResponseEntity<Inmueble>(inmueble, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/modificarInmueble")
	public ResponseEntity<Inmueble> procesarModificarInmueble(@RequestBody Inmueble inmueble){
		return (inmuebleRepo.modificarInmueble(inmueble))?
			new ResponseEntity<Inmueble>(inmueble, HttpStatus.OK):
			new ResponseEntity<Inmueble>(inmueble, HttpStatus.NOT_MODIFIED);
	}
	
	@DeleteMapping("/borrarInmueble/{idInmueble}")
	public ResponseEntity<Inmueble> procesarBorrarInmueble (@PathVariable ("idInmueble") int idInmueble){
		Inmueble inmueble = inmuebleRepo.findById(idInmueble);
		return inmuebleRepo.borrarInmueble(idInmueble)?
			new ResponseEntity<Inmueble>(inmueble, HttpStatus.OK):
			new ResponseEntity<Inmueble>(inmueble, HttpStatus.NOT_FOUND);
	}
	
	
}
