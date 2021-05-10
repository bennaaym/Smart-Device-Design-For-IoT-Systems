package cc.ooad.project.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class UnitState implements ISubject
{
    List<IObserver> observerList;

    public UnitState()
    {
        this.observerList = new ArrayList<>();
    }
    @Override
    public void attach(IObserver observer) {
        this.observerList.add(observer);
    }

    @Override
    public void detach(IObserver observer) {this.observerList.remove(observer);}

    @Override
    public void notify(String notification) {
        for(IObserver observer : observerList)
            observer.update(notification);
    }


}
