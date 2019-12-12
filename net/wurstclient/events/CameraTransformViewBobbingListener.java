 package net.wurstclient.events;
 
 import java.util.ArrayList;
 import net.wurstclient.event.CancellableEvent;
 import net.wurstclient.event.Listener;
 
 
 
 
 
 
 
 
 
 
 
 
 public interface CameraTransformViewBobbingListener
   extends Listener
 {
   void onCameraTransformViewBobbing(CameraTransformViewBobbingEvent paramCameraTransformViewBobbingEvent);
   
   public static class CameraTransformViewBobbingEvent
     extends CancellableEvent<CameraTransformViewBobbingListener>
   {
     public void fire(ArrayList<CameraTransformViewBobbingListener> listeners) {
       for (CameraTransformViewBobbingListener listener : listeners) {
         
         listener.onCameraTransformViewBobbing(this);
         
         if (isCancelled()) {
           break;
         }
       } 
     }
 
 
     
     public Class<CameraTransformViewBobbingListener> getListenerType() { return CameraTransformViewBobbingListener.class; }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\events\CameraTransformViewBobbingListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */