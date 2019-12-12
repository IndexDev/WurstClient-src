 package net.wurstclient.clickgui.screens;
 
 import net.minecraft.class_2585;
 import net.minecraft.class_437;
 import net.wurstclient.clickgui.ClickGui;
 
 
 
 
 
 
 
 
 public final class ClickGuiScreen
   extends class_437
 {
   private final ClickGui gui;
   
   public ClickGuiScreen(ClickGui gui) {
     super(new class_2585(""));
     this.gui = gui;
   }
 
 
 
   
   public boolean isPauseScreen() { return false; }
 
 
 
   
   public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
     this.gui.handleMouseClick((int)mouseX, (int)mouseY, mouseButton);
     return super.mouseClicked(mouseX, mouseY, mouseButton);
   }
 
 
   
   public boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
     this.gui.handleMouseRelease(mouseX, mouseY, mouseButton);
     return super.mouseReleased(mouseX, mouseY, mouseButton);
   }
 
 
   
   public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
     this.gui.handleMouseScroll(mouseX, mouseY, delta);
     return super.mouseScrolled(mouseX, mouseY, delta);
   }
 
 
   
   public void render(int mouseX, int mouseY, float partialTicks) {
     super.render(mouseX, mouseY, partialTicks);
     this.gui.render(mouseX, mouseY, partialTicks);
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\screens\ClickGuiScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */