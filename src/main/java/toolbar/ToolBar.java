package toolbar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import main.MainPanel;

public final class ToolBar extends JPanel{
    private MainPanel mp;
    private String selectedTool = "cursor";
    
    public ToolBar(){
        this.setFocusable(false);
        this.setOpaque(true);
        this.setBackground(Color.black);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.white, 1));
    }
    
    public void setMainPanel(MainPanel mp){
        this.mp = mp;
    }
    
    public void createTools(){
        TBItem_Cursor cursor = new TBItem_Cursor();
        createTool(cursor);
        TBItem_Selection selection = new TBItem_Selection();
        createTool(selection);
        TBItem_PasteTool pasteTool = new TBItem_PasteTool();
        createTool(pasteTool);
        TBItem_Borracha borracha = new TBItem_Borracha();
        createTool(borracha);
        TBItem_Balde balde = new TBItem_Balde();
        createTool(balde);
        TBItem_Grid grid = new TBItem_Grid();
        createTool(grid);
        TBItem_Undo undo = new TBItem_Undo();
        createTool(undo);
    }
    
    private void createTool(ToolBarItem t){
        t.registerObserver(mp);
        t.setMinimumSize(new Dimension(32, 32));
        t.setPreferredSize(new Dimension(32, 32));
        t.setMaximumSize(new Dimension(32, 32));
        add(t);
    }

    public String getSelectedTool() {
        return selectedTool;
    }

    public void setSelectedTool(String selectedTool) {
        this.selectedTool = selectedTool;
    }
    
}
