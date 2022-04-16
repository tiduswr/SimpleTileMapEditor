package memento;

public interface Memento<T> {
    public T getState();
    public void setState(T m);
}
