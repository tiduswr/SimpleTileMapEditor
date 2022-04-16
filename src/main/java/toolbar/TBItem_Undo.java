package toolbar;

import main.MainPanel;
import spritesheet.BufferedImageLoader;

public class TBItem_Undo extends ToolBarItem{
    public TBItem_Undo(){
        super(new BufferedImageLoader().loadImage("tools/undo.png"));
    }
    
    @Override
    public void update(MainPanel mp) {
        mp.getTileFrame().getCaretaker().undo();
        mp.getTileFrame().repaint();
    }
}
