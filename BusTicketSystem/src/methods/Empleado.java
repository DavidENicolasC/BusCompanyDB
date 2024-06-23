/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import LoginDB.ConectarOracleDB;
import java.sql.SQLException;

/**
 *
 * @author David
 */
public class Empleado extends ConectarOracleDB {
    public Object[] getUsuario(String usr, char psw[]){
        // Busca un empleado usando como parametro su usuario y contraseña
        // Se conecta a la base de datos
       System.out.println("Conectando a la base de datos...");
       conectar();
       // variable que guardara los datos del empleado
       Object employee [];
       try{       
            employee = new Object[3];
            // consulta
            sentenciaSQL  = "SELECT EMPLEADO.ID, NOMBRES, PASS " +
                            "FROM EMPLEADO " +
                            "JOIN USUARIOSAPP " +
                            "ON EMPLEADO.ID = USUARIOSAPP.EMPLEADO_ID " +
                            "WHERE EMPLEADO.ID LIKE ? " +
                            "AND PASS LIKE ?";           
            ps = conn.prepareStatement(sentenciaSQL);
            // asigna los parametros
            ps.setString(1, usr);
            ps.setString(2, String.valueOf(psw));
            rs = ps.executeQuery();
            if (rs.next()){
                // Recupera los valores
                employee [0] = rs.getString(1);
                employee [1] = rs.getString(2);
                employee [2] = rs.getString(3);
            }
            else 
                employee [0] = 0;
            return employee;
       }
       catch (SQLException ex){
           System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "getUsuario");
            return null;
        }
       finally{
           desconectar();           
       }
    }
}
