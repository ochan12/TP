
package Persistencia;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccesoBD {
    private static String conexion;
    private static String driver;
    private static String usuario;
    private static String contraseña;
    
    public AccesoBD(){
        conexion = "jdbc:derby://localhost:1527/BD_Vocabulario";
        driver = "com.mysql.jdbc.Driver";
        usuario = "Marcelo";
        contraseña = " ";
        
    }
    
    
    private static Connection crearConexion()
    {      
        Connection con = null;
        try           
        {            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(conexion, "APP", contraseña);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return con;
    }    
    
    
    public ResultSet query(String consulta) throws SQLException{
        ResultSet resultado = null;
        Connection con = null;
        Statement stm = null;
        try
        {
            con = crearConexion();
            con.setAutoCommit(false);
            stm = con.createStatement();
            resultado = stm.executeQuery(consulta);
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());   
        }
        finally
        {
//            resultado.close();
//            stm.close();
            cerrar(con);
        }
        return resultado;
    }
    
    
    public void noQuery(String consulta){
        Connection con = null;
        Statement stm = null;
        try
        {
            con = crearConexion();
            stm  = con.createStatement();
            boolean update = stm.execute(consulta);
            System.out.println(update);
            stm.close();       
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally{
            
            cerrar(con);
        }
        
    }
    
    public void cerrar(Connection con){
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void transaccion(String transaccion){
        
        
    }
    
}
