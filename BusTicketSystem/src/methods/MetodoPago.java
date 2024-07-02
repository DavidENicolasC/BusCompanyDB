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
public class MetodoPago extends ConectarOracleDB {
    public Object [][] selectMetodos_Pago(){
        // Obtiene todos lo idiomas
        // Conecta a la base de datos
       conectar();
       // Variable para guardar los idiomas
       Object[][] metodos_pago;
       // Contador
       int i = 0;
       int cant = 0;
       try{
           // consulta
           sentenciaSQL = "SELECT COUNT(*) FROM METODO_PAGO";
           // prepara la consulta
           ps = conn.prepareStatement(sentenciaSQL);
           // ejecuta la consulta
           rs = ps.executeQuery();
           // Ontiene la cantidad de registros
           if (rs.next()){
               cant = rs.getInt(1);
           }
           
           // consulta los datos
           metodos_pago = new Object[cant][9];
           sentenciaSQL  = "SELECT * FROM METODO_PAGO";           
           ps = conn.prepareStatement(sentenciaSQL);
           rs = ps.executeQuery();
              
           // Recorre el result set para obtener los datos y asignarlos
           // al arreglo
           while (rs.next()){
               metodos_pago[i][0] = rs.getInt(1);     //ID
               metodos_pago[i][1] = rs.getString(2);  //NOMBRE
               i++;
           }     
           return metodos_pago;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectMetodos_Pago");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object[] selectMetodo_Pago(int id){
        // Consulta una pelicula buscando por su id
        // Se conecta a la base de datos
        conectar();
        Object metodo_pago[];// Para guardar la cantidad de registros
        try{
           
            // Consulta los registros 
            metodo_pago = new Object[9];          
            sentenciaSQL  = "SELECT * FROM METODO_PAGO" +
                            " WHERE ID = ?";
            // prepara la sentencia 
            ps = conn.prepareStatement(sentenciaSQL);
            //Se ingresa el parametro del ID
            ps.setInt(1, id);
            // Ejecuta la sentencia y la asigna al result set 
            rs = ps.executeQuery();
            rs.next();
            // Recupera los valores
            metodo_pago[0] = rs.getInt(1);     //ID
            metodo_pago[1] = rs.getString(2);  //NOMBRE
            return metodo_pago;
       }
       catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectMetodo_Pago");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public int insertMetodo_Pago(Object[] metodo_pago){
        // Guarda una pelicula
        // Se conecta a la base de datos
        conectar();
        try{
            int id = 0;
            
            sentenciaSQL = "SELECT COUNT(*) FROM METODO_PAGO";
            ps = conn.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            // guarda el nuevo id
            if (rs.next()){
                id = rs.getInt(1);
            }
            id++;
            
            // inserta mandando todos los datos, incluido en nuevo id
            sentenciaSQL = "INSERT INTO METODO_PAGO VALUES(?,?)";
            ps = conn.prepareStatement(sentenciaSQL);
            // Asigna los valores del arreglo  
            ps.setInt(1, id);                           //ID
            ps.setString(2, metodo_pago[1].toString());   //NOMBRE
            ps.executeUpdate();
            return id;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "insertMetodo_Pago");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int updateMetodo_Pago(Object metodo_pago[]){
        // se conecta a la base de datos
       conectar();
       try{
           // actualiza los datos
           sentenciaSQL = "UPDATE METODO_PAGO SET" +
                          " NOMBRE = ?" +
                          " WHERE ID = ?";
            ps = conn.prepareStatement(sentenciaSQL);
            
            ps.setString(1, metodo_pago[1].toString());                   //NOMBRE
            ps.setInt(2, Integer.parseInt(metodo_pago[0].toString()));    //ID
            ps.executeUpdate();
            return Integer.parseInt(metodo_pago[0].toString());
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "updateMetodo_Pago");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int deleteMetodo_Pago(int id){
        // Si borro el casting, borra una pelicula 
        // Conecta a la base de datos
        conectar();
        try{
            sentenciaSQL = "DELETE FROM METODO_PAGO WHERE ID = ?";
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
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "deleteMetodo_Pago");
            if (ex.getErrorCode() ==  2292)
                return 1;
            return 2;
        }
        finally{
           desconectar();
        }
    }
}
