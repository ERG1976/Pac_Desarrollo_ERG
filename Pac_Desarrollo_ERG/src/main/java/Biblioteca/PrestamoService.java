package Biblioteca;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PrestamoService {

    // Método para crear un nuevo préstamo en la base de datos
    public void crearPrestamo(Prestamo prestamo) {
        Session session = null;
        Transaction tx = null;
        try {
            // Abre una sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            // Inicia una transacción
            tx = session.beginTransaction();
            // Guarda el préstamo en la base de datos
            session.save(prestamo);
            // Confirma la transacción
            tx.commit();
        } catch (Exception e) {
            // Si ocurre un error, realiza un rollback
            if (tx != null) {
                tx.rollback();
            }
            // Imprime la traza de la excepción si ocurre un error
            e.printStackTrace();
        } finally {
            // Cierra la sesión de Hibernate
            if (session != null) {
                session.close();
            }
        }
    }

    // Método para obtener un préstamo por su ID de la base de datos
    public Prestamo obtenerPrestamoPorId(int id) {
        Session session = null;
        Prestamo prestamo = null;
        try {
            // Abre una sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            // Obtiene un préstamo por su ID
            prestamo = session.get(Prestamo.class, id);
        } catch (Exception e) {
            // Imprime la traza de la excepción si ocurre un error
            e.printStackTrace();
        } finally {
            // Cierra la sesión de Hibernate
            if (session != null) {
                session.close();
            }
        }
        // Devuelve el préstamo encontrado
        return prestamo;
    }

    // Método para actualizar un préstamo en la base de datos
    public void actualizarPrestamo(Prestamo prestamo) {
        Session session = null;
        Transaction tx = null;
        try {
            // Abre una sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            // Inicia una transacción
            tx = session.beginTransaction();
            // Actualiza el préstamo en la base de datos
            session.update(prestamo);
            // Confirma la transacción
            tx.commit();
        } catch (Exception e) {
            // Si ocurre un error, realiza un rollback
            if (tx != null) {
                tx.rollback();
            }
            // Imprime la traza de la excepción si ocurre un error
            e.printStackTrace();
        } finally {
            // Cierra la sesión de Hibernate
            if (session != null) {
                session.close();
            }
        }
    }

    // Método para borrar un préstamo de la base de datos
    public void borrarPrestamo(Prestamo prestamo) {
        Session session = null;
        Transaction tx = null;
        try {
            // Abre una sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            // Inicia una transacción
            tx = session.beginTransaction();
            // Borra el préstamo de la base de datos
            session.delete(prestamo);
            // Confirma la transacción
            tx.commit();
        } catch (Exception e) {
            // Si ocurre un error, realiza un rollback
            if (tx != null) {
                tx.rollback();
            }
            // Imprime la traza de la excepción si ocurre un error
            e.printStackTrace();
        } finally {
            // Cierra la sesión de Hibernate
            if (session != null) {
                session.close();
            }
        }
    }

    // Método para obtener los libros actualmente prestados a un lector
    public List<Libro> librosActualmentePrestadosALector(Lector lector) {
        Session session = null;
        List<Libro> librosPrestados = null;
        try {
            // Abre una sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            // Crea una consulta HQL para obtener los libros prestados al lector
            Query<Libro> query = session.createQuery(
                "SELECT p.libro FROM Prestamo p WHERE p.lector = :lector AND p.fechadevolucion IS NULL", Libro.class);
            // Establece el parámetro del lector en la consulta
            query.setParameter("lector", lector);
            // Ejecuta la consulta y obtiene los resultados
            librosPrestados = query.getResultList();
        } catch (Exception e) {
            // Imprime la traza de la excepción si ocurre un error
            e.printStackTrace();
        } finally {
            // Cierra la sesión de Hibernate
            if (session != null) {
                session.close();
            }
        }
        // Devuelve la lista de libros prestados al lector
        return librosPrestados;
    }

    // Método para obtener los libros disponibles para préstamo
    public List<Libro> librosDisponiblesParaPrestamo() {
        Session session = null;
        List<Libro> librosDisponibles = null;
        try {
            // Abre una sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            // Crea una consulta HQL para obtener los libros disponibles para préstamo
            Query<Libro> query = session.createQuery(
                "SELECT l FROM Libro l WHERE l.disponible = true", Libro.class);
            // Ejecuta la consulta y obtiene los resultados
            librosDisponibles = query.getResultList();
        } catch (Exception e) {
            // Imprime la traza de la excepción si ocurre un error
            e.printStackTrace();
        } finally {
            // Cierra la sesión de Hibernate
            if (session != null) {
                session.close();
            }
        }
        // Devuelve la lista de libros disponibles para préstamo
        return librosDisponibles;
    }

    // Método para obtener el historial de préstamos de un lector
    public List<Prestamo> historialPrestamosPorLector(Lector lector) {
        Session session = null;
        List<Prestamo> historialPrestamos = null;
        try {
            // Abre una sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            // Crea una consulta HQL para obtener el historial de préstamos del lector
            Query<Prestamo> query = session.createQuery(
                "FROM Prestamo p WHERE p.lector = :lector", Prestamo.class);
            // Establece el parámetro del lector en la consulta
            query.setParameter("lector", lector);
            // Ejecuta la consulta y obtiene los resultados
            historialPrestamos = query.getResultList();
        } catch (Exception e) {
            // Imprime la traza de la excepción si ocurre un error
            e.printStackTrace();
        } finally {
            // Cierra la sesión de Hibernate
            if (session != null) {
                session.close();
            }
        }
        // Devuelve el historial de préstamos del lector
        return historialPrestamos;
    }
}

