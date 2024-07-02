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
public class Terminal extends ConectarOracleDB {
    public Object [][] selectTerminales(){
        // Obtiene todos lo idiomas
        // Conecta a la base de datos
       conectar();
       // Variable para guardar los idiomas
       Object[][] terminales;
       // Contador
       int i = 0;
       int cant = 0;
       try{
           // consulta
           sentenciaSQL = "SELECT COUNT(*) FROM TERMINAL";
           // prepara la consulta
           ps = conn.prepareStatement(sentenciaSQL);
           // ejecuta la consulta
           rs = ps.executeQuery();
           // Ontiene la cantidad de registros
           if (rs.next()){
               cant = rs.getInt(1);
           }
           
           // consulta los datos
           terminales = new Object[cant][4];
           sentenciaSQL  = "SELECT * FROM TERMINAL";           
           ps = conn.prepareStatement(sentenciaSQL);
           rs = ps.executeQuery();
           // Recorre el result set para obtener los datos y asignarlos
           // al arreglo
           while (rs.next()){
               terminales[i][0] = rs.getInt(1);     //ID
               terminales[i][1] = rs.getString(2);  //NOMBRE
               terminales[i][2] = rs.getInt(3);     //DIRECCION_ID
               terminales[i][3] = rs.getInt(4);     //GERENTE_ID
               i++;
           }     
           return terminales;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectTerminales");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object[] selectTerminal(int id){
        // Consulta una pelicula buscando por su id
        // Se conecta a la base de datos
        conectar();
        Object terminal[];// Para guardar la cantidad de registros
        try{
           
            // Consulta los registros 
            terminal = new Object[4];          
            sentenciaSQL  = "SELECT * FROM TERMINAL" +
                            " WHERE ID = ?";
            // prepara la sentencia 
            ps = conn.prepareStatement(sentenciaSQL);
            //Se ingresa el parametro del ID
            ps.setInt(1, id);
            // Ejecuta la sentencia y la asigna al result set 
            rs = ps.executeQuery();
            rs.next();
            // Recupera los valores
            terminal[0] = rs.getInt(1);     //ID
            terminal[1] = rs.getString(2);  //NOMBRE
            terminal[2] = rs.getString(3);  //DIRECCION_ID
            terminal[3] = rs.getString(4);  //GERENTE_ID
            return terminal;
       }
       catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectTerminal");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public int insertTerminal(Object[] terminal){
        // Guarda una pelicula
        // Se conecta a la base de datos
        conectar();
        try{
            int id = 0;
            
            sentenciaSQL = "SELECT MAX(ID) FROM TERMINAL";
            ps = conn.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            // guarda el nuevo id
            if (rs.next()){
                id = rs.getInt(1);
            }
            id++;
            
            // inserta mandando todos los datos, incluido en nuevo id
            sentenciaSQL = "INSERT INTO TERMINAL VALUES(?,?,?,?)";
            ps = conn.prepareStatement(sentenciaSQL);
            // Asigna los valores del arreglo  
            //ID
            ps.setInt(1, id);
            //NOMBRE
            ps.setString(2, terminal[1].toString());
            //DIRECCION_ID
            ps.setInt(3, Integer.parseInt(terminal[2].toString()));
            //GERENTE_ID
            ps.setInt(4, Integer.parseInt(terminal[3].toString()));
            ps.executeUpdate();
            return id;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "insertTerminal");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int updateTerminal(Object terminal[]){
        // se conecta a la base de datos
       conectar();
       try{
           // actualiza los datos
           sentenciaSQL = "UPDATE TERMINAL SET" +
                          " NOMBRE = ?" +
                          ", DIRECCION_ID = ?" +
                          ", GERENTE_ID = ?" +
                          "WHERE ID = ?";
            ps = conn.prepareStatement(sentenciaSQL);
            
            ps.setString(1, terminal[1].toString());
            ps.setInt(2, Integer.parseInt(terminal[2].toString()));
            ps.setInt(3, Integer.parseInt(terminal[3].toString()));
            ps.setInt(4, Integer.parseInt(terminal[0].toString()));
            ps.executeUpdate();
            return Integer.parseInt(terminal[0].toString());
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "updateTerminal");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int deleteTerminal(int id){
        // Si borro el casting, borra una pelicula 
        // Conecta a la base de datos
        conectar();
        try{
            sentenciaSQL = "DELETE FROM TERMINAL" +
                    " WHERE ID = ?";
            ps = conn.prepareStatement(sentenciaSQL);
            ps.setInt(1, id);
            int res = ps.executeUpdate();
            if (res == 1){
                return 0;
            }            
            return -1;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "deleteTerminal");
            if (ex.getErrorCode() ==  2292)
                return 1;
            return 2;
        }
        finally{
           desconectar();
        }
    }
}
