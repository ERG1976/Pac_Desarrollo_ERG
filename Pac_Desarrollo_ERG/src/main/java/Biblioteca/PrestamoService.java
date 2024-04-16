package Biblioteca;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PrestamoService {

    public void crearPrestamo(Prestamo prestamo) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(prestamo);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Prestamo obtenerPrestamoPorId(int id) {
        Session session = null;
        Prestamo prestamo = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            prestamo = session.get(Prestamo.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return prestamo;
    }

    public void actualizarPrestamo(Prestamo prestamo) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(prestamo);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void borrarPrestamo(Prestamo prestamo) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(prestamo);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Libro> librosActualmentePrestadosALector(Lector lector) {
        Session session = null;
        List<Libro> librosPrestados = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Libro> query = session.createQuery(
                "SELECT p.libro FROM Prestamo p WHERE p.lector = :lector AND p.fechadevolucion IS NULL", Libro.class);
            query.setParameter("lector", lector);
            librosPrestados = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return librosPrestados;
    }

    public List<Libro> librosDisponiblesParaPrestamo() {
        Session session = null;
        List<Libro> librosDisponibles = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Libro> query = session.createQuery(
                "SELECT l FROM Libro l WHERE l.disponible = true", Libro.class);
            librosDisponibles = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return librosDisponibles;
    }

    public List<Prestamo> historialPrestamosPorLector(Lector lector) {
        Session session = null;
        List<Prestamo> historialPrestamos = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Prestamo> query = session.createQuery(
                "FROM Prestamo p WHERE p.lector = :lector", Prestamo.class);
            query.setParameter("lector", lector);
            historialPrestamos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return historialPrestamos;
    }
}
