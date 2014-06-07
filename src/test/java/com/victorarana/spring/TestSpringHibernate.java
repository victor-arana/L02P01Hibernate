package com.victorarana.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.victorarana.spring.dao.UsuarioDAOInterface;
import com.victorarana.spring.dto.Usuario;

public class TestSpringHibernate {
	@SuppressWarnings("resource")
	public static void main(String[] args){
		
		// Creamos el contexto de Spring
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/context.xml");		
		UsuarioDAOInterface usuarioDAO = (UsuarioDAOInterface) contexto.getBean("UsuarioDAO");
		
		Usuario usuarioNuevo = new Usuario();
		
		usuarioNuevo.setUsuarioId(3);
		usuarioNuevo.setNombre("paco");
		usuarioNuevo.setPassword("123");
		usuarioNuevo.setEmail("paco@email.com");		
		usuarioDAO.creaUsuario(usuarioNuevo);
		System.out.println(usuarioNuevo.getNombre());
	}
}
