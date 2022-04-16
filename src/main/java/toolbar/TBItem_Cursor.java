package toolbar;

import java.awt.Cursor;
import main.MainPanel;
import spritesheet.BufferedImageLoader;

public class TBItem_Cursor extends ToolBarItem{
    
    public TBItem_Cursor(){
        super(new BufferedImageLoader().loadImage("tools/cursor.png"));
    }
    
    @Override
    public void update(MainPanel mp) {
        mp.getToolBar().setSelectedTool("cursor");
        mp.setCursor(Cursor.getDefaultCursor());
    }
}
