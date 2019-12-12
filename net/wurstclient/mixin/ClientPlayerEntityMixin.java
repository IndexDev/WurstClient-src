 package net.wurstclient.mixin;
 
 import com.mojang.authlib.GameProfile;
 import net.minecraft.class_1313;
 import net.minecraft.class_243;
 import net.minecraft.class_2797;
 import net.minecraft.class_634;
 import net.minecraft.class_638;
 import net.minecraft.class_742;
 import net.wurstclient.WurstClient;
 import net.wurstclient.events.ChatOutputListener;
 import net.wurstclient.events.IsPlayerInWaterListener;
 import net.wurstclient.events.PlayerMoveListener;
 import net.wurstclient.events.PostMotionListener;
 import net.wurstclient.events.PreMotionListener;
 import net.wurstclient.events.UpdateListener;
 import net.wurstclient.mixinterface.IClientPlayerEntity;
 import org.spongepowered.asm.mixin.Mixin;
 import org.spongepowered.asm.mixin.Shadow;
 import org.spongepowered.asm.mixin.injection.At;
 import org.spongepowered.asm.mixin.injection.Inject;
 import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Mixin({net.minecraft.class_746.class})
 public class ClientPlayerEntityMixin
   extends class_742
   implements IClientPlayerEntity
 {
   @Shadow
   private float field_3941;
   @Shadow
   private float field_3925;
   @Shadow
   private class_634 field_3944;
   
   public ClientPlayerEntityMixin(WurstClient wurst, class_638 clientWorld_1, GameProfile gameProfile_1) { super(clientWorld_1, gameProfile_1); }
 
 
 
 
   
   @Inject(at = {@At("HEAD")}, method = {"sendChatMessage(Ljava/lang/String;)V"}, cancellable = true)
   private void onSendChatMessage(String message, CallbackInfo ci) {
     ChatOutputListener.ChatOutputEvent event = new ChatOutputListener.ChatOutputEvent(message);
     WurstClient.INSTANCE.getEventManager().fire(event);
     
     if (event.isCancelled()) {
       
       ci.cancel();
       
       return;
     } 
     if (!event.isModified()) {
       return;
     }
     
     class_2797 packet = new class_2797(event.getMessage());
     this.field_3944.method_2883(packet);
     ci.cancel();
   }
 
 
 
 
   
   @Inject(at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V", ordinal = 0)}, method = {"tick()V"})
   private void onTick(CallbackInfo ci) { WurstClient.INSTANCE.getEventManager().fire(UpdateListener.UpdateEvent.INSTANCE); }
 
 
 
   
   @Inject(at = {@At("HEAD")}, method = {"sendMovementPackets()V"})
   private void onSendMovementPacketsHEAD(CallbackInfo ci) { WurstClient.INSTANCE.getEventManager().fire(PreMotionListener.PreMotionEvent.INSTANCE); }
 
 
 
   
   @Inject(at = {@At("TAIL")}, method = {"sendMovementPackets()V"})
   private void onSendMovementPacketsTAIL(CallbackInfo ci) { WurstClient.INSTANCE.getEventManager().fire(PostMotionListener.PostMotionEvent.INSTANCE); }
 
 
 
 
   
   @Inject(at = {@At("HEAD")}, method = {"move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V"})
   private void onMove(class_1313 type, class_243 offset, CallbackInfo ci) {
     PlayerMoveListener.PlayerMoveEvent event = new PlayerMoveListener.PlayerMoveEvent(this);
     WurstClient.INSTANCE.getEventManager().fire(event);
   }
 
 
   
   public boolean method_5799() {
     boolean inWater = super.method_5799();
     IsPlayerInWaterListener.IsPlayerInWaterEvent event = new IsPlayerInWaterListener.IsPlayerInWaterEvent(inWater);
     WurstClient.INSTANCE.getEventManager().fire(event);
     
     return event.isInWater();
   }
 
 
 
   
   public void setNoClip(boolean noClip) { this.field_5960 = noClip; }
 
 
 
 
   
   public float getLastYaw() { return this.field_3941; }
 
 
 
 
   
   public float getLastPitch() { return this.field_3925; }
 
 
 
 
   
   public void setMovementMultiplier(class_243 movementMultiplier) { this.field_17046 = movementMultiplier; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\mixin\ClientPlayerEntityMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */