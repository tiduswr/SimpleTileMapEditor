package projectmanager;

import filehandler.FileTypeFilter;
import filehandler.StoreData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import main.ProjectManager;

public class LoadProject extends javax.swing.JPanel {
    
    private ProjectManager pm;
    private File storeData;
    
    public LoadProject(ProjectManager pm) {
        this.pm = pm;
        initComponents();
        setup();
    }
    
    private void setup(){
        btFind.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fs = new JFileChooser(System.getProperty("user.home"));
                FileTypeFilter filter = new FileTypeFilter(".tmap", "Tile Map File");
                
                fs.setFileFilter(filter);
                int response = fs.showOpenDialog(pm);
                if(response == JFileChooser.APPROVE_OPTION){
                    storeData = fs.getSelectedFile();
                    txtTileMapPath.setText(storeData.getAbsolutePath());
                }else{
                    storeData = null;
                    txtTileMapPath.setText("");
                }
            }
        });
        btLoad.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                pm.loadProject(getStoredData());
            }
        });
    }
    
    public StoreData getStoredData(){
        if(storeData != null){
            FileInputStream fis = null;
            try {
                
                fis = new FileInputStream(storeData.getAbsolutePath());
                ObjectInputStream ois = new ObjectInputStream(fis);
                return (StoreData) ois.readObject();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoadProject.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao tentar ler o arquivo");
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(LoadProject.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao tentar ler o arquivo");
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(LoadProject.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Erro ao tentar ler o arquivo");
                }
            }
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTileMapPath = new javax.swing.JTextField();
        btFind = new javax.swing.JButton();
        btLoad = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Load Project:");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Project Location:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tile Map");

        txtTileMapPath.setEditable(false);
        txtTileMapPath.setBackground(new java.awt.Color(0, 0, 0));
        txtTileMapPath.setForeground(new java.awt.Color(255, 255, 255));
        txtTileMapPath.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btFind.setBackground(new java.awt.Color(0, 0, 0));
        btFind.setForeground(new java.awt.Color(255, 255, 255));
        btFind.setText("Find");
        btFind.setBorder(null);

        btLoad.setBackground(new java.awt.Color(0, 0, 0));
        btLoad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btLoad.setForeground(new java.awt.Color(51, 255, 51));
        btLoad.setText("Load");
        btLoad.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTileMapPath, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btFind, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTileMapPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFind, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(btLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFind;
    private javax.swing.JButton btLoad;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtTileMapPath;
    // End of variables declaration//GEN-END:variables
}
