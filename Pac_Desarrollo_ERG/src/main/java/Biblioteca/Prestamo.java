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

    // Constructor
    public Prestamo() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Date getFechaPrestamo() {
        return fechaprestamo;
    }

    public void setFechaPrestamo(Date fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    public Date getFechaDevolucion() {
        return fechadevolucion;
    }

    public void setFechaDevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }
}
