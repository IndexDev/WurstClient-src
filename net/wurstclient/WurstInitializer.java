 package net.wurstclient;
 
 import net.fabricmc.api.ModInitializer;
 
 
 
 
 
 
 
 
 
 
 
 
 
 public final class WurstInitializer
   implements ModInitializer
 {
   private static boolean initialized;
   
   public void onInitialize() {
     if (initialized) {
       throw new RuntimeException("WurstInitializer.onInitialize() ran twice!");
     }
     
     WurstClient.INSTANCE.initialize();
     initialized = true;
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\WurstInitializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */