 package net.wurstclient.altmanager.screens;
 
 import net.minecraft.class_2585;
 import net.minecraft.class_437;
 import net.minecraft.class_442;
 import net.wurstclient.altmanager.LoginManager;
 
 
 
 
 
 
 
 
 
 public final class DirectLoginScreen
   extends AltEditorScreen
 {
   public DirectLoginScreen(class_437 prevScreen) { super(prevScreen, new class_2585("Direct Login")); }
 
 
 
 
   
   protected String getDoneButtonText() { return "Login"; }
 
 
 
   
   protected void pressDoneButton() {
     if (getPassword().isEmpty()) {
       
       this.message = "";
       LoginManager.changeCrackedName(getEmail());
     } else {
       
       this.message = LoginManager.login(getEmail(), getPassword());
     } 
     if (this.message.isEmpty()) {
       this.minecraft.method_1507(new class_442());
     } else {
       doErrorEffect();
     } 
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\altmanager\screens\DirectLoginScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */