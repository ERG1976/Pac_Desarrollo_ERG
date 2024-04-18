package Biblioteca;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class LibroService {

    // Método para insertar un libro en la base de datos
    public void insertarLibro(Libro libro) {
        Session session = null;
        Transaction tx = null;
        try {
            // Abre una sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            // Inicia una transacción
            tx = session.beginTransaction();
            // Guarda el libro en la base de datos
            session.save(libro);
            // Confirma la transacción
            tx.commit();
            // Imprime un mensaje de éxito
            System.out.println("Libro insertado correctamente: " + libro.getId());
        } catch (Exception e) {
            // Si ocurre un error, realiza un rollback
            if (tx != null) {
                tx.rollback();
            }
            // Imprime un mensaje de error y la traza de la excepción
            System.out.println("Error al insertar el libro: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cierra la sesión de Hibernate
            if (session != null) {
                session.close();
            }
        }
    }

    // Método para obtener todos los libros de la base de datos
    public List<Libro> obtenerTodosLosLibros() {
        Session session = null;
        List<Libro> libros = null;
        try {
            // Abre una sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            // Obtiene todos los libros usando una consulta HQL
            libros = session.createQuery("FROM Libro", Libro.class).list();
        } catch (Exception e) {
            // Imprime la traza de la excepción si ocurre un error
            e.printStackTrace();
        } finally {
            // Cierra la sesión de Hibernate
            if (session != null) {
                session.close();
            }
        }
        // Devuelve la lista de libros
        return libros;
    }

    // Método para obtener un libro por su ID de la base de datos
    public Libro obtenerLibroPorId(int id) {
        Session session = null;
        Libro libro = null;
        try {
            // Abre una sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            // Obtiene un libro por su ID
            libro = session.get(Libro.class, id);
        } catch (Exception e) {
            // Imprime la traza de la excepción si ocurre un error
            e.printStackTrace();
        } finally {
            // Cierra la sesión de Hibernate
            if (session != null) {
                session.close();
            }
        }
        // Devuelve el libro encontrado
        return libro;
    }
}

