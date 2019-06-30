package mx.edu.itsmt.angelus.bdmascotas.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mx.edu.itsmt.angelus.bdmascotas.Modelo.Mascota;

public class MascotaControl {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase database;

    public MascotaControl(Context context) {
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

    public long insertar(Mascota mascota) {
        long i=0;
        try{
            ContentValues cv = new ContentValues();
            cv.put("nombre",mascota.getNombre());
            cv.put("especie",mascota.getEspecie());
            cv.put("edad",mascota.getEdad());
            cv.put("genero",mascota.getGenero());
            cv.put("propietario",mascota.getPropietario());
            i =database.insert("MASCOTAS",null,cv);
        }catch (SQLException e){
            System.out.println("Exception SQL"+e.getMessage());
        }
      return i;
    }

    public Mascota buscar(int id){
        Cursor cursor;
        Mascota objMascota = new Mascota();
        cursor = database.rawQuery("SELECT * FROM Mascotas" +
                " WHERE idMascota LIKE '"
                +id+"'",null);
        while (cursor.moveToNext()){

            objMascota.setIdMascota(cursor.getInt(0));
            objMascota.setNombre(cursor.getString(1));
            objMascota.setEspecie(cursor.getString(2));
            objMascota.setGenero(cursor.getString(4));
            objMascota.setPropietario(cursor.getString(5));
            // listado.add(objetoPersona);
        }
        cursor.close();
        return  objMascota;
    }//buscar


}
