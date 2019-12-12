 package net.wurstclient.clickgui.screens;
 
 import java.util.List;
 import net.minecraft.class_1792;
 import net.minecraft.class_1799;
 import net.minecraft.class_2246;
 import net.minecraft.class_2378;
 import net.minecraft.class_2585;
 import net.minecraft.class_2960;
 import net.minecraft.class_308;
 import net.minecraft.class_310;
 import net.minecraft.class_327;
 import net.minecraft.class_342;
 import net.minecraft.class_358;
 import net.minecraft.class_410;
 import net.minecraft.class_4185;
 import net.minecraft.class_4357;
 import net.minecraft.class_437;
 import net.wurstclient.settings.ItemListSetting;
 import org.lwjgl.opengl.GL11;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public final class EditItemListScreen
   extends class_437
 {
   private final class_437 prevScreen;
   private final ItemListSetting itemList;
   private ListGui listGui;
   private class_342 itemNameField;
   private class_4185 addButton;
   private class_4185 removeButton;
   private class_4185 doneButton;
   private class_1792 itemToAdd;
   
   public EditItemListScreen(class_437 prevScreen, ItemListSetting itemList) {
     super(new class_2585(""));
     this.prevScreen = prevScreen;
     this.itemList = itemList;
   }
 
 
 
   
   public boolean isPauseScreen() { return false; }
 
 
 
   
   public void init() {
     this.listGui = new ListGui(this.minecraft, this, this.itemList.getItemNames());
     
     this.itemNameField = new class_342(this.minecraft.field_1772, this.width / 2 - 152, this.height - 55, 150, 18, "");
     
     this.children.add(this.itemNameField);
     
     addButton(this.addButton = new class_4185(this.width / 2 - 2, this.height - 56, 30, 20, "Add", b -> {
             
             this.itemList.add(this.itemToAdd);
             this.itemNameField.method_1852("");
           }));
     
     addButton(this.removeButton = new class_4185(this.width / 2 + 52, this.height - 56, 100, 20, "Remove Selected", b -> 
           
           this.itemList.remove(this.listGui.selected)));
     
     addButton(new class_4185(this.width - 108, 8, 100, 20, "Reset to Defaults", b -> 
           this.minecraft.method_1507(new class_410((), new class_2585("Reset to Defaults"), new class_2585("Are you sure?")))));
 
 
 
 
 
     
     addButton(this.doneButton = new class_4185(this.width / 2 - 100, this.height - 28, 200, 20, "Done", b -> 
           this.minecraft.method_1507(this.prevScreen)));
   }
 
 
   
   public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
     boolean childClicked = super.mouseClicked(mouseX, mouseY, mouseButton);
     
     this.itemNameField.mouseClicked(mouseX, mouseY, mouseButton);
     this.listGui.mouseClicked(mouseX, mouseY, mouseButton);
     
     if (!childClicked && (mouseX < ((this.width - 220) / 2) || mouseX > (this.width / 2 + 129) || mouseY < 32.0D || mouseY > (this.height - 64)))
     {
       this.listGui.selected = -1;
     }
     return childClicked;
   }
 
 
 
   
   public boolean mouseDragged(double double_1, double double_2, int int_1, double double_3, double double_4) {
     this.listGui.mouseDragged(double_1, double_2, int_1, double_3, double_4);
     return super.mouseDragged(double_1, double_2, int_1, double_3, double_4);
   }
 
 
 
   
   public boolean mouseReleased(double double_1, double double_2, int int_1) {
     this.listGui.mouseReleased(double_1, double_2, int_1);
     return super.mouseReleased(double_1, double_2, int_1);
   }
 
 
 
   
   public boolean mouseScrolled(double double_1, double double_2, double double_3) {
     this.listGui.mouseScrolled(double_1, double_2, double_3);
     return super.mouseScrolled(double_1, double_2, double_3);
   }
 
 
   
   public boolean keyPressed(int keyCode, int scanCode, int int_3) {
     if (keyCode == 257) {
       this.addButton.onPress();
     } else if (keyCode == 261) {
       this.removeButton.onPress();
     } else if (keyCode == 256) {
       this.doneButton.onPress();
     } 
     return super.keyPressed(keyCode, scanCode, int_3);
   }
 
 
   
   public void tick() {
     this.itemNameField.method_1865();
     
     this.itemToAdd = (class_1792)class_2378.field_11142.method_10223(new class_2960(this.itemNameField.method_1882()));
     this.addButton.active = (this.itemToAdd != null);
     
     this.removeButton
       .active = (this.listGui.selected >= 0 && this.listGui.selected < this.listGui.list.size());
   }
 
 
   
   public void render(int mouseX, int mouseY, float partialTicks) {
     renderBackground();
     this.listGui.render(mouseX, mouseY, partialTicks);
     
     drawCenteredString(this.minecraft.field_1772, this.itemList
         .getName() + " (" + this.listGui.getItemCount() + ")", this.width / 2, 12, 16777215);
 
     
     this.itemNameField.render(mouseX, mouseY, partialTicks);
     super.render(mouseX, mouseY, partialTicks);
     
     GL11.glPushMatrix();
     GL11.glTranslated((-64 + this.width / 2 - 152), 0.0D, 0.0D);
     
     if (this.itemNameField.method_1882().isEmpty() && !this.itemNameField.isFocused()) {
       drawString(this.minecraft.field_1772, "item name or ID", 68, this.height - 50, 8421504);
     }
     
     fill(48, this.height - 56, 64, this.height - 36, -6250336);
     fill(49, this.height - 55, 64, this.height - 37, -16777216);
     fill(214, this.height - 56, 244, this.height - 55, -6250336);
     fill(214, this.height - 37, 244, this.height - 36, -6250336);
     fill(244, this.height - 56, 246, this.height - 36, -6250336);
     fill(214, this.height - 55, 243, this.height - 52, -16777216);
     fill(214, this.height - 40, 243, this.height - 37, -16777216);
     fill(215, this.height - 55, 216, this.height - 37, -16777216);
     fill(242, this.height - 55, 245, this.height - 37, -16777216);
     this.listGui.renderIconAndGetName(new class_1799(this.itemToAdd), 52, this.height - 52, false);
 
     
     GL11.glPopMatrix();
   }
   
   private static class ListGui
     extends class_358 {
     private final class_310 mc;
     private final List<String> list;
     private int selected = -1;
 
 
     
     public ListGui(class_310 mc, EditItemListScreen screen, List<String> list) {
       super(mc, screen.width, screen.height, 32, screen.height - 64, 30);
       this.mc = mc;
       this.list = list;
     }
 
 
 
     
     protected int getItemCount() { return this.list.size(); }
 
 
 
 
     
     protected boolean selectItem(int index, int int_2, double var3, double var4) {
       if (index >= 0 && index < this.list.size()) {
         this.selected = index;
       }
       return true;
     }
 
 
 
     
     protected boolean isSelectedItem(int index) { return (index == this.selected); }
 
 
 
 
     
     protected void renderBackground() {}
 
 
 
 
     
     protected void renderItem(int index, int x, int y, int var4, int var5, int var6, float partialTicks) {
       String name = (String)this.list.get(index);
       class_1792 item = (class_1792)class_2378.field_11142.method_10223(new class_2960(name));
       class_1799 stack = new class_1799(item);
       class_327 fr = this.mc.field_1772;
 
       
       String displayName = renderIconAndGetName(stack, x + 1, y + 1, true);
       fr.method_1729(displayName, (x + 28), y, 15790320);
       fr.method_1729(name, (x + 28), (y + 9), 10526880);
       fr.method_1729("ID: " + class_2378.field_11142.method_10221(item).toString(), (x + 28), (y + 18), 10526880);
     }
 
 
 
     
     private String renderIconAndGetName(class_1799 stack, int x, int y, boolean large) {
       if (stack.method_7960()) {
         
         GL11.glPushMatrix();
         GL11.glTranslated(x, y, 0.0D);
         if (large) {
           GL11.glScaled(1.5D, 1.5D, 1.5D);
         } else {
           GL11.glScaled(0.75D, 0.75D, 0.75D);
         } 
         class_308.method_1453();
         this.mc.method_1480()
           .method_4023(new class_1799(class_2246.field_10219), 0, 0);
         class_308.method_1450();
         GL11.glPopMatrix();
         
         GL11.glPushMatrix();
         GL11.glTranslated(x, y, 0.0D);
         if (large)
           GL11.glScaled(2.0D, 2.0D, 2.0D); 
         GL11.glDisable(2929);
         class_327 fr = this.mc.field_1772;
         fr.method_1720("?", 3.0F, 2.0F, 15790320);
         GL11.glEnable(2929);
         GL11.glPopMatrix();
         
         return class_4357.field_19632 + "unknown item" + class_4357.field_19633;
       } 
 
 
       
       GL11.glPushMatrix();
       GL11.glTranslated(x, y, 0.0D);
       if (large) {
         GL11.glScaled(1.5D, 1.5D, 1.5D);
       } else {
         GL11.glScaled(0.75D, 0.75D, 0.75D);
       } 
       class_308.method_1453();
       this.mc.method_1480().method_4023(stack, 0, 0);
       class_308.method_1450();
       
       GL11.glPopMatrix();
       
       return stack.method_7964().method_10863();
     }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\screens\EditItemListScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */