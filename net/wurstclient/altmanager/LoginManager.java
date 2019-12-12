 package net.wurstclient.altmanager;
 
 import com.mojang.authlib.Agent;
 import com.mojang.authlib.exceptions.AuthenticationException;
 import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
 import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
 import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
 import java.net.Proxy;
 import net.minecraft.class_320;
 import net.wurstclient.WurstClient;
 
 
 
 
 
 
 
 
 
 
 
 
 
 public final class LoginManager
 {
   public static String login(String email, String password) {
     YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication)(new YggdrasilAuthenticationService(Proxy.NO_PROXY, "")).createUserAuthentication(Agent.MINECRAFT);
     
     auth.setUsername(email);
     auth.setPassword(password);
 
     
     try {
       auth.logIn();
       WurstClient.IMC
         .setSession(new class_320(auth.getSelectedProfile().getName(), auth
             .getSelectedProfile().getId().toString(), auth
             .getAuthenticatedToken(), "mojang"));
       return "";
     }
     catch (AuthenticationUnavailableException e) {
       
       return "§4§lCannot contact authentication server!";
     }
     catch (AuthenticationException e) {
       
       e.printStackTrace();
       
       if (e.getMessage().contains("Invalid username or password.") || e
         .getMessage().toLowerCase().contains("account migrated")) {
         return "§4§lWrong password! (or shadowbanned)";
       }
       return "§4§lCannot contact authentication server!";
     }
     catch (NullPointerException e) {
       
       e.printStackTrace();
       return "§4§lWrong password! (or shadowbanned)";
     } 
   }
 
 
   
   public static void changeCrackedName(String newName) { WurstClient.IMC.setSession(new class_320(newName, "", "", "mojang")); }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\altmanager\LoginManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */