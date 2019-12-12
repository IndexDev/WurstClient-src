package net.wurstclient.event;

import java.util.ArrayList;

public abstract class Event<T extends Listener> extends Object {
  public abstract void fire(ArrayList<T> paramArrayList);
  
  public abstract Class<T> getListenerType();
}


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\event\Event.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */