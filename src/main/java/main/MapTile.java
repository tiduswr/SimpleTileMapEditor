package main;

public class MapTile {
    private int mapCodes[][], rows, cols, x, y;
    private TileFrame tf;
    
    public MapTile(int rows, int cols, TileFrame tf){
        mapCodes = new int[rows][cols];
        
        for(int i = 0; i < mapCodes.length; i++){
            for(int j = 0; j < mapCodes[i].length; j++){
                mapCodes[i][j] = -1;
            }
        }
        
        x = 0;
        y = 0;
        this.rows = rows;
        this.cols = cols;
        this.tf = tf;
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
        if((y - 1) >= 0) y --;
    }
    
    public void left(){
       x ++;
    }
    
    public void right(){
        if((x - 1) >= 0) x --;
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
    
}
