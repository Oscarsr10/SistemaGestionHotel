/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.BCliente;
import Datos.BProducto;
import Datos.BTrabajador;
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
public class FTrabajador {
    private Conexion mysql= new Conexion();
    private Connection connect=mysql.conectar();
    private String consulta="";
    private String consultaA2="";
    public Integer registrostotales;
    
    public DefaultTableModel listar(String buscar){
        DefaultTableModel modelo;
        
        String[] titulos = {"ID", "Nombre", "Primer apellido", "Segundo apellido", "Doc", "Número Documento", "Dirección", 
            "Teléfono", "Email", "Salario","Acceso","Usuario","Clave","Estado"};

        String[] dato = new String[14];

        registrostotales = 0;
        modelo = new DefaultTableModel(null, titulos);

        consulta = "select p.idpersona,p.nombre,p.primer_apellido,p.segundo_apellido,p.tipo_documento,p.num_documento,"
                + "p.direccion,p.telefono,p.email,t.salario,t.acceso,t.usuario,t.clave,t.estado from persona p inner join trabajador t "
                + "on p.idpersona=t.idpersona where num_documento like '%"
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
                dato[9] = rs.getString("salario");
                dato[10] = rs.getString("acceso");
                dato[11] = rs.getString("usuario");
                dato[12] = rs.getString("clave");
                dato[13] = rs.getString("estado");

                registrostotales = registrostotales + 1;
                modelo.addRow(dato);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(BTrabajador dts) {
        consulta = "insert into persona (nombre,primer_apellido,segundo_apellido,tipo_documento,num_documento,direccion,telefono,email)"
                + "values (?,?,?,?,?,?,?,?)";
        consultaA2 = "insert into trabajador (idpersona,salario,acceso,usuario,clave,estado)"
                + "values ((select idpersona from persona order by idpersona desc limit 1),?,?,?,?,?)";
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

            pst2.setDouble(1, dts.getSalario());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getUsuario());
            pst2.setString(4, dts.getClave());
            pst2.setString(5, dts.getEstado());

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

    public boolean editar(BTrabajador dts) {
        consulta = "update persona set nombre=?,primer_apellido=?,segundo_apellido=?,tipo_documento=?,num_documento=?,"
                + " direccion=?,telefono=?,email=? where idpersona=?";
        
        consultaA2 = "update trabajador set salario=?,acceso=?,usuario=?,clave=?,estado=?"
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

            pst2.setDouble(1, dts.getSalario());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getUsuario());
            pst2.setString(4, dts.getClave());
            pst2.setString(5, dts.getEstado());
            pst2.setInt(6, dts.getIdpersona());

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

    public boolean eliminar(BTrabajador dts) {
        consulta = "delete from trabajador where idpersona=?";
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
    public DefaultTableModel login(String usuario, String clave){
        DefaultTableModel modelo;
        
        String[] titulos = {"ID", "Nombre", "Primer apellido", "Segundo apellido", "Doc", "Número Documento", "Dirección", 
            "Acceso","Usuario","Clave","Estado"};

        String[] dato = new String[8];

        registrostotales = 0;
        modelo = new DefaultTableModel(null, titulos);

        consulta = "select p.idpersona,p.nombre,p.primer_apellido,p.segundo_apellido,"
                + "t.acceso,t.usuario,t.clave,t.estado from persona p inner join trabajador t "
                + "on p.idpersona=t.idpersona where t.usuario='"
                + usuario + "' and t.clave='" + clave + "' and t.estado='A'";

        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                dato[0] = rs.getString("idpersona");
                dato[1] = rs.getString("nombre");
                dato[2] = rs.getString("primer_apellido");
                dato[3] = rs.getString("segundo_apellido");
                dato[4] = rs.getString("acceso");
                dato[5] = rs.getString("usuario");
                dato[6] = rs.getString("clave");
                dato[7] = rs.getString("estado");

                registrostotales = registrostotales + 1;
                modelo.addRow(dato);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
}
