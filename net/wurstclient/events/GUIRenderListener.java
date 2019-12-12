 package net.wurstclient.events;
 
 import java.util.ArrayList;
 import net.wurstclient.event.Event;
 import net.wurstclient.event.Listener;
 
 
 
 
 
 
 
 
 
 public interface GUIRenderListener
   extends Listener
 {
   void onRenderGUI(float paramFloat);
   
   public static class GUIRenderEvent
     extends Event<GUIRenderListener>
   {
     private final float partialTicks;
     
     public GUIRenderEvent(float partialTicks) { this.partialTicks = partialTicks; }
 
 
 
     
     public void fire(ArrayList<GUIRenderListener> listeners) {
       for (GUIRenderListener listener : listeners) {
         listener.onRenderGUI(this.partialTicks);
       }
     }
 
 
     
     public Class<GUIRenderListener> getListenerType() { return GUIRenderListener.class; }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\events\GUIRenderListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */