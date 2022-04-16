package memento;

import java.util.Stack;


public class CaretakerMemento {
    
    private Stack<Memento> undo;
    private Originator o;
    
    public CaretakerMemento(Originator o){
        undo = new Stack<>();
        this.o = o;
    }
    
    public void saveState(){
        undo.push(o.createMemento());
        System.out.println("saved");
    }
    
    public void undo(){
        if(!undo.isEmpty()){
            Memento item = undo.pop();
            o.setMemento(item);
        }else{
            o.setMemento(null);
        }
        System.out.println("undo");
    }
    
    public void clearBackups(){
        this.undo.clear();
    }
    
}
