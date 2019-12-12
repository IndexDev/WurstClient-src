 package net.wurstclient.hacks;
 
 import net.minecraft.class_1792;
 import net.minecraft.class_1799;
 import net.minecraft.class_2378;
 import net.wurstclient.Category;
 import net.wurstclient.SearchTags;
 import net.wurstclient.events.UpdateListener;
 import net.wurstclient.hack.Hack;
 import net.wurstclient.settings.ItemListSetting;
 
 
 
 
 
 
 
 @SearchTags({"auto drop", "AutoEject", "auto-eject", "auto eject", "InventoryCleaner", "inventory cleaner", "InvCleaner", "inv cleaner"})
 public final class AutoDropHack
   extends Hack
   implements UpdateListener
 {
   private ItemListSetting items = new ItemListSetting("Items", "Unwanted items that will be dropped.", new String[] { "minecraft:allium", "minecraft:azure_bluet", "minecraft:blue_orchid", "minecraft:cornflower", "minecraft:dandelion", "minecraft:lilac", "minecraft:lily_of_the_valley", "minecraft:orange_tulip", "minecraft:oxeye_daisy", "minecraft:peony", "minecraft:pink_tulip", "minecraft:poisonous_potato", "minecraft:poppy", "minecraft:red_tulip", "minecraft:rose_bush", "minecraft:rotten_flesh", "minecraft:sunflower", "minecraft:wheat_seeds", "minecraft:white_tulip" });
 
 
 
 
 
 
 
 
 
   
   private final String renderName = (Math.random() < 0.01D) ? "AutoLinus" : getName();
 
   
   public AutoDropHack() {
     super("AutoDrop", "Automatically drops unwanted items.");
     setCategory(Category.ITEMS);
     addSetting(this.items);
   }
 
 
 
   
   public String getRenderName() { return this.renderName; }
 
 
 
 
   
   public void onEnable() { EVENTS.add(UpdateListener.class, this); }
 
 
 
 
   
   public void onDisable() { EVENTS.remove(UpdateListener.class, this); }
 
 
 
   
   public void onUpdate() {
     for (int slot = 9; slot < 45; slot++) {
       
       int adjustedSlot = slot;
       if (adjustedSlot >= 36)
         adjustedSlot -= 36; 
       class_1799 stack = MC.field_1724.field_7514.method_5438(adjustedSlot);
       
       if (!stack.method_7960()) {
 
         
         class_1792 item = stack.method_7909();
         String itemName = class_2378.field_11142.method_10221(item).toString();
         
         if (this.items.getItemNames().contains(itemName))
         {
           
           IMC.getInteractionManager().windowClick_THROW(slot);
         }
       } 
     } 
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\hacks\AutoDropHack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */