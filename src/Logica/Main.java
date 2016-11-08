package Logica;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        Persistencia.AccesoBD acceso = new Persistencia.AccesoBD();
        acceso.noQuery("DELETE FROM PALABRAxLIBRO");
        acceso.noQuery("DELETE FROM PALABRAS");
        acceso.noQuery("DELETE FROM LIBROS");
        
        Interfaz.Principal P = new Interfaz.Principal();
        P.setVisible(true);        
    }
}

    
    /*
        
        CREACION DE LA BD
        Connection conn = null;
        
        try {
            //obtenemos el driver de para mysql
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //obtenemos la conexión
            conn = DriverManager.getConnection("jdbc:derby:.\\DB\\Vocabulario.DB;create=true");
            if (conn != null) {
                String creartabla1 = "CREATE TABLE PALABRAS (contenido_palabra varchar(50) PRIMARY KEY, contador_palabra integer NOT NULL)";
                String creartabla2 = "CREATE TABLE LIBROS (id_libro integer PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), nombre_libro varchar(50) NOT NULL, autor_libro varchar(50) NOT NULL, lenguaje_libro varchar(50))";
                String creartabla3 = "CREATE TABLE PALABRAxLIBRO (id_libro integer NOT NULL, contenido_palabra varchar (50) NOT NULL, CONSTRAINT pk_palabraXlibro PRIMARY KEY (id_libro, contenido_palabra), CONSTRAINT fk_palabra FOREIGN KEY (contenido_palabra) REFERENCES PALABRAS (contenido_palabra), CONSTRAINT fk_libro FOREIGN KEY (id_libro) REFERENCES LIBROS (id_libro))";

                try {
                    PreparedStatement pstm = conn.prepareStatement(creartabla1);
                    pstm.execute();
                    pstm.close();

                    PreparedStatement pstm2 = conn.prepareStatement(creartabla2);
                    pstm2.execute();
                    pstm2.close();

                    PreparedStatement pstm3 = conn.prepareStatement(creartabla3);
                    pstm3.execute();
                    pstm3.close();

                } catch (SQLException ex) {
                    System.out.println("" + ex.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("TUVIEJA");
        } catch (ClassNotFoundException ese) {
            System.out.println("" + ese.getMessage());
        }
    
    */