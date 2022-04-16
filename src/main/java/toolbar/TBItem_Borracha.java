package toolbar;

import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import main.MainPanel;
import spritesheet.BufferedImageLoader;
import tilemap.TileSettings;

public class TBItem_Borracha extends ToolBarItem{
    
    public TBItem_Borracha(){
        super(new BufferedImageLoader().loadImage("tools/borracha.png"));
    }
    
    @Override
    public void update(MainPanel mp) {
        mp.getSpriteSheetPanel().setSelected(new TileSettings(-1));
        mp.updateSelectedItem();
        mp.getToolBar().setSelectedTool("borracha");
        mp.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
            new ImageIcon(new BufferedImageLoader().loadImage("cursor/borracha.png")).getImage(),
            new Point(0,0),"custom cursor"));
    }
}
