 package net.wurstclient.mixin;
 
 import net.minecraft.class_3675;
 import net.wurstclient.WurstClient;
 import net.wurstclient.mixinterface.IKeyBinding;
 import org.spongepowered.asm.mixin.Mixin;
 import org.spongepowered.asm.mixin.Shadow;
 
 
 
 
 
 
 
 
 
 
 
 
 @Mixin({net.minecraft.class_304.class})
 public class KeyBindingMixin
   implements IKeyBinding
 {
   @Shadow
   private boolean field_1653;
   @Shadow
   private class_3675.class_306 field_1655;
   
   public void setPressed(boolean pressed) { this.field_1653 = pressed; }
 
 
 
   
   public boolean isActallyPressed() {
     long handle = WurstClient.MC.field_1704.method_4490();
     int code = this.field_1655.method_1444();
     return class_3675.method_15987(handle, code);
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\mixin\KeyBindingMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */