 package net.wurstclient.mixin;
 
 import net.minecraft.class_1922;
 import net.minecraft.class_1935;
 import net.minecraft.class_2338;
 import net.minecraft.class_2350;
 import net.minecraft.class_2680;
 import net.wurstclient.WurstClient;
 import net.wurstclient.events.ShouldDrawSideListener;
 import org.spongepowered.asm.mixin.Mixin;
 import org.spongepowered.asm.mixin.injection.At;
 import org.spongepowered.asm.mixin.injection.Inject;
 import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Mixin({net.minecraft.class_2248.class})
 public abstract class BlockMixin
   implements class_1935
 {
   @Inject(at = {@At("HEAD")}, method = {"shouldDrawSide(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)Z"}, cancellable = true)
   private static void onShouldDrawSide(class_2680 state, class_1922 blockView, class_2338 blockPos, class_2350 side, CallbackInfoReturnable<Boolean> cir) {
     ShouldDrawSideListener.ShouldDrawSideEvent event = new ShouldDrawSideListener.ShouldDrawSideEvent(state);
     WurstClient.INSTANCE.getEventManager().fire(event);
     
     if (event.isRendered() != null)
       cir.setReturnValue(event.isRendered()); 
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\mixin\BlockMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */