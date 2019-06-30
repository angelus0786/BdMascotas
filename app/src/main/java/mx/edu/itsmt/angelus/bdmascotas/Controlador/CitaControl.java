package mx.edu.itsmt.angelus.bdmascotas.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import mx.edu.itsmt.angelus.bdmascotas.Modelo.Cita;


public class CitaControl {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase database;

    public CitaControl(Context context) {
        openHelper =new  BasedeDatos(context);
        this.database = database;
    }

    public  void openBD() {
        try{
            database=openHelper.getWritableDatabase();
        }catch(Exception e){
            System.out.println("Excepcion de openBD");
        }
    }
    public  void closeBD() {
        openHelper.close();
    }

    public long insertar(Cita cita) {
        long i=0;
        try{
            ContentValues cv = new ContentValues();
            cv.put("fecha",cita.getFecha());
            cv.put("hora",cita.getHora());
            cv.put("mascota",cita.getIdMascota());
            i =database.insert("CITAS",null,cv);
        }catch (SQLException e){
            System.out.println("Exception SQL"+e.getMessage());
        }
        return i;
    }

    public List<String> getAll() {
        Cursor c;
        List<String> listado = new ArrayList<>();
        c = database.rawQuery("Select a.idMascota,a.nombre,b.fecha,b.hora,a.propietario" +
                        " From mascotas AS a, citas AS b" +
                        " WHERE a.idMascota=b.mascota;",null);
        while(c.moveToNext()){
            listado.add("IdMascota:"+
                    c.getInt(0)+"\nNombre:"+
                    c.getString(1)+"\nFecha:"+
                    c.getString(2)+"\nHora: "+
                    c.getString(3)+"\nPropietario:"+
                    c.getString(4));

        }
        c.close();
        return listado;
    }
    //citas repetidas
    public long buscar(String fecha,String hora){
        Cursor cursor;
        long contador=0;
        Cita objCita = new Cita();
        cursor = database.rawQuery("SELECT idCita FROM citas" +
                " WHERE fecha LIKE '"
                +fecha+"' and hora LIKE '"+hora+"'",null);
        while (cursor.moveToNext()){
          contador++;
          objCita.setIdCita(cursor.getInt(0));

        }
        cursor.close();
        return  contador;//objCita;
    }//buscar


}
