 package net.wurstclient.clickgui.components;
 
 import java.util.Arrays;
 import java.util.stream.IntStream;
 import java.util.stream.Stream;
 import net.minecraft.class_327;
 import net.wurstclient.WurstClient;
 import net.wurstclient.clickgui.ClickGui;
 import net.wurstclient.clickgui.ComboBoxPopup;
 import net.wurstclient.clickgui.Component;
 import net.wurstclient.clickgui.Window;
 import net.wurstclient.settings.EnumSetting;
 import org.lwjgl.opengl.GL11;
 
 
 public final class ComboBoxComponent<T extends Enum>
   extends Component
 {
   private final ClickGui gui;
   private final class_327 tr;
   private final EnumSetting<T> setting;
   private final int popupWidth;
   private ComboBoxPopup popup;
   
   public ComboBoxComponent(EnumSetting<T> setting) {
     this.gui = WurstClient.INSTANCE.getGui();
     this.tr = WurstClient.MC.field_1772;
 
 
 
 
 
 
     
     this.setting = setting;
     this.popupWidth = calculatePopupWitdh();
     
     setWidth(getDefaultWidth());
     setHeight(getDefaultHeight());
   }
 
   
   private int calculatePopupWitdh() {
     Stream<T> values = Arrays.stream(this.setting.getValues());
     Stream<String> vNames = values.map(Enum::toString);
     IntStream vWidths = vNames.mapToInt(s -> this.tr.method_1727(s));
     return vWidths.max().getAsInt();
   }
 
 
   
   public void handleMouseClick(double mouseX, double mouseY, int mouseButton) {
     if (mouseX < (getX() + getWidth() - this.popupWidth - 15)) {
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
     if (isPopupOpen()) {
       
       this.popup.close();
       this.popup = null;
       
       return;
     } 
     this.popup = new ComboBoxPopup(this, this.setting, this.popupWidth);
     this.gui.addPopup(this.popup);
   }
 
   
   private void handleRightClick() {
     if (isPopupOpen()) {
       return;
     }
     T defaultSelected = (T)this.setting.getDefaultSelected();
     this.setting.setSelected(defaultSelected);
   }
 
 
   
   private boolean isPopupOpen() { return (this.popup != null && !this.popup.isClosing()); }
 
 
 
   
   public void render(int mouseX, int mouseY, float partialTicks) {
     int x1 = getX();
     int x2 = x1 + getWidth();
     int x3 = x2 - 11;
     int x4 = x3 - this.popupWidth - 4;
     int y1 = getY();
     int y2 = y1 + getHeight();
     
     boolean hovering = isHovering(mouseX, mouseY, x1, x2, y1, y2);
     boolean hText = (hovering && mouseX < x4);
     boolean hBox = (hovering && mouseX >= x4);
 
     
     if (hText) {
       this.gui.setTooltip(this.setting.getDescription());
     }
     drawBackground(x1, x4, y1, y2);
     drawBox(x2, x4, y1, y2, hBox);
     
     drawSeparator(x3, y1, y2);
     drawArrow(x2, x3, y1, y2, hBox);
     
     drawNameAndValue(x1, x4, y1);
   }
 
 
   
   private boolean isHovering(int mouseX, int mouseY, int x1, int x2, int y1, int y2) {
     Window parent = getParent();
     boolean scrollEnabled = parent.isScrollingEnabled();
     int scroll = scrollEnabled ? parent.getScrollOffset() : 0;
     
     return (mouseX >= x1 && mouseY >= y1 && mouseX < x2 && mouseY < y2 && mouseY >= -scroll && mouseY < parent
       .getHeight() - 13 - scroll);
   }
 
   
   private void drawBackground(int x1, int x4, int y1, int y2) {
     float[] bgColor = this.gui.getBgColor();
     float opacity = this.gui.getOpacity();
     GL11.glColor4f(bgColor[0], bgColor[1], bgColor[2], opacity);
     
     GL11.glBegin(7);
     GL11.glVertex2i(x1, y1);
     GL11.glVertex2i(x1, y2);
     GL11.glVertex2i(x4, y2);
     GL11.glVertex2i(x4, y1);
     GL11.glEnd();
   }
 
   
   private void drawBox(int x2, int x4, int y1, int y2, boolean hBox) {
     float[] bgColor = this.gui.getBgColor();
     float[] acColor = this.gui.getAcColor();
     float opacity = this.gui.getOpacity();
 
     
     float bgAlpha = hBox ? (opacity * 1.5F) : opacity;
     GL11.glColor4f(bgColor[0], bgColor[1], bgColor[2], bgAlpha);
     GL11.glBegin(7);
     GL11.glVertex2i(x4, y1);
     GL11.glVertex2i(x4, y2);
     GL11.glVertex2i(x2, y2);
     GL11.glVertex2i(x2, y1);
     GL11.glEnd();
 
     
     GL11.glColor4f(acColor[0], acColor[1], acColor[2], 0.5F);
     GL11.glBegin(2);
     GL11.glVertex2i(x4, y1);
     GL11.glVertex2i(x4, y2);
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
 
   
   private void drawArrow(int x2, int x3, int y1, int y2, boolean hBox) {
     double ya2, ya1, xa1 = (x3 + 1);
     double xa2 = (x3 + x2) / 2.0D;
     double xa3 = (x2 - 1);
 
 
     
     if (isPopupOpen()) {
       
       ya1 = y2 - 3.5D;
       ya2 = (y1 + 3);
       GL11.glColor4f(hBox ? 1.0F : 0.85F, 0.0F, 0.0F, 1.0F);
     }
     else {
       
       ya1 = y1 + 3.5D;
       ya2 = (y2 - 3);
       GL11.glColor4f(0.0F, hBox ? 1.0F : 0.85F, 0.0F, 1.0F);
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
 
   
   private void drawNameAndValue(int x1, int x4, int y1) {
     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
     GL11.glEnable(3553);
     
     String name = this.setting.getName();
     String value = "" + this.setting.getSelected();
     int color = 15790320;
     
     this.tr.method_1729(name, x1, (y1 + 2), color);
     this.tr.method_1729(value, (x4 + 2), (y1 + 2), color);
     
     GL11.glDisable(3553);
   }
 
 
 
   
   public int getDefaultWidth() { return this.tr.method_1727(this.setting.getName()) + this.popupWidth + 17; }
 
 
 
 
   
   public int getDefaultHeight() { return 11; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\components\ComboBoxComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */