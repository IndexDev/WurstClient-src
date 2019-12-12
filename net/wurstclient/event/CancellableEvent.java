 package net.wurstclient.event;
 
 
 
 
 
 
 
 
 
 public abstract class CancellableEvent<T extends Listener>
   extends Event<T>
 {
   private boolean cancelled = false;
   
   public void cancel() { this.cancelled = true; }
 
 
 
   
   public boolean isCancelled() { return this.cancelled; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\event\CancellableEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */