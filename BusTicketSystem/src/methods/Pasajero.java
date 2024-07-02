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
public class Pasajero extends ConectarOracleDB{
    public Object [][] selectPasajeros(){
        // Obtiene todos lo idiomas
        // Conecta a la base de datos
       conectar();
       // Variable para guardar los idiomas
       Object[][] pasajeros;
       // Contador
       int i = 0;
       int cant = 0;
       try{
           // consulta
           sentenciaSQL = "SELECT COUNT(*) FROM PASAJERO";
           // prepara la consulta
           ps = conn.prepareStatement(sentenciaSQL);
           // ejecuta la consulta
           rs = ps.executeQuery();
           // Ontiene la cantidad de registros
           if (rs.next()){
               cant = rs.getInt(1);
           }
           
           // consulta los datos
           pasajeros = new Object[cant][5];
           sentenciaSQL  = "SELECT * FROM PASAJERO";           
           ps = conn.prepareStatement(sentenciaSQL);
           rs = ps.executeQuery();
              
           // Recorre el result set para obtener los datos y asignarlos
           // al arreglo
           while (rs.next()){
               pasajeros[i][0] = rs.getInt(1);      //ID
               pasajeros[i][1] = rs.getString(2);   //NOMBRES
               pasajeros[i][2] = rs.getString(3);   //APELLIDO_PATERNO
               pasajeros[i][3] = rs.getString(4);   //APELLIDO_MATERNO
               pasajeros[i][4] = rs.getString(5);   //CORREO_ELECTRONICO
               i++;
           }     
           return pasajeros;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectPasajeros");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object[] selectPasajero(int id){
        // Consulta una pelicula buscando por su id
        // Se conecta a la base de datos
        conectar();
        Object pasajero[];// Para guardar la cantidad de registros
        try{
           
            // Consulta los registros 
            pasajero = new Object[5];          
            sentenciaSQL  = "SELECT * FROM PASAJERO" +
                            " WHERE ID = ?";
            // prepara la sentencia 
            ps = conn.prepareStatement(sentenciaSQL);
            //Se ingresa el parametro del ID
            ps.setInt(1, id);
            // Ejecuta la sentencia y la asigna al result set 
            rs = ps.executeQuery();
            rs.next();
            // Recupera los valores
            pasajero[0] = rs.getInt(1);     //ID
            pasajero[1] = rs.getString(2);  //NOMBRES
            pasajero[2] = rs.getString(3);  //APELLIDO_PATERNO
            pasajero[3] = rs.getString(4);  //APELLIDO_MATERNO
            pasajero[4] = rs.getString(5);  //CORREO_ELECTRONICO
            return pasajero;
       }
       catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectPasajero");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public int insertPasajero(Object[] pasajero){
        // Guarda una pelicula
        // Se conecta a la base de datos
        conectar();
        try{
            int id = 0;
            
            sentenciaSQL = "SELECT COUNT(*) FROM PASAJERO";
            ps = conn.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            // guarda el nuevo id
            if (rs.next()){
                id = rs.getInt(1);
            }
            id++;
            
            // inserta mandando todos los datos, incluido en nuevo id
            sentenciaSQL = "INSERT INTO PASAJERO VALUES(?,?,?,?,?)";
            ps = conn.prepareStatement(sentenciaSQL);
            // Asigna los valores del arreglo  
            ps.setInt(1, id);                           //ID
            ps.setString(2, pasajero[0].toString());    //NOMBRES
            ps.setString(3, pasajero[1].toString());    //APELLIDO_PATERNO
            ps.setString(4, pasajero[2].toString());    //APELLIDO_MATERNO
            ps.setString(5, pasajero[3].toString());    //CORREO_ELECTRONICO
            ps.executeUpdate();
            return id;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "insertPasajero");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int updatePasajero(Object pasajero[]){
        // se conecta a la base de datos
       conectar();
       try{
           // actualiza los datos
           sentenciaSQL = "UPDATE PASAJERO SET" +
                          " NOMBRES = ?" +
                          ", APELLIDO_PATERNO = ?" +
                          ", APELLIDO_MATERNO = ?" +
                          ", CORREO_ELECTRONICO = ?" +
                          " WHERE ID = ?";
            ps = conn.prepareStatement(sentenciaSQL);
            
            ps.setString(1, pasajero[1].toString());                //NOMBRES
            ps.setString(2, pasajero[2].toString());                //APELLIDO_PATERNO
            ps.setString(3, pasajero[3].toString());                //APELLIDO_MATERNO
            ps.setString(4, pasajero[4].toString());                //CORREO_ELECTRONICO
            ps.setInt(5, Integer.parseInt(pasajero[0].toString())); //ID
            ps.executeUpdate();
            return Integer.parseInt(pasajero[0].toString());
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "updatePasajero");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int deletePasajero(int id){
        // Si borro el casting, borra una pelicula 
        // Conecta a la base de datos
        conectar();
        try{
            sentenciaSQL = "DELETE FROM PASAJERO WHERE ID = ?";
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
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "deletePasajero");
            if (ex.getErrorCode() ==  2292)
                return 1;
            return 2;
        }
        finally{
           desconectar();
        }
    }
}
