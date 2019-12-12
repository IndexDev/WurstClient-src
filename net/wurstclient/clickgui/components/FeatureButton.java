 package net.wurstclient.clickgui.components;
 
 import java.util.Objects;
 import net.minecraft.class_310;
 import net.minecraft.class_327;
 import net.wurstclient.Feature;
 import net.wurstclient.WurstClient;
 import net.wurstclient.clickgui.ClickGui;
 import net.wurstclient.clickgui.Component;
 import net.wurstclient.clickgui.SettingsWindow;
 import net.wurstclient.clickgui.Window;
 import org.lwjgl.opengl.GL11;
 
 
 public final class FeatureButton
   extends Component
 {
   private final class_310 MC;
   private final ClickGui GUI;
   private final Feature feature;
   private final boolean hasSettings;
   private Window settingsWindow;
   
   public FeatureButton(Feature feature) {
     this.MC = WurstClient.MC;
     this.GUI = WurstClient.INSTANCE.getGui();
 
 
 
 
 
 
 
     
     this.feature = (Feature)Objects.requireNonNull(feature);
     setWidth(getDefaultWidth());
     setHeight(getDefaultHeight());
     this.hasSettings = !feature.getSettings().isEmpty();
   }
 
 
   
   public void handleMouseClick(double mouseX, double mouseY, int mouseButton) {
     if (mouseButton != 0) {
       return;
     }
     if (this.hasSettings && (mouseX > (getX() + getWidth() - 12) || this.feature
       .getPrimaryAction().isEmpty())) {
       
       if (isSettingsWindowOpen()) {
         closeSettingsWindow();
       } else {
         openSettingsWindow();
       } 
       
       return;
     } 
     this.feature.doPrimaryAction();
   }
 
 
   
   private boolean isSettingsWindowOpen() { return (this.settingsWindow != null && !this.settingsWindow.isClosing()); }
 
 
   
   private void openSettingsWindow() {
     this.settingsWindow = new SettingsWindow(this.feature, getParent(), getY());
     this.GUI.addWindow(this.settingsWindow);
   }
 
   
   private void closeSettingsWindow() {
     this.settingsWindow.close();
     this.settingsWindow = null;
   }
 
 
   
   public void render(int mouseX, int mouseY, float partialTicks) {
     int x1 = getX();
     int x2 = x1 + getWidth();
     int x3 = this.hasSettings ? (x2 - 11) : x2;
     int y1 = getY();
     int y2 = y1 + getHeight();
     
     boolean hovering = isHovering(mouseX, mouseY, x1, x2, y1, y2);
     boolean hHack = (hovering && mouseX < x3);
     boolean hSettings = (hovering && mouseX >= x3);
     
     if (hHack) {
       setTooltip();
     }
     drawButtonBackground(x1, x3, y1, y2, hHack);
     
     if (this.hasSettings) {
       drawSettingsBackground(x2, x3, y1, y2, hSettings);
     }
     drawOutline(x1, x2, y1, y2);
     
     if (this.hasSettings) {
       
       drawSeparator(x3, y1, y2);
       drawSettingsArrow(x2, x3, y1, y2, hSettings);
     } 
     
     drawName(x1, x3, y1);
   }
 
 
   
   private boolean isHovering(int mouseX, int mouseY, int x1, int x2, int y1, int y2) {
     Window parent = getParent();
     boolean scrollEnabled = parent.isScrollingEnabled();
     int scroll = scrollEnabled ? parent.getScrollOffset() : 0;
     
     return (mouseX >= x1 && mouseY >= y1 && mouseX < x2 && mouseY < y2 && mouseY >= -scroll && mouseY < parent
       .getHeight() - 13 - scroll);
   }
 
   
   private void setTooltip() {
     String tooltip = this.feature.getDescription();
 
 
 
 
 
 
 
 
 
 
     
     this.GUI.setTooltip(tooltip);
   }
 
 
   
   private void drawButtonBackground(int x1, int x3, int y1, int y2, boolean hHack) {
     float[] bgColor = this.GUI.getBgColor();
     float opacity = this.GUI.getOpacity();
     
     GL11.glBegin(7);
     
     if (this.feature.isEnabled()) {
 
 
       
       GL11.glColor4f(0.0F, 1.0F, 0.0F, hHack ? (opacity * 1.5F) : opacity);
     } else {
       GL11.glColor4f(bgColor[0], bgColor[1], bgColor[2], hHack ? (opacity * 1.5F) : opacity);
     } 
     
     GL11.glVertex2i(x1, y1);
     GL11.glVertex2i(x1, y2);
     GL11.glVertex2i(x3, y2);
     GL11.glVertex2i(x3, y1);
     
     GL11.glEnd();
   }
 
 
   
   private void drawSettingsBackground(int x2, int x3, int y1, int y2, boolean hSettings) {
     float[] bgColor = this.GUI.getBgColor();
     float opacity = this.GUI.getOpacity();
     
     GL11.glBegin(7);
     GL11.glColor4f(bgColor[0], bgColor[1], bgColor[2], hSettings ? (opacity * 1.5F) : opacity);
     
     GL11.glVertex2i(x3, y1);
     GL11.glVertex2i(x3, y2);
     GL11.glVertex2i(x2, y2);
     GL11.glVertex2i(x2, y1);
     GL11.glEnd();
   }
 
   
   private void drawOutline(int x1, int x2, int y1, int y2) {
     float[] acColor = this.GUI.getAcColor();
     
     GL11.glBegin(2);
     GL11.glColor4f(acColor[0], acColor[1], acColor[2], 0.5F);
     GL11.glVertex2i(x1, y1);
     GL11.glVertex2i(x1, y2);
     GL11.glVertex2i(x2, y2);
     GL11.glVertex2i(x2, y1);
     GL11.glEnd();
   }
 
 
   
   private void drawSeparator(int x3, int y1, int y2) {
     GL11.glBegin(1);
     GL11.glVertex2i(x3, y1);
     GL11.glVertex2i(x3, y2);
     GL11.glEnd();
   }
 
 
   
   private void drawSettingsArrow(int x2, int x3, int y1, int y2, boolean hSettings) {
     double ya2, ya1, xa1 = (x3 + 1);
     double xa2 = (x3 + x2) / 2.0D;
     double xa3 = (x2 - 1);
 
 
     
     if (isSettingsWindowOpen()) {
       
       ya1 = y2 - 3.5D;
       ya2 = (y1 + 3);
       GL11.glColor4f(hSettings ? 1.0F : 0.85F, 0.0F, 0.0F, 1.0F);
     }
     else {
       
       ya1 = y1 + 3.5D;
       ya2 = (y2 - 3);
       GL11.glColor4f(0.0F, hSettings ? 1.0F : 0.85F, 0.0F, 1.0F);
     } 
 
     
     GL11.glBegin(4);
     GL11.glVertex2d(xa1, ya1);
     GL11.glVertex2d(xa3, ya1);
     GL11.glVertex2d(xa2, ya2);
     GL11.glEnd();
 
     
     GL11.glColor4f(0.0625F, 0.0625F, 0.0625F, 0.5F);
     GL11.glBegin(2);
     GL11.glVertex2d(xa1, ya1);
     GL11.glVertex2d(xa3, ya1);
     GL11.glVertex2d(xa2, ya2);
     GL11.glEnd();
   }
 
   
   private void drawName(int x1, int x3, int y1) {
     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
     GL11.glEnable(3553);
     
     class_327 tr = this.MC.field_1772;
     String name = this.feature.getName();
     int nameWidth = tr.method_1727(name);
     int tx = x1 + (x3 - x1 - nameWidth) / 2;
     int ty = y1 + 2;
     
     tr.method_1729(name, tx, ty, 15790320);
     
     GL11.glDisable(3553);
   }
 
 
   
   public int getDefaultWidth() {
     String name = this.feature.getName();
     class_327 tr = this.MC.field_1772;
     int width = tr.method_1727(name) + 4;
     if (this.hasSettings) {
       width += 11;
     }
     return width;
   }
 
 
 
   
   public int getDefaultHeight() { return 11; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\components\FeatureButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */