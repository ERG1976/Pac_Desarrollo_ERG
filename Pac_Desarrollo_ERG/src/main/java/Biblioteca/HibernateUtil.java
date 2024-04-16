package Biblioteca;

import java.util.function.Consumer;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    // Declaramos la factoría de sesiones como estática y final para que sea única y no cambie.
    private static final SessionFactory sessionFactory;

    // Inicializador estático para configurar Hibernate y crear la factoría de sesiones.
    static {
        try {
            System.out.println("Cargando configuración de Hibernate...");
            // Configuración de Hibernate desde el archivo hibernate.cfg.xml
            Configuration configuration = new Configuration().configure();
            // Creación del constructor de la factoría de sesiones
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            System.out.println("Creando factoría de sesiones...");
            // Construcción de la factoría de sesiones
            sessionFactory = configuration.buildSessionFactory(builder.build());
            System.out.println("Factoría de sesiones creada con éxito.");
        } catch (Throwable ex) {
            // Manejo de excepciones en caso de error al inicializar la factoría de sesiones
            System.err.println("Error al inicializar la factoría de sesiones: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Método para obtener la factoría de sesiones
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Método para cerrar la factoría de sesiones
    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    // Método para ejecutar acciones dentro de una transacción
    public static void doInTransaction(Consumer<Session> action) throws IllegalStateException, SystemException {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            // Iniciamos una nueva transacción
            tx = (Transaction) session.beginTransaction();
            // Ejecutamos la acción proporcionada
            action.accept(session);
            // Commit de la transacción si todo ha ido bien
            tx.commit();
        } catch (Exception e) {
            // En caso de error, realizamos rollback de la transacción
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}

