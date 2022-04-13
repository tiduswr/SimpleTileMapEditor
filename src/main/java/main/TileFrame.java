
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class TileFrame extends javax.swing.JPanel {
    
    private int tileSize = 32;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private SpriteSheet sheet;
    private boolean drawGridOn = false;
    private MapTile mapTile;
    
    public TileFrame() {
        initComponents();
        this.setBackground(Color.BLACK);
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getPoint().x;
                int y = e.getPoint().y;
                selectedRow = x/tileSize;
                selectedCol = y/tileSize;
                
                TileSettings tile = sheet.getSelected();
                if(tile != null){
                    mapTile.setCode(sheet.getSelected().getCode(), selectedRow + mapTile.getX(), selectedCol + mapTile.getY());
                    repaint();
                }
            }
        });
        this.addMouseWheelListener(new MouseAdapter(){
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {           
                
                if(tileSize > 0 && (tileSize - e.getWheelRotation()) > 0){
                    tileSize -= e.getWheelRotation();
                }
                if(mapTile.lengthRows()*tileSize < getHeight() || mapTile.lengthCols()*tileSize < getWidth()){
                    mapTile.setPosition(0, 0);
                }
                System.out.println(tileSize);
                repaint();
            }
        });
        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getPoint().x;
                int y = e.getPoint().y;
                selectedRow = x/tileSize;
                selectedCol = y/tileSize;
                
                TileSettings tile = sheet.getSelected();
                if(tile != null){
                    mapTile.setCode(sheet.getSelected().getCode(), selectedRow + mapTile.getX(), selectedCol + mapTile.getY());
                    repaint();
                }
                repaint();
            }
        });
        createMapTile(50, 50);
    }
    
    public void createMapTile(int rows, int cols){
        mapTile = new MapTile(rows, cols, this);
    }
    
    public void setSheet(SpriteSheet s){
        this.sheet = s;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        
        //drawOnGrid(g2);
        drawMap(g2);
        
        g2.dispose();
    }
    
    public void drawMap(Graphics2D g2){
        int mapx = mapTile.getX();
        int mapy = mapTile.getY();
        Color oldColor = g2.getColor();
        
        for(int i = 0; i < mapTile.lengthRows() -  mapTile.getX(); i ++){
            for(int j = 0; j < mapTile.lengthCols() -  mapTile.getY(); j ++){
                Integer code = mapTile.getCode(i + mapTile.getX(), j + mapTile.getY());
                g2.setColor(Color.white);
                if(code != null && code != -1){
                    TileSettings tile = sheet.getImageByCode(code);
                    g2.drawImage(tile.getImg(), i*tileSize, j*tileSize, tileSize, tileSize, null);
                }
                if(drawGridOn && code != null){
                    g2.drawRect(i*tileSize, j*tileSize, tileSize, tileSize);
                }else if(code == null){
                    g2.setColor(Color.gray);
                    g2.fillRect(i*tileSize, j*tileSize, tileSize, tileSize);
                }
            }
        }
        g2.setColor(oldColor);
    }
    
    public void setDrawGridOn(boolean value){
        this.drawGridOn = value;
        repaint();
    }
    
    public boolean getDrawGridOn(){
        return this.drawGridOn;
    }
    
    public int getTileSize() {
        return tileSize;
    }
    
    public MapTile getMapTile(){
        return mapTile;
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
