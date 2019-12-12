 package net.wurstclient.clickgui.components;
 
 import net.minecraft.class_310;
 import net.minecraft.class_327;
 import net.minecraft.class_437;
 import net.wurstclient.WurstClient;
 import net.wurstclient.clickgui.ClickGui;
 import net.wurstclient.clickgui.Component;
 import net.wurstclient.clickgui.Window;
 import net.wurstclient.clickgui.screens.EditSliderScreen;
 import net.wurstclient.settings.SliderSetting;
 import org.lwjgl.opengl.GL11;
 
 
 public final class SliderComponent
   extends Component
 {
   private final class_310 MC;
   private final ClickGui GUI;
   private final SliderSetting setting;
   private boolean dragging;
   
   public SliderComponent(SliderSetting setting) {
     this.MC = WurstClient.MC;
     this.GUI = WurstClient.INSTANCE.getGui();
 
 
 
 
 
     
     this.setting = setting;
     setWidth(getDefaultWidth());
     setHeight(getDefaultHeight());
   }
 
 
   
   public void handleMouseClick(double mouseX, double mouseY, int mouseButton) {
     if (mouseY < (getY() + 11)) {
       return;
     }
     switch (mouseButton) {
       
       case 0:
         handleLeftClick();
         break;
       
       case 1:
         handleRightClick();
         break;
     } 
   }
 
   
   private void handleLeftClick() {
     if (class_437.hasControlDown()) {
       this.MC.method_1507(new EditSliderScreen(this.MC.field_1755, this.setting));
     } else {
       this.dragging = true;
     } 
   }
 
   
   private void handleRightClick() { this.setting.setValue(this.setting.getDefaultValue()); }
 
 
 
   
   public void render(int mouseX, int mouseY, float partialTicks) {
     int x1 = getX();
     int x2 = x1 + getWidth();
     int x3 = x1 + 2;
     int x4 = x2 - 2;
     int y1 = getY();
     int y2 = y1 + getHeight();
     int y3 = y1 + 11;
     int y4 = y3 + 4;
     int y5 = y2 - 4;
     
     handleDragging(mouseX, x3, x4);
     
     boolean hovering = isHovering(mouseX, mouseY, x1, x2, y1, y2);
     boolean hSlider = ((hovering && mouseY >= y3) || this.dragging);
     boolean renderAsDisabled = (this.setting.isDisabled() || this.setting.isLocked());
     
     if (hovering && mouseY < y3) {
       setTooltip();
     } else if (hSlider && !this.dragging) {
       this.GUI.setTooltip("§e[ctrl]§r+§e[left-click]§r for precise input");
     } 
     
     if (renderAsDisabled) {
       
       hovering = false;
       hSlider = false;
     } 
     
     drawBackground(x1, x2, x3, x4, y1, y2, y4, y5);
     drawRail(x3, x4, y4, y5, hSlider, renderAsDisabled);
     drawKnob(x1, x2, y2, y3, hSlider, renderAsDisabled);
     drawNameAndValue(x1, x2, y1, renderAsDisabled);
   }
 
   
   private void handleDragging(int mouseX, int x3, int x4) {
     if (!this.dragging) {
       return;
     }
     if (!this.GUI.isLeftMouseButtonPressed()) {
       
       this.dragging = false;
       
       return;
     } 
     double sliderStartX = x3;
     double sliderWidth = (x4 - x3);
     double mousePercentage = (mouseX - sliderStartX) / sliderWidth;
     
     double min = this.setting.getMinimum();
     double range = this.setting.getRange();
     double value = min + range * mousePercentage;
     
     this.setting.setValue(value);
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
       
       tooltip = tooltip + "\n\nThis slider is locked to ";
       tooltip = tooltip + this.setting.getValueString() + ".";
     }
     else if (this.setting.isDisabled()) {
       tooltip = tooltip + "\n\nThis slider is disabled.";
     } 
     this.GUI.setTooltip(tooltip);
   }
 
 
   
   private void drawBackground(int x1, int x2, int x3, int x4, int y1, int y2, int y4, int y5) {
     float[] bgColor = this.GUI.getBgColor();
     float opacity = this.GUI.getOpacity();
     
     GL11.glColor4f(bgColor[0], bgColor[1], bgColor[2], opacity);
     GL11.glBegin(7);
     
     GL11.glVertex2i(x1, y1);
     GL11.glVertex2i(x1, y4);
     GL11.glVertex2i(x2, y4);
     GL11.glVertex2i(x2, y1);
     
     GL11.glVertex2i(x1, y5);
     GL11.glVertex2i(x1, y2);
     GL11.glVertex2i(x2, y2);
     GL11.glVertex2i(x2, y5);
     
     GL11.glVertex2i(x1, y4);
     GL11.glVertex2i(x1, y5);
     GL11.glVertex2i(x3, y5);
     GL11.glVertex2i(x3, y4);
     
     GL11.glVertex2i(x4, y4);
     GL11.glVertex2i(x4, y5);
     GL11.glVertex2i(x2, y5);
     GL11.glVertex2i(x2, y4);
     
     GL11.glEnd();
   }
 
 
   
   private void drawRail(int x3, int x4, int y4, int y5, boolean hSlider, boolean renderAsDisabled) {
     float[] bgColor = this.GUI.getBgColor();
     float[] acColor = this.GUI.getAcColor();
     float opacity = this.GUI.getOpacity();
     
     double xl1 = x3;
     double xl2 = x4;
     if (!renderAsDisabled && this.setting.isLimited()) {
       
       double ratio = (x4 - x3) / this.setting.getRange();
       xl1 += ratio * (this.setting.getUsableMin() - this.setting.getMinimum());
       xl2 += ratio * (this.setting.getUsableMax() - this.setting.getMaximum());
     } 
 
     
     GL11.glColor4f(1.0F, 0.0F, 0.0F, hSlider ? (opacity * 1.5F) : opacity);
     GL11.glBegin(7);
     GL11.glVertex2d(x3, y4);
     GL11.glVertex2d(x3, y5);
     GL11.glVertex2d(xl1, y5);
     GL11.glVertex2d(xl1, y4);
     GL11.glVertex2d(xl2, y4);
     GL11.glVertex2d(xl2, y5);
     GL11.glVertex2d(x4, y5);
     GL11.glVertex2d(x4, y4);
     GL11.glEnd();
 
     
     GL11.glColor4f(bgColor[0], bgColor[1], bgColor[2], hSlider ? (opacity * 1.5F) : opacity);
     
     GL11.glBegin(7);
     GL11.glVertex2d(xl1, y4);
     GL11.glVertex2d(xl1, y5);
     GL11.glVertex2d(xl2, y5);
     GL11.glVertex2d(xl2, y4);
     GL11.glEnd();
 
     
     GL11.glColor4f(acColor[0], acColor[1], acColor[2], 0.5F);
     GL11.glBegin(2);
     GL11.glVertex2i(x3, y4);
     GL11.glVertex2i(x3, y5);
     GL11.glVertex2i(x4, y5);
     GL11.glVertex2i(x4, y4);
     GL11.glEnd();
   }
 
 
 
   
   private void drawKnob(int x1, int x2, int y2, int y3, boolean hSlider, boolean renderAsDisabled) {
     double percentage = (this.setting.getValue() - this.setting.getMinimum()) / (this.setting.getMaximum() - this.setting.getMinimum());
     double xk1 = x1 + (x2 - x1 - 8) * percentage;
     double xk2 = xk1 + 8.0D;
     double yk1 = y3 + 1.5D;
     double yk2 = y2 - 1.5D;
 
     
     if (renderAsDisabled) {
       GL11.glColor4f(0.5F, 0.5F, 0.5F, 0.75F);
     } else {
       
       float f = (float)(2.0D * percentage);
       GL11.glColor4f(f, 2.0F - f, 0.0F, hSlider ? 1.0F : 0.75F);
     } 
     GL11.glBegin(7);
     GL11.glVertex2d(xk1, yk1);
     GL11.glVertex2d(xk1, yk2);
     GL11.glVertex2d(xk2, yk2);
     GL11.glVertex2d(xk2, yk1);
     GL11.glEnd();
 
     
     GL11.glColor4f(0.0625F, 0.0625F, 0.0625F, 0.5F);
     GL11.glBegin(2);
     GL11.glVertex2d(xk1, yk1);
     GL11.glVertex2d(xk1, yk2);
     GL11.glVertex2d(xk2, yk2);
     GL11.glVertex2d(xk2, yk1);
     GL11.glEnd();
   }
 
 
   
   private void drawNameAndValue(int x1, int x2, int y1, boolean renderAsDisabled) {
     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
     GL11.glEnable(3553);
     
     class_327 tr = this.MC.field_1772;
     String name = this.setting.getName();
     String value = this.setting.getValueString();
     int valueWidth = tr.method_1727(value);
     int color = renderAsDisabled ? 11184810 : 15790320;
     tr.method_1729(name, x1, (y1 + 2), color);
     tr.method_1729(value, (x2 - valueWidth), (y1 + 2), color);
     
     GL11.glDisable(3553);
   }
 
 
   
   public int getDefaultWidth() {
     class_327 tr = this.MC.field_1772;
     int nameWitdh = tr.method_1727(this.setting.getName());
     int valueWidth = tr.method_1727(this.setting.getValueString());
     return nameWitdh + valueWidth + 6;
   }
 
 
 
   
   public int getDefaultHeight() { return 22; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\components\SliderComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */