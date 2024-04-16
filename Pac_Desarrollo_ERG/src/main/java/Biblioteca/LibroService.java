package Biblioteca;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class LibroService {

	public void insertarLibro(Libro libro) {
	    Session session = null;
	    Transaction tx = null;
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        tx = session.beginTransaction();
	        session.save(libro);
	        tx.commit();
	        System.out.println("Libro insertado correctamente: " + libro.getId());
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        System.out.println("Error al insertar el libro: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}


    public List<Libro> obtenerTodosLosLibros() {
        Session session = null;
        List<Libro> libros = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            libros = session.createQuery("FROM Libro", Libro.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return libros;
    }

    public Libro obtenerLibroPorId(int id) {
        Session session = null;
        Libro libro = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            libro = session.get(Libro.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return libro;
    }
}
