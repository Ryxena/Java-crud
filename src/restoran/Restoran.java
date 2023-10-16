/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package restoran;

import java.sql.ResultSet;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Restoran extends javax.swing.JFrame {

    JComboBox comboBox;
    String sql;
    int ID;
    private static DefaultTableModel tabelModel;

    /**
     * Creates new form Restoran
     */
    public Restoran() {
        initComponents();
        jenis();
        showData(null);
    }

    private void kosong() {
        if (ID != -1) {
            String query = "DELETE FROM menu WHERE id = ?";
            try (Connection connection = DB.connectdb(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, ID);
                int hasil = preparedStatement.executeUpdate();

                if (hasil > 0) {
                    JOptionPane.showMessageDialog(null, "Baris dengan ID " + ID + " berhasil dihapus dari basis data!");
                } else {
                    JOptionPane.showMessageDialog(null, "Baris dengan ID " + ID + " tidak ditemukan dalam basis data.");
                }

                // Reset idToDelete setelah penghapusan
                ID = -1;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tidak ada baris yang dipilih untuk dihapus.");
        }
    }

    private void tambah() {
        //INSERT INTO `menu` (`id`, `nama`, `harga`, `jenis`) VALUES (NULL, 'Es Teh', '3000', 'minuman');
        String nama = NamaProd.getText();
        double price = Double.parseDouble(harga.getText());
        String jenis = jComboBox1.getSelectedItem().toString();
        String query = "INSERT INTO menu (nama, harga, jenis) VALUES (?, ?, ?)";
        try (Connection connection = DB.connectdb(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nama);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, jenis);

            int hasil = preparedStatement.executeUpdate();
            if (hasil > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan ke Database");
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menambahkan data ke Database");
            }
            NamaProd.setText("");
            harga.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    private void jenis() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Makanan");
        jComboBox1.addItem("Minuman");

    }

    private void update() {
        String nama = NamaProd.getText();
        double price = Double.parseDouble(harga.getText());
        String jenis = jComboBox1.getSelectedItem().toString();

        String query = "UPDATE menu SET nama = ?, harga = ?, jenis = ? WHERE id = ?";
        try (Connection connection = DB.connectdb(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nama);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, jenis);
            preparedStatement.setInt(4, ID);

            int hasil = preparedStatement.executeUpdate();
            if (hasil > 0) {
                JOptionPane.showMessageDialog(null, "Data dengan ID " + ID + " berhasil diperbarui di Database");
            } else {
                JOptionPane.showMessageDialog(null, "Gagal memperbarui data dengan ID " + ID + " di Database");
            }

            // Kosongkan input setelah berhasil memperbarui data
            NamaProd.setText("");
            harga.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    private void showData(String cari) {
        if (cari == null) {
            sql = "select * from menu";
        } else {
            sql = "SELECT * FROM menu WHERE nama LIKE '%" + cari + "%' OR jenis LIKE '%" + cari + "%'";
        }
        String[] kolom = {"ID", "Nama", "Harga", "Jenis", "Image"};
        tabelModel = new DefaultTableModel(null, kolom);
        try (Connection connection = DB.connectdb(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                double harga = resultSet.getDouble("harga");
                String jenis = resultSet.getString("jenis");
                ImageIcon img = new ImageIcon("C:/Users/ASUS/Downloads/este.jpg");
                Object[] baris = {id, nama, harga, jenis, new ImageIcon("C:/Users/ASUS/Downloads/este.jpg")};
                tabelModel.addRow(baris);
            }

            table.setModel(tabelModel);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        NamaProd = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        Tambah = new javax.swing.JButton();
        Edit = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        FInd = new javax.swing.JTextField();
        cari = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        harga = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nama");

        jLabel2.setText("Jenis");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        NamaProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaProdActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "Nama", "Jenis", "Jumlah"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setToolTipText("");
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        Tambah.setText("Tambah");
        Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TambahActionPerformed(evt);
            }
        });

        Edit.setText("Edit");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        FInd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FIndActionPerformed(evt);
            }
        });

        cari.setText("Cari");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        jLabel4.setText("Harga");

        harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Tambah)
                        .addGap(18, 18, 18)
                        .addComponent(Edit)
                        .addGap(18, 18, 18)
                        .addComponent(delete)
                        .addGap(33, 33, 33)
                        .addComponent(FInd, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(NamaProd))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(harga)))
                .addContainerGap(234, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(NamaProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tambah)
                    .addComponent(Edit)
                    .addComponent(delete)
                    .addComponent(FInd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TambahActionPerformed
        // TODO add your handling code here:
        tambah();
        showData(null);
    }//GEN-LAST:event_TambahActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        kosong();
        showData(null);
    }//GEN-LAST:event_deleteActionPerformed

    private void FIndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FIndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FIndActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
        showData(FInd.getText().trim());
    }//GEN-LAST:event_cariActionPerformed

    private void NamaProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaProdActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        int baris = table.getSelectedRow();

        ID = Integer.parseInt(table.getModel().getValueAt(baris, 0).toString());
        NamaProd.setText(table.getModel().getValueAt(baris, 1).toString());
        harga.setText(table.getModel().getValueAt(baris, 2).toString());
        System.out.println(ID);
    }//GEN-LAST:event_tableMouseClicked

    private void hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaActionPerformed

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        // TODO add your handling code here:
        update();
        showData(null);
    }//GEN-LAST:event_EditActionPerformed

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
            java.util.logging.Logger.getLogger(Restoran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Restoran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Restoran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Restoran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Restoran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Edit;
    private javax.swing.JTextField FInd;
    private javax.swing.JTextField NamaProd;
    private javax.swing.JButton Tambah;
    private javax.swing.JButton cari;
    private javax.swing.JButton delete;
    private javax.swing.JTextField harga;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
