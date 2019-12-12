 package net.wurstclient.clickgui;
 
 
 
 
 
 
 
 
 
 
 
 public abstract class Popup
 {
   private final Component owner;
   private int x;
   private int y;
   private int width;
   private int height;
   private boolean closing;
   
   public Popup(Component owner) { this.owner = owner; }
 
   
   public abstract void handleMouseClick(int paramInt1, int paramInt2, int paramInt3);
 
   
   public abstract void render(int paramInt1, int paramInt2);
 
   
   public abstract int getDefaultWidth();
 
   
   public abstract int getDefaultHeight();
   
   public Component getOwner() { return this.owner; }
 
 
 
   
   public int getX() { return this.x; }
 
 
 
   
   public void setX(int x) { this.x = x; }
 
 
 
   
   public int getY() { return this.y; }
 
 
 
   
   public void setY(int y) { this.y = y; }
 
 
 
   
   public int getWidth() { return this.width; }
 
 
 
   
   public void setWidth(int width) { this.width = width; }
 
 
 
   
   public int getHeight() { return this.height; }
 
 
 
   
   public void setHeight(int height) { this.height = height; }
 
 
 
   
   public boolean isClosing() { return this.closing; }
 
 
 
   
   public void close() { this.closing = true; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\clickgui\Popup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */