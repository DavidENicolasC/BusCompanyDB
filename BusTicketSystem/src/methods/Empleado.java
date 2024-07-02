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
    public Object [][] selectEmpleados(){
        // Obtiene todos lo idiomas
        // Conecta a la base de datos
       conectar();
       // Variable para guardar los idiomas
       Object[][] empleados;
       // Contador
       int i = 0;
       int cant = 0;
       try{
           // consulta
           sentenciaSQL = "SELECT COUNT(*) FROM EMPLEADO";
           // prepara la consulta
           ps = conn.prepareStatement(sentenciaSQL);
           // ejecuta la consulta
           rs = ps.executeQuery();
           // Ontiene la cantidad de registros
           if (rs.next()){
               cant = rs.getInt(1);
           }
           
           // consulta los datos
           empleados = new Object[cant][7];
           sentenciaSQL  = "SELECT * FROM EMPLEADO";           
           ps = conn.prepareStatement(sentenciaSQL);
           rs = ps.executeQuery();
              
           // Recorre el result set para obtener los datos y asignarlos
           // al arreglo
           while (rs.next()){
               empleados[i][0] = rs.getInt(1);      //ID
               empleados[i][1] = rs.getString(2);   //NOMBRES
               empleados[i][2] = rs.getString(3);   //APELLIDO_PATERNO
               empleados[i][3] = rs.getString(4);   //APELLIDO_MATERNO
               empleados[i][4] = rs.getInt(5);      //ANIOS_DE_SERVICIO
               empleados[i][5] = rs.getInt(6);      //DIRECCION_ID
               empleados[i][6] = rs.getInt(7);      //PUESTO_ID
               i++;
           }     
           return empleados;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectEmpleados");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object [][] selectGerentes(){
        // Obtiene todos los estados
        // Conecta a la base de datos
       conectar();
       // Variable para guardar los estados
       Object[][] gerentes;
       // Contador
       int i = 0;
       int cant = 0;
       try{
           // consulta
           sentenciaSQL = "SELECT COUNT(*) FROM EMPLEADO" +
                   " WHERE PUESTO_ID  LIKE 2";
           // prepara la consulta
           ps = conn.prepareStatement(sentenciaSQL);
           // ejecuta la consulta
           rs = ps.executeQuery();
           // Ontiene la cantidad de registros
           if (rs.next()){
               cant = rs.getInt(1);
           }
           
           // consulta los datos
           gerentes = new Object[cant][7];
           sentenciaSQL  = "SELECT ID, NOMBRES" + 
                   ", APELLIDO_PATERNO, APELLIDO_MATERNO" +
                   ", ANIOS_DE_SERVICIO, DIRECCION_ID, PUESTO_ID" +
                   " FROM EMPLEADO" +
                   " WHERE PUESTO_ID LIKE 2";           
           ps = conn.prepareStatement(sentenciaSQL);
           rs = ps.executeQuery();
              
           // Recorre el result set para obtener los datos y asignarlos
           // al arreglo
           while (rs.next()){
               gerentes[i][0] = rs.getInt(1);       //ID
               gerentes[i][1] = rs.getString(2);    //NOMBRES
               gerentes[i][2] = rs.getString(3);    //APELLIDO_PATERNO
               gerentes[i][3] = rs.getString(4);    //APELLIDO_MATERNO
               gerentes[i][4] = rs.getInt(5);       //ANIOS_DE_SERVICIO
               gerentes[i][5] = rs.getInt(6);       //DIRECCION_ID
               gerentes[i][6] = rs.getInt(7);       //PUESTO_ID
               i++;
           }     
           return gerentes;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectGerentes");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object[] selectEmpleado(int id){
        // Consulta una pelicula buscando por su id
        // Se conecta a la base de datos
        conectar();
        Object empleado[];// Para guardar la cantidad de registros
        try{
           
            // Consulta los registros 
            empleado = new Object[9];          
            sentenciaSQL  = "SELECT * FROM EMPLEADO" +
                            " WHERE ID = ?";
            // prepara la sentencia 
            ps = conn.prepareStatement(sentenciaSQL);
            //Se ingresa el parametro del ID
            ps.setInt(1, id);
            // Ejecuta la sentencia y la asigna al result set 
            rs = ps.executeQuery();
            rs.next();
            // Recupera los valores
            empleado[0] = rs.getInt(1);      //ID
            empleado[1] = rs.getString(2);   //NOMBRES
            empleado[2] = rs.getString(3);   //APELLIDO_PATERNO
            empleado[3] = rs.getString(4);   //APELLIDO_MATERNO
            empleado[4] = rs.getInt(5);      //ANIOS_DE_SERVICIO
            empleado[5] = rs.getInt(6);      //DIRECCION_ID
            empleado[6] = rs.getInt(7);      //PUESTO_ID
            return empleado;
       }
       catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectEmpleado");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public int insertEmpleado(Object[] empleado){
        // Guarda una pelicula
        // Se conecta a la base de datos
        conectar();
        try{
            int id = 0;
            
            sentenciaSQL = "SELECT COUNT(*) FROM EMPLEADO";
            ps = conn.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            // guarda el nuevo id
            if (rs.next()){
                id = rs.getInt(1);
            }
            id++;
            
            // inserta mandando todos los datos, incluido en nuevo id
            sentenciaSQL = "INSERT INTO EMPLEADO VALUES(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sentenciaSQL);
            // Asigna los valores del arreglo  
            ps.setInt(1, id); //ID
            ps.setString(2, empleado[1].toString());                //NOMBRES
            ps.setString(3, empleado[2].toString());                //APELLIDO_PATERNO
            ps.setString(4, empleado[3].toString());                //APELLIDO_MATERNO
            ps.setInt(5, Integer.parseInt(empleado[4].toString())); //ANIOS_DE_SERVICIO
            ps.setInt(6, Integer.parseInt(empleado[5].toString())); //DIRECCION_ID
            ps.setInt(7, Integer.parseInt(empleado[6].toString())); //PUESTO_ID
            ps.executeUpdate();
            return id;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "insertEmpleado");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int updateEmpleado(Object empleado[]){
        // se conecta a la base de datos
       conectar();
       try{
           // actualiza los datos
           sentenciaSQL = "UPDATE EMPLEADO SET" +
                          " NOMBRES = ?" +
                          ", APELLIDO_PATERNO = ?" +
                          ", APELLIDO_MATERNO = ?" +
                          ", ANIOS_DE_SERVICIO = ?" +
                          ", DIRECCION_ID = ?" +
                          ", PUESTO_ID = ?" +
                          " WHERE ID = ?";
            ps = conn.prepareStatement(sentenciaSQL);
            
            ps.setString(1, empleado[1].toString());                //NOMBRES
            ps.setString(2, empleado[2].toString());                //APELLIDO_PATERNO
            ps.setString(3, empleado[3].toString());                //APELLIDO_MATERNO
            ps.setInt(4, Integer.parseInt(empleado[4].toString())); //ANIOS_DE_SERVICIO
            ps.setInt(5, Integer.parseInt(empleado[5].toString())); //DIRECCION_ID
            ps.setInt(6, Integer.parseInt(empleado[6].toString())); //PUESTO_ID
            ps.setInt(7, Integer.parseInt(empleado[0].toString())); //ID
            ps.executeUpdate();
            return Integer.parseInt(empleado[0].toString());
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "updateEmpleado");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int deleteEmpleado(int id){
        // Si borro el casting, borra una pelicula 
        // Conecta a la base de datos
        conectar();
        try{
            sentenciaSQL = "DELETE FROM EMPLEADO WHERE ID = ?";
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
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "deleteEmpleado");
            if (ex.getErrorCode() ==  2292)
                return 1;
            return 2;
        }
        finally{
           desconectar();
        }
    }
}
