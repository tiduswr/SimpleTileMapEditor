package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;

public class SpriteSheet extends javax.swing.JPanel {

    private int qtdItens;
    private int tileSize = 32;
    private int tileSizeSpriteSheet = 16;
    private BufferedImage spriteSheet;
    private int rows, cols;
    private BufferedImage[][] tiles;
    private TileSettings selected = null;
    
    public SpriteSheet() {
        loadSpriteSheet("tiles01.png");
        
        initComponents();
        this.setBackground(Color.BLACK);
        
        qtdItens = 10;
        drawGrid();
        
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setSize(new Dimension(640, 64));
    }
    
    private void loadSpriteSheet(String sheetName){
        BufferedImageLoader loader = new BufferedImageLoader();
        spriteSheet = loader.loadImage(sheetName);
        rows = spriteSheet.getWidth()/tileSizeSpriteSheet;
        cols = spriteSheet.getHeight()/tileSizeSpriteSheet;
        tiles = new BufferedImage[rows][cols];
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                SpriteSheetLoader sheetLoader = new SpriteSheetLoader(spriteSheet, tileSizeSpriteSheet);
                tiles[i][j] = sheetLoader.grabImage(i+1, j+1, tileSizeSpriteSheet, tileSizeSpriteSheet);
            }
        }
    }
    
    private void drawGrid(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                SpriteSheetLoader loader = new SpriteSheetLoader(spriteSheet, tileSizeSpriteSheet);
                TileButton button = new TileButton(tiles[i][j], i, j, this);
                button.setMinimumSize(new Dimension(64, 64));
                button.setPreferredSize(new Dimension(64, 64));
                button.setMaximumSize(new Dimension(64, 64));
                this.add(button);
            }
        }
        revalidate();
    }
    
    public TileSettings getImage(int row, int col){
        return new TileSettings(tiles[row][col], row, col, cols);
    }

    public TileSettings getSelected() {
        return selected;
    }

    public void setSelected(TileSettings selected) {
        this.selected = selected;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
