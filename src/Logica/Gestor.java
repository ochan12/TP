package Logica;

import Persistencia.Conexion;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import static java.util.Objects.hash;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.LinkedList;

public class Gestor {

    private Interfaz.VentanaVerPalabras ventanaPalabras; //Declaramos la ventana para visualizar la tabla
    private Hashtable<Integer, Palabra> hashTable;
    private ArrayList<Libro> librosCargados;
    private Conexion accesoBD;

    //Constructor
    public Gestor() {
        ventanaPalabras = new Interfaz.VentanaVerPalabras();
        hashTable = new Hashtable<Integer, Palabra>();
        librosCargados = new ArrayList<Libro>();
        accesoBD = new Conexion();
    }
/*
    public void cargarPalabras() {
        try {
            //CARGAR PALABRAS
            String busqueda = "Select * FROM PALABRA";
            ResultSet palabras = accesoBD.query(busqueda);
            if (palabras == null) {
                return;
            }
            while (palabras.next()) {
                String palabraAgregar = palabras.getString("NOMBRE_PALABRA");
                int repeticionesPalabra = palabras.getShort("CANTIDAD_PALABRA");
                hashTable.put(palabraAgregar.hashCode(), new Palabra(palabraAgregar, repeticionesPalabra));
            }
            //Cargar libros y asociarlos a las palabras
            busqueda = "SELECT * FROM PALABRAXLIBRO PL JOIN LIBRO L ON PL.ID_LIBRO = L.ID_LIBRO";
            palabras = accesoBD.query(busqueda);
            while (palabras.next()) {
                String nombreLibro = palabras.getString("NOMBRE_LIBRO");
                String autorLibro = palabras.getString("AUTOR_LIBRO");
                String lenguajeLibro = palabras.getString("LENGUAJE_LIBRO");
                Libro libroNuevo = new Libro(autorLibro, nombreLibro, lenguajeLibro);

                hashTable.get(palabras.getString("NOMBRE_PALABRA").hashCode()).addLibro(libroNuevo);
                if (!existeLibro(libroNuevo)) {
                    librosCargados.add(libroNuevo);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
/*
    public void guardarPalabras() {
        try {
            
            String insert = "", select = "", update = "";
            Enumeration e = hashTable.keys();
            int clave;
            Palabra valor;
            while (e.hasMoreElements()) {
                clave = (Integer) e.nextElement();//Se busca la clave hash
                valor = hashTable.get(clave);//Se busca la Palabra en esa posicion hash
                select = "SELECT * FROM PALABRA WHERE NOMBRE_PALABRA like '" + valor.getContenido() + "'";
                ResultSet resultado = accesoBD.query(select);
                System.out.println("llegue");
                if (!resultado.next()) {
                    System.out.println("palabra no encontrada");
                    insert = "INSERT INTO PALABRA VALUES('" + valor.getContenido() + "', " + valor.getContador() + ")";
                    accesoBD.noQuery(insert);
                    for (Libro libro : valor.getLibros()) {
                        insert = "INSERT INTO LIBRO (AUTOR_LIBRO, TITULO_LIBRO, LENGUAJE_LIBRO) VALUES ('" + libro.getAutor() + "', '" + libro.getTitulo() + "', '" + libro.getIdioma() + "')";
                        accesoBD.noQuery(insert);
                        select = "SELECT * FROM LIBRO WHERE TITULO_LIBRO like '" + libro.getTitulo() + "' AND AUTOR_LIBRO like '" + libro.getAutor() + "'";
                        ResultSet esteLibro = accesoBD.query(select);
                        insert = "INSERT INTO PALABRAXLIBRO VALUES ('" + valor.getContenido() + "', " + esteLibro.getString("ID_LIBRO") + ")";
                        accesoBD.noQuery(insert);
                    }
                } else {
                    System.out.println("estaba la palabra");
                    update = "UPDATE PALABRA SET CANTIDAD_PALABRA = " + valor.getContador();
                    accesoBD.noQuery(update);
                    for (Libro libro : valor.getLibros()) {
                        select = "SELECT * FROM LIBRO WHERE TITULO_LIBRO like '" + libro.getTitulo() + "' AND AUTOR_LIBRO like '" + libro.getAutor() + "'";
                        ResultSet esteLibro = accesoBD.query(select);
                        if (!esteLibro.first()) {
                            insert = "INSERT INTO LIBRO (AUTOR_LIBRO, TITULO_LIBRO, LENGUAJE_LIBRO) VALUES ('" + libro.getAutor() + "', '" + libro.getTitulo() + "', '" + libro.getIdioma() + "')";
                            accesoBD.noQuery(insert);
                            insert = "INSERT INTO PALABRAXLIBRO VALUES ('" + valor.getContenido() + "', " + esteLibro.getString("ID_LIBRO") + ")";
                            accesoBD.noQuery(insert);
                        }

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }*/

    public void cargarPalabras(ArrayList<File> listaArchivo) {
        for (File archivo : listaArchivo) {
            /*if (!archivo.getName().endsWith(".txt")) //Revisamos que el archivo sea .txt
             {
             return;
             }*/
            try {

                Libro nuevoLibro = this.tomarDatosLibros(archivo);

                if (existeLibro(nuevoLibro)) {
                    return;
                }
                librosCargados.add(nuevoLibro);

                this.cargarPalabrasEnHash(archivo, nuevoLibro);
                System.out.println(nuevoLibro.toString());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    //Comprueba que la palabra no contenga ninguno de estos caracteres: @(arroba),0,1,2,3,4,5,6,7,8,9
    private boolean validarPalabra(String palabra) {
        String[] caracterNoValido = {"@", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "="};
        for (String caracter : caracterNoValido) {
            if (palabra.contains(caracter) || palabra.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private Libro tomarDatosLibros(File archivo) {

        StringBuffer titulo = new StringBuffer();
        StringBuffer autor = new StringBuffer();
        StringBuffer fecha = new StringBuffer();
        StringBuffer lenguaje = new StringBuffer();
        String[] palabras;
        boolean f = false;
        try {
            BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "8859_1"));
            String linea = lector.readLine();

            while (!f) {
                if (linea.contains("Title:")) {

                    palabras = linea.split(" ");
                    for (String palabra : palabras) {
                        if (!palabra.contains(":")) {
                            titulo.append(palabra.toLowerCase() + " ");
                        }
                    }
                } else if (linea.contains("Author:")) {
                    palabras = linea.split(" ");

                    for (String palabra : palabras) {
                        if (!palabra.contains(":")) {
                            autor.append(palabra.toLowerCase() + " ");
                        }
                    }
                } else if (linea.contains("Language:")) {
                    palabras = linea.split(" ");
                    f = true;
                    for (String palabra : palabras) {
                        if (!palabra.contains(":")) {
                            lenguaje.append(palabra.toLowerCase() + " ");
                        }
                    }
                } else if (linea.contains("Release Date:")) {
                    palabras = linea.split(" ");

                    for (String palabra : palabras) {
                        if (!palabra.contains(":") && !palabra.contains("[") && !palabra.contains("]") && !palabra.contains("Release")) {
                            fecha.append(palabra.toLowerCase() + " ");
                        }
                    }
                }
                linea = lector.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new Libro(autor.toString(), titulo.toString(), lenguaje.toString());
    }

    private void cargarPalabrasEnHash(File archivo, Libro libro) {

        try {
            BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "8859_1"));
            String[] palabras = null;
            String delimitadores = "[ _.,;:?!¡«»¿\\'\\\"\\\\[\\[]\\]()~#*/-]+";

            Interfaz.BarraProgreso barraActual = this.mostrarBarraProgreso(archivo);
            barraActual.setVisible(false);
            String linea = lector.readLine(); //Lee una linea de texto del archivo
            System.out.println(linea);
            while (linea != null) //Un ciclo para "linea" no nulo
            {
                palabras = linea.split(delimitadores); //Dividimos las palabras de "linea" por los delimitadores, y lo almacenamos en "palabras"
                for (String palabra : palabras) //Ciclo que recorre todas las palabras obtenidas anteriormente
                {
                    if (validarPalabra(palabra)) {
                        palabra = palabra.toLowerCase(); //Convertir la palabra a letra minúscula
                        // System.out.println(palabra + " - ");  // PARA PROBAR

                        //Verificamos que la tabla no esté vacía, en caso de no estarlo buscamos si la palabra a ser ingresada existe o no
                        //Si la palabra existe sumamos el contador
                        if (!hashTable.isEmpty() && hashTable.containsKey(palabra.hashCode())) {
                            hashTable.get(Math.abs(palabra.hashCode())).sumarContador();
                            hashTable.get(Math.abs(palabra.hashCode())).agregarLibro(libro);
                        } //De estar vacía la tabla o de no encontrarse la palabra simplemente se inserta una nueva palabra
                        else {
                            hashTable.put(Math.abs(palabra.hashCode()), new Palabra(palabra, libro));
                        }
                    }
                }

                barraActual.actualizarBarraProgreso();

                linea = lector.readLine();

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private int calcularCantidadLineas(File archivo) {
        int cantidadLineas = 0;

        try {
            BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "8859_1"));
            String linea = lector.readLine();
            while (linea != null) {
                cantidadLineas++;
                linea = lector.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cantidadLineas;
    }

    private Interfaz.BarraProgreso mostrarBarraProgreso(File archivo) {
        Interfaz.BarraProgreso barra = new Interfaz.BarraProgreso();
        barra.crearBarra(this.calcularCantidadLineas(archivo));
        barra.setVisible(true);
        return barra;
    }

    private boolean existeLibro(Libro libro) {
        for (Libro librosCargado : librosCargados) {
            if (librosCargado.compareTo(libro) == 0) {
                return true;
            }
        }
        return false;
    }

    public void visualizarPalabras(String filtro) {
        String[] modelo = {"Palabra", "Repeticiones", "Libros"}; //Vector que representa las columnas de la tabla
        Object[][] matrizLibros = new Object[this.hashTable.size()][3]; //Una matriz de objetos que sería la tabla en sí

        //Recorrer la hashTable y almacenando los objetos en una matriz para setear el modelo de la JLIST
        Enumeration e = hashTable.keys();
        int clave, i = 0;
        Palabra valor;
        //Aca se va a ir llenando la matriz con los valores que tiene la hashTable, para llenar la tabla
        while (e.hasMoreElements()) {
            clave = (Integer) e.nextElement();//Se busca la clave hash
            valor = hashTable.get(clave);//Se busca la Palabra en esa posicion hash
            if (filtro.equals("") || valor.getContenido().startsWith(filtro.toLowerCase())) {
                Object[] palabra = {valor.getContenido(), valor.getContador(), valor.librosDondeSeEncuentra()};
                matrizLibros[i] = palabra;//A cada fila de la matriz le corresponde el libro anterior
                i++;
            }
        }
        ventanaPalabras.setModeloTabla(modelo, matrizLibros);
        ventanaPalabras.setGestor(this);
        ventanaPalabras.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaPalabras.setVisible(true);
    }
;

}
