package com.tfg.inmobiliaria.modelo.dao;

import java.util.List;

import com.tfg.inmobiliaria.beansentity.Favorito;

public interface IntFavoritoDao {

	//Devuelve la lista de favoritos del usuario que indicamos
	List<Favorito> findByUsername(String username);
}
