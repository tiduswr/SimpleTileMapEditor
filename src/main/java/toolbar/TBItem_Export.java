package toolbar;

import filehandler.FileTypeFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import main.MainPanel;
import spritesheet.BufferedImageLoader;

public class TBItem_Export extends ToolBarItem{
    
    public TBItem_Export(){
        super(new BufferedImageLoader().loadImage("tools/export.png"));
    }
    
    @Override
    public void update(MainPanel mp) {
        JFileChooser fs = new JFileChooser(System.getProperty("user.home"));
        FileTypeFilter filter = new FileTypeFilter(".txt", "Text File");
        int[][] map = mp.getTileFrame().getMapTile().getData();
        
        fs.setDialogTitle("Export to .txt");
        fs.setFileFilter(filter);
        
        int result = fs.showSaveDialog(labelFor);
        
        if(result == JFileChooser.APPROVE_OPTION){
            File file = fs.getSelectedFile();
            
            try{
                String filePath = fs.getSelectedFile().getAbsolutePath() + ".txt";
                FileWriter fw = new FileWriter(filePath);
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[0].length; j++) {
                        //Save rotated
                        fw.write(String.valueOf(map[j][i]));
                        if(j != map[0].length - 1) fw.write(" ");
                    }
                    fw.write("\n");
                }
                
                fw.flush();
                fw.close();
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
}
