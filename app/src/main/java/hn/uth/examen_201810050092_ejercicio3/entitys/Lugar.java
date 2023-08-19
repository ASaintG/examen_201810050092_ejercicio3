package hn.uth.examen_201810050092_ejercicio3.entitys;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "lugar_table")
public class Lugar implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idlugar")
    private int idlugar;
    @NonNull
    @ColumnInfo(name = "lugar")
    private String lugar;
    @NonNull
    @ColumnInfo(name = "tipoLugar")
    private  String tipoLugar;

    @NonNull
    @ColumnInfo(name = "longitud")
    private Double longitud;
    @NonNull
    @ColumnInfo(name = "latitud")
    private Double latitud;
    @NonNull
    @ColumnInfo(name = "descripcion")
    private String descripcion;
    @NonNull
    @ColumnInfo(name = "retorno")
    private int retorno;


    public Lugar(@NonNull String lugar, @NonNull String tipoLugar, @NonNull Double longitud, @NonNull Double latitud, @NonNull String descripcion,  int retorno) {
        this.lugar = lugar;
        this.tipoLugar = tipoLugar;

        this.longitud = longitud;
        this.latitud = latitud;
        this.descripcion = descripcion;
        this.retorno = retorno;
    }

    public int getIdlugar() {
        return idlugar;
    }

    public void setIdlugar(int idlugar) {
        this.idlugar = idlugar;
    }

    @NonNull
    public String getLugar() {
        return lugar;
    }

    public void setLugar(@NonNull String lugar) {
        this.lugar = lugar;
    }

    @NonNull
    public String getTipoLugar() {
        return tipoLugar;
    }

    public void setTipoLugar(@NonNull String tipoLugar) {
        this.tipoLugar = tipoLugar;
    }

    @NonNull
    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(@NonNull Double longitud) {
        this.longitud = longitud;
    }
    public String getLongitudeStr() {
        return longitud+"";
    }
    @NonNull
    public Double getLatitud() {
        return latitud;
    }
    public String getLatitudeStr() {
        return latitud+"";
    }

    public void setLatitud(@NonNull Double latitud) {
        this.latitud = latitud;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }


    public int getRetorno() {
        return retorno;
    }

    public void setRetorno(int retorno) {
        this.retorno = retorno;
    }
}



