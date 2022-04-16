package memento;

public interface Originator<T> {
    public void setMemento(Memento<T> m);
    public Memento<T> createMemento();
}
