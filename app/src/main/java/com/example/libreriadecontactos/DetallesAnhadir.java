package com.example.libreriadecontactos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.libreriadecontactos.ConexionBaseDeDatos.Contacto;

public class DetallesAnhadir extends AppCompatActivity {
    private Button botonAgregar;
    private EditText editTextNombre;
    private EditText editTextNumeroTel;
    private EditText editTextCorreo;
    private EditText editTextDireccion;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anhadircontactovista);
        botonAgregar = (Button) findViewById(R.id.buttonAgregarContacto);
        editTextNombre = (EditText) findViewById(R.id.textViewNombreContacto);
        editTextNumeroTel =(EditText) findViewById(R.id.textViewNumeroDeTelefonoAnhadir);
        editTextCorreo = (EditText) findViewById(R.id.textViewCorreoAnhadir);
        editTextDireccion = (EditText) findViewById(R.id.textViewDireccionAnhadir);
        InicializarComponentes();

    }

    private void InicializarComponentes() {

        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacto contacto = new Contacto(
                        editTextNombre.getText().toString(),
                        editTextNumeroTel.getText().toString(),
                        editTextCorreo.getText().toString(),
                        editTextDireccion.getText().toString()
                );
                Intent contactoañadido = new Intent();
                contactoañadido.putExtra("contactoañadido",contacto);
                setResult(RESULT_OK,contactoañadido);
                finish();
            }
        });

    }
}
