/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.BCliente;
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
public class FCliente {
    private Conexion mysql= new Conexion();
    private Connection connect=mysql.conectar();
    private String consulta="";
    private String consultaA2="";
    public Integer registrostotales;
    
    public DefaultTableModel listar(String buscar){
        DefaultTableModel modelo;
        
        String[] titulos = {"ID", "Nombre", "Primer apellido", "Segundo apellido", "Doc", "Númeo Documento", "Dirección", "Teléfono", "Email", "Código"};

        String[] dato = new String[10];

        registrostotales = 0;
        modelo = new DefaultTableModel(null, titulos);

        consulta = "select p.idpersona,p.nombre,p.primer_apellido,p.segundo_apellido,p.tipo_documento,p.num_documento,"
                + "p.direccion,p.telefono,p.email,c.codigo_cliente from persona p inner join cliente c "
                + "on p.idpersona=c.idpersona where num_documento like '%"
                + buscar + "%' order by idpersona desc";

        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                dato[0] = rs.getString("idpersona");
                dato[1] = rs.getString("nombre");
                dato[2] = rs.getString("primer_apellido");
                dato[3] = rs.getString("segundo_apellido");
                dato[4] = rs.getString("tipo_documento");
                dato[5] = rs.getString("num_documento");
                dato[6] = rs.getString("direccion");
                dato[7] = rs.getString("telefono");
                dato[8] = rs.getString("email");
                dato[9] = rs.getString("codigo_cliente");

                registrostotales = registrostotales + 1;
                modelo.addRow(dato);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(BCliente dts) {
        consulta = "insert into persona (nombre,primer_apellido,segundo_apellido,tipo_documento,num_documento,direccion,telefono,email)"
                + "values (?,?,?,?,?,?,?,?)";
        consultaA2 = "insert into cliente (idpersona,codigo_cliente)"
                + "values ((select idpersona from persona order by idpersona desc limit 1),?)";
        try {

            PreparedStatement pst = connect.prepareStatement(consulta);
            PreparedStatement pst2 = connect.prepareStatement(consultaA2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getPrimer_apellido());
            pst.setString(3, dts.getSegundo_apellido());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());

            pst2.setString(1, dts.getCodigo_cliente());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(BCliente dts) {
        consulta = "update persona set nombre=?,primer_apellido=?,segundo_apellido=?,tipo_documento=?,num_documento=?,"
                + " direccion=?,telefono=?,email=? where idpersona=?";
        
        consultaA2 = "update cliente set codigo_cliente=?"
                + " where idpersona=?";
        try {

            PreparedStatement pst = connect.prepareStatement(consulta);
            PreparedStatement pst2 = connect.prepareStatement(consultaA2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getPrimer_apellido());
            pst.setString(3, dts.getSegundo_apellido());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());
            pst.setInt(9, dts.getIdpersona());

            pst2.setString(1, dts.getCodigo_cliente());
            pst2.setInt(2, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(BCliente dts) {
        consulta = "delete from cliente where idpersona=?";
        consultaA2 = "delete from persona where idpersona=?";

        try {

            PreparedStatement pst = connect.prepareStatement(consulta);
            PreparedStatement pst2 = connect.prepareStatement(consultaA2);

            
            pst.setInt(1, dts.getIdpersona());

            
            pst2.setInt(1, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}
