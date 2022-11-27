package com.example.libreriadecontactos.ConexionBaseDeDatos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import androidx.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "Contactos_tabla")
public class Contacto implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idContacto")
    private int numero_Contacto;
    @ColumnInfo(name = "nombreContacto")
    private String nombreContacto;
    @ColumnInfo(name = "numeroTelefono")
    private String numeroTelefono;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "direccion")
    private String direccion;

    public Contacto(String nombreContacto, String numeroTelefono, String email, String direccion) {
        this.nombreContacto = nombreContacto;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.direccion = direccion;
    }

    public int getNumero_Contacto() {
        return numero_Contacto;
    }

    public void setNumero_Contacto(int numero_Contacto) {
        this.numero_Contacto = numero_Contacto;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "numero_Contacto=" + numero_Contacto +
                ", nombreContacto='" + nombreContacto + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
