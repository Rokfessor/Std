import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Chat implements Observable {
    Set<Observer> users;

    public Chat() {
        users = new HashSet<>();
    }

    @Override
    public void addObserver(Observer observer) {
        users.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        users.remove(observer);
    }

    @Override
    public void notifyObservers(String message, Observer source) {
        users.forEach(o -> {
            if (o == source)
                return;

            o.update(message);
        });
    }
}
