package filehandler;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;

public class StoreData implements Serializable{
    
    private int[][] mapCodes;
    private byte[] spriteSheet;
    private int tileMapSize, spriteSize;

    public StoreData(int[][] mapCodes, BufferedImage spriteSheet, int tileMapSize, int spriteSize) throws IOException {
        this.mapCodes = mapCodes;
        this.tileMapSize = tileMapSize;
        this.spriteSize = spriteSize;
        
        //Transform BufferedImage to byte array to serialize data
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(spriteSheet, "png", baos);
        this.spriteSheet = baos.toByteArray();
    }

    public int getTileMapSize() {
        return tileMapSize;
    }

    public int getSpriteSize() {
        return spriteSize;
    }

    public int[][] getMapCodes() {
        return mapCodes;
    }

    public BufferedImage getSpriteSheet() throws IOException {
        InputStream is = new ByteArrayInputStream(spriteSheet);
        return ImageIO.read(is);
    }
    
}
