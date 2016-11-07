
package Persistencia;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private Connection conexion = null;

    public Connection conectarBD() {
        try {
            //obtenemos el driver de para mysql
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //obtenemos la conexión
            conexion = DriverManager.getConnection("jdbc:derby:.\\DB\\Vocabulario.DB");
            //conexion = DriverManager.getConnection("jdbc:derby:.\\BD_Vocabulario");
            if (conexion != null) {
                System.out.println("OK base de datos listo");
            }
        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {
        }
        return conexion;
    }

    public void desconectarBD() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

