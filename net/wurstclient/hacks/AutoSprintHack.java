 package net.wurstclient.hacks;
 
 import net.minecraft.class_746;
 import net.wurstclient.Category;
 import net.wurstclient.SearchTags;
 import net.wurstclient.events.UpdateListener;
 import net.wurstclient.hack.Hack;
 
 
 
 
 
 
 
 @SearchTags({"auto sprint"})
 public final class AutoSprintHack
   extends Hack
   implements UpdateListener
 {
   public AutoSprintHack() {
     super("AutoSprint", "Makes you sprint automatically.");
     setCategory(Category.MOVEMENT);
   }
 
 
 
   
   public void onEnable() { EVENTS.add(UpdateListener.class, this); }
 
 
 
 
   
   public void onDisable() { EVENTS.remove(UpdateListener.class, this); }
 
 
 
   
   public void onUpdate() {
     class_746 player = MC.field_1724;
     
     if (player.field_5976 || player.method_5715()) {
       return;
     }
     if (player.method_5637() || player.method_5869()) {
       return;
     }
     if (player.field_6250 > 0.0F)
       player.method_5728(true); 
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\hacks\AutoSprintHack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */