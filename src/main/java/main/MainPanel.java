package main;

import filehandler.StoreData;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import spritesheet.BufferedImageLoader;
import spritesheet.SpriteSheet;
import tilemap.SelectedArea;
import tilemap.TileFrame;
import toolbar.Observable;
import toolbar.Observer;
import toolbar.ToolBar;

public final class MainPanel extends javax.swing.JFrame implements Observer{
    
    private int tileMapSize = 50, spriteSize = 16;
    private File spriteSheetFile;
    private BufferedImage spriteSheetLoadedFromProject = null;
    private TileFrame tfConfig;
    
    public MainPanel() {
        tfConfig = new TileFrame(tileMapSize, spriteSize);
        initComponents();
        setIconToMainPanel();
        startConfigs();
        tileFrame.setSheet(spriteSheet);
        setupTileFrame();
    }

    public MainPanel(int tileMapSize, int spriteSize, File spriteSheet) {
        this.tileMapSize = tileMapSize;
        this.spriteSize = spriteSize;
        this.spriteSheetFile = spriteSheet;
        tfConfig = new TileFrame(tileMapSize, spriteSize);
        
        initComponents();
        setIconToMainPanel();
        setupTileFrame();
        
        //Start SpriteSheet
        this.spriteSheet = new SpriteSheet(this, spriteSheetFile.getAbsolutePath());
        this.jScrollPane1.setViewportView(this.spriteSheet);
        tileFrame.setSheet(this.spriteSheet);
        
        startConfigs();
    }
    
    public MainPanel(StoreData data) throws IOException {
        tfConfig = new TileFrame(data.getTileMapSize(), data.getSpriteSize(), data.getMapCodes());
        initComponents();
        setIconToMainPanel();
        
        this.tileMapSize = data.getTileMapSize();
        this.spriteSize = data.getSpriteSize();
        this.spriteSheetFile = null;
        this.spriteSheetLoadedFromProject = data.getSpriteSheet();
        setupTileFrame();
        
        //Start SpriteSheet
        this.spriteSheet = new spritesheet.SpriteSheet(this, spriteSheetLoadedFromProject);
        this.jScrollPane1.setViewportView(this.spriteSheet);
        tileFrame.setSheet(this.spriteSheet);
        
        startConfigs();
    }
    
    private void setIconToMainPanel(){
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage buff = loader.loadImage("menu/icon.png");
        this.setIconImage(buff);
        this.setTitle("Simple Tile Map Editor");
    }
    
    private void startConfigs(){
        generalSetup();
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
                if(code == KeyEvent.VK_ESCAPE){
                    System.out.println("ESC");
                    tileFrame.resetSelectedArea();
                    tileFrame.selectionVisible(false);
                }
                repaint();
            }
        });
        tileFrame.requestFocus();
        setMapInfo();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void generalSetup(){
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        spriteSheetFile = new File(getClass().getResource("/tiles01.png").getPath());
        toolBar.setMainPanel(this);
        toolBar.createTools();
        selectedArea.setMainPanel(this);
    }
    
    private void setupTileFrame(){
        tileFrame.setMainPanel(this);
        tileFrame.startSelectionHandler();
    }
    
    public void setMapInfo(){
        String info = "Map Size: ("+ tileFrame.getTileMapSize() + "x" + tileFrame.getTileMapSize() + 
                ") / Tile Size of Sprite Sheet: " + tileFrame.getTileSizeSpriteSheet();
        this.projectInfoLabel.setText(info);
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

    public BufferedImage getSpriteSheetLoadedFromProject() {
        return spriteSheetLoadedFromProject;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        tileFrame = tfConfig;
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        selectedItemView = new javax.swing.JPanel();
        lblSprite = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblCode = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        toolBar = new toolbar.ToolBar();
        projectInfo = new javax.swing.JPanel();
        projectInfoLabel = new javax.swing.JLabel();
        selectedArea = new tilemap.SelectedArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        mainPanel.setBackground(new java.awt.Color(0, 0, 0));

        tileFrame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tileFrame.setMaximumSize(new java.awt.Dimension(480, 480));
        tileFrame.setMinimumSize(new java.awt.Dimension(480, 480));
        tileFrame.setPreferredSize(new java.awt.Dimension(480, 480));

        javax.swing.GroupLayout tileFrameLayout = new javax.swing.GroupLayout(tileFrame);
        tileFrame.setLayout(tileFrameLayout);
        tileFrameLayout.setHorizontalGroup(
            tileFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        tileFrameLayout.setVerticalGroup(
            tileFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SPRITE SHEET:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SPRITE MAP:");

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setFocusable(false);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        projectInfo.setBackground(new java.awt.Color(0, 0, 0));
        projectInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        projectInfoLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        projectInfoLabel.setForeground(new java.awt.Color(255, 255, 255));
        projectInfoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        projectInfoLabel.setText("Map Size: (XXxXX) / Tile Size of Sprite Sheet: XX");

        javax.swing.GroupLayout projectInfoLayout = new javax.swing.GroupLayout(projectInfo);
        projectInfo.setLayout(projectInfoLayout);
        projectInfoLayout.setHorizontalGroup(
            projectInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projectInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(projectInfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                .addContainerGap())
        );
        projectInfoLayout.setVerticalGroup(
            projectInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projectInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(projectInfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(projectInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tileFrame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectedItemView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                            .addComponent(selectedArea, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectedItemView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(projectInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tileFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectedArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
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

    //Other variables
    private SpriteSheet spriteSheet;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCode;
    private javax.swing.JLabel lblSprite;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel projectInfo;
    private javax.swing.JLabel projectInfoLabel;
    private tilemap.SelectedArea selectedArea;
    private javax.swing.JPanel selectedItemView;
    private tilemap.TileFrame tileFrame;
    private toolbar.ToolBar toolBar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void notify(Observable o) {
        o.update(this);
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
    
    public SelectedArea getSelectedAreaFrame(){
        return this.selectedArea;
    }
    
}
