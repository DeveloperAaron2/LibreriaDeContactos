package com.example.libreriadecontactos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.libreriadecontactos.ConexionBaseDeDatos.Contacto;

public class Detalles extends AppCompatActivity {
    private Button botonModificar;
    private EditText editTextNombre;
    private EditText editTextNumeroTel;
    private EditText editTextCorreo;
    private EditText editTextDireccion;
    private Button botonBorrar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detallescontacto);
        botonModificar = (Button) findViewById(R.id.buttonModificar);
        botonBorrar = (Button) findViewById(R.id.buttonBorrar);
        editTextNombre = (EditText) findViewById(R.id.EditTextNombreDetalles);
        editTextNumeroTel =(EditText) findViewById(R.id.EditTextNumeroDeTelefonoDetalles);
        editTextCorreo = (EditText) findViewById(R.id.EditTextCorreoDetalles);
        editTextDireccion = (EditText) findViewById(R.id.EditTextDireccionDetalles);
        InicializarComponentes();
        RecogeIntent();

    }

    private void RecogeIntent() {
        Intent contactoRecogido = getIntent();
        Contacto contacto = (Contacto) contactoRecogido.getSerializableExtra("contactoAbierto");
        editTextNombre.setText(contacto.getNombreContacto());
        editTextNumeroTel.setText(contacto.getNumeroTelefono());
        editTextCorreo.setText(contacto.getEmail());
        editTextDireccion.setText(contacto.getDireccion());
    }

    private void InicializarComponentes() {
        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DevuelveContacto = getIntent();
                DevuelveContacto.putExtra("BorrarContacto","Borrar");
                setResult(RESULT_OK,DevuelveContacto);
                finish();
            }
        });

        botonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DevuelveContacto = getIntent();
                Contacto contacto = new Contacto(
                        editTextNombre.getText().toString(),
                        editTextNumeroTel.getText().toString(),
                        editTextCorreo.getText().toString(),
                        editTextDireccion.getText().toString()
                );
                DevuelveContacto.putExtra("contactoModificar",contacto);
                DevuelveContacto.putExtra("ContactoModificar","modificar");
                setResult(RESULT_OK,DevuelveContacto);
                finish();
            }
        });
    }
}
