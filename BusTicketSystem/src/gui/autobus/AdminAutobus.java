/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.autobus;

import gui.MenuPrincipal;
import methods.Autobus;
import methods.Base;
import methods.Carroceria;
import methods.Empleado;
import methods.Linea;
import methods.Terminal;
import utils.UtilsTable;

/**
 *
 * @author David
 */
public class AdminAutobus extends javax.swing.JFrame {
    Object[][] listaAutobus;
    Object[][] listaCarrocerias;
    Object[][] listaBases;
    Object[][] listaLineas;
    Object[][] listaEmpleados;
    Object[] car;
    Object[] bas;
    Object[] lin;
    Object[] emp;
    Object[] term;
    
    Autobus autobus;
    Carroceria carroceria;
    Base base;
    Linea linea;
    Empleado empleado;      //OPERADOR
    Terminal terminal;
    /**
     * Creates new form AdminAutobus
     */
    public AdminAutobus() {
        initComponents();
        autobus = new Autobus();
        carroceria = new Carroceria();
        base = new Base();
        linea = new Linea();
        empleado = new Empleado();
        terminal = new Terminal();
        
        mostrarAutobuses();
    }
    
    public void mostrarAutobuses()
    {
        listaAutobus = autobus.selectAutobuses();
        Object[][] listaT = new Object[listaAutobus.length][7];
        for( int k=0; k<listaAutobus.length; k++ )
        {
            listaT[k][0] = listaAutobus[k][0];
            listaT[k][1] = listaAutobus[k][1];
            listaT[k][2] = listaAutobus[k][2];
            car = carroceria.selectCarroceria(Integer.parseInt(listaAutobus[k][3].toString()));
            lin = linea.selectLinea(Integer.parseInt(listaAutobus[k][5].toString()));
            emp = empleado.selectEmpleado(Integer.parseInt(listaAutobus[k][6].toString()));
            bas = base.selectBase(Integer.parseInt(listaAutobus[k][4].toString()));
            term = terminal.selectTerminal(Integer.parseInt(bas[1].toString()));
            listaT[k][3] = car[1].toString()+" "+car[2].toString();
            listaT[k][4] = term[1].toString();
            listaT[k][5] = lin[1].toString();
            listaT[k][6] = emp[1].toString();
        }
        String[] T_ESTADOS = {"No.","Valor","Años","Carroceria","Base","Linea","Operador"};
        int[][] cellAlignment = {{0,javax.swing.SwingConstants.LEFT}};
        int[][] cellSize = {{0,80},
                            {0,80},
                            {0,80},
                            {0,80},
                            {0,80},
                            {0,80},
                            {0,80},};
        // pone los datos en la tabla
        UtilsTable.llenaTabla(TablaAutobuses,listaT, 
                T_ESTADOS, cellAlignment, cellSize);
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
        TablaAutobuses = new javax.swing.JTable();
        BotonAgregarAutobus = new javax.swing.JButton();
        BotonEditarAutobus = new javax.swing.JButton();
        BotonEliminarAutobus = new javax.swing.JButton();
        BotonRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 0, 51));

        jLabel9.setFont(new java.awt.Font("Broadway", 0, 72)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ADE");

        jLabel10.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Autobuses del Este");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ADMINISTRAR AUTOBUSES");

        TablaAutobuses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaAutobuses.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(TablaAutobuses);

        BotonAgregarAutobus.setText("AGREGAR AUTOBUS");
        BotonAgregarAutobus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarAutobusActionPerformed(evt);
            }
        });

        BotonEditarAutobus.setText("EDITAR AUTOBUS");
        BotonEditarAutobus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEditarAutobusActionPerformed(evt);
            }
        });

        BotonEliminarAutobus.setText("ELIMINAR AUTOBUS");
        BotonEliminarAutobus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarAutobusActionPerformed(evt);
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
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BotonEditarAutobus, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonAgregarAutobus, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonEliminarAutobus, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotonAgregarAutobus, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BotonEditarAutobus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BotonEliminarAutobus, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonAgregarAutobusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarAutobusActionPerformed
        AgregarAutobus agB = new AgregarAutobus();
        agB.setVisible(true);
        dispose();
    }//GEN-LAST:event_BotonAgregarAutobusActionPerformed

    private void BotonEditarAutobusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEditarAutobusActionPerformed
        if (TablaAutobuses.getSelectedRow() < 0)
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una fila.","Información",1);
        else
        {
            Object[] editAutobus = autobus.selectAutobus(Integer.parseInt(TablaAutobuses.getValueAt(TablaAutobuses.getSelectedRow(), 0).toString()));

            ActualizarAutobus actA = new ActualizarAutobus(Integer.parseInt(editAutobus[0].toString()));
            actA.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_BotonEditarAutobusActionPerformed

    private void BotonEliminarAutobusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarAutobusActionPerformed
        int r = autobus.deleteAutobus( Integer.parseInt(listaAutobus[TablaAutobuses.getSelectedRow() ][0].toString()) ); //Elimina el registro
        switch (r) {
            case 0:
                javax.swing.JOptionPane.showMessageDialog(this, "Autobus eliminado correctamente.","Aviso",1);
                break;
            case 1:
                javax.swing.JOptionPane.showMessageDialog(this, "No se puede borrar el registro.","Aviso",1);
                break;
            default:
                javax.swing.JOptionPane.showMessageDialog(this, "Ocurrio un error al intentar borrar el autobus.","Aviso",1);
                break;
        }
        listaAutobus = autobus.selectAutobuses();
        mostrarAutobuses();
    }//GEN-LAST:event_BotonEliminarAutobusActionPerformed

    private void BotonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRegresarActionPerformed
        MenuPrincipal t = new MenuPrincipal(1,"Usuario");
        t.setVisible(true);
        dispose();
    }//GEN-LAST:event_BotonRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(AdminAutobus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminAutobus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminAutobus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminAutobus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminAutobus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAgregarAutobus;
    private javax.swing.JButton BotonEditarAutobus;
    private javax.swing.JButton BotonEliminarAutobus;
    private javax.swing.JButton BotonRegresar;
    private javax.swing.JTable TablaAutobuses;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
