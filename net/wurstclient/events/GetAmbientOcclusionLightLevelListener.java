 package net.wurstclient.events;
 
 import java.util.ArrayList;
 import net.minecraft.class_2680;
 import net.wurstclient.event.Event;
 import net.wurstclient.event.Listener;
 
 
 
 
 
 
 
 
 
 
 
 public interface GetAmbientOcclusionLightLevelListener
   extends Listener
 {
   void onGetAmbientOcclusionLightLevel(GetAmbientOcclusionLightLevelEvent paramGetAmbientOcclusionLightLevelEvent);
   
   public static class GetAmbientOcclusionLightLevelEvent
     extends Event<GetAmbientOcclusionLightLevelListener>
   {
     private final class_2680 state;
     private float lightLevel;
     private final float defaultLightLevel;
     
     public GetAmbientOcclusionLightLevelEvent(class_2680 state, float lightLevel) {
       this.state = state;
       this.lightLevel = lightLevel;
       this.defaultLightLevel = lightLevel;
     }
 
 
     
     public class_2680 getState() { return this.state; }
 
 
 
     
     public float getLightLevel() { return this.lightLevel; }
 
 
 
     
     public void setLightLevel(float lightLevel) { this.lightLevel = lightLevel; }
 
 
 
     
     public float getDefaultLightLevel() { return this.defaultLightLevel; }
 
 
 
 
     
     public void fire(ArrayList<GetAmbientOcclusionLightLevelListener> listeners) {
       for (GetAmbientOcclusionLightLevelListener listener : listeners) {
         listener.onGetAmbientOcclusionLightLevel(this);
       }
     }
 
 
     
     public Class<GetAmbientOcclusionLightLevelListener> getListenerType() { return GetAmbientOcclusionLightLevelListener.class; }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\events\GetAmbientOcclusionLightLevelListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */