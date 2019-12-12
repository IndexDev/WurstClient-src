 package net.wurstclient.util;
 
 import net.minecraft.class_243;
 import net.minecraft.class_3532;
 import net.minecraft.class_746;
 import net.wurstclient.RotationFaker;
 import net.wurstclient.WurstClient;
 import net.wurstclient.mixinterface.IClientPlayerEntity;
 
 
 
 
 
 
 
 
 
 
 
 public static enum RotationUtils
 {
   public static class_243 getEyesPos() {
     player = WurstClient.MC.field_1724;
     
     return new class_243(player.field_5987, player.field_6010 + player
         .method_18381(player.method_18376()), player.field_6035);
   }
 
   
   public static class_243 getClientLookVec() {
     player = WurstClient.MC.field_1724;
     float f = 0.017453292F;
     float pi = 3.1415927F;
     
     float f1 = class_3532.method_15362(-player.field_6031 * f - pi);
     float f2 = class_3532.method_15374(-player.field_6031 * f - pi);
     float f3 = -class_3532.method_15362(-player.field_5965 * f);
     float f4 = class_3532.method_15374(-player.field_5965 * f);
     
     return new class_243((f2 * f3), f4, (f1 * f3));
   }
 
   
   public static class_243 getServerLookVec() {
     rotationFaker = WurstClient.INSTANCE.getRotationFaker();
     float serverYaw = rotationFaker.getServerYaw();
     float serverPitch = rotationFaker.getServerPitch();
     
     float f = class_3532.method_15362(-serverYaw * 0.017453292F - 3.1415927F);
     float f1 = class_3532.method_15374(-serverYaw * 0.017453292F - 3.1415927F);
     float f2 = -class_3532.method_15362(-serverPitch * 0.017453292F);
     float f3 = class_3532.method_15374(-serverPitch * 0.017453292F);
     return new class_243((f1 * f2), f3, (f * f2));
   }
 
   
   public static Rotation getNeededRotations(class_243 vec) {
     class_243 eyesPos = getEyesPos();
     
     double diffX = vec.field_1352 - eyesPos.field_1352;
     double diffY = vec.field_1351 - eyesPos.field_1351;
     double diffZ = vec.field_1350 - eyesPos.field_1350;
     
     double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
     
     float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
     float pitch = (float)-Math.toDegrees(Math.atan2(diffY, diffXZ));
     
     return new Rotation(yaw, pitch);
   }
 
   
   public static double getAngleToLookVec(class_243 vec) {
     Rotation needed = getNeededRotations(vec);
     
     class_746 player = WurstClient.MC.field_1724;
     float currentYaw = class_3532.method_15393(player.field_6031);
     float currentPitch = class_3532.method_15393(player.field_5965);
     
     float diffYaw = currentYaw - needed.yaw;
     float diffPitch = currentPitch - needed.pitch;
     
     return Math.sqrt((diffYaw * diffYaw + diffPitch * diffPitch));
   }
 
   
   public static double getAngleToLastReportedLookVec(class_243 vec) {
     Rotation needed = getNeededRotations(vec);
     
     IClientPlayerEntity player = WurstClient.IMC.getPlayer();
     float lastReportedYaw = class_3532.method_15393(player.getLastYaw());
     float lastReportedPitch = class_3532.method_15393(player.getLastPitch());
     
     float diffYaw = lastReportedYaw - needed.yaw;
     float diffPitch = lastReportedPitch - needed.pitch;
     
     return Math.sqrt((diffYaw * diffYaw + diffPitch * diffPitch));
   }
 
   
   public static final class Rotation
   {
     private final float yaw;
     private final float pitch;
     
     public Rotation(float yaw, float pitch) {
       this.yaw = class_3532.method_15393(yaw);
       this.pitch = class_3532.method_15393(pitch);
     }
 
 
     
     public float getYaw() { return this.yaw; }
 
 
 
     
     public float getPitch() { return this.pitch; }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclien\\util\RotationUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */