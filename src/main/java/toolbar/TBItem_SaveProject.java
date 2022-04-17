package toolbar;

import filehandler.FileTypeFilter;
import filehandler.StoreData;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import main.MainPanel;
import spritesheet.BufferedImageLoader;

public class TBItem_SaveProject extends ToolBarItem{
    
    public TBItem_SaveProject(){
        super(new BufferedImageLoader().loadImage("tools/saveProject.png"));
    }
    
    @Override
    public void update(MainPanel mp) {
        //Create save structure
        StoreData data = null;
        try {
            data = new StoreData(mp.getTileFrame().getMapTile().getData(),
                    mp.getSpriteSheetPanel().getSpriteSheet(), mp.getTileFrame().getTileMapSize(),
                    mp.getTileFrame().getTileSizeSpriteSheet());
        } catch (IOException ex) {
            Logger.getLogger(TBItem_SaveProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Find path to save
        if(data != null){
            JFileChooser fs = new JFileChooser(System.getProperty("user.home"));
            FileTypeFilter filter = new FileTypeFilter(".tmap", "Tile Map");
            fs.setDialogTitle("Save");
            fs.setFileFilter(filter);

            //Save file
            int result = fs.showSaveDialog(labelFor);
            if(result == JFileChooser.APPROVE_OPTION){
                FileOutputStream fout = null;
                try{

                    String filePath = fs.getSelectedFile().getAbsolutePath() + ".tmap";
                    fout = new FileOutputStream(filePath);
                    ObjectOutputStream oos = new ObjectOutputStream(fout);
                    oos.writeObject(data);

                }catch(FileNotFoundException ex){
                    Logger.getLogger(TBItem_SaveProject.class.getName()).log(Level.SEVERE,null, ex);
                    JOptionPane.showMessageDialog(null, "Erro ao tentar salvar");
                } catch (IOException ex) {
                    Logger.getLogger(TBItem_SaveProject.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Erro ao tentar salvar");
                } finally {
                    try {
                        fout.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao tentar salvar");
                        Logger.getLogger(TBItem_SaveProject.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
