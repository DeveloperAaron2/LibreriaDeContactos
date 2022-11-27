package com.example.libreriadecontactos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceScreen;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.libreriadecontactos.Adapter.ContactosListAdapter;
import com.example.libreriadecontactos.ConexionBaseDeDatos.Contacto;
import com.example.libreriadecontactos.OnItemClickListener.ItemClickListener;
import com.example.libreriadecontactos.ViewModel.ContactosViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private final int ANHADIRCONTACTO = 1;
    private final int ABRIRCONTACTO = 2;
    private RecyclerView recyclerViewContactos;
    private ContactosViewModel viewModel;
    private FloatingActionButton bottonAñadir;
    private ContactosListAdapter ADAPTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewContactos = (RecyclerView) findViewById(R.id.reciclerViewContactos);
        bottonAñadir = (FloatingActionButton) findViewById(R.id.floatingActionButtonAñadir);
        ADAPTER = new ContactosListAdapter(new ContactosListAdapter.ContactoDiff());
        recyclerViewContactos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContactos.setAdapter(ADAPTER);
        viewModel = new ViewModelProvider(this).get(ContactosViewModel.class);
        viewModel.getTodosLosContactos().observe(this,contacts -> ADAPTER.submitList(contacts));
        InicializarComponentes();
    }

    private void InicializarComponentes() {
        bottonAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirAñadir;
                abrirAñadir = new Intent(v.getContext(), DetallesAnhadir.class);
                startActivityForResult(abrirAñadir, ANHADIRCONTACTO);
            }
        });
        recyclerViewContactos.addOnItemTouchListener(new ItemClickListener(this, recyclerViewContactos, new ItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int posicion) {
                Intent abrirContacto = new Intent(v.getContext(),Detalles.class);
                Contacto contacto = viewModel.getContacto(posicion);
                abrirContacto.putExtra("posicion",posicion);
                abrirContacto.putExtra("contactoAbierto",contacto);
                startActivityForResult(abrirContacto,ABRIRCONTACTO);
            }
        }));

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ANHADIRCONTACTO && resultCode == RESULT_OK){
            Contacto contacto = (Contacto)data.getSerializableExtra("contactoañadido");
            viewModel.InsertaNuevoContacto(contacto);
        }
        if(requestCode == ABRIRCONTACTO && resultCode == RESULT_OK){
            if(data.getExtras().containsKey("BorrarContacto")){
                int posicion =data.getIntExtra("posicion",0);
                viewModel.BorrarContacto(ADAPTER.getCurrentList().get(posicion).getNumero_Contacto());
                Toast.makeText(this,"Borrado", Toast.LENGTH_SHORT).show();
            }
            else if (data.getExtras().containsKey("contactoModificar")){
                int posicion = data.getIntExtra("posicion",0);
                Contacto contacto = ADAPTER.getCurrentList().get(posicion);
                Contacto contactoModificado = (Contacto) data.getSerializableExtra("contactoModificar");
                contacto.setNombreContacto(contactoModificado.getNombreContacto());
                contacto.setNumeroTelefono(contactoModificado.getNumeroTelefono());
                contacto.setEmail(contactoModificado.getEmail());
                contacto.setDireccion(contactoModificado.getDireccion());
                viewModel.ModificarContacto(contacto);
                Toast.makeText(this,"Modificado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
