 package net.wurstclient.util;
 
 import net.minecraft.class_1268;
 import net.minecraft.class_2338;
 import net.minecraft.class_2350;
 import net.minecraft.class_2382;
 import net.minecraft.class_243;
 import net.minecraft.class_2680;
 import net.minecraft.class_2846;
 import net.minecraft.class_2879;
 import net.minecraft.class_310;
 import net.minecraft.class_634;
 import net.wurstclient.WurstClient;
 
 
 
 
 
 
 
 public static enum BlockBreaker
 {
   private static final WurstClient WURST;
   private static final class_310 MC;
   
   static  {
     WURST = WurstClient.INSTANCE;
     MC = WurstClient.MC;
   }
   
   public static boolean breakOneBlock(class_2338 pos) {
     class_2350 side = null;
     class_2350[] sides = class_2350.values();
     
     class_243 eyesPos = RotationUtils.getEyesPos();
     
     class_243 relCenter = BlockUtils.getState(pos).method_17770(MC.field_1687, pos).method_1107().method_1005();
     class_243 center = (new class_243(pos)).method_1019(relCenter);
     
     class_243[] hitVecs = new class_243[sides.length];
     for (int i = 0; i < sides.length; i++) {
       
       class_2382 dirVec = sides[i].method_10163();
       
       class_243 relHitVec = new class_243(relCenter.field_1352 * dirVec.method_10263(), relCenter.field_1351 * dirVec.method_10264(), relCenter.field_1350 * dirVec.method_10260());
       hitVecs[i] = center.method_1019(relHitVec);
     } 
     
     class_2680 state = BlockUtils.getState(pos);
     for (int i = 0; i < sides.length; ) {
 
       
       if (MC.field_1687.method_17745(eyesPos, hitVecs[i], pos, state
           .method_17770(MC.field_1687, pos), state) != null) {
         i++; continue;
       } 
       side = sides[i];
     } 
 
     
     if (side == null) {
       
       double distanceSqToCenter = eyesPos.method_1025(center);
       for (int i = 0; i < sides.length; ) {
 
         
         if (eyesPos.method_1025(hitVecs[i]) >= distanceSqToCenter) {
           i++; continue;
         } 
         side = sides[i];
       } 
     } 
 
 
     
     if (side == null) {
       side = sides[0];
     }
     
     WURST.getRotationFaker().faceVectorPacket(hitVecs[side.ordinal()]);
 
     
     if (!MC.field_1761.method_2902(pos, side)) {
       return false;
     }
     
     MC.field_1724.field_3944
       .method_2883(new class_2879(class_1268.field_5808));
     
     return true;
   }
 
   
   public static void breakBlocksWithPacketSpam(Iterable<class_2338> blocks) {
     class_243 eyesPos = RotationUtils.getEyesPos();
     class_634 netHandler = MC.field_1724.field_3944;
     
     for (class_2338 pos : blocks) {
       
       class_243 posVec = (new class_243(pos)).method_1031(0.5D, 0.5D, 0.5D);
       double distanceSqPosVec = eyesPos.method_1025(posVec); class_2350[] arrayOfclass_2350; int i;
       byte b;
       for (arrayOfclass_2350 = class_2350.values(), i = arrayOfclass_2350.length, b = 0; b < i; ) { class_2350 side = arrayOfclass_2350[b];
 
         
         class_243 hitVec = posVec.method_1019((new class_243(side.method_10163())).method_1021(0.5D));
 
         
         if (eyesPos.method_1025(hitVec) >= distanceSqPosVec) {
           b++;
           continue;
         } 
         netHandler.method_2883(new class_2846(class_2846.class_2847.field_12968, pos, side));
         
         netHandler.method_2883(new class_2846(class_2846.class_2847.field_12973, pos, side)); }
     
     } 
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclien\\util\BlockBreaker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */