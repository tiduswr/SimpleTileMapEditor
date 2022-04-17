package toolbar;

import main.MainPanel;
import spritesheet.BufferedImageLoader;
import tilemap.TileFrame;

public class TBItem_Grid extends ToolBarItem{
    
    public TBItem_Grid(){
        super(new BufferedImageLoader().loadImage("tools/grid.png"));
    }
    
    @Override
    public void update(MainPanel mp) {
        TileFrame tileFrame = mp.getTileFrame();
        
        if(tileFrame.getDrawGridOn()){
            tileFrame.setDrawGridOn(false);
        }else{
            tileFrame.setDrawGridOn(true);
        }
        
        tileFrame.repaint();
        
    }
}
