 package net.wurstclient.events;
 
 import java.util.ArrayList;
 import net.wurstclient.event.Event;
 import net.wurstclient.event.Listener;
 
 
 
 
 
 
 
 
 
 public interface IsPlayerInWaterListener
   extends Listener
 {
   void onIsPlayerInWater(IsPlayerInWaterEvent paramIsPlayerInWaterEvent);
   
   public static class IsPlayerInWaterEvent
     extends Event<IsPlayerInWaterListener>
   {
     private boolean inWater;
     private final boolean normallyInWater;
     
     public IsPlayerInWaterEvent(boolean inWater) {
       this.inWater = inWater;
       this.normallyInWater = inWater;
     }
 
 
     
     public boolean isInWater() { return this.inWater; }
 
 
 
     
     public void setInWater(boolean inWater) { this.inWater = inWater; }
 
 
 
     
     public boolean isNormallyInWater() { return this.normallyInWater; }
 
 
 
     
     public void fire(ArrayList<IsPlayerInWaterListener> listeners) {
       for (IsPlayerInWaterListener listener : listeners) {
         listener.onIsPlayerInWater(this);
       }
     }
 
 
     
     public Class<IsPlayerInWaterListener> getListenerType() { return IsPlayerInWaterListener.class; }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\events\IsPlayerInWaterListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */