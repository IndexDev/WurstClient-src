 package net.wurstclient.clickgui;
 
 import java.util.stream.Stream;
 import net.minecraft.class_1041;
 import net.wurstclient.Feature;
 import net.wurstclient.WurstClient;
 import net.wurstclient.settings.Setting;
 
 
 
 
 
 
 
 
 public final class SettingsWindow
   extends Window
 {
   public SettingsWindow(Feature feature, Window parent, int buttonY) {
     super(feature.getName() + " Settings");
     
     Stream<Setting> settings = feature.getSettings().values().stream();
     settings.map(Setting::getComponent).forEach(c -> add(c));
     
     setClosable(true);
     setMinimizable(false);
     pack();
     
     setInitialPosition(parent, buttonY);
   }
 
   
   private void setInitialPosition(Window parent, int buttonY) {
     int scroll = parent.isScrollingEnabled() ? parent.getScrollOffset() : 0;
     int x = parent.getX() + parent.getWidth() + 5;
     int y = parent.getY() + 12 + buttonY + scroll;
     
     class_1041 mcWindow = WurstClient.MC.field_1704;
     if (x + getWidth() > mcWindow.method_4486())
       x = parent.getX() - getWidth() - 5; 
     if (y + getHeight() > mcWindow.method_4502()) {
       y -= getHeight() - 14;
     }
     setX(x);
     setY(y);
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\SettingsWindow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */