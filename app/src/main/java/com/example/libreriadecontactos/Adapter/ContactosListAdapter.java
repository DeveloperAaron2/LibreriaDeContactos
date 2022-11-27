package com.example.libreriadecontactos.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.libreriadecontactos.ConexionBaseDeDatos.Contacto;
import com.example.libreriadecontactos.R;

public class ContactosListAdapter extends ListAdapter<Contacto,ContactosViewHolder> {
    public ContactosListAdapter(@NonNull DiffUtil.ItemCallback<Contacto> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ContactosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactovista,parent,false);

        return new ContactosViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactosViewHolder holder, int position) {
        Contacto contacto = getItem(position);
        holder.textViewNombreContacto.setText(contacto.getNombreContacto());
        holder.textViewNumeroContacto.setText(contacto.getNumeroTelefono());
        
    }
    public static class ContactoDiff extends DiffUtil.ItemCallback<Contacto>{

        @Override
        public boolean areItemsTheSame(@NonNull Contacto oldItem, @NonNull Contacto newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contacto oldItem, @NonNull Contacto newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
    }
}
