package Persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccesoBD {
    private Conexion conexion;
    
    public AccesoBD(){
        conexion = new Conexion();
    }
     
    public ArrayList<String>[] query(String sql) {
        //Connection conexion = this.conexion.conectarBD();
        ArrayList<String>[] retorno = null;

        try {
            PreparedStatement preparedStatement = conexion.conectarBD().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int cantidadColumnas = metaData.getColumnCount();
/*
            System.out.println(""+ cantidadColumnas);
            
            for (int i = 0; i < cantidadColumnas; i++) {
                System.out.println(""+ metaData.getColumnName(i+1));
            }*/
            
            retorno = new ArrayList[cantidadColumnas];
            
            for (int i = 0; i < cantidadColumnas; i++) {
                retorno[i] = new ArrayList<>();
            }
            
            while (resultSet.next()) {
                for (int i = 0; i < cantidadColumnas; i++) {
                   retorno[i].add(resultSet.getString(metaData.getColumnName(i+1)));
                }
            }
            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("ERROR: CATCH LINEA 46 - " + e.getMessage());
        } finally {
            conexion.desconectarBD();
        }

        return retorno;
    }

    public boolean noQuery(String sql) {
        boolean retorno = false;

        try {
            PreparedStatement preparedStatement = conexion.conectarBD().prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.close();
            retorno = true;

        } catch (SQLException ex) {
            System.out.println("ERROR LINEA 64 - " + ex.getMessage());
        } finally {
            conexion.desconectarBD();
        }

        return retorno;
    }
}
