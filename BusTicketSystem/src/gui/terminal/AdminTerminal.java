/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.terminal;

import gui.MenuPrincipal;
import gui.base.AdminBase;
import methods.Direccion;
import methods.Empleado;
import methods.Estado;
import methods.Terminal;
import utils.UtilsTable;

/**
 *
 * @author David
 */
public class AdminTerminal extends javax.swing.JFrame {
    Object listaTerminales[][];
    Object listaEmpleados[][];
    Object listaDirecciones[][];
    Object listaEstados[][];
    Object[] dir;
    Object[] emp;
    Object[] est;
    Object[] bas;
    Object[] sal;
    
    Terminal terminal;
    Empleado empleado;
    Direccion direccion;
    Estado estado;
    /**
     * Creates new form AdminTerminal
     */
    public AdminTerminal() {
        initComponents();
        terminal = new Terminal();
        empleado = new Empleado();
        direccion = new Direccion();
        estado = new Estado();
        
        mostrarTerminales();
    }
    
    public void mostrarTerminales() {
        // consulta los datos de las terminales
        listaTerminales = terminal.selectTerminales();
        listaDirecciones = direccion.selectDirecciones();
        listaEmpleados = empleado.selectEmpleados();
        listaEstados = estado.selectEstados();
        Object[][] listaT = new Object[listaTerminales.length][11];
        for(int i=0; i<listaTerminales.length; i++)
        {
            dir = direccion.selectDireccion(Integer.parseInt(listaTerminales[i][2].toString()));
            emp = empleado.selectEmpleado( Integer.parseInt(listaTerminales[i][3].toString()) );
            est = estado.selectEstado( Integer.parseInt(dir[8].toString()) );
            listaT[i][0] = listaTerminales[i][0];
            listaT[i][1] = est[1].toString();
            listaT[i][2] = listaTerminales[i][1];
            listaT[i][3] = emp[1].toString();
            listaT[i][4] = dir[1].toString();
            listaT[i][5] = dir[2].toString();
            listaT[i][6] = dir[3].toString();
            listaT[i][7] = dir[4].toString();
            listaT[i][8] = dir[5].toString();
            listaT[i][9] = dir[6].toString();
            listaT[i][10] = dir[7].toString();
        }
        // configuración de la tabla
        String[] T_TERMINALES = {"ID","Estado", "Nombre", "Gerente", "Ciudad", 
            "Colonia", "CP", "Calle", "#", "Tel.", "e-mail"};
        int[][] cellAlignment = {{0,javax.swing.SwingConstants.LEFT}};
        int[][] cellSize = {{0,73},
                            {1,73},
                            {2,73},
                            {3,73},
                            {4,73},
                            {5,73},
                            {6,73},
                            {7,73},
                            {8,73},
                            {9,73},
                            {10,73}};
        // pone los datos en la tabla
        UtilsTable.llenaTabla(TablaTerminales,listaT, 
                T_TERMINALES, cellAlignment, cellSize);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaTerminales = new javax.swing.JTable();
        BotonAgregarTerminal = new javax.swing.JButton();
        BotonEditarTerminal = new javax.swing.JButton();
        BotonEliminarTerminal = new javax.swing.JButton();
        BotonAdministrarBases = new javax.swing.JButton();
        BotonRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 0, 51));

        jLabel9.setFont(new java.awt.Font("Broadway", 0, 72)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ADE");

        jLabel10.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Autobuses del Este");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ADMINISTRAR TERMINALES");

        TablaTerminales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaTerminales.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(TablaTerminales);

        BotonAgregarTerminal.setText("AGREGAR TERMINAL");
        BotonAgregarTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarTerminalActionPerformed(evt);
            }
        });

        BotonEditarTerminal.setText("EDITAR TERMINAL");
        BotonEditarTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEditarTerminalActionPerformed(evt);
            }
        });

        BotonEliminarTerminal.setText("ELIMINAR TERMINAL");
        BotonEliminarTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarTerminalActionPerformed(evt);
            }
        });

        BotonAdministrarBases.setText("ADMINISTRAR BASES");
        BotonAdministrarBases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAdministrarBasesActionPerformed(evt);
            }
        });

        BotonRegresar.setText("REGRESAR");
        BotonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 320, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BotonEditarTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonAgregarTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonEliminarTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonAdministrarBases, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(71, 71, 71))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BotonAgregarTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BotonEditarTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BotonEliminarTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BotonAdministrarBases, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonAgregarTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarTerminalActionPerformed
        AgregarTerminal agT = new AgregarTerminal();
        agT.setVisible(true);
        dispose();
    }//GEN-LAST:event_BotonAgregarTerminalActionPerformed

    private void BotonEditarTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEditarTerminalActionPerformed
        if (TablaTerminales.getSelectedRow() < 0)
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una fila.","Información",1);
        else
        {
            Object[] editTerminal = terminal.selectTerminal(Integer.parseInt(TablaTerminales.getValueAt(TablaTerminales.getSelectedRow(), 0).toString()));
            
            ActualizarTerminal actT = new ActualizarTerminal(Integer.parseInt(editTerminal[0].toString()));
            actT.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_BotonEditarTerminalActionPerformed

    private void BotonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRegresarActionPerformed
        MenuPrincipal p = new MenuPrincipal(1, "USUARIO");
            p.setVisible(true);
            dispose();
    }//GEN-LAST:event_BotonRegresarActionPerformed

    private void BotonEliminarTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarTerminalActionPerformed
        
        direccion.deleteDireccion( Integer.parseInt(listaTerminales[ TablaTerminales.getSelectedRow() ][2].toString()) );
        terminal.deleteTerminal( Integer.parseInt(listaTerminales[ TablaTerminales.getSelectedRow() ][0].toString()) );
        mostrarTerminales();
    }//GEN-LAST:event_BotonEliminarTerminalActionPerformed

    private void BotonAdministrarBasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAdministrarBasesActionPerformed
        AdminBase adB = new AdminBase();
        adB.setVisible(true);
        dispose();
    }//GEN-LAST:event_BotonAdministrarBasesActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(AdminTerminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminTerminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminTerminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminTerminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminTerminal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAdministrarBases;
    private javax.swing.JButton BotonAgregarTerminal;
    private javax.swing.JButton BotonEditarTerminal;
    private javax.swing.JButton BotonEliminarTerminal;
    private javax.swing.JButton BotonRegresar;
    private javax.swing.JTable TablaTerminales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
