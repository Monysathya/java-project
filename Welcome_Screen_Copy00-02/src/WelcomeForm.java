import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.sql.*;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Sathya
 */
public class WelcomeForm extends javax.swing.JFrame {
    /**
     * Creates new form WelcomeForm
     */
    
    ShowDataToTable sd = null;
    
    DefaultTableModel model;
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    MouseListener[] ml;
    
    FrmLogin frmLogin  = new FrmLogin();
     
    public WelcomeForm() {        
        initComponents();
        
        // Maximize jframe when start
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        indDashboard.setOpaque(true);
        setColorPanel(panel_dashboard);
                
        ml = panel_update.getMouseListeners();  // get event mousePressed from panel_update
        
        frmLogin.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                WelcomeForm.this.setVisible(true);
                
                if (frmLogin.isLoggedin()) {
                    changeLoginText();
            
                    if (frmLogin.getAccountType().equals("Admin")) {
                        userLoginAdmin();
                    }   
                    
                    if (frmLogin.getAccountType().equals("Insurance")) {
                        userLoginInsurance();
                    }
                    
                    if (frmLogin.getAccountType().equals("Healthcare")) {
                        userLoginHealthcare();
                    }   
                    
//                    if (frmLogin.getAccountType().equals("Insurance")) {
//                        
//                    }
                }
            }
        });
  
        sd = new ShowDataToTable("SELECT * FROM Provider", tblHP, 3);
        sd = new ShowDataToTable("SELECT * FROM Insurance", tblInsCom, 3);
        sd = new ShowDataToTable("SELECT * FROM [Plan]", tblPlan, 3);
        sd = new ShowDataToTable("SELECT ID, [First name] + ' ' + [Last name] AS [Name], Gender, DOB, Address, Area, Degree FROM Doctor", tblDoctor, 7);
    }
    
    private void disableUpdate() {
        lblLock.setVisible(true);
        
        panel_update.setEnabled(false);
        panel_update.removeMouseListener(ml[0]);  // remove event mousePressed
        
        lblUpdate.setForeground(new Color(240, 240, 240));
        lblUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Update_Disable_25px.png")));
    }
    
    private void enableUpdate() {
        lblLock.setVisible(false);
        
        lblUpdate.setForeground(new Color(255, 255, 255));       
        lblUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Update_25px.png")));
        
        panel_update.setEnabled(true);
        panel_update.addMouseListener(ml[0]);
    }
    
    private void changeLoginText() {
        lblLogin.setText(frmLogin.getUsername());
        
        if (frmLogin.getAccountType().equals("Insurance")) {
            lblLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Company_30px.png")));
        }
        
        if (frmLogin.getAccountType().equals("Healthcare")) {
            lblLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Hospital_3_30px.png")));
        }    
        
        if (frmLogin.getAccountType().equals("Normal")) {
            lblLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_User_30px.png")));
        }
        
        if (frmLogin.getAccountType().equals("Admin")) {
            lblLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Admin_30px.png")));
        }
    }
    
    private void userLoginAdmin() {
        enableUpdate();
    }
    
    private void userLoginInsurance() {
        jLabel1.setText("Insurance");
        
        disableUpdate();
    }
    
    private void userLoginHealthcare() {
        jLabel1.setText("Healthcare");
        
        disableUpdate();
    }
    
    private void connectDatabase(String query) {        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");            
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HealthCareService;user=sa;password=sathya123;");           
            stmt = con.createStatement();            
            rs = stmt.executeQuery(query);           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    private void setColorPanel(JPanel panel) {
        panel.setBackground(new Color(57, 109, 160));
    }
    
    private void resetColorPanel(JPanel panel) {
        panel.setBackground(new Color(18,55,92));
    }
    
    private void setColorPanelButton(JPanel panel) {
        panel.setBackground(new Color(77, 201, 212));
    }
    
    private void resetColorPanelButton(JPanel panel) {
        panel.setBackground(new Color(0,160,174));
    }
    
    private void showPanelInCard(JPanel pnlParent, JPanel pnlChild) {
        pnlParent.removeAll();
        pnlParent.add(pnlChild);
        pnlParent.repaint();
        pnlParent.validate();
    }
    
    private void setColorChooseSidePane(JPanel p1, JPanel p2, JPanel p3, JPanel p4, JPanel p5, JPanel p6, JPanel p7, JPanel p8, JPanel p9, JPanel p10) {
        setColorPanel(p1);
        resetColorPanel(p2);
        resetColorPanel(p3);
        resetColorPanel(p4);
        resetColorPanel(p5);
        resetColorPanel(p6);
        resetColorPanel(p7);
        resetColorPanel(p8);
        resetColorPanel(p9);
        resetColorPanel(p10);
    }
    
    private void setIndexChooseSidePane(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8, boolean b9, boolean b10) {
        indDashboard.setOpaque(b1);
        indInsurance.setOpaque(b2);
        indHealthcarePlan.setOpaque(b3);
        indHP.setOpaque(b4);
        indDoctor.setOpaque(b5);
        indStatistic.setOpaque(b6);
        indUpdate.setOpaque(b7);
        indFind.setOpaque(b8);
        indAbout.setOpaque(b9);
        indHelp.setOpaque(b10);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scpMain = new javax.swing.JScrollPane();
        panel_main = new javax.swing.JPanel();
        panel_left = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        panel_dashboard = new javax.swing.JPanel();
        lblDashboard = new javax.swing.JLabel();
        indDashboard = new javax.swing.JPanel();
        panel_insurance_com = new javax.swing.JPanel();
        lblInsuranceCom = new javax.swing.JLabel();
        indInsurance = new javax.swing.JPanel();
        panel_healthcarePlan = new javax.swing.JPanel();
        lblInsuranceCom2 = new javax.swing.JLabel();
        indHealthcarePlan = new javax.swing.JPanel();
        panel_healthcarePro = new javax.swing.JPanel();
        lblHealthcarePro = new javax.swing.JLabel();
        indHP = new javax.swing.JPanel();
        panel_doctor = new javax.swing.JPanel();
        lblHealthcarePro1 = new javax.swing.JLabel();
        indDoctor = new javax.swing.JPanel();
        panel_statistic = new javax.swing.JPanel();
        lblStatistic = new javax.swing.JLabel();
        indStatistic = new javax.swing.JPanel();
        panel_update = new javax.swing.JPanel();
        lblUpdate = new javax.swing.JLabel();
        indUpdate = new javax.swing.JPanel();
        lblLock = new javax.swing.JLabel();
        panel_find = new javax.swing.JPanel();
        lblFind = new javax.swing.JLabel();
        indFind = new javax.swing.JPanel();
        panel_about = new javax.swing.JPanel();
        lblAbout = new javax.swing.JLabel();
        indAbout = new javax.swing.JPanel();
        panel_help = new javax.swing.JPanel();
        lblHelp = new javax.swing.JLabel();
        indHelp = new javax.swing.JPanel();
        panel_titlebar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panel_login = new javax.swing.JPanel();
        lblLogin = new javax.swing.JLabel();
        panel_home = new javax.swing.JPanel();
        panel_hDashboard = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        panel_hInsurance_com = new javax.swing.JPanel();
        panel_hInsMain = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        tfSearchInsCom = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbSearchIns = new javax.swing.JComboBox<>();
        panel_viewProvider = new javax.swing.JPanel();
        lblSearchHP2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInsCom = new javax.swing.JTable();
        panel_hHealthcarePlan = new javax.swing.JPanel();
        panel_hHPlanMain = new javax.swing.JPanel();
        lbl2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        tfSearchPlan = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbSearchPlan = new javax.swing.JComboBox<>();
        panel_viewDoctor2 = new javax.swing.JPanel();
        lblSearchHP3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPlan = new javax.swing.JTable();
        panel_hHealthcarePro = new javax.swing.JPanel();
        lblHealthcarePro2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        tfSearchHP = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbSearchHP = new javax.swing.JComboBox<>();
        panel_viewDoctor = new javax.swing.JPanel();
        lblSearchHP1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHP = new javax.swing.JTable();
        panel_hDoctor = new javax.swing.JPanel();
        panel_hDoctorMain = new javax.swing.JPanel();
        lbl3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        tfSearchDoctor = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbSearchDoctor = new javax.swing.JComboBox<>();
        panel_viewDoctor3 = new javax.swing.JPanel();
        lblSearchHP4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDoctor = new javax.swing.JTable();
        panel_hStatistic = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panel_hUpdates = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        panel_hFind = new javax.swing.JPanel();
        panel_findOption = new javax.swing.JPanel();
        lblDoctor = new javax.swing.JLabel();
        lblPatient = new javax.swing.JLabel();
        lblTreatment = new javax.swing.JLabel();
        panel_findDoctor = new javax.swing.JPanel();
        lblBack = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        cmbDisease = new javax.swing.JComboBox<>();
        cmbProvider = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        panel_findPatient = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblBack1 = new javax.swing.JLabel();
        panel_findTreatment = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblBack2 = new javax.swing.JLabel();
        panel_hAbout = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        panel_hHelp = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Healthcare Management System v1.0");
        setBackground(new java.awt.Color(255, 255, 255));

        panel_left.setBackground(new java.awt.Color(18, 55, 92));

        lblLogo.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Health_Book_48px.png"))); // NOI18N
        lblLogo.setText("Logo Here");

        panel_dashboard.setBackground(new java.awt.Color(18, 55, 92));
        panel_dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_dashboardMousePressed(evt);
            }
        });
        panel_dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDashboard.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblDashboard.setForeground(new java.awt.Color(255, 255, 255));
        lblDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Dashboard_25px.png"))); // NOI18N
        lblDashboard.setText("Dashboard");
        lblDashboard.setIconTextGap(20);
        panel_dashboard.add(lblDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 280, 42));

        indDashboard.setBackground(new java.awt.Color(255, 204, 0));
        indDashboard.setOpaque(false);
        indDashboard.setPreferredSize(new java.awt.Dimension(10, 52));

        javax.swing.GroupLayout indDashboardLayout = new javax.swing.GroupLayout(indDashboard);
        indDashboard.setLayout(indDashboardLayout);
        indDashboardLayout.setHorizontalGroup(
            indDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        indDashboardLayout.setVerticalGroup(
            indDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        panel_dashboard.add(indDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 42));

        panel_insurance_com.setBackground(new java.awt.Color(18, 55, 92));
        panel_insurance_com.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_insurance_com.setPreferredSize(new java.awt.Dimension(304, 42));
        panel_insurance_com.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_insurance_comMousePressed(evt);
            }
        });
        panel_insurance_com.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInsuranceCom.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblInsuranceCom.setForeground(new java.awt.Color(255, 255, 255));
        lblInsuranceCom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Company_25px_2.png"))); // NOI18N
        lblInsuranceCom.setText("Insurance Company");
        lblInsuranceCom.setIconTextGap(20);
        lblInsuranceCom.setPreferredSize(new java.awt.Dimension(112, 42));
        panel_insurance_com.add(lblInsuranceCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 280, 42));

        indInsurance.setBackground(new java.awt.Color(255, 204, 0));
        indInsurance.setOpaque(false);
        indInsurance.setPreferredSize(new java.awt.Dimension(10, 52));

        javax.swing.GroupLayout indInsuranceLayout = new javax.swing.GroupLayout(indInsurance);
        indInsurance.setLayout(indInsuranceLayout);
        indInsuranceLayout.setHorizontalGroup(
            indInsuranceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        indInsuranceLayout.setVerticalGroup(
            indInsuranceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        panel_insurance_com.add(indInsurance, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 42));

        panel_healthcarePlan.setBackground(new java.awt.Color(18, 55, 92));
        panel_healthcarePlan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_healthcarePlan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_healthcarePlanMousePressed(evt);
            }
        });
        panel_healthcarePlan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInsuranceCom2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblInsuranceCom2.setForeground(new java.awt.Color(255, 255, 255));
        lblInsuranceCom2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Health_Book_25px_3.png"))); // NOI18N
        lblInsuranceCom2.setText("Health Care Plans");
        lblInsuranceCom2.setIconTextGap(20);
        lblInsuranceCom2.setPreferredSize(new java.awt.Dimension(112, 42));
        panel_healthcarePlan.add(lblInsuranceCom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 280, 42));

        indHealthcarePlan.setBackground(new java.awt.Color(255, 204, 0));
        indHealthcarePlan.setOpaque(false);
        indHealthcarePlan.setPreferredSize(new java.awt.Dimension(10, 52));

        javax.swing.GroupLayout indHealthcarePlanLayout = new javax.swing.GroupLayout(indHealthcarePlan);
        indHealthcarePlan.setLayout(indHealthcarePlanLayout);
        indHealthcarePlanLayout.setHorizontalGroup(
            indHealthcarePlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        indHealthcarePlanLayout.setVerticalGroup(
            indHealthcarePlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        panel_healthcarePlan.add(indHealthcarePlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 42));

        panel_healthcarePro.setBackground(new java.awt.Color(18, 55, 92));
        panel_healthcarePro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_healthcarePro.setPreferredSize(new java.awt.Dimension(304, 42));
        panel_healthcarePro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_healthcareProMousePressed(evt);
            }
        });
        panel_healthcarePro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHealthcarePro.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHealthcarePro.setForeground(new java.awt.Color(255, 255, 255));
        lblHealthcarePro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Hospital_3_25px.png"))); // NOI18N
        lblHealthcarePro.setText("Health Care Providers");
        lblHealthcarePro.setIconTextGap(20);
        lblHealthcarePro.setPreferredSize(new java.awt.Dimension(171, 42));
        panel_healthcarePro.add(lblHealthcarePro, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 280, 42));

        indHP.setBackground(new java.awt.Color(255, 204, 0));
        indHP.setOpaque(false);
        indHP.setPreferredSize(new java.awt.Dimension(10, 52));

        javax.swing.GroupLayout indHPLayout = new javax.swing.GroupLayout(indHP);
        indHP.setLayout(indHPLayout);
        indHPLayout.setHorizontalGroup(
            indHPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        indHPLayout.setVerticalGroup(
            indHPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        panel_healthcarePro.add(indHP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 42));

        panel_doctor.setBackground(new java.awt.Color(18, 55, 92));
        panel_doctor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_doctor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_doctorMousePressed(evt);
            }
        });
        panel_doctor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHealthcarePro1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHealthcarePro1.setForeground(new java.awt.Color(255, 255, 255));
        lblHealthcarePro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Doctor_Male_25px_1.png"))); // NOI18N
        lblHealthcarePro1.setText("Doctors");
        lblHealthcarePro1.setIconTextGap(20);
        lblHealthcarePro1.setPreferredSize(new java.awt.Dimension(171, 42));
        panel_doctor.add(lblHealthcarePro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 280, 42));

        indDoctor.setBackground(new java.awt.Color(255, 204, 0));
        indDoctor.setOpaque(false);
        indDoctor.setPreferredSize(new java.awt.Dimension(10, 52));

        javax.swing.GroupLayout indDoctorLayout = new javax.swing.GroupLayout(indDoctor);
        indDoctor.setLayout(indDoctorLayout);
        indDoctorLayout.setHorizontalGroup(
            indDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        indDoctorLayout.setVerticalGroup(
            indDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        panel_doctor.add(indDoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 42));

        panel_statistic.setBackground(new java.awt.Color(18, 55, 92));
        panel_statistic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_statistic.setPreferredSize(new java.awt.Dimension(304, 42));
        panel_statistic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_statisticMousePressed(evt);
            }
        });
        panel_statistic.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblStatistic.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblStatistic.setForeground(new java.awt.Color(255, 255, 255));
        lblStatistic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Statistics_25px.png"))); // NOI18N
        lblStatistic.setText("Statistic, Graph & Data Analysis");
        lblStatistic.setIconTextGap(20);
        panel_statistic.add(lblStatistic, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 280, 42));

        indStatistic.setBackground(new java.awt.Color(255, 204, 0));
        indStatistic.setOpaque(false);
        indStatistic.setPreferredSize(new java.awt.Dimension(10, 52));

        javax.swing.GroupLayout indStatisticLayout = new javax.swing.GroupLayout(indStatistic);
        indStatistic.setLayout(indStatisticLayout);
        indStatisticLayout.setHorizontalGroup(
            indStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        indStatisticLayout.setVerticalGroup(
            indStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        panel_statistic.add(indStatistic, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 42));

        panel_update.setBackground(new java.awt.Color(18, 55, 92));
        panel_update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_update.setPreferredSize(new java.awt.Dimension(304, 42));
        panel_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_updateMousePressed(evt);
            }
        });
        panel_update.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUpdate.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblUpdate.setForeground(new java.awt.Color(128, 128, 128));
        lblUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Update_Disable_25px.png"))); // NOI18N
        lblUpdate.setText("Updates");
        lblUpdate.setIconTextGap(20);
        panel_update.add(lblUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 280, 42));

        indUpdate.setBackground(new java.awt.Color(255, 204, 0));
        indUpdate.setOpaque(false);
        indUpdate.setPreferredSize(new java.awt.Dimension(10, 52));

        javax.swing.GroupLayout indUpdateLayout = new javax.swing.GroupLayout(indUpdate);
        indUpdate.setLayout(indUpdateLayout);
        indUpdateLayout.setHorizontalGroup(
            indUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        indUpdateLayout.setVerticalGroup(
            indUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        panel_update.add(indUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 42));

        lblLock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_Lock_20px.png"))); // NOI18N
        panel_update.add(lblLock, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        panel_find.setBackground(new java.awt.Color(18, 55, 92));
        panel_find.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_find.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_findMousePressed(evt);
            }
        });
        panel_find.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFind.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblFind.setForeground(new java.awt.Color(255, 255, 255));
        lblFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Search_25px.png"))); // NOI18N
        lblFind.setText("Find");
        lblFind.setIconTextGap(20);
        panel_find.add(lblFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 280, 42));

        indFind.setBackground(new java.awt.Color(255, 204, 0));
        indFind.setOpaque(false);
        indFind.setPreferredSize(new java.awt.Dimension(10, 52));

        javax.swing.GroupLayout indFindLayout = new javax.swing.GroupLayout(indFind);
        indFind.setLayout(indFindLayout);
        indFindLayout.setHorizontalGroup(
            indFindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        indFindLayout.setVerticalGroup(
            indFindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        panel_find.add(indFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 42));

        panel_about.setBackground(new java.awt.Color(18, 55, 92));
        panel_about.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_about.setPreferredSize(new java.awt.Dimension(304, 42));
        panel_about.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_aboutMousePressed(evt);
            }
        });
        panel_about.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAbout.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblAbout.setForeground(new java.awt.Color(255, 255, 255));
        lblAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_About_25px.png"))); // NOI18N
        lblAbout.setText("About");
        lblAbout.setIconTextGap(20);
        panel_about.add(lblAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 280, 42));

        indAbout.setBackground(new java.awt.Color(255, 204, 0));
        indAbout.setOpaque(false);
        indAbout.setPreferredSize(new java.awt.Dimension(10, 52));

        javax.swing.GroupLayout indAboutLayout = new javax.swing.GroupLayout(indAbout);
        indAbout.setLayout(indAboutLayout);
        indAboutLayout.setHorizontalGroup(
            indAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        indAboutLayout.setVerticalGroup(
            indAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        panel_about.add(indAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 42));

        panel_help.setBackground(new java.awt.Color(18, 55, 92));
        panel_help.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_help.setPreferredSize(new java.awt.Dimension(304, 42));
        panel_help.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_helpMousePressed(evt);
            }
        });
        panel_help.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHelp.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblHelp.setForeground(new java.awt.Color(255, 255, 255));
        lblHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Help_25px.png"))); // NOI18N
        lblHelp.setText("Help");
        lblHelp.setIconTextGap(20);
        panel_help.add(lblHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 280, 42));

        indHelp.setBackground(new java.awt.Color(255, 204, 0));
        indHelp.setOpaque(false);
        indHelp.setPreferredSize(new java.awt.Dimension(10, 52));

        javax.swing.GroupLayout indHelpLayout = new javax.swing.GroupLayout(indHelp);
        indHelp.setLayout(indHelpLayout);
        indHelpLayout.setHorizontalGroup(
            indHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        indHelpLayout.setVerticalGroup(
            indHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        panel_help.add(indHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 42));

        javax.swing.GroupLayout panel_leftLayout = new javax.swing.GroupLayout(panel_left);
        panel_left.setLayout(panel_leftLayout);
        panel_leftLayout.setHorizontalGroup(
            panel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panel_insurance_com, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panel_healthcarePro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panel_statistic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panel_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panel_about, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panel_help, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(panel_leftLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(lblLogo))
            .addComponent(panel_healthcarePlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panel_doctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panel_find, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panel_leftLayout.setVerticalGroup(
            panel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_leftLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblLogo)
                .addGap(77, 77, 77)
                .addComponent(panel_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_insurance_com, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_healthcarePlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_healthcarePro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_doctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_statistic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_find, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_about, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_help, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_titlebar.setBackground(new java.awt.Color(18, 55, 92));
        panel_titlebar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        panel_titlebar.setLayout(new java.awt.BorderLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Management of Health Care Services");
        panel_titlebar.add(jLabel2, java.awt.BorderLayout.CENTER);

        panel_login.setBackground(new java.awt.Color(255, 255, 255));

        lblLogin.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblLogin.setForeground(new java.awt.Color(60, 141, 188));
        lblLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Add_User_Male_30px_4.png"))); // NOI18N
        lblLogin.setText("Log in / Register");
        lblLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLoginMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panel_loginLayout = new javax.swing.GroupLayout(panel_login);
        panel_login.setLayout(panel_loginLayout);
        panel_loginLayout.setHorizontalGroup(
            panel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_loginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblLogin)
                .addGap(21, 21, 21))
        );
        panel_loginLayout.setVerticalGroup(
            panel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panel_home.setBackground(new java.awt.Color(245, 245, 245));
        panel_home.setLayout(new java.awt.CardLayout());

        jScrollPane5.setPreferredSize(new java.awt.Dimension(854, 1000));

        jPanel7.setPreferredSize(new java.awt.Dimension(854, 998));

        jLabel3.setText("Dashboard");

        jLabel1.setText("jLabel1");

        jLabel15.setText("jLabel15");

        jLabel16.setText("jLabel16");

        jLabel17.setText("jLabel17");

        jLabel18.setText("jLabel18");

        jLabel19.setText("jLabel19");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addContainerGap())
        );

        jScrollPane5.setViewportView(jPanel7);

        javax.swing.GroupLayout panel_hDashboardLayout = new javax.swing.GroupLayout(panel_hDashboard);
        panel_hDashboard.setLayout(panel_hDashboardLayout);
        panel_hDashboardLayout.setHorizontalGroup(
            panel_hDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_hDashboardLayout.setVerticalGroup(
            panel_hDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
        );

        panel_home.add(panel_hDashboard, "card2");

        panel_hInsMain.setBackground(new java.awt.Color(239, 240, 244));

        lbl1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl1.setText("Insurance Company");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));

        tfSearchInsCom.setForeground(java.awt.Color.gray);
        tfSearchInsCom.setText("Search here");
        tfSearchInsCom.setBorder(null);
        tfSearchInsCom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchInsComKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Search by:");

        cbSearchIns.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbSearchIns.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name", "Address" }));

        panel_viewProvider.setBackground(new java.awt.Color(0, 160, 174));
        panel_viewProvider.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel_viewProvider.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_viewProvider.setPreferredSize(new java.awt.Dimension(90, 26));
        panel_viewProvider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_viewProviderMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_viewProviderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_viewProviderMouseExited(evt);
            }
        });

        lblSearchHP2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblSearchHP2.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchHP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearchHP2.setText("View Provider");

        javax.swing.GroupLayout panel_viewProviderLayout = new javax.swing.GroupLayout(panel_viewProvider);
        panel_viewProvider.setLayout(panel_viewProviderLayout);
        panel_viewProviderLayout.setHorizontalGroup(
            panel_viewProviderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSearchHP2, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
        );
        panel_viewProviderLayout.setVerticalGroup(
            panel_viewProviderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSearchHP2, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        jScrollPane2.setBorder(null);

        tblInsCom.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblInsCom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInsCom.setOpaque(false);
        tblInsCom.setRowHeight(24);
        tblInsCom.setSelectionBackground(new java.awt.Color(18, 55, 92));
        tblInsCom.setSelectionForeground(new java.awt.Color(250, 250, 250));
        tblInsCom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInsComMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblInsCom);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSearchInsCom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbSearchIns, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_viewProvider, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbSearchIns, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addComponent(panel_viewProvider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(tfSearchInsCom, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_hInsMainLayout = new javax.swing.GroupLayout(panel_hInsMain);
        panel_hInsMain.setLayout(panel_hInsMainLayout);
        panel_hInsMainLayout.setHorizontalGroup(
            panel_hInsMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hInsMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_hInsMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_hInsMainLayout.createSequentialGroup()
                        .addComponent(lbl1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_hInsMainLayout.setVerticalGroup(
            panel_hInsMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hInsMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_hInsurance_comLayout = new javax.swing.GroupLayout(panel_hInsurance_com);
        panel_hInsurance_com.setLayout(panel_hInsurance_comLayout);
        panel_hInsurance_comLayout.setHorizontalGroup(
            panel_hInsurance_comLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hInsurance_comLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panel_hInsMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        panel_hInsurance_comLayout.setVerticalGroup(
            panel_hInsurance_comLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hInsurance_comLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panel_hInsMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        panel_home.add(panel_hInsurance_com, "card4");

        panel_hHPlanMain.setBackground(new java.awt.Color(239, 240, 244));

        lbl2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl2.setText("Health Care Plans");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));

        tfSearchPlan.setForeground(java.awt.Color.gray);
        tfSearchPlan.setText("Search here");
        tfSearchPlan.setBorder(null);
        tfSearchPlan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchPlanKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Search by:");

        cbSearchPlan.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbSearchPlan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name", "Description" }));

        panel_viewDoctor2.setBackground(new java.awt.Color(0, 160, 174));
        panel_viewDoctor2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel_viewDoctor2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_viewDoctor2.setPreferredSize(new java.awt.Dimension(90, 26));
        panel_viewDoctor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_viewDoctor2MouseClicked(evt);
            }
        });

        lblSearchHP3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblSearchHP3.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchHP3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearchHP3.setText("View Doctor");

        javax.swing.GroupLayout panel_viewDoctor2Layout = new javax.swing.GroupLayout(panel_viewDoctor2);
        panel_viewDoctor2.setLayout(panel_viewDoctor2Layout);
        panel_viewDoctor2Layout.setHorizontalGroup(
            panel_viewDoctor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSearchHP3, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
        );
        panel_viewDoctor2Layout.setVerticalGroup(
            panel_viewDoctor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSearchHP3, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        jScrollPane3.setBorder(null);

        tblPlan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblPlan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPlan.setOpaque(false);
        tblPlan.setRowHeight(24);
        tblPlan.setSelectionBackground(new java.awt.Color(18, 55, 92));
        tblPlan.setSelectionForeground(new java.awt.Color(250, 250, 250));
        tblPlan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPlanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblPlan);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSearchPlan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbSearchPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_viewDoctor2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbSearchPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addComponent(panel_viewDoctor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(tfSearchPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_hHPlanMainLayout = new javax.swing.GroupLayout(panel_hHPlanMain);
        panel_hHPlanMain.setLayout(panel_hHPlanMainLayout);
        panel_hHPlanMainLayout.setHorizontalGroup(
            panel_hHPlanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hHPlanMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_hHPlanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_hHPlanMainLayout.createSequentialGroup()
                        .addComponent(lbl2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_hHPlanMainLayout.setVerticalGroup(
            panel_hHPlanMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hHPlanMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_hHealthcarePlanLayout = new javax.swing.GroupLayout(panel_hHealthcarePlan);
        panel_hHealthcarePlan.setLayout(panel_hHealthcarePlanLayout);
        panel_hHealthcarePlanLayout.setHorizontalGroup(
            panel_hHealthcarePlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_hHPlanMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_hHealthcarePlanLayout.setVerticalGroup(
            panel_hHealthcarePlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_hHPlanMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panel_home.add(panel_hHealthcarePlan, "card10");

        panel_hHealthcarePro.setBackground(new java.awt.Color(239, 240, 244));

        lblHealthcarePro2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblHealthcarePro2.setText("Healthcare Providers");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));

        tfSearchHP.setForeground(java.awt.Color.gray);
        tfSearchHP.setText("Search here");
        tfSearchHP.setBorder(null);
        tfSearchHP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchHPKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Search by:");

        cbSearchHP.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbSearchHP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name", "Address" }));

        panel_viewDoctor.setBackground(new java.awt.Color(0, 160, 174));
        panel_viewDoctor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel_viewDoctor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_viewDoctor.setPreferredSize(new java.awt.Dimension(90, 26));
        panel_viewDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_viewDoctorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_viewDoctorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel_viewDoctorMouseExited(evt);
            }
        });

        lblSearchHP1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblSearchHP1.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchHP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearchHP1.setText("View Doctor");

        javax.swing.GroupLayout panel_viewDoctorLayout = new javax.swing.GroupLayout(panel_viewDoctor);
        panel_viewDoctor.setLayout(panel_viewDoctorLayout);
        panel_viewDoctorLayout.setHorizontalGroup(
            panel_viewDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSearchHP1, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
        );
        panel_viewDoctorLayout.setVerticalGroup(
            panel_viewDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSearchHP1, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        jScrollPane1.setBorder(null);

        tblHP.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblHP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHP.setOpaque(false);
        tblHP.setRowHeight(24);
        tblHP.setSelectionBackground(new java.awt.Color(18, 55, 92));
        tblHP.setSelectionForeground(new java.awt.Color(250, 250, 250));
        tblHP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSearchHP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbSearchHP, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_viewDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbSearchHP, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))
                            .addComponent(panel_viewDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(tfSearchHP, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel9, tfSearchHP});

        javax.swing.GroupLayout panel_hHealthcareProLayout = new javax.swing.GroupLayout(panel_hHealthcarePro);
        panel_hHealthcarePro.setLayout(panel_hHealthcareProLayout);
        panel_hHealthcareProLayout.setHorizontalGroup(
            panel_hHealthcareProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hHealthcareProLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_hHealthcareProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_hHealthcareProLayout.createSequentialGroup()
                        .addComponent(lblHealthcarePro2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_hHealthcareProLayout.setVerticalGroup(
            panel_hHealthcareProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hHealthcareProLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHealthcarePro2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_home.add(panel_hHealthcarePro, "card3");

        panel_hDoctorMain.setBackground(new java.awt.Color(239, 240, 244));

        lbl3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl3.setText("Doctors");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));

        tfSearchDoctor.setForeground(java.awt.Color.gray);
        tfSearchDoctor.setText("Search here");
        tfSearchDoctor.setBorder(null);
        tfSearchDoctor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchDoctorKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Search by:");

        cbSearchDoctor.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbSearchDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name", "Date of Birth", "Address", "Area of Specialty", "Degree" }));

        panel_viewDoctor3.setBackground(new java.awt.Color(0, 160, 174));
        panel_viewDoctor3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel_viewDoctor3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_viewDoctor3.setPreferredSize(new java.awt.Dimension(90, 26));
        panel_viewDoctor3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_viewDoctor3MouseClicked(evt);
            }
        });

        lblSearchHP4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblSearchHP4.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchHP4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearchHP4.setText("View Doctor");

        javax.swing.GroupLayout panel_viewDoctor3Layout = new javax.swing.GroupLayout(panel_viewDoctor3);
        panel_viewDoctor3.setLayout(panel_viewDoctor3Layout);
        panel_viewDoctor3Layout.setHorizontalGroup(
            panel_viewDoctor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSearchHP4, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
        );
        panel_viewDoctor3Layout.setVerticalGroup(
            panel_viewDoctor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSearchHP4, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        jScrollPane4.setBorder(null);

        tblDoctor.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblDoctor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Gender", "Date of Birth", "Address", "Area of Specialty", "Degree"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoctor.setOpaque(false);
        tblDoctor.setRowHeight(24);
        tblDoctor.setSelectionBackground(new java.awt.Color(18, 55, 92));
        tblDoctor.setSelectionForeground(new java.awt.Color(250, 250, 250));
        tblDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoctorMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblDoctor);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSearchDoctor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbSearchDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_viewDoctor3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbSearchDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13))
                            .addComponent(panel_viewDoctor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(tfSearchDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_hDoctorMainLayout = new javax.swing.GroupLayout(panel_hDoctorMain);
        panel_hDoctorMain.setLayout(panel_hDoctorMainLayout);
        panel_hDoctorMainLayout.setHorizontalGroup(
            panel_hDoctorMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hDoctorMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_hDoctorMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_hDoctorMainLayout.createSequentialGroup()
                        .addComponent(lbl3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_hDoctorMainLayout.setVerticalGroup(
            panel_hDoctorMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hDoctorMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_hDoctorLayout = new javax.swing.GroupLayout(panel_hDoctor);
        panel_hDoctor.setLayout(panel_hDoctorLayout);
        panel_hDoctorLayout.setHorizontalGroup(
            panel_hDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_hDoctorMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_hDoctorLayout.setVerticalGroup(
            panel_hDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_hDoctorMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panel_home.add(panel_hDoctor, "card11");

        jLabel4.setText("Statistic");

        javax.swing.GroupLayout panel_hStatisticLayout = new javax.swing.GroupLayout(panel_hStatistic);
        panel_hStatistic.setLayout(panel_hStatisticLayout);
        panel_hStatisticLayout.setHorizontalGroup(
            panel_hStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hStatisticLayout.createSequentialGroup()
                .addContainerGap(570, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(331, 331, 331))
        );
        panel_hStatisticLayout.setVerticalGroup(
            panel_hStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hStatisticLayout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(jLabel4)
                .addContainerGap(331, Short.MAX_VALUE))
        );

        panel_home.add(panel_hStatistic, "card6");

        jLabel5.setText("Update");

        javax.swing.GroupLayout panel_hUpdatesLayout = new javax.swing.GroupLayout(panel_hUpdates);
        panel_hUpdates.setLayout(panel_hUpdatesLayout);
        panel_hUpdatesLayout.setHorizontalGroup(
            panel_hUpdatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hUpdatesLayout.createSequentialGroup()
                .addGap(335, 335, 335)
                .addComponent(jLabel5)
                .addContainerGap(569, Short.MAX_VALUE))
        );
        panel_hUpdatesLayout.setVerticalGroup(
            panel_hUpdatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hUpdatesLayout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(jLabel5)
                .addContainerGap(330, Short.MAX_VALUE))
        );

        panel_home.add(panel_hUpdates, "card7");

        panel_hFind.setLayout(new java.awt.CardLayout());

        panel_findOption.setLayout(new java.awt.GridLayout(1, 0));

        lblDoctor.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblDoctor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoctor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Medical_Doctor_104px.png"))); // NOI18N
        lblDoctor.setText("Doctor");
        lblDoctor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDoctor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblDoctor.setIconTextGap(10);
        lblDoctor.setOpaque(true);
        lblDoctor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoctorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDoctorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDoctorMouseExited(evt);
            }
        });
        panel_findOption.add(lblDoctor);

        lblPatient.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPatient.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Nurse_Call_104px.png"))); // NOI18N
        lblPatient.setText("Patient");
        lblPatient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblPatient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPatient.setIconTextGap(10);
        lblPatient.setOpaque(true);
        lblPatient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblPatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPatientMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPatientMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPatientMouseExited(evt);
            }
        });
        panel_findOption.add(lblPatient);

        lblTreatment.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTreatment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTreatment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Treatment_104px.png"))); // NOI18N
        lblTreatment.setText("Treatment");
        lblTreatment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTreatment.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTreatment.setIconTextGap(10);
        lblTreatment.setOpaque(true);
        lblTreatment.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblTreatment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTreatmentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTreatmentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTreatmentMouseExited(evt);
            }
        });
        panel_findOption.add(lblTreatment);

        panel_hFind.add(panel_findOption, "card2");

        lblBack.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Back_Arrow_25px_1.png"))); // NOI18N
        lblBack.setText("Back to option");
        lblBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBack.setIconTextGap(7);
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBackMouseExited(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(0, 102, 255));
        jLabel14.setFont(new java.awt.Font("Microsoft Yi Baiti", 0, 55)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Find Doctor");
        jPanel1.add(jLabel14);

        cmbDisease.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        cmbDisease.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a Disease" }));

        cmbProvider.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        cmbProvider.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Provider" }));

        btnSearch.setFont(new java.awt.Font("Malgun Gothic", 0, 24)); // NOI18N
        btnSearch.setText("Search");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbDisease, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbProvider, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(btnSearch)))
                .addContainerGap(514, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbDisease, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbProvider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_findDoctorLayout = new javax.swing.GroupLayout(panel_findDoctor);
        panel_findDoctor.setLayout(panel_findDoctorLayout);
        panel_findDoctorLayout.setHorizontalGroup(
            panel_findDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_findDoctorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_findDoctorLayout.setVerticalGroup(
            panel_findDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_findDoctorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(462, Short.MAX_VALUE))
        );

        panel_hFind.add(panel_findDoctor, "card3");

        jLabel8.setText("Patient");

        lblBack1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lblBack1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Back_Arrow_25px_1.png"))); // NOI18N
        lblBack1.setText("Back to option");
        lblBack1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBack1.setIconTextGap(7);
        lblBack1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBack1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBack1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBack1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout panel_findPatientLayout = new javax.swing.GroupLayout(panel_findPatient);
        panel_findPatient.setLayout(panel_findPatientLayout);
        panel_findPatientLayout.setHorizontalGroup(
            panel_findPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_findPatientLayout.createSequentialGroup()
                .addGroup(panel_findPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_findPatientLayout.createSequentialGroup()
                        .addGap(354, 354, 354)
                        .addComponent(jLabel8))
                    .addGroup(panel_findPatientLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblBack1)))
                .addContainerGap(551, Short.MAX_VALUE))
        );
        panel_findPatientLayout.setVerticalGroup(
            panel_findPatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_findPatientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBack1)
                .addGap(250, 250, 250)
                .addComponent(jLabel8)
                .addContainerGap(488, Short.MAX_VALUE))
        );

        panel_hFind.add(panel_findPatient, "card4");

        jLabel11.setText("treatment");

        lblBack2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lblBack2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Back_Arrow_25px_1.png"))); // NOI18N
        lblBack2.setText("Back to option");
        lblBack2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBack2.setIconTextGap(7);
        lblBack2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBack2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBack2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBack2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout panel_findTreatmentLayout = new javax.swing.GroupLayout(panel_findTreatment);
        panel_findTreatment.setLayout(panel_findTreatmentLayout);
        panel_findTreatmentLayout.setHorizontalGroup(
            panel_findTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_findTreatmentLayout.createSequentialGroup()
                .addContainerGap(488, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(403, 403, 403))
            .addGroup(panel_findTreatmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBack2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_findTreatmentLayout.setVerticalGroup(
            panel_findTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_findTreatmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBack2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 453, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(285, 285, 285))
        );

        panel_hFind.add(panel_findTreatment, "card5");

        panel_home.add(panel_hFind, "card9");

        jLabel6.setText("About");

        javax.swing.GroupLayout panel_hAboutLayout = new javax.swing.GroupLayout(panel_hAbout);
        panel_hAbout.setLayout(panel_hAboutLayout);
        panel_hAboutLayout.setHorizontalGroup(
            panel_hAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_hAboutLayout.createSequentialGroup()
                .addContainerGap(586, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(324, 324, 324))
        );
        panel_hAboutLayout.setVerticalGroup(
            panel_hAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hAboutLayout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(jLabel6)
                .addContainerGap(332, Short.MAX_VALUE))
        );

        panel_home.add(panel_hAbout, "card8");

        jLabel7.setText("Help clicked");

        javax.swing.GroupLayout panel_hHelpLayout = new javax.swing.GroupLayout(panel_hHelp);
        panel_hHelp.setLayout(panel_hHelpLayout);
        panel_hHelpLayout.setHorizontalGroup(
            panel_hHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hHelpLayout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(jLabel7)
                .addContainerGap(607, Short.MAX_VALUE))
        );
        panel_hHelpLayout.setVerticalGroup(
            panel_hHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hHelpLayout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(jLabel7)
                .addContainerGap(348, Short.MAX_VALUE))
        );

        panel_home.add(panel_hHelp, "card5");

        javax.swing.GroupLayout panel_mainLayout = new javax.swing.GroupLayout(panel_main);
        panel_main.setLayout(panel_mainLayout);
        panel_mainLayout.setHorizontalGroup(
            panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_mainLayout.createSequentialGroup()
                .addComponent(panel_left, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_titlebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        panel_mainLayout.setVerticalGroup(
            panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_mainLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_left, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_mainLayout.createSequentialGroup()
                        .addComponent(panel_titlebar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panel_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panel_home, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        scpMain.setViewportView(panel_main);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1262, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Underline text
    private void lblLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginMouseEntered
        Font font = lblLogin.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);  
        lblLogin.setFont(font.deriveFont(attributes));
        
    }//GEN-LAST:event_lblLoginMouseEntered

    // No underline text
    private void lblLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginMouseExited
        Font font = lblLogin.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, -1);
        lblLogin.setFont(font.deriveFont(attributes));
        
    }//GEN-LAST:event_lblLoginMouseExited

    private void panel_healthcareProMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_healthcareProMousePressed
        setColorChooseSidePane(panel_healthcarePro, panel_dashboard, panel_healthcarePlan, panel_insurance_com, panel_about, panel_statistic, panel_update, panel_find, panel_doctor, panel_help);
        
        setIndexChooseSidePane(false, false, false, true, false, false, false, false, false, false);
        
        showPanelInCard(panel_home, panel_hHealthcarePro);
        
        SubTable.setTableHeader(tblHP, new Color(240, 240, 240), Color.BLACK, new Font("Tahoma", Font.PLAIN, 12));
        
        TextFieldSearch.setTextLook(tfSearchHP);
    }//GEN-LAST:event_panel_healthcareProMousePressed

    private void panel_dashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_dashboardMousePressed
        setColorChooseSidePane(panel_dashboard, panel_healthcarePro, panel_healthcarePlan, panel_insurance_com, panel_about, panel_statistic, panel_update, panel_find, panel_doctor, panel_help);
        
        setIndexChooseSidePane(true, false, false, false, false, false, false, false, false, false);
        
        showPanelInCard(panel_home, panel_hDashboard);
    }//GEN-LAST:event_panel_dashboardMousePressed

    private void panel_statisticMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_statisticMousePressed
        setColorChooseSidePane(panel_statistic, panel_dashboard, panel_healthcarePlan, panel_insurance_com, panel_about, panel_healthcarePro, panel_update, panel_find, panel_doctor, panel_help);
        
        setIndexChooseSidePane(false, false, false, false, false, true, false, false, false, false);
        
        showPanelInCard(panel_home, panel_hStatistic);
    }//GEN-LAST:event_panel_statisticMousePressed

    private void panel_updateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_updateMousePressed
        setColorChooseSidePane(panel_update, panel_dashboard, panel_healthcarePlan, panel_insurance_com, panel_about, panel_healthcarePro, panel_statistic, panel_find, panel_doctor, panel_help);
        
        setIndexChooseSidePane(false, false, false, false, false, false, true, false, false, false);
        
        showPanelInCard(panel_home, panel_hUpdates);
    }//GEN-LAST:event_panel_updateMousePressed
 
    private void panel_aboutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_aboutMousePressed
        setColorChooseSidePane(panel_about, panel_dashboard, panel_healthcarePlan, panel_insurance_com, panel_statistic, panel_healthcarePro, panel_update, panel_find, panel_doctor, panel_help);
        
        setIndexChooseSidePane(false, false, false, false, false, false, false, false, true, false);
        
        showPanelInCard(panel_home, panel_hAbout);
    }//GEN-LAST:event_panel_aboutMousePressed

    private void panel_helpMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_helpMousePressed
        setColorChooseSidePane(panel_help, panel_dashboard, panel_healthcarePlan, panel_insurance_com, panel_about, panel_healthcarePro, panel_update, panel_find, panel_doctor, panel_statistic);
        
        setIndexChooseSidePane(false, false, false, false, false, false, false, false, false, true);
        
        showPanelInCard(panel_home, panel_hHelp);
    }//GEN-LAST:event_panel_helpMousePressed

    private void panel_insurance_comMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_insurance_comMousePressed
        setColorChooseSidePane(panel_insurance_com, panel_dashboard, panel_healthcarePlan, panel_help, panel_about, panel_healthcarePro, panel_update, panel_find, panel_doctor, panel_statistic);
        
        setIndexChooseSidePane(false, true, false, false, false, false, false, false, false, false);
        
        showPanelInCard(panel_home, panel_hInsurance_com);
        
        SubTable.setTableHeader(tblInsCom, new Color(240, 240, 240), Color.BLACK, new Font("Tahoma", Font.PLAIN, 12));
        
        TextFieldSearch.setTextLook(tfSearchInsCom);
    }//GEN-LAST:event_panel_insurance_comMousePressed

    private void tfSearchHPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchHPKeyReleased
        String search = tfSearchHP.getText();
        
        if (cbSearchHP.getSelectedIndex() == 0) {
            sd = new ShowDataToTable("Select * From Provider Where ID Like '%" + search + "%'", tblHP, 3);
        }
        
        if (cbSearchHP.getSelectedIndex() == 1) {
            sd = new ShowDataToTable("Select * From Provider Where NAME Like '%" + search + "%'", tblHP, 3);
        }
        
        if (cbSearchHP.getSelectedIndex() == 2) {
            sd = new ShowDataToTable("Select * From Provider Where Address Like '%" + search + "%'", tblHP, 3);
        }
    }//GEN-LAST:event_tfSearchHPKeyReleased

    private void tblHPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHPMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            
            int index = tblHP.getSelectedRow();

            model = (DefaultTableModel)tblHP.getModel();

            String ID = model.getValueAt(index, 0).toString();

            HPInsurance hpIn = new HPInsurance(ID);

            hpIn.setSize(800, 500);
            hpIn.setVisible(true);  
        }
    }//GEN-LAST:event_tblHPMouseClicked

    private void panel_viewDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_viewDoctorMouseClicked
        int index = tblHP.getSelectedRow();
        
        model = (DefaultTableModel)tblHP.getModel();
        
        String ID = model.getValueAt(index, 0).toString();
        
        HPDoctor hpDoc = new HPDoctor(ID);
        
        hpDoc.setSize(600, 400);
        hpDoc.setVisible(true);
    }//GEN-LAST:event_panel_viewDoctorMouseClicked

    private void tfSearchInsComKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchInsComKeyReleased
        String search = tfSearchInsCom.getText();
        
        if (cbSearchIns.getSelectedIndex() == 0) {
            sd = new ShowDataToTable("Select * From Insurance Where ID Like '%" + search + "%'", tblInsCom, 3);
        }
        
        if (cbSearchIns.getSelectedIndex() == 1) {
            sd = new ShowDataToTable("Select * From Insurance Where Name Like '%" + search + "%'", tblInsCom, 3);
        }
        
        if (cbSearchIns.getSelectedIndex() == 2) {
            sd = new ShowDataToTable("Select * From Insurance Where Addresss Like '%" + search + "%'", tblInsCom, 3);
        }
    }//GEN-LAST:event_tfSearchInsComKeyReleased

    private void panel_viewProviderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_viewProviderMouseClicked
        int index = tblInsCom.getSelectedRow();
        
        model = (DefaultTableModel)tblInsCom.getModel();
        
        String ID = model.getValueAt(index, 0).toString();
        
        InsuranceHP InsHP = new InsuranceHP(ID);
        
        InsHP.setSize(800, 500);
        InsHP.setVisible(true);
    }//GEN-LAST:event_panel_viewProviderMouseClicked

    private void tblInsComMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInsComMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            
            int index = tblInsCom.getSelectedRow();

            model = (DefaultTableModel)tblInsCom.getModel();

            String ID = model.getValueAt(index, 0).toString();

            InsurancePlan insPlan = new InsurancePlan(ID);

            insPlan.setSize(800, 500);
            insPlan.setVisible(true);  
        }
    }//GEN-LAST:event_tblInsComMouseClicked

    private void panel_healthcarePlanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_healthcarePlanMousePressed
        setColorChooseSidePane(panel_healthcarePlan, panel_dashboard, panel_healthcarePro, panel_insurance_com, panel_about, panel_statistic, panel_update, panel_find, panel_doctor, panel_help);
        
        setIndexChooseSidePane(false, false, true, false, false, false, false, false, false, false);
        
        showPanelInCard(panel_home, panel_hHealthcarePlan);
        
        SubTable.setTableHeader(tblPlan, new Color(240, 240, 240), Color.BLACK, new Font("Tahoma", Font.PLAIN, 12));
        
        TextFieldSearch.setTextLook(tfSearchPlan);
        
        panel_viewDoctor2.setVisible(false);
    }//GEN-LAST:event_panel_healthcarePlanMousePressed

    private void panel_doctorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_doctorMousePressed
        setColorChooseSidePane(panel_doctor, panel_dashboard, panel_healthcarePlan, panel_insurance_com, panel_about, panel_statistic, panel_update, panel_find, panel_healthcarePro, panel_help);
        
        setIndexChooseSidePane(false, false, false, false, true, false, false, false, false, false);
        
        showPanelInCard(panel_home, panel_hDoctor);
        
        SubTable.setTableHeader(tblDoctor, new Color(240, 240, 240), Color.BLACK, new Font("Tahoma", Font.PLAIN, 12));
        
        TextFieldSearch.setTextLook(tfSearchDoctor);
        
        panel_viewDoctor3.setVisible(false);
    }//GEN-LAST:event_panel_doctorMousePressed

    private void panel_findMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_findMousePressed
        setColorChooseSidePane(panel_find, panel_dashboard, panel_healthcarePlan, panel_insurance_com, panel_about, panel_statistic, panel_update, panel_healthcarePro, panel_doctor, panel_help);
        
        setIndexChooseSidePane(false, false, false, false, false, false, false, true, false, false);
        
        showPanelInCard(panel_home, panel_hFind);
