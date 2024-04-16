package Biblioteca;

import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Inicio del programa...");

        // Configuración de Hibernate
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build());
        Session session = sessionFactory.openSession();

        System.out.println("Configuración realizada");

        try {
            // Muestra el menú principal
            mostrarMenu(session);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ha ocurrido un error en la aplicación.");
        } finally {
            // Cerrar sesión y fábrica de sesiones de Hibernate
            session.close();
            sessionFactory.close();
        }
    }

    // Método para mostrar el menú principal
    private static void mostrarMenu(Session session) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            // Menú de la biblioteca
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                                         BIBLIOTECA                                                                                                    ");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.println("1- Insertar Libro");
            System.out.println("2- Insertar Lector");
            System.out.println("3- Listado de Libros");
            System.out.println("4- Listado de Lectores");
            System.out.println("5- Ver Libro por ID");
            System.out.println("6- Ver Lector por ID");
            System.out.println("7- Salir");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.print("Seleccione una Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            // Switch para manejar las opciones del menú
            switch (opcion) {
                case 1:
                    // Lógica para insertar un libro
                    insertarLibro(session, scanner);
                    break;
                case 2:
                    // Lógica para insertar un lector
                    insertarLector(session, scanner);
                    break;
                case 3:
                    // Lógica para listar libros
                    listarLibros(session);
                    break;
                case 4:
                    // Lógica para listar lectores
                    listarLectores(session);
                    break;
                case 5:
                    // Lógica para ver un libro por ID
                    verLibroPorId(session, scanner);
                    break;
                case 6:
                    // Lógica para ver un lector por ID
                    verLectorPorId(session, scanner);
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }

        scanner.close();
    }

    // Método para insertar un libro en la base de datos
    private static void insertarLibro(Session session, Scanner scanner) {
        System.out.println("Ingrese el título del libro:");
        String titulo = scanner.nextLine();

        System.out.println("Ingrese el autor del libro:");
        String autor = scanner.nextLine();

        System.out.println("Ingrese el año de publicación del libro:");
        int anoPublicacion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.println("El libro está disponible para préstamo? (true/false):");
        boolean disponible = scanner.nextBoolean();
        scanner.nextLine(); // Consumir el salto de línea

        // Validación de campos obligatorios
        if (titulo.isEmpty() || autor.isEmpty()) {
            System.out.println("El título y el autor del libro son campos obligatorios.");
            return;
        }

        // Crear un nuevo objeto Libro con los datos ingresados por el usuario
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setAnoPublicacion(anoPublicacion);
        libro.setDisponible(disponible);

        // Insertar el libro en la base de datos
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(libro);
            tx.commit();
            System.out.println("Libro insertado correctamente.");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error al insertar el libro: " + e.getMessage());
        } finally {
            session.clear(); // Limpiar la sesión para evitar objetos en caché
        }
    }

    // Método para insertar un lector en la base de datos
    private static void insertarLector(Session session, Scanner scanner) {
        System.out.println("Ingrese el nombre del lector:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el apellido del lector:");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese el correo electrónico del lector:");
        String email = scanner.nextLine();

        // Validación de campos obligatorios
        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty()) {
            System.out.println("El nombre, apellido y correo electrónico del lector son campos obligatorios.");
            return;
        }

        // Crear un nuevo objeto Lector con los datos ingresados por el usuario
        Lector lector = new Lector();
        lector.setNombre(nombre);
        lector.setApellido(apellido);
        lector.setEmail(email);

        // Insertar el lector en la base de datos
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(lector);
            tx.commit();
            System.out.println("Lector insertado correctamente.");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error al insertar el lector: " + e.getMessage());
        }
    }

    // Método para listar todos los libros en la base de datos
    private static void listarLibros(Session session) {
        System.out.println("Listado de Libros:");
        Query<Libro> query = session.createQuery("FROM Libro", Libro.class);
        List<Libro> libros = query.list();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            for (Libro libro : libros) {
                System.out.println("ID: " + libro.getId() + ", Título: " + libro.getTitulo() + ", Autor: " + libro.getAutor() + ", Año de Publicación: " + libro.getAnoPublicacion() + ", Disponible: " + libro.isDisponible());
            }
        }
    }

    // Método para listar todos los lectores en la base de datos
    private static void listarLectores(Session session) {
        System.out.println("Listado de Lectores:");
        Query<Lector> query = session.createQuery("FROM Lector", Lector.class);
        List<Lector> lectores = query.list();
        if (lectores.isEmpty()) {
            System.out.println("No hay lectores registrados.");
        } else {
            for (Lector lector : lectores) {
                System.out.println("ID: " + lector.getId() + ", Nombre: " + lector.getNombre() + ", Apellido: " + lector.getApellido() + ", Email: " + lector.getEmail());
            }
        }
    }

    // Método para ver la información de un libro por su ID
    private static void verLibroPorId(Session session, Scanner scanner) {
        System.out.println("Ingrese el ID del libro:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        Libro libro = session.get(Libro.class, id);
        if (libro != null) {
            System.out.println("ID: " + libro.getId() + ", Título: " + libro.getTitulo() + ", Autor: " + libro.getAutor() + ", Año de Publicación: " + libro.getAnoPublicacion() + ", Disponible: " + libro.isDisponible());
        } else {
            System.out.println("No se encontró ningún libro con el ID proporcionado.");
        }
    }

    // Método para ver la información de un lector por su ID
    private static void verLectorPorId(Session session, Scanner scanner) {
        System.out.println("Ingrese el ID del lector:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        Lector lector = session.get(Lector.class, id);
        if (lector != null) {
            System.out.println("ID: " + lector.getId() + ", Nombre: " + lector.getNombre() + ", Apellido: " + lector.getApellido() + ", Email: " + lector.getEmail());
        } else {
            System.out.println("No se encontró ningún lector con el ID proporcionado.");
        }
    }

}

