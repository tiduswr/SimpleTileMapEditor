 
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
    
    //Handle Selection
    private int hoverRow = -1, hoverCol = -1;
    private SelectionHandler selectionHandler;
    
    public TileFrame() {
        start();
        createMapTile(tileMapSize, tileMapSize);
    }
    
    public TileFrame(int tileMapSize, int tileSizeSpriteSheet) {
        this.tileMapSize = tileMapSize;
        this.tileSizeSpriteSheet = tileSizeSpriteSheet;
        start();
        createMapTile(tileMapSize, tileMapSize);
    }
    
    public TileFrame(int tileMapSize, int tileSizeSpriteSheet, int[][] codes) {
        this.tileMapSize = tileMapSize;
        this.tileSizeSpriteSheet = tileSizeSpriteSheet;
        start();
        createMapTile(codes);
    }
    
    public void start(){
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
                    if(tile != null && mp.getToolBar().getSelectedTool().equals("balde")){
                        mapTile.nearReplacement(selectedRow + mapTile.getX(), selectedCol + mapTile.getY(), 
                                sheet.getSelected().getCode(), mapTile.getCode(selectedRow + mapTile.getX(), 
                                        selectedCol + mapTile.getY()));
                        
                    }else if(tile != null && mp.getToolBar().getSelectedTool().equals("cursor") && 
                                sheet.getSelected().getCode() != -1){
                        mapTile.setCode(sheet.getSelected().getCode(), selectedRow + mapTile.getX(), 
                                selectedCol + mapTile.getY());
                        
                    }else if(tile != null && mp.getToolBar().getSelectedTool().equals("borracha")){
                        mapTile.setCode(sheet.getSelected().getCode(), selectedRow + mapTile.getX(), 
                                selectedCol + mapTile.getY());
                    }else if(mapTile.getSelectedArea() != null && mp.getToolBar().getSelectedTool().equals("pasteTool")){
                        mapTile.placeSubImageAt(selectedRow + mapTile.getX(), selectedCol + mapTile.getY(), 
                                mapTile.getSelectedArea());
                    }
                    
                    //Atualiza a area selecionada em tempo real
                    if(selectionHandler != null) selectionHandler.updateSelectionOnScreen();
                    repaint();
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
                
                //Handle selection Function
                if(selectionHandler != null) selectionHandler.updateSelectionOnScreen();
                repaint();
            }
        });
        this.addMouseWheelListener(new MouseAdapter(){
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {           
                
                if(tileSize > 0 && (tileSize - e.getWheelRotation()) > 0){
                    tileSize -= e.getWheelRotation();
                    selectionHandler.setTileSize(tileSize);
                    
                    //Update hoverRow and hoverCol values
                    int x = e.getPoint().x;
                    int y = e.getPoint().y;
                    hoverRow = x/tileSize;
                    hoverCol = y/tileSize;
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
                        if(selectionHandler != null) selectionHandler.updateLocationLogic(selectedRow, selectedCol);
                        repaint();
                    }
                }else{
                    dragTileMapEditor(e);
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getPoint().x;
                int y = e.getPoint().y;
                hoverRow = x/tileSize;
                hoverCol = y/tileSize;
                repaint();
            }
        });
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
                selectionHandler.left();
            }
            //Direita
            if(passedRows > 0){
                mapTile.right();
                selectionHandler.right();
            }
            //Cima
            if(passedCols < 0){
                mapTile.up();
                selectionHandler.up();
            }
            //Baixo
            if(passedCols > 0){
                mapTile.down();
                selectionHandler.down();
            }
            
            hoverRow += passedRows;
            hoverCol += passedCols;
            firstPressed = e.getPoint();
            repaint();
        }
    }
    
    public void createMapTile(int rows, int cols){
        mapTile = new MapTile(rows, cols, this);
        caretaker = new CaretakerMemento(mapTile);
    }
    
    public void createMapTile(int[][] map){
        mapTile = new MapTile(map, this);
        caretaker = new CaretakerMemento(mapTile);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        
        //Draw map
        drawMap(g2);
        
        if(selectionHandler != null){
            //Draw selection Stroke
            if(selectionHandler.isSelected()) selectionHandler.drawSelection(g2);
            //Draw selected image
            if(mp.getToolBar().getSelectedTool().equals("pasteTool")){
                selectionHandler.drawSelectedImage(g2, getWidth(), getHeight(), hoverRow, hoverCol);
            }
        }
        
        g2.dispose();
    }
    
    public void drawMap(Graphics2D g2){
        Color oldColor = g2.getColor();
        
        for(int i = 0; i < mapTile.lengthRows() -  mapTile.getX(); i ++){
            for(int j = 0; j < mapTile.lengthCols() -  mapTile.getY(); j ++){
                Integer code = mapTile.getCode(i + mapTile.getX(), j + mapTile.getY());
                
                //Draw black tiles for -1 codes
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
    
    public void resetSelectedArea(){
        mapTile.resetSelectedArea();
        mp.getSelectedAreaFrame().setImage(null);
        startSelectionHandler();
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

    public SelectionHandler getSelectionHandler() {
        return selectionHandler;
    }
    
    public void startSelectionHandler(){
        selectionHandler = new SelectionHandler(mapTile, mp, tileSize, tileMapSize);
    }
    
    public void selectionVisible(boolean state){
       selectionHandler.setSelected(state);
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
