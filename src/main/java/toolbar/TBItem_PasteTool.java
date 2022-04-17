package toolbar;

import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import main.MainPanel;
import spritesheet.BufferedImageLoader;

public class TBItem_PasteTool extends ToolBarItem{
    
    public TBItem_PasteTool(){
        super(new BufferedImageLoader().loadImage("tools/pasteTool.png"));
    }
    
    @Override
    public void update(MainPanel mp) {
        mp.getToolBar().setSelectedTool("pasteTool");
        mp.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
            new ImageIcon(new BufferedImageLoader().loadImage("cursor/pasteTool.png")).getImage(),
            new Point(0,0),"custom cursor"));
    }
}
