 package net.wurstclient.keybinds;
 
 
 
 
 
 
 
 
 
 public final class PossibleKeybind
 {
   private final String command;
   private final String description;
   
   public PossibleKeybind(String command, String description) {
     this.command = command;
     this.description = description;
   }
 
 
   
   public String getCommand() { return this.command; }
 
 
 
   
   public String getDescription() { return this.description; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\keybinds\PossibleKeybind.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */