/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.BHabitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oscar
 */
public class FHabitacion {
    private Conexion mysql= new Conexion();
    private Connection connect=mysql.conectar();
    private String consulta="";
    public Integer registrostotales;
    
    public DefaultTableModel listar(String buscar){
        DefaultTableModel modelo;
        
        String [] titulos = {"Id","Número","Planta","Descripción","Características","Precio","Estado","Tipo"};
        
        String [] dato = new String [8];
        
        registrostotales=0;
        modelo = new DefaultTableModel(null,titulos);
        
        consulta="Select * from habitacion where planta like '%"+ buscar + "%' order by idhabitacion";
        
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            while(rs.next()){
                dato [0]=rs.getString("idhabitacion");
                dato [1]=rs.getString("numero");
                dato [2]=rs.getString("planta");
                dato [3]=rs.getString("descripcion");
                dato [4]=rs.getString("caracteristicas");
                dato [5]=rs.getString("precio_dia");
                dato [6]=rs.getString("estado");
                dato [7]=rs.getString("tipo");
                
                registrostotales=registrostotales+1;
                modelo.addRow(dato);
                
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    public boolean insertar (BHabitacion dts){
        consulta="insert into habitacion (numero,planta,descripcion,caracteristicas,precio_dia,estado,tipo)" +
                "values (?,?,?,?,?,?,?)";
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPlanta());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecio_dia());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo());
            
            int n=pst.executeUpdate();
            
            if (n!=0){
                return true;
            }
            else  {
                return false;
            }
            
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
        }
    }
    
    public boolean editar (BHabitacion dts){
        consulta="update habitacion set numero=?,planta=?,descripcion=?,caracteristicas=?,precio_dia=?,estado=?,tipo=?"+
                " where idhabitacion=?";
        
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPlanta());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecio_dia());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo());
            pst.setInt(8, dts.getIdhabitacion());
            
            int n=pst.executeUpdate();
            
            if (n!=0){
                return true;
            }
            else  {
                return false;
            }
            
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
        }
    }
    
    public boolean desocupar (BHabitacion dts){
        consulta="update habitacion set estado='Disponible'"+
                " where idhabitacion=?";
        
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            
            pst.setInt(1, dts.getIdhabitacion());
            
            int n=pst.executeUpdate();
            
            if (n!=0){
                return true;
            }
            else  {
                return false;
            }
            
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
        }
    }
    
    public boolean ocupar (BHabitacion dts){
        consulta="update habitacion set estado='Ocupado'"+
                " where idhabitacion=?";
        
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            
            pst.setInt(1, dts.getIdhabitacion());
            
            int n=pst.executeUpdate();
            
            if (n!=0){
                return true;
            }
            else  {
                return false;
            }
            
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
        }
    }
    
    public boolean eliminar (BHabitacion dts){
        consulta="delete from habitacion where idhabitacion=?";
        
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setInt(1, dts.getIdhabitacion());

            
            int n=pst.executeUpdate();
            
            if (n!=0){
                return true;
            }
            else  {
                return false;
            }
            
        } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
        }
    }
    
    public DefaultTableModel listarvista(String buscar){
        DefaultTableModel modelo;
        
        String [] titulos = {"Id","Número","Planta","Descripción","Características","Precio","Estado","Tipo"};
        
        String [] dato = new String [8];
        
        registrostotales=0;
        modelo = new DefaultTableModel(null,titulos);
        
        consulta="Select * from habitacion where planta like '%"+ buscar + "%' and estado='Disponible' order by idhabitacion";
        
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            while(rs.next()){
                dato [0]=rs.getString("idhabitacion");
                dato [1]=rs.getString("numero");
                dato [2]=rs.getString("planta");
                dato [3]=rs.getString("descripcion");
                dato [4]=rs.getString("caracteristicas");
                dato [5]=rs.getString("precio_dia");
                dato [6]=rs.getString("estado");
                dato [7]=rs.getString("tipo");
                
                registrostotales=registrostotales+1;
                modelo.addRow(dato);
                
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
}
