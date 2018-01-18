/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.BHabitacion;
import Datos.BProducto;
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
public class FProducto {
    private Conexion mysql= new Conexion();
    private Connection connect=mysql.conectar();
    private String consulta="";
    public Integer registrostotales;
    
    public DefaultTableModel listar(String buscar){
        DefaultTableModel modelo;
        
        String [] titulos = {"Id","Nombre","Descripción","Unidad Medida","Precio Venta"};
        
        String [] dato = new String [5];
        
        registrostotales=0;
        modelo = new DefaultTableModel(null,titulos);
        
        consulta="Select * from producto where nombre like '%"+ buscar + "%' order by idproducto desc";
        
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            while(rs.next()){
                dato [0]=rs.getString("idproducto");
                dato [1]=rs.getString("nombre");
                dato [2]=rs.getString("descripcion");
                dato [3]=rs.getString("unidad_medida");
                dato [4]=rs.getString("precio_venta");
                
                registrostotales=registrostotales+1;
                modelo.addRow(dato);
                
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    public boolean insertar (BProducto dts){
        consulta="insert into producto (nombre,descripcion,unidad_medida,precio_venta)" +
                "values (?,?,?,?)";
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getUnidad_medida());
            pst.setDouble(4, dts.getPrecio_venta());
            
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
    
    public boolean editar (BProducto dts){
        consulta="update producto set nombre=?,descripcion=?,unidad_medida=?,precio_venta=?"+
                " where idproducto=?";
        
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getUnidad_medida());
            pst.setDouble(4, dts.getPrecio_venta());
            pst.setInt(5, dts.getIdproducto());
            
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
    
    public boolean eliminar (BProducto dts){
        consulta="delete from producto where idproducto=?";
        
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setInt(1, dts.getIdproducto());

            
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
}
