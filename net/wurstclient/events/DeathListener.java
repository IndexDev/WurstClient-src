 package net.wurstclient.events;
 
 import java.util.ArrayList;
 import net.wurstclient.event.Event;
 import net.wurstclient.event.Listener;
 
 
 
 
 
 
 
 public interface DeathListener
   extends Listener
 {
   void onDeath();
   
   public static class DeathEvent
     extends Event<DeathListener>
   {
     public static final DeathEvent INSTANCE = new DeathEvent();
 
 
     
     public void fire(ArrayList<DeathListener> listeners) {
       for (DeathListener listener : listeners) {
         listener.onDeath();
       }
     }
 
 
     
     public Class<DeathListener> getListenerType() { return DeathListener.class; }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\events\DeathListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */