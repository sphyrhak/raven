package com.raven.form;

import com.raven.component.Form;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Sale_Form2 extends Form {

    public String status = "EMITIDO";

//private Connection connection = new ConexionBD().connect();
    String total;
    String code;
    String action;
    String saleNumber, documentType;

    int records;
    String ids[] = new String[50];
    int num = 0;

    static int counter;
    public String employeeId, employeeName;
    int saleId, newSaleId;
    String saleIdPrint;

    public String productCode;
//static Connection conn = null;

//static ResultSet rs = null;
    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtmDetail = new DefaultTableModel();

    String criterion, search;

    public Sale_Form2() {
        initComponents();
//        txtDates.setDisabledTextColor(Color.blue);
//        txtDates.setText(date());
//        txtDates.setVisible(true);//falta esta variable

        txtDate.setEnabled(false);
        txtDate.setDisabledTextColor(Color.blue);
        txtDate.setText(currentDate());
        //numVenta = generarNumFactura();
//        txtNumFactura.setText(numVenta);
//        
//        numVenta = generaNumVenta();
//        txtNumero.setText(numVenta);
//
//        numVenta = generaIdVenta();
//        txtUltimoId.setText(numVenta);
//        
//        this.setSize(860, 723);
//
//        mirar();
//        txtIdEmpleado.setVisible(false);
//        btnNuevo.requestFocus();
//        btnEliminarProducto.setEnabled(false);
//        btnLimpiarTabla.setEnabled(false);
//        
//        jpnImporte.setVisible(false);
//        
//        txtUltimoId.setVisible(false);
//        txtCategoria.setVisible(false);
//        
        String title[] = {"ID", "NOMBRE", "TIPO", "CANTIDAD", "PRECIO", "TOTAL"};
        dtmDetail.setColumnIdentifiers(title);
        jtbProductDetail.setModel(dtmDetail);
        //CrearTablaProductDetail();
    }

    private void cleanText() {

        txtSubTotal.setText("0.0");
        txtIGV.setText("0.0");
        txtTotalPay.setText("0.0");
//        txtCodigoProducto.setText("");
//        txtCodigoProducto.setText("");
        cmbProductName.setSelectedIndex(0);
        cmbMemName.setSelectedIndex(0);
        cmbProductType.setSelectedIndex(0);
        txtPriceMem.setText("");
        txtStock.setText("");
        txtPriceProduct.setText("");
        txtPriceMem.setText("");
        txtAmountProd.setText("");
        txtAmountMem.setText("");
        txtTotalProduct.setText("");
//        txtCodigoProducto.requestFocus();
    }

    private void look() {
        btnNew.setEnabled(true);
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        btnExit.setEnabled(true);
        btnPrint.setEnabled(false);
//        btnComprobante.setEnabled(false);no tengo
//        btnImporte.setEnabled(false);no tengo
//
//        txtCodigoProducto.setEnabled(false); en duda
//        txtSerie.setEnabled(false);no tengo
        txtAmountProd.setEnabled(false);
        txtDate.setEnabled(false);
        txtNumSale.setEnabled(false);
//        txtNumFactura.setEnabled(false);no tengo
//        
//        
        cmbClient.setSelectedIndex(0);
        cmbProductType.setSelectedIndex(0);
        cmbProductName.setSelectedIndex(0);
        cmbMemName.setSelectedIndex(0);
//        btnBuscarProducto.setEnabled(false);
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);
        btnClean.setEnabled(false);//limpiar tabla
//        chkCambiarSerie.setEnabled(false);
//        chkCambiarSerie.setSelected(false);
//
//        txtTotalVenta.setText("0.0");
//        txtDescuento.setText("0.0");
        txtSubTotal.setText("0.0");
        txtIGV.setText("0.0");
        txtTotalPay.setText("0.0");
//        txtCodigoProducto.setText("");
//        txtCodigoProducto.setText("");
        cmbProductName.setSelectedIndex(0);
        cmbMemName.setSelectedIndex(0);
        cmbProductType.setSelectedIndex(0);
        txtStock.setText("");
        txtPriceProduct.setText("");
        txtPriceMem.setText("");
        txtAmountProd.setText("");
        txtAmountMem.setText("");//siempre se compra una membresia
        txtTotalProduct.setText("");
        cmbClient.setSelectedIndex(0);
        txtDNI.setText("");
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
        btnExit.setEnabled(false);
//        btnComprobante.setEnabled(true);
//
//        txtCodigoProducto.setEnabled(true);
//        txtSerie.setEnabled(true);
        txtAmountProd.setEnabled(true);
//        txtFecha.setEnabled(true);
//        txtNumero.setEnabled(true);
//        txtNumFactura.setEnabled(true);
//
        cmbClient.setEnabled(true);
        cmbProductName.setEnabled(true);
        cmbMemName.setEnabled(true);
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(false);
        btnClean.setEnabled(false);//limpiar tabla
//        chkCambiarSerie.setEnabled(true);
//
//        txtCodigoProducto.requestFocus();
    }

    private void setOcultarColumnasJTable(JTable tbl, int columna[]) {
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
        cmbProductType = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cmbProductName = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPriceProduct = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cmbMemName = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtPriceMem = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtAmountMem = new javax.swing.JTextField();
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
        btnExit = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        txtNumSale = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        jPanel1.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("FACTURACION");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(358, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(358, 358, 358))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DEL CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel2.setOpaque(false);

        jLabel1.setText("Cliente:");

        cmbClient.setMaximumRowCount(8);
        cmbClient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione cliente", "Item 2", "Item 3", "Item 4", "dsefw", "dad", "fsfs", "fsfs", "ffs", "ffa", "fafa", "fafa", "affa", "af" }));

        jLabel3.setText("DNI:");

        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });

        jLabel4.setText("Tipo:");

        cmbProductType.setMaximumRowCount(8);
        cmbProductType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione tipo de producto", "Producto", "Membresia" }));
        cmbProductType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProductTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addGap(10, 10, 10)
                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(cmbProductType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cmbClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUCTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel3.setOpaque(false);

        jLabel5.setText("Producto:");

        cmbProductName.setMaximumRowCount(8);
        cmbProductName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione producto", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Stock:");

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
                .addGap(85, 85, 85)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MEMBRESIA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel4.setOpaque(false);

        jLabel8.setText("Membresia:");

        cmbMemName.setMaximumRowCount(5);
        cmbMemName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione plan", "Mensual", "Trimestral", "Semestral" }));

        jLabel9.setText("Precio:");

        jLabel20.setText("Cantidad");

        txtAmountMem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmountMemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addGap(10, 10, 10)
                .addComponent(cmbMemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jLabel20)
                .addGap(10, 10, 10)
                .addComponent(txtAmountMem, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(10, 10, 10)
                .addComponent(txtPriceMem, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbMemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtPriceMem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txtAmountMem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jScrollPane1.setViewportView(jtbProductDetail);
        if (jtbProductDetail.getColumnModel().getColumnCount() > 0) {
            jtbProductDetail.getColumnModel().getColumn(0).setMaxWidth(45);
        }

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DETALLES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel5.setOpaque(false);

        jLabel10.setText("Cantidad:");

        jLabel11.setText("Total:");

        txtTotalProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalProductActionPerformed(evt);
            }
        });

        txtAmountProd.addKeyListener(new java.awt.event.KeyAdapter() {
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
                .addContainerGap(30, Short.MAX_VALUE))
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

        jLabel12.setText("SUB TOTAL");

        jLabel13.setText("I.G.V");

        jLabel14.setText("TOTAL A PAGAR");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(txtTotalPay, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
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

        jLabel15.setText("EFECTIVO");

        jLabel16.setText("CAMBIO");

        txtChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChangeActionPerformed(evt);
            }
        });

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
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtChange, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(txtCash))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(btnChange)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChange)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OPCIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel8.setOpaque(false);

        btnSave.setText("Guardar");

        btnPrint.setText("Imprimir");

        btnNew.setText("Nuevo");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

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
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnSave)
                        .addComponent(btnPrint))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(btnNew)
                        .addGap(4, 4, 4))
                    .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnSave)
                .addGap(32, 32, 32)
                .addComponent(btnPrint)
                .addGap(32, 32, 32)
                .addComponent(btnCancel)
                .addGap(18, 18, 18))
        );

        jPanel9.setOpaque(false);

        btnAdd.setText("Agregar");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Eliminar");

        btnClean.setText("Limpiar");
        btnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanActionPerformed(evt);
            }
        });

        btnExit.setText("Salir");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnAdd)
                .addGap(25, 25, 25)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(btnClean)
                .addGap(25, 25, 25)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(btnClean)
                    .addComponent(btnExit))
                .addGap(19, 19, 19))
        );

        jPanel10.setOpaque(false);

        jLabel19.setText("N°");

        jLabel18.setText("Venta:");

        jLabel17.setText("Fecha:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(47, 47, 47))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(txtNumSale))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(20, 20, 20)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents
    public static String currentDate() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-yyy");
        return formatofecha.format(fecha);

    }

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

    private void addData(int ID, String name, String type, double amount, double price, double total) {

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
//    private void CalcularValorVenta() {
//        int row = 0;
//        double saleValue = 0;
//        row = dtmDetail.getRowCount();
//        for (int f = 0; f < row; f++) {
//            saleValue += Double.parseDouble(String.valueOf(jtbProductDetail.getModel().getValueAt(f, 5)));
//        }
//        txtTotalVenta.setText(String.valueOf(valorVenta));
//    }

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
        double totalPay = 0;
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
        totalPay = Double.parseDouble(txtSubTotal.getText()) + Double.parseDouble(txtIGV.getText());
        txtTotalPay.setText(String.valueOf(formateador.format(totalPay)));
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

    private void cmbProductTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProductTypeActionPerformed

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

    private void txtAmountMemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountMemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAmountMemActionPerformed

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
        double stock, cant;
        String selectedItem = cmbProductType.getSelectedItem().toString();
        if (selectedItem.equals("Producto")) {
            if (txtStock.getText().isEmpty() || cmbClient.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Por favor agregue un cliente y un artículo");
                cmbProductName.requestFocus();
            } else {
                if (!txtAmountProd.getText().equals("")) {
                    if (txtAmountProd.getText().equals("")) {
                        txtAmountProd.setText("0");
                        cant = Double.parseDouble(txtAmountProd.getText());
                    } else {
                        cant = Double.parseDouble(txtAmountProd.getText());
                    }

                    if (cant > 0) {
                        stock = Double.parseDouble(txtStock.getText());
                        cant = Double.parseDouble(txtAmountProd.getText());
                        if (cant <= stock) {
//                    int d1 = Integer.parseInt(txtCodigoProducto.getText());
//                    String d2 = txtCodigoProducto.getText();
//                    String d3 = txtNombreProducto.getText();
//                    String d4 = txtDescripcionProducto.getText();
//                    String d5 = txtCategoria.getText();
//                    double d6 = Double.parseDouble(txtCantidadProducto.getText());
//                    double d7 = Double.parseDouble(txtPrecioProducto.getText());
//                    double d8 = Double.parseDouble(txtTotalProducto.getText());
//                    double d9 = Double.parseDouble(txtCosto.getText());
//                    agregardatos(d1, d2, d3, d4, d5, d6, d7,d8, d9);
//
//                    CalcularValor_Venta();
                            CalculateTotalPay();
                            CalculateSubTotal();
                            CalculateIGV();

                            txtAmountProd.setText("");
                            txtTotalProduct.setText("");
//                    txtImporte.setText("");
//                    txtCambio.setText("");
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbClient;
    private javax.swing.JComboBox<String> cmbMemName;
    private javax.swing.JComboBox<String> cmbProductName;
    private javax.swing.JComboBox<String> cmbProductType;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbProductDetail;
    private javax.swing.JTextField txtAmountMem;
    private javax.swing.JTextField txtAmountProd;
    private javax.swing.JTextField txtCash;
    private javax.swing.JTextField txtChange;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtIGV;
    private javax.swing.JTextField txtNumSale;
    private javax.swing.JTextField txtPriceMem;
    private javax.swing.JTextField txtPriceProduct;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotalPay;
    private javax.swing.JTextField txtTotalProduct;
    // End of variables declaration//GEN-END:variables
}
