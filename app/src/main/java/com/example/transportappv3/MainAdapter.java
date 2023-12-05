package com.example.transportappv3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

     @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model){
        holder.nombre.setText(model.getNombre());
        holder.apellidoPaterno.setText(model.getApellidoPaterno());
        holder.apellidoMaterno.setText(model.getApellidoMaterno());
        holder.correo.setText(model.getCorreo());
        holder.edad.setText(model.getEdad());
        holder.nombreUser.setText(model.getNombreUser());
        holder.sexo.setText(model.getSexo());
        holder.telefono.setText(model.getTelefono());
     }

     @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent, false);
        return new myViewHolder(view);
     }

     static class myViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, apellidoPaterno, apellidoMaterno, correo, edad, nombreUser, sexo, telefono;
        public myViewHolder(@NonNull View itemView){
            super(itemView);
            nombre = (TextView)itemView.findViewById(R.id.nombreT);
            apellidoPaterno = (TextView)itemView.findViewById(R.id.apellidoPT);
            apellidoMaterno = (TextView)itemView.findViewById(R.id.apellidoMT);
            nombreUser = (TextView)itemView.findViewById(R.id.nombreUserT);
            telefono = (TextView) itemView.findViewById(R.id.telefonoT);
            correo = (TextView) itemView.findViewById(R.id.correoT);
            edad = (TextView) itemView.findViewById(R.id.edadT);
            sexo = (TextView) itemView.findViewById(R.id.sexoT);
        }
     }

}
