package net.wurstclient.mixinterface;

import net.minecraft.class_320;

public interface IMinecraftClient {
  void rightClick();
  
  void setItemUseCooldown(int paramInt);
  
  IClientPlayerInteractionManager getInteractionManager();
  
  int getItemUseCooldown();
  
  IClientPlayerEntity getPlayer();
  
  void setSession(class_320 paramclass_320);
}


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\mixinterface\IMinecraftClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */