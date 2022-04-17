package main;

import filehandler.StoreData;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import projectmanager.CreateProject;
import projectmanager.LoadProject;
import spritesheet.BufferedImageLoader;

public final class ProjectManager extends javax.swing.JFrame {
    
    public ProjectManager() {
        initComponents();
        configs();
    }
    
    private void configs(){
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Simple Tile Map Editor");
        
        //Set icons
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage buff = loader.loadImage("menu/icon.png");
        this.setIconImage(buff);
        lblIcon.setIcon(new ImageIcon(buff.getScaledInstance(64, 64, Image.SCALE_DEFAULT)));
        
        menuOptCreateProject.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                panelLayout.removeAll();
                panelLayout.add(new CreateProject(ProjectManager.this));
                revalidate();
                repaint();
            }
        });
        menuOptLoadProject.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                panelLayout.removeAll();
                panelLayout.add(new LoadProject(ProjectManager.this));
                revalidate();
                repaint();
            }
        });
    }
    
    public void createNewProject(Integer tileMapSize, Integer spriteSize, File spriteSheet){
        if(checkIntegrityProject(tileMapSize, spriteSize, spriteSheet)){
            MainPanel mp = new MainPanel(tileMapSize, spriteSize, spriteSheet);
            mp.setVisible(true);
            this.dispose();
        }
    }
    
    public boolean checkIntegrityProject(Integer tileMapSize, Integer spriteSize, File spriteSheet){
        return tileMapSize != null && tileMapSize > 0 && spriteSize != null && spriteSize > 0 && spriteSheet != null;
    }
    
    public void loadProject(StoreData data){
        if(data != null){
            MainPanel mp;
            try {
                mp = new MainPanel(data);
                mp.setVisible(true);
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao tentar ler o arquivo");
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBackground = new javax.swing.JPanel();
        panelHeader = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblIcon = new javax.swing.JLabel();
        panelLayout = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        menuBarOptions = new javax.swing.JMenu();
        menuOptCreateProject = new javax.swing.JMenuItem();
        menuOptLoadProject = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBackground.setBackground(new java.awt.Color(0, 0, 0));

        panelHeader.setBackground(new java.awt.Color(0, 0, 0));
        panelHeader.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        panelHeader.setOpaque(false);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Simple Tile Map Editor");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(lblIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelLayout.setBackground(new java.awt.Color(0, 0, 0));
        panelLayout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        panelLayout.setOpaque(false);
        panelLayout.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout panelBackgroundLayout = new javax.swing.GroupLayout(panelBackground);
        panelBackground.setLayout(panelBackgroundLayout);
        panelBackgroundLayout.setHorizontalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBackgroundLayout.setVerticalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLayout, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuBarOptions.setText("Options");

        menuOptCreateProject.setText("Create New Project");
        menuBarOptions.add(menuOptCreateProject);

        menuOptLoadProject.setText("Load Project");
        menuBarOptions.add(menuOptLoadProject);

        menuBar.add(menuBarOptions);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ProjectManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ProjectManager().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuBarOptions;
    private javax.swing.JMenuItem menuOptCreateProject;
    private javax.swing.JMenuItem menuOptLoadProject;
    private javax.swing.JPanel panelBackground;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelLayout;
    // End of variables declaration//GEN-END:variables
}
