 package net.wurstclient.clickgui;
 
 import com.google.gson.JsonElement;
 import com.google.gson.JsonObject;
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.NoSuchFileException;
 import java.nio.file.Path;
 import java.util.ArrayList;
 import java.util.LinkedHashMap;
 import java.util.Objects;
 import java.util.stream.Stream;
 import net.minecraft.class_1041;
 import net.minecraft.class_310;
 import net.minecraft.class_327;
 import net.wurstclient.Category;
 import net.wurstclient.Feature;
 import net.wurstclient.WurstClient;
 import net.wurstclient.clickgui.components.FeatureButton;
 import net.wurstclient.hacks.ClickGuiHack;
 import net.wurstclient.settings.Setting;
 import net.wurstclient.util.json.JsonUtils;
 import org.lwjgl.opengl.GL11;
 
 
 
 
 
 
 
 
 
 
 public final class ClickGui
 {
   private static final WurstClient WURST = WurstClient.INSTANCE;
   private static final class_310 MC = WurstClient.MC; private final ArrayList<Window> windows; private final ArrayList<Popup> popups; private final Path windowsFile; private float[] bgColor;
   public ClickGui(Path windowsFile) {
     this.windows = new ArrayList();
     this.popups = new ArrayList();
 
     
     this.bgColor = new float[3];
     this.acColor = new float[3];
 
     
     this.tooltip = "";
 
 
 
 
     
     this.windowsFile = windowsFile;
   }
   private float[] acColor; private float opacity; private String tooltip; private boolean leftMouseButtonPressed;
   public void init() {
     JsonObject json;
     updateColors();
     
     LinkedHashMap<Category, Window> windowMap = new LinkedHashMap<Category, Window>();
     for (Category category : Category.values()) {
       windowMap.put(category, new Window(category.getName()));
     }
     ArrayList<Feature> features = new ArrayList<Feature>();
     features.addAll(WURST.getHax().getAllHax());
     features.addAll(WURST.getCmds().getAllCmds());
     features.addAll(WURST.getOtfs().getAllOtfs());
     
     for (Feature f : features) {
       if (f.getCategory() != null)
         ((Window)windowMap.get(f.getCategory())).add(new FeatureButton(f)); 
     } 
     this.windows.addAll(windowMap.values());
     
     Window uiSettings = new Window("By INDEX");
     uiSettings.add(new FeatureButton((WURST.getOtfs()).wurstLogoOtf));
     uiSettings.add(new FeatureButton((WURST.getOtfs()).hackListOtf));
     ClickGuiHack clickGuiHack = (WURST.getHax()).clickGuiHack;
     Stream<Setting> settings = clickGuiHack.getSettings().values().stream();
     settings.map(Setting::getComponent).forEach(c -> uiSettings.add(c));
     this.windows.add(uiSettings);
     
     for (Window window : this.windows) {
       window.setMinimized(true);
     }
 
 
     
     int x = 5;
     int y = 5;
     class_1041 sr = MC.field_1704;
     for (Window window : this.windows) {
       
       window.pack();
       
       if (x + window.getWidth() + 5 > sr.method_4486()) {
         
         x = 5;
         y += 18;
       } 
       
       window.setX(x);
       window.setY(y);
       x += window.getWidth() + 5;
     } 
 
     
     try (BufferedReader reader = Files.newBufferedReader(this.windowsFile)) {
       
       json = JsonUtils.JSON_PARSER.parse(reader).getAsJsonObject();
     }
     catch (NoSuchFileException e) {
       
       saveWindows();
       
       return;
     } catch (Exception e) {
       
       System.out.println("Failed to load " + this.windowsFile.getFileName());
       e.printStackTrace();
       
       saveWindows();
       
       return;
     } 
     for (Window window : this.windows) {
       
       JsonElement jsonWindow = json.get(window.getTitle());
       if (jsonWindow == null || !jsonWindow.isJsonObject()) {
         continue;
       }
       JsonElement jsonX = jsonWindow.getAsJsonObject().get("x");
       if (jsonX.isJsonPrimitive() && jsonX.getAsJsonPrimitive().isNumber()) {
         window.setX(jsonX.getAsInt());
       }
       JsonElement jsonY = jsonWindow.getAsJsonObject().get("y");
       if (jsonY.isJsonPrimitive() && jsonY.getAsJsonPrimitive().isNumber()) {
         window.setY(jsonY.getAsInt());
       }
       
       JsonElement jsonMinimized = jsonWindow.getAsJsonObject().get("minimized");
       if (jsonMinimized.isJsonPrimitive() && jsonMinimized
         .getAsJsonPrimitive().isBoolean()) {
         window.setMinimized(jsonMinimized.getAsBoolean());
       }
       JsonElement jsonPinned = jsonWindow.getAsJsonObject().get("pinned");
       if (jsonPinned.isJsonPrimitive() && jsonPinned
         .getAsJsonPrimitive().isBoolean()) {
         window.setPinned(jsonPinned.getAsBoolean());
       }
     } 
     saveWindows();
   }
 
   
   private void saveWindows() {
     JsonObject json = new JsonObject();
     
     for (Window window : this.windows) {
       
       if (window.isClosable()) {
         continue;
       }
       JsonObject jsonWindow = new JsonObject();
       jsonWindow.addProperty("x", Integer.valueOf(window.getX()));
       jsonWindow.addProperty("y", Integer.valueOf(window.getY()));
       jsonWindow.addProperty("minimized", Boolean.valueOf(window.isMinimized()));
       jsonWindow.addProperty("pinned", Boolean.valueOf(window.isPinned()));
       json.add(window.getTitle(), jsonWindow);
     } 
     
     try (BufferedWriter writer = Files.newBufferedWriter(this.windowsFile, new java.nio.file.OpenOption[0])) {
       
       JsonUtils.PRETTY_GSON.toJson(json, writer);
     }
     catch (IOException e) {
       
       System.out.println("Failed to save " + this.windowsFile.getFileName());
       e.printStackTrace();
     } 
   }
 
   
   public void handleMouseClick(int mouseX, int mouseY, int mouseButton) {
     if (mouseButton == 0) {
       this.leftMouseButtonPressed = true;
     }
     
     boolean popupClicked = handlePopupMouseClick(mouseX, mouseY, mouseButton);
     
     if (!popupClicked) {
       handleWindowMouseClick(mouseX, mouseY, mouseButton);
     }
     for (Popup popup : this.popups) {
       if (popup.getOwner().getParent().isClosing())
         popup.close(); 
     } 
     this.windows.removeIf(w -> w.isClosing());
     this.popups.removeIf(p -> p.isClosing());
   }
 
 
   
   public void handleMouseRelease(double mouseX, double mouseY, int mouseButton) {
     if (mouseButton == 0) {
       this.leftMouseButtonPressed = false;
     }
   }
   
   public void handleMouseScroll(double mouseX, double mouseY, double delta) {
     int dWheel = (int)delta * 4;
     if (dWheel == 0) {
       return;
     }
     for (int i = this.windows.size() - 1; i >= 0; i--) {
       
       Window window = (Window)this.windows.get(i);
       
       if (window.isScrollingEnabled() && !window.isMinimized() && 
         !window.isInvisible())
       {
         
         if (mouseX >= window.getX() && mouseY >= (window.getY() + 13))
         {
           if (mouseX < (window.getX() + window.getWidth()) && mouseY < (window
             .getY() + window.getHeight())) {
 
             
             int scroll = window.getScrollOffset() + dWheel;
             scroll = Math.min(scroll, 0);
             scroll = Math.max(scroll, 
                 -window.getInnerHeight() + window.getHeight() - 13);
             window.setScrollOffset(scroll);
             break;
           } 
         }
       }
     } 
   }
   
   public boolean handleNavigatorPopupClick(double mouseX, double mouseY, int mouseButton) {
     boolean popupClicked = handlePopupMouseClick(mouseX, mouseY, mouseButton);
     
     if (popupClicked) {
       
       for (Popup popup : this.popups) {
         if (popup.getOwner().getParent().isClosing())
           popup.close(); 
       } 
       this.popups.removeIf(p -> p.isClosing());
     } 
     
     return popupClicked;
   }
 
 
   
   public void handleNavigatorMouseClick(double cMouseX, double cMouseY, int mouseButton, Window window) {
     if (mouseButton == 0) {
       this.leftMouseButtonPressed = true;
     }
     handleComponentMouseClick(window, cMouseX, cMouseY, mouseButton);
     
     for (Popup popup : this.popups) {
       if (popup.getOwner().getParent().isClosing())
         popup.close(); 
     } 
     this.popups.removeIf(p -> p.isClosing());
   }
 
 
   
   private boolean handlePopupMouseClick(double mouseX, double mouseY, int mouseButton) {
     for (int i = this.popups.size() - 1; i >= 0; i--) {
       
       Popup popup = (Popup)this.popups.get(i);
       Component owner = popup.getOwner();
       Window parent = owner.getParent();
       
       int x0 = parent.getX() + owner.getX();
       
       int y0 = parent.getY() + 13 + parent.getScrollOffset() + owner.getY();
       
       int x1 = x0 + popup.getX();
       int y1 = y0 + popup.getY();
       int x2 = x1 + popup.getWidth();
       int y2 = y1 + popup.getHeight();
       
       if (mouseX >= x1 && mouseY >= y1)
       {
         if (mouseX < x2 && mouseY < y2) {
 
           
           int cMouseX = (int)(mouseX - x0);
           int cMouseY = (int)(mouseY - y0);
           popup.handleMouseClick(cMouseX, cMouseY, mouseButton);
           
           this.popups.remove(i);
           this.popups.add(popup);
           return true;
         }  } 
     } 
     return false;
   }
 
   
   private void handleWindowMouseClick(int mouseX, int mouseY, int mouseButton) {
     for (int i = this.windows.size() - 1; i >= 0; i--) {
       
       Window window = (Window)this.windows.get(i);
       if (window.isInvisible()) {
         continue;
       }
       int x1 = window.getX();
       int y1 = window.getY();
       int x2 = x1 + window.getWidth();
       int y2 = y1 + window.getHeight();
       int y3 = y1 + 13;
       
       if (mouseX < x1 || mouseY < y1)
         continue; 
       if (mouseX >= x2 || mouseY >= y2) {
         continue;
       }
       if (mouseY < y3) {
         handleTitleBarMouseClick(window, mouseX, mouseY, mouseButton);
       } else if (!window.isMinimized()) {
         
         window.validate();
         
         int cMouseX = mouseX - x1;
         int cMouseY = mouseY - y3;
         
         if (window.isScrollingEnabled() && mouseX >= x2 - 3) {
           handleScrollbarMouseClick(window, cMouseX, cMouseY, mouseButton);
         }
         else {
           
           if (window.isScrollingEnabled()) {
             cMouseY -= window.getScrollOffset();
           }
           handleComponentMouseClick(window, cMouseX, cMouseY, mouseButton);
         } 
       } else {
         continue;
       } 
 
       
       this.windows.remove(i);
       this.windows.add(window);
     } 
   }
 
 
 
   
   private void handleTitleBarMouseClick(Window window, int mouseX, int mouseY, int mouseButton) {
     if (mouseButton != 0) {
       return;
     }
     if (mouseY < window.getY() + 2 || mouseY >= window.getY() + 11) {
       
       window.startDragging(mouseX, mouseY);
       
       return;
     } 
     int x3 = window.getX() + window.getWidth();
     
     if (window.isClosable()) {
       
       x3 -= 11;
       if (mouseX >= x3 && mouseX < x3 + 9) {
         
         window.close();
         
         return;
       } 
     } 
     if (window.isPinnable()) {
       
       x3 -= 11;
       if (mouseX >= x3 && mouseX < x3 + 9) {
         
         window.setPinned(!window.isPinned());
         saveWindows();
         
         return;
       } 
     } 
     if (window.isMinimizable()) {
       
       x3 -= 11;
       if (mouseX >= x3 && mouseX < x3 + 9) {
         
         window.setMinimized(!window.isMinimized());
         saveWindows();
         
         return;
       } 
     } 
     window.startDragging(mouseX, mouseY);
   }
 
 
   
   private void handleScrollbarMouseClick(Window window, int mouseX, int mouseY, int mouseButton) {
     if (mouseButton != 0) {
       return;
     }
     if (mouseX >= window.getWidth() - 1) {
       return;
     }
     double outerHeight = (window.getHeight() - 13);
     double innerHeight = window.getInnerHeight();
     double maxScrollbarHeight = outerHeight - 2.0D;
     
     int scrollbarY = (int)(outerHeight * -window.getScrollOffset() / innerHeight + 1.0D);
     int scrollbarHeight = (int)(maxScrollbarHeight * outerHeight / innerHeight);
 
     
     if (mouseY < scrollbarY || mouseY >= scrollbarY + scrollbarHeight) {
       return;
     }
     window.startDraggingScrollbar(window.getY() + 13 + mouseY);
   }
 
 
   
   private void handleComponentMouseClick(Window window, double mouseX, double mouseY, int mouseButton) {
     for (int i2 = window.countChildren() - 1; i2 >= 0; i2--) {
       
       Component c = window.getChild(i2);
       
       if (mouseX >= c.getX() && mouseY >= c.getY())
       {
         if (mouseX < (c.getX() + c.getWidth()) && mouseY < (c
           .getY() + c.getHeight())) {
 
           
           c.handleMouseClick(mouseX, mouseY, mouseButton);
           break;
         }  } 
     } 
   }
   
   public void render(int mouseX, int mouseY, float partialTicks) {
     updateColors();
     
     GL11.glDisable(2884);
     GL11.glDisable(3553);
     GL11.glEnable(3042);
     GL11.glBlendFunc(770, 771);
     GL11.glShadeModel(7425);
     GL11.glLineWidth(1.0F);
     
     this.tooltip = "";
     for (Window window : this.windows) {
       
       if (window.isInvisible()) {
         continue;
       }
       
       if (window.isDragging()) {
         if (this.leftMouseButtonPressed) {
           window.dragTo(mouseX, mouseY);
         } else {
           
           window.stopDragging();
           saveWindows();
         } 
       }
       
       if (window.isDraggingScrollbar())
         if (this.leftMouseButtonPressed) {
           window.dragScrollbarTo(mouseY);
         } else {
           window.stopDraggingScrollbar();
         }  
       renderWindow(window, mouseX, mouseY, partialTicks);
     } 
     
     GL11.glDisable(3553);
     renderPopupsAndTooltip(mouseX, mouseY);
     
     GL11.glEnable(2884);
     GL11.glEnable(3553);
     GL11.glDisable(3042);
   }
 
 
   
   public void renderPopupsAndTooltip(int mouseX, int mouseY) {
     for (Popup popup : this.popups) {
       
       Component owner = popup.getOwner();
       Window parent = owner.getParent();
       
       int x1 = parent.getX() + owner.getX();
       
       int y1 = parent.getY() + 13 + parent.getScrollOffset() + owner.getY();
       
       GL11.glPushMatrix();
       GL11.glTranslated(x1, y1, 0.0D);
       
       int cMouseX = mouseX - x1;
       int cMouseY = mouseY - y1;
       popup.render(cMouseX, cMouseY);
       
       GL11.glPopMatrix();
     } 
 
     
     if (!this.tooltip.isEmpty()) {
       
       String[] lines = this.tooltip.split("\n");
       class_327 fr = MC.field_1772;
       
       int tw = 0;
       fr.getClass(); int th = lines.length * 9;
       for (String line : lines) {
         
         int lw = fr.method_1727(line);
         if (lw > tw)
           tw = lw; 
       } 
       int sw = MC.field_1755.width;
       int sh = MC.field_1755.height;
       
       int xt1 = (mouseX + tw + 11 <= sw) ? (mouseX + 8) : (mouseX - tw - 8);
       int xt2 = xt1 + tw + 3;
       int yt1 = (mouseY + th - 2 <= sh) ? (mouseY - 4) : (mouseY - th - 4);
       int yt2 = yt1 + th + 2;
       
       GL11.glPushMatrix();
       GL11.glTranslated(0.0D, 0.0D, 300.0D);
 
       
       GL11.glDisable(3553);
       GL11.glColor4f(this.bgColor[0], this.bgColor[1], this.bgColor[2], 0.75F);
       GL11.glBegin(7);
       GL11.glVertex2i(xt1, yt1);
       GL11.glVertex2i(xt1, yt2);
       GL11.glVertex2i(xt2, yt2);
       GL11.glVertex2i(xt2, yt1);
       GL11.glEnd();
 
       
       GL11.glColor4f(this.acColor[0], this.acColor[1], this.acColor[2], 0.5F);
       GL11.glBegin(2);
       GL11.glVertex2i(xt1, yt1);
       GL11.glVertex2i(xt1, yt2);
       GL11.glVertex2i(xt2, yt2);
       GL11.glVertex2i(xt2, yt1);
       GL11.glEnd();
 
       
       GL11.glEnable(3553);
       for (int i = 0; i < lines.length; i++) {
         fr.getClass(); fr.method_1729(lines[i], (xt1 + 2), (yt1 + 2 + i * 9), 16777215);
       } 
       
       GL11.glPopMatrix();
     } 
   }
 
   
   public void renderPinnedWindows(float partialTicks) {
     GL11.glDisable(2884);
     GL11.glDisable(3553);
     GL11.glEnable(3042);
     GL11.glBlendFunc(770, 771);
     GL11.glShadeModel(7425);
     GL11.glLineWidth(1.0F);
     
     for (Window window : this.windows) {
       if (window.isPinned() && !window.isInvisible()) {
         renderWindow(window, -2147483648, -2147483648, partialTicks);
       }
     } 
     GL11.glEnable(2884);
     GL11.glEnable(3553);
     GL11.glDisable(3042);
   }
 
   
   public void updateColors() {
     ClickGuiHack clickGui = (WURST.getHax()).clickGuiHack;
     
     this.opacity = clickGui.getOpacity();
     this.bgColor = clickGui.getBgColor();
 
 
 
 
 
 
 
 
 
     
     this.acColor = clickGui.getAcColor();
   }
 
 
   
   private void renderWindow(Window window, int mouseX, int mouseY, float partialTicks) {
     int x1 = window.getX();
     int y1 = window.getY();
     int x2 = x1 + window.getWidth();
     int y2 = y1 + window.getHeight();
     int y3 = y1 + 13;
     
     if (window.isMinimized()) {
       y2 = y3;
     }
     if (mouseX >= x1 && mouseY >= y1 && mouseX < x2 && mouseY < y2) {
       this.tooltip = "";
     }
     GL11.glDisable(3553);
     
     if (!window.isMinimized()) {
       
       window.validate();
 
       
       if (window.isScrollingEnabled()) {
         
         int xs1 = x2 - 3;
         int xs2 = xs1 + 2;
         int xs3 = x2;
         
         double outerHeight = (y2 - y3);
         double innerHeight = window.getInnerHeight();
         double maxScrollbarHeight = outerHeight - 2.0D;
         
         double scrollbarY = outerHeight * -window.getScrollOffset() / innerHeight + 1.0D;
         double scrollbarHeight = maxScrollbarHeight * outerHeight / innerHeight;
 
         
         int ys1 = y3;
         int ys2 = y2;
         int ys3 = ys1 + (int)scrollbarY;
         int ys4 = ys3 + (int)scrollbarHeight;
 
         
         GL11.glColor4f(this.bgColor[0], this.bgColor[1], this.bgColor[2], this.opacity);
         GL11.glBegin(7);
         GL11.glVertex2i(xs2, ys1);
         GL11.glVertex2i(xs2, ys2);
         GL11.glVertex2i(xs3, ys2);
         GL11.glVertex2i(xs3, ys1);
         GL11.glVertex2i(xs1, ys1);
         GL11.glVertex2i(xs1, ys3);
         GL11.glVertex2i(xs2, ys3);
         GL11.glVertex2i(xs2, ys1);
         GL11.glVertex2i(xs1, ys4);
         GL11.glVertex2i(xs1, ys2);
         GL11.glVertex2i(xs2, ys2);
         GL11.glVertex2i(xs2, ys4);
         GL11.glEnd();
         
         boolean hovering = (mouseX >= xs1 && mouseY >= ys3 && mouseX < xs2 && mouseY < ys4);
 
 
         
         GL11.glColor4f(this.acColor[0], this.acColor[1], this.acColor[2], hovering ? (this.opacity * 1.5F) : this.opacity);
         
         GL11.glBegin(7);
         GL11.glVertex2i(xs1, ys3);
         GL11.glVertex2i(xs1, ys4);
         GL11.glVertex2i(xs2, ys4);
         GL11.glVertex2i(xs2, ys3);
         GL11.glEnd();
 
         
         GL11.glColor4f(this.acColor[0], this.acColor[1], this.acColor[2], 0.5F);
         GL11.glBegin(2);
         GL11.glVertex2i(xs1, ys3);
         GL11.glVertex2i(xs1, ys4);
         GL11.glVertex2i(xs2, ys4);
         GL11.glVertex2i(xs2, ys3);
         GL11.glEnd();
       } 
       
       int x3 = x1 + 2;
       int x4 = window.isScrollingEnabled() ? (x2 - 3) : x2;
       int x5 = x4 - 2;
       int y4 = y3 + window.getScrollOffset();
 
 
       
       GL11.glColor4f(this.bgColor[0], this.bgColor[1], this.bgColor[2], this.opacity);
       GL11.glBegin(7);
       GL11.glVertex2i(x1, y3);
       GL11.glVertex2i(x1, y2);
       GL11.glVertex2i(x3, y2);
       GL11.glVertex2i(x3, y3);
       GL11.glVertex2i(x5, y3);
       GL11.glVertex2i(x5, y2);
       GL11.glVertex2i(x4, y2);
       GL11.glVertex2i(x4, y3);
       GL11.glEnd();
       
       class_1041 sr = MC.field_1704;
       int sf = (int)sr.method_4495();
       GL11.glScissor(x1 * sf, (sr.method_4502() - y2) * sf, window
           .getWidth() * sf, (y2 - y3) * sf);
       GL11.glEnable(3089);
       GL11.glPushMatrix();
       GL11.glTranslated(x1, y4, 0.0D);
       
       GL11.glColor4f(this.bgColor[0], this.bgColor[1], this.bgColor[2], this.opacity);
       GL11.glBegin(7);
 
 
       
       int xc1 = 2;
       int xc2 = x5 - x1; int yc1;
       for (yc1 = 0; yc1 < window.countChildren(); yc1++) {
         
         int yc1 = window.getChild(yc1).getY();
         int yc2 = yc1 - 2;
         GL11.glVertex2i(xc1, yc2);
         GL11.glVertex2i(xc1, yc1);
         GL11.glVertex2i(xc2, yc1);
         GL11.glVertex2i(xc2, yc2);
       } 
 
 
 
       
       if (window.countChildren() == 0) {
         int yc1 = 0;
       }
       else {
         
         Component lastChild = window.getChild(window.countChildren() - 1);
         yc1 = lastChild.getY() + lastChild.getHeight();
       } 
       int yc2 = yc1 + 2;
       GL11.glVertex2i(xc1, yc2);
       GL11.glVertex2i(xc1, yc1);
       GL11.glVertex2i(xc2, yc1);
       GL11.glVertex2i(xc2, yc2);
       
       GL11.glEnd();
 
       
       int cMouseX = mouseX - x1;
       int cMouseY = mouseY - y4;
       for (int i = 0; i < window.countChildren(); i++) {
         window.getChild(i).render(cMouseX, cMouseY, partialTicks);
       }
       GL11.glPopMatrix();
       GL11.glDisable(3089);
     } 
 
     
     GL11.glColor4f(this.acColor[0], this.acColor[1], this.acColor[2], 0.5F);
     GL11.glBegin(2);
     GL11.glVertex2i(x1, y1);
     GL11.glVertex2i(x1, y2);
     GL11.glVertex2i(x2, y2);
     GL11.glVertex2i(x2, y1);
     GL11.glEnd();
     
     if (!window.isMinimized()) {
 
       
       GL11.glBegin(1);
       GL11.glVertex2i(x1, y3);
       GL11.glVertex2i(x2, y3);
       GL11.glEnd();
     } 
 
     
     int x3 = x2;
     int y4 = y1 + 2;
     int y5 = y3 - 2;
     boolean hoveringY = (mouseY >= y4 && mouseY < y5);
     
     if (window.isClosable()) {
       
       x3 -= 11;
       int x4 = x3 + 9;
       boolean hovering = (hoveringY && mouseX >= x3 && mouseX < x4);
       renderCloseButton(x3, y4, x4, y5, hovering);
     } 
     
     if (window.isPinnable()) {
       
       x3 -= 11;
       int x4 = x3 + 9;
       boolean hovering = (hoveringY && mouseX >= x3 && mouseX < x4);
       renderPinButton(x3, y4, x4, y5, hovering, window.isPinned());
     } 
     
     if (window.isMinimizable()) {
       
       x3 -= 11;
       int x4 = x3 + 9;
       boolean hovering = (hoveringY && mouseX >= x3 && mouseX < x4);
       renderMinimizeButton(x3, y4, x4, y5, hovering, window
           .isMinimized());
     } 
 
 
     
     GL11.glColor4f(this.acColor[0], this.acColor[1], this.acColor[2], this.opacity);
     GL11.glBegin(7);
     GL11.glVertex2i(x3, y1);
     GL11.glVertex2i(x3, y4);
     GL11.glVertex2i(x2, y4);
     GL11.glVertex2i(x2, y1);
     GL11.glVertex2i(x3, y5);
     GL11.glVertex2i(x3, y3);
     GL11.glVertex2i(x2, y3);
     GL11.glVertex2i(x2, y5);
     GL11.glEnd();
 
 
     
     GL11.glBegin(7);
     GL11.glVertex2i(x1, y1);
     GL11.glVertex2i(x1, y3);
     GL11.glVertex2i(x3, y3);
     GL11.glVertex2i(x3, y1);
     GL11.glEnd();
 
     
     GL11.glEnable(3553);
     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
     class_327 fr = MC.field_1772;
     String title = fr.method_1714(window.getTitle(), x3 - x1);
     fr.method_1729(title, (x1 + 2), (y1 + 3), 15790320);
   }
 
 
   
   private void renderTitleBarButton(int x1, int y1, int x2, int y2, boolean hovering) {
     int x3 = x2 + 2;
 
     
     GL11.glColor4f(this.bgColor[0], this.bgColor[1], this.bgColor[2], hovering ? (this.opacity * 1.5F) : this.opacity);
     
     GL11.glBegin(7);
     GL11.glVertex2i(x1, y1);
     GL11.glVertex2i(x1, y2);
     GL11.glVertex2i(x2, y2);
     GL11.glVertex2i(x2, y1);
     GL11.glEnd();
 
     
     GL11.glColor4f(this.acColor[0], this.acColor[1], this.acColor[2], this.opacity);
     GL11.glBegin(7);
     GL11.glVertex2i(x2, y1);
     GL11.glVertex2i(x2, y2);
     GL11.glVertex2i(x3, y2);
     GL11.glVertex2i(x3, y1);
     GL11.glEnd();
 
     
     GL11.glColor4f(this.acColor[0], this.acColor[1], this.acColor[2], 0.5F);
     GL11.glBegin(2);
     GL11.glVertex2i(x1, y1);
     GL11.glVertex2i(x1, y2);
     GL11.glVertex2i(x2, y2);
     GL11.glVertex2i(x2, y1);
     GL11.glEnd();
   }
 
   
   private void renderMinimizeButton(int x1, int y1, int x2, int y2, boolean hovering, boolean minimized) {
     double ya2, ya1;
     renderTitleBarButton(x1, y1, x2, y2, hovering);
     
     double xa1 = (x1 + 1);
     double xa2 = (x1 + x2) / 2.0D;
     double xa3 = (x2 - 1);
 
 
     
     if (minimized) {
       
       ya1 = (y1 + 3);
       ya2 = y2 - 2.5D;
       GL11.glColor4f(0.0F, hovering ? 1.0F : 0.85F, 0.0F, 1.0F);
     }
     else {
       
       ya1 = (y2 - 3);
       ya2 = y1 + 2.5D;
       GL11.glColor4f(hovering ? 1.0F : 0.85F, 0.0F, 0.0F, 1.0F);
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
 
 
   
   private void renderPinButton(int x1, int y1, int x2, int y2, boolean hovering, boolean pinned) {
     renderTitleBarButton(x1, y1, x2, y2, hovering);
     float h = hovering ? 1.0F : 0.85F;
     
     if (pinned) {
       
       double xk1 = (x1 + 2);
       double xk2 = (x2 - 2);
       double xk3 = (x1 + 1);
       double xk4 = (x2 - 1);
       double yk1 = (y1 + 2);
       double yk2 = (y2 - 2);
       double yk3 = y2 - 0.5D;
 
       
       GL11.glColor4f(h, 0.0F, 0.0F, 0.5F);
       GL11.glBegin(7);
       GL11.glVertex2d(xk1, yk1);
       GL11.glVertex2d(xk2, yk1);
       GL11.glVertex2d(xk2, yk2);
       GL11.glVertex2d(xk1, yk2);
       GL11.glVertex2d(xk3, yk2);
       GL11.glVertex2d(xk4, yk2);
       GL11.glVertex2d(xk4, yk3);
       GL11.glVertex2d(xk3, yk3);
       GL11.glEnd();
       
       double xn1 = x1 + 3.5D;
       double xn2 = x2 - 3.5D;
       double yn1 = y2 - 0.5D;
       double yn2 = y2;
 
       
       GL11.glColor4f(h, h, h, 1.0F);
       GL11.glBegin(7);
       GL11.glVertex2d(xn1, yn1);
       GL11.glVertex2d(xn2, yn1);
       GL11.glVertex2d(xn2, yn2);
       GL11.glVertex2d(xn1, yn2);
       GL11.glEnd();
 
       
       GL11.glColor4f(0.0625F, 0.0625F, 0.0625F, 0.5F);
       GL11.glBegin(2);
       GL11.glVertex2d(xk1, yk1);
       GL11.glVertex2d(xk2, yk1);
       GL11.glVertex2d(xk2, yk2);
       GL11.glVertex2d(xk1, yk2);
       GL11.glEnd();
       GL11.glBegin(2);
       GL11.glVertex2d(xk3, yk2);
       GL11.glVertex2d(xk4, yk2);
       GL11.glVertex2d(xk4, yk3);
       GL11.glVertex2d(xk3, yk3);
       GL11.glEnd();
       GL11.glBegin(2);
       GL11.glVertex2d(xn1, yn1);
       GL11.glVertex2d(xn2, yn1);
       GL11.glVertex2d(xn2, yn2);
       GL11.glVertex2d(xn1, yn2);
       GL11.glEnd();
     }
     else {
       
       double xk1 = x2 - 3.5D;
       double xk2 = x2 - 0.5D;
       double xk3 = (x2 - 3);
       double xk4 = (x1 + 3);
       double xk5 = (x1 + 2);
       double xk6 = (x2 - 2);
       double xk7 = (x1 + 1);
       double yk1 = y1 + 0.5D;
       double yk2 = y1 + 3.5D;
       double yk3 = (y2 - 3);
       double yk4 = (y1 + 3);
       double yk5 = (y1 + 2);
       double yk6 = (y2 - 2);
       double yk7 = (y2 - 1);
 
       
       GL11.glColor4f(0.0F, h, 0.0F, 1.0F);
       GL11.glBegin(7);
       GL11.glVertex2d(xk1, yk1);
       GL11.glVertex2d(xk2, yk2);
       GL11.glVertex2d(xk3, yk3);
       GL11.glVertex2d(xk4, yk4);
       GL11.glVertex2d(xk5, yk5);
       GL11.glVertex2d(xk6, yk6);
       GL11.glVertex2d(xk3, yk7);
       GL11.glVertex2d(xk7, yk4);
       GL11.glEnd();
       
       double xn1 = (x1 + 3);
       double xn2 = (x1 + 4);
       double xn3 = (x1 + 1);
       double yn1 = (y2 - 4);
       double yn2 = (y2 - 3);
       double yn3 = (y2 - 1);
 
       
       GL11.glColor4f(h, h, h, 1.0F);
       GL11.glBegin(4);
       GL11.glVertex2d(xn1, yn1);
       GL11.glVertex2d(xn2, yn2);
       GL11.glVertex2d(xn3, yn3);
       GL11.glEnd();
 
       
       GL11.glColor4f(0.0625F, 0.0625F, 0.0625F, 0.5F);
       GL11.glBegin(2);
       GL11.glVertex2d(xk1, yk1);
       GL11.glVertex2d(xk2, yk2);
       GL11.glVertex2d(xk3, yk3);
       GL11.glVertex2d(xk4, yk4);
       GL11.glEnd();
       GL11.glBegin(2);
       GL11.glVertex2d(xk5, yk5);
       GL11.glVertex2d(xk6, yk6);
       GL11.glVertex2d(xk3, yk7);
       GL11.glVertex2d(xk7, yk4);
       GL11.glEnd();
       GL11.glBegin(2);
       GL11.glVertex2d(xn1, yn1);
       GL11.glVertex2d(xn2, yn2);
       GL11.glVertex2d(xn3, yn3);
       GL11.glEnd();
     } 
   }
 
 
   
   private void renderCloseButton(int x1, int y1, int x2, int y2, boolean hovering) {
     renderTitleBarButton(x1, y1, x2, y2, hovering);
     
     double xc1 = (x1 + 2);
     double xc2 = (x1 + 3);
     double xc3 = (x2 - 2);
     double xc4 = (x2 - 3);
     double xc5 = x1 + 3.5D;
     double xc6 = (x1 + x2) / 2.0D;
     double xc7 = x2 - 3.5D;
     double yc1 = (y1 + 3);
     double yc2 = (y1 + 2);
     double yc3 = (y2 - 3);
     double yc4 = (y2 - 2);
     double yc5 = y1 + 3.5D;
     double yc6 = (y1 + y2) / 2.0D;
     double yc7 = y2 - 3.5D;
 
     
     GL11.glColor4f(hovering ? 1.0F : 0.85F, 0.0F, 0.0F, 1.0F);
     GL11.glBegin(7);
     GL11.glVertex2d(xc1, yc1);
     GL11.glVertex2d(xc2, yc2);
     GL11.glVertex2d(xc3, yc3);
     GL11.glVertex2d(xc4, yc4);
     GL11.glVertex2d(xc3, yc1);
     GL11.glVertex2d(xc4, yc2);
     GL11.glVertex2d(xc6, yc5);
     GL11.glVertex2d(xc7, yc6);
     GL11.glVertex2d(xc6, yc7);
     GL11.glVertex2d(xc5, yc6);
     GL11.glVertex2d(xc1, yc3);
     GL11.glVertex2d(xc2, yc4);
     GL11.glEnd();
 
     
     GL11.glColor4f(0.0625F, 0.0625F, 0.0625F, 0.5F);
     GL11.glBegin(2);
     GL11.glVertex2d(xc1, yc1);
     GL11.glVertex2d(xc2, yc2);
     GL11.glVertex2d(xc6, yc5);
     GL11.glVertex2d(xc4, yc2);
     GL11.glVertex2d(xc3, yc1);
     GL11.glVertex2d(xc7, yc6);
     GL11.glVertex2d(xc3, yc3);
     GL11.glVertex2d(xc4, yc4);
     GL11.glVertex2d(xc6, yc7);
     GL11.glVertex2d(xc2, yc4);
     GL11.glVertex2d(xc1, yc3);
     GL11.glVertex2d(xc5, yc6);
     GL11.glEnd();
   }
 
 
   
   public float[] getBgColor() { return this.bgColor; }
 
 
 
   
   public float[] getAcColor() { return this.acColor; }
 
 
 
   
   public float getOpacity() { return this.opacity; }
 
 
 
   
   public void setTooltip(String tooltip) { this.tooltip = (String)Objects.requireNonNull(tooltip); }
 
 
 
   
   public void addWindow(Window window) { this.windows.add(window); }
 
 
 
   
   public void addPopup(Popup popup) { this.popups.add(popup); }
 
 
 
   
   public boolean isLeftMouseButtonPressed() { return this.leftMouseButtonPressed; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\ClickGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */