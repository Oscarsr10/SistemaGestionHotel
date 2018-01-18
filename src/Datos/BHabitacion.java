/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author Oscar
 */
public class BHabitacion {
    private int idhabitacion;
    private String numero;
    private String planta;
    private String descripcion;
    private String caracteristicas;
    private Double precio_dia;
    private String estado;
    private String tipo;

    public BHabitacion(int idhabitacion, String numero, String planta, String descripcion, String caracteristicas, Double precio_dia, String estado, String tipo) {
        this.idhabitacion = idhabitacion;
        this.numero = numero;
        this.planta = planta;
        this.descripcion = descripcion;
        this.caracteristicas = caracteristicas;
        this.precio_dia = precio_dia;
        this.estado = estado;
        this.tipo = tipo;
    }

    public BHabitacion() {
    }

    public int getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Double getPrecio_dia() {
        return precio_dia;
    }

    public void setPrecio_dia(Double precio_dia) {
        this.precio_dia = precio_dia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
    
    
}
