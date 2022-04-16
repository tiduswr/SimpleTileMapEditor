 
package tilemap;

import spritesheet.SpriteSheet;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingUtilities;
import main.MainPanel;
import memento.CaretakerMemento;

public final class TileFrame extends javax.swing.JPanel {
    
    private int tileMapSize = 50;
    private int tileSizeSpriteSheet = 16;
    private int tileSize = 32;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private SpriteSheet sheet;
    private boolean drawGridOn = false;
    private MapTile mapTile;
    private MainPanel mp;
    
    //Handle Mementos
    private CaretakerMemento caretaker;
    private boolean saved = false;
    
    //Handle the drag tileFrame editor
    private Point firstPressed;
    private Integer lastPassedRow = null;
    private Integer lastPassedCol = null;
    private boolean dragTileEditorStarted = false;
    private Cursor lastCursor;
    
    //Handle Selection Tool
    private boolean selectionStarted;
    private int lastSelectedRow = -1;
    private int lastSelectedCol = -1;
    
    public TileFrame() {
        initComponents();
        this.setBackground(Color.GRAY);
        
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!saved){
                    saved = true;
                    caretaker.saveState();
                }
                
                int x = e.getPoint().x;
                int y = e.getPoint().y;
                selectedRow = x/tileSize;
                selectedCol = y/tileSize;
                TileSettings tile = sheet.getSelected();
                
                if(e.getButton() == MouseEvent.BUTTON1){
                    //FERRAMENTAS
                    if(mp.getToolBar().getSelectedTool().equals("balde")){
                        if(tile != null){
                            mapTile.nearReplacement(selectedRow + mapTile.getX(), selectedCol + mapTile.getY(), 
                                    sheet.getSelected().getCode(), mapTile.getCode(selectedRow + mapTile.getX(), selectedCol + mapTile.getY()));
                            repaint();
                        }
                    }else{
                        if(tile != null && mp.getToolBar().getSelectedTool().equals("cursor") && 
                                sheet.getSelected().getCode() != -1){
                            mapTile.setCode(sheet.getSelected().getCode(), selectedRow + mapTile.getX(), 
                                    selectedCol + mapTile.getY());
                            repaint();
                        }else if(tile != null && mp.getToolBar().getSelectedTool().equals("borracha")){
                            mapTile.setCode(sheet.getSelected().getCode(), selectedRow + mapTile.getX(), 
                                    selectedCol + mapTile.getY());
                            repaint();
                        }
                    }
                }
                
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(dragTileEditorStarted){
                    firstPressed = null;
                    lastPassedRow = null;
                    lastPassedCol = null;
                    dragTileEditorStarted = false;
                    mp.setCursor(lastCursor);
                }
                saved = false;
                if(selectionStarted){
                    if(selectedRow - lastSelectedRow >= 0 &&  selectedCol - lastSelectedCol >= 0){
                        System.out.println(lastSelectedCol + " " + lastSelectedRow + " " + selectedRow + " " + selectedCol);
                        mapTile.setSelection(lastSelectedRow + mapTile.getX(), lastSelectedCol + mapTile.getY(), 
                            selectedRow + mapTile.getX(), selectedCol + mapTile.getY());
                        mp.getSelectedAreaFrame().setImage(mapTile.getSelectedArea());
                    }
                    
                    selectionStarted = false;
                    lastSelectedRow = -1;
                    lastSelectedCol = -1;
                }
            }
        });
        this.addMouseWheelListener(new MouseAdapter(){
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {           
                
                if(tileSize > 0 && (tileSize - e.getWheelRotation()) > 0){
                    tileSize -= e.getWheelRotation();
                }
                repaint();
            }
        });
        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
                if(!saved && !mp.getToolBar().getSelectedTool().equals("selection")){
                    saved = true;
                    caretaker.saveState();
                }
                
                int x = e.getPoint().x;
                int y = e.getPoint().y;
                selectedRow = x/tileSize;
                selectedCol = y/tileSize;
                
                if(SwingUtilities.isLeftMouseButton(e)){
                    TileSettings tile = sheet.getSelected();
                    if(tile != null && mp.getToolBar().getSelectedTool().equals("cursor") && 
                        sheet.getSelected().getCode() != -1){
                        mapTile.setCode(sheet.getSelected().getCode(), selectedRow + mapTile.getX(), selectedCol + mapTile.getY());
                        repaint();
                    }else if(tile != null && mp.getToolBar().getSelectedTool().equals("borracha")) {
                        mapTile.setCode(sheet.getSelected().getCode(), selectedRow + mapTile.getX(), selectedCol + mapTile.getY());
                        repaint();
                    }else if(mp.getToolBar().getSelectedTool().equals("selection")){
                        if(!selectionStarted){
                            lastSelectedRow = selectedRow;
                            lastSelectedCol = selectedCol;
                        }
                        if(!((selectedRow + mapTile.getX()) < 0 || (selectedCol + mapTile.getY()) < 0)){
                            selectionStarted = !((selectedRow + mapTile.getX()) >= tileMapSize || 
                                    (selectedCol + mapTile.getY()) >= tileMapSize);
                        }
                        repaint();
                    }
                }else{
                    dragTileMapEditor(e);
                }
            }
        });
        this.
        createMapTile(tileMapSize, tileMapSize);
    }
    
    private void dragTileMapEditor(MouseEvent e){
        if(firstPressed == null) firstPressed = e.getPoint();
        if(!dragTileEditorStarted) {
            lastCursor = mp.getCursor();
            mp.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        }

        dragTileEditorStarted = true;
        int firstSelectedRow = firstPressed.x/tileSize;
        int firstSelectedCol = firstPressed.y/tileSize;
        int passedRows = selectedRow - firstSelectedRow;
        int passedCols = selectedCol - firstSelectedCol;

        if(lastPassedRow == null || lastPassedCol == null){
            lastPassedRow = passedRows;
            lastPassedCol = passedCols;
        }

        if(lastPassedRow != passedRows || lastPassedCol != passedCols){
            lastPassedRow = passedRows;
            lastPassedCol = passedCols;

            //Esquerda
            if(passedRows < 0){
                mapTile.left();
            }
            //Direita
            if(passedRows > 0){
                mapTile.right();
            }
            //Cima
            if(passedCols < 0){
                mapTile.up();
            }
            //Baixo
            if(passedCols > 0){
                mapTile.down();
            }
            firstPressed = e.getPoint();
            repaint();
        }
    }
    
    public void createMapTile(int rows, int cols){
        mapTile = new MapTile(rows, cols, this);
        caretaker = new CaretakerMemento(mapTile);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        
        //drawOnGrid(g2);
        drawMap(g2);
        
        if(selectionStarted){
            drawSelection(g2);
        }
        
        g2.dispose();
    }
    
    public void drawSelection(Graphics2D g2){
        Color oldColor = g2.getColor();
        g2.setColor(Color.red);
        
        int firstX, firstY, lastX, lastY;
        
        if(selectedRow >= lastSelectedRow && selectedCol >= lastSelectedCol){
            firstX = lastSelectedRow*tileSize;
            firstY = lastSelectedCol*tileSize;
            lastX = (selectedRow*tileSize)+tileSize;
            lastY = (selectedCol*tileSize)+tileSize;
            lastX -= firstX;
            lastY -= firstY;
            g2.drawRect(firstX, firstY, lastX, lastY);
        }
        
        
        g2.setColor(oldColor);
    }
    
    public void drawMap(Graphics2D g2){
        int mapx = mapTile.getX();
        int mapy = mapTile.getY();
        Color oldColor = g2.getColor();
        
        for(int i = 0; i < mapTile.lengthRows() -  mapTile.getX(); i ++){
            for(int j = 0; j < mapTile.lengthCols() -  mapTile.getY(); j ++){
                Integer code = mapTile.getCode(i + mapTile.getX(), j + mapTile.getY());
                g2.setColor(Color.black);
                g2.fillRect(i*tileSize, j*tileSize, tileSize, tileSize);
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

    public int getTileSizeSpriteSheet() {
        return tileSizeSpriteSheet;
    }
    
    public int getTileMapSize() {
        return this.tileMapSize;
    }

    public void setTileMapSize(int tileMapSize) {
        this.tileMapSize = tileMapSize;
    }

    public void setTileSizeSpriteSheet(int tileSizeSpriteSheet) {
        this.tileSizeSpriteSheet = tileSizeSpriteSheet;
    }

    public CaretakerMemento getCaretaker() {
        return caretaker;
    }
    
    public void setSheet(SpriteSheet s){
        this.sheet = s;
    }
    
    public void setMainPanel(MainPanel mp){
        this.mp = mp;
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
