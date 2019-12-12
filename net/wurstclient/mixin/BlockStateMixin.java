 package net.wurstclient.mixin;
 
 import com.google.common.collect.ImmutableMap;
 import net.minecraft.class_1922;
 import net.minecraft.class_2248;
 import net.minecraft.class_2338;
 import net.minecraft.class_2679;
 import net.minecraft.class_2680;
 import net.minecraft.class_2688;
 import net.minecraft.class_2769;
 import net.wurstclient.WurstClient;
 import net.wurstclient.events.GetAmbientOcclusionLightLevelListener;
 import net.wurstclient.events.IsNormalCubeListener;
 import org.spongepowered.asm.mixin.Mixin;
 import org.spongepowered.asm.mixin.injection.At;
 import org.spongepowered.asm.mixin.injection.Inject;
 import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Mixin({class_2680.class})
 public class BlockStateMixin
   extends class_2679<class_2248, class_2680>
   implements class_2688<class_2680>
 {
   private BlockStateMixin(WurstClient wurst, class_2248 object_1, ImmutableMap<class_2769<?>, Comparable<?>> immutableMap_1) { super(object_1, immutableMap_1); }
 
 
 
 
 
   
   @Inject(at = {@At("TAIL")}, method = {"isSimpleFullBlock(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z"}, cancellable = true)
   private void onIsSimpleFullBlock(CallbackInfoReturnable<Boolean> cir) {
     IsNormalCubeListener.IsNormalCubeEvent event = new IsNormalCubeListener.IsNormalCubeEvent();
     WurstClient.INSTANCE.getEventManager().fire(event);
     
     cir.setReturnValue(Boolean.valueOf((((Boolean)cir.getReturnValue()).booleanValue() && !event.isCancelled())));
   }
 
 
 
 
 
 
 
   
   @Inject(at = {@At("TAIL")}, method = {"getAmbientOcclusionLightLevel(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)F"}, cancellable = true)
   private void onGetAmbientOcclusionLightLevel(class_1922 blockView, class_2338 blockPos, CallbackInfoReturnable<Float> cir) {
     GetAmbientOcclusionLightLevelListener.GetAmbientOcclusionLightLevelEvent event = new GetAmbientOcclusionLightLevelListener.GetAmbientOcclusionLightLevelEvent((class_2680)this, cir.getReturnValueF());
     
     WurstClient.INSTANCE.getEventManager().fire(event);
     cir.setReturnValue(Float.valueOf(event.getLightLevel()));
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\mixin\BlockStateMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */