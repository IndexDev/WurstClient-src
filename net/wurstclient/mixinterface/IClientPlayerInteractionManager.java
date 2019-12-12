package net.wurstclient.mixinterface;

import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2846;

public interface IClientPlayerInteractionManager {
  float getCurrentBreakingProgress();
  
  void setBreakingBlock(boolean paramBoolean);
  
  class_1799 windowClick_PICKUP(int paramInt);
  
  class_1799 windowClick_QUICK_MOVE(int paramInt);
  
  class_1799 windowClick_THROW(int paramInt);
  
  void rightClickItem();
  
  void rightClickBlock(class_2338 paramclass_2338, class_2350 paramclass_2350, class_243 paramclass_243);
  
  void sendPlayerActionC2SPacket(class_2846.class_2847 paramclass_2847, class_2338 paramclass_2338, class_2350 paramclass_2350);
  
  void setBlockHitDelay(int paramInt);
}


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\mixinterface\IClientPlayerInteractionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */