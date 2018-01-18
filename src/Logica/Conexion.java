/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Oscar
 */
public class Conexion {
    public String db="gestionhotel";
    public String url="jdbc:mysql://127.0.0.1/" +db;
    public String usuario="root";
    public String clave="";
    
    public Conexion(){
    }
    
    public Connection conectar(){
        Connection enlace=null;
        
        try {   
            Class.forName("org.gjt.mm.mysql.Driver");
            enlace=DriverManager.getConnection(this.url, this.usuario, this.clave);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return enlace;
    }
}
