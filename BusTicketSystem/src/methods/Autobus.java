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
public class Autobus extends ConectarOracleDB {
    public Object [][] selectAutobuses(){
        // Obtiene todos lo idiomas
        // Conecta a la base de datos
       conectar();
       // Variable para guardar los idiomas
       Object[][] autobuses;
       // Contador
       int i = 0;
       int cant = 0;
       try{
           // consulta
           sentenciaSQL = "SELECT COUNT(*) FROM AUTOBUS";
           // prepara la consulta
           ps = conn.prepareStatement(sentenciaSQL);
           // ejecuta la consulta
           rs = ps.executeQuery();
           // Ontiene la cantidad de registros
           if (rs.next()){
               cant = rs.getInt(1);
           }
           
           // consulta los datos
           autobuses = new Object[cant][7];
           sentenciaSQL  = "SELECT * FROM AUTOBUS";           
           ps = conn.prepareStatement(sentenciaSQL);
           rs = ps.executeQuery();
              
           // Recorre el result set para obtener los datos y asignarlos
           // al arreglo
           while (rs.next()){
               autobuses[i][0] = rs.getInt(1);  //NUMERO_ECONOMICO
               autobuses[i][1] = rs.getInt(2);  //VALOR_DEL_AUTOBUS
               autobuses[i][2] = rs.getInt(3);  //ANIOS_DE_OPERACION
               autobuses[i][3] = rs.getInt(4);  //CARROCERIA_ID
               autobuses[i][4] = rs.getInt(5);  //BASE_ID
               autobuses[i][5] = rs.getInt(6);  //LINEA_ID
               autobuses[i][6] = rs.getInt(7);  //OPERADOR_ID
               i++;
           }     
           return autobuses;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectAutobuses");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object[] selectAutobus(int id){
        // Consulta una pelicula buscando por su id
        // Se conecta a la base de datos
        conectar();
        Object autobus[];// Para guardar la cantidad de registros
        try{
           
            // Consulta los registros 
            autobus = new Object[7];          
            sentenciaSQL  = "SELECT * FROM AUTOBUS" +
                            " WHERE NUMERO_ECONOMICO = ?";
            // prepara la sentencia 
            ps = conn.prepareStatement(sentenciaSQL);
            //Se ingresa el parametro del ID
            ps.setInt(1, id);
            // Ejecuta la sentencia y la asigna al result set 
            rs = ps.executeQuery();
            rs.next();
            // Recupera los valores
            autobus[0] = rs.getInt(1);  //NUMERO_ECONOMICO
            autobus[1] = rs.getInt(2);  //VALOR_DEL_AUTOBUS
            autobus[2] = rs.getInt(3);  //ANIOS_DE_OPERACION
            autobus[3] = rs.getInt(4);  //CARROCERIA_ID
            autobus[4] = rs.getInt(5);  //BASE_ID
            autobus[5] = rs.getInt(6);  //LINEA_ID
            autobus[6] = rs.getInt(7);  //OPERADOR_ID
            return autobus;
       }
       catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectAutobus");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public int insertAutobus(Object[] autobus){
        // Guarda una pelicula
        // Se conecta a la base de datos
        conectar();
        try{
            int id = 0;
            
            sentenciaSQL = "SELECT MAX(NUMERO_ECONOMICO) FROM AUTOBUS";
            ps = conn.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            // guarda el nuevo id
            if (rs.next()){
                id = rs.getInt(1);
            }
            id++;
            
            // inserta mandando todos los datos, incluido en nuevo id
            sentenciaSQL = "INSERT INTO AUTOBUS VALUES(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sentenciaSQL);
            // Asigna los valores del arreglo  
            ps.setInt(1, id);                                       //NUMERO_ECONOMICO
            ps.setInt(2, Integer.parseInt(autobus[1].toString()));  //VALOR_DEL_AUTOBUS
            ps.setInt(3, Integer.parseInt(autobus[2].toString()));  //ANIOS_DE_OPERACION
            ps.setInt(4, Integer.parseInt(autobus[3].toString()));  //CARROCERIA_ID
            ps.setInt(5, Integer.parseInt(autobus[4].toString()));  //BASE_ID
            ps.setInt(6, Integer.parseInt(autobus[5].toString()));  //LINEA_ID
            ps.setInt(7, Integer.parseInt(autobus[6].toString()));  //OPERADOR_ID
            ps.executeUpdate();
            return id;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "insertAutobus");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int updateAutobus(Object autobus[]){
        // se conecta a la base de datos
       conectar();
       try{
           // actualiza los datos
           sentenciaSQL = "UPDATE AUTOBUS SET" +
                          " VALOR_DEL_AUTOBUS = ?" +
                          ", ANIOS_DE_OPERACION = ?" +
                          ", CARROCERIA_ID = ?" +
                          ", BASE_ID = ?" +
                          ", LINEA_ID = ?" +
                          ", OPERADOR_ID = ?" +
                          " WHERE NUMERO_ECONOMICO = ?";
            ps = conn.prepareStatement(sentenciaSQL);
            
            ps.setInt(1, Integer.parseInt(autobus[1].toString()));  //VALOR_DEL_AUTOBUS
            ps.setInt(2, Integer.parseInt(autobus[2].toString()));  //ANIOS_DE_OPERACION
            ps.setInt(3, Integer.parseInt(autobus[3].toString()));  //CARROCERIA_ID
            ps.setInt(4, Integer.parseInt(autobus[4].toString()));  //BASE_ID
            ps.setInt(5, Integer.parseInt(autobus[5].toString()));  //LINEA_ID
            ps.setInt(6, Integer.parseInt(autobus[6].toString()));  //OPERADOR_ID
            ps.setInt(7, Integer.parseInt(autobus[0].toString()));  //NUMERO_ECONOMICO
            ps.executeUpdate();
            return Integer.parseInt(autobus[0].toString());
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "updateAutobus");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int deleteAutobus(int id){
        // Si borro el casting, borra una pelicula 
        // Conecta a la base de datos
        conectar();
        try{
            sentenciaSQL = "DELETE FROM AUTOBUS WHERE NUMERO_ECONOMICO = ?";
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
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "deleteAutobus");
            if (ex.getErrorCode() ==  2292)
                return 1;
            return 2;
        }
        finally{
           desconectar();
        }
    }
}
