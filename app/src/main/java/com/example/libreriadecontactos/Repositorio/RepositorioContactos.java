package com.example.libreriadecontactos.Repositorio;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.libreriadecontactos.ConexionBaseDeDatos.Contacto;
import com.example.libreriadecontactos.ConexionBaseDeDatos.ContactosDB;
import com.example.libreriadecontactos.ConexionBaseDeDatos.ContactosDao;

import java.util.ArrayList;
import java.util.List;

public class RepositorioContactos {

    private ContactosDao contactosDao;
    private LiveData<List<Contacto>> todosLosContactos;

    public RepositorioContactos(Application app){

        ContactosDB contactosDB = ContactosDB.getBaseDatosContactos(app);
        contactosDao = contactosDB.contactosDAO();
        todosLosContactos = contactosDao.seleccionarTablaContactos();

    }

    public LiveData<List<Contacto>> getTodosLosContactos() {
        return todosLosContactos;
    }
    public void InsertaContacto(Contacto nuevoContacto){
        ContactosDB.baseDatosEscritor.execute(()->contactosDao.insert(nuevoContacto));
    }
    public void BorrarContacto(int contacto){
        ContactosDB.baseDatosEscritor.execute(()->contactosDao.BorrarContactoPorId(contacto));

    }
    public void ModificarContacto(Contacto contacto){
        ContactosDB.baseDatosEscritor.execute(()-> contactosDao.ModificarContacto(contacto));
    }
    public Contacto getContacto(int posicion){
        return todosLosContactos.getValue().get(posicion);
    }

}
