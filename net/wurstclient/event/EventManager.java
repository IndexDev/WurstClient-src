 package net.wurstclient.event;
 
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.Objects;
 import net.minecraft.class_128;
 import net.minecraft.class_129;
 import net.minecraft.class_148;
 import net.wurstclient.WurstClient;
 
 
 
 
 
 
 public final class EventManager
 {
   private final WurstClient wurst;
   private final HashMap<Class<? extends Listener>, ArrayList<? extends Listener>> listenerMap;
   
   public EventManager(WurstClient wurst) {
     this.listenerMap = new HashMap();
 
 
 
     
     this.wurst = wurst;
   }
 
   
   public <L extends Listener, E extends Event<L>> void fire(E event) {
     if (!this.wurst.isEnabled()) {
       return;
     }
     
     try {
       Class<L> type = event.getListenerType();
       
       ArrayList<L> listeners = (ArrayList)this.listenerMap.get(type);
       
       if (listeners == null || listeners.isEmpty()) {
         return;
       }
 
       
       ArrayList<L> listeners2 = new ArrayList<L>(listeners);
 
 
 
 
       
       listeners2.removeIf(Objects::isNull);
       
       event.fire(listeners2);
     }
     catch (Throwable e) {
       
       e.printStackTrace();
       
       class_128 report = class_128.method_560(e, "Firing Wurst event");
       class_129 section = report.method_562("Affected event");
       section.method_577("Event class", () -> event.getClass().getName());
       
       throw new class_148(report);
     } 
   }
 
 
 
   
   public <L extends Listener> void add(Class<L> type, L listener) {
     try {
       ArrayList<L> listeners = (ArrayList)this.listenerMap.get(type);
       
       if (listeners == null) {
         
         listeners = new ArrayList<L>(Arrays.asList(new Listener[] { listener }));
         this.listenerMap.put(type, listeners);
         
         return;
       } 
       listeners.add(listener);
     }
     catch (Throwable e) {
       
       e.printStackTrace();
 
       
       class_128 report = class_128.method_560(e, "Adding Wurst event listener");
       class_129 section = report.method_562("Affected listener");
       section.method_577("Listener type", () -> type.getName());
       section.method_577("Listener class", () -> listener.getClass().getName());
       
       throw new class_148(report);
     } 
   }
 
 
 
   
   public <L extends Listener> void remove(Class<L> type, L listener) {
     try {
       ArrayList<L> listeners = (ArrayList)this.listenerMap.get(type);
       
       if (listeners != null) {
         listeners.remove(listener);
       }
     } catch (Throwable e) {
       
       e.printStackTrace();
 
       
       class_128 report = class_128.method_560(e, "Removing Wurst event listener");
       class_129 section = report.method_562("Affected listener");
       section.method_577("Listener type", () -> type.getName());
       section.method_577("Listener class", () -> listener.getClass().getName());
       
       throw new class_148(report);
     } 
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\event\EventManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */