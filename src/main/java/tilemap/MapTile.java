package tilemap;

import memento.Memento;
import memento.Originator;

public class MapTile implements Originator<int[][]>{
    private int mapCodes[][], rows, cols, x, y;
    private int selectedArea[][];
    private TileFrame tf;
    
    public MapTile(int rows, int cols, TileFrame tf){
        x = 0;
        y = 0;
        this.rows = rows;
        this.cols = cols;
        
        createEmptyArray();
        
        this.tf = tf;
    }
    
    public MapTile(int mapCodes[][], TileFrame tf){
        x = 0;
        y = 0;
        this.rows = mapCodes.length;
        this.cols = mapCodes[0].length;
        this.mapCodes = mapCodes;
        this.tf = tf;
    }
    
    private void createEmptyArray(){
        mapCodes = new int[rows][cols];
        for (int[] mapCode : mapCodes) {
            for (int j = 0; j < mapCode.length; j++) {
                mapCode[j] = -1;
            }
        }
    }
    
    public void nearReplacement(int x, int y, int code, int codeTolReplace){
        boolean visited[][] = new boolean[rows][cols];
        nearReplace(x, y, code, codeTolReplace, visited);
    }
    
    private void nearReplace(int x, int y, int code, int codeTolReplace, boolean visited[][]){
        visited[x][y] = true;
        
        //Cima
        if((x - 1) < rows && x - 1 >= 0){
            if(mapCodes[x-1][y] == mapCodes[x][y] && !visited[x-1][y]){
                nearReplace(x - 1, y, code, codeTolReplace, visited);
            }
        }
        //Baixo
        if((x + 1) < rows && (x + 1) >= 0){
            if(mapCodes[x+1][y] == mapCodes[x][y] && !visited[x+1][y]){
                nearReplace(x + 1, y, code, codeTolReplace, visited);
            }
        }
        //Esquerda
        if((y - 1) < cols && (y - 1) >= 0){
            if(mapCodes[x][y-1] == mapCodes[x][y] && !visited[x][y-1]){
                nearReplace(x, y-1, code, codeTolReplace, visited);
            }
        }
        //Direita
        if((y + 1) < cols && (y + 1) >= 0){
            if(mapCodes[x][y+1] == mapCodes[x][y] && !visited[x][y+1]){
                nearReplace(x, y+1, code, codeTolReplace, visited);
            }
        }
        
        if(mapCodes[x][y] == codeTolReplace) mapCodes[x][y] = code;
    }
    
    public int[][] getData(){
        return mapCodes;
    }
    
    public void placeSubImageAt(int x, int y, int[][] codes){
        if(x >= 0 && y >= 0 && x < cols && y < rows){
            for(int i = 0; i < codes.length; i++){
                for(int j = 0; j < codes[0].length; j++){
                    if(i+x < mapCodes.length && j+y < mapCodes[0].length){
                        mapCodes[i+x][j+y] = codes[i][j];
                    }
                }
            }
        }
    }
    
    public void setSelection(int x1, int y1, int x2, int y2){
        
        int selRows = x2 - x1 + 1;
        int selCols = y2 - y1 + 1;
        int i2 = 0;
        int j2 = 0;
        selectedArea = new int[selRows][selCols];
        
        for(int i = x1; i < x2+1; i++){
          for(int j = y1; j < y2+1; j++){
              selectedArea[i2][j2] = mapCodes[i][j];
              j2++;
          }
          j2 = 0;
          i2++;
        }
        
    }
    
    public void setPosition(int x, int y){
        if(x >= 0 && y >= 0 && x < rows && y < cols){
            this.x = x;
            this.y = y;
        }else{
            if(x < 0 && x < rows){
                this.x = 0;
            }
            if(y < 0 && y < cols){
                this.y = 0;
            }
        }
    }
    
    public Integer getCode(int row, int col){
        if(row >= 0 && col >= 0 && row < rows && col < cols){
            return mapCodes[row][col];
        }
        return null;
    }
    
    public void setCode(int code, int row, int col){
        if(row >= 0 && col >= 0 && row < rows && col < cols){
            mapCodes[row][col] = code;
        }
    }
    
    public int lengthRows(){
        return rows;
    }
    
    public int lengthCols(){
        return cols;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void up(){
       y ++;
    }
    
    public void down(){
        y --;
    }
    
    public void left(){
       x ++;
    }
    
    public void right(){
        x --;
    }

    public int[][] getSelectedArea() {
        return selectedArea;
    }
    
    public void resetSelectedArea(){
        this.selectedArea = null;
    }
    
    @Override
    public void setMemento(Memento<int[][]> m) {
        if(m != null){
            this.mapCodes = m.getState();
        }else{
            createEmptyArray();
        }
    }

    @Override
    public Memento<int[][]> createMemento() {
        int [][] backup = new int[mapCodes.length][mapCodes[0].length];
        boolean diference;
        
        for(int i = 0; i < mapCodes.length; i++){
          for(int j = 0; j < mapCodes[0].length; j++){
              backup[i][j] = mapCodes[i][j];
          }
        }
        
        return new MapTileBackup(backup);
    }
    
}
