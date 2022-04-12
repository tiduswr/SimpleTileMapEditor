package main;

import java.awt.image.BufferedImage;


public class TileSettings {
    private BufferedImage img;
    private int row, col, code;

    public TileSettings(BufferedImage img, int row, int col, int cols) {
        this.img = img;
        this.row = row;
        this.col = col;
        this.code = ((col - 1) * cols) + row;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
}
