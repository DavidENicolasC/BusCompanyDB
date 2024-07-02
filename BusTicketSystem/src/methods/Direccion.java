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
public class Direccion extends ConectarOracleDB {
    public Object [][] selectDirecciones(){
        // Obtiene todos lo idiomas
        // Conecta a la base de datos
       conectar();
       // Variable para guardar los idiomas
       Object[][] direcciones;
       // Contador
       int i = 0;
       int cant = 0;
       try{
           // consulta
           sentenciaSQL = "SELECT COUNT(*) FROM DIRECCION";
           // prepara la consulta
           ps = conn.prepareStatement(sentenciaSQL);
           // ejecuta la consulta
           rs = ps.executeQuery();
           // Ontiene la cantidad de registros
           if (rs.next()){
               cant = rs.getInt(1);
           }
           
           // consulta los datos
           direcciones = new Object[cant][9];
           sentenciaSQL  = "SELECT * FROM DIRECCION";           
           ps = conn.prepareStatement(sentenciaSQL);
           rs = ps.executeQuery();
              
           // Recorre el result set para obtener los datos y asignarlos
           // al arreglo
           while (rs.next()){
               direcciones[i][0] = rs.getInt(1);     //ID
               direcciones[i][1] = rs.getString(2);  //ALCALDIA/MUNICIPIO
               direcciones[i][2] = rs.getString(3);  //COLONIA
               direcciones[i][3] = rs.getInt(4);     //CODIGO POSTAL
               direcciones[i][4] = rs.getString(5);  //CALLE
               direcciones[i][5] = rs.getInt(6);     //NUMERO
               direcciones[i][6] = rs.getLong(7);    //TELEFONO
               direcciones[i][7] = rs.getString(8);  //CORREO ELECTRONICO
               direcciones[i][8] = rs.getInt(9);  //ESTADO_ID
               i++;
           }     
           return direcciones;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "getDirecciones");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object[] selectDireccion(int id){
        // Consulta una pelicula buscando por su id
        // Se conecta a la base de datos
        conectar();
        Object direccion[];// Para guardar la cantidad de registros
        try{
           
            // Consulta los registros 
            direccion = new Object[9];          
            sentenciaSQL  = "SELECT * FROM DIRECCION" +
                            " WHERE ID = ?";
            // prepara la sentencia 
            ps = conn.prepareStatement(sentenciaSQL);
            //Se ingresa el parametro del ID
            ps.setInt(1, id);
            // Ejecuta la sentencia y la asigna al result set 
            rs = ps.executeQuery();
            rs.next();
            // Recupera los valores
            direccion[0] = rs.getInt(1);     //ID
            direccion[1] = rs.getString(2);  //ALCALDIA/MUNICIPIO
            direccion[2] = rs.getString(3);  //COLONIA
            direccion[3] = rs.getInt(4);     //CODIGO POSTAL
            direccion[4] = rs.getString(5);  //CALLE
            direccion[5] = rs.getInt(6);     //NUMERO
            direccion[6] = rs.getLong(7);    //TELEFONO
            direccion[7] = rs.getString(8);  //CORREO ELECTRONICO
            direccion[8] = rs.getInt(9);  //ESTADO_ID
            return direccion;
       }
       catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectDireccion");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public int insertDireccion(Object[] direccion){
        // Guarda una pelicula
        // Se conecta a la base de datos
        conectar();
        try{
            int id = 0;
            
            sentenciaSQL = "SELECT MAX(ID) FROM DIRECCION";
            ps = conn.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            // guarda el nuevo id
            if (rs.next()){
                id = rs.getInt(1);
            }
            id++;
            
            // inserta mandando todos los datos, incluido en nuevo id
            sentenciaSQL = "INSERT INTO DIRECCION VALUES(?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sentenciaSQL);
            // Asigna los valores del arreglo  
            ps.setInt(1, id);    //ID
            ps.setString(2, direccion[1].toString());                   //ALCALDIA/MUNICIPIO
            ps.setString(3, direccion[2].toString());                   //COLONIA
            ps.setInt(4, Integer.parseInt(direccion[3].toString()));    //CODIGO POSTAL
            ps.setString(5, direccion[4].toString());                   //CALLE
            ps.setInt(6, Integer.parseInt(direccion[5].toString()));    //NUMERO
            ps.setLong(7, Long.parseLong(direccion[6].toString()));     //TELEFONO
            ps.setString(8, direccion[7].toString());                   //CORREO ELECTRONICO
            ps.setInt(9, Integer.parseInt(direccion[8].toString()));    //ESTADO_ID
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
    
    public int updateDireccion(Object direccion[]){
        // se conecta a la base de datos
       conectar();
       try{
           // actualiza los datos
           sentenciaSQL = "UPDATE DIRECCION SET" +
                          " ALCALDIA_MUNICIPIO = ?" +
                          ", COLONIA = ?" +
                          ", CODIGO_POSTAL = ?" +
                          ", CALLE = ?" +
                          ", NUMERO = ?" +
                          ", TELEFONO = ?" +
                          ", CORREO_ELECTRONICO = ?" +
                          ", ESTADO_ID = ?" +
                          " WHERE ID = ?";
            ps = conn.prepareStatement(sentenciaSQL);
            
            ps.setString(1, direccion[1].toString());                   //ALCALDIA/MUNICIPIO
            ps.setString(2, direccion[2].toString());                   //COLONIA
            ps.setInt(3, Integer.parseInt(direccion[3].toString()));    //CODIGO POSTAL
            ps.setString(4, direccion[4].toString());                   //CALLE
            ps.setInt(5, Integer.parseInt(direccion[5].toString()));    //NUMERO
            ps.setLong(6, Long.parseLong(direccion[6].toString()));     //TELEFONO
            ps.setString(7, direccion[7].toString());                   //CORREO ELECTRONICO
            ps.setInt(8, Integer.parseInt(direccion[8].toString()));    //ESTADO_ID
            ps.setInt(9, Integer.parseInt(direccion[0].toString()));    //ID
            ps.executeUpdate();
            return Integer.parseInt(direccion[0].toString());
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "updateDireccion");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int deleteDireccion(int id){
        // Si borro el casting, borra una pelicula 
        // Conecta a la base de datos
        conectar();
        try{
            sentenciaSQL = "DELETE FROM DIRECCION WHERE ID = ?";
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
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "deleteDireccion");
            if (ex.getErrorCode() ==  2292)
                return 1;
            return 2;
        }
        finally{
           desconectar();
        }
    }
}
