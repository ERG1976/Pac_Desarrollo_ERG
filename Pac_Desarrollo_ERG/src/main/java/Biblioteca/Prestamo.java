package Biblioteca;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "lector_id")
    private Lector lector;

    @Temporal(TemporalType.DATE)
    private Date fechaprestamo;

    @Temporal(TemporalType.DATE)
    private Date fechadevolucion;

    // Constructor, getters y setters
}
