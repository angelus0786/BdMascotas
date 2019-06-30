package mx.edu.itsmt.angelus.bdmascotas.Modelo;

import java.io.Serializable;

public class Cita implements Serializable {

    private int idMascota;
    private String fecha;
    private String Hora;
    private int idCita;

    public Cita() {
    }

    public Cita(int idMascota, String fecha, String hora) {
        this.idMascota = idMascota;
        this.fecha = fecha;
        Hora = hora;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "idMascota=" + idMascota +
                ", fecha='" + fecha + '\'' +
                ", Hora='" + Hora + '\'' +
                '}';
    }
}
