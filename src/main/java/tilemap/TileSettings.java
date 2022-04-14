package tilemap;

import java.awt.image.BufferedImage;


public class TileSettings {
    private BufferedImage img;
    private int code;
    
    public TileSettings(int code) {
        this.img = null;
        this.code = code;
    }
    
    public TileSettings(BufferedImage img, int code) {
        this.img = img;
        this.code = code;
    }
    
    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
