
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TileFrame extends javax.swing.JPanel {
    
    private int tileSize = 32;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private SpriteSheet sheet;
    private boolean drawGridOn = false;
    private boolean gridDrawned = false;
    
    public TileFrame() {
        initComponents();
        this.setBackground(Color.BLACK);
        
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                selectedRow = x/tileSize;
                selectedCol = y/tileSize;
                repaint();
                
            }
        });
        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                selectedRow = x/tileSize;
                selectedCol = y/tileSize;
                repaint();
            }
        });
    }
    
    public void setSheet(SpriteSheet s){
        this.sheet = s;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        drawOnGrid(g2);
        if(drawGridOn && !gridDrawned) {
            drawGrid(g2);
        }
        
        g2.dispose();
    }
    
    public void drawGrid(Graphics2D g2){
        //Draw grid
        Color oldColor = g2.getColor();
        g2.setColor(Color.white);
        
        int gridLength = tileSize*15;
        int row = 0;
        for(int i = 0; i < gridLength; i++){
            int col = 0;
            for(int j = 0; j < gridLength; j++){
                g2.drawRect(row, col, tileSize, tileSize);
                col += tileSize;
            }
            row += tileSize;
        }
        g2.setColor(oldColor);
        gridDrawned = true;
    }
    
    private void drawOnGrid(Graphics2D g2){
        if(selectedRow != -1 && selectedCol != -1){
            TileSettings tile = sheet.getSelected();
            if(tile != null){
                g2.drawImage(tile.getImg(), selectedRow*tileSize, selectedCol*tileSize, tileSize, tileSize, null);
                if(drawGridOn){
                    Color oldColor = g2.getColor();
                    g2.setColor(Color.white);
                    g2.drawRect(selectedRow*tileSize, selectedCol*tileSize, tileSize, tileSize);
                    g2.setColor(oldColor);
                }
            }
        }
    }
    
    public void setDrawGridOn(boolean value){
        this.drawGridOn = value;
    }
    
    public boolean getDrawGridOn(){
        return this.drawGridOn;
    }

    public boolean isGridDrawned() {
        return gridDrawned;
    }

    public void setGridDrawned(boolean gridDrawned) {
        this.gridDrawned = gridDrawned;
    }
    
    public int getTileSize() {
        return tileSize;
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