//        
//        SubTable.setTableHeader(tblHP, new Color(240, 240, 240), Color.BLACK, new Font("Tahoma", Font.PLAIN, 12));
//        
//        TextFieldSearch.setTextLook(tfSearchHP);
    }//GEN-LAST:event_panel_findMousePressed

    private void tfSearchPlanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchPlanKeyReleased
        String search = tfSearchPlan.getText();
        
        if (cbSearchPlan.getSelectedIndex() == 0) {
            sd = new ShowDataToTable("SELECT * FROM [Plan] WHERE ID LIKE '%" + search + "%'", tblPlan, 3);
        }
        
        if (cbSearchPlan.getSelectedIndex() == 1) {
            sd = new ShowDataToTable("SELECT * FROM [Plan] WHERE Name LIKE '%" + search + "%'", tblPlan, 3);
        }
        
        if (cbSearchPlan.getSelectedIndex() == 2) {
            sd = new ShowDataToTable("SELECT * FROM [Plan] WHERE Description LIKE '%" + search + "%'", tblPlan, 3);
        }
    }//GEN-LAST:event_tfSearchPlanKeyReleased

    private void panel_viewDoctor2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_viewDoctor2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panel_viewDoctor2MouseClicked

    private void tblPlanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPlanMouseClicked

    private void panel_viewDoctorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_viewDoctorMouseExited
        resetColorPanelButton(panel_viewDoctor);
    }//GEN-LAST:event_panel_viewDoctorMouseExited

    private void panel_viewDoctorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_viewDoctorMouseEntered
        setColorPanelButton(panel_viewDoctor);
    }//GEN-LAST:event_panel_viewDoctorMouseEntered

    private void tfSearchDoctorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchDoctorKeyReleased
        String search = tfSearchDoctor.getText();
        
        if (cbSearchDoctor.getSelectedIndex() == 0) {
            sd = new ShowDataToTable("SELECT ID, [First name] + ' ' + [Last name] AS [Name], Gender, DOB, Address, Area, Degree FROM Doctor WHERE ID LIKE '%" + search + "%'", tblDoctor, 7);
        }
        
        if (cbSearchDoctor.getSelectedIndex() == 1) {
            sd = new ShowDataToTable("SELECT ID, [First name] + ' ' + [Last name] AS [Name], Gender, DOB, Address, Area, Degree FROM Doctor WHERE [First name] + [Last name] LIKE '%" + search + "%'", tblDoctor, 7);
        }
        
        if (cbSearchDoctor.getSelectedIndex() == 2) {
            sd = new ShowDataToTable("SELECT ID, [First name] + ' ' + [Last name] AS [Name], Gender, DOB, Address, Area, Degree FROM Doctor WHERE DOB LIKE '%" + search + "%'", tblDoctor, 7);
        }
        
        if (cbSearchDoctor.getSelectedIndex() == 3) {
            sd = new ShowDataToTable("SELECT ID, [First name] + ' ' + [Last name] AS [Name], Gender, DOB, Address, Area, Degree FROM Doctor WHERE Address LIKE '%" + search + "%'", tblDoctor, 7);
        }
        
        if (cbSearchDoctor.getSelectedIndex() == 4) {
            sd = new ShowDataToTable("SELECT ID, [First name] + ' ' + [Last name] AS [Name], Gender, DOB, Address, Area, Degree FROM Doctor WHERE Area LIKE '%" + search + "%'", tblDoctor, 7);
        }
        
        if (cbSearchDoctor.getSelectedIndex() == 5) {
            sd = new ShowDataToTable("SELECT ID, [First name] + ' ' + [Last name] AS [Name], Gender, DOB, Address, Area, Degree FROM Doctor WHERE Degree LIKE '%" + search + "%'", tblDoctor, 7);
        }
    }//GEN-LAST:event_tfSearchDoctorKeyReleased

    private void panel_viewDoctor3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_viewDoctor3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panel_viewDoctor3MouseClicked

    private void tblDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoctorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDoctorMouseClicked

    private void panel_viewProviderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_viewProviderMouseEntered
        setColorPanelButton(panel_viewProvider);
    }//GEN-LAST:event_panel_viewProviderMouseEntered

    private void panel_viewProviderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_viewProviderMouseExited
        resetColorPanelButton(panel_viewProvider);
    }//GEN-LAST:event_panel_viewProviderMouseExited

    private void lblDoctorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoctorMouseEntered
        lblDoctor.setBackground(new Color(255, 255, 224));
    }//GEN-LAST:event_lblDoctorMouseEntered

    private void lblDoctorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoctorMouseExited
        lblDoctor.setBackground(new Color(240,240,240));
    }//GEN-LAST:event_lblDoctorMouseExited

    private void lblPatientMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPatientMouseEntered
        lblPatient.setBackground(new Color(255, 255, 224));
    }//GEN-LAST:event_lblPatientMouseEntered

    private void lblPatientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPatientMouseExited
        lblPatient.setBackground(new Color(240,240,240));
    }//GEN-LAST:event_lblPatientMouseExited

    private void lblTreatmentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTreatmentMouseEntered
        lblTreatment.setBackground(new Color(255, 255, 224));
    }//GEN-LAST:event_lblTreatmentMouseEntered

    private void lblTreatmentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTreatmentMouseExited
        lblTreatment.setBackground(new Color(240,240,240));
    }//GEN-LAST:event_lblTreatmentMouseExited

    FrmSearchDoctor frmFDoctor = new FrmSearchDoctor();
    
    private void lblDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoctorMouseClicked
