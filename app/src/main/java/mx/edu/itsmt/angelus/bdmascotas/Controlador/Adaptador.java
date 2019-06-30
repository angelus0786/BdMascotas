package mx.edu.itsmt.angelus.bdmascotas.Controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import mx.edu.itsmt.angelus.bdmascotas.R;

public class Adaptador extends BaseAdapter {

    Context context;
    List<String> lista;
    public Adaptador(Context context, List<String> lista) {
        this.context=context;
        this.lista=lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        LayoutInflater layoutInflater =
                LayoutInflater.from(context);
        vista =
                layoutInflater.inflate(R.layout.datos_cita,null);
        TextView tvid = vista.findViewById(R.id.tvid);
        tvid.setText(lista.get(position));
        return vista;

    }
}
