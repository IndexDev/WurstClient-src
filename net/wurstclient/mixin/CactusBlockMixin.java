 package net.wurstclient.mixin;
 
 import net.minecraft.class_1922;
 import net.minecraft.class_2248;
 import net.minecraft.class_2338;
 import net.minecraft.class_265;
 import net.minecraft.class_2680;
 import net.minecraft.class_3726;
 import net.wurstclient.WurstClient;
 import net.wurstclient.event.EventManager;
 import net.wurstclient.events.CactusCollisionShapeListener;
 import org.spongepowered.asm.mixin.Mixin;
 import org.spongepowered.asm.mixin.injection.At;
 import org.spongepowered.asm.mixin.injection.Inject;
 import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
 
 
 
 
 
 
 
 
 
 
 
 @Mixin({net.minecraft.class_2266.class})
 public abstract class CactusBlockMixin
   extends class_2248
 {
   private CactusBlockMixin(WurstClient wurst, class_2248.class_2251 block$Settings_1) { super(block$Settings_1); }
 
 
 
 
 
 
 
   
   @Inject(at = {@At("HEAD")}, method = {"getCollisionShape(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/EntityContext;)Lnet/minecraft/util/shape/VoxelShape;"}, cancellable = true)
   private void onGetCollisionShape(class_2680 blockState_1, class_1922 blockView_1, class_2338 blockPos_1, class_3726 entityContext_1, CallbackInfoReturnable<class_265> cir) {
     EventManager events = WurstClient.INSTANCE.getEventManager();
     if (events == null) {
       return;
     }
     CactusCollisionShapeListener.CactusCollisionShapeEvent event = new CactusCollisionShapeListener.CactusCollisionShapeEvent();
     events.fire(event);
     
     class_265 collisionShape = event.getCollisionShape();
     if (collisionShape != null)
       cir.setReturnValue(collisionShape); 
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\mixin\CactusBlockMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */