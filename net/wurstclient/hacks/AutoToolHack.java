 package net.wurstclient.hacks;
 
 import net.minecraft.class_1661;
 import net.minecraft.class_1799;
 import net.minecraft.class_1890;
 import net.minecraft.class_1893;
 import net.minecraft.class_2338;
 import net.minecraft.class_2680;
 import net.minecraft.class_746;
 import net.wurstclient.Category;
 import net.wurstclient.SearchTags;
 import net.wurstclient.events.BlockBreakingProgressListener;
 import net.wurstclient.events.UpdateListener;
 import net.wurstclient.hack.Hack;
 import net.wurstclient.settings.CheckboxSetting;
 import net.wurstclient.util.BlockUtils;
 
 
 
 
 
 
 
 
 @SearchTags({"auto tool", "AutoSwitch", "auto switch"})
 public final class AutoToolHack
   extends Hack
   implements BlockBreakingProgressListener, UpdateListener
 {
   private final CheckboxSetting useSwords = new CheckboxSetting("Use swords", "Uses swords to break leaves,\ncobwebs, etc.", false);
 
   
   private final CheckboxSetting useHands = new CheckboxSetting("Use hands", "Uses an empty hand or a\nnon-damageable item when\nno applicable tool is found.", true);
 
 
 
 
   
   private final CheckboxSetting repairMode = new CheckboxSetting("Repair mode", "Won't use tools that are about to break.", false);
 
   
   private final CheckboxSetting switchBack = new CheckboxSetting("Switch back", "After using a tool, automatically switches\nback to the previously selected slot.", true);
 
 
   
   private int prevSelectedSlot;
 
 
   
   public AutoToolHack() {
     super("AutoTool", "Automatically equips the fastest applicable tool\nin your hotbar when you try to break a block.");
 
     
     setCategory(Category.BLOCKS);
     addSetting(this.useSwords);
     addSetting(this.useHands);
     addSetting(this.repairMode);
     addSetting(this.switchBack);
   }
 
 
   
   public void onEnable() {
     EVENTS.add(BlockBreakingProgressListener.class, this);
     EVENTS.add(UpdateListener.class, this);
     this.prevSelectedSlot = -1;
   }
 
 
   
   public void onDisable() {
     EVENTS.remove(BlockBreakingProgressListener.class, this);
     EVENTS.remove(UpdateListener.class, this);
   }
 
 
   
   public void onBlockBreakingProgress(BlockBreakingProgressListener.BlockBreakingProgressEvent event) {
     class_2338 pos = event.getBlockPos();
     if (!BlockUtils.canBeClicked(pos)) {
       return;
     }
     if (this.prevSelectedSlot == -1) {
       this.prevSelectedSlot = MC.field_1724.field_7514.field_7545;
     }
     equipBestTool(pos, this.useSwords.isChecked(), this.useHands.isChecked(), this.repairMode
         .isChecked());
   }
 
 
   
   public void onUpdate() {
     if (this.prevSelectedSlot == -1 || MC.field_1761.method_2923()) {
       return;
     }
     if (this.switchBack.isChecked()) {
       MC.field_1724.field_7514.field_7545 = this.prevSelectedSlot;
     }
     this.prevSelectedSlot = -1;
   }
 
 
   
   public void equipBestTool(class_2338 pos, boolean useSwords, boolean useHands, boolean repairMode) {
     class_746 player = MC.field_1724;
     if (player.field_7503.field_7477) {
       return;
     }
     int bestSlot = getBestSlot(pos, useSwords, repairMode);
     if (bestSlot == -1) {
       
       class_1799 heldItem = player.method_6047();
       if (!isDamageable(heldItem)) {
         return;
       }
       if (repairMode && isTooDamaged(heldItem)) {
         
         selectFallbackSlot();
         
         return;
       } 
       if (useHands && isWrongTool(heldItem, pos)) {
         
         selectFallbackSlot();
         
         return;
       } 
       
       return;
     } 
     player.field_7514.field_7545 = bestSlot;
   }
 
   
   private int getBestSlot(class_2338 pos, boolean useSwords, boolean repairMode) {
     class_746 player = MC.field_1724;
     class_1661 inventory = player.field_7514;
     class_1799 heldItem = MC.field_1724.method_6047();
     
     class_2680 state = BlockUtils.getState(pos);
     float bestSpeed = getMiningSpeed(heldItem, state);
     int bestSlot = -1;
     
     for (int slot = 0; slot < 9; slot++) {
       
       if (slot != inventory.field_7545) {
 
         
         class_1799 stack = inventory.method_5438(slot);
         
         float speed = getMiningSpeed(stack, state);
         if (speed > bestSpeed)
         {
           
           if (useSwords || !(stack.method_7909() instanceof net.minecraft.class_1829))
           {
             
             if (!repairMode || !isTooDamaged(stack)) {
 
               
               bestSpeed = speed;
               bestSlot = slot;
             }  }  } 
       } 
     }  return bestSlot;
   }
 
   
   private float getMiningSpeed(class_1799 stack, class_2680 state) {
     float speed = stack.method_7924(state);
     
     if (speed > 1.0F) {
 
       
       int efficiency = class_1890.method_8225(class_1893.field_9131, stack);
       if (efficiency > 0 && !stack.method_7960()) {
         speed += (efficiency * efficiency + 1);
       }
     } 
     return speed;
   }
 
 
   
   private boolean isDamageable(class_1799 stack) { return (!stack.method_7960() && stack.method_7909().method_7846()); }
 
 
 
   
   private boolean isTooDamaged(class_1799 stack) { return (stack.method_7936() - stack.method_7919() <= 4); }
 
 
   
   private boolean isWrongTool(class_1799 heldItem, class_2338 pos) {
     class_2680 state = BlockUtils.getState(pos);
     return (getMiningSpeed(heldItem, state) <= 1.0F);
   }
 
   
   private void selectFallbackSlot() {
     int fallbackSlot = getFallbackSlot();
     class_1661 inventory = MC.field_1724.field_7514;
     
     if (fallbackSlot == -1) {
       
       if (inventory.field_7545 == 8) {
         inventory.field_7545 = 0;
       } else {
         inventory.field_7545++;
       } 
       
       return;
     } 
     inventory.field_7545 = fallbackSlot;
   }
 
   
   private int getFallbackSlot() {
     class_1661 inventory = MC.field_1724.field_7514;
     
     for (int slot = 0; slot < 9; slot++) {
       
       if (slot != inventory.field_7545) {
 
         
         class_1799 stack = inventory.method_5438(slot);
         
         if (!isDamageable(stack))
           return slot; 
       } 
     } 
     return -1;
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\hacks\AutoToolHack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */