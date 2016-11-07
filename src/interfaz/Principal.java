package Interfaz;

import Logica.Gestor;
import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();        
        gestor = new Gestor();
        //gestor.cargarPalabras();
        listaArchivosSeleccionados = new ArrayList<File>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_seleccionarLibros = new javax.swing.JButton();
        btn_verPalabra = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaLibros = new javax.swing.JList();
        btn_cargarPalabras = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_seleccionarLibros.setText("Seleccionar Libros");
        btn_seleccionarLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_seleccionarLibrosActionPerformed(evt);
            }
        });

        btn_verPalabra.setText("Ver Palabras");
        btn_verPalabra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verPalabraActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 2, 18)); // NOI18N
        jLabel1.setText("Trabajo Práctico Único");

        jScrollPane1.setViewportView(listaLibros);

        btn_cargarPalabras.setText("Cargar Palabras");
        btn_cargarPalabras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cargarPalabrasActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(92, 92, 92))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btn_seleccionarLibros, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btn_verPalabra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_cargarPalabras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton1))
                        .addGap(25, 25, 25))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btn_seleccionarLibros)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(34, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_cargarPalabras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_verPalabra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(65, 65, 65))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_seleccionarLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionarLibrosActionPerformed
        JFileChooser selectorArchivo = new JFileChooser(); //Se declara un Selector de archivos 
        selectorArchivo.setMultiSelectionEnabled(true); //Se setea el selector de archivos en selección múltiple
        selectorArchivo.setCurrentDirectory(new File("F:\\Mauri\\GitHub\\TP\\Libros [TP Unico TSB 2014]")); //Se setea el directorio por defecto en el selector de archivos
        selectorArchivo.setFileFilter(new javax.swing.filechooser.FileFilter() //Filtra todos los archivos que no sean directorios o archivos .txt en el selector de archivos
        {
            //Los archivos que aparencen en la ventanita
            @Override
            public boolean accept(File f) {
                return (f.isFile() && f.getName().endsWith(".txt")) || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Archivos de Texto";
            }

        });

        if (selectorArchivo.showDialog(this, "Abrir") != JFileChooser.CANCEL_OPTION) {
            try {
                File[] arregloArchivosNuevos = selectorArchivo.getSelectedFiles();
                ArrayList<File> arregloArchivosViejo = this.listaArchivosSeleccionados;
                DefaultListModel modeloList = new DefaultListModel();
                boolean agregar = false; 
                
                if (this.listaArchivosSeleccionados.isEmpty()) {
                    for (File archivo : arregloArchivosNuevos) {
                        modeloList.addElement(archivo.getName());
                        this.listaArchivosSeleccionados.add(archivo);
                    }
                } else {
                    for (File archivo : arregloArchivosViejo) {
                        modeloList.addElement(archivo.getName());
                    }

                    for (File archivo : arregloArchivosNuevos) {
                        agregar = true;
                        
                        for (File archivoLista : arregloArchivosViejo) {
                            if (archivoLista.getAbsolutePath().equals(archivo.getAbsolutePath())) {  
                                agregar = false;
                                break;                                
                            }
                        }
                        
                        if (agregar){
                            modeloList.addElement(archivo.getName());
                            this.listaArchivosSeleccionados.add(archivo);
                        }
                    }
                }
                
                listaLibros.setModel(modeloList);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                
            }
        }
    }//GEN-LAST:event_btn_seleccionarLibrosActionPerformed

    private void btn_verPalabraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verPalabraActionPerformed
        gestor.visualizarPalabras("");
    }//GEN-LAST:event_btn_verPalabraActionPerformed

    private void btn_cargarPalabrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cargarPalabrasActionPerformed
        Runnable corredor = new Runnable() {
            public void run() {
                if (listaArchivosSeleccionados.size() != 0) {
                    gestor.cargarPalabras(listaArchivosSeleccionados);
                }
                listaLibros.setModel(new DefaultListModel());
                listaArchivosSeleccionados.clear();
            }
        };
        Thread nuevoHilo = new Thread(corredor, "Code Executer");
        nuevoHilo.start();
    }//GEN-LAST:event_btn_cargarPalabrasActionPerformed
   
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // gestor.guardarPalabras();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    

    private Gestor gestor;
    private ArrayList<File> listaArchivosSeleccionados;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cargarPalabras;
    private javax.swing.JButton btn_seleccionarLibros;
    private javax.swing.JButton btn_verPalabra;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listaLibros;
    // End of variables declaration//GEN-END:variables

}
