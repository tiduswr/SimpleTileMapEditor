package toolbar;

import main.MainPanel;

public interface Observable {
    public void update(MainPanel mp);
    public void registerObserver(Observer o);
    public void notifyAllObservers();
}
