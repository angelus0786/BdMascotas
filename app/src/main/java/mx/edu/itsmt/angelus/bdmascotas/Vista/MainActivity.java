package mx.edu.itsmt.angelus.bdmascotas.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import mx.edu.itsmt.angelus.bdmascotas.Controlador.MascotaControl;
import mx.edu.itsmt.angelus.bdmascotas.Modelo.Mascota;
import mx.edu.itsmt.angelus.bdmascotas.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nombre,edad,propietario;
    Spinner spGenero,spEspecie;
    Button btRegistro,btCita;
    ArrayAdapter<String> arrayAdapterG,arrayAdapterE;
    String generos[]={"Macho","Hembra"};
    String especie[]={"Canino","Felino","Roedor","Reptil","Ave"};
    Mascota mascota;
    MascotaControl mc;

    public void vincular() {
        nombre = findViewById(R.id.etnombre);
        edad = findViewById(R.id.etedad);
        propietario = findViewById(R.id.etpropietario);
        spGenero = findViewById(R.id.sp_genero);
        spEspecie = findViewById(R.id.sp_especie);
        btRegistro = findViewById(R.id.bt_registrar);
        btCita = findViewById(R.id.bt_cita);

        //iniciando los spinner
        arrayAdapterG
           = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,generos);
        arrayAdapterE
           = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,especie);

        spGenero.setAdapter(arrayAdapterG);
        spEspecie.setAdapter(arrayAdapterE);
        //botones
        btRegistro.setOnClickListener(this);
        btCita.setOnClickListener(this);
    }

    public  void registrarMascota() {

        if (nombre.getText().toString().isEmpty()||
        edad.getText().toString().isEmpty()||
        propietario.getText().toString().isEmpty()){
            Toast.makeText(this,"LLenar todos los campos",Toast.LENGTH_LONG).show();

        }else{
            mascota=new Mascota();
            mascota.setNombre(nombre.getText().toString());
            mascota.setEspecie(spEspecie.getSelectedItem().toString());
            mascota.setEdad(Integer.parseInt(edad.getText().toString()));
            mascota.setGenero(spGenero.getSelectedItem().toString());
            mascota.setPropietario(propietario.getText().toString());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vincular();
    }

    @Override
    public void onClick(View v) {
        mc=new MascotaControl(getApplicationContext());
        switch (v.getId()){
            case R.id.bt_registrar:
                registrarMascota();
                System.out.println(mascota.toString());
                mc.openBD();
                long bandera = mc.insertar(mascota);

                if (bandera>0){
                    Toast.makeText(this,"Registro Almacenado",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(this,"Registro No Almacenado",Toast.LENGTH_LONG).show();
                }
                mc.closeBD();
                break;
            case R.id.bt_cita:
                Intent intent = new Intent(MainActivity.this,CitasActivity.class);
                startActivity(intent);
                break;
        }
    }
}
