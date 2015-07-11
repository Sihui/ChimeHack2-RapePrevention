package hackmate.rapeprevention.Models;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {
  interface Listener<T> {
    void notifyChange(T obj);
  }

  private Object obj;
  private List<Listener<T>> listeners = new ArrayList<>();

  public Observable() {
  }

  public Observable(T obj) {
    this.obj = obj;
  }

  public static <T> Observable<T> from(T obj) {
    return new Observable<>(obj);
  }

  public T get() {
    return (T) obj;
  }

  public void set(T obj) {
    this.obj = obj;
    // Notify
    for (Listener<T> listener : listeners) {
      listener.notifyChange(obj);
    }
  }

  public void addListener(Listener<T> listener) {
    listeners.add(listener);
  }

  public void removeListener(Listener<T> listener) {
    listeners.remove(listener);
  }
}
