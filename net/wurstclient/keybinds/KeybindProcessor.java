 package net.wurstclient.keybinds;
 
 import net.minecraft.class_3675;
 import net.minecraft.class_437;
 import net.wurstclient.WurstClient;
 import net.wurstclient.command.CmdProcessor;
 import net.wurstclient.events.KeyPressListener;
 import net.wurstclient.hack.Hack;
 import net.wurstclient.hack.HackList;
 
 
 
 
 
 
 
 
 
 
 
 
 public final class KeybindProcessor
   implements KeyPressListener
 {
   private final HackList hax;
   private final KeybindList keybinds;
   private final CmdProcessor cmdProcessor;
   
   public KeybindProcessor(HackList hax, KeybindList keybinds, CmdProcessor cmdProcessor) {
     this.hax = hax;
     this.keybinds = keybinds;
     this.cmdProcessor = cmdProcessor;
   }
 
 
   
   public void onKeyPress(KeyPressListener.KeyPressEvent event) {
     if (event.getAction() != 1) {
       return;
     }
     class_437 screen = WurstClient.MC.field_1755;
     if (screen != null && !(screen instanceof net.wurstclient.clickgui.screens.ClickGuiScreen)) {
       return;
     }
     String keyName = getKeyName(event);
     String cmds = this.keybinds.getCommands(keyName);
     processCmds(cmds);
   }
 
   
   private String getKeyName(KeyPressListener.KeyPressEvent event) {
     int keyCode = event.getKeyCode();
     int scanCode = event.getScanCode();
     return class_3675.method_15985(keyCode, scanCode).method_1441();
   }
 
   
   private void processCmds(String cmds) {
     if (cmds == null) {
       return;
     }
     cmds = cmds.replace(";", "§").replace("§§", ";");
     for (String cmd : cmds.split("§")) {
       processCmd(cmd.trim());
     }
   }
   
   private void processCmd(String cmd) {
     if (cmd.startsWith(".")) {
       this.cmdProcessor.process(cmd.substring(1));
     } else if (cmd.contains(" ")) {
       this.cmdProcessor.process(cmd);
     } else {
       
       Hack hack = this.hax.getHackByName(cmd);
       
       if (hack != null) {
         hack.setEnabled(!hack.isEnabled());
       } else {
         this.cmdProcessor.process(cmd);
       } 
     } 
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\keybinds\KeybindProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */