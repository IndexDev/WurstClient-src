 package net.wurstclient.events;
 
 import java.util.ArrayList;
 import net.minecraft.class_265;
 import net.wurstclient.event.Event;
 import net.wurstclient.event.Listener;
 
 
 
 
 
 
 
 
 
 
 public interface CactusCollisionShapeListener
   extends Listener
 {
   void onCactusCollisionShape(CactusCollisionShapeEvent paramCactusCollisionShapeEvent);
   
   public static class CactusCollisionShapeEvent
     extends Event<CactusCollisionShapeListener>
   {
     private class_265 collisionShape;
     
     public class_265 getCollisionShape() { return this.collisionShape; }
 
 
 
     
     public void setCollisionShape(class_265 collisionShape) { this.collisionShape = collisionShape; }
 
 
 
     
     public void fire(ArrayList<CactusCollisionShapeListener> listeners) {
       for (CactusCollisionShapeListener listener : listeners) {
         listener.onCactusCollisionShape(this);
       }
     }
 
 
     
     public Class<CactusCollisionShapeListener> getListenerType() { return CactusCollisionShapeListener.class; }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\events\CactusCollisionShapeListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */