package mx.edu.itsmt.angelus.bdmascotas.Vista;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import mx.edu.itsmt.angelus.bdmascotas.Controlador.CitaControl;
import mx.edu.itsmt.angelus.bdmascotas.Controlador.MascotaControl;
import mx.edu.itsmt.angelus.bdmascotas.Modelo.Cita;
import mx.edu.itsmt.angelus.bdmascotas.Modelo.Mascota;
import mx.edu.itsmt.angelus.bdmascotas.R;

public class CitasActivity extends AppCompatActivity implements View.OnClickListener {

    EditText idMascota,nombre,especie,genero,propietario,fecha,hora;
    Button btBuscar,btCita,btConsulta;
    ImageButton imbfecha,imbhora;
    Mascota mascota;
    MascotaControl mc;
    CitaControl cc;
    Cita cita;
    Calendar calendar=Calendar.getInstance();
    final String CERO="0",BARRA="/";
    int id;
    public  void vincular() {
        idMascota = findViewById(R.id.etidmascota);
        nombre = findViewById(R.id.ednombreMascota);
        especie = findViewById(R.id.ed_especie);
        genero = findViewById(R.id.ed_genero);
        propietario = findViewById(R.id.ed_propietario);
        fecha = findViewById(R.id.ed_fecha);
        hora = findViewById(R.id.ed_hora);
        btBuscar = findViewById(R.id.btbusca);
        btCita = findViewById(R.id.btagendar);
        btConsulta = findViewById(R.id.btconsulta);
        imbfecha = findViewById(R.id.im_fecha);
        imbhora = findViewById(R.id.im_hora);
        //botones
        imbfecha.setOnClickListener(this);
        imbhora.setOnClickListener(this);
        btBuscar.setOnClickListener(this);
        btCita.setOnClickListener(this);
        btConsulta.setOnClickListener(this);

        mascota =  new Mascota();
        mc=new MascotaControl(getApplicationContext());
    }

    public  void busca() {
         id = Integer.parseInt(idMascota.getText().toString());
         mc.openBD();
         mascota =mc.buscar(id);
         nombre.setText(mascota.getNombre());
         especie.setText(mascota.getEspecie());
         genero.setText(mascota.getGenero());
         propietario.setText(mascota.getPropietario());
    }

    public  void hacerCita() {
        cita = new Cita();
        if (fecha.getText().toString().isEmpty()||
        hora.getText().toString().isEmpty()||
        nombre.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Debe establecer fecha y hora",Toast.LENGTH_LONG).show();
        }else {

            cita.setIdMascota(id);
            cita.setFecha(fecha.getText().toString());
            cita.setHora(hora.getText().toString());
            System.out.println(cita.toString());
            cc = new CitaControl(getApplicationContext());
            cc.openBD();
            long bcita = cc.buscar(fecha.getText().toString(), hora.getText().toString());
            System.out.println(bcita);
            if (bcita>0) {
                Toast.makeText(this, "Fecha ocupada", Toast.LENGTH_LONG).show();
            } else {
                long bandera = cc.insertar(cita);
                if (bandera > 0) {
                    Toast.makeText(this, "Cita Agendada", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "No se Agendo", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public  void obtenerFecha() {

      final int anio = calendar.get(Calendar.YEAR);
      final int mes = calendar.get(Calendar.MONTH);
      final int dia = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog seccionFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
             int mesActual = month+1;
               String mesf = (mesActual<10)?CERO+String.valueOf(mesActual):String.valueOf(mesActual);
               String diaf = (dia<10)?CERO+String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
               String aniof = String.valueOf(year);
               fecha.setText(diaf+BARRA+mesf+BARRA+aniof);
            }
        },anio,mes,dia);
        seccionFecha.show();
        seccionFecha.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

    }

    public  void obtenerHora() {
     int hrs = calendar.get(Calendar.HOUR_OF_DAY);
     int min = calendar.get(Calendar.MINUTE);

     TimePickerDialog seleccionhora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

               String horaF =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
               String minutoF = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
               String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                hora.setText(horaF+":"+minutoF+" "+AM_PM);
            }
        },hrs,min,false);
     seleccionhora.show();
     
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);
        vincular();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btbusca:
                busca();
                break;
            case R.id.btagendar:
                hacerCita();
                break;
            case R.id.btconsulta:
                Intent intent =new Intent(CitasActivity.this,ConsultaActivity.class);
                startActivity(intent);
                break;
            case R.id.im_fecha:
                obtenerFecha();
                break;
            case R.id.im_hora:
                obtenerHora();
                break;
        }
    }
}
