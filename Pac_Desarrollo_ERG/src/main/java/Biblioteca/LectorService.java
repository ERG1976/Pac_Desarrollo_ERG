package Biblioteca;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class LectorService {

    // Método para insertar un lector en la base de datos
    public void insertarLector(Lector lector) {
        Session session = null;
        Transaction tx = null;
        try {
            // Abrir sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            // Iniciar transacción
            tx = session.beginTransaction();
            // Guardar el lector en la base de datos
            session.save(lector);
            // Confirmar la transacción
            tx.commit();
        } catch (Exception e) {
            // En caso de error, realizar rollback
            if (tx != null) {
                tx.rollback();
            }
            // Imprimir la excepción
            e.printStackTrace();
        } finally {
            // Cerrar sesión de Hibernate
            if (session != null) {
                session.close();
            }
        }
    }

    // Método para obtener todos los lectores de la base de datos
    public List<Lector> obtenerTodosLosLectores() {
        Session session = null;
        List<Lector> lectores = null;
        try {
            // Abrir sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            // Consultar todos los lectores
            lectores = session.createQuery("FROM Lector", Lector.class).list();
        } catch (Exception e) {
            // Imprimir la excepción en caso de error
            e.printStackTrace();
        } finally {
            // Cerrar sesión de Hibernate
            if (session != null) {
                session.close();
            }
        }
        return lectores;
    }

    // Método para obtener un lector por su ID
    public Lector obtenerLectorPorId(int id) {
        Session session = null;
        Lector lector = null;
        try {
            // Abrir sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            // Obtener el lector por su ID
            lector = session.get(Lector.class, id);
        } catch (Exception e) {
            // Imprimir la excepción en caso de error
            e.printStackTrace();
        } finally {
            // Cerrar sesión de Hibernate
            if (session != null) {
                session.close();
            }
        }
        return lector;
    }
}

