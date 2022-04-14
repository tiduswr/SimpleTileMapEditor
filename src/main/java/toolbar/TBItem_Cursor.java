package toolbar;

import java.awt.Cursor;
import java.util.ArrayList;
import main.MainPanel;
import spritesheet.BufferedImageLoader;

public class TBItem_Cursor extends ToolBarItem{
    private ArrayList<Observer> observers;
    
    public TBItem_Cursor(){
        super(new BufferedImageLoader().loadImage("tools/cursor.png"));
    }
    
    @Override
    public void updateSelectedTile(MainPanel mp) {
        mp.getToolBar().setSelectedTool("cursor");
        mp.setCursor(Cursor.getDefaultCursor());
    }
}
