/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.BConsumo;
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
public class FConsumo {
    private Conexion mysql= new Conexion();
    private Connection connect=mysql.conectar();
    private String consulta="";
    public Integer registrostotales;
    public Double consumostotales;
    
    public DefaultTableModel listar(String buscar){
        DefaultTableModel modelo;
        
        String [] titulos = {"Id","Idreserva","Idproducto","Producto","Cantidad","Precio Venta","Estado"};
        
        String [] dato = new String [7];
        
        registrostotales=0;
        consumostotales=0.0;
        modelo = new DefaultTableModel(null,titulos);
        
        consulta="Select c.idconsumo,c.idreserva,c.idproducto,p.nombre,c.cantidad,c.precio_venta " 
              + ",c.estado from consumo c inner join producto p on c.idproducto=p.idproducto"
               + " where c.idreserva =" + buscar + " order by c.idconsumo desc";
        
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            while(rs.next()){
                dato [0]=rs.getString("idconsumo");
                dato [1]=rs.getString("idreserva");
                dato [2]=rs.getString("idproducto");
                dato [3]=rs.getString("nombre");
                dato [4]=rs.getString("cantidad");
                dato [5]=rs.getString("precio_venta");
                dato [6]=rs.getString("estado");
                
                registrostotales=registrostotales+1;
                consumostotales=consumostotales + (rs.getDouble("cantidad") * rs.getDouble("precio_venta") );
                modelo.addRow(dato);
                
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    public boolean insertar (BConsumo dts){
        consulta="insert into consumo (idreserva,idproducto,cantidad,precio_venta,estado)" +
                "values (?,?,?,?,?)";
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setInt(1, dts.getIdreserva());
            pst.setInt(2, dts.getIdproducto());
            pst.setDouble(3, dts.getCantidad());
            pst.setDouble(4, dts.getPrecio_venta());
            pst.setString(5, dts.getEstado());
            
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
    
    public boolean editar (BConsumo dts){
        consulta="update consumo set idreserva=?,idproducto=?,cantidad=?,precio_venta=?,estado=?"+
                " where idconsumo=?";
        
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setInt(1, dts.getIdreserva());
            pst.setInt(2, dts.getIdproducto());
            pst.setDouble(3, dts.getCantidad());
            pst.setDouble(4, dts.getPrecio_venta());
            pst.setString(5, dts.getEstado());
           
            pst.setInt(6, dts.getIdconsumo());
            
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
    
    public boolean eliminar (BConsumo dts){
        consulta="delete from consumo where idconsumo=?";
        
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setInt(1, dts.getIdconsumo());

            
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
