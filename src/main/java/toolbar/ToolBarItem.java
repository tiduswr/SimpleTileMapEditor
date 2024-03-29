package toolbar;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import main.MainPanel;

public class ToolBarItem extends JLabel implements Observable{
    private ArrayList<Observer> observers;
    
    public ToolBarItem(BufferedImage icon){
        Image tmp = icon.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(32, 2, BufferedImage.TYPE_INT_ARGB);
        setIcon(new ImageIcon(tmp));
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
               setBorder(BorderFactory.createLineBorder(Color.red, 1));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(null);
            }
        });
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                notifyAllObservers();
            }
        });
        observers = new ArrayList<>();
    }
    
    @Override
    public void registerObserver(Observer o){
        this.observers.add(o);
    }
    
    @Override
    public void notifyAllObservers() {
        observers.forEach(o -> {
            o.notify(this);
        });
    }

    @Override
    public void update(MainPanel mp) {
    }
}
