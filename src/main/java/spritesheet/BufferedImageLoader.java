
package spritesheet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class BufferedImageLoader {
    private BufferedImage image;
    
    public BufferedImage loadImage(String path){
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/" + path));
        } catch (IOException ex) {
            Logger.getLogger(BufferedImageLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }
    
    public BufferedImage loadExternalImage(String completePath){
        try {
            image = ImageIO.read(new File(completePath));
        } catch (IOException ex) {
            Logger.getLogger(BufferedImageLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }
    
}
