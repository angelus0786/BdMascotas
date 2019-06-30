package mx.edu.itsmt.angelus.bdmascotas.Modelo;

import java.io.Serializable;

public class Mascota implements Serializable {

    private int idMascota;
    private String nombre;
    private String especie;
    private int edad;
    private String genero;
    private String propietario;

    public Mascota() {
    }

    public Mascota(String nombre, String especie, int edad, String genero, String propietario) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.genero = genero;
        this.propietario = propietario;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", propietario='" + propietario + '\'' +
                '}';
    }
}
