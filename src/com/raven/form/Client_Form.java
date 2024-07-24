/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.raven.component.Form;
import com.raven.connection.ConnectionDB;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Client_Form extends Form {



    public Client_Form() {
        initComponents();
        txtNumDni.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            filtrarTabla();
        }

        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            filtrarTabla();
        }

        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            filtrarTabla();
        }
    });
        mostrarTabla();
        inicializarFormulario();
     tablaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getClickCount() == 2) {
                cargarDatosFilaSeleccionada();
            }
        }
    });
    }

    //--------------------------------------------------------------
    //AQUI VAN A ESTAR TODOS LOS METODOS
    //--------------------------------------------------------------
     //METODO PARA FILTRAR LA TABLA
    private void filtrarTabla() {
    String dni = txtNumDni.getText().trim();
    
    // Definir la consulta con un parámetro para el DNI
    String query = "SELECT c.Id_Cliente, c.NombreApellido, c.Documento, c.Teléfono, c.Correo, m.Plan, m.Fecha_Inicio, m.Fecha_Final, m.Precio " +
                   "FROM cliente c " +
                   "JOIN membresía m ON c.Id_Membresía = m.Id_Membresía " +
                   "WHERE c.Documento LIKE ?";
    
    try (Connection conn = ConnectionDB.conectar();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, "%" + dni + "%"); // Usar LIKE para buscar por el DNI
        ResultSet rs = pstmt.executeQuery();
        
        // Crear el modelo de tabla con los datos obtenidos
        DefaultTableModel modelo = (DefaultTableModel) tablaCliente.getModel();
        modelo.setRowCount(0); // Limpiar las filas existentes
        
        while (rs.next()) {
            // Agregar cada fila al modelo de tabla
            modelo.addRow(new Object[]{
                rs.getInt("Id_Cliente"),
                rs.getString("NombreApellido"),
                rs.getString("DNI"),
                rs.getString("Teléfono"),
                rs.getString("Correo"),
                rs.getString("Plan"),
                rs.getDate("Fecha_Inicio"),
                rs.getDate("Fecha_Final"),
                rs.getDouble("Precio")
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this, "Error al filtrar los datos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}

    
    //--------------------------------------------------------------
    //-----METODO PARA MOSTRAR LOS DATOS DE LA FILA A LOS CAMPOS----
    //--------------------------------------------------------------
    private void cargarDatosFilaSeleccionada() {
    if (tablaCliente.getSelectedRow() != -1) {
        // Obtener la fila seleccionada
        int selectedRow = tablaCliente.getSelectedRow();

        // Obtener los datos de la fila seleccionada
        String nombre = tablaCliente.getValueAt(selectedRow, 1).toString();
        String dni = tablaCliente.getValueAt(selectedRow, 2).toString();
        String telefono = tablaCliente.getValueAt(selectedRow, 3).toString();
        String correo = tablaCliente.getValueAt(selectedRow, 4).toString();
        String membresia = tablaCliente.getValueAt(selectedRow, 5).toString();
        String fechaInicial = tablaCliente.getValueAt(selectedRow, 6).toString();
        String fechaFinal = tablaCliente.getValueAt(selectedRow, 7).toString();
        String precio = tablaCliente.getValueAt(selectedRow, 8).toString();

        // Cargar los datos en los campos
        txtNombre.setText(nombre);
        txtDNI.setText(dni);
        txtTeléfono.setText(telefono);
        txtCorreo.setText(correo);
        cbxMembresia.setSelectedItem(membresia);

        // Para los campos de fecha, asumiendo que ya tienes objetos JDateChooser
        txtfechainicial.setDate(parseDate(fechaInicial));
        txtfechafinal.setDate(parseDate(fechaFinal));
        txtPrecio.setText(precio);

        // Desbloquear los campos para edición
        desbloquearCampos();
    }
}

// Método auxiliar para parsear la fecha desde un String
private java.util.Date parseDate(String dateStr) {
    try {
        return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
    } catch (java.text.ParseException e) {
        e.printStackTrace();
        return null;
    }
}

// Método para desbloquear los campos
private void desbloquearCampos() {
    txtNombre.setEnabled(true);
    txtDNI.setEnabled(true);
    txtTeléfono.setEnabled(true);
    txtCorreo.setEnabled(true);
    cbxMembresia.setEnabled(true);
    txtfechainicial.setEnabled(true);
    txtfechafinal.setEnabled(true);
    txtPrecio.setEnabled(true);
}






    
    
    
    
    
    //--------------------------------------------------------------
    //--METODO PARA MOSTRAR LA BASE DE DATOS A LA TABLA DEL JPANEL--
    //--------------------------------------------------------------   
     private void mostrarTabla() {
    DefaultTableModel tableModel = (DefaultTableModel) tablaCliente.getModel();
    tableModel.setRowCount(0);  // Limpiar la tabla antes de agregar nuevos datos
    
    String query = "SELECT c.Id_Cliente, c.NombreApellido, c.Documento, c.Teléfono, c.Correo, " +
                   "m.Plan, m.Fecha_Inicio, m.Fecha_Final, m.Precio " +
                   "FROM cliente c " +
                   "JOIN membresía m ON c.Id_Membresía = m.Id_Membresía";

    try (Connection conn = ConnectionDB.conectar();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            Object[] row = {
                rs.getInt("Id_Cliente"),
                rs.getString("NombreApellido"),
                rs.getString("Documento"),
                rs.getString("Teléfono"),
                rs.getString("Correo"),
                rs.getString("Plan"),
                rs.getDate("Fecha_Inicio"),
                rs.getDate("Fecha_Final"),
                rs.getDouble("Precio")
            };
            tableModel.addRow(row);
            System.out.println("Row added: " + java.util.Arrays.toString(row)); // Debugging line
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    
    
    //--------------------------------------------------------------
    //-------------METODO PARA AGREGAR UN NUEVO CLIENTE-------------
    //--------------------------------------------------------------
public void agregarCliente() {
    // Obtener los datos de los campos
    String nombre = txtNombre.getText().trim();
    String dni = txtDNI.getText().trim();
    String telefono = txtTeléfono.getText().trim();
    String correo = txtCorreo.getText().trim();
    String membresia = cbxMembresia.getSelectedItem().toString();
    String fechaInicial = new java.text.SimpleDateFormat("yyyy-MM-dd").format(txtfechainicial.getDate());
    String fechaFinal = new java.text.SimpleDateFormat("yyyy-MM-dd").format(txtfechafinal.getDate());
    String precio = txtPrecio.getText().trim();
    
    // Validar campos
    if (nombre.isEmpty() || dni.isEmpty() || telefono.isEmpty() || correo.isEmpty() || membresia.isEmpty() || fechaInicial.isEmpty() || fechaFinal.isEmpty() || precio.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Todos los campos deben ser completados correctamente.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Obtener el Id_Membresía asociado con el plan de membresía seleccionado
    String idMembresia = getIdMembresia(membresia);
    
    if (idMembresia == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "El plan de membresía seleccionado no es válido.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Insertar datos en la tabla membresía
    String insertMembresia = "INSERT INTO membresía (Plan, Precio, Fecha_Inicio, Fecha_Final) VALUES (?, ?, ?, ?)";
    String insertCliente = "INSERT INTO cliente (NombreApellido, Documento, Teléfono, Correo, Id_Membresía) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection conn = ConnectionDB.conectar();
         PreparedStatement pstmtMembresia = conn.prepareStatement(insertMembresia, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement pstmtCliente = conn.prepareStatement(insertCliente)) {
        
        // Insertar datos en membresía
        pstmtMembresia.setString(1, membresia);
        pstmtMembresia.setString(2, precio);
        pstmtMembresia.setString(3, fechaInicial);
        pstmtMembresia.setString(4, fechaFinal);
        pstmtMembresia.executeUpdate();
        
        // Obtener el Id_Membresía generado
        ResultSet generatedKeys = pstmtMembresia.getGeneratedKeys();
        if (generatedKeys.next()) {
            idMembresia = String.valueOf(generatedKeys.getInt(1));
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No se pudo obtener el ID de membresía.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Insertar datos en cliente
        pstmtCliente.setString(1, nombre);
        pstmtCliente.setString(2, dni);
        pstmtCliente.setString(3, telefono);
        pstmtCliente.setString(4, correo);
        pstmtCliente.setString(5, idMembresia);
        pstmtCliente.executeUpdate();
        
        javax.swing.JOptionPane.showMessageDialog(this, "Cliente agregado correctamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos(); // Limpiar campos después de agregar
        mostrarTabla(); // Actualizar la tabla
    } catch (SQLException e) {
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this, "Error al agregar el cliente.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}
private String getIdMembresia(String plan) {
    String idMembresia = null;
    String query = "SELECT Id_Membresía FROM membresía WHERE Plan = ?";
    try (Connection conn = ConnectionDB.conectar();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, plan);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            idMembresia = rs.getString("Id_Membresía");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return idMembresia;
}



private void limpiarCampos() {
    // Limpiar los campos de texto
    txtNombre.setText("");
    txtDNI.setText("");
    txtTeléfono.setText("");
    txtCorreo.setText("");
    txtPrecio.setText("");

    // Limpiar los campos de fecha
    txtfechainicial.setDate(null);
    txtfechafinal.setDate(null);

    // Restablecer el combo box a su valor inicial
    cbxMembresia.setSelectedIndex(0); // O ajusta al valor que consideres como predeterminado

    // Asegurarse de que los campos estén bloqueados
    inicializarFormulario(); // Bloquear campos si es necesario
}





// Método para obtener Id_Membresía desde el plan
private String getIdMembresiaFromPlan(String plan) {
    String idMembresia = null;
    String query = "SELECT Id_Membresía FROM membresía WHERE Plan = ?";
    try (Connection conn = ConnectionDB.conectar();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setString(1, plan);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            idMembresia = rs.getString("Id_Membresía");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return idMembresia;
}







    
    //--------------------------------------------------------------
    //---------------METODO PARA ELIMINAR UN CLIENTE----------------
    //--------------------------------------------------------------
    private void eliminarCliente(String dni) {
    // Eliminar cliente de la base de datos
    String deleteCliente = "DELETE FROM cliente WHERE Documento = ?";
    try (Connection conn = ConnectionDB.conectar();
         PreparedStatement pstmt = conn.prepareStatement(deleteCliente)) {
        pstmt.setString(1, dni);
        int rowsAffected = pstmt.executeUpdate();
        
        if (rowsAffected > 0) {
            // Cliente eliminado con éxito
            javax.swing.JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            // Actualizar la tabla
            mostrarTabla();
        } else {
            // Cliente no encontrado
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontró un cliente con el DNI proporcionado.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this, "Error al eliminar el cliente.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}


    //--------------------------------------------------------------
    //-----------METODO PARA ACTUALIZAR DATOS DEL CLIENTE-----------
    //--------------------------------------------------------------
    private void actualizarCliente() {
    // Obtener los datos de los campos
    String nombre = txtNombre.getText().trim();
    String dni = txtDNI.getText().trim();
    String telefono = txtTeléfono.getText().trim();
    String correo = txtCorreo.getText().trim();
    String membresia = cbxMembresia.getSelectedItem().toString();
    int idMembresia = obtenerIdMembresia(membresia); // Obtener el Id_Membresía
    String fechaInicial = new java.text.SimpleDateFormat("yyyy-MM-dd").format(txtfechainicial.getDate());
    String fechaFinal = new java.text.SimpleDateFormat("yyyy-MM-dd").format(txtfechafinal.getDate());
    String precio = txtPrecio.getText().trim();
    
    // Validar el DNI
    if (dni.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "El DNI debe ser proporcionado.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Actualizar datos en la base de datos
    String updateCliente = "UPDATE cliente SET NombreApellido = ?, Teléfono = ?, Correo = ?, Id_Membresía = ? WHERE Documento = ?";
    String updateMembresia = "UPDATE membresía SET Fecha_Inicio = ?, Fecha_Final = ?, Precio = ? WHERE Id_Membresía = ?";
    try (Connection conn = ConnectionDB.conectar();
         PreparedStatement pstmtCliente = conn.prepareStatement(updateCliente);
         PreparedStatement pstmtMembresia = conn.prepareStatement(updateMembresia)) {

        // Actualizar datos del cliente
        pstmtCliente.setString(1, nombre);
        pstmtCliente.setString(2, telefono);
        pstmtCliente.setString(3, correo);
        pstmtCliente.setInt(4, idMembresia); // Usa el Id_Membresía
        pstmtCliente.setString(5, dni);
        int rowsAffectedCliente = pstmtCliente.executeUpdate();

        // Actualizar datos de membresía
        pstmtMembresia.setString(1, fechaInicial);
        pstmtMembresia.setString(2, fechaFinal);
        pstmtMembresia.setString(3, precio);
        pstmtMembresia.setInt(4, idMembresia); // Usa el Id_Membresía
        int rowsAffectedMembresia = pstmtMembresia.executeUpdate();
        
        if (rowsAffectedCliente > 0 && rowsAffectedMembresia > 0) {
            // Actualización exitosa
            javax.swing.JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            // Actualizar la tabla
            mostrarTabla();
        } else {
            // No se actualizó ningún registro
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontró un cliente con el DNI proporcionado o la membresía no existe.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar el cliente.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}




    //----------------------------------------------------------------
    //METODO PARA OBTENER EL ID MEMBRESÍA A PARTIR DEL NOMBRE DEL PLAN
    //----------------------------------------------------------------
    // Método para obtener el Id_Membresía basado en el Plan
private int obtenerIdMembresia(String plan) {
    String query = "SELECT Id_Membresía FROM membresía WHERE Plan = ?";
    try (Connection conn = ConnectionDB.conectar();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, plan);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("Id_Membresía");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Retorna -1 si no se encuentra el Id_Membresía
}



    
    

    //------------------------------------------------------------
    //--------------METODO PARA BLOQUEAR LOS CAMPOS---------------
    //-------------------------------------------------------------
    private void bloquearCampos() {
    txtNombre.setEnabled(false);
    txtDNI.setEnabled(false);
    txtTeléfono.setEnabled(false);
    txtCorreo.setEnabled(false);
    cbxMembresia.setEnabled(false);
    txtfechainicial.setEnabled(false);
    txtfechafinal.setEnabled(false);
    txtPrecio.setEnabled(false);
    }
    
    
   
    
    
   private void inicializarFormulario() {
    bloquearCampos(); // Bloquea todos los campos del formulario al inicio

    // Limpia los campos si es necesario
    txtNombre.setText("");
    txtDNI.setText("");
    txtTeléfono.setText("");
    txtCorreo.setText("");
    cbxMembresia.setSelectedIndex(0); // O cualquier valor predeterminado
    txtfechainicial.setDate(null);
    txtfechafinal.setDate(null);
    txtPrecio.setText("");

    // Actualiza la tabla o cualquier otro componente si es necesario
    mostrarTabla();
}


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTeléfono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbxMembresia = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        txtNumDni = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        BtnNuevo = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtfechainicial = new com.toedter.calendar.JDateChooser();
        txtfechafinal = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 192, 40));
        jLabel1.setText("ADMINISTRAR CLIENTES");

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 192, 40));
        jLabel2.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 192, 40));
        jLabel3.setText("DNI:");

        txtDNI.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNIKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 192, 40));
        jLabel4.setText("Teléfono:");

        txtTeléfono.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtTeléfono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTeléfonoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 192, 40));
        jLabel5.setText("Correo:");

        txtCorreo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 192, 40));
        jLabel6.setText("Membresia:");

        cbxMembresia.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cbxMembresia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mensual", "Trimestral", "Semestral", "Anual" }));

        jLabel7.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 192, 40));
        jLabel7.setText("Fecha Inicial:");

        jLabel8.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 192, 40));
        jLabel8.setText("Fecha Final:");

        btnCrear.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        txtNumDni.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtNumDni.setForeground(new java.awt.Color(0, 0, 0));
        txtNumDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumDniActionPerformed(evt);
            }
        });
        txtNumDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumDniKeyTyped(evt);
            }
        });

        tablaCliente.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Documento", "Teléfono", "Correo", "Membresia", "Fecha Inicial", "Fecha Final", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
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
        tablaCliente.setRowHeight(25);
        tablaCliente.getTableHeader().setReorderingAllowed(false);
        tablaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCliente);
        if (tablaCliente.getColumnModel().getColumnCount() > 0) {
            tablaCliente.getColumnModel().getColumn(0).setPreferredWidth(10);
            tablaCliente.getColumnModel().getColumn(8).setPreferredWidth(10);
        }

        BtnNuevo.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        BtnNuevo.setText("Nuevo");
        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });

        BtnGuardar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        BtnGuardar.setText("Guardar");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtfechainicial.setDateFormatString("yyyy-MM-dd");

        txtfechafinal.setDateFormatString("yyyy-MM-dd");

        jLabel10.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 192, 40));
        jLabel10.setText("Precio:");

        txtPrecio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 192, 40));
        jLabel9.setText("Buscar por DNI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1)
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addGap(503, 503, 503)
                .addComponent(jLabel1))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel9))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtNumDni)
                .addGap(285, 285, 285)
                .addComponent(btnCrear)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(BtnNuevo)
                .addGap(18, 18, 18)
                .addComponent(BtnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(12, 12, 12)
                        .addComponent(txtNombre)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel3)
                        .addGap(8, 8, 8)
                        .addComponent(txtDNI)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8)
                        .addComponent(txtTeléfono)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5)
                        .addGap(8, 8, 8)
                        .addComponent(txtCorreo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addComponent(cbxMembresia, 0, 132, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(12, 12, 12)
                        .addComponent(txtfechainicial, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8)
                        .addGap(10, 10, 10)
                        .addComponent(txtfechafinal, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)
                        .addGap(11, 11, 11)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)))
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtTeléfono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addComponent(cbxMembresia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtfechainicial, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtfechafinal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23)
                .addComponent(jLabel9)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(txtNumDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCrear))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnNuevo)
                    .addComponent(BtnGuardar)
                    .addComponent(btnEliminar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumDniActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtNumDniActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        // TODO add your handling code here:
        actualizarCliente();
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // TODO add your handling code here:
        agregarCliente();
    }//GEN-LAST:event_btnCrearActionPerformed

    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
        // TODO add your handling code here:
        desbloquearCampos();
        // Limpia los campos si es necesario
    txtNombre.setText("");
    txtDNI.setText("");
    txtTeléfono.setText("");
    txtCorreo.setText("");
    cbxMembresia.setSelectedIndex(0);
    txtfechainicial.setDate(null);
    txtfechafinal.setDate(null);
    txtPrecio.setText("");
    }//GEN-LAST:event_BtnNuevoActionPerformed

    private void tablaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClienteMouseClicked
       
    }//GEN-LAST:event_tablaClienteMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
          // Obtener la fila seleccionada
    int selectedRow = tablaCliente.getSelectedRow();

    // Verificar si se ha seleccionado una fila
    if (selectedRow >= 0) {
        // Obtener el DNI de la fila seleccionada
        String dni = tablaCliente.getValueAt(selectedRow, 2).toString(); // Asegúrate de que el índice de columna es el correcto

        // Confirmar la eliminación con el usuario
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar al cliente con DNI " + dni + "?", "Confirmación", javax.swing.JOptionPane.YES_NO_OPTION);
        
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            //AQUI SE ESTA LLAMANDO AL METODO PARA ELIMINAR AL CLIENTE
            eliminarCliente(dni);
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona una fila para eliminar.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
     
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNumDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumDniKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtNumDniKeyTyped

    private void txtDNIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIKeyTyped
        // TODO add your handling code here:
         // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtDNIKeyTyped

    private void txtTeléfonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTeléfonoKeyTyped
        // TODO add your handling code here:
         // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtTeléfonoKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        // TODO add your handling code here:
         // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtPrecioKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cbxMembresia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaCliente;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumDni;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTeléfono;
    private com.toedter.calendar.JDateChooser txtfechafinal;
    private com.toedter.calendar.JDateChooser txtfechainicial;
    // End of variables declaration//GEN-END:variables
}
