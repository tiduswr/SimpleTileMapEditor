package main;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import spritesheet.SpriteSheet;
import tilemap.TileFrame;
import toolbar.Observable;
import toolbar.Observer;
import toolbar.ToolBar;

public final class MainPanel extends javax.swing.JFrame implements Observer{
        
    public MainPanel() {
        initComponents();
        setup();
        tileFrame.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if(code == KeyEvent.VK_W){
                    tileFrame.getMapTile().down();
                }
                if(code == KeyEvent.VK_S){
                    tileFrame.getMapTile().up();
                }
                if(code == KeyEvent.VK_A){
                    tileFrame.getMapTile().right();
                }
                if(code == KeyEvent.VK_D){
                    tileFrame.getMapTile().left();
                }
                repaint();
            }
        });
        tileFrame.requestFocus();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void setup(){
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        tileFrame.setSheet(spriteSheet);
        tileFrame.setMainPanel(this);
        spriteSheet.setMainFrame(this);
        toolBar.setMainPanel(this);
        toolBar.createTools();
    }
    
    public void updateSelectedItem(){
        if(spriteSheet.getSelected().getImg() != null){
            lblCode.setText(String.valueOf(spriteSheet.getSelected().getCode()));
            BufferedImage img = spriteSheet.getSelected().getImg();
            Image tmp = img.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(32, 2, BufferedImage.TYPE_INT_ARGB);
            lblSprite.setIcon(new ImageIcon(tmp));
        }else{
            lblCode.setText("???");
            lblSprite.setIcon(null);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        tileFrame = new tilemap.TileFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        spriteSheet = new spritesheet.SpriteSheet();
        btGrid = new javax.swing.JButton();
        selectedItemView = new javax.swing.JPanel();
        lblSprite = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblCode = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        toolBar = new toolbar.ToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(0, 0, 0));

        tileFrame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tileFrame.setMaximumSize(new java.awt.Dimension(480, 480));
        tileFrame.setMinimumSize(new java.awt.Dimension(480, 480));
        tileFrame.setPreferredSize(new java.awt.Dimension(480, 480));

        javax.swing.GroupLayout tileFrameLayout = new javax.swing.GroupLayout(tileFrame);
        tileFrame.setLayout(tileFrameLayout);
        tileFrameLayout.setHorizontalGroup(
            tileFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );
        tileFrameLayout.setVerticalGroup(
            tileFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SPRITE SHEET:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SPRITE MAP:");

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setFocusable(false);
        jScrollPane1.setViewportView(spriteSheet);

        btGrid.setText("Grid OFF");
        btGrid.setFocusable(false);
        btGrid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGridActionPerformed(evt);
            }
        });

        selectedItemView.setBackground(new java.awt.Color(0, 0, 0));
        selectedItemView.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        lblSprite.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblSprite.setForeground(new java.awt.Color(255, 255, 255));
        lblSprite.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lblSprite.setOpaque(true);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tile:");

        lblCode.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblCode.setForeground(new java.awt.Color(255, 255, 255));
        lblCode.setText("???");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Sprite Selected -> Code:");

        javax.swing.GroupLayout selectedItemViewLayout = new javax.swing.GroupLayout(selectedItemView);
        selectedItemView.setLayout(selectedItemViewLayout);
        selectedItemViewLayout.setHorizontalGroup(
            selectedItemViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectedItemViewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSprite, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        selectedItemViewLayout.setVerticalGroup(
            selectedItemViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectedItemViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(selectedItemViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(selectedItemViewLayout.createSequentialGroup()
                        .addGroup(selectedItemViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(lblSprite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tileFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGrid)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(selectedItemView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btGrid))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tileFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(selectedItemView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGridActionPerformed
        if(tileFrame.getDrawGridOn()){
            tileFrame.setDrawGridOn(false);
            btGrid.setText("Grid OFF");
        }else{
            tileFrame.setDrawGridOn(true);
            btGrid.setText("Grid ON");
        }
        repaint();
    }//GEN-LAST:event_btGridActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new MainPanel().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGrid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCode;
    private javax.swing.JLabel lblSprite;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel selectedItemView;
    private spritesheet.SpriteSheet spriteSheet;
    private tilemap.TileFrame tileFrame;
    private toolbar.ToolBar toolBar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o) {
        o.updateSelectedTile(this);
    }
    
    public SpriteSheet getSpriteSheetPanel(){
        return spriteSheet;
    }
    
    public TileFrame getTileFrame(){
        return tileFrame;
    }
    
    public ToolBar getToolBar(){
        return toolBar;
    }
    
}