 package net.wurstclient.clickgui;
 
 import java.util.ArrayList;
 import net.minecraft.class_327;
 import net.wurstclient.WurstClient;
 
 
 
 public class Window
 {
   private String title;
   private int x;
   private int y;
   private int width;
   private int height;
   private boolean valid;
   private final ArrayList<Component> children;
   private boolean dragging;
   private int dragOffsetX;
   private int dragOffsetY;
   private boolean minimized;
   
   public Window(String title) {
     this.children = new ArrayList();
 
 
 
 
 
     
     this.minimizable = true;
 
     
     this.pinnable = true;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
     
     this.title = title;
   }
   private boolean minimizable; private boolean pinned; private boolean pinnable; private boolean closable; private boolean closing; private boolean invisible; private int innerHeight; private int scrollOffset; private boolean scrollingEnabled; private boolean draggingScrollbar;
   private int scrollbarDragOffsetY;
   
   public final String getTitle() { return this.title; }
 
 
 
   
   public final void setTitle(String title) { this.title = title; }
 
 
 
   
   public final int getX() { return this.x; }
 
 
 
   
   public final void setX(int x) { this.x = x; }
 
 
 
   
   public final int getY() { return this.y; }
 
 
 
   
   public final void setY(int y) { this.y = y; }
 
 
 
   
   public final int getWidth() { return this.width; }
 
 
   
   public final void setWidth(int width) {
     if (this.width != width) {
       invalidate();
     }
     this.width = width;
   }
 
 
   
   public final int getHeight() { return this.height; }
 
 
   
   public final void setHeight(int height) {
     if (this.height != height) {
       invalidate();
     }
     this.height = height;
   }
 
   
   public final void pack() {
     int maxChildWidth = 0;
     for (Component c : this.children) {
       if (c.getDefaultWidth() > maxChildWidth)
         maxChildWidth = c.getDefaultWidth(); 
     }  maxChildWidth += 4;
     
     class_327 tr = WurstClient.MC.field_1772;
     int titleBarWidth = tr.method_1727(this.title) + 4;
     if (this.minimizable)
       titleBarWidth += 11; 
     if (this.pinnable)
       titleBarWidth += 11; 
     if (this.closable) {
       titleBarWidth += 11;
     }
     int childrenHeight = 13;
     for (Component c : this.children)
       childrenHeight += c.getHeight() + 2; 
     childrenHeight += 2;
     
     if (childrenHeight > 200) {
       
       setWidth(Math.max(maxChildWidth + 3, titleBarWidth));
       setHeight(200);
     }
     else {
       
       setWidth(Math.max(maxChildWidth, titleBarWidth));
       setHeight(childrenHeight);
     } 
     
     validate();
   }
 
   
   public final void validate() {
     if (this.valid) {
       return;
     }
     int offsetY = 2;
     int cWidth = this.width - 4;
     for (Component c : this.children) {
       
       c.setX(2);
       c.setY(offsetY);
       c.setWidth(cWidth);
       offsetY += c.getHeight() + 2;
     } 
     
     this.innerHeight = offsetY;
     
     this.scrollingEnabled = (this.innerHeight > this.height - 13);
     if (this.scrollingEnabled) {
       cWidth -= 3;
     }
     for (Component c : this.children) {
       c.setWidth(cWidth);
     }
     this.valid = true;
   }
 
 
   
   public final void invalidate() { this.valid = false; }
 
 
 
   
   public final int countChildren() { return this.children.size(); }
 
 
 
   
   public final Component getChild(int index) { return (Component)this.children.get(index); }
 
 
   
   public final void add(Component component) {
     this.children.add(component);
     component.setParent(this);
     invalidate();
   }
 
   
   public final void remove(int index) {
     ((Component)this.children.get(index)).setParent(null);
     this.children.remove(index);
     invalidate();
   }
 
   
   public final void remove(Component component) {
     this.children.remove(component);
     component.setParent(null);
     invalidate();
   }
 
 
   
   public final boolean isDragging() { return this.dragging; }
 
 
   
   public final void startDragging(int mouseX, int mouseY) {
     this.dragging = true;
     this.dragOffsetX = this.x - mouseX;
     this.dragOffsetY = this.y - mouseY;
   }
 
   
   public final void dragTo(int mouseX, int mouseY) {
     this.x = mouseX + this.dragOffsetX;
     this.y = mouseY + this.dragOffsetY;
   }
 
   
   public final void stopDragging() {
     this.dragging = false;
     this.dragOffsetX = 0;
     this.dragOffsetY = 0;
   }
 
 
   
   public final boolean isMinimized() { return this.minimized; }
 
 
 
   
   public final void setMinimized(boolean minimized) { this.minimized = minimized; }
 
 
 
   
   public final boolean isMinimizable() { return this.minimizable; }
 
 
 
   
   public final void setMinimizable(boolean minimizable) { this.minimizable = minimizable; }
 
 
 
   
   public final boolean isPinned() { return this.pinned; }
 
 
 
   
   public final void setPinned(boolean pinned) { this.pinned = pinned; }
 
 
 
   
   public final boolean isPinnable() { return this.pinnable; }
 
 
 
   
   public final void setPinnable(boolean pinnable) { this.pinnable = pinnable; }
 
 
 
   
   public final boolean isClosable() { return this.closable; }
 
 
 
   
   public final void setClosable(boolean closable) { this.closable = closable; }
 
 
 
   
   public final boolean isClosing() { return this.closing; }
 
 
 
   
   public final void close() { this.closing = true; }
 
 
 
   
   public final boolean isInvisible() { return this.invisible; }
 
 
 
   
   public final void setInvisible(boolean invisible) { this.invisible = invisible; }
 
 
 
   
   public final int getInnerHeight() { return this.innerHeight; }
 
 
 
   
   public final int getScrollOffset() { return this.scrollOffset; }
 
 
 
   
   public final void setScrollOffset(int scrollOffset) { this.scrollOffset = scrollOffset; }
 
 
 
   
   public final boolean isScrollingEnabled() { return this.scrollingEnabled; }
 
 
 
   
   public final boolean isDraggingScrollbar() { return this.draggingScrollbar; }
 
 
   
   public final void startDraggingScrollbar(int mouseY) {
     this.draggingScrollbar = true;
     double outerHeight = (this.height - 13);
     double scrollbarY = outerHeight * -this.scrollOffset / this.innerHeight + 1.0D;
     
     this.scrollbarDragOffsetY = (int)(scrollbarY - mouseY);
   }
 
   
   public final void dragScrollbarTo(int mouseY) {
     int scrollbarY = mouseY + this.scrollbarDragOffsetY;
     double outerHeight = (this.height - 13);
     this.scrollOffset = (int)((scrollbarY - 1) / outerHeight * this.innerHeight * -1.0D);
     this.scrollOffset = Math.min(this.scrollOffset, 0);
     this.scrollOffset = Math.max(this.scrollOffset, -this.innerHeight + this.height - 13);
   }
 
   
   public final void stopDraggingScrollbar() {
     this.draggingScrollbar = false;
     this.scrollbarDragOffsetY = 0;
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\Window.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */