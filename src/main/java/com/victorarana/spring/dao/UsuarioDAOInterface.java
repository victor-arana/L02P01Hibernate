package com.victorarana.spring.dao;

import java.util.List;

import com.victorarana.spring.dto.Usuario;

public interface UsuarioDAOInterface {
	
	public Usuario buscaPorId(Integer usuarioId);
	
	public void creaUsuario(Usuario usuario);

	public Usuario buscaPorEmail(String email);

	public List<Usuario> buscaPorNombre(String nombre);
}
