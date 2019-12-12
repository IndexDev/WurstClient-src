 package net.wurstclient.clickgui;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public abstract class Component
 {
   private int x;
   private int y;
   private int width;
   private int height;
   private Window parent;
   
   public void handleMouseClick(double mouseX, double mouseY, int mouseButton) {}
   
   public abstract void render(int paramInt1, int paramInt2, float paramFloat);
   
   public abstract int getDefaultWidth();
   
   public abstract int getDefaultHeight();
   
   public int getX() { return this.x; }
 
 
   
   public void setX(int x) {
     if (this.x != x) {
       invalidateParent();
     }
     this.x = x;
   }
 
 
   
   public int getY() { return this.y; }
 
 
   
   public void setY(int y) {
     if (this.y != y) {
       invalidateParent();
     }
     this.y = y;
   }
 
 
   
   public int getWidth() { return this.width; }
 
 
   
   public void setWidth(int width) {
     if (this.width != width) {
       invalidateParent();
     }
     this.width = width;
   }
 
 
   
   public int getHeight() { return this.height; }
 
 
   
   public void setHeight(int height) {
     if (this.height != height) {
       invalidateParent();
     }
     this.height = height;
   }
 
 
   
   public Window getParent() { return this.parent; }
 
 
 
   
   public void setParent(Window parent) { this.parent = parent; }
 
 
   
   private void invalidateParent() {
     if (this.parent != null)
       this.parent.invalidate(); 
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\Component.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */