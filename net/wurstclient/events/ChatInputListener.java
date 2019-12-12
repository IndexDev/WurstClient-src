 package net.wurstclient.events;
 
 import java.util.ArrayList;
 import java.util.List;
 import net.minecraft.class_2561;
 import net.minecraft.class_303;
 import net.wurstclient.event.CancellableEvent;
 import net.wurstclient.event.Listener;
 
 
 
 
 
 
 
 
 
 public interface ChatInputListener
   extends Listener
 {
   void onReceivedMessage(ChatInputEvent paramChatInputEvent);
   
   public static class ChatInputEvent
     extends CancellableEvent<ChatInputListener>
   {
     private class_2561 component;
     private List<class_303> chatLines;
     
     public ChatInputEvent(class_2561 component, List<class_303> chatLines) {
       this.component = component;
       this.chatLines = chatLines;
     }
 
 
     
     public class_2561 getComponent() { return this.component; }
 
 
 
     
     public void setComponent(class_2561 component) { this.component = component; }
 
 
 
     
     public List<class_303> getChatLines() { return this.chatLines; }
 
 
 
     
     public void fire(ArrayList<ChatInputListener> listeners) {
       for (ChatInputListener listener : listeners) {
         
         listener.onReceivedMessage(this);
         
         if (isCancelled()) {
           break;
         }
       } 
     }
 
 
     
     public Class<ChatInputListener> getListenerType() { return ChatInputListener.class; }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\events\ChatInputListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */