 package net.wurstclient.hacks;
 
 import java.util.Arrays;
 import net.minecraft.class_2338;
 import net.minecraft.class_239;
 import net.minecraft.class_3965;
 import net.wurstclient.Category;
 import net.wurstclient.SearchTags;
 import net.wurstclient.events.UpdateListener;
 import net.wurstclient.hack.Hack;
 import net.wurstclient.util.BlockBreaker;
 
 
 
 
 
 
 
 
 @SearchTags({"auto mine", "AutoBreak", "auto break"})
 public final class AutoMineHack
   extends Hack
   implements UpdateListener
 {
   private class_2338 currentBlock;
   
   public AutoMineHack() {
     super("AutoMine", "Automatically mines any block that you look at.");
     setCategory(Category.BLOCKS);
   }
 
 
   
   public void onEnable() {
     (WURST.getHax()).nukerHack.setEnabled(false);
     EVENTS.add(UpdateListener.class, this);
   }
 
 
   
   public void onDisable() {
     EVENTS.remove(UpdateListener.class, this);
     stopMiningAndResetProgress();
   }
 
 
   
   public void onUpdate() {
     setCurrentBlockFromHitResult();
     
     if (this.currentBlock != null) {
       breakCurrentBlock();
     }
   }
   
   private void setCurrentBlockFromHitResult() {
     if (MC.field_1765 == null || MC.field_1765.method_17784() == null || MC.field_1765
       .method_17783() != class_239.class_240.field_1332 || !(MC.field_1765 instanceof class_3965)) {
 
       
       stopMiningAndResetProgress();
       
       return;
     } 
     this.currentBlock = ((class_3965)MC.field_1765).method_17777();
   }
 
   
   private void breakCurrentBlock() {
     if (MC.field_1724.field_7503.field_7477) {
       BlockBreaker.breakBlocksWithPacketSpam(Arrays.asList(new class_2338[] { this.currentBlock }));
     } else {
       BlockBreaker.breakOneBlock(this.currentBlock);
     } 
   }
   
   private void stopMiningAndResetProgress() {
     if (this.currentBlock == null) {
       return;
     }
     IMC.getInteractionManager().setBreakingBlock(true);
     MC.field_1761.method_2925();
     this.currentBlock = null;
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\hacks\AutoMineHack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */