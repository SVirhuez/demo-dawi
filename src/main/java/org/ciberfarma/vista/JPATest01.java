package org.ciberfarma.vista;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.ciberfarma.modelo.Usuario;

import org.ciberfarma.modelo.Usuario;

public class JPATest01 {
	
	public static void main(String[] args) {
		
		// Crear un objeto de usuario a grabar
		
		Usuario u = new Usuario();
		u.setCodigo(10);
		u.setNombre("Saul");
		u.setApellido("Virhuez");
		u.setUsuario("sauro@ciber.com");
		u.setClave("saul");
		u.setFnacim("1999/10/02");
		u.setTipo(1);
		u.setEstado(1);
		
		// grabar el objeto
		// 1. fabricar el acceso a los datos
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		// 2. manejador de entidades
		EntityManager manager = fabrica.createEntityManager();
		
		// 3. empezar mi transaccion
		manager.getTransaction().begin();
		// proceso a realizar (persistencia)
		manager.persist(u);
		//manager.merge(u);
		// 4. Confirmar la transaccion
		manager.getTransaction().commit();
	}
}
