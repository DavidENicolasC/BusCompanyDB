/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import LoginDB.ConectarOracleDB;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author David
 */
public class Salida extends ConectarOracleDB {
    public Object [][] selectSalidas(){
        // Obtiene todos lo idiomas
        // Conecta a la base de datos
       conectar();
       // Variable para guardar los idiomas
       Object[][] salidas;
       // Contador
       int i = 0;
       int cant = 0;
       try{
           // consulta
           sentenciaSQL = "SELECT COUNT(*) FROM SALIDA";
           // prepara la consulta
           ps = conn.prepareStatement(sentenciaSQL);
           // ejecuta la consulta
           rs = ps.executeQuery();
           // Ontiene la cantidad de registros
           if (rs.next()){
               cant = rs.getInt(1);
           }
           
           // consulta los datos
           salidas = new Object[cant][9];
           sentenciaSQL  = "SELECT * FROM SALIDA";           
           ps = conn.prepareStatement(sentenciaSQL);
           rs = ps.executeQuery();
              
           // Recorre el result set para obtener los datos y asignarlos
           // al arreglo
           while (rs.next()){
               salidas[i][0] = rs.getInt(1);    //ID
               salidas[i][1] = rs.getInt(2);    //ANDEN_ORIGEN
               salidas[i][2] = rs.getInt(3);    //ANDEN_DESTINO
               SimpleDateFormat horm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               salidas[i][3] = horm.format(rs.getDate(4));   //FECHA_HORA
               salidas[i][4] = rs.getInt(5);    //COSTO
               SimpleDateFormat min = new SimpleDateFormat("hh:mm:ss");
               salidas[i][5] = min.format(rs.getDate(6));   //DURACION
               salidas[i][6] = rs.getInt(7);    //TERMINAL_ORIGEN_ID
               salidas[i][7] = rs.getInt(8);    //TERMINAL_DESTINO_ID
               salidas[i][8] = rs.getInt(9);    //AUTOBUS_NUMERO_ECONOMICO
               i++;
           }     
           return salidas;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectSalidas");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object[] selectSalida(int id){
        // Consulta una pelicula buscando por su id
        // Se conecta a la base de datos
        conectar();
        Object salida[];// Para guardar la cantidad de registros
        try{
           
            // Consulta los registros 
            salida = new Object[9];          
            sentenciaSQL  = "SELECT * FROM SALIDA" +
                            " WHERE ID = ?";
            // prepara la sentencia 
            ps = conn.prepareStatement(sentenciaSQL);
            //Se ingresa el parametro del ID
            ps.setInt(1, id);
            // Ejecuta la sentencia y la asigna al result set 
            rs = ps.executeQuery();
            rs.next();
            // Recupera los valores
            salida[0] = rs.getInt(1);   //ID
            salida[1] = rs.getInt(2);   //ANDEN_ORIGEN
            salida[2] = rs.getInt(3);   //ANDEN_DESTINO
            SimpleDateFormat horm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            salida[3] = horm.format(rs.getDate(4));   //FECHA_HORA
            salida[4] = rs.getInt(5);    //COSTO
            SimpleDateFormat min = new SimpleDateFormat("hh:mm:ss");
            salida[5] = min.format(rs.getDate(6));   //DURACION
            salida[6] = rs.getInt(7);   //TERMINAL_ORIGEN_ID
            salida[7] = rs.getInt(8);   //TERMINAL_DESTINO_ID
            salida[8] = rs.getInt(9);   //AUTOBUS_NUMERO_ECONOMICO
            return salida;
       }
       catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectSalida");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public int insertSalida(Object[] salida) throws ParseException{
        // Guarda una pelicula
        // Se conecta a la base de datos
        conectar();
        try{
            int id = 0;
            
            sentenciaSQL = "SELECT COUNT(*) FROM SALIDA";
            ps = conn.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            // guarda el nuevo id
            if (rs.next()){
                id = rs.getInt(1);
            }
            id++;
            
            // inserta mandando todos los datos, incluido en nuevo id
            sentenciaSQL = "INSERT INTO SALIDA VALUES(?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sentenciaSQL);
            // Asigna los valores del arreglo  
            ps.setInt(1, id);                                       //ID
            ps.setInt(2, Integer.parseInt(salida[1].toString()));   //ANDEN_ORIGEN
            ps.setInt(3, Integer.parseInt(salida[2].toString()));   //ANDEN_DESTINO
            SimpleDateFormat horm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.sql.Date d = new java.sql.Date(horm.parse(salida[3].toString()).getTime());
            ps.setDate(4, d); //FECHA_HORA
            ps.setInt(5, Integer.parseInt(salida[4].toString()));   //COSTO
            SimpleDateFormat min = new SimpleDateFormat("hh:mm:ss");
            java.sql.Date t = new java.sql.Date(min.parse(salida[5].toString()).getTime());
            ps.setDate(6, t);      //DURACION
            ps.setInt(7, Integer.parseInt(salida[6].toString()));   //TERMINAL_ORIGEN_ID
            ps.setInt(8, Integer.parseInt(salida[7].toString()));   //TERMINAL_DESTINO_ID
            ps.setInt(9, Integer.parseInt(salida[8].toString()));   //AUTOBUS_NUMERO_ECONOMICO
            ps.executeUpdate();
            return id;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "insertSalida");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int updateSalida(Object salida[]) throws ParseException{
        // se conecta a la base de datos
       conectar();
       try{
           // actualiza los datos
           sentenciaSQL = "UPDATE SALIDA SET" +
                          " ANDEN_ORIGEN = ?" +
                          ", ANDEN_DESTINO = ?" +
                          ", FECHA_HORA = ?" +
                          ", COSTO = ?" +
                          ", DURACION = ?" +
                          ", TERMINAL_ORIGEN_ID = ?" +
                          ", TERMINAL_DESTINO_ID = ?" +
                          ", AUTOBUS_NUMERO_ECONOMICO = ?" +
                          " WHERE ID = ?";
            ps = conn.prepareStatement(sentenciaSQL);
            // Asigna los valores del arreglo  
            ps.setInt(1, Integer.parseInt(salida[1].toString()));   //ANDEN_ORIGEN
            ps.setInt(2, Integer.parseInt(salida[2].toString()));   //ANDEN_DESTINO
            SimpleDateFormat horm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.sql.Date d = new java.sql.Date(horm.parse(salida[3].toString()).getTime());
            ps.setDate(3, d); //FECHA_HORA
            ps.setInt(4, Integer.parseInt(salida[4].toString()));   //COSTO
            SimpleDateFormat min = new SimpleDateFormat("hh:mm:ss");
            java.sql.Date t = new java.sql.Date(min.parse(salida[5].toString()).getTime());
            ps.setDate(5, t);      //DURACION
            ps.setInt(6, Integer.parseInt(salida[6].toString()));   //TERMINAL_ORIGEN_ID
            ps.setInt(7, Integer.parseInt(salida[7].toString()));   //TERMINAL_DESTINO_ID
            ps.setInt(8, Integer.parseInt(salida[8].toString()));   //AUTOBUS_NUMERO_ECONOMICO
            ps.setInt(9, Integer.parseInt(salida[0].toString()));                                       //ID
            ps.executeUpdate();
            return Integer.parseInt(salida[0].toString());
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "updateSalida");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int deleteSalida(int id){
        // Si borro el casting, borra una pelicula 
        // Conecta a la base de datos
        conectar();
        try{
            sentenciaSQL = "DELETE FROM SALIDA WHERE ID = ?";
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
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "deleteSalida");
            if (ex.getErrorCode() ==  2292)
                return 1;
            return 2;
        }
        finally{
           desconectar();
        }
    }
}
