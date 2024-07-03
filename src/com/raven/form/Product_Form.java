
package com.raven.form;

import com.raven.component.Form;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Product_Form extends Form {
    private int selectedRow = -1;
    public Product_Form() {
        initComponents();
        addTableMouseListener();
        
        // Código para tener bloqueado los campos de texto
        disableTextFields();
        //codigo para tener bloqueado los campos de texto
        txtNombreProducto.setEnabled(false);
        txtCategoria.setEnabled(false);
        txtStock.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtStockMax.setEnabled(false);
        txtStockMin.setEnabled(false);
        txtUnidadMedida.setEnabled(false);
    }
    //metodo para la barra de busqueda
    
    
    
    private void clearTextFields() {
        txtNombreProducto.setText("");
        txtCategoria.setText("");
        txtStock.setText("");
        txtPrecio.setText("");
        txtDescripcion.setText("");
        txtStockMax.setText("");
        txtStockMin.setText("");
        txtUnidadMedida.setText("");
    }

    private void disableTextFields() {
        txtNombreProducto.setEnabled(false);
        txtCategoria.setEnabled(false);
        txtStock.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtStockMax.setEnabled(false);
        txtStockMin.setEnabled(false);
        txtUnidadMedida.setEnabled(false);
    }
    private void addTableMouseListener() {
        jTable1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    selectedRow = jTable1.getSelectedRow();
                    if (selectedRow != -1) {
                        loadSelectedRowData(selectedRow);
                    }
                }
            }
        });
    }
    private void loadSelectedRowData(int row) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object[] rowData = getRowData(row);
        if (rowData != null && rowData.length > 0) {
            txtNombreProducto.setText(rowData[1].toString());
            txtCategoria.setText(rowData[2].toString());
            txtPrecio.setText(rowData[3].toString());
            txtStock.setText(rowData[4].toString());
            txtUnidadMedida.setText(rowData[5].toString());
            txtStockMin.setText(rowData[6].toString());
            txtStockMax.setText(rowData[7].toString());
            txtDescripcion.setText(rowData[8].toString());

            enableTextFields();
        } else {
            JOptionPane.showMessageDialog(this, "Fila vacía seleccionada. Por favor, selecciona una fila con datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Object[] getRowData(int row) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if (row >= 0 && row < model.getRowCount()) {
            return new Object[]{
                model.getValueAt(row, 0),
                model.getValueAt(row, 1),
                model.getValueAt(row, 2),
                model.getValueAt(row, 3),
                model.getValueAt(row, 4),
                model.getValueAt(row, 5),
                model.getValueAt(row, 6),
                model.getValueAt(row, 7),
                model.getValueAt(row, 8)
            };
        }
        return null;
    }
    private void enableTextFields() {
        txtNombreProducto.setEnabled(true);
        txtCategoria.setEnabled(true);
        txtStock.setEnabled(true);
        txtPrecio.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtStockMax.setEnabled(true);
        txtStockMin.setEnabled(true);
        txtUnidadMedida.setEnabled(true);
    }
     
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        lblNombreProducto = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        lblStock = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        lblStockMax = new javax.swing.JLabel();
        lblDescripción = new javax.swing.JLabel();
        txtStockMax = new javax.swing.JTextField();
        txtStockMin = new javax.swing.JTextField();
        txtUnidadMedida = new javax.swing.JTextField();
        lblStockMin = new javax.swing.JLabel();
        lblUnidadMedida = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        txtSearchBar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("PRODUCTOS");

        lblNombreProducto.setText("Nombre Producto:");

        txtNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProductoActionPerformed(evt);
            }
        });

        lblCategoria.setText("Categoría:");

        txtCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoriaActionPerformed(evt);
            }
        });

        lblStock.setText("Stock:");

        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });

        lblPrecio.setText("Precio:");

        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });

        lblStockMax.setText("Stock max:");

        lblDescripción.setText("Descripción:");

        txtStockMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockMaxActionPerformed(evt);
            }
        });

        txtStockMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockMinActionPerformed(evt);
            }
        });

        txtUnidadMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnidadMedidaActionPerformed(evt);
            }
        });

        lblStockMin.setText("Stock min:");

        lblUnidadMedida.setText("Unidad de medida:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre producto", "Categoría", "Precio", "Stock", "Unidad de medida", "Stock min", "Stock max", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
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
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setResizable(false);
        }

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        txtSearchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchBarActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingrese el nombre del producto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(439, 439, 439)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNombreProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPrecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblUnidadMedida)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblStockMin)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtStockMin, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblStockMax)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtStockMax, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblDescripción)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregar))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnNuevo)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnGuardar)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnEliminar))
                                .addComponent(txtSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)))))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreProducto)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategoria)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecio)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStock)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUnidadMedida)
                    .addComponent(txtUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStockMin)
                    .addComponent(txtStockMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStockMax)
                    .addComponent(txtStockMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripción)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar))
                .addContainerGap(161, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.removeRow(selectedRow);
            clearTextFields();
            disableTextFields();
            selectedRow = -1;
        } else {
            JOptionPane.showMessageDialog(this, "No hay fila seleccionada para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoriaActionPerformed

    private void txtNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProductoActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockActionPerformed

    private void txtUnidadMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnidadMedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnidadMedidaActionPerformed

    private void txtStockMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockMinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockMinActionPerformed

    private void txtStockMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockMaxActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        // Obtener los datos de los campos de texto
    String nombreProducto = txtNombreProducto.getText();
    String categoria = txtCategoria.getText();
    String precio = txtPrecio.getText();
    String stock = txtStock.getText();
    String unidadMedida = txtUnidadMedida.getText();
    String stockMin = txtStockMin.getText();
    String stockMax = txtStockMax.getText();
    String descripcion = txtDescripcion.getText();

    // Verificar si todos los campos están llenos
    if (nombreProducto.isEmpty() || categoria.isEmpty() || precio.isEmpty() || 
        stock.isEmpty() || unidadMedida.isEmpty() || stockMin.isEmpty() || 
        stockMax.isEmpty() || descripcion.isEmpty()) {
        // Mostrar un mensaje de error si hay campos vacíos
        JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener el modelo de la tabla
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    
    // Insertar los datos en la primera posición de la tabla
    model.insertRow(0, new Object[]{model.getRowCount() + 1, nombreProducto, categoria, 
                                    Double.parseDouble(precio), Integer.parseInt(stock), 
                                    unidadMedida, Integer.parseInt(stockMin), 
                                    Integer.parseInt(stockMax), descripcion});

    // Bloquear los campos de texto nuevamente
    txtNombreProducto.setEnabled(false);
    txtCategoria.setEnabled(false);
    txtStock.setEnabled(false);
    txtPrecio.setEnabled(false);
    txtDescripcion.setEnabled(false);
    txtStockMax.setEnabled(false);
    txtStockMin.setEnabled(false);
    txtUnidadMedida.setEnabled(false);

    // Limpiar los campos de texto
    txtNombreProducto.setText("");
    txtCategoria.setText("");
    txtStock.setText("");
    txtPrecio.setText("");
    txtDescripcion.setText("");
    txtStockMax.setText("");
    txtStockMin.setText("");
    txtUnidadMedida.setText("");
    
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        txtNombreProducto.setEnabled(true);
        txtCategoria.setEnabled(true);
        txtStock.setEnabled(true);
        txtPrecio.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtStockMax.setEnabled(true);
        txtStockMin.setEnabled(true);
        txtUnidadMedida.setEnabled(true);
        // Limpiar los campos de texto
    txtNombreProducto.setText("");
    txtCategoria.setText("");
    txtStock.setText("");
    txtPrecio.setText("");
    txtDescripcion.setText("");
    txtStockMax.setText("");
    txtStockMin.setText("");
    txtUnidadMedida.setText("");
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (selectedRow != -1) {
            // Obtener los datos de los campos de texto
            String nombreProducto = txtNombreProducto.getText();
            String categoria = txtCategoria.getText();
            String precio = txtPrecio.getText();
            String stock = txtStock.getText();
            String unidadMedida = txtUnidadMedida.getText();
            String stockMin = txtStockMin.getText();
            String stockMax = txtStockMax.getText();
            String descripcion = txtDescripcion.getText();

            // Verificar si todos los campos están llenos
            if (nombreProducto.isEmpty() || categoria.isEmpty() || precio.isEmpty() || 
                stock.isEmpty() || unidadMedida.isEmpty() || stockMin.isEmpty() || 
                stockMax.isEmpty() || descripcion.isEmpty()) {
                // Mostrar un mensaje de error si hay campos vacíos
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Actualizar la fila seleccionada en la tabla
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setValueAt(nombreProducto, selectedRow, 1);
            model.setValueAt(categoria, selectedRow, 2);
            model.setValueAt(Double.parseDouble(precio), selectedRow, 3);
            model.setValueAt(Integer.parseInt(stock), selectedRow, 4);
            model.setValueAt(unidadMedida, selectedRow, 5);
            model.setValueAt(Integer.parseInt(stockMin), selectedRow, 6);
            model.setValueAt(Integer.parseInt(stockMax), selectedRow, 7);
            model.setValueAt(descripcion, selectedRow, 8);

            // Limpiar los campos de texto
            clearTextFields();

            // Deshabilitar los campos de texto
            disableTextFields();

            // Resetear la fila seleccionada
            selectedRow = -1;
        } else {
            JOptionPane.showMessageDialog(this, "No hay fila seleccionada para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtSearchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchBarActionPerformed
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_txtSearchBarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDescripción;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblStockMax;
    private javax.swing.JLabel lblStockMin;
    private javax.swing.JLabel lblUnidadMedida;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSearchBar;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtStockMax;
    private javax.swing.JTextField txtStockMin;
    private javax.swing.JTextField txtUnidadMedida;
    // End of variables declaration//GEN-END:variables
}
