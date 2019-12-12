 package net.wurstclient.util;
 
 import net.minecraft.class_1297;
 import net.minecraft.class_1657;
 import net.minecraft.class_2945;
 import net.minecraft.class_638;
 import net.minecraft.class_745;
 import net.minecraft.class_746;
 import net.wurstclient.WurstClient;
 
 
 
 
 
 
 
 public class FakePlayerEntity
   extends class_745
 {
   private final class_746 player = WurstClient.MC.field_1724;
   private final class_638 world = WurstClient.MC.field_1687;
 
   
   public FakePlayerEntity() {
     super(WurstClient.MC.field_1687, WurstClient.MC.field_1724.method_7334());
     method_5719(this.player);
     
     copyInventory();
     copyPlayerModel(this.player, this);
     copyRotation();
     resetCapeMovement();
     
     spawn();
   }
 
 
   
   private void copyInventory() { this.field_7514.method_7377(this.player.field_7514); }
 
 
   
   private void copyPlayerModel(class_1297 from, class_1297 to) {
     class_2945 fromTracker = from.method_5841();
     class_2945 toTracker = to.method_5841();
     Byte playerModel = (Byte)fromTracker.method_12789(class_1657.field_7518);
     toTracker.method_12778(class_1657.field_7518, playerModel);
   }
 
   
   private void copyRotation() {
     this.field_6241 = this.player.field_6241;
     this.field_6283 = this.player.field_6283;
   }
 
   
   private void resetCapeMovement() {
     this.field_7500 = this.field_5987;
     this.field_7521 = this.field_6010;
     this.field_7499 = this.field_6035;
   }
 
 
   
   private void spawn() { this.world.method_2942(method_5628(), this); }
 
 
 
   
   public void despawn() { this.field_5988 = true; }
 
 
 
   
   public void resetPlayerPosition() { this.player.method_5808(this.field_5987, this.field_6010, this.field_6035, this.field_6031, this.field_5965); }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclien\\util\FakePlayerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */