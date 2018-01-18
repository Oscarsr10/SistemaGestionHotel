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
public class BTrabajador extends BPersona {
    private Double salario;
    private String acceso;
    private String usuario;
    private String clave;
    private String estado;

    public BTrabajador() {
    }

    public BTrabajador(Double salario, String acceso, String usuario, String clave, String estado) {
        this.salario = salario;
        this.acceso = acceso;
        this.usuario = usuario;
        this.clave = clave;
        this.estado = estado;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
