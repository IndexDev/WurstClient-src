 package net.wurstclient.clickgui;
 
 import net.minecraft.class_327;
 import net.wurstclient.WurstClient;
 import net.wurstclient.settings.EnumSetting;
 import org.lwjgl.opengl.GL11;
 
 
 
 
 
 
 
 
 public final class ComboBoxPopup<T extends Enum>
   extends Popup
 {
   private final ClickGui gui = WurstClient.INSTANCE.getGui();
   private final class_327 tr = WurstClient.MC.field_1772;
   
   private final EnumSetting<T> setting;
   
   private final int popupWidth;
 
   
   public ComboBoxPopup(Component owner, EnumSetting<T> setting, int popupWidth) {
     super(owner);
     this.setting = setting;
     this.popupWidth = popupWidth;
     
     setWidth(getDefaultWidth());
     setHeight(getDefaultHeight());
     
     setX(owner.getWidth() - getWidth());
     setY(owner.getHeight());
   }
 
 
   
   public void handleMouseClick(int mouseX, int mouseY, int mouseButton) {
     if (mouseButton != 0) {
       return;
     }
     int yi1 = getY() - 11; Enum[] arrayOfEnum; int i; byte b;
     for (arrayOfEnum = this.setting.getValues(), i = arrayOfEnum.length, b = 0; b < i; ) { T value = (T)arrayOfEnum[b];
       
       if (value != this.setting.getSelected()) {
 
         
         yi1 += 11;
         int yi2 = yi1 + 11;
         
         if (mouseY >= yi1 && mouseY < yi2) {
 
           
           this.setting.setSelected(value);
           close();
           break;
         } 
       } 
       b++; }
   
   }
   public void render(int mouseX, int mouseY) {
     int x1 = getX();
     int x2 = x1 + getWidth();
     int y1 = getY();
     int y2 = y1 + getHeight();
     
     boolean hovering = isHovering(mouseX, mouseY, x1, x2, y1, y2);
     
     if (hovering) {
       this.gui.setTooltip("");
     }
     drawOutline(x1, x2, y1, y2);
     
     int yi1 = y1 - 11; Enum[] arrayOfEnum; int i; byte b;
     for (arrayOfEnum = this.setting.getValues(), i = arrayOfEnum.length, b = 0; b < i; ) { T value = (T)arrayOfEnum[b];
       
       if (value != this.setting.getSelected()) {
 
         
         yi1 += 11;
         int yi2 = yi1 + 11;
         
         boolean hValue = (hovering && mouseY >= yi1 && mouseY < yi2);
         drawValueBackground(x1, x2, yi1, yi2, hValue);
         
         drawValueName(x1, yi1, value);
       } 
       b++; }
   
   }
 
   
   private boolean isHovering(int mouseX, int mouseY, int x1, int x2, int y1, int y2) { return (mouseX >= x1 && mouseY >= y1 && mouseX < x2 && mouseY < y2); }
 
 
   
   private void drawOutline(int x1, int x2, int y1, int y2) {
     float[] acColor = this.gui.getAcColor();
     GL11.glColor4f(acColor[0], acColor[1], acColor[2], 0.5F);
     
     GL11.glBegin(2);
     GL11.glVertex2i(x1, y1);
     GL11.glVertex2i(x1, y2);
     GL11.glVertex2i(x2, y2);
     GL11.glVertex2i(x2, y1);
     GL11.glEnd();
   }
 
 
   
   private void drawValueBackground(int x1, int x2, int yi1, int yi2, boolean hValue) {
     float[] bgColor = this.gui.getBgColor();
     float alpha = this.gui.getOpacity() * (hValue ? 1.5F : 1.0F);
     GL11.glColor4f(bgColor[0], bgColor[1], bgColor[2], alpha);
     
     GL11.glBegin(7);
     GL11.glVertex2i(x1, yi1);
     GL11.glVertex2i(x1, yi2);
     GL11.glVertex2i(x2, yi2);
     GL11.glVertex2i(x2, yi1);
     GL11.glEnd();
   }
 
   
   private void drawValueName(int x1, int yi1, Enum value) {
     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
     GL11.glEnable(3553);
     this.tr.method_1729(value.toString(), (x1 + 2), (yi1 + 2), 15790320);
     GL11.glDisable(3553);
   }
 
 
 
   
   public int getDefaultWidth() { return this.popupWidth + 15; }
 
 
 
   
   public int getDefaultHeight() {
     int numValues = this.setting.getValues().length;
     return (numValues - 1) * 11;
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\ComboBoxPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */