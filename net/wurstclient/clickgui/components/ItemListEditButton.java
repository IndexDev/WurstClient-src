 package net.wurstclient.clickgui.components;
 
 import net.minecraft.class_327;
 import net.wurstclient.WurstClient;
 import net.wurstclient.clickgui.ClickGui;
 import net.wurstclient.clickgui.Component;
 import net.wurstclient.clickgui.screens.EditItemListScreen;
 import net.wurstclient.settings.ItemListSetting;
 import org.lwjgl.opengl.GL11;
 
 
 
 
 
 
 
 
 
 public final class ItemListEditButton
   extends Component
 {
   private final ItemListSetting setting;
   private int buttonWidth;
   
   public ItemListEditButton(ItemListSetting setting) {
     this.setting = setting;
     
     class_327 fr = WurstClient.MC.field_1772;
     this.buttonWidth = fr.method_1727("Edit...");
     
     setWidth(getDefaultWidth());
     setHeight(getDefaultHeight());
   }
 
 
   
   public void handleMouseClick(double mouseX, double mouseY, int mouseButton) {
     if (mouseButton != 0) {
       return;
     }
     if (mouseX < (getX() + getWidth() - this.buttonWidth - 4)) {
       return;
     }
     WurstClient.MC.method_1507(new EditItemListScreen(WurstClient.MC.field_1755, this.setting));
   }
 
 
 
   
   public void render(int mouseX, int mouseY, float partialTicks) {
     ClickGui gui = WurstClient.INSTANCE.getGui();
     float[] bgColor = gui.getBgColor();
     float[] acColor = gui.getAcColor();
     float opacity = gui.getOpacity();
     
     int x1 = getX();
     int x2 = x1 + getWidth();
     int x3 = x2 - this.buttonWidth - 4;
     int y1 = getY();
     int y2 = y1 + getHeight();
 
     
     int scroll = getParent().isScrollingEnabled() ? getParent().getScrollOffset() : 0;
 
     
     boolean hovering = (mouseX >= x1 && mouseY >= y1 && mouseX < x2 && mouseY < y2 && mouseY >= -scroll && mouseY < getParent().getHeight() - 13 - scroll);
     boolean hText = (hovering && mouseX < x3);
     boolean hBox = (hovering && mouseX >= x3);
 
     
     if (hText) {
       gui.setTooltip(this.setting.getDescription());
     }
     
     GL11.glColor4f(bgColor[0], bgColor[1], bgColor[2], opacity);
     GL11.glBegin(7);
     GL11.glVertex2i(x1, y1);
     GL11.glVertex2i(x1, y2);
     GL11.glVertex2i(x3, y2);
     GL11.glVertex2i(x3, y1);
     GL11.glEnd();
 
     
     GL11.glColor4f(bgColor[0], bgColor[1], bgColor[2], hBox ? (opacity * 1.5F) : opacity);
     
     GL11.glBegin(7);
     GL11.glVertex2i(x3, y1);
     GL11.glVertex2i(x3, y2);
     GL11.glVertex2i(x2, y2);
     GL11.glVertex2i(x2, y1);
     GL11.glEnd();
     GL11.glColor4f(acColor[0], acColor[1], acColor[2], 0.5F);
     GL11.glBegin(2);
     GL11.glVertex2i(x3, y1);
     GL11.glVertex2i(x3, y2);
     GL11.glVertex2i(x2, y2);
     GL11.glVertex2i(x2, y1);
     GL11.glEnd();
 
     
     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
     GL11.glEnable(3553);
     class_327 fr = WurstClient.MC.field_1772;
     String text = this.setting.getName() + ": " + this.setting.getItemNames().size();
     fr.method_1729(text, x1, (y1 + 2), 15790320);
     fr.method_1729("Edit...", (x3 + 2), (y1 + 2), 15790320);
     GL11.glDisable(3553);
   }
 
 
   
   public int getDefaultWidth() {
     class_327 fr = WurstClient.MC.field_1772;
     String text = this.setting.getName() + ": " + this.setting.getItemNames().size();
     return fr.method_1727(text) + this.buttonWidth + 6;
   }
 
 
 
   
   public int getDefaultHeight() { return 11; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\components\ItemListEditButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */