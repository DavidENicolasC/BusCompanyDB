/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


/**
 *
 * @author David
 */
public class ConectarOracleDB {

    public Connection conn = null;
    public ArrayList<Statement> statements;
    public Statement s;
    public String sentenciaSQL;
    public ResultSet rs;
    public PreparedStatement ps;
    public CallableStatement cstmt;
    
    //AVISOS DB
    public static final String DB_CONTACT = "Contacte al proveedor.";
    public static String DB_NOCONECT = "No se pudo conectar a la base de datos. " + DB_CONTACT;
    public static String DB_NO_DB = "No se encuentra la base de datos. " + DB_CONTACT;
    public static String DB_DRIVERNO_REC = "No se reconoce el controlador. " + DB_CONTACT;
    public static String DB_NOINST_DRIVER = "No se puede crear una instancia del controlador JDBC. " + DB_CONTACT;
    public static String DB_NOACCESS_DRIVER = "No se permite el acceso a controlador JDBC. " + DB_CONTACT;
    public static String DB_T_ERROR = "Error de la base de datos.";
    public static String DB_CLOSECON_ERR = "No se puede cerrar la base de datos. " + DB_CONTACT;
    public static String DB_CLOSESTA_ERR = "No se puede cerrar el statement. " + DB_CONTACT;
    
    // Errores de transacción
    public static String DB_ERR_QUERY = "Error al consultar la base de datos. " + DB_CONTACT;
    public static String DB_ERR_INSERT = "Error al insertar en la base de datos. " + DB_CONTACT;
    public static String DB_ERR_UPDATE = "Error al modificar la base de datos. " + DB_CONTACT;
    public static String DB_ERR_DELATE = "Error al borrar en la base de datos. " + DB_CONTACT;
    
    // Configuración de la conexión
    public static String JDBC_DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
    public static String JDBC_PROTOCOL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    public static String JDBC_USER = "USR_AUTOBUSES";
    public static String JDBC_PSW = "ADO";
    
    // Conecta a la base de datos del sistema y la crea
    public void conectar (){
        // Lee los controladores
        loadDriver();
        conn = null;
        // Lista de sentencias
        statements = new ArrayList();
        try{
            // Propiedades de la conexión
            Properties props = new Properties();
            props.put("user", JDBC_USER);
            props.put("password", JDBC_PSW);
            
            // Obtiene la conexión
            conn = DriverManager.getConnection(JDBC_PROTOCOL, props);
            conn.setAutoCommit(true);
            // Crea la sentencia
            s = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statements.add(s);
        }
        catch (SQLException sqle){
            System.out.println(DB_T_ERROR +  sqle.getSQLState() + 
                    DB_NOCONECT);
            s = null;
        }
    }
    
    // Lee los controladores de Oracle
    private void loadDriver(){
        try {
            Class.forName(JDBC_DRIVER_ORACLE).newInstance();
        } 
        catch (ClassNotFoundException cnfe) {
            System.out.println(DB_T_ERROR + DB_DRIVERNO_REC);
        } 
        catch (InstantiationException ie) {
            System.out.println(DB_T_ERROR + DB_NOINST_DRIVER);
        } 
        catch (IllegalAccessException iae) {
            System.out.println(DB_T_ERROR + DB_NOACCESS_DRIVER);
        }
    }
    
    // Desconecta la base de datos
    public void desconectar (){
        if (statements != null) 
            statements.clear();
        try{
            if (s != null)
                s.close();
            if (conn != null) 
                conn.close();
        }
        catch (SQLException sqle) {
            System.out.println(DB_T_ERROR + sqle.getSQLState() +
                    DB_CLOSECON_ERR + " " + sqle.getMessage());
        }
    }    
}
