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
public class UsuariosApp extends ConectarOracleDB {
    public Object [][] selectUsuarios_App(){
        // Obtiene todos lo idiomas
        // Conecta a la base de datos
       conectar();
       // Variable para guardar los idiomas
       Object[][] usuarios_app;
       // Contador
       int i = 0;
       int cant = 0;
       try{
           // consulta
           sentenciaSQL = "SELECT COUNT(*) FROM USUARIOSAPP";
           // prepara la consulta
           ps = conn.prepareStatement(sentenciaSQL);
           // ejecuta la consulta
           rs = ps.executeQuery();
           // Ontiene la cantidad de registros
           if (rs.next()){
               cant = rs.getInt(1);
           }
           
           // consulta los datos
           usuarios_app = new Object[cant][3];
           sentenciaSQL  = "SELECT * FROM USUARIOSAPP";           
           ps = conn.prepareStatement(sentenciaSQL);
           rs = ps.executeQuery();
              
           // Recorre el result set para obtener los datos y asignarlos
           // al arreglo
           while (rs.next()){
               usuarios_app[i][0] = rs.getInt(1);       //ID
               usuarios_app[i][1] = rs.getInt(2);       //EMPLEADO_ID
               usuarios_app[i][2] = rs.getString(3);    //PASS
               i++;
           }     
           return usuarios_app;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectUsuarios_App");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object[] selectUsuario_App(int id){
        // Consulta un usuario de la app buscando por su id
        // Se conecta a la base de datos
        conectar();
        Object usuario_app[];// Para guardar la cantidad de registros
        try{
           
            // Consulta los registros 
            usuario_app = new Object[3];          
            sentenciaSQL  = "SELECT * FROM USUARIOSAPP" +
                            " WHERE ID = ?";
            // prepara la sentencia 
            ps = conn.prepareStatement(sentenciaSQL);
            //Se ingresa el parametro del ID
            ps.setInt(1, id);
            // Ejecuta la sentencia y la asigna al result set 
            rs = ps.executeQuery();
            rs.next();
            // Recupera los valores
            usuario_app[0] = rs.getInt(1);     //ID
            usuario_app[1] = rs.getInt(2);  //ALCALDIA/MUNICIPIO
            usuario_app[2] = rs.getString(3);  //COLONIA
            return usuario_app;
       }
       catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectUsuarioApp");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object[] selectUsuario_AppPorIDyPass(int usr, char psw[]){
        // Consulta un usuario de la app buscando por su id
        // Se conecta a la base de datos
        conectar();
        Object usuario_app[];// Para guardar la cantidad de registros
        try{
           
            // Consulta los registros 
            usuario_app = new Object[3];          
            sentenciaSQL  = "SELECT * FROM USUARIOSAPP" +
                            " WHERE EMPLEADO_ID LIKE ?" + 
                            " AND PASS LIKE ?";
            // prepara la sentencia 
            ps = conn.prepareStatement(sentenciaSQL);
            //Se ingresa el parametro del ID
            ps.setInt(1, usr);
            ps.setString(2, String.valueOf(psw));
            // Ejecuta la sentencia y la asigna al result set 
            rs = ps.executeQuery();
            if(rs.next()) {
                    // Recupera los valores
                usuario_app[0] = rs.getInt(1);      //ID
                usuario_app[1] = rs.getInt(2);      //EMPLEADO_ID
                usuario_app[2] = rs.getString(3);   //PASS
                return usuario_app;
            } else {
                return null;
            }
       }
       catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectUsuarioAppPorIDyPass");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public int insertUsuario_App(Object[] usuario_app){
        // Guarda una pelicula
        // Se conecta a la base de datos
        conectar();
        try{
            int id = 0;
            
            sentenciaSQL = "SELECT COUNT(*) FROM USUARIOSAPP";
            ps = conn.prepareStatement(sentenciaSQL);
            rs = ps.executeQuery();
            // guarda el nuevo id
            if (rs.next()){
                id = rs.getInt(1);
            }
            id++;
            
            // inserta mandando todos los datos, incluido en nuevo id
            sentenciaSQL = "INSERT INTO USUARIOSAPP VALUES(?,?,?)";
            ps = conn.prepareStatement(sentenciaSQL);
            // Asigna los valores del arreglo  
            ps.setInt(1, id);                           //ID
            ps.setString(2, usuario_app[0].toString()); //EMPLEADO_ID
            ps.setString(3, usuario_app[1].toString()); //PASS
            ps.executeUpdate();
            return id;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "insertUsuarios_App");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int updateUsuario_App(Object usuario_app[]){
        // se conecta a la base de datos
       conectar();
       try{
           // actualiza los datos
           sentenciaSQL = "UPDATE USUARIOSAPP SET" +
                          " EMPLEADO_ID = ?" +
                          ", PASS = ?" +
                          " WHERE ID = ?";
            ps = conn.prepareStatement(sentenciaSQL);
            
            ps.setString(1, usuario_app[1].toString());                 //EMPLEADO_ID
            ps.setString(2, usuario_app[2].toString());                 //PASS
            ps.setInt(9, Integer.parseInt(usuario_app[0].toString()));    //ID
            ps.executeUpdate();
            return Integer.parseInt(usuario_app[0].toString());
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "updateUsuario_App");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int deleteUsuario_App(int id){
        // Si borro el casting, borra una pelicula 
        // Conecta a la base de datos
        conectar();
        try{
            sentenciaSQL = "DELETE FROM USUARIOSAPP WHERE ID = ?";
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
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "deleteUsuario_App");
            if (ex.getErrorCode() ==  2292)
                return 1;
            return 2;
        }
        finally{
           desconectar();
        }
    }
}
