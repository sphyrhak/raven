
package com.raven.form;

import com.raven.component.Form;
import java.awt.BorderLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;




public class Product_Form extends Form {
    
    private int selectedRow = -1;
    
    public Product_Form() {
        initComponents();
        addTableMouseListener();
       
        
        
        // Código para tener bloqueado los campos de texto
        disableTextFields();//Sirve para bloquear los campos del texto al inicio de la app
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
    private void filtrarProductos(String textoBusqueda) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    jTable1.setRowSorter(sorter);

    if (textoBusqueda.isEmpty()) {
        // Si el campo de búsqueda está vacío, muestra todas las filas
        sorter.setRowFilter(null);
    } else {
        // Aplica un filtro que ignore mayúsculas y minúsculas y busque coincidencias parciales en cualquier columna
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda));
    }
}
    
    
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
                if (e.getClickCount() == 1) {
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
    private void agregarFilaATabla(String nombreProducto, String categoria, double precio, String stock, String unidadMedida, String stockMin, String stockMax, String descripcion) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{model.getRowCount() + 1, nombreProducto, categoria, precio, Integer.parseInt(stock), unidadMedida, Integer.parseInt(stockMin), Integer.parseInt(stockMax), descripcion});
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
        jLabel3 = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PRODUCTOS");

        lblNombreProducto.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblNombreProducto.setForeground(new java.awt.Color(255, 204, 0));
        lblNombreProducto.setText("Nombre Producto:");

        txtNombreProducto.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProductoActionPerformed(evt);
            }
        });
        txtNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyTyped(evt);
            }
        });

        lblCategoria.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblCategoria.setForeground(new java.awt.Color(255, 204, 0));
        lblCategoria.setText("Categoría:");

        txtCategoria.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoriaActionPerformed(evt);
            }
        });
        txtCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCategoriaKeyTyped(evt);
            }
        });

        lblStock.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblStock.setForeground(new java.awt.Color(255, 204, 0));
        lblStock.setText("Stock:");

        txtStock.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });
        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockKeyTyped(evt);
            }
        });

        lblPrecio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(255, 204, 0));
        lblPrecio.setText("Precio:");

        txtPrecio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        txtDescripcion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        lblStockMax.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblStockMax.setForeground(new java.awt.Color(255, 204, 51));
        lblStockMax.setText("Stock max:");

        lblDescripción.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblDescripción.setForeground(new java.awt.Color(255, 204, 0));
        lblDescripción.setText("Descripción:");

        txtStockMax.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtStockMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockMaxActionPerformed(evt);
            }
        });
        txtStockMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockMaxKeyTyped(evt);
            }
        });

        txtStockMin.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtStockMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockMinActionPerformed(evt);
            }
        });
        txtStockMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockMinKeyTyped(evt);
            }
        });

        txtUnidadMedida.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtUnidadMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnidadMedidaActionPerformed(evt);
            }
        });
        txtUnidadMedida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnidadMedidaKeyTyped(evt);
            }
        });

        lblStockMin.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblStockMin.setForeground(new java.awt.Color(255, 204, 0));
        lblStockMin.setText("Stock min:");

        lblUnidadMedida.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblUnidadMedida.setForeground(new java.awt.Color(255, 204, 0));
        lblUnidadMedida.setText("Unidad de medida:");

        jTable1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE PRODUCTO", "CATEGORÍA", "PRECIO", "STOCK", "UNIDAD DE MEDIDA", "STOCK MIN", "STOCK MAX", "DESCRIPCIÓN"
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
        jTable1.setRowHeight(25);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(30);
        }

        btnNuevo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        txtSearchBar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtSearchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchBarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 0));
        jLabel1.setText("Ingrese el nombre del producto:");

        jLabel3.setText("joshua");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUnidadMedida)
                                .addGap(5, 5, 5)
                                .addComponent(txtUnidadMedida, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addGap(10, 10, 10)
                                .addComponent(lblStockMin)
                                .addGap(6, 6, 6)
                                .addComponent(txtStockMin, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addGap(8, 8, 8)
                                .addComponent(lblStockMax)
                                .addGap(11, 11, 11)
                                .addComponent(txtStockMax, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addGap(10, 10, 10)
                                .addComponent(lblDescripción)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombreProducto)
                                .addGap(9, 9, 9)
                                .addComponent(txtNombreProducto)
                                .addGap(10, 10, 10)
                                .addComponent(lblCategoria)
                                .addGap(9, 9, 9)
                                .addComponent(txtCategoria)
                                .addGap(48, 48, 48)
                                .addComponent(lblPrecio)
                                .addGap(7, 7, 7)
                                .addComponent(txtPrecio)
                                .addGap(60, 60, 60)
                                .addComponent(lblStock, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtStock, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(txtDescripcion))
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearchBar)
                        .addGap(38, 38, 38)
                        .addComponent(btnAgregar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addGap(24, 24, 24)
                                .addComponent(btnGuardar)
                                .addGap(20, 20, 20)
                                .addComponent(btnEliminar)
                                .addGap(117, 117, 117)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(48, 48, 48))
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreProducto)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategoria)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecio)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblStock)
                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUnidadMedida)
                    .addComponent(txtUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStockMin)
                    .addComponent(txtStockMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStockMax)
                    .addComponent(txtStockMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDescripción)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar))
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevo)
                            .addComponent(btnGuardar)
                            .addComponent(btnEliminar)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addGap(50, 50, 50))
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
        try {
    double precio = Double.parseDouble(txtPrecio.getText());
    // Aquí puedes usar 'precio' según tus necesidades
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Por favor, ingresa un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed
        // TODO add your handling code here:
        try {
    int stock = Integer.parseInt(txtStock.getText());
    if (stock < 0) {
        JOptionPane.showMessageDialog(this, "El stock debe ser un número entero positivo.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    // Aquí puedes usar 'stock' según tus necesidades
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Por favor, ingresa un valor numérico válido para el stock.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}
    }//GEN-LAST:event_txtStockActionPerformed

    private void txtUnidadMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnidadMedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnidadMedidaActionPerformed

    private void txtStockMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockMinActionPerformed
        // TODO add your handling code here:
        try {
    int stockMin = Integer.parseInt(txtStockMin.getText());
    if (stockMin < 0) {
        JOptionPane.showMessageDialog(this, "El stock mínimo debe ser un número entero positivo.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    // Aquí puedes usar 'stockMin' según tus necesidades
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Por favor, ingresa un valor numérico válido para el stock mínimo.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}
    }//GEN-LAST:event_txtStockMinActionPerformed

    private void txtStockMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockMaxActionPerformed
        // TODO add your handling code here:
        try {
    int stockMax = Integer.parseInt(txtStockMax.getText());
    if (stockMax < 0) {
        JOptionPane.showMessageDialog(this, "El stock máximo debe ser un número entero positivo.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    // Aquí puedes usar 'stockMax' según tus necesidades
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Por favor, ingresa un valor numérico válido para el stock máximo.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}
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
    //Verificar si el precio es numérico
    
    
        
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
        // Obtén el texto ingresado en el campo de búsqueda
    String textoBusqueda = txtSearchBar.getText().trim();

    // Filtra la tabla de productos según el texto de búsqueda
    filtrarProductos(textoBusqueda);
        
        
    }//GEN-LAST:event_txtSearchBarActionPerformed

    private void txtNombreProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isDigit(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite letras en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtNombreProductoKeyTyped

    private void txtCategoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCategoriaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isDigit(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite letras en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtCategoriaKeyTyped

    private void txtUnidadMedidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadMedidaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isDigit(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite letras en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtUnidadMedidaKeyTyped

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isDigit(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite letras en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtStockKeyTyped

    private void txtStockMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockMinKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtStockMinKeyTyped

    private void txtStockMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockMaxKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtStockMaxKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
