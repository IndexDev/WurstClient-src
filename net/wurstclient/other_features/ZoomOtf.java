 package net.wurstclient.other_features;
 
 import net.wurstclient.SearchTags;
 import net.wurstclient.events.MouseScrollListener;
 import net.wurstclient.other_feature.OtherFeature;
 import net.wurstclient.settings.CheckboxSetting;
 import net.wurstclient.settings.SliderSetting;
 import net.wurstclient.util.MathUtils;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @SearchTags({"telescope", "optifine"})
 public final class ZoomOtf
   extends OtherFeature
   implements MouseScrollListener
 {
   private final SliderSetting level;
   private final CheckboxSetting scroll;
   private Double currentLevel;
   
   public ZoomOtf() {
     super("Zoom", "Allows you to zoom in.\nBy default, the zoom is activated by pressing the §lV§r key.\nGo to Wurst Options -> Zoom to change this keybind.");
     this.level = new SliderSetting("Zoom level", 3.0D, 1.0D, 50.0D, 0.1D, v -> SliderSetting.ValueDisplay.DECIMAL.getValueString(v) + "x");
     this.scroll = new CheckboxSetting("Use mouse wheel", "If enabled, you can use the mouse wheel\nwhile zooming to zoom in even further.", true);
     addSetting(this.level);
     addSetting(this.scroll);
     EVENTS.add(MouseScrollListener.class, this);
   }
 
   
   public double changeFovBasedOnZoom(double fov) {
     if (this.currentLevel == null) {
       this.currentLevel = Double.valueOf(this.level.getValue());
     }
     if (!WURST.getZoomKey().method_1434()) {
       
       this.currentLevel = Double.valueOf(this.level.getValue());
       return fov;
     } 
     
     return fov / this.currentLevel.doubleValue();
   }
 
 
   
   public void onMouseScroll(double amount) {
     if (!WURST.getZoomKey().method_1434() || !this.scroll.isChecked()) {
       return;
     }
     if (this.currentLevel == null) {
       this.currentLevel = Double.valueOf(this.level.getValue());
     }
     if (amount > 0.0D) {
       this.currentLevel = Double.valueOf(this.currentLevel.doubleValue() * 1.1D);
     } else if (amount < 0.0D) {
       this.currentLevel = Double.valueOf(this.currentLevel.doubleValue() * 0.9D);
     } 
     this.currentLevel = Double.valueOf(MathUtils.clamp(this.currentLevel.doubleValue(), this.level.getMinimum(), this.level
           .getMaximum()));
   }
 
 
   
   public SliderSetting getLevelSetting() { return this.level; }
 
 
 
   
   public CheckboxSetting getScrollSetting() { return this.scroll; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\other_features\ZoomOtf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */