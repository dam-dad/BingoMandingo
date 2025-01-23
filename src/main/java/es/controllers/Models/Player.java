package es.controllers.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {

    private IntegerProperty idCarton;
    private StringProperty nombre;
    private StringProperty apellido;
    private IntegerProperty puntuacion;
    private Carton carton;

    // Constructor
    public Player(int idCarton, String nombre, String apellido, int puntuacion) {
        this.idCarton = new SimpleIntegerProperty(idCarton);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.puntuacion = new SimpleIntegerProperty(puntuacion);
        this.carton = null;
    }

    // Getters y Setters

    public Carton getCarton() {
        return carton;
    }

    public void setCarton(Carton carton) {
        this.carton = carton;
    }
    public int getIdCarton() {
        return idCarton.get();
    }

    public void setIdCarton(int idCarton) {
        this.idCarton.set(idCarton);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public int getPuntuacion() {
        return puntuacion.get();
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion.set(puntuacion);
    }

    // Propiedades JavaFX
    public IntegerProperty idCartonProperty() {
        return idCarton;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public IntegerProperty puntuacionProperty() {
        return puntuacion;
    }

    @Override
    public String toString() {
        return nombre.get() + " " + apellido.get() + (carton != null ? " con Cartón " + carton.getId() : "");
    }


}
