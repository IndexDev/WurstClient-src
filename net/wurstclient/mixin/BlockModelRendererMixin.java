 package net.wurstclient.mixin;
 
 import java.util.Random;
 import net.minecraft.class_1087;
 import net.minecraft.class_1920;
 import net.minecraft.class_2338;
 import net.minecraft.class_2680;
 import net.minecraft.class_287;
 import net.wurstclient.WurstClient;
 import net.wurstclient.events.ShouldDrawSideListener;
 import net.wurstclient.events.TesselateBlockListener;
 import org.spongepowered.asm.mixin.Mixin;
 import org.spongepowered.asm.mixin.Shadow;
 import org.spongepowered.asm.mixin.injection.At;
 import org.spongepowered.asm.mixin.injection.Inject;
 import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Mixin({net.minecraft.class_778.class})
 public abstract class BlockModelRendererMixin
 {
   @Inject(at = {@At("HEAD")}, method = {"tesselateSmooth(Lnet/minecraft/world/ExtendedBlockView;Lnet/minecraft/client/render/model/BakedModel;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/client/render/BufferBuilder;ZLjava/util/Random;J)Z", "tesselateFlat(Lnet/minecraft/world/ExtendedBlockView;Lnet/minecraft/client/render/model/BakedModel;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/client/render/BufferBuilder;ZLjava/util/Random;J)Z"}, cancellable = true)
   private void onTesselateSmoothOrFlat(class_1920 extendedBlockView_1, class_1087 bakedModel_1, class_2680 blockState_1, class_2338 blockPos_1, class_287 bufferBuilder_1, boolean depthTest, Random random_1, long long_1, CallbackInfoReturnable<Boolean> cir) {
     TesselateBlockListener.TesselateBlockEvent event = new TesselateBlockListener.TesselateBlockEvent(blockState_1);
     WurstClient.INSTANCE.getEventManager().fire(event);
     
     if (event.isCancelled()) {
       
       cir.cancel();
       
       return;
     } 
     if (!depthTest) {
       return;
     }
     ShouldDrawSideListener.ShouldDrawSideEvent event2 = new ShouldDrawSideListener.ShouldDrawSideEvent(blockState_1);
     WurstClient.INSTANCE.getEventManager().fire(event2);
     if (!Boolean.TRUE.equals(event2.isRendered())) {
       return;
     }
     method_3361(extendedBlockView_1, bakedModel_1, blockState_1, blockPos_1, bufferBuilder_1, false, random_1, long_1);
   }
 
 
 
 
 
 
   
   @Shadow
   public boolean method_3361(class_1920 extendedBlockView_1, class_1087 bakedModel_1, class_2680 blockState_1, class_2338 blockPos_1, class_287 bufferBuilder_1, boolean boolean_1, Random random_1, long long_1) { return false; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\mixin\BlockModelRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */