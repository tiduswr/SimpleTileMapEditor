package memento;

import java.util.Stack;


public class CaretakerMemento {
    
    private StaticStack<Memento> undo;
    private Originator o;
    
    public CaretakerMemento(Originator o){
        undo = new StaticStack<>(new Memento[50]);
        this.o = o;
    }
    
    public void saveState(){
        undo.push(o.createMemento());
        System.out.println("saved");
    }
    
    public void undo(){
        Memento item = undo.pop();
        o.setMemento(item);
        System.out.println("undo");
    }
    
    public void clearBackups(){
        this.undo.clear();
    }
    
}
