 package net.wurstclient.events;
 
 import java.util.ArrayList;
 import net.minecraft.class_2338;
 import net.minecraft.class_2350;
 import net.wurstclient.event.Event;
 import net.wurstclient.event.Listener;
 
 
 
 
 
 
 
 
 
 
 public interface BlockBreakingProgressListener
   extends Listener
 {
   void onBlockBreakingProgress(BlockBreakingProgressEvent paramBlockBreakingProgressEvent);
   
   public static class BlockBreakingProgressEvent
     extends Event<BlockBreakingProgressListener>
   {
     private final class_2338 blockPos;
     private final class_2350 direction;
     
     public BlockBreakingProgressEvent(class_2338 blockPos, class_2350 direction) {
       this.blockPos = blockPos;
       this.direction = direction;
     }
 
 
     
     public void fire(ArrayList<BlockBreakingProgressListener> listeners) {
       for (BlockBreakingProgressListener listener : listeners) {
         listener.onBlockBreakingProgress(this);
       }
     }
 
 
     
     public Class<BlockBreakingProgressListener> getListenerType() { return BlockBreakingProgressListener.class; }
 
 
 
     
     public class_2338 getBlockPos() { return this.blockPos; }
 
 
 
     
     public class_2350 getDirection() { return this.direction; }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\events\BlockBreakingProgressListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */