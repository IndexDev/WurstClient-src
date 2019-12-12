 package net.wurstclient.hacks;
 
 import net.minecraft.class_1661;
 import net.wurstclient.Category;
 import net.wurstclient.SearchTags;
 import net.wurstclient.events.UpdateListener;
 import net.wurstclient.hack.Hack;
 
 
 
 
 
 
 
 @SearchTags({"auto switch"})
 public final class AutoSwitchHack
   extends Hack
   implements UpdateListener
 {
   public AutoSwitchHack() {
     super("AutoSwitch", "Switches the item in your hand all the time.\n\n§lProTip:§r Use this in combination with BuildRandom while\nhaving a lot of different colored wool or concrete\nblocks in your hotbar.");
 
 
     
     setCategory(Category.ITEMS);
   }
 
 
 
   
   public void onEnable() { EVENTS.add(UpdateListener.class, this); }
 
 
 
 
   
   public void onDisable() { EVENTS.remove(UpdateListener.class, this); }
 
 
 
   
   public void onUpdate() {
     class_1661 inventory = MC.field_1724.field_7514;
     
     if (inventory.field_7545 == 8) {
       inventory.field_7545 = 0;
     } else {
       inventory.field_7545++;
     } 
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\hacks\AutoSwitchHack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */