package com.example.libreriadecontactos.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libreriadecontactos.R;

public class ContactosViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageViewHolder;
    public TextView textViewNombreContacto;
    public TextView textViewNumeroContacto;
    public ContactosViewHolder(@NonNull View itemView) {
        super(itemView);
        imageViewHolder = (ImageView) itemView.findViewById(R.id.imageView2);
        textViewNombreContacto = (TextView) itemView.findViewById(R.id.textViewNombreContacto);
        textViewNumeroContacto = (TextView) itemView.findViewById(R.id.textViewNumeroDeTelefonoContacto);
    }
}
