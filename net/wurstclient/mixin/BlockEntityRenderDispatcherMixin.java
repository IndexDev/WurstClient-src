 package net.wurstclient.mixin;
 
 import net.minecraft.class_2586;
 import net.wurstclient.WurstClient;
 import net.wurstclient.events.RenderBlockEntityListener;
 import org.spongepowered.asm.mixin.Mixin;
 import org.spongepowered.asm.mixin.injection.At;
 import org.spongepowered.asm.mixin.injection.Inject;
 import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Mixin({net.minecraft.class_824.class})
 public class BlockEntityRenderDispatcherMixin
 {
   @Inject(at = {@At("HEAD")}, method = {"render(Lnet/minecraft/block/entity/BlockEntity;FI)V"}, cancellable = true)
   private void onRender(class_2586 blockEntity, float partialTicks, int destroyStage, CallbackInfo ci) {
     RenderBlockEntityListener.RenderBlockEntityEvent event = new RenderBlockEntityListener.RenderBlockEntityEvent(blockEntity);
     WurstClient.INSTANCE.getEventManager().fire(event);
     
     if (event.isCancelled())
       ci.cancel(); 
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\mixin\BlockEntityRenderDispatcherMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */