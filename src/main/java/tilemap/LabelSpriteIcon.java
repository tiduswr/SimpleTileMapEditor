package tilemap;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import main.MainPanel;

public class LabelSpriteIcon extends JLabel{
    
    private MainPanel mp = null;
    private int[][] codes = null;
    
    public LabelSpriteIcon(){
    }
    
    public void setMainPanel(MainPanel mp){
        this.mp = mp;
    }
    
    public void updateImage(int[][] codes){
        this.codes = codes;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        if(codes != null && mp != null){
            int height, width;
            height = this.getHeight()/codes[0].length;
            width = this.getWidth()/codes.length;
            
            for(int i = 0; i < codes.length; i++){
                for(int j = 0; j < codes[0].length; j++){
                    TileSettings settings = mp.getSpriteSheetPanel().getImageByCode(codes[i][j]);
                    if(settings != null) g2.drawImage(settings.getImg(), i*width, j*height, width, height, null);
                }
            }
        }
        
        g2.dispose();
    }
}
