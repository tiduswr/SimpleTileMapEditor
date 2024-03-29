package toolbar;

import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import main.MainPanel;
import spritesheet.BufferedImageLoader;

public class TBItem_Balde extends ToolBarItem{
    
    public TBItem_Balde(){
        super(new BufferedImageLoader().loadImage("tools/balde.png"));
    }
    
    @Override
    public void update(MainPanel mp) {
        mp.getToolBar().setSelectedTool("balde");
        mp.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
            new ImageIcon(new BufferedImageLoader().loadImage("cursor/balde.png")).getImage(),
            new Point(0,0),"custom cursor"));
    }
}
