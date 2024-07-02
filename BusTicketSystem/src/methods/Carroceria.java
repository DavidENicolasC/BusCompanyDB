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
public class Carroceria extends ConectarOracleDB {
    public Object [][] selectCarrocerias(){
        // Obtiene todos lo idiomas
        // Conecta a la base de datos
       conectar();
       // Variable para guardar los idiomas
       Object[][] carrocerias;
       // Contador
       int i = 0;
       int cant = 0;
       try{
           // consulta
           sentenciaSQL = "SELECT COUNT(*) FROM CARROCERIA";
           // prepara la consulta
           ps = conn.prepareStatement(sentenciaSQL);
           // ejecuta la consulta
           rs = ps.executeQuery();
           // Ontiene la cantidad de registros
           if (rs.next()){
               cant = rs.getInt(1);
           }
           
           // consulta los datos
           carrocerias = new Object[cant][6];
           sentenciaSQL  = "SELECT * FROM CARROCERIA";           
           ps = conn.prepareStatement(sentenciaSQL);
           rs = ps.executeQuery();
              
           // Recorre el result set para obtener los datos y asignarlos
           // al arreglo
           while (rs.next()){
               carrocerias[i][0] = rs.getInt(1);    //ID
               carrocerias[i][1] = rs.getString(2); //MARCA
               carrocerias[i][2] = rs.getString(3); //MODELO
               carrocerias[i][3] = rs.getString(4); //VERSION
               carrocerias[i][4] = rs.getInt(5);    //NUMERO_DE_ASIENTOS
               carrocerias[i][5] = rs.getString(6); //BANIO
               i++;
           }     
           return carrocerias;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "getCarrocerias");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object[] selectCarroceria(int id){
        // Consulta una pelicula buscando por su id
        // Se conecta a la base de datos
        conectar();
        Object carroceria[];// Para guardar la cantidad de registros
        try{
           
            // Consulta los registros 
            carroceria = new Object[9];          
            sentenciaSQL  = "SELECT * FROM CARROCERIA" +
                            " WHERE ID = ?";
            // prepara la sentencia 
            ps = conn.prepareStatement(sentenciaSQL);
            //Se ingresa el parametro del ID
            ps.setInt(1, id);
            // Ejecuta la sentencia y la asigna al result set 
            rs = ps.executeQuery();
            rs.next();
            // Recupera los valores
            carroceria[0] = rs.getInt(1);       //ID
            carroceria[1] = rs.getString(2);    //MARCA
            carroceria[2] = rs.getString(3);    //MODELO
            carroceria[3] = rs.getString(4);    //VERSION
            carroceria[4] = rs.getInt(5);       //NUMERO_DE_ASIENTOS
            carroceria[5] = rs.getString(6);    //BANIO
            return carroceria;
       }
       catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectCarroceria");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public int insertCarroceria(Object[] direccion){
        // Guarda una pelicula
        // Se conecta a la base de datos
        conectar();
        try{
            int id = 0;
            
            sentenciaSQL = "SELECT COUNT(*) FROM CARROCERIA";
            ps = conn.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            // guarda el nuevo id
            if (rs.next()){
                id = rs.getInt(1);
            }
            id++;
            
            // inserta mandando todos los datos, incluido en nuevo id
            sentenciaSQL = "INSERT INTO CARROCERIA VALUES(?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sentenciaSQL);
            // Asigna los valores del arreglo  
            ps.setInt(1, id);                                           //ID
            ps.setString(2, direccion[1].toString());                   //MARCA
            ps.setString(3, direccion[2].toString());                   //MODELO
            ps.setString(4, direccion[3].toString());                   //VERSION
            ps.setInt(5, Integer.parseInt(direccion[4].toString()));    //NUMERO_DE_ASIENTOS
            ps.setString(6, direccion[5].toString());                   //BANIO
            ps.executeUpdate();
            return id;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "insertCarroceria");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int updateCarroceria(Object carroceria[]){
        // se conecta a la base de datos
       conectar();
       try{
           // actualiza los datos
           sentenciaSQL = "UPDATE CARROCERIA SET" +
                          " MARCA = ?" +
                          ", MODELO = ?" +
                          ", VERSION = ?" +
                          ", NUMERO_DE_ASIENTOS = ?" +
                          ", BANIO = ?" +
                          " WHERE ID = ?";
            ps = conn.prepareStatement(sentenciaSQL);
            
            ps.setString(1, carroceria[1].toString());                  //MARCA
            ps.setString(2, carroceria[2].toString());                  //MODELO
            ps.setString(3, carroceria[3].toString());                  //VERSION
            ps.setInt(4, Integer.parseInt(carroceria[4].toString()));   //NUMERO_DE_ASIENTOS
            ps.setString(5, carroceria[5].toString());                  //BANIO
            ps.setInt(6, Integer.parseInt(carroceria[0].toString()));   //ID
            ps.executeUpdate();
            return Integer.parseInt(carroceria[0].toString());
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "updateCarroceria");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int deleteCarroceria(int id){
        // Si borro el casting, borra una pelicula 
        // Conecta a la base de datos
        conectar();
        try{
            sentenciaSQL = "DELETE FROM CARROCERIA WHERE ID = ?";
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
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "deleteCarroceria");
            if (ex.getErrorCode() ==  2292)
                return 1;
            return 2;
        }
        finally{
           desconectar();
        }
    }
}
