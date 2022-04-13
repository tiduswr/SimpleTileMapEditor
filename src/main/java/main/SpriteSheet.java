package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpriteSheet extends javax.swing.JPanel {

    private int qtdItens;
    private int tileSize = 32;
    private int tileSizeSpriteSheet = 16;
    private BufferedImage spriteSheet;
    private int rows, cols;
    private BufferedImage[][] tiles;
    private BufferedImage[] tilesByIndex;
    private TileSettings selected = null;
    private MainPanel mainFrame = null;
    
    public SpriteSheet() {
        loadSpriteSheet("tiles01.png");
        
        initComponents();
        this.setBackground(Color.BLACK);
        
        qtdItens = 10;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        drawGrid();
    }
    
    private void loadSpriteSheet(String sheetName){
        BufferedImageLoader loader = new BufferedImageLoader();
        spriteSheet = loader.loadImage(sheetName);
        rows = spriteSheet.getWidth()/tileSizeSpriteSheet;
        cols = spriteSheet.getHeight()/tileSizeSpriteSheet;
        tiles = new BufferedImage[rows][cols];
        tilesByIndex = new BufferedImage[rows*cols];
        
        int count = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                SpriteSheetLoader sheetLoader = new SpriteSheetLoader(spriteSheet, tileSizeSpriteSheet);
                tiles[i][j] = sheetLoader.grabImage(i+1, j+1, tileSizeSpriteSheet, tileSizeSpriteSheet);
                tilesByIndex[count] = tiles[i][j];
                count++;
            }
        }
    }
    
    private void drawGrid(){
        int count = 0;
        JPanel panel = null;
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(count == 6 || panel == null){
                    if(panel != null) this.add(panel);
                    panel = new JPanel();
                    count = 0;
                }else if(count > 0){
                    this.add(panel);
                }
                 
                panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
                SpriteSheetLoader loader = new SpriteSheetLoader(spriteSheet, tileSizeSpriteSheet);
                TileButton button = new TileButton(tiles[i][j], i, j, this);
                button.setMinimumSize(new Dimension(64, 64));
                button.setPreferredSize(new Dimension(64, 64));
                button.setMaximumSize(new Dimension(64, 64));
                panel.add(button);
                
                count++;
            }
        }
        revalidate();
    }
    
    public TileSettings getImage(int row, int col){
        return new TileSettings(tiles[row][col], row, col, cols);
    }
    
    public TileSettings getImageByCode(int code){
        return new TileSettings(tilesByIndex[code], code, cols);
    }
    
    public TileSettings getSelected() {
        return selected;
    }

    public void setSelected(TileSettings selected) {
        this.selected = selected;
    }
    
    public void updateSelectedItem(){
       mainFrame.updateSelectedItem();
    }
    
    public void setMainFrame(MainPanel fr){
        this.mainFrame = fr;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
