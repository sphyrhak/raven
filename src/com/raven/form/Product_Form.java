
package com.raven.form;

import com.raven.component.Form;
import com.raven.connection.ConnectionDB;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import com.raven.querys.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;



public class Product_Form extends Form {
    
    private int selectedRow = -1;
    ConnectionDB con1 = new ConnectionDB();
    Connection conet;
    DefaultTableModel modelo;
    Statement st;
    ResultSet rs;
    int idc;
    
    
    public Product_Form() {
        initComponents();
//        addTableMouseListener();
jTable1.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        int fila = jTable1.getSelectedRow();
        if (fila >= 0) {
            loadSelectedRowData(fila);
        }
    }
});

        mostrarTabla();
        
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
    
    
    //---------------------------------------------------------------
    //CODIGO PARA MOSTRAR LOS DAOTS DE LA FILA A LOS CAMPOS DEL PANEL
    //---------------------------------------------------------------
    private void loadSelectedRowData(int row) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    // Obtener el ID del producto de la fila seleccionada
    int idProducto = (int) model.getValueAt(row, 0);
    
    // Consulta SQL para obtener los datos del producto por ID
    String sql = "SELECT * FROM productos WHERE Id_Producto = ?";

    try (Connection conet = con1.conectar();
         PreparedStatement pst = conet.prepareStatement(sql)) {

        // Establecer el parámetro de la consulta
        pst.setInt(1, idProducto);

        // Ejecutar la consulta y obtener el resultado
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                // Obtener los datos del ResultSet y asignarlos a los campos
                txtNombreProducto.setText(rs.getString("NombreProducto"));
                txtCategoria.setText(rs.getString("Categoría"));
                txtPrecio.setText(rs.getString("Precio"));
                txtStock.setText(rs.getString("Stock"));
                txtStockMin.setText(rs.getString("Stock_Min"));
                txtStockMax.setText(rs.getString("Stock_Max"));
                txtUnidadMedida.setText(rs.getString("UnidadMedida"));
                txtDescripcion.setText(rs.getString("DescripciónProducto"));

                // Habilitar campos para edición si es necesario
                enableTextFields();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron datos para el producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar los datos del producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        System.out.println("Error al cargar los datos del producto: " + e.getMessage());
    }
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
    
    //---------------------------------------------------
    //METODO PARA TRAER LA BASE DE DATOS A NUESTRO JPANEL
    //---------------------------------------------------
    void mostrarTabla(){
    
       String[] titulos = {"Id_Producto", "NombreProducto", "Stock", "Stock_Min", "Stock_Max", "Precio", "Categoría", "UnidadMedida", "DescripciónProducto"};
    modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);

    String sql = "SELECT * FROM productos";

    try (Connection conet = con1.conectar();
         Statement st = conet.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            Object[] fila = new Object[9];
            fila[0] = rs.getInt("Id_Producto");
            fila[1] = rs.getString("NombreProducto");
            fila[2] = rs.getInt("Stock");
            fila[3] = rs.getInt("Stock_Min");
            fila[4] = rs.getInt("Stock_Max");
            fila[5] = rs.getDouble("Precio");
            fila[6] = rs.getString("Categoría");
            fila[7] = rs.getString("UnidadMedida");
            fila[8] = rs.getString("DescripciónProducto");
            modelo.addRow(fila);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + e.getMessage());
        System.out.println("Error al mostrar los datos: " + e.getMessage());
    }
}

    
    //---------------------------------------------------
    //METODO PARA AGREGAR A LA BASE DE DATOS
    //---------------------------------------------------
    void agregarFila(){
        // Obtener texto de los campos y limpiar espacios en blanco
//    String idProducto = txtIdProducto.getText().trim();  // Asume que Id_Producto es un campo de texto
    String nom = txtNombreProducto.getText().trim();
    String cate = txtCategoria.getText().trim();
    String prec = txtPrecio.getText().trim();
    String stock = txtStock.getText().trim();
    String stockmin = txtStockMin.getText().trim();
    String stockmax = txtStockMax.getText().trim();
    String medida = txtUnidadMedida.getText().trim();
    String descrip = txtDescripcion.getText().trim();
    
    System.out.println("Nombre: " + nom);
    System.out.println("Categoría: " + cate);
    System.out.println("Precio: " + prec);
    System.out.println("Stock: " + stock);
    System.out.println("Stock Min: " + stockmin);
    System.out.println("Stock Max: " + stockmax);
    System.out.println("Unidad Medida: " + medida);
    System.out.println("Descripción: " + descrip);
    
    // Validar que todos los campos están completos
    if (nom.isEmpty() || cate.isEmpty() || prec.isEmpty() || stock.isEmpty() ||
        stockmin.isEmpty() || stockmax.isEmpty() || medida.isEmpty() || descrip.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos.");
        return; // Salir del método si hay campos vacíos
    }
    
    // Construir la consulta SQL
    String sql = "INSERT INTO productos (NombreProducto, Stock, Stock_Min, Stock_Max, Precio, Categoría, UnidadMedida, DescripciónProducto) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    try (Connection conet = con1.conectar(); 
         PreparedStatement pst = conet.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
//        pst.setString(1, idProducto);  // Asume que Id_Producto es una cadena; cambia según tu tipo de dato
        pst.setString(1, nom);
        pst.setInt(2, Integer.parseInt(stock));          // Convertir a entero si es necesario
        pst.setInt(3, Integer.parseInt(stockmin));       // Convertir a entero si es necesario
        pst.setInt(4, Integer.parseInt(stockmax));       // Convertir a entero si es necesario
        pst.setDouble(5, Double.parseDouble(prec));      // Convertir a doble si es necesario
        pst.setString(6, cate);
        pst.setString(7, medida);
        pst.setString(8, descrip);

        // Ejecutar la consulta
        int filasAfectadas = pst.executeUpdate();
        
        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Nuevo producto agregado correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el producto.");
        }
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error en el formato de número: " + e.getMessage());
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error SQL: " + e.getMessage());
    }
    }
    
    //---------------------------------------------------
    //METODO PARA LIMPIAR LA TABLA DEL JPANEL 
    //---------------------------------------------------
    void limpiarTabla(){
        for (int i = 0; i <= jTable1.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i-1;
        }
    }
    
    //---------------------------------------------------
    //METODO PARA LIMPIAR LOS CAMPOS DEL JTABLE
    //---------------------------------------------------
    public void limpiarCampos() {
        // Limpiar todos los campos de texto
        txtNombreProducto.setText("");
        txtCategoria.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtStockMin.setText("");
        txtStockMax.setText("");
        txtUnidadMedida.setText("");
        txtDescripcion.setText("");
    }

    //---------------------------------------------------
    //METODO PARA ELIMINAR LA FILA DE LA TABLA
    //---------------------------------------------------
    void eliminar(){
        int fila = jTable1.getSelectedRow();
    if (fila < 0) {
        JOptionPane.showMessageDialog(null, "Producto no seleccionado");
    } else {
        // Obtener el Id_Producto de la fila seleccionada
        int idProducto = (int) jTable1.getValueAt(fila, 0); // Asume que Id_Producto está en la primera columna

        try {
            String sql = "DELETE FROM productos WHERE Id_Producto=" + idProducto;
            conet = con1.conectar();
            st = conet.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Producto eliminado");

            // Eliminar la fila del modelo de la tabla
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.removeRow(fila);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    }

    //---------------------------------------------------
    //METODO PARA ACTUALIZAR LA FILA DE LA TABLA
    //---------------------------------------------------
    void modificar(){
    int fila = jTable1.getSelectedRow();
    if (fila < 0) {
        JOptionPane.showMessageDialog(null, "Seleccione un producto para modificar.");
        return;
    }

    // Obtener el ID del producto de la fila seleccionada
    int idProducto = (int) jTable1.getValueAt(fila, 0);

    // Obtener texto de los campos y limpiar espacios en blanco
    String nom = txtNombreProducto.getText().trim();
    String cate = txtCategoria.getText().trim();
    String prec = txtPrecio.getText().trim();
    String stock = txtStock.getText().trim();
    String stockmin = txtStockMin.getText().trim();
    String stockmax = txtStockMax.getText().trim();
    String medida = txtUnidadMedida.getText().trim();
    String descrip = txtDescripcion.getText().trim();

    // Validar que todos los campos están completos
    if (nom.isEmpty() || cate.isEmpty() || prec.isEmpty() || stock.isEmpty() ||
        stockmin.isEmpty() || stockmax.isEmpty() || medida.isEmpty() || descrip.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos.");
        return; // Salir del método si hay campos vacíos
    }

    // Validar y convertir los campos numéricos
    int stockInt = 0, stockMinInt = 0, stockMaxInt = 0;
    double precioDouble = 0.0;

    try {
        stockInt = Integer.parseInt(stock);
        stockMinInt = Integer.parseInt(stockmin);
        stockMaxInt = Integer.parseInt(stockmax);
        precioDouble = Double.parseDouble(prec);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error en el formato de número: " + e.getMessage());
        System.out.println("Error en el formato de número: " + e.getMessage());
        return; // Salir del método si hay un error de formato
    }

    // Construir la consulta SQL
    String sql = "UPDATE productos SET NombreProducto=?, Stock=?, Stock_Min=?, Stock_Max=?, Precio=?, Categoría=?, UnidadMedida=?, DescripciónProducto=? WHERE Id_Producto=?";

    try (Connection conet = con1.conectar();
         PreparedStatement pst = conet.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
        pst.setString(1, nom);
        pst.setInt(2, stockInt);
        pst.setInt(3, stockMinInt);
        pst.setInt(4, stockMaxInt);
        pst.setDouble(5, precioDouble);
        pst.setString(6, cate);
        pst.setString(7, medida);
        pst.setString(8, descrip);
        pst.setInt(9, idProducto);

        // Ejecutar la consulta
        int filasAfectadas = pst.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Producto modificado correctamente.");
            limpiarCampos();
            mostrarTabla(); // Actualizar la tabla para reflejar los cambios
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo modificar el producto.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error SQL: " + e.getMessage());
        System.out.println("Error SQL: " + e.getMessage());
    }
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
                "NombreProducto", "Categoría", "Precio", "Stock", "Unidad_Medida", "Stock_Min", "Stock_Max", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setRowHeight(25);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(30);
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
                                .addComponent(txtUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(lblStockMin)
                                .addGap(6, 6, 6)
                                .addComponent(txtStockMin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(lblStockMax)
                                .addGap(11, 11, 11)
                                .addComponent(txtStockMax, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDescripción))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombreProducto)
                                .addGap(9, 9, 9)
                                .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(lblCategoria)
                                .addGap(9, 9, 9)
                                .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(lblPrecio)
                                .addGap(7, 7, 7)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(lblStock, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(txtNombreProducto)
                    .addComponent(lblCategoria)
                    .addComponent(txtCategoria)
                    .addComponent(lblPrecio)
                    .addComponent(txtPrecio)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblStock)
                        .addComponent(txtStock)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUnidadMedida)
                    .addComponent(txtUnidadMedida)
                    .addComponent(lblStockMin)
                    .addComponent(txtStockMin)
                    .addComponent(lblStockMax)
                    .addComponent(txtStockMax)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDescripción)
                        .addComponent(txtDescripcion)))
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
        eliminar(); 
        mostrarTabla();
        limpiarCampos();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoriaActionPerformed

    }//GEN-LAST:event_txtCategoriaActionPerformed

    private void txtNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProductoActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed

    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed

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
    //SIRVE PARA QUE DESPUES DE AGREGAR LOS CAMPOS SE BLOQUEEN
    
    txtNombreProducto.setEnabled(false);
    txtCategoria.setEnabled(false);
    txtStock.setEnabled(false);
    txtPrecio.setEnabled(false);
    txtDescripcion.setEnabled(false);
    txtStockMax.setEnabled(false);
    txtStockMin.setEnabled(false);
    txtUnidadMedida.setEnabled(false);
    
        agregarFila();
        mostrarTabla();
        limpiarCampos();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        //CODIGO PARA HABILITAR LOS CAMPOS DESPUÉS DE DARLE CLICK AL BOTON NUEVO
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
        txtNombreProducto.setEnabled(false);
    txtCategoria.setEnabled(false);
    txtStock.setEnabled(false);
    txtPrecio.setEnabled(false);
    txtDescripcion.setEnabled(false);
    txtStockMax.setEnabled(false);
    txtStockMin.setEnabled(false);
    txtUnidadMedida.setEnabled(false);

        modificar();
        limpiarCampos();
        mostrarTabla();
        
        
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
//        char c = evt.getKeyChar();//Llamamos el evento
//        if (Character.isDigit(c)) { //Comparamos si ingresamos un digito
//            evt.consume(); //evitar que se capture el digito
//            JOptionPane.showMessageDialog(this, "Solo se permite letras en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
//        }
    }//GEN-LAST:event_txtNombreProductoKeyTyped

    private void txtCategoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCategoriaKeyTyped
        // TODO add your handling code here:
//        char c = evt.getKeyChar();//Llamamos el evento
//        if (Character.isDigit(c)) { //Comparamos si ingresamos un digito
//            evt.consume(); //evitar que se capture el digito
//            JOptionPane.showMessageDialog(this, "Solo se permite letras en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
//        }
    }//GEN-LAST:event_txtCategoriaKeyTyped

    private void txtUnidadMedidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadMedidaKeyTyped
        // TODO add your handling code here:
//        char c = evt.getKeyChar();//Llamamos el evento
//        if (Character.isDigit(c)) { //Comparamos si ingresamos un digito
//            evt.consume(); //evitar que se capture el digito
//            JOptionPane.showMessageDialog(this, "Solo se permite letras en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
//        }
    }//GEN-LAST:event_txtUnidadMedidaKeyTyped

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        // TODO add your handling code here:
//        char c = evt.getKeyChar();//Llamamos el evento
//        if (Character.isDigit(c)) { //Comparamos si ingresamos un digito
//            evt.consume(); //evitar que se capture el digito
//            JOptionPane.showMessageDialog(this, "Solo se permite letras en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
//        }
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        // TODO add your handling code here:
//        char c = evt.getKeyChar();//Llamamos el evento
//        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
//            evt.consume(); //evitar que se capture el digito
//            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
//        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyTyped
        // TODO add your handling code here:
//        char c = evt.getKeyChar();//Llamamos el evento
//        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
//            evt.consume(); //evitar que se capture el digito
//            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
//        }
    }//GEN-LAST:event_txtStockKeyTyped

    private void txtStockMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockMinKeyTyped
//        // TODO add your handling code here:
//        char c = evt.getKeyChar();//Llamamos el evento
//        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
//            evt.consume(); //evitar que se capture el digito
//            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
//        }
    }//GEN-LAST:event_txtStockMinKeyTyped

    private void txtStockMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockMaxKeyTyped
        // TODO add your handling code here:
//        char c = evt.getKeyChar();//Llamamos el evento
//        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
//            evt.consume(); //evitar que se capture el digito
//            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
//        }
    }//GEN-LAST:event_txtStockMaxKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked


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
