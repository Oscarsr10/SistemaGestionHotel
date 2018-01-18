/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.BHabitacion;
import Datos.BPago;
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
public class FPago {
    private Conexion mysql= new Conexion();
    private Connection connect=mysql.conectar();
    private String consulta="";
    public Integer registrostotales;
    
    public DefaultTableModel listar(String buscar){
        DefaultTableModel modelo;
        
        String [] titulos = {"ID","Idreserva","Comprobante","Número","Iva","Total","Fecha Emisión","Fecha Pago"};
        
        String [] dato = new String [8];
        
        registrostotales=0;
        modelo = new DefaultTableModel(null,titulos);
        
        consulta="Select * from pago where idreserva="+ buscar + " order by idpago desc";
        
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            while(rs.next()){
                dato [0]=rs.getString("idpago");
                dato [1]=rs.getString("idreserva");
                dato [2]=rs.getString("tipo_comprobante");
                dato [3]=rs.getString("num_comprobante");
                dato [4]=rs.getString("iva");
                dato [5]=rs.getString("total_pago");
                dato [6]=rs.getString("fecha_emision");
                dato [7]=rs.getString("fecha_pago");
                
                
                registrostotales=registrostotales+1;
                modelo.addRow(dato);
                
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    public boolean insertar (BPago dts){
        consulta="insert into pago (idreserva,tipo_comprobante,num_comprobante,iva,total_pago,fecha_emision,fecha_pago)" +
                "values (?,?,?,?,?,?,?)";
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setInt(1, dts.getIdreserva());
            pst.setString(2, dts.getTipo_comprobante());
            pst.setString(3, dts.getNum_comprobante());
            pst.setDouble(4, dts.getIva());
            pst.setDouble(5, dts.getTotal_pago());
            pst.setDate(6, dts.getFecha_emision());
            pst.setDate(7, dts.getFecha_pago());
            
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
    
    public boolean editar (BPago dts){
        consulta="update pago set idreserva=?,tipo_comprobante=?,num_comprobante=?,iva=?,total_pago=?,fecha_emision=?,fecha_pago=?"+
                " where idpago=?";
        
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setInt(1, dts.getIdreserva());
            pst.setString(2, dts.getTipo_comprobante());
            pst.setString(3, dts.getNum_comprobante());
            pst.setDouble(4, dts.getIva());
            pst.setDouble(5, dts.getTotal_pago());
            pst.setDate(6, dts.getFecha_emision());
            pst.setDate(7, dts.getFecha_pago());
           
           pst.setInt(8, dts.getIdpago());
            
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
    
    public boolean eliminar (BPago dts){
        consulta="delete from pago where idpago=?";
        
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setInt(1, dts.getIdpago());

            
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
