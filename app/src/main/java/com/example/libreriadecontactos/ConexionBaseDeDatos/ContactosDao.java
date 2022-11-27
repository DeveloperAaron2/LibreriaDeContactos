package com.example.libreriadecontactos.ConexionBaseDeDatos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactosDao {
    @Insert
    void insert(Contacto contacto);
    @Query("SELECT * from Contactos_tabla ORDER BY nombreContacto desc")
    LiveData<List<Contacto>> seleccionarTablaContactos();
    @Query("DELETE from Contactos_tabla")
    void borrarTodo();
   @Query("DELETE FROM Contactos_tabla WHERE idContacto =:id")
   void BorrarContactoPorId(int id);
    @Update
    void ModificarContacto(Contacto contacto);
}
