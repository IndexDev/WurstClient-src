 package net.wurstclient;
 
 import net.minecraft.class_243;
 import net.minecraft.class_746;
 import net.wurstclient.events.PostMotionListener;
 import net.wurstclient.events.PreMotionListener;
 import net.wurstclient.util.RotationUtils;
 
 
 
 
 
 
 
 
 
 
 public final class RotationFaker
   implements PreMotionListener, PostMotionListener
 {
   private boolean fakeRotation;
   private float serverYaw;
   private float serverPitch;
   private float realYaw;
   private float realPitch;
   
   public void onPreMotion() {
     if (!this.fakeRotation) {
       return;
     }
     class_746 player = WurstClient.MC.field_1724;
     this.realYaw = player.field_6031;
     this.realPitch = player.field_5965;
     player.field_6031 = this.serverYaw;
     player.field_5965 = this.serverPitch;
   }
 
 
   
   public void onPostMotion() {
     if (!this.fakeRotation) {
       return;
     }
     class_746 player = WurstClient.MC.field_1724;
     player.field_6031 = this.realYaw;
     player.field_5965 = this.realPitch;
     this.fakeRotation = false;
   }
 
 
   
   public void faceVectorPacket(class_243 vec) {
     RotationUtils.Rotation rotations = RotationUtils.getNeededRotations(vec);
     
     this.fakeRotation = true;
     this.serverYaw = rotations.getYaw();
     this.serverPitch = rotations.getPitch();
   }
 
 
   
   public float getServerYaw() { return this.fakeRotation ? this.serverYaw : WurstClient.MC.field_1724.field_6031; }
 
 
 
   
   public float getServerPitch() { return this.fakeRotation ? this.serverPitch : WurstClient.MC.field_1724.field_5965; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\RotationFaker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */