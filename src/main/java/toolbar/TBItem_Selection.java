package toolbar;

import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import main.MainPanel;
import spritesheet.BufferedImageLoader;

public class TBItem_Selection extends ToolBarItem{
    public TBItem_Selection(){
        super(new BufferedImageLoader().loadImage("tools/selection.png"));
    }
    
    @Override
    public void update(MainPanel mp) {
        mp.getToolBar().setSelectedTool("selection");
        mp.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
            new ImageIcon(new BufferedImageLoader().loadImage("cursor/selection.png")).getImage(),
            new Point(0,0),"custom cursor"));
    }
}
