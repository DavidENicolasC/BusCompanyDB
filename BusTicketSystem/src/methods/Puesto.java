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
public class Puesto extends ConectarOracleDB{
    public Object [][] selectPuestos(){
        // Obtiene todos lo idiomas
        // Conecta a la base de datos
       conectar();
       // Variable para guardar los idiomas
       Object[][] puestos;
       // Contador
       int i = 0;
       int cant = 0;
       try{
           // consulta
           sentenciaSQL = "SELECT COUNT(*) FROM PUESTO";
           // prepara la consulta
           ps = conn.prepareStatement(sentenciaSQL);
           // ejecuta la consulta
           rs = ps.executeQuery();
           // Ontiene la cantidad de registros
           if (rs.next()){
               cant = rs.getInt(1);
           }
           
           // consulta los datos
           puestos = new Object[cant][3];
           sentenciaSQL  = "SELECT * FROM PUESTO";           
           ps = conn.prepareStatement(sentenciaSQL);
           rs = ps.executeQuery();
              
           // Recorre el result set para obtener los datos y asignarlos
           // al arreglo
           while (rs.next()){
               puestos[i][0] = rs.getInt(1);    //ID
               puestos[i][1] = rs.getString(2); //NOMBRE
               puestos[i][2] = rs.getInt(3);    //SALARIO
               i++;
           }     
           return puestos;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "getPuestos");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object[] selectPuesto(int id){
        // Consulta una pelicula buscando por su id
        // Se conecta a la base de datos
        conectar();
        Object puesto[];// Para guardar la cantidad de registros
        try{
           
            // Consulta los registros 
            puesto = new Object[9];          
            sentenciaSQL  = "SELECT * FROM PUESTO" +
                            " WHERE ID = ?";
            // prepara la sentencia 
            ps = conn.prepareStatement(sentenciaSQL);
            //Se ingresa el parametro del ID
            ps.setInt(1, id);
            // Ejecuta la sentencia y la asigna al result set 
            rs = ps.executeQuery();
            rs.next();
            // Recupera los valores
            puesto[0] = rs.getInt(1);    //ID
            puesto[1] = rs.getString(2); //NOMBRE
            puesto[2] = rs.getInt(3);    //SALARIO
               return puesto;
       }
       catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectPuesto");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public int insertPuesto(Object[] puesto){
        // Guarda una pelicula
        // Se conecta a la base de datos
        conectar();
        try{
            int id = 0;
            
            sentenciaSQL = "SELECT COUNT(*) FROM PUESTO";
            ps = conn.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            // guarda el nuevo id
            if (rs.next()){
                id = rs.getInt(1);
            }
            id++;
            
            // inserta mandando todos los datos, incluido en nuevo id
            sentenciaSQL = "INSERT INTO PUESTO VALUES(?,?,?)";
            ps = conn.prepareStatement(sentenciaSQL);
            // Asigna los valores del arreglo  
            ps.setInt(1, id);                                       //ID
            ps.setString(2, puesto[1].toString());                  //NOMBRE
            ps.setInt(3, Integer.parseInt(puesto[2].toString()));   //SALARIO
            ps.executeUpdate();
            return id;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "insertPuesto");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int updatePuesto(Object puesto[]){
        // se conecta a la base de datos
       conectar();
       try{
           // actualiza los datos
           sentenciaSQL = "UPDATE PUESTO SET" +
                          " NOMBRE = ?" +
                          ", SALARIO = ?" +
                          " WHERE ID = ?";
            ps = conn.prepareStatement(sentenciaSQL);
            
            ps.setString(1, puesto[1].toString());                   //NOMBRE
            ps.setInt(2, Integer.parseInt(puesto[2].toString()));    //SALARIO
            ps.setInt(3, Integer.parseInt(puesto[0].toString()));    //ID
            ps.executeUpdate();
            return Integer.parseInt(puesto[0].toString());
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "updatePuesto");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int deletePuesto(int id){
        // Si borro el casting, borra una pelicula 
        // Conecta a la base de datos
        conectar();
        try{
            sentenciaSQL = "DELETE FROM PUESTO WHERE ID = ?";
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
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "deletePuesto");
            if (ex.getErrorCode() ==  2292)
                return 1;
            return 2;
        }
        finally{
           desconectar();
        }
    }
}
