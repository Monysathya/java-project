import java.awt.Color;
import java.awt.Font;

public class DoctorDisease extends javax.swing.JDialog {

    private final SubFrame sub;
    
    private static String ID;
    
    private final Color colorForeExited;
    private final Color colorBackExited;
    
    public DoctorDisease(String id, java.awt.Frame parent, boolean modal) {   
        super(parent, modal);
        initComponents();
        
        colorForeExited = this.lblClose.getForeground();
        colorBackExited = this.lblClose.getBackground();
        
        sub = new SubFrame(this.panel_BGInsPlan, this, new Color(0, 160, 174), 1);
        sub.closeButton(lblClose, this, colorBackExited, colorForeExited);
        
        DoctorDisease.ID = id;
        
        SubTable.setTableHeader(tblDoctorDisease, new Color(240, 240, 240), Color.BLACK, new Font("Tahoma", Font.PLAIN, 12));
        
        ShowDataToTable.show("SELECT D.Name\n" +
                                "FROM Disease D INNER JOIN Cure C\n" +
                                "ON D.ID = C.[Disease.ID]\n" +
                                "WHERE C.[Doctor.ID] = '" + ID + "' ", tblDoctorDisease, 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_BGInsPlan = new javax.swing.JPanel();
        lblHealthcarePro = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDoctorDisease = new javax.swing.JTable();
        lblClose = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        lblHealthcarePro.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblHealthcarePro.setText("Disease List");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        tblDoctorDisease.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblDoctorDisease.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Disease's Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoctorDisease.setRowHeight(20);
        jScrollPane2.setViewportView(tblDoctorDisease);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblClose.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 12)); // NOI18N
        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClose.setText("X");
        lblClose.setOpaque(true);

        javax.swing.GroupLayout panel_BGInsPlanLayout = new javax.swing.GroupLayout(panel_BGInsPlan);
        panel_BGInsPlan.setLayout(panel_BGInsPlanLayout);
        panel_BGInsPlanLayout.setHorizontalGroup(
            panel_BGInsPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_BGInsPlanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_BGInsPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_BGInsPlanLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panel_BGInsPlanLayout.createSequentialGroup()
                        .addComponent(lblHealthcarePro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        panel_BGInsPlanLayout.setVerticalGroup(
            panel_BGInsPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_BGInsPlanLayout.createSequentialGroup()
                .addGroup(panel_BGInsPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_BGInsPlanLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblHealthcarePro))
                    .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_BGInsPlan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_BGInsPlan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HPInsurance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HPInsurance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HPInsurance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HPInsurance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            DoctorDisease dialog = new DoctorDisease(ID, new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblHealthcarePro;
    private javax.swing.JPanel panel_BGInsPlan;
    public javax.swing.JTable tblDoctorDisease;
    // End of variables declaration//GEN-END:variables
}
