 package net.wurstclient.events;
 
 import java.util.ArrayList;
 import java.util.Objects;
 import net.wurstclient.event.CancellableEvent;
 import net.wurstclient.event.Listener;
 
 
 
 
 
 
 
 
 
 public interface ChatOutputListener
   extends Listener
 {
   void onSentMessage(ChatOutputEvent paramChatOutputEvent);
   
   public static class ChatOutputEvent
     extends CancellableEvent<ChatOutputListener>
   {
     private final String originalMessage;
     private String message;
     
     public ChatOutputEvent(String message) {
       this.message = (String)Objects.requireNonNull(message);
       this.originalMessage = message;
     }
 
 
     
     public String getMessage() { return this.message; }
 
 
 
     
     public void setMessage(String message) { this.message = message; }
 
 
 
     
     public String getOriginalMessage() { return this.originalMessage; }
 
 
 
     
     public boolean isModified() { return !this.originalMessage.equals(this.message); }
 
 
 
     
     public void fire(ArrayList<ChatOutputListener> listeners) {
       for (ChatOutputListener listener : listeners) {
         
         listener.onSentMessage(this);
         
         if (isCancelled()) {
           break;
         }
       } 
     }
 
 
     
     public Class<ChatOutputListener> getListenerType() { return ChatOutputListener.class; }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\events\ChatOutputListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */