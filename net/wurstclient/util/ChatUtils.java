 package net.wurstclient.util;
 
 import net.minecraft.class_2561;
 import net.minecraft.class_2585;
 import net.minecraft.class_310;
 import net.minecraft.class_338;
 import net.wurstclient.WurstClient;
 
 
 public static enum ChatUtils
 {
   private static final class_310 MC;
   public static final String WURST_PREFIX = "§c[§6Wurst§c]§r ";
   private static final String WARNING_PREFIX = "§c[§6§lWARNING§c]§r ";
   private static final String ERROR_PREFIX = "§c[§4§lERROR§c]§r ";
   private static final String SYNTAX_ERROR_PREFIX = "§4Syntax error:§r ";
   private static boolean enabled;
   
   static  {
     MC = WurstClient.MC;
 
 
 
 
 
 
 
 
 
     
     enabled = true;
   }
 
   
   public static void setEnabled(boolean enabled) { ChatUtils.enabled = enabled; }
 
 
   
   public static void component(class_2561 component) {
     if (!enabled) {
       return;
     }
     class_338 chatHud = MC.field_1705.method_1743();
     class_2585 prefix = new class_2585("§c[§6Wurst§c]§r ");
     chatHud.method_1812(prefix.method_10852(component));
   }
 
 
   
   public static void message(String message) { component(new class_2585(message)); }
 
 
 
   
   public static void warning(String message) { message("§c[§6§lWARNING§c]§r " + message); }
 
 
 
   
   public static void error(String message) { message("§c[§4§lERROR§c]§r " + message); }
 
 
 
   
   public static void syntaxError(String message) { message("§4Syntax error:§r " + message); }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclien\\util\ChatUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */