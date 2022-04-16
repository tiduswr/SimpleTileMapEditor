package tilemap;

import memento.Memento;

public class MapTileBackup implements Memento<int[][]>{
    
    private int[][] state;
    
    public MapTileBackup(int[][] state){
        this.state = state;
    }
    
    @Override
    public int[][] getState() {
        return state;
    }

    @Override
    public void setState(int[][] state) {
        this.state = state;
    }
    
}
