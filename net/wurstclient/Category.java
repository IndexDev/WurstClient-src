 package net.wurstclient;
 
 
 
 
 
 
 
 
 public static enum Category
 {
   BLOCKS("Blocks"),
   MOVEMENT("Movement"),
   COMBAT("Combat"),
   RENDER("Render"),
   CHAT("Chat"),
   FUN("Fun"),
   ITEMS("Items"),
   OTHER("Other");
 
   
   private final String name;
 
   
   Category(String name) { this.name = name; }
 
 
 
   
   public String getName() { return this.name; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\Category.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */