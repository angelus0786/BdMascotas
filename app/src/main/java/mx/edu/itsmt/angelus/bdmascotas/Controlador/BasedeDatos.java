package mx.edu.itsmt.angelus.bdmascotas.Controlador;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class BasedeDatos extends SQLiteOpenHelper {

    private static String NombreBD="MASCOTALANDIA.SQL";
    private static String TB_MASCOTA="MASCOTAS";
    private static String TB_CITA="CITAS";
    private static int VERSION=1;
    Context context;
    private static String SQL1="CREATE TABLE "+TB_MASCOTA+" ("+
            "idMascota INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT, especie TEXT, edad INTEGER, " +
            "genero TEXT, propietario TEXT);";
    private static String SQL2="CREATE TABLE "+TB_CITA+" ("+
            "idCita INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "fecha TEXT, hora TEXT, mascota INTEGER," +
            "FOREIGN KEY(mascota) REFERENCES MASCOTAS(idMascota));";

    public BasedeDatos(Context context) {
        super(context, NombreBD, null, VERSION);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(SQL1);
        db.execSQL(SQL2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MASCOTAS;");
        db.execSQL("DROP TABLE IF EXISTS CITAS;");
        onCreate(db);
    }
}
