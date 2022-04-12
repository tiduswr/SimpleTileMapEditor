package main;

import java.awt.image.BufferedImage;

public class SpriteSheetLoader {
    private BufferedImage image;
    private int tileSize;
    
    public SpriteSheetLoader(BufferedImage image, int tileSize){
        this.image = image;
        this.tileSize = tileSize;
    }
    
    public BufferedImage grabImage(int col, int row, int width, int height){
        BufferedImage img = image.getSubimage((col*tileSize) - tileSize, (row*tileSize) - tileSize, width, height);
        return img;
    }
    
}
