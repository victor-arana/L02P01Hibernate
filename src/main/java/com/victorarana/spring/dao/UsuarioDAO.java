package com.victorarana.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.victorarana.spring.dto.Usuario;

@Component("UsuarioDAO")
public class UsuarioDAO implements UsuarioDAOInterface {
	
	@Autowired
	private SessionFactory sessionFactory;

	/* Busca por id en la tabla mapeada por la clase Usuario
	 * el renglón cuyo id sea igual a usuario id 
	 */
	@Override
	public Usuario buscaPorId(Integer usuarioId) {
		// Transacciones de manera programática
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// Tabla objetoBD = (Tabla) session.get(Tabla.class, Object)
		Usuario usuario = (Usuario) session.get(Usuario.class, usuarioId);
		
		// Termina la transacción y cerramos la sesión de hibernate
		session.getTransaction().commit();
		session.close();		
		return usuario;
	}
	
	@Override
	public Usuario buscaPorEmail(String email){
		// Transacciones de manera programática
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		Usuario usuario = (Usuario) criteria.uniqueResult();
		
		// Termina la transacción y cerramos la sesión de hibernate
		session.getTransaction().commit();
		session.close();		
		return usuario;		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> buscaPorNombre(String nombre){
		// Transacciones de manera programática
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.like("nombre", "%" + nombre + "%"));
		List<Usuario> usuarios = criteria.list();		

		// Termina la transacción y cerramos la sesión de hibernate
		session.getTransaction().commit();
		session.close();		
		return usuarios;
	}

	/* 
	 * Crea un nuevo renglón en la tabla cuyas columnas se llenan con las propiedades de 
	 * la instancia usuario, es equivalente a un insert
	 */
	@Override
	public void creaUsuario(Usuario usuario) {
		// Transacciones de manera programática
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(usuario);
		
		// Termina la transacción y cerramos la sesión de hibernate
		session.getTransaction().commit();
		session.close(); 
	}
}