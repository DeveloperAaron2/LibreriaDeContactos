package com.example.libreriadecontactos.ConexionBaseDeDatos;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import android.content.Context;

import androidx.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Contacto.class},version = 1,exportSchema = false)
public abstract class ContactosDB extends RoomDatabase {

    private static final String DB_NOMBRE = "contactos_agenda";

    public abstract ContactosDao contactosDAO();

    //Hilos necesarios para la escritura de la BBDD permitiendo el uso de la aplicación durante esta tarea.
    private static final int NUMERO_HILOS = 4;
    public static final ExecutorService baseDatosEscritor = Executors.newFixedThreadPool(NUMERO_HILOS);

    //"Patron singleton" con la intención de que el objeto generado por esta clase sea único en toda la aplicación
    private static volatile ContactosDB INSTANCIA;

    public static ContactosDB getBaseDatosContactos(final Context contexto){
        if (INSTANCIA == null) {
            synchronized (ContactosDB.class){
                if (INSTANCIA == null)
                    INSTANCIA = Room.databaseBuilder(contexto.getApplicationContext(),
                                    ContactosDB.class,DB_NOMBRE)
                            .addCallback(callBackAnimales).build();
            }
        }

        return INSTANCIA;
    }

    private static RoomDatabase.Callback callBackAnimales = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            baseDatosEscritor.execute(() -> {
                        ContactosDao dao = INSTANCIA.contactosDAO();
                        dao.borrarTodo();
                        dao.insert(new Contacto("Mamá","662370800","mama@gmail.com","micasa"));
                        dao.insert(new Contacto("PAPA","662413521","papa@gmail.com","micasa"));
                    }
            );
        }
    };
}
