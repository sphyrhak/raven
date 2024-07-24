
package com.raven.form;

import com.raven.component.Form;
import com.raven.connection.ConnectionDB;
import com.raven.event.EventColorChange;
import com.raven.theme.ThemeColorChange;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import com.raven.component.Form;
import com.raven.connection.ConnectionDB;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JTable;

public class Sale_Form extends Form {
//private JTable jTable1;
    private DefaultTableModel modelo;
    public Sale_Form() {
        initComponents();
        mostrarTabla();
        if (ThemeColorChange.getInstance().getMode() == ThemeColorChange.Mode.LIGHT) {
            lbTitleSale.setForeground(new Color(80, 80, 80));
            lbTexDNI.setForeground(new Color(80, 80, 80));
        }
        
    }

    @Override
    public void changeColor(Color color) {
        lbTitleSale.setForeground(color);
        lbTexDNI.setForeground(color);       
    }

 private void mostrarTabla() {
       // Definir el modelo de la tabla
    String[] titulos = {"Id_Ventas", "NombreApellido", "Teléfono", "Documento", "Fecha", "Subtotal", "IGV", "Total"};
    DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);

    String sql = "SELECT v.Id_Ventas, c.NombreApellido, c.Teléfono, c.Documento, " +
                 "v.Fecha, v.Subtotal, v.IGV, v.Total " +
                 "FROM ventas v " +
                 "INNER JOIN cliente c ON v.Id_Cliente = c.Id_Cliente";

    try (Connection cn = ConnectionDB.conectar(); // Asegúrate de que tu método de conexión esté funcionando
         Statement st = cn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            Object[] fila = new Object[8];
            fila[0] = rs.getInt("Id_Ventas");
            fila[1] = rs.getString("NombreApellido");
            fila[2] = rs.getString("Teléfono");
            fila[3] = rs.getString("Documento");
            fila[4] = rs.getString("Fecha");
            fila[5] = rs.getDouble("Subtotal");
            fila[6] = rs.getDouble("IGV");
            fila[7] = rs.getDouble("Total");
            modelo.addRow(fila);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + e.getMessage());
        System.out.println("Error al mostrar los datos: " + e.getMessage());
    }
    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbTitleSale = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        lbTexDNI = new javax.swing.JLabel();

        jPanel1.setOpaque(false);

        lbTitleSale.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTitleSale.setForeground(new java.awt.Color(230, 230, 230));
        lbTitleSale.setText("ADMINISTRAR VENTA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(374, 374, 374)
                .addComponent(lbTitleSale)
                .addContainerGap(381, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitleSale)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "DNI", "TIPO", "FECHA", "DESCRIPCION", "SUBTOTAL", "IGV", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(45);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(60);
        }

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        lbTexDNI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTexDNI.setForeground(new java.awt.Color(230, 230, 230));
        lbTexDNI.setText("Ingresar DNI del cliente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTexDNI)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(59, 59, 59))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(lbTexDNI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addGap(41, 41, 41))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbTexDNI;
    private javax.swing.JLabel lbTitleSale;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
