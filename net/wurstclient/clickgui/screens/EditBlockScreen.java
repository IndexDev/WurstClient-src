 package net.wurstclient.clickgui.screens;
 
 import net.minecraft.class_1799;
 import net.minecraft.class_2246;
 import net.minecraft.class_2248;
 import net.minecraft.class_2585;
 import net.minecraft.class_308;
 import net.minecraft.class_327;
 import net.minecraft.class_342;
 import net.minecraft.class_4185;
 import net.minecraft.class_437;
 import net.wurstclient.WurstClient;
 import net.wurstclient.settings.BlockSetting;
 import net.wurstclient.util.BlockUtils;
 import org.lwjgl.opengl.GL11;
 
 
 
 
 
 
 
 
 
 
 
 public final class EditBlockScreen
   extends class_437
 {
   private final class_437 prevScreen;
   private final BlockSetting setting;
   private class_342 blockField;
   private class_4185 doneButton;
   
   public EditBlockScreen(class_437 prevScreen, BlockSetting setting) {
     super(new class_2585(""));
     this.prevScreen = prevScreen;
     this.setting = setting;
   }
 
 
   
   public void init() {
     int x1 = this.width / 2 - 100;
     int y1 = 60;
     int y2 = this.height / 3 * 2;
     
     class_327 tr = this.minecraft.field_1772;
     String valueString = this.setting.getBlockName();
     
     this.blockField = new class_342(tr, x1, y1, 178, 18, "");
     this.blockField.method_1852(valueString);
     this.blockField.method_1875(0);
     
     this.children.add(this.blockField);
     method_20085(this.blockField);
     this.blockField.method_1876(true);
     
     this.doneButton = new class_4185(x1, y2, 200, 20, "Done", b -> done());
     addButton(this.doneButton);
   }
 
   
   private void done() {
     String value = this.blockField.method_1882();
     class_2248 block = BlockUtils.getBlockFromName(value);
     
     if (block != null) {
       this.setting.setBlock(block);
     }
     this.minecraft.method_1507(this.prevScreen);
   }
 
 
   
   public boolean keyPressed(int keyCode, int scanCode, int int_3) {
     switch (keyCode) {
       
       case 257:
         done();
         break;
       
       case 256:
         this.minecraft.method_1507(this.prevScreen);
         break;
     } 
     
     return super.keyPressed(keyCode, scanCode, int_3);
   }
 
 
 
   
   public void tick() { this.blockField.method_1865(); }
 
 
 
   
   public void render(int mouseX, int mouseY, float partialTicks) {
     class_327 tr = this.minecraft.field_1772;
     
     renderBackground();
     drawCenteredString(tr, this.setting.getName(), this.width / 2, 20, 16777215);
     
     this.blockField.render(mouseX, mouseY, partialTicks);
     super.render(mouseX, mouseY, partialTicks);
     
     GL11.glPushMatrix();
     GL11.glTranslated((-64 + this.width / 2 - 100), 115.0D, 0.0D);
 
     
     boolean lblAbove = (!this.blockField.method_1882().isEmpty() || this.blockField.isFocused());
     String lblText = lblAbove ? "Block name or ID:" : "block name or ID";
     int lblX = lblAbove ? 50 : 68;
     int lblY = lblAbove ? -66 : -50;
     int lblColor = lblAbove ? 15790320 : 8421504;
     drawString(tr, lblText, lblX, lblY, lblColor);
     
     fill(48, -56, 64, -36, -6250336);
     fill(49, -55, 64, -37, -16777216);
     fill(214, -56, 244, -55, -6250336);
     fill(214, -37, 244, -36, -6250336);
     fill(244, -56, 246, -36, -6250336);
     fill(214, -55, 243, -52, -16777216);
     fill(214, -40, 243, -37, -16777216);
     fill(215, -55, 216, -37, -16777216);
     fill(242, -55, 245, -37, -16777216);
     
     class_2248 blockToAdd = BlockUtils.getBlockFromName(this.blockField.method_1882());
     renderIcon(new class_1799(blockToAdd), 52, -52, false);
     
     GL11.glPopMatrix();
   }
 
 
 
   
   public boolean isPauseScreen() { return false; }
 
 
 
 
   
   public boolean shouldCloseOnEsc() { return false; }
 
 
   
   private void renderIcon(class_1799 stack, int x, int y, boolean large) {
     GL11.glPushMatrix();
     
     GL11.glTranslated(x, y, 0.0D);
     double scale = large ? 1.5D : 0.75D;
     GL11.glScaled(scale, scale, scale);
     
     class_308.method_1453();
     class_1799 grass = new class_1799(class_2246.field_10219);
     class_1799 renderStack = !stack.method_7960() ? stack : grass;
     WurstClient.MC.method_1480().method_4023(renderStack, 0, 0);
     class_308.method_1450();
     
     GL11.glPopMatrix();
     
     if (stack.method_7960()) {
       renderQuestionMark(x, y, large);
     }
   }
   
   private void renderQuestionMark(int x, int y, boolean large) {
     GL11.glPushMatrix();
     
     GL11.glTranslated(x, y, 0.0D);
     if (large) {
       GL11.glScaled(2.0D, 2.0D, 2.0D);
     }
     GL11.glDisable(2929);
     class_327 tr = WurstClient.MC.field_1772;
     tr.method_1720("?", 3.0F, 2.0F, 15790320);
     GL11.glEnable(2929);
     
     GL11.glPopMatrix();
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\screens\EditBlockScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */