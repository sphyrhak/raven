package com.raven.form;

import com.raven.component.Form;
import com.raven.connection.ConnectionDB;
import com.raven.querys.Sales;
import com.raven.querys.VentaPDF;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Sale_Form2 extends Form {

    public String status = "EMITIDO";

    private Connection cn = ConnectionDB.conectar();
    private int idCliente = 0;
    String total;
    String code;
    String action;
    String saleNumber, documentType;
    private int idProducto = 0;
    private String nombreProducto = "";
    private int cantidadProductoBBDD = 0;
    private double precioUnitario = 0.0;

    int records;
    String ids[] = new String[50];
    int num = 0;

    static int counter;
    public String employeeId, employeeName;
    int saleId, newSaleId;

    public String productCode;
    static Connection conn = null;

    static ResultSet rs = null;
    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtmDetail = new DefaultTableModel();

    String criterion, search;

    public Sale_Form2() {
        initComponents();
//        txtDates.setDisabledTextColor(Color.blue);
//        txtDates.setText(date());
//        txtDates.setVisible(true);//falta esta variable
        this.CargarComboClientes();
        this.CargarComboProductos();
        this.updateVentaID();
        txtDate.setEnabled(false);
        txtDate.setDisabledTextColor(Color.blue);
        txtDate.setText(date());
        txtStock.setEnabled(false);
        txtStock.setDisabledTextColor(Color.black);
        txtPriceProduct.setEnabled(false);
        txtPriceProduct.setDisabledTextColor(Color.black);
        txtProductType.setEnabled(false);
        txtProductType.setDisabledTextColor(Color.black);
        txtIGV.setEnabled(false);
        txtIGV.setDisabledTextColor(Color.black);
        txtTotalPay.setEnabled(false);
        txtTotalPay.setDisabledTextColor(Color.black);
        txtSubTotal.setEnabled(false);
        txtSubTotal.setDisabledTextColor(Color.black);
        txtChange.setEnabled(false);
        txtChange.setDisabledTextColor(Color.black);
        txtTotalProduct.setEnabled(false);
        txtTotalProduct.setDisabledTextColor(Color.black);

        //numVenta = generarNumFactura();
//        txtNumFactura.setText(numVenta);
//        
//        saleNumber = generaNumVenta();
//        txtNumSale.setText(saleNumber);
//
//        numVenta = generaIdVenta();
//        txtUltimoId.setText(numVenta);
//        
//        this.setSize(860, 723);
//
        look();
//        txtIdEmpleado.setVisible(false);
        btnNew.requestFocus();
        btnDelete.setEnabled(false);
        btnClean.setEnabled(false);
//        
//        jpnImporte.setVisible(false);
//        
//        txtUltimoId.setVisible(false);
//        txtCategoria.setVisible(false);
//        
        String title[] = {"ID", "NOMBRE", "TIPO", "CANTIDAD", "PRECIO", "TOTAL"};
        dtmDetail.setColumnIdentifiers(title);
        jtbProductDetail.setModel(dtmDetail);
//        CreatejtbProductDetail();
    }
//    public String generaNumVenta() {
//
//        Sales oVenta = new Sales();
//        try {
//
//            rs = oVenta.obtenerUltimoIdVenta();
//            while (rs.next()) {
//                if (rs.getString(1) != null) {
//                    Scanner s = new Scanner(rs.getString(1));
//                    int c = s.useDelimiter("C").nextInt() + 1;
//
//                    if (c < 10) {
//                        return "C0000" + c;
//                    }
//                    if (c < 100) {
//                        return "C000" + c;
//                    }
//                    if (c < 1000) {
//                        return "C00" + c;
//                    }
//                    if (c < 10000) {
//                        return "C0" + c;
//                    } else {
//                        return "C" + c;
//                    }
//                }
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                rs.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return "C00001";
//
//    }

    private void cleanText() {

        txtSubTotal.setText("0.0");
        txtIGV.setText("0.0");
        txtTotalPay.setText("0.0");
        cmbProductName.setSelectedIndex(0);
        txtStock.setText("");
        txtPriceProduct.setText("");
        txtAmountProd.setText("");
        txtTotalProduct.setText("");

    }

    private void look() {
        btnNew.setEnabled(true);
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        btnPrint.setEnabled(false);
        btnChange.setEnabled(false);
        btnSearchClient.setEnabled(false);

        txtAmountProd.setEnabled(false);
        txtDate.setEnabled(false);
        txtNumSale.setEnabled(false);
        txtDNI.setEnabled(false);

        cmbClient.setSelectedIndex(0);
        cmbProductName.setSelectedIndex(0);
        cmbClient.setEnabled(false);
        cmbProductName.setEnabled(false);
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);
        btnClean.setEnabled(false);//limpiar tabla

//
//        txtTotalVenta.setText("0.0");
//        txtDescuento.setText("0.0");
        txtSubTotal.setText("0.0");
        txtIGV.setText("0.0");
        txtTotalPay.setText("0.0");
//        txtCodigoProducto.setText("");
//        txtCodigoProducto.setText("");
        cmbProductName.setSelectedIndex(0);
        txtStock.setText("");
        txtPriceProduct.setText("");
        txtAmountProd.setText("");
        txtTotalProduct.setText("");
        cmbClient.setSelectedIndex(0);
        txtDNI.setText("");
        txtChange.setText("");
        txtCash.setText("");
//        txtDireccion.setText("");
//        txtRuc.setText("");
//        txtIdCliente.setText("");
//        txtComprobante.setText("");
//        txtCategoria.setText("");
//        txtDescripcionProducto.setText("");
        btnNew.requestFocus();
    }

    private void modify() {
        btnNew.setEnabled(false);
        btnCancel.setEnabled(true);
        btnSearchClient.setEnabled(true);
        btnChange.setEnabled(true);
//        btnComprobante.setEnabled(true);
//
//        txtCodigoProducto.setEnabled(true);
//        txtSerie.setEnabled(true);
        txtAmountProd.setEnabled(true);
        txtDNI.setEnabled(true);
//        txtFecha.setEnabled(true);
//        txtNumero.setEnabled(true);
//        txtNumFactura.setEnabled(true);
//
        cmbClient.setEnabled(true);
        cmbProductName.setEnabled(true);
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(false);
        btnClean.setEnabled(false);//limpiar tabla
//        chkCambiarSerie.setEnabled(true);
//
//        txtCodigoProducto.requestFocus();
    }

    public void setOcultarColumnasJTable(JTable tbl, int columna[]) {
        for (int i = 0; i < columna.length; i++) {
            tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbClient = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnSearchClient = new javax.swing.JButton();
        txtProductType = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cmbProductName = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPriceProduct = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbProductDetail = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTotalProduct = new javax.swing.JTextField();
        txtAmountProd = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        txtSubTotal = new javax.swing.JTextField();
        txtIGV = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTotalPay = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtCash = new javax.swing.JTextField();
        txtChange = new javax.swing.JTextField();
        btnChange = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClean = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        txtNumSale = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        jPanel1.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 0));
        jLabel2.setText("FACTURACION");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(441, 441, 441)
                .addComponent(jLabel2)
                .addGap(419, 419, 419))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DEL CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 204, 0))); // NOI18N
        jPanel2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 0));
        jLabel1.setText("Cliente:");

        cmbClient.setMaximumRowCount(8);
        cmbClient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione cliente", "Item 2", "Item 3", "Item 4", "dsefw", "dad", "fsfs", "fsfs", "ffs", "ffa", "fafa", "fafa", "affa", "af" }));
        cmbClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClientActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 204, 0));
        jLabel3.setText("DNI:");

        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });
        txtDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNIKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 204, 0));
        jLabel4.setText("Tipo:");

        btnSearchClient.setText("Buscar");
        btnSearchClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchClientActionPerformed(evt);
            }
        });

        txtProductType.setText("Producto");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(7, 7, 7)
                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(btnSearchClient, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(txtProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchClient)
                    .addComponent(jLabel4)
                    .addComponent(txtProductType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUCTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 204, 0))); // NOI18N
        jPanel3.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 204, 0));
        jLabel5.setText("Producto:");

        cmbProductName.setMaximumRowCount(8);
        cmbProductName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione producto", "Item 2", "Item 3", "Item 4" }));
        cmbProductName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProductNameActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 204, 0));
        jLabel6.setText("Stock:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 204, 0));
        jLabel7.setText("Precio:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPriceProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtPriceProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jtbProductDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jtbProductDetail.setRowHeight(25);
        jScrollPane1.setViewportView(jtbProductDetail);
        if (jtbProductDetail.getColumnModel().getColumnCount() > 0) {
            jtbProductDetail.getColumnModel().getColumn(0).setMaxWidth(45);
        }

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DETALLES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 204, 0))); // NOI18N
        jPanel5.setOpaque(false);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 204, 0));
        jLabel10.setText("Cantidad:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 204, 0));
        jLabel11.setText("Total:");

        txtTotalProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalProductActionPerformed(evt);
            }
        });

        txtAmountProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAmountProdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAmountProdKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAmountProd, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(txtTotalProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAmountProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setOpaque(false);

        txtSubTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubTotalActionPerformed(evt);
            }
        });

        txtIGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIGVActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 204, 0));
        jLabel12.setText("SUB TOTAL");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 204, 0));
        jLabel13.setText("I.G.V");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 204, 0));
        jLabel14.setText("TOTAL A PAGAR");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel14)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(txtTotalPay, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setOpaque(false);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 204, 0));
        jLabel15.setText("EFECTIVO");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 204, 0));
        jLabel16.setText("CAMBIO");

        txtCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCashKeyTyped(evt);
            }
        });

        txtChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChangeActionPerformed(evt);
            }
        });

        btnChange.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnChange.setForeground(new java.awt.Color(0, 0, 0));
        btnChange.setText("CAMBIO");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCash, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChange, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnChange)
                .addGap(13, 13, 13))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnChange)
                .addGap(24, 24, 24))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OPCIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 204, 0))); // NOI18N
        jPanel8.setOpaque(false);

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(0, 0, 0));
        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(0, 0, 0));
        btnPrint.setText("Imprimir");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnNew.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNew.setForeground(new java.awt.Color(0, 0, 0));
        btnNew.setText("Nuevo");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(0, 0, 0));
        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrint, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnNew)
                        .addComponent(btnSave)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnNew)
                .addGap(31, 31, 31)
                .addComponent(btnSave)
                .addGap(31, 31, 31)
                .addComponent(btnPrint)
                .addGap(32, 32, 32)
                .addComponent(btnCancel)
                .addGap(18, 18, 18))
        );

        jPanel9.setOpaque(false);

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 0, 0));
        btnAdd.setText("Agregar");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Eliminar");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClean.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnClean.setForeground(new java.awt.Color(0, 0, 0));
        btnClean.setText("Limpiar");
        btnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnAdd)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClean)
                .addGap(19, 19, 19))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnClean)
                    .addComponent(btnAdd))
                .addGap(22, 22, 22))
        );

        jPanel10.setOpaque(false);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 204, 0));
        jLabel19.setText("N°");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 204, 0));
        jLabel18.setText("Venta:");

        txtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 204, 0));
        jLabel17.setText("Fecha:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel17)
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel18)
                .addGap(47, 47, 47))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(txtNumSale))
                .addGap(24, 24, 24))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(20, 20, 20)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents
//    public static String currentDate() {
//        Date fecha = new Date();
//        SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-yyy");
//        return formatofecha.format(fecha);
//
//    }

    public static String date() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyy-MM-dd");
        return formatofecha.format(fecha);
    }

    private void CalculateTotal() {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
        double precio_prod = 0, cant_prod = 0, total_prod = 0;
        precio_prod = Double.parseDouble(txtPriceProduct.getText());
        cant_prod = Double.parseDouble(txtAmountProd.getText());
        total_prod = precio_prod * cant_prod;
        txtTotalProduct.setText(String.valueOf(formateador.format(total_prod)));
    }

    public int recorrer(int id) {
        int row = 0, value = -1;

        row = jtbProductDetail.getRowCount();

        for (int f = 0; f < row; f++) {
            if (Integer.parseInt(String.valueOf(dtmDetail.getValueAt(f, 0))) == id) {

                value = f;
                break;

            } else {
                value = -1;
            }
        }
        return value;
    }

    private void addData(int ID, String name, String type, int amount, double price, double total) {

        int p = recorrer(ID);
        double n_amount, n_total;
        if (p > -1) {

            n_amount = Double.parseDouble(String.valueOf(jtbProductDetail.getModel().getValueAt(p, 3))) + amount;
            jtbProductDetail.setValueAt(n_amount, p, 3);

            n_total = Double.parseDouble(String.valueOf(jtbProductDetail.getModel().getValueAt(p, 3))) * Double.parseDouble(String.valueOf(jtbProductDetail.getModel().getValueAt(p, 4)));
            jtbProductDetail.setValueAt(n_total, p, 5);

        } else {
            String Data[] = {String.valueOf(ID), name, type, String.valueOf(amount), String.valueOf(price), String.valueOf(total)};
            dtmDetail.addRow(Data);
        }
        jtbProductDetail.setModel(dtmDetail);
    }

    private boolean validateDouble(String valor) {
        try {
            double num = Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

   private void CalculateSubTotal() {
        double subtotal = 0;
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
        subtotal = Double.parseDouble(txtTotalPay.getText()) / 1.18;
        txtSubTotal.setText(String.valueOf(formateador.format(subtotal)));
    }

    private void CalculateIGV() {
        double igv = 0;
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
        igv = Double.parseDouble(txtSubTotal.getText()) * 0.18;
        txtIGV.setText(String.valueOf(formateador.format(igv)));
    }

    private void CalculateTotalPay() {
        int fila = 0;
        double TotalPay = 0;
        fila = dtmDetail.getRowCount();
        for (int f = 0; f < fila; f++) {
            TotalPay += Double.parseDouble(String.valueOf(jtbProductDetail.getModel().getValueAt(f, 5)));
        }
        txtTotalPay.setText(String.valueOf(TotalPay));
    }

    private void CleanTable() {
        try {
            int rows = jtbProductDetail.getRowCount();
            for (int i = 0; rows > i; i++) {
                dtmDetail.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIActionPerformed

    private void txtTotalProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalProductActionPerformed

    private void txtChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChangeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChangeActionPerformed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        // TODO add your handling code here:
        if (!txtCash.getText().isEmpty()) {
            //validamos que el usuario no ingrese otros caracteres no numericos 
            boolean validation = validateDouble(txtCash.getText());
            if (validation == true) {
                //validar que el efectivo sea mayor a cero
                double efc = Double.parseDouble(txtCash.getText().trim());
                double top = Double.parseDouble(txtTotalPay.getText().trim());

                if (efc < top) {
                    JOptionPane.showMessageDialog(null, "El Dinero en efectivo no es suficiente");
                } else {
                    double change = (efc - top);
                    double chang = (double) Math.round(change * 100d) / 100;
                    String chan = String.valueOf(chang);
                    txtChange.setText(chan);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No de admiten caracteres no numericos");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese dinero en efectivo para calcular cambio");
        }
    }//GEN-LAST:event_btnChangeActionPerformed

    private void txtIGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIGVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIGVActionPerformed

    private void txtSubTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubTotalActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        look();
        CleanTable();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanActionPerformed
        // TODO add your handling code here:
        CleanTable();
        txtSubTotal.setText("0.0");
        txtIGV.setText("0.0");
        txtTotalPay.setText("0.0");
    }//GEN-LAST:event_btnCleanActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        action = "Nuevo";
        modify();
        cleanText();
        CleanTable();
    }//GEN-LAST:event_btnNewActionPerformed

    private void txtAmountProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountProdKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtAmountProdKeyTyped

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        Sales sl = new Sales();
        int stock, cant;
        String Tipo = txtProductType.getText();
        if (Tipo.equals("Producto")) {
            if (txtAmountProd.getText().isEmpty() || cmbClient.getSelectedItem().equals("Seleccione cliente:")) {
                JOptionPane.showMessageDialog(this, "Por favor agregue un cliente y un artículo");
                cmbProductName.requestFocus();
            } else {
                if (!txtAmountProd.getText().equals("")) {
                    if (txtAmountProd.getText().equals("")) {
                        txtAmountProd.setText("0");
                        cant = Integer.parseInt(txtAmountProd.getText());
                    } else {
                        cant = Integer.parseInt(txtAmountProd.getText());
                    }

                    if (cant > 0) {
                        stock = Integer.parseInt(txtStock.getText());
                        cant = Integer.parseInt(txtAmountProd.getText());
                        if (cant <= stock) {
                            String nombreProducto = cmbProductName.getSelectedItem().toString();
                            int productId = sl.obtenerIdProducto(nombreProducto);
                            String d2 = cmbProductName.getSelectedItem().toString();
                            String d3 = txtProductType.getText();
                            int d4 = Integer.parseInt(txtAmountProd.getText());
                            double d5 = Double.parseDouble(txtPriceProduct.getText());
                            double d6 = Double.parseDouble(txtTotalProduct.getText());
                            addData(productId, d2, d3, d4, d5, d6);   
                            CalculateTotalPay();
                            CalculateSubTotal();
                            CalculateIGV();

                            txtAmountProd.setText("");
                            txtTotalProduct.setText("");
                            txtCash.setText("");
                            txtChange.setText("");
//                
                        } else {
                            JOptionPane.showMessageDialog(null, "Stock Insuficiente");
                            txtAmountProd.setText("");
                            txtTotalProduct.setText("");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese Cantidad mayor a 0");
                        txtAmountProd.setText("");
                        txtTotalProduct.setText("");
                        txtAmountProd.requestFocus();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese cantidad");
                    txtAmountProd.requestFocus();
                }

                btnSave.setEnabled(true);
                btnDelete.setEnabled(true);
                btnClean.setEnabled(true);

            }
        }


    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if ( txtNumSale.getText().isEmpty() || txtStock.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor rellene todos los campos");
            //--------------------------------------------------
        } else {
        
        int result = JOptionPane.showConfirmDialog(this, "¿Desea Generar la venta?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
        Sales ventas=new Sales();
       
        String Fecha = txtDate.getText();
        String SubTotal = txtSubTotal.getText();
        String Igv = txtIGV.getText();
        String Total = txtTotalPay.getText();
        int IdCliente = cmbClient.getSelectedIndex();
//        String IdEmpleado = txtDescripcion.getText(); //descripcion
        //+++++++++++++++++++++++
        if (num == 0) {
            ventas.GuardarVentas( Fecha, SubTotal, Igv, Total, IdCliente);
            guardarDetalle();
        }
//            numVenta=generaNumVenta();
//            txtNumero.setText(numVenta);
            
        
        }
        if (result == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Venta Anulada!");
        }
        
        }
        
    btnPrint.setEnabled(true);
    btnNew.setEnabled(true);
    btnCancel.setEnabled(false);
     


    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtDNIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtDNIKeyTyped

    private void txtCashKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();//Llamamos el evento
        if (Character.isLetter(c)) { //Comparamos si ingresamos un digito
            evt.consume(); //evitar que se capture el digito
            JOptionPane.showMessageDialog(this, "Solo se permite números en este campo", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtCashKeyTyped

    private void btnSearchClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchClientActionPerformed
        // TODO add your handling code here:
        String DniBuscar = txtDNI.getText().trim();
        Connection cn = ConnectionDB.conectar();
        String sql = "SELECT NombreApellido FROM cliente WHERE Documento = '" + DniBuscar + "'";
        try (Connection conet = ConnectionDB.conectar(); Statement st = conet.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                cmbClient.setSelectedItem(rs.getString("NombreApellido"));
            } else {
                cmbClient.setSelectedItem("Seleccione cliente:");
                JOptionPane.showMessageDialog(null, "¡DNI de cliente incorrecta o no encontrada!");
            }
            txtDNI.setText("");
            cn.close();
        } catch (SQLException e) {
            System.out.println("¡Error al buscar cliente!, " + e);
        }
    }//GEN-LAST:event_btnSearchClientActionPerformed

    private void cmbProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductNameActionPerformed
        // TODO add your handling code here:
        updateProductDetails();
    }//GEN-LAST:event_cmbProductNameActionPerformed

    private void cmbClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClientActionPerformed
        // TODO add your handling code here:
        updateClientDetails();
    }//GEN-LAST:event_cmbClientActionPerformed

    private void txtAmountProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountProdKeyReleased
        // TODO add your handling code here:
        CalculateTotal();
    }//GEN-LAST:event_txtAmountProdKeyReleased

    private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        this.ObtenerIdCliente();
        VentaPDF pdf = new VentaPDF();
        pdf.DatosCliente(idCliente);
        pdf.generarFacturaPDF();
        JOptionPane.showMessageDialog(null, "¡Factura creada!");
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int fila = jtbProductDetail.getSelectedRow();
        if (fila == -1) {
        JOptionPane.showMessageDialog(null, "Por favor seleccione una fila", "ERROR", JOptionPane.ERROR_MESSAGE);
    } else{
        if (fila > 0) {
            dtmDetail.removeRow(fila);
            CalculateSubTotal();
            CalculateIGV();
        } else if (fila == 0) {
            dtmDetail.removeRow(fila);

            txtSubTotal.setText("0.0");
            txtIGV.setText("0.0");
            txtTotalPay.setText("0.0");
            CalculateTotalPay();
            CalculateSubTotal();
            CalculateIGV();
        }
        }
        CalculateTotalPay();
        CalculateSubTotal();
        CalculateIGV();
                                          
    }//GEN-LAST:event_btnDeleteActionPerformed
    /*
    Metodo para cargar los clientes en el jComboBox
     */
    private void CargarComboClientes() {
        Connection cn = ConnectionDB.conectar();
        String sql = "SELECT * FROM cliente";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            cmbClient.removeAllItems();
            cmbClient.addItem("Seleccione cliente:");
            while (rs.next()) {
                cmbClient.addItem(rs.getString("NombreApellido"));
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("¡Error al cargar clientes, !" + e);
        }
    }

    /*
    Metodo para cargar los productos en el jComboBox
     */
    private void CargarComboProductos() {
        Connection cn = ConnectionDB.conectar();
        String sql = "SELECT * FROM productos";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            cmbProductName.removeAllItems();
            cmbProductName.addItem("Seleccione producto:");
            while (rs.next()) {
                cmbProductName.addItem(rs.getString("NombreProducto"));
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("¡Error al cargar productos, !" + e);
        }
    }

    private void updateProductDetails() {
        String selectedProduct = (String) cmbProductName.getSelectedItem();

        try (Connection conn = ConnectionDB.conectar()) {
            String sql = "SELECT Stock, Precio FROM productos WHERE NombreProducto = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, selectedProduct);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt("Stock");
                double price = rs.getDouble("Precio");
                txtStock.setText(String.valueOf(stock));
                txtPriceProduct.setText(String.valueOf(price));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateClientDetails() {
        String selectedClient = (String) cmbClient.getSelectedItem();

        try (Connection conn = ConnectionDB.conectar()) {
            String sql = "SELECT Documento FROM cliente WHERE NombreApellido = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, selectedClient);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int DNI = rs.getInt("Documento");
                txtDNI.setText(String.valueOf(DNI));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void updateVentaID() {
    try (Connection conn = ConnectionDB.conectar()) {
        String sql = "SELECT MAX(Id_Ventas) AS ultimo_id FROM ventas";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int ultimoID = rs.getInt("ultimo_id");
            int nuevoID = ultimoID + 1;
            txtNumSale.setText(String.valueOf(nuevoID));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    }
    private void lastSaleID() {
    try (Connection conn = ConnectionDB.conectar()) {
        String sql = "SELECT MAX(Id_ventas) AS ultimo_id FROM ventas";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            saleId = rs.getInt("ultimo_id");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    void guardarDetalle() {
    lastSaleID();
    Sales DetalleVentas = new Sales();
    Connection cn = null;

    try {
        cn = ConnectionDB.conectar();
        cn.setAutoCommit(false);

        int fila = jtbProductDetail.getRowCount();
        for (int f = 0; f < fila; f++) {
            String IdVentas = String.valueOf(saleId);
            String IdProducto = String.valueOf(jtbProductDetail.getModel().getValueAt(f, 0));   
            String Cantidad = String.valueOf(jtbProductDetail.getModel().getValueAt(f, 3));
            String precio = String.valueOf(jtbProductDetail.getModel().getValueAt(f, 4));
            String Tipo = String.valueOf(jtbProductDetail.getModel().getValueAt(f, 2));

            if (num == 0) {
                DetalleVentas.GuardarDetalleVentas(IdVentas, IdProducto, Cantidad, precio, Tipo);
                RestarStockProductos(cn, Integer.parseInt(IdProducto), Integer.parseInt(Cantidad));
            }
        }
        cn.commit();
    } catch (SQLException e) {
        if (cn != null) {
            try {
                cn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        e.printStackTrace();
    } finally {
        if (cn != null) {
            try {
                cn.setAutoCommit(true);
                cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
private void ObtenerIdCliente() {
        try {
            String sql = "SELECT * FROM cliente where NombreApellido = '" + this.cmbClient.getSelectedItem() + "'";
            Connection cn = ConnectionDB.conectar();
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                idCliente = rs.getInt("Id_Cliente");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener id del cliente, " + e);
        }
    }

private void RestarStockProductos(Connection cn, int idProducto, int cantidad) throws SQLException {
    int cantidadProductosBaseDeDatos = 0;
    String sqlSelect = "SELECT Stock FROM productos WHERE Id_Producto = ?";
    try (PreparedStatement st = cn.prepareStatement(sqlSelect)) {
        st.setInt(1, idProducto);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            cantidadProductosBaseDeDatos = rs.getInt("Stock");
        }
    }

    String sqlUpdate = "UPDATE productos SET Stock=? WHERE Id_Producto = ?";
    try (PreparedStatement consulta = cn.prepareStatement(sqlUpdate)) {
        int cantidadNueva = cantidadProductosBaseDeDatos - cantidad;
        consulta.setInt(1, cantidadNueva);
        consulta.setInt(2, idProducto);
        consulta.executeUpdate();
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearchClient;
    private javax.swing.JComboBox<String> cmbClient;
    private javax.swing.JComboBox<String> cmbProductName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jtbProductDetail;
    private javax.swing.JTextField txtAmountProd;
    private javax.swing.JTextField txtCash;
    private javax.swing.JTextField txtChange;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtIGV;
    private javax.swing.JTextField txtNumSale;
    private javax.swing.JTextField txtPriceProduct;
    private javax.swing.JTextField txtProductType;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtSubTotal;
    public static javax.swing.JTextField txtTotalPay;
    private javax.swing.JTextField txtTotalProduct;
    // End of variables declaration//GEN-END:variables
}
