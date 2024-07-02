/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import LoginDB.ConectarOracleDB;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author David
 */
public class Venta extends ConectarOracleDB{
    public Object [][] selectVentas(){
        // Obtiene todos lo idiomas
        // Conecta a la base de datos
       conectar();
       // Variable para guardar los idiomas
       Object[][] ventas;
       // Contador
       int i = 0;
       int cant = 0;
       try{
           // consulta
           sentenciaSQL = "SELECT COUNT(*) FROM VENTA";
           // prepara la consulta
           ps = conn.prepareStatement(sentenciaSQL);
           // ejecuta la consulta
           rs = ps.executeQuery();
           // Ontiene la cantidad de registros
           if (rs.next()){
               cant = rs.getInt(1);
           }
           
           // consulta los datos
           ventas = new Object[cant][9];
           sentenciaSQL  = "SELECT * FROM VENTA";           
           ps = conn.prepareStatement(sentenciaSQL);
           rs = ps.executeQuery();
              
           // Recorre el result set para obtener los datos y asignarlos
           // al arreglo
           while (rs.next()){
               ventas[i][0] = rs.getInt(1);     //ID
               ventas[i][1] = rs.getDate(2);    //FECHA_HORA
               ventas[i][2] = rs.getInt(3);     //METODO_´PAGO_ID
               ventas[i][3] = rs.getInt(4);     //PRECIO
               ventas[i][4] = rs.getInt(5);     //ASIENTO
               ventas[i][5] = rs.getString(6);  //DESCUENTO
               ventas[i][6] = rs.getInt(7);     //SALIDA_ID
               ventas[i][7] = rs.getInt(8);     //PASAJERO_ID
               ventas[i][8] = rs.getInt(9);     //VENDEDOR_ID
               i++;
           }     
           return ventas;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectVentas");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public Object[] selectVenta(int id){
        // Consulta una pelicula buscando por su id
        // Se conecta a la base de datos
        conectar();
        Object[] venta;// Para guardar la cantidad de registros
        try{
           
            // Consulta los registros 
            venta = new Object[9];          
            sentenciaSQL  = "SELECT * FROM VENTA" +
                            " WHERE ID = ?";
            // prepara la sentencia 
            ps = conn.prepareStatement(sentenciaSQL);
            //Se ingresa el parametro del ID
            ps.setInt(1, id);
            // Ejecuta la sentencia y la asigna al result set 
            rs = ps.executeQuery();
            rs.next();
            // Recupera los valores
            venta[0] = rs.getInt(1);     //ID
            venta[1] = rs.getDate(2);    //FECHA_HORA
            venta[2] = rs.getInt(3);     //METODO_´PAGO_ID
            venta[3] = rs.getInt(4);     //PRECIO
            venta[4] = rs.getInt(5);     //ASIENTO
            venta[5] = rs.getString(6);  //DESCUENTO
            venta[6] = rs.getInt(7);     //SALIDA_ID
            venta[7] = rs.getInt(8);     //PASAJERO_ID
            venta[8] = rs.getInt(9);     //VENDEDOR_ID
            return venta;
       }
       catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "selectVenta");
            return null;
        }
       finally{
           desconectar();           
       }
    }
    
    public int insertVenta(Object[] venta){
        // Guarda una pelicula
        // Se conecta a la base de datos
        conectar();
        try{
            int id = 0;
            
            sentenciaSQL = "SELECT COUNT(*) FROM DIRECCION";
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
            ps.setDate(2, Date.valueOf(venta[0].toString()));       //FECHA_HORA
            ps.setInt(3, Integer.parseInt(venta[1].toString()));    //METODO_PAGO_ID
            ps.setInt(4, Integer.parseInt(venta[2].toString()));    //PRECIO
            ps.setInt(5, Integer.parseInt(venta[3].toString()));    //ASIENTO
            ps.setString(6, venta[4].toString());                   //DESCUENTO
            ps.setInt(7, Integer.parseInt(venta[5].toString()));    //SALIDA_ID
            ps.setInt(8, Integer.parseInt(venta[6].toString()));    //PASAJERO_ID
            ps.setInt(9, Integer.parseInt(venta[7].toString()));    //VENDEDOR_ID
            ps.executeUpdate();
            return id;
        }
        catch (SQLException ex){
            System.out.println("Error " +  ex.getSQLState() + "\n\n" + ex.getMessage() + 
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "insertVenta");
            return -1;
        }
       finally{
           desconectar();
       }
    }
    
    public int updateVenta(Object[] venta){
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
            
            ps.setDate(1, Date.valueOf(venta[1].toString()));       //FECHA_HORA
            ps.setInt(2, Integer.parseInt(venta[2].toString()));    //METODO_PAGO_ID
            ps.setInt(3, Integer.parseInt(venta[3].toString()));    //PRECIO
            ps.setInt(4, Integer.parseInt(venta[4].toString()));    //ASIENTO
            ps.setString(5, venta[5].toString());                   //DESCUENTO
            ps.setInt(6, Integer.parseInt(venta[6].toString()));     //SALIDA_ID
            ps.setInt(7, Integer.parseInt(venta[7].toString()));    //PASAJERO_ID
            ps.setInt(8, Integer.parseInt(venta[8].toString()));    //VENDEDOR_ID
            ps.setInt(9, Integer.parseInt(venta[0].toString()));    //ID
            ps.executeUpdate();
            return Integer.parseInt(venta[0].toString());
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
    
    public int deleteVenta(int id){
        // Si borro el casting, borra una pelicula 
        // Conecta a la base de datos
        conectar();
        try{
            sentenciaSQL = "DELETE FROM VENTA WHERE ID = ?";
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
                    "\n\n" + sentenciaSQL + "\n\nUbicación: " + "deleteVenta");
            if (ex.getErrorCode() ==  2292)
                return 1;
            return 2;
        }
        finally{
           desconectar();
        }
    }
}
