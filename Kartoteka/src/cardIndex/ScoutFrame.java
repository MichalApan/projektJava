/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardIndex;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import model.Badge;
import model.GainedBadge;
import model.GainedRank;
import model.GainedRank.stat;
import model.Scout;
import model.ScoutRank;

/**
 *
 * @author Michał
 */
public final class ScoutFrame extends JFrame implements WindowListener, TableModelListener {

    private final CardIndex k;
    private final Scout s;
    private final JComboBox cb = new JComboBox();

    /**
     * Creates new form ScoutFrame
     *
     * @param k CardIndex class
     * @param s Scout class
     */
    public ScoutFrame(CardIndex k, Scout s) {
        this.s = s;
        this.k = k;

        initComponents();
        writeData();

        jTabbedPane1.addChangeListener((ChangeEvent e) -> {
            listInit();
        });
    }

    void writeData() {
        addWindowListener(this);
        jLabelPesel.setText("" + s.getPesel());
        jLabelName.setText(s.getName());
        jLabelSurname.setText(s.getSurname());
        ScoutRank rank = translateIdToRank(s.getScoutRankId());
        jLabelRank.setText("" + rank.getTitle());

        List<ScoutRank> l = k.selectScoutRank();
        DefaultListModel listModel = new DefaultListModel();
        for (ScoutRank b : l) {
            listModel.addElement(b.getTitle());
        }
        jList1.setModel(listModel);
    }

    private ScoutRank translateIdToRank(int id) {

        List<ScoutRank> l = k.selectScoutRank();
        for (ScoutRank sr : l) {
            if (sr.getScoutRankId() == id) {
                return sr;
            }
        }
        return null;
    }

    private Badge translateIdToBadge(int id) {

        List<Badge> l = k.selectBadges();
        for (Badge sr : l) {
            if (sr.getId() == id) {
                return sr;
            }
        }
        return null;
    }

    private String translateStatus(int s) {
        stat[] val = GainedRank.stat.values();
        return val[s].toString();
    }

    private void gainedRanksTableInit() {
        DefaultTableModel dtmGainedRanks = new DefaultTableModel();
        dtmGainedRanks.addTableModelListener(this);
        jTable1.setModel(dtmGainedRanks);

        String[] columnsGainedRanks = {"Status", "Stopień", "Opiekun", "Numer Rozkazu", "Data"};
        setColumns(dtmGainedRanks, columnsGainedRanks);

        List<GainedRank> grList = k.selectGainedRank();

        for (int i = 0; i < grList.size(); i++) {
            GainedRank gr = grList.get(i);
            if (gr.getPesel() == s.getPesel()) {
                Vector<String> v = new Vector<>();
                v.addElement("" + translateStatus(gr.getS()));
                ScoutRank rank = translateIdToRank(gr.getScoutRankId());
                v.addElement("" + rank.getTitle());
                v.addElement("" + gr.getSupervisor());
                v.addElement("" + gr.getCommandNumber());
                v.addElement("" + gr.getDate());
                dtmGainedRanks.addRow(v);
            }
        }

        TableColumn statColumn = jTable1.getColumnModel().getColumn(0);
        cb.removeAllItems();
        stat[] statList = GainedRank.stat.values();
        for (stat st : statList) {
            cb.addItem(st.name());
        }
        statColumn.setCellEditor(new DefaultCellEditor(cb));
    }

    private void gainedBadgeTableInit() {
        DefaultTableModel dtmGainedBadge = new DefaultTableModel();
        dtmGainedBadge.addTableModelListener(this);
        jTable2.setModel(dtmGainedBadge);

        String[] columnsGainedBadge = {"Nazwa", "Numer Rozkazu", "Data"};
        setColumns(dtmGainedBadge, columnsGainedBadge);

        List<GainedBadge> grList = k.selectGainedBadge();

        for (int i = 0; i < grList.size(); i++) {
            GainedBadge gr = grList.get(i);
            if (gr.getPesel() == s.getPesel()) {
                Vector<String> v = new Vector<>();
                Badge b = translateIdToBadge(gr.getBadgeId());
                v.addElement("" + b.getTitle());
                v.addElement("" + gr.getCommandNumber());
                v.addElement("" + gr.getDate());
                dtmGainedBadge.addRow(v);
            }
        }
    }

    Badge badgeTitleToId(String name) {
        List<Badge> l = k.selectBadges();
        for (Badge b : l) {
            if (b.getTitle().equals(name)) {
                return b;
            }
        }
        return null;
    }

    ScoutRank rankTitleToId(String name) {
        List<ScoutRank> l = k.selectScoutRank();
        for (ScoutRank b : l) {
            if (b.getTitle().equals(name)) {
                return b;
            }
        }
        return null;
    }

    private void setColumns(DefaultTableModel tbm, String[] columns) {
        for (String column : columns) {
            tbm.addColumn(column);
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
        jLabel5 = new javax.swing.JLabel();
        jTextFieldNumber = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldDate = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jTextFieldSupervisor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButtonDelete = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelPesel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelSurname = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelRank = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Informacje");
        setAlwaysOnTop(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Numer Rozkazu");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 126, -1));

        jTextFieldNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNumberActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 31, 126, -1));

        jLabel6.setText("Data (DD/MM/RRRR)");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 62, 126, -1));

        jTextFieldDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDateActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 82, 126, -1));

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 10, 150, 190));

        jTextFieldSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSupervisorActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldSupervisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 128, 126, -1));

        jLabel7.setText("Opiekun");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 108, 126, -1));

        jButtonAdd.setText("Dodaj");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 155, 126, -1));

        jButtonDelete.setText("Usuń");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 180, 60, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("PESEL:");

        jLabelPesel.setText("jLabel");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Imię:");

        jLabelName.setText("jLabel");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nazwisko:");

        jLabelSurname.setText("jLabel");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Stopień:");

        jLabelRank.setText("jLabel");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPesel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelSurname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelRank, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                        .addGap(77, 77, 77))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPesel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSurname)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRank)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });
        jTabbedPane1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jTabbedPane1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jTabbedPane1.addTab("Sprawności", jScrollPane2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jTabbedPane1.addTab("Stopnie", jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        listInit();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    void listInit() {
        if (jTabbedPane1.getSelectedIndex() == 1) {
            List<ScoutRank> l = k.selectScoutRank();
            DefaultListModel listModel = new DefaultListModel();
            for (ScoutRank b : l) {
                listModel.addElement(b.getTitle());
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel();
            for (stat b : GainedRank.stat.values()) {
                model.addElement(b.name());
            }
            jComboBox1.setModel(model);

            jList1.setModel(listModel);
            jTextFieldSupervisor.setVisible(true);
            jComboBox1.setVisible(true);
            jLabel7.setVisible(true);
        }
        if (jTabbedPane1.getSelectedIndex() == 0) {
            List<Badge> l = k.selectBadges();
            DefaultListModel listModel = new DefaultListModel();
            for (Badge b : l) {
                listModel.addElement(b.getTitle());
            }
            jList1.setModel(listModel);
            jTextFieldSupervisor.setVisible(false);
            jComboBox1.setVisible(false);
            jLabel7.setVisible(false);
        }
    }
    private void jTabbedPane1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTabbedPane1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1CaretPositionChanged

    private void jTextFieldDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDateActionPerformed

    private void jTextFieldNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNumberActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        try {
            int index = jTabbedPane1.getSelectedIndex();
            if (index == 1) {
                int e = jComboBox1.getSelectedIndex();
                ScoutRank r = rankTitleToId(jList1.getSelectedValue());
                String number = jTextFieldNumber.getText();
                String date = jTextFieldDate.getText();
                String supervisor = jTextFieldSupervisor.getText();
                k.insertGainedRank(s.getPesel(), r.getScoutRankId(), e, supervisor, date, number);
            }
            if (index == 0) {
                String number = jTextFieldNumber.getText();
                String date = jTextFieldDate.getText();
                Badge b = badgeTitleToId(jList1.getSelectedValue());
                k.insertGainedBadge(s.getPesel(), b.getId(), number, date);
            }
        } catch (Exception e) {
               
        }
        gainedBadgeTableInit();
        gainedRanksTableInit();
        listInit();

    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jTextFieldSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSupervisorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSupervisorActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        int pesel = s.getPesel();
        switch (jTabbedPane1.getSelectedIndex()) {
            case 0: {
                int row = jTable2.getSelectedRow();
                String title = jTable2.getValueAt(row, 0).toString();
                Badge b = badgeTitleToId(title);
                int badgeId = b.getId();
                if (row >= 0) {
                    k.deleteGainedBadge(pesel, badgeId);
                }
                break;
            }
            case 1: {
                int row = jTable1.getSelectedRow();
                String title = jTable1.getValueAt(row, 1).toString();
                ScoutRank sr = rankTitleToId(title);
                int rankId = sr.getScoutRankId();
                if (row >= 0) {
                    k.deleteGainedRank(pesel, rankId);
                }
                break;
            }
            default:
                break;
        }
        gainedBadgeTableInit();
        gainedRanksTableInit();
        listInit();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Windows look and feel */
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
            java.util.logging.Logger.getLogger(ScoutFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }
        //</editor-fold>

        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPesel;
    private javax.swing.JLabel jLabelRank;
    private javax.swing.JLabel jLabelSurname;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldDate;
    private javax.swing.JTextField jTextFieldNumber;
    private javax.swing.JTextField jTextFieldSupervisor;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
        gainedBadgeTableInit();
        gainedRanksTableInit();
        listInit();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel) e.getSource();

        if (row >= 0 && column == 0) {
            String table = null;
            String columnName = null;
            String id = null;
            int scoutRankID = 0;
            Object data = cb.getSelectedIndex();
            int pesel = s.getPesel();
            switch (jTabbedPane1.getSelectedIndex()) {
                case 0:
                    break;
                case 1:
                    table = "gainedRank";
                    columnName = "status";
                    String scoutRankTitle = model.getValueAt(row, 1).toString();
                    List<ScoutRank> l = k.selectScoutRank();
                    for (ScoutRank sr : l) {
                        if ((sr.getTitle()).equals(scoutRankTitle)) {
                            scoutRankID = sr.getScoutRankId();
                        }
                    }
                    id = "scoutRankID = " + scoutRankID + " AND pesel ";
                    break;
                default:
                    break;
            }
            k.update(pesel, table, columnName, id, data.toString());
        }
    }
}