//        showPanelInCard(panel_hFind, panel_findDoctor);
        lblDoctorMouseExited(evt);
        
        frmFDoctor.setVisible(true);
    }//GEN-LAST:event_lblDoctorMouseClicked

    FrmSearchPatient frmFPatient = new FrmSearchPatient();
            
    private void lblPatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPatientMouseClicked
//        showPanelInCard(panel_hFind, panel_findPatient);

        lblPatientMouseExited(evt);
        
        frmFPatient.setVisible(true);
    }//GEN-LAST:event_lblPatientMouseClicked

    FrmSearchTreatmentMedicine frmFTreatment = new FrmSearchTreatmentMedicine();
    
    private void lblTreatmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTreatmentMouseClicked
//        showPanelInCard(panel_hFind, panel_findTreatment);
        lblTreatmentMouseExited(evt);
        
        frmFTreatment.setVisible(true);
    }//GEN-LAST:event_lblTreatmentMouseClicked

    private void lblBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseEntered
        lblBack.setText("<html><u>Back to option</u></html>");
    }//GEN-LAST:event_lblBackMouseEntered

    private void lblBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseExited
        lblBack.setText("Back to option");
    }//GEN-LAST:event_lblBackMouseExited

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        showPanelInCard(panel_hFind, panel_findOption);
    }//GEN-LAST:event_lblBackMouseClicked

    private void lblBack1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack1MouseClicked
        showPanelInCard(panel_hFind, panel_findOption);
    }//GEN-LAST:event_lblBack1MouseClicked

    private void lblBack1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack1MouseEntered
        lblBack1.setText("<html><u>Back to option</u></html>");
    }//GEN-LAST:event_lblBack1MouseEntered

    private void lblBack1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack1MouseExited
        lblBack1.setText("Back to option");
    }//GEN-LAST:event_lblBack1MouseExited

    private void lblBack2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack2MouseClicked
        showPanelInCard(panel_hFind, panel_findOption);
    }//GEN-LAST:event_lblBack2MouseClicked

    private void lblBack2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack2MouseEntered
        lblBack2.setText("<html><u>Back to option</u></html>");
    }//GEN-LAST:event_lblBack2MouseEntered

    private void lblBack2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack2MouseExited
        lblBack2.setText("Back to option");
    }//GEN-LAST:event_lblBack2MouseExited
        
    private void lblLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginMouseClicked
        this.setVisible(false);
        
        frmLogin.setVisible(true);
    }//GEN-LAST:event_lblLoginMouseClicked
       
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new WelcomeForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbSearchDoctor;
    private javax.swing.JComboBox<String> cbSearchHP;
    private javax.swing.JComboBox<String> cbSearchIns;
    private javax.swing.JComboBox<String> cbSearchPlan;
    private javax.swing.JComboBox<String> cmbDisease;
    private javax.swing.JComboBox<String> cmbProvider;
    private javax.swing.JPanel indAbout;
    private javax.swing.JPanel indDashboard;
    private javax.swing.JPanel indDoctor;
    private javax.swing.JPanel indFind;
    private javax.swing.JPanel indHP;
    private javax.swing.JPanel indHealthcarePlan;
    private javax.swing.JPanel indHelp;
    private javax.swing.JPanel indInsurance;
    private javax.swing.JPanel indStatistic;
    private javax.swing.JPanel indUpdate;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lblAbout;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblBack1;
    private javax.swing.JLabel lblBack2;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblDoctor;
    private javax.swing.JLabel lblFind;
    private javax.swing.JLabel lblHealthcarePro;
    private javax.swing.JLabel lblHealthcarePro1;
    private javax.swing.JLabel lblHealthcarePro2;
    private javax.swing.JLabel lblHelp;
    private javax.swing.JLabel lblInsuranceCom;
    private javax.swing.JLabel lblInsuranceCom2;
    private javax.swing.JLabel lblLock;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPatient;
    private javax.swing.JLabel lblSearchHP1;
    private javax.swing.JLabel lblSearchHP2;
    private javax.swing.JLabel lblSearchHP3;
    private javax.swing.JLabel lblSearchHP4;
    private javax.swing.JLabel lblStatistic;
    private javax.swing.JLabel lblTreatment;
    private javax.swing.JLabel lblUpdate;
    private javax.swing.JPanel panel_about;
    private javax.swing.JPanel panel_dashboard;
    private javax.swing.JPanel panel_doctor;
    private javax.swing.JPanel panel_find;
    private javax.swing.JPanel panel_findDoctor;
    private javax.swing.JPanel panel_findOption;
    private javax.swing.JPanel panel_findPatient;
    private javax.swing.JPanel panel_findTreatment;
    private javax.swing.JPanel panel_hAbout;
    private javax.swing.JPanel panel_hDashboard;
    private javax.swing.JPanel panel_hDoctor;
    private javax.swing.JPanel panel_hDoctorMain;
    private javax.swing.JPanel panel_hFind;
    private javax.swing.JPanel panel_hHPlanMain;
    private javax.swing.JPanel panel_hHealthcarePlan;
    private javax.swing.JPanel panel_hHealthcarePro;
    private javax.swing.JPanel panel_hHelp;
    private javax.swing.JPanel panel_hInsMain;
    private javax.swing.JPanel panel_hInsurance_com;
    private javax.swing.JPanel panel_hStatistic;
    private javax.swing.JPanel panel_hUpdates;
    private javax.swing.JPanel panel_healthcarePlan;
    private javax.swing.JPanel panel_healthcarePro;
    private javax.swing.JPanel panel_help;
    private javax.swing.JPanel panel_home;
    private javax.swing.JPanel panel_insurance_com;
    private javax.swing.JPanel panel_left;
    private javax.swing.JPanel panel_login;
    private javax.swing.JPanel panel_main;
    private javax.swing.JPanel panel_statistic;
    private javax.swing.JPanel panel_titlebar;
    private javax.swing.JPanel panel_update;
    private javax.swing.JPanel panel_viewDoctor;
    private javax.swing.JPanel panel_viewDoctor2;
    private javax.swing.JPanel panel_viewDoctor3;
    private javax.swing.JPanel panel_viewProvider;
    private javax.swing.JScrollPane scpMain;
    private javax.swing.JTable tblDoctor;
    private javax.swing.JTable tblHP;
    private javax.swing.JTable tblInsCom;
    private javax.swing.JTable tblPlan;
    private javax.swing.JTextField tfSearchDoctor;
    private javax.swing.JTextField tfSearchHP;
    private javax.swing.JTextField tfSearchInsCom;
    private javax.swing.JTextField tfSearchPlan;
    // End of variables declaration//GEN-END:variables
}
