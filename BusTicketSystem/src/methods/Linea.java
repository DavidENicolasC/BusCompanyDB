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
public class Linea extends ConectarOracleDB {
    public Object [][] selectLineas(){
        // Obtiene todos lo idiomas
        // Conecta a la base de datos
       conectar();
       // Variable para guardar los idiomas
       Object[][] lineas;
       // Contador
       int i = 0;
       int cant = 0;
       try{
           // consulta
           sentenciaSQL = "SELECT COUNT(*) FROM LINEA";
           // prepara la consulta
           ps = conn.prepareStatement(sentenciaSQL);
           // ejecuta la consulta
           rs = ps.executeQuery();
           // Ontiene la cantidad de registros
           if (rs.next()){
               cant = rs.getInt(1);
           }
           
           // consulta los datos
           lineas = new Object[cant][3];
           sentenciaSQL  = "SELECT * FROM LINEA";           
           ps = conn.prepareStatement(sentenciaSQL);
           rs = ps.executeQuery();
              
           // Recorre el result set para obtener los datos y asignarlos
           // al arreglo
           while (rs.next()){
               lineas[i][0] = rs.getInt(1);     //ID
               lineas[i][1] = rs.getString(2);  //NOMBRE
               lineas[i][2] = rs.getString(3);  //TIPO_SERVICIO
               i++;
           }     
           return lineas;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "getLineas");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object[] selectLinea(int id){
        // Consulta una pelicula buscando por su id
        // Se conecta a la base de datos
        conectar();
        Object linea[];// Para guardar la cantidad de registros
        try{
           
            // Consulta los registros 
            linea = new Object[3];          
            sentenciaSQL  = "SELECT * FROM LINEA" +
                            " WHERE ID = ?";
            // prepara la sentencia 
            ps = conn.prepareStatement(sentenciaSQL);
            //Se ingresa el parametro del ID
            ps.setInt(1, id);
            // Ejecuta la sentencia y la asigna al result set 
            rs = ps.executeQuery();
            rs.next();
            // Recupera los valores
            linea[0] = rs.getInt(1);     //ID
            linea[1] = rs.getString(2);  //NOMBRE
            linea[2] = rs.getString(3);  //TIPO_SERVICIO
            return linea;
       }
       catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectLinea");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public int insertLinea(Object[] linea){
        // Guarda una pelicula
        // Se conecta a la base de datos
        conectar();
        try{
            int id = 0;
            
            sentenciaSQL = "SELECT COUNT(*) FROM LINEA";
            ps = conn.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            // guarda el nuevo id
            if (rs.next()){
                id = rs.getInt(1);
            }
            id++;
            
            // inserta mandando todos los datos, incluido en nuevo id
            sentenciaSQL = "INSERT INTO LINEA VALUES(?,?,?)";
            ps = conn.prepareStatement(sentenciaSQL);
            // Asigna los valores del arreglo  
            ps.setInt(1, id);                       //ID
            ps.setString(2, linea[1].toString());   //NOMBRE
            ps.setString(3, linea[2].toString());   //TIPO_SERVICIO
            ps.executeUpdate();
            return id;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "insertLinea");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int updateLinea(Object linea[]){
        // se conecta a la base de datos
       conectar();
       try{
           // actualiza los datos
           sentenciaSQL = "UPDATE DIRECCION SET" +
                          " NOMBRE = ?" +
                          ", TIPO_SERVICIO = ?" +
                          " WHERE ID = ?";
            ps = conn.prepareStatement(sentenciaSQL);
            
            ps.setString(1, linea[1].toString());                   //NOMBRE
            ps.setString(2, linea[2].toString());                   //TIPO_SERVICIO
            ps.setInt(3, Integer.parseInt(linea[0].toString()));    //ID
            ps.executeUpdate();
            return Integer.parseInt(linea[0].toString());
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "updateLinea");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int deleteLinea(int id){
        // Si borro el casting, borra una pelicula 
        // Conecta a la base de datos
        conectar();
        try{
            sentenciaSQL = "DELETE FROM LINEA WHERE ID = ?";
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
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "deleteLinea");
            if (ex.getErrorCode() ==  2292)
                return 1;
            return 2;
        }
        finally{
           desconectar();
        }
    }
}
