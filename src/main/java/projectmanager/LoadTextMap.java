package projectmanager;

import filehandler.FileTypeFilter;
import filehandler.StoreData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import main.ProjectManager;
import spritesheet.BufferedImageLoader;

public class LoadTextMap extends javax.swing.JPanel {
    private ProjectManager pm;
    private File textMap, sheet;
    private final Pattern NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");
    
    public LoadTextMap(ProjectManager pm) {
        this.pm = pm;
        initComponents();
        setup();
    }
    
    private void setup(){
        btFindTextMap.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fs = new JFileChooser(System.getProperty("user.home"));
                FileTypeFilter filter = new FileTypeFilter(".txt", "Tile Map File");
                
                fs.setFileFilter(filter);
                int response = fs.showOpenDialog(pm);
                if(response == JFileChooser.APPROVE_OPTION){
                    textMap = fs.getSelectedFile();
                    txtTextMap.setText(textMap.getAbsolutePath());
                }else{
                    textMap = null;
                    txtTextMap.setText("");
                }
            }
        });
        btFindSpriteSheet.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fs = new JFileChooser(System.getProperty("user.home"));
                FileTypeFilter filter = new FileTypeFilter(".png", "Tile Map File");
                
                fs.setFileFilter(filter);
                int response = fs.showOpenDialog(pm);
                if(response == JFileChooser.APPROVE_OPTION){
                    sheet = fs.getSelectedFile();
                    txtSpriteSheet.setText(sheet.getAbsolutePath());
                }else{
                    sheet = null;
                    txtSpriteSheet.setText("");
                }
            }
        });
        btLoad.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                pm.loadProject(getStoredData());
            }
        });
    }
    
    public StoreData getStoredData(){
        if(textMap != null && sheet != null){
            BufferedImageLoader loader = new BufferedImageLoader();
            BufferedImage buff = loader.loadExternalImage(sheet.getAbsolutePath());
            if(buff != null){
                try{
                    //Read file
                    InputStream is = new FileInputStream(textMap);
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    
                    //Count lines
                    Stream<String> stream = Files.lines(textMap.toPath(), StandardCharsets.UTF_8);
                    int lineCount = (int) stream.count();
                    
                    //Count columns
                    String line = br.readLine();
                    String[] values = line.split(" ");
                    int columnCount = values.length;
                    is.close();
                    br.close();
                    
                    //Read File from start
                    is = new FileInputStream(textMap);
                    br = new BufferedReader(new InputStreamReader(is));
                    String[][] fileString = new String[lineCount][];
                    for(int i = 0; i < lineCount; i++){
                        line = br.readLine();
                        fileString[i] = line.split(" ");
                    }
                    
                    //Load map
                    if(lineCount == columnCount){
                        if(isNumeric(txtSpriteSize.getText())){
                            int[][] map = new int[lineCount][columnCount];
                            int row;
                            int col;
                            
                            for (int i = 0; i < map.length; i++) {
                                for(int j = 0; j < map[0].length; j++){
                                    //See if the Rotate option is active
                                    System.out.println(optRotate.isSelected());
                                    if(!optRotate.isSelected()){
                                        row = i;
                                        col = j;
                                    }else{
                                        row = j;
                                        col = i;
                                    }
                                    
                                    if(isNumeric(fileString[row][col])){
                                        map[i][j] = Integer.parseInt(fileString[row][col]);
                                    }else{
                                        map[i][j] = -1;
                                    }
                                }
                            }

                            //Close file
                            br.close();
                            
                            StoreData data = new StoreData(map, buff, lineCount, Integer.parseInt(txtSpriteSize.getText()));
                            return data;
                        }else{
                            JOptionPane.showMessageDialog(pm, "Type only number on Sprite Size");
                        }
                    }else{
                        br.close();
                        JOptionPane.showMessageDialog(pm, "Rows and Cols on map must be on the same size");
                    }
                    
                }catch(IOException | NumberFormatException e){
                    JOptionPane.showMessageDialog(pm, "Error trying to read file!");
                    Logger.getLogger(LoadTextMap.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return null;
    }
    
    private boolean isNumeric(String strNum) {
        if (strNum == null) return false; 
        return NUMERIC_PATTERN.matcher(strNum).matches();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btLoad = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTextMap = new javax.swing.JTextField();
        btFindTextMap = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtSpriteSheet = new javax.swing.JTextField();
        btFindSpriteSheet = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSpriteSize = new javax.swing.JTextField();
        optRotate = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(0, 0, 0));

        btLoad.setBackground(new java.awt.Color(0, 0, 0));
        btLoad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btLoad.setForeground(new java.awt.Color(51, 255, 51));
        btLoad.setText("Load");
        btLoad.setBorder(null);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Load Project:");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Project Location:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Text Map");

        txtTextMap.setEditable(false);
        txtTextMap.setBackground(new java.awt.Color(0, 0, 0));
        txtTextMap.setForeground(new java.awt.Color(255, 255, 255));
        txtTextMap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btFindTextMap.setBackground(new java.awt.Color(0, 0, 0));
        btFindTextMap.setForeground(new java.awt.Color(255, 255, 255));
        btFindTextMap.setText("Find");
        btFindTextMap.setBorder(null);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Sprite Sheet");

        txtSpriteSheet.setEditable(false);
        txtSpriteSheet.setBackground(new java.awt.Color(0, 0, 0));
        txtSpriteSheet.setForeground(new java.awt.Color(255, 255, 255));
        txtSpriteSheet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btFindSpriteSheet.setBackground(new java.awt.Color(0, 0, 0));
        btFindSpriteSheet.setForeground(new java.awt.Color(255, 255, 255));
        btFindSpriteSheet.setText("Find");
        btFindSpriteSheet.setBorder(null);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Sprite Size:");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("pixels.");

        txtSpriteSize.setBackground(new java.awt.Color(0, 0, 0));
        txtSpriteSize.setForeground(new java.awt.Color(255, 255, 255));
        txtSpriteSize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        optRotate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        optRotate.setForeground(new java.awt.Color(255, 255, 255));
        optRotate.setText("Rotate");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optRotate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTextMap, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btFindTextMap, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSpriteSheet)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btFindSpriteSheet, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtSpriteSize)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTextMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFindTextMap, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSpriteSheet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFindSpriteSheet, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSpriteSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(optRotate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(btLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFindSpriteSheet;
    private javax.swing.JButton btFindTextMap;
    private javax.swing.JButton btLoad;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JCheckBox optRotate;
    private javax.swing.JTextField txtSpriteSheet;
    private javax.swing.JTextField txtSpriteSize;
    private javax.swing.JTextField txtTextMap;
    // End of variables declaration//GEN-END:variables
}
