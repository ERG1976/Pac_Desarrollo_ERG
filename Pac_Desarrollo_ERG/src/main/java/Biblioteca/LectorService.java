package Biblioteca;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class LectorService {

    public void insertarLector(Lector lector) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(lector);
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

    public List<Lector> obtenerTodosLosLectores() {
        Session session = null;
        List<Lector> lectores = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            lectores = session.createQuery("FROM Lector", Lector.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return lectores;
    }

    public Lector obtenerLectorPorId(int id) {
        Session session = null;
        Lector lector = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            lector = session.get(Lector.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return lector;
    }
}
