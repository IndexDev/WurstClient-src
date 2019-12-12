 package net.wurstclient.hacks;
 
 import java.util.List;
 import net.minecraft.class_2561;
 import net.minecraft.class_303;
 import net.minecraft.class_338;
 import net.minecraft.class_341;
 import net.minecraft.class_3532;
 import net.wurstclient.Category;
 import net.wurstclient.SearchTags;
 import net.wurstclient.events.ChatInputListener;
 import net.wurstclient.hack.Hack;
 import net.wurstclient.util.MathUtils;
 
 
 
 
 
 
 
 
 @SearchTags({"NoSpam", "ChatFilter", "anti spam", "no spam", "chat filter"})
 public final class AntiSpamHack
   extends Hack
   implements ChatInputListener
 {
   public AntiSpamHack() {
     super("AntiSpam", "Blocks chat spam by adding a counter to repeated\nmessages.");
     
     setCategory(Category.CHAT);
   }
 
 
 
   
   public void onEnable() { EVENTS.add(ChatInputListener.class, this); }
 
 
 
 
   
   public void onDisable() { EVENTS.remove(ChatInputListener.class, this); }
 
 
 
   
   public void onReceivedMessage(ChatInputListener.ChatInputEvent event) {
     List<class_303> chatLines = event.getChatLines();
     if (chatLines.isEmpty()) {
       return;
     }
     class_338 chat = MC.field_1705.method_1743();
     
     int maxTextLength = class_3532.method_15357(chat.method_1811() / chat.method_1814());
     List<class_2561> newLines = class_341.method_1850(event.getComponent(), maxTextLength, MC.field_1772, false, false);
 
     
     int spamCounter = 1;
     int matchingLines = 0;
     
     int i = chatLines.size() - 1; while (true) { if (i >= 0)
       
       { String oldLine = ((class_303)chatLines.get(i)).method_1412().getString();
         
         if (matchingLines <= newLines.size() - 1)
         
         { String newLine = ((class_2561)newLines.get(matchingLines)).getString();
           
           if (matchingLines < newLines.size() - 1) {
             
             if (oldLine.equals(newLine)) {
               matchingLines++;
             } else {
               matchingLines = 0;
             } 
             
             continue;
           } 
           if (!oldLine.startsWith(newLine)) {
             
             matchingLines = 0;
             
             continue;
           } 
           if (i > 0 && matchingLines == newLines.size() - 1) {
 
             
             String twoLines = oldLine + ((class_303)chatLines.get(i - 1)).method_1412().getString();
             String addedText = twoLines.substring(newLine.length());
             
             if (addedText.startsWith(" [x") && addedText.endsWith("]")) {
 
               
               String oldSpamCounter = addedText.substring(3, addedText.length() - 1);
               
               if (MathUtils.isInteger(oldSpamCounter)) {
                 
                 spamCounter += Integer.parseInt(oldSpamCounter);
                 matchingLines++;
                 
                 continue;
               } 
             } 
           } 
           if (oldLine.length() == newLine.length())
           { spamCounter++; }
           else
           
           { String addedText = oldLine.substring(newLine.length());
             if (!addedText.startsWith(" [x") || !addedText.endsWith("]"))
             
             { matchingLines = 0;
                }
             
             else
             
             { String oldSpamCounter = addedText.substring(3, addedText.length() - 1);
               if (!MathUtils.isInteger(oldSpamCounter))
               
               { matchingLines = 0; }
               
               else
               
               { spamCounter += Integer.parseInt(oldSpamCounter);
 
 
                 
                 int i2 = i + matchingLines; }  }  continue; }  }  } else { break; }  int j = i + matchingLines;
       
       i--; }
 
     
     if (spamCounter > 1)
       event.getComponent().method_10864(" [x" + spamCounter + "]"); 
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\hacks\AntiSpamHack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */