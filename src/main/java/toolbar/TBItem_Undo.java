package toolbar;

import main.MainPanel;
import spritesheet.BufferedImageLoader;
import tilemap.TileFrame;

public class TBItem_Undo extends ToolBarItem{
    public TBItem_Undo(){
        super(new BufferedImageLoader().loadImage("tools/undo.png"));
    }
    
    @Override
    public void update(MainPanel mp) {
        TileFrame tf = mp.getTileFrame();
        
        tf.getCaretaker().undo();
        if(tf.getSelectionHandler() != null) tf.getSelectionHandler().updateSelectionOnScreen();
        mp.getTileFrame().repaint();
    }
}
