 package net.wurstclient.clickgui.components;
 
 import net.minecraft.class_1799;
 import net.minecraft.class_2246;
 import net.minecraft.class_308;
 import net.minecraft.class_327;
 import net.minecraft.class_4357;
 import net.minecraft.class_437;
 import net.wurstclient.WurstClient;
 import net.wurstclient.clickgui.ClickGui;
 import net.wurstclient.clickgui.Component;
 import net.wurstclient.clickgui.screens.EditBlockScreen;
 import net.wurstclient.settings.BlockSetting;
 import org.lwjgl.opengl.GL11;
 
 
 
 
 
 
 
 
 
 
 public final class BlockComponent
   extends Component
 {
   private static final int BLOCK_WITDH = 24;
   private final BlockSetting setting;
   
   public BlockComponent(BlockSetting setting) {
     this.setting = setting;
     
     setWidth(getDefaultWidth());
     setHeight(getDefaultHeight());
   }
 
 
   
   public void handleMouseClick(double mouseX, double mouseY, int mouseButton) {
     if (mouseX < (getX() + getWidth() - 24)) {
       return;
     }
     if (mouseButton == 0) {
       
       class_437 currentScreen = WurstClient.MC.field_1755;
       EditBlockScreen editScreen = new EditBlockScreen(currentScreen, this.setting);
       
       WurstClient.MC.method_1507(editScreen);
     }
     else if (mouseButton == 1) {
       this.setting.resetToDefault();
     } 
   }
 
   
   public void render(int mouseX, int mouseY, float partialTicks) {
     ClickGui gui = WurstClient.INSTANCE.getGui();
     float[] bgColor = gui.getBgColor();
     float opacity = gui.getOpacity();
     
     int x1 = getX();
     int x2 = x1 + getWidth();
     int x3 = x2 - 24;
     int y1 = getY();
     int y2 = y1 + getHeight();
 
     
     int scroll = getParent().isScrollingEnabled() ? getParent().getScrollOffset() : 0;
 
     
     boolean hovering = (mouseX >= x1 && mouseY >= y1 && mouseX < x2 && mouseY < y2 && mouseY >= -scroll && mouseY < getParent().getHeight() - 13 - scroll);
     boolean hText = (hovering && mouseX < x3);
     boolean hBlock = (hovering && mouseX >= x3);
     
     class_1799 stack = new class_1799(this.setting.getBlock());
 
     
     if (hText) {
       gui.setTooltip(this.setting.getDescription());
     } else if (hBlock) {
       
       String tooltip = "§6Name:§r " + getBlockName(stack);
       tooltip = tooltip + "\n§6ID:§r " + this.setting.getBlockName();
       tooltip = tooltip + "\n\n§e[left-click]§r to edit";
       tooltip = tooltip + "\n§e[right-click]§r to reset";
       gui.setTooltip(tooltip);
     } 
 
     
     GL11.glColor4f(bgColor[0], bgColor[1], bgColor[2], opacity);
     GL11.glBegin(7);
     GL11.glVertex2i(x1, y1);
     GL11.glVertex2i(x1, y2);
     GL11.glVertex2i(x2, y2);
     GL11.glVertex2i(x2, y1);
     GL11.glEnd();
 
     
     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
     GL11.glEnable(3553);
     class_327 fr = WurstClient.MC.field_1772;
     String text = this.setting.getName() + ":";
     fr.method_1729(text, x1, (y1 + 2), 15790320);
     
     renderIcon(stack, x3, y1, true);
     
     GL11.glDisable(3553);
   }
 
 
   
   public int getDefaultWidth() {
     class_327 tr = WurstClient.MC.field_1772;
     String text = this.setting.getName() + ":";
     return tr.method_1727(text) + 24 + 4;
   }
 
 
 
   
   public int getDefaultHeight() { return 24; }
 
 
   
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
 
   
   private String getBlockName(class_1799 stack) {
     if (stack.method_7960()) {
       return class_4357.field_19632 + "unknown block" + class_4357.field_19633;
     }
     
     return stack.method_7964().method_10863();
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\components\BlockComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */