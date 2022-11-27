package com.example.libreriadecontactos.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.libreriadecontactos.ConexionBaseDeDatos.Contacto;
import com.example.libreriadecontactos.Repositorio.RepositorioContactos;

import java.util.ArrayList;
import java.util.List;

public class ContactosViewModel extends AndroidViewModel {
    private RepositorioContactos repositorioContactos;
    private LiveData<List<Contacto>> todosLosContactos;

    public ContactosViewModel(@NonNull Application application) {
        super(application);
        repositorioContactos = new RepositorioContactos(application);
        todosLosContactos = repositorioContactos.getTodosLosContactos();

    }

    public LiveData<List<Contacto>> getTodosLosContactos() {
        return todosLosContactos;
    }
    public void InsertaNuevoContacto(Contacto nuevoContacto){
        repositorioContactos.InsertaContacto(nuevoContacto);
    }
    public void BorrarContacto(int contacto){
       repositorioContactos.BorrarContacto(contacto);
    }
    public void ModificarContacto(Contacto contacto){
        repositorioContactos.ModificarContacto(contacto);
    }
    public Contacto getContacto(int posicion){
        return repositorioContactos.getContacto(posicion);
    }
}
