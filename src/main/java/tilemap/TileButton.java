package tilemap;

import spritesheet.SpriteSheet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class TileButton extends JButton{
    private Stroke stroke = new BasicStroke(3);
    private Color colorBorder = Color.white;
    private BufferedImage img;
    
    public TileButton(BufferedImage img, int index, SpriteSheet spriteSheet){
        this.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                TileSettings last = spriteSheet.getImageByCode(index);
                spriteSheet.setSelected(last);
                spriteSheet.updateSelectedItem();
            }
        });
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBorder(BorderFactory.createLineBorder(Color.red, 1));
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBorder(BorderFactory.createLineBorder(Color.white, 1));
                repaint();
            }
        });
        this.img = img;
        this.setFocusable(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        Color oldColor = g2.getColor();
        Stroke oldStroke = g2.getStroke();
        
        g2.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        
        g2.setColor(oldColor);
        g2.setStroke(oldStroke);
    }
}
