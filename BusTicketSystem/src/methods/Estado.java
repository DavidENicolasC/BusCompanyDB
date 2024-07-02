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
public class Estado extends ConectarOracleDB {
    public Object [][] selectEstados(){
        // Obtiene todos los estados
        // Conecta a la base de datos
       conectar();
       // Variable para guardar los estados
       Object[][] estados;
       // Contador
       int i = 0;
       int cant = 0;
       try{
           // consulta
           sentenciaSQL = "SELECT COUNT(*) FROM ESTADO";
           // prepara la consulta
           ps = conn.prepareStatement(sentenciaSQL);
           // ejecuta la consulta
           rs = ps.executeQuery();
           // Ontiene la cantidad de registros
           if (rs.next()){
               cant = rs.getInt(1);
           }
           
           // consulta los datos
           estados = new Object[cant][2];
           sentenciaSQL  = "SELECT ID, NOMBRE" + 
                   " FROM ESTADO";           
           ps = conn.prepareStatement(sentenciaSQL);
           rs = ps.executeQuery();
              
           // Recorre el result set para obtener los datos y asignarlos
           // al arreglo
           while (rs.next()){
               estados[i][0] = rs.getInt(1);
               estados[i][1] = rs.getString(2);
               i++;
           }     
           return estados;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectEstados");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object[] selectEstado(int id){
        // Consulta una pelicula buscando por su id
        // Se conecta a la base de datos
        conectar();
        Object estado[];// Para guardar la cantidad de registros
        try{
           
            // Consulta los registros 
            estado = new Object[2];          
            sentenciaSQL  = "SELECT * FROM ESTADO" +
                            " WHERE ID = ?";
            // prepara la sentencia 
            ps = conn.prepareStatement(sentenciaSQL);
            //Se ingresa el parametro del ID
            ps.setInt(1, id);
            // Ejecuta la sentencia y la asigna al result set 
            rs = ps.executeQuery();
            rs.next();
            // Recupera los valores
            estado[0] = rs.getInt(1);     //ID
            estado[1] = rs.getString(2);  //NOMBRE
            return estado;
       }
       catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectEstado");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public int insertEstado(Object[] estado){
        // Guarda una pelicula
        // Se conecta a la base de datos
        conectar();
        try{
            int id = 0;
            
            sentenciaSQL = "SELECT MAX(ID) FROM ESTADO";
            ps = conn.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            // guarda el nuevo id
            if (rs.next()){
                id = rs.getInt(1);
            }
            id++;
            
            // inserta mandando todos los datos, incluido en nuevo id
            sentenciaSQL = "INSERT INTO ESTADO VALUES(?,?)";
            ps = conn.prepareStatement(sentenciaSQL);
            // Asigna los valores del arreglo  
            //ID
            ps.setInt(1, id);
            //NOMBRE
            ps.setString(2, estado[1].toString());
            ps.executeUpdate();
            return id;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "insertEstado");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int updateEstado(Object estado[]){
        // se conecta a la base de datos
       conectar();
       try{
           // actualiza los datos
           sentenciaSQL = "UPDATE ESTADO SET" +
                   " NOMBRE = ?" +
                   "WHERE ID = ?";
            ps = conn.prepareStatement(sentenciaSQL);
            
            ps.setString(1, estado[1].toString());
            ps.setInt(2, Integer.parseInt(estado[0].toString()));
            ps.executeUpdate();
            return Integer.parseInt(estado[0].toString());
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "updateEstado");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int deleteEstado(int id){
        // Si borro el casting, borra una pelicula 
        // Conecta a la base de datos
        conectar();
        try{
            sentenciaSQL = "DELETE FROM ESTADO WHERE ID = ?";
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
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "deleteEstado");
            if (ex.getErrorCode() ==  2292)
                return 1;
            return 2;
        }
        finally{
           desconectar();
        }
    }
}
