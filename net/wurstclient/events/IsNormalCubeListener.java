 package net.wurstclient.events;
 
 import java.util.ArrayList;
 import net.wurstclient.event.CancellableEvent;
 import net.wurstclient.event.Listener;
 
 
 
 
 
 
 
 
 
 
 public interface IsNormalCubeListener
   extends Listener
 {
   void onIsNormalCube(IsNormalCubeEvent paramIsNormalCubeEvent);
   
   public static class IsNormalCubeEvent
     extends CancellableEvent<IsNormalCubeListener>
   {
     public void fire(ArrayList<IsNormalCubeListener> listeners) {
       for (IsNormalCubeListener listener : listeners) {
         
         listener.onIsNormalCube(this);
         
         if (isCancelled()) {
           break;
         }
       } 
     }
 
 
     
     public Class<IsNormalCubeListener> getListenerType() { return IsNormalCubeListener.class; }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\events\IsNormalCubeListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */