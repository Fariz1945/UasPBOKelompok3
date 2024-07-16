/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gallery;

import javax.swing.JOptionPane;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 62851
 */
public class FDataUser extends javax.swing.JFrame {

    /**
     * Creates new form FDataUser
     */
    public FDataUser() {
        initComponents();
        load_data();
    }
    
    //Load data dari database tb_gambar
    private void load_data(){
        Connection kon=koneksi.koneksiDb();
        Object header[]={"ID","NAMA","NIK", "ALAMAT"};
        DefaultTableModel data = new DefaultTableModel(null, header) ;
        TableGambar.setModel(data);
        String sql_data="SELECT * FROM tb_ktp";
        try{
            Statement st=kon.createStatement();
            ResultSet rs=st.executeQuery(sql_data);
            while(rs.next()){
                String d1=rs.getString(1);
                String d2=rs.getString(2);
                String d3=rs.getString(3);
                String d4=rs.getString(4);
                
                data.addRow(new Object[]{d1, d2, d3,  d4});
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void insertData(String nama, String nik, String alamat) {
        String query = String.format("INSERT INTO tb_ktp (nama_ktp, nik_ktp, alamat_ktp) VALUES ('%s', '%s', '%s')", nama, nik, alamat);
        try (Connection conn = koneksi.koneksiDb();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            // Get generated keys (ID)
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan dengan ID: " + id);
            }
            load_data(); // Refresh data
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }
     
    private void updateData(int id, String nama, String nik, String alamat) {
        String query = String.format("UPDATE tb_ktp SET nama_ktp = '%s', nik_ktp = '%s', alamat_ktp = '%s' WHERE id_ktp = %d", nama, nik, alamat, id);
        try (Connection conn = koneksi.koneksiDb();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui");
            load_data(); // Refresh data
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }
    
    private void deleteData(int id) {
        String query = String.format("DELETE FROM tb_ktp WHERE id_ktp = %d", id);
        try (Connection conn = koneksi.koneksiDb();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
            resetKTPTable();
            load_data(); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }
    
    private void resetKTPTable() {
    String createTempTable = "CREATE TABLE temp_tb_ktp AS SELECT * FROM tb_ktp;";
    String truncateTable = "TRUNCATE TABLE tb_ktp;";
    String insertBack = "INSERT INTO tb_ktp (nama_ktp, nik_ktp, alamat_ktp) SELECT nama_ktp, nik_ktp, alamat_ktp FROM temp_tb_ktp;";
    String dropTempTable = "DROP TABLE temp_tb_ktp;";

    try (Connection conn = koneksi.koneksiDb();
         Statement stmt = conn.createStatement()) {
        stmt.executeUpdate(createTempTable);
        stmt.executeUpdate(truncateTable);
        stmt.executeUpdate(insertBack);
        stmt.executeUpdate(dropTempTable);
        JOptionPane.showMessageDialog(this, "Auto-increment ID has been reset.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
    }
    }
    
    private void viewDetailData(int id) {
        String query = String.format("SELECT * FROM tb_ktp WHERE id_ktp = %d", id);
        try (Connection conn = koneksi.koneksiDb();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                String idktp = rs.getString("id_ktp");
                String nama = rs.getString("nama_ktp");
                String nik = rs.getString("nik_ktp");
                String alamat = rs.getString("alamat_ktp");
                
                KTP ktpForm = new KTP(String.valueOf(id), nama, nik, alamat);
                ktpForm.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        id_ktp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableGambar = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        nama_ktp = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        alamat_ktp = new javax.swing.JTextField();
        nik_ktp = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setText("KELOLA DATA PENGGUNA");

        jButton1.setText("KELUAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("ID KTP");

        id_ktp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_ktpActionPerformed(evt);
            }
        });

        jLabel3.setText("NIK");

        TableGambar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TableGambar);

        jLabel5.setText("Nama ");

        nama_ktp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_ktpActionPerformed(evt);
            }
        });

        jButton2.setText("INPUT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("EDIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("DELETE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setText("Alamat");

        jButton5.setText("DETAIL");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(id_ktp)
                                    .addComponent(nama_ktp, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(alamat_ktp)
                                    .addComponent(nik_ktp)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5)
                                    .addComponent(jButton4))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(id_ktp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(nama_ktp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nik_ktp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(alamat_ktp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(27, 27, 27)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
                int keluar=JOptionPane.showOptionDialog(this,
                "Keluar dari Kelola data pengguna?",
                "Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,null,null);
        if (keluar==JOptionPane.YES_NO_OPTION)
        {
            new FAdmin().show();
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void id_ktpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_ktpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_ktpActionPerformed

    private void nama_ktpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_ktpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nama_ktpActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String nama = nama_ktp.getText();
        String nik = nik_ktp.getText();
        String alamat = alamat_ktp.getText();
        insertData(nama, nik, alamat);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int selectedRow = TableGambar.getSelectedRow();
            if (selectedRow >= 0) {
                int id = Integer.parseInt(TableGambar.getValueAt(selectedRow, 0).toString());
                String nama = nama_ktp.getText();
                String nik = nik_ktp.getText();
                String alamat = alamat_ktp.getText();
                updateData(id, nama, nik, alamat);
            } else {
                JOptionPane.showMessageDialog(this, "Pilih data yang akan diupdate");
            }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int selectedRow = TableGambar.getSelectedRow();
            if (selectedRow >= 0) {
                int id = Integer.parseInt(TableGambar.getValueAt(selectedRow, 0).toString());
                deleteData(id);
            } else {
                JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus");
            }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int selectedRow = TableGambar.getSelectedRow();
        if (selectedRow != -1) {
            int id = Integer.parseInt(TableGambar.getValueAt(selectedRow, 0).toString());
            
            viewDetailData(id);
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(FDataUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FDataUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FDataUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FDataUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FDataUser().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableGambar;
    private javax.swing.JTextField alamat_ktp;
    private javax.swing.JTextField id_ktp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nama_ktp;
    private javax.swing.JTextField nik_ktp;
    // End of variables declaration//GEN-END:variables

}
