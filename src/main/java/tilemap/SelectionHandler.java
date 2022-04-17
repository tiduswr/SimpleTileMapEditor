package tilemap;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import main.MainPanel;

public class SelectionHandler {
    private MapTile mapTile;
    private MainPanel mp;
    private int tileSize, tileMapSize;
    
    private int firstScreenX = 0, firstScreenY = 0;
    private int lastScreenX = 0, lastScreenY = 0;
    
    private boolean selectionStarted;
    private boolean selected;
    private int lastSelectedRow = -1;
    private int lastSelectedCol = -1;
    private int selectedRow = -1, selectedCol = -1;
    private int sX = 0, sY = 0;
    private Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                                  0, new float[]{9}, 0);

    public SelectionHandler(MapTile mapTile, MainPanel mp, int tileSize, int tileMapSize) {
        this.mapTile = mapTile;
        this.mp = mp;
        this.tileSize = tileSize;
        this.tileMapSize = tileMapSize;
    }
    
    public void up(){
        firstScreenY --;
        lastScreenY --;
    }
    
    public void down(){
        firstScreenY ++;
        lastScreenY ++;
    }
    
    public void left(){
        firstScreenX --;
        lastScreenX --;
    }
    
    public void right(){
        firstScreenX ++;
        lastScreenX ++;
    }
    
    public void updateLocationLogic(int selectedRow, int selectedCol){
        this.selectedRow = selectedRow;
        this.selectedCol = selectedCol;
        sX = mapTile.getX();
        sY = mapTile.getY();

        if(!selectionStarted){
            lastSelectedRow = selectedRow;
            lastSelectedCol = selectedCol;
            selected = true;
        }

        if(!((selectedRow + sX) < 0 || (selectedCol + sY) < 0)){
            selectionStarted = !((selectedRow + sX) >= tileMapSize || 
                    (selectedCol + sY) >= tileMapSize);
        }
    }
    
    public void updateSelectionOnScreen(){
        if(mapTile.getSelectedArea() != null || selectionStarted){
            if(selectedRow - lastSelectedRow >= 0 &&  selectedCol - lastSelectedCol >= 0 && 
                    lastSelectedRow + sX < tileMapSize &&  lastSelectedCol + sY < tileMapSize){
                mapTile.setSelection(lastSelectedRow + sX, lastSelectedCol + sY, 
                    selectedRow + sX, selectedCol + sY);
                mp.getSelectedAreaFrame().setImage(mapTile.getSelectedArea());
            }
            if(!selected){
                lastSelectedRow = -1;
                lastSelectedCol = -1;
            }
            selectionStarted = false;
        }
    }
    
    public void drawSelection(Graphics2D g2){
        if(selectedRow >= lastSelectedRow && selectedCol >= lastSelectedCol &&
            selectedRow + sX >= 0 && selectedCol + sY >= 0 && 
            selectedRow + sX < tileMapSize && selectedCol + sY < tileMapSize){
            
            int firstX, firstY, lastX, lastY;
            Color oldColor = g2.getColor();
            Stroke oldStroke = g2.getStroke();

            g2.setStroke(dashed);
            g2.setColor(Color.red);
            
            firstX = lastSelectedRow*tileSize + firstScreenX*tileSize;
            firstY = lastSelectedCol*tileSize + firstScreenY*tileSize;
            lastX = (selectedRow*tileSize) + (lastScreenX*tileSize)+tileSize;
            lastY = (selectedCol*tileSize) + (lastScreenY*tileSize)+tileSize;
            lastX -= firstX;
            lastY -= firstY;
            g2.drawRect(firstX + sX*tileSize, firstY + sY*tileSize, lastX, lastY);
            
            g2.setStroke(oldStroke);
            g2.setColor(oldColor);
        }
    }
    
    public void drawSelectedImage(Graphics2D g2, int panelWidth, int panelHeight, int hoverRow, int hoverCol){
        int[][] codes = mapTile.getSelectedArea();
        int i2 = hoverRow, j2 = hoverCol;
        
        if(codes != null && mp != null){
            Color oldColor = g2.getColor();
            Stroke oldStroke = g2.getStroke();
            
            for(int i = 0; i < codes.length; i++){
                for(int j = 0; j < codes[0].length; j++){
                    TileSettings settings = mp.getSpriteSheetPanel().getImageByCode(codes[i][j]);
                    if(settings != null) {
                        if((i2*tileSize) <= panelWidth && (j2*tileSize) <= panelHeight){
                            g2.drawImage(settings.getImg(), i2*tileSize, j2*tileSize, tileSize, tileSize, null);
                        }
                    }
                    j2++;
                }
                j2 = hoverCol;
                i2++;
            }
            
            g2.setStroke(dashed);
            g2.setColor(Color.red);
            g2.drawRect(hoverRow*tileSize, hoverCol*tileSize, tileSize*codes.length, tileSize*codes[0].length);
            g2.setStroke(oldStroke);
            g2.setColor(oldColor);
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }
    
}
