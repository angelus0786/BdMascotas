package mx.edu.itsmt.angelus.bdmascotas.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ListView;

import mx.edu.itsmt.angelus.bdmascotas.Controlador.Adaptador;
import mx.edu.itsmt.angelus.bdmascotas.Controlador.CitaControl;
import mx.edu.itsmt.angelus.bdmascotas.R;

public class ConsultaActivity extends AppCompatActivity {

    ListView listView;
    CitaControl cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        cc = new CitaControl(getApplicationContext());
        cc.openBD();
        listView = findViewById(R.id.listview);

        Adaptador adapter = new Adaptador(getApplicationContext(), cc.getAll());
        listView.setAdapter(adapter);
    }
}
