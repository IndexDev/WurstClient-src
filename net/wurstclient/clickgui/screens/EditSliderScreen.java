 package net.wurstclient.clickgui.screens;
 
 import net.minecraft.class_2585;
 import net.minecraft.class_327;
 import net.minecraft.class_342;
 import net.minecraft.class_4185;
 import net.minecraft.class_437;
 import net.wurstclient.settings.SliderSetting;
 import net.wurstclient.util.MathUtils;
 
 
 
 
 
 
 
 
 
 
 
 
 public final class EditSliderScreen
   extends class_437
 {
   private final class_437 prevScreen;
   private final SliderSetting slider;
   private class_342 valueField;
   private class_4185 doneButton;
   
   public EditSliderScreen(class_437 prevScreen, SliderSetting slider) {
     super(new class_2585(""));
     this.prevScreen = prevScreen;
     this.slider = slider;
   }
 
 
   
   public void init() {
     int x1 = this.width / 2 - 100;
     int y1 = 60;
     int y2 = this.height / 3 * 2;
     
     class_327 tr = this.minecraft.field_1772;
     SliderSetting.ValueDisplay vd = SliderSetting.ValueDisplay.DECIMAL;
     String valueString = vd.getValueString(this.slider.getValue());
     
     this.valueField = new class_342(tr, x1, y1, 200, 20, "");
     this.valueField.method_1852(valueString);
     this.valueField.method_1875(0);
     
     this.children.add(this.valueField);
     method_20085(this.valueField);
     this.valueField.method_1876(true);
     
     this.doneButton = new class_4185(x1, y2, 200, 20, "Done", b -> done());
     addButton(this.doneButton);
   }
 
   
   private void done() {
     String value = this.valueField.method_1882();
     
     if (MathUtils.isDouble(value)) {
       this.slider.setValue(Double.parseDouble(value));
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
 
 
 
   
   public void tick() { this.valueField.method_1865(); }
 
 
 
   
   public void render(int mouseX, int mouseY, float partialTicks) {
     renderBackground();
     drawCenteredString(this.minecraft.field_1772, this.slider.getName(), this.width / 2, 20, 16777215);
 
     
     this.valueField.render(mouseX, mouseY, partialTicks);
     super.render(mouseX, mouseY, partialTicks);
   }
 
 
 
   
   public boolean isPauseScreen() { return false; }
 
 
 
 
   
   public boolean shouldCloseOnEsc() { return false; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\screens\EditSliderScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */