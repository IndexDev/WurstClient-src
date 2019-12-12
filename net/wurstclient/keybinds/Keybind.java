 package net.wurstclient.keybinds;
 
 import java.util.Objects;
 
 
 
 
 
 
 
 public class Keybind
   extends Object
   implements Comparable<Keybind>
 {
   private final String key;
   private final String commands;
   
   public Keybind(String key, String commands) {
     this.key = (String)Objects.requireNonNull(key);
     this.commands = (String)Objects.requireNonNull(commands);
   }
 
 
   
   public String getKey() { return this.key; }
 
 
 
   
   public String getCommands() { return this.commands; }
 
 
 
 
   
   public int compareTo(Keybind o) { return this.key.compareToIgnoreCase(o.key); }
 
 
 
   
   public boolean equals(Object obj) {
     if (obj == null || !(obj instanceof Keybind)) {
       return false;
     }
     Keybind otherKeybind = (Keybind)obj;
     return this.key.equalsIgnoreCase(otherKeybind.key);
   }
 
 
 
   
   public int hashCode() { return this.key.hashCode(); }
 
 
 
 
   
   public String toString() { return this.key.replace("key.keyboard.", "") + " -> " + this.commands; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\keybinds\Keybind.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */