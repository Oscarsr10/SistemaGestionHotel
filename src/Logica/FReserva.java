/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.BHabitacion;
import Datos.BProducto;
import Datos.BReserva;
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
public class FReserva {
    private Conexion mysql= new Conexion();
    private Connection connect=mysql.conectar();
    private String consulta="";
    public Integer registrostotales;
    
    public DefaultTableModel listar(String buscar){
        DefaultTableModel modelo;
        
        String [] titulos = {"Id","Idhabitacion","Numero","Idcliente","Cliente","Idtrabajador","Trabajador","Tipo Reserva","Fecha Reserva","Fecha Llegada","Fecha Salida","Costo","Estado"};
        
        String [] dato = new String [13];
        
        registrostotales=0;
        modelo = new DefaultTableModel(null,titulos);
        
        consulta="Select r.idreserva,r.idhabitacion,h.numero,r.idcliente,"+
                "(select nombre from persona where idpersona=r.idcliente)as clienten,"+
                "(select primer_apellido from persona where idpersona=r.idcliente)as clienteap,"+
                "r.idtrabajador, (select nombre from persona where idpersona=r.idtrabajador)as trabajadorn,"+
                "(select primer_apellido from persona where idpersona=r.idtrabajador)as trabajadorap,"+
                "r.tipo_reserva,r.fecha_reserva,r.fec"
                + "ha_llegada,r.fecha_salida,"+
                "r.costo_alojamiento,r.estado from reserva r inner join habitacion h on r.idhabitacion=h.idhabitacion where r.fecha_reserva like '%"+ buscar + "%' order by idreserva desc";
        
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            while(rs.next()){
                dato [0]=rs.getString("idreserva");
                dato [1]=rs.getString("idhabitacion");
                dato [2]=rs.getString("numero");
                dato [3]=rs.getString("idcliente");
                dato [4]=rs.getString("clienten") + " " + rs.getString("clienteap");
                dato [5]=rs.getString("idtrabajador");
                dato [6]=rs.getString("trabajadorn") + " " + rs.getString("trabajadorap");
                dato [7]=rs.getString("tipo_reserva");
                dato [8]=rs.getString("fecha_reserva");
                dato [9]=rs.getString("fecha_llegada");
                dato [10]=rs.getString("fecha_salida");
                dato [11]=rs.getString("costo_alojamiento");
                dato [12]=rs.getString("estado");
                
                registrostotales=registrostotales+1;
                modelo.addRow(dato);
                
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    public boolean insertar (BReserva dts){
        consulta="insert into reserva (idhabitacion,idcliente,idtrabajador,tipo_reserva,fecha_reserva,fecha_llegada,fecha_salida,costo_alojamiento,estado)" +
                "values (?,?,?,?,?,?,?,?,?)";
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setInt(1, dts.getIdhabitacion());
            pst.setInt(2, dts.getIdcliente());
            pst.setInt(3, dts.getIdtrabajador());
            pst.setString(4, dts.getTipo_reserva());
            pst.setDate(5, dts.getFecha_reserva());
            pst.setDate(6, dts.getFecha_llegada());
            pst.setDate(7, dts.getFecha_salida());
            pst.setDouble(8, dts.getCosto_alojamiento());
            pst.setString(9, dts.getEstado());
            
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
    
    public boolean editar (BReserva dts){
        consulta="update reserva set idhabitacion=?,idcliente=?,idtrabajador=?,tipo_reserva=?,fecha_reserva=?,fecha_llegada=?,fecha_salida=?,costo_alojamiento=?,estado=?"+
                " where idreserva=?";
        
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setInt(1, dts.getIdhabitacion());
            pst.setInt(2, dts.getIdcliente());
            pst.setInt(3, dts.getIdtrabajador());
            pst.setString(4, dts.getTipo_reserva());
            pst.setDate(5, dts.getFecha_reserva());
            pst.setDate(6, dts.getFecha_llegada());
            pst.setDate(7, dts.getFecha_salida());
            pst.setDouble(8, dts.getCosto_alojamiento());
            pst.setString(9, dts.getEstado());
            pst.setInt(10, dts.getIdreserva());
            
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
    
    public boolean pagar (BReserva dts){
        consulta="update reserva set estado='Pagada'"+
                " where idreserva=?";
        
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            
            pst.setInt(1, dts.getIdreserva());
            
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
    
    public boolean eliminar (BReserva dts){
        consulta="delete from reserva where idreserva=?";
        
        try {
            
            PreparedStatement pst = connect.prepareStatement(consulta);
            pst.setInt(1, dts.getIdreserva());

            
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
