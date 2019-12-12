 package net.wurstclient.clickgui.components;
 
 import net.minecraft.class_310;
 import net.wurstclient.WurstClient;
 import net.wurstclient.clickgui.ClickGui;
 import net.wurstclient.clickgui.Component;
 import net.wurstclient.clickgui.Window;
 import net.wurstclient.settings.CheckboxSetting;
 import org.lwjgl.opengl.GL11;
 
 
 
 public final class CheckboxComponent
   extends Component
 {
   private final class_310 MC;
   private final ClickGui GUI;
   private final CheckboxSetting setting;
   
   public CheckboxComponent(CheckboxSetting setting) {
     this.MC = WurstClient.MC;
     this.GUI = WurstClient.INSTANCE.getGui();
 
 
 
 
     
     this.setting = setting;
     setWidth(getDefaultWidth());
     setHeight(getDefaultHeight());
   }
 
 
   
   public void handleMouseClick(double mouseX, double mouseY, int mouseButton) {
     switch (mouseButton) {
       
       case 0:
         this.setting.setChecked(!this.setting.isChecked());
         break;
       
       case 1:
         this.setting.setChecked(this.setting.isCheckedByDefault());
         break;
     } 
   }
 
 
   
   public void render(int mouseX, int mouseY, float partialTicks) {
     int x1 = getX();
     int x2 = x1 + getWidth();
     int x3 = x1 + 11;
     int y1 = getY();
     int y2 = y1 + getHeight();
     
     boolean hovering = isHovering(mouseX, mouseY, x1, x2, y1, y2);
     
     if (hovering && mouseX >= x3) {
       setTooltip();
     }
     if (this.setting.isLocked()) {
       hovering = false;
     }
     drawBackground(x2, x3, y1, y2);
     drawBox(x1, x3, y1, y2, hovering);
     
     if (this.setting.isChecked()) {
       drawCheck(x1, y1, hovering);
     }
     drawName(x3, y1);
   }
 
 
   
   private boolean isHovering(int mouseX, int mouseY, int x1, int x2, int y1, int y2) {
     Window parent = getParent();
     boolean scrollEnabled = parent.isScrollingEnabled();
     int scroll = scrollEnabled ? parent.getScrollOffset() : 0;
     
     return (mouseX >= x1 && mouseY >= y1 && mouseX < x2 && mouseY < y2 && mouseY >= -scroll && mouseY < parent
       .getHeight() - 13 - scroll);
   }
 
   
   private void setTooltip() {
     String tooltip = this.setting.getDescription();
     
     if (this.setting.isLocked()) {
       
       tooltip = tooltip + "\n\nThis checkbox is locked to ";
       tooltip = tooltip + this.setting.isChecked() + ".";
     } 
     
     this.GUI.setTooltip(tooltip);
   }
 
   
   private void drawBackground(int x2, int x3, int y1, int y2) {
     float[] bgColor = this.GUI.getBgColor();
     float opacity = this.GUI.getOpacity();
     
     GL11.glColor4f(bgColor[0], bgColor[1], bgColor[2], opacity);
     GL11.glBegin(7);
     GL11.glVertex2i(x3, y1);
     GL11.glVertex2i(x3, y2);
     GL11.glVertex2i(x2, y2);
     GL11.glVertex2i(x2, y1);
     GL11.glEnd();
   }
 
   
   private void drawBox(int x1, int x3, int y1, int y2, boolean hovering) {
     float[] bgColor = this.GUI.getBgColor();
     float[] acColor = this.GUI.getAcColor();
     float opacity = this.GUI.getOpacity();
     
     GL11.glColor4f(bgColor[0], bgColor[1], bgColor[2], hovering ? (opacity * 1.5F) : opacity);
     
     GL11.glBegin(7);
     GL11.glVertex2i(x1, y1);
     GL11.glVertex2i(x1, y2);
     GL11.glVertex2i(x3, y2);
     GL11.glVertex2i(x3, y1);
     GL11.glEnd();
     
     GL11.glColor4f(acColor[0], acColor[1], acColor[2], 0.5F);
     GL11.glBegin(2);
     GL11.glVertex2i(x1, y1);
     GL11.glVertex2i(x1, y2);
     GL11.glVertex2i(x3, y2);
     GL11.glVertex2i(x3, y1);
     GL11.glEnd();
   }
 
   
   private void drawCheck(int x1, int y1, boolean hovering) {
     double xc1 = x1 + 2.5D;
     double xc2 = x1 + 3.5D;
     double xc3 = x1 + 4.5D;
     double xc4 = x1 + 7.5D;
     double xc5 = x1 + 8.5D;
     double yc1 = y1 + 2.5D;
     double yc2 = y1 + 3.5D;
     double yc3 = y1 + 5.5D;
     double yc4 = y1 + 6.5D;
     double yc5 = y1 + 8.5D;
 
     
     if (this.setting.isLocked()) {
       GL11.glColor4f(0.5F, 0.5F, 0.5F, 0.75F);
     } else {
       GL11.glColor4f(0.0F, hovering ? 1.0F : 0.85F, 0.0F, 1.0F);
     }  GL11.glBegin(7);
     GL11.glVertex2d(xc2, yc3);
     GL11.glVertex2d(xc3, yc4);
     GL11.glVertex2d(xc3, yc5);
     GL11.glVertex2d(xc1, yc4);
     GL11.glVertex2d(xc4, yc1);
     GL11.glVertex2d(xc5, yc2);
     GL11.glVertex2d(xc3, yc5);
     GL11.glVertex2d(xc3, yc4);
     GL11.glEnd();
 
     
     GL11.glColor4f(0.0625F, 0.0625F, 0.0625F, 0.5F);
     GL11.glBegin(2);
     GL11.glVertex2d(xc2, yc3);
     GL11.glVertex2d(xc3, yc4);
     GL11.glVertex2d(xc4, yc1);
     GL11.glVertex2d(xc5, yc2);
     GL11.glVertex2d(xc3, yc5);
     GL11.glVertex2d(xc1, yc4);
     GL11.glEnd();
   }
 
   
   private void drawName(int x3, int y1) {
     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
     GL11.glEnable(3553);
     
     String name = this.setting.getName();
     int tx = x3 + 2;
     int ty = y1 + 2;
     int color = this.setting.isLocked() ? 11184810 : 15790320;
     this.MC.field_1772.method_1729(name, tx, ty, color);
     
     GL11.glDisable(3553);
   }
 
 
 
   
   public int getDefaultWidth() { return this.MC.field_1772.method_1727(this.setting.getName()) + 13; }
 
 
 
 
   
   public int getDefaultHeight() { return 11; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\components\CheckboxComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */