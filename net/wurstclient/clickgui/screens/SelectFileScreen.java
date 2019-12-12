 package net.wurstclient.clickgui.screens;
 
 import java.nio.file.Path;
 import java.util.ArrayList;
 import java.util.List;
 import net.minecraft.class_156;
 import net.minecraft.class_2585;
 import net.minecraft.class_310;
 import net.minecraft.class_327;
 import net.minecraft.class_358;
 import net.minecraft.class_410;
 import net.minecraft.class_4185;
 import net.minecraft.class_437;
 import net.wurstclient.settings.FileSetting;
 
 
 
 
 
 
 
 
 
 
 
 
 public final class SelectFileScreen
   extends class_437
 {
   private final class_437 prevScreen;
   private final FileSetting setting;
   private ListGui listGui;
   private class_4185 doneButton;
   
   public SelectFileScreen(class_437 prevScreen, FileSetting blockList) {
     super(new class_2585(""));
     this.prevScreen = prevScreen;
     this.setting = blockList;
   }
 
 
 
   
   public boolean isPauseScreen() { return false; }
 
 
 
   
   public void init() {
     this.listGui = new ListGui(this.minecraft, this, this.setting.listFiles());
     
     addButton(new class_4185(8, 8, 100, 20, "Open Folder", b -> 
           openFolder()));
     addButton(new class_4185(this.width - 108, 8, 100, 20, "Reset to Defaults", b -> 
           askToConfirmReset()));
     
     this.doneButton = (class_4185)addButton(new class_4185(this.width / 2 - 102, this.height - 48, 100, 20, "Done", b -> 
           done()));
     addButton(new class_4185(this.width / 2 + 2, this.height - 48, 100, 20, "Cancel", b -> 
           openPrevScreen()));
   }
 
 
   
   private void openFolder() { class_156.method_668().method_672(this.setting.getFolder().toFile()); }
 
 
 
   
   private void openPrevScreen() { this.minecraft.method_1507(this.prevScreen); }
 
 
   
   private void done() {
     if (this.listGui.selected >= 0 && this.listGui.selected < this.listGui.list.size()) {
       
       Path path = (Path)this.listGui.list.get(this.listGui.selected);
       String fileName = "" + path.getFileName();
       this.setting.setSelectedFile(fileName);
     } 
     
     openPrevScreen();
   }
 
   
   private void askToConfirmReset() {
     class_2585 title = new class_2585("Reset Folder");
 
     
     class_2585 message = new class_2585("This will empty the '" + this.setting.getFolder().getFileName() + "' folder and then re-generate the default files.\nAre you sure you want to do this?");
 
 
     
     this.minecraft.method_1507(new class_410(c -> 
           confirmReset(c), title, message));
   }
 
   
   private void confirmReset(boolean confirmed) {
     if (confirmed) {
       this.setting.resetFolder();
     }
     this.minecraft.method_1507(this);
   }
 
 
   
   public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
     boolean childClicked = super.mouseClicked(mouseX, mouseY, mouseButton);
     
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
       done();
     } else if (keyCode == 256) {
       openPrevScreen();
     } 
     return super.keyPressed(keyCode, scanCode, int_3);
   }
 
 
 
   
   public void tick() { this.doneButton
       .active = (this.listGui.selected >= 0 && this.listGui.selected < this.listGui.list.size()); }
 
 
 
   
   public void render(int mouseX, int mouseY, float partialTicks) {
     renderBackground();
     this.listGui.render(mouseX, mouseY, partialTicks);
     
     drawCenteredString(this.minecraft.field_1772, this.setting.getName(), this.width / 2, 12, 16777215);
 
     
     super.render(mouseX, mouseY, partialTicks);
     
     if (this.doneButton.isHovered() && !this.doneButton.active)
       renderTooltip("You must first select a file.", mouseX, mouseY); 
   }
   
   private static class ListGui
     extends class_358 {
     private final class_310 mc;
     private final List<Path> list;
     private int selected = -1;
 
 
     
     public ListGui(class_310 mc, SelectFileScreen screen, ArrayList<Path> list) {
       super(mc, screen.width, screen.height, 36, screen.height - 64, 20);
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
       class_327 fr = this.mc.field_1772;
       
       Path path = (Path)this.list.get(index);
       fr.method_1729("" + path.getFileName(), (x + 28), y, 15790320);
       fr.method_1729("" + this.minecraft.field_1697.toPath().relativize(path), (x + 28), (y + 9), 10526880);
     }
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\screens\SelectFileScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */