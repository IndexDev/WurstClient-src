 package net.wurstclient.altmanager.screens;
 
 import java.io.IOException;
 import java.io.InputStream;
 import java.net.URI;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import net.minecraft.class_156;
 import net.minecraft.class_2561;
 import net.minecraft.class_342;
 import net.minecraft.class_4185;
 import net.minecraft.class_437;
 import net.wurstclient.WurstClient;
 import net.wurstclient.altmanager.AltRenderer;
 import net.wurstclient.altmanager.NameGenerator;
 import org.lwjgl.opengl.GL11;
 
 
 
 
 
 
 
 
 
 
 public abstract class AltEditorScreen
   extends class_437
 {
   private final Path skinFolder = WurstClient.INSTANCE
     .getWurstFolder().resolve("skins");
   
   protected final class_437 prevScreen;
   
   private class_342 emailBox;
   
   private class_342 passwordBox;
   
   private class_4185 doneButton;
   private class_4185 stealSkinButton;
   protected String message = "";
   
   private int errorTimer;
   
   public AltEditorScreen(class_437 prevScreen, class_2561 title) {
     super(title);
     this.prevScreen = prevScreen;
   }
 
 
   
   public final void init() {
     addButton(this
         
         .doneButton = new class_4185(this.width / 2 - 100, this.height / 4 + 72 + 12, 200, 20, getDoneButtonText(), b -> pressDoneButton()));
     
     addButton(new class_4185(this.width / 2 - 100, this.height / 4 + 120 + 12, 200, 20, "Cancel", b -> 
           this.minecraft.method_1507(this.prevScreen)));
     
     addButton(new class_4185(this.width / 2 - 100, this.height / 4 + 96 + 12, 200, 20, "Random Name", b -> 
           
           this.emailBox.method_1852(NameGenerator.generateName())));
     
     addButton(this.stealSkinButton = new class_4185(this.width - (this.width / 2 - 100) / 2 - 64, this.height - 32, 128, 20, "Steal Skin", b -> 
           
           this.message = stealSkin(getEmail())));
     
     addButton(new class_4185((this.width / 2 - 100) / 2 - 64, this.height - 32, 128, 20, "Open Skin Folder", b -> 
           
           class_156.method_668().method_672(this.skinFolder.toFile())));
     
     this.emailBox = new class_342(this.font, this.width / 2 - 100, 60, 200, 20, "");
     this.emailBox.method_1880(48);
     this.emailBox.method_1876(true);
     this.emailBox.method_1852(getDefaultEmail());
     this.children.add(this.emailBox);
     
     this.passwordBox = new class_342(this.font, this.width / 2 - 100, 100, 200, 20, "");
     
     this.passwordBox.method_1852(getDefaultPassword());
     this.passwordBox.method_1854((text, int_1) -> {
           String stars = "";
           for (int i = 0; i < text.length(); i++)
             stars = stars + "*"; 
           return stars;
         });
     this.children.add(this.passwordBox);
     
     method_20085(this.emailBox);
   }
 
 
   
   public final void tick() {
     this.emailBox.method_1865();
     this.passwordBox.method_1865();
     
     String email = this.emailBox.method_1882().trim();
     boolean alex = email.equalsIgnoreCase("Alexander01998");
     
     this.doneButton
       .active = (!email.isEmpty() && (!alex || !this.passwordBox.method_1882().isEmpty()));
     
     this.stealSkinButton.active = !alex;
   }
 
 
   
   protected final String getEmail() { return this.emailBox.method_1882(); }
 
 
 
   
   protected final String getPassword() { return this.passwordBox.method_1882(); }
 
 
 
   
   protected String getDefaultEmail() { return this.minecraft.method_1548().method_1676(); }
 
 
 
   
   protected String getDefaultPassword() { return ""; }
 
 
 
 
 
 
 
   
   protected final void doErrorEffect() { this.errorTimer = 8; }
 
 
   
   private final String stealSkin(String name) {
     String skin = name + ".png";
 
     
     URI u = URI.create("http://skins.minecraft.net/MinecraftSkins/").resolve(skin);
     Path path = this.skinFolder.resolve(skin);
     
     try (InputStream in = u.toURL().openStream()) {
       
       Files.copy(in, path, new java.nio.file.CopyOption[0]);
       return "§a§lSaved skin as " + skin;
     }
     catch (IOException e) {
       
       e.printStackTrace();
       return "§4§lSkin could not be saved.";
     } 
   }
 
 
   
   public boolean keyPressed(int keyCode, int scanCode, int int_3) {
     if (keyCode == 257) {
       this.doneButton.onPress();
     }
     return super.keyPressed(keyCode, scanCode, int_3);
   }
 
 
   
   public boolean mouseClicked(double x, double y, int button) {
     this.emailBox.mouseClicked(x, y, button);
     this.passwordBox.mouseClicked(x, y, button);
     
     if (this.emailBox.isFocused() || this.passwordBox.isFocused()) {
       this.message = "";
     }
     return super.mouseClicked(x, y, button);
   }
 
 
   
   public void render(int mouseX, int mouseY, float partialTicks) {
     renderBackground();
 
     
     AltRenderer.drawAltBack(this.emailBox.method_1882(), (this.width / 2 - 100) / 2 - 64, this.height / 2 - 128, 128, 256);
     
     AltRenderer.drawAltBody(this.emailBox.method_1882(), this.width - (this.width / 2 - 100) / 2 - 64, this.height / 2 - 128, 128, 256);
 
 
     
     drawString(this.font, "Name or E-Mail", this.width / 2 - 100, 47, 10526880);
     drawString(this.font, "Password", this.width / 2 - 100, 87, 10526880);
     drawCenteredString(this.font, this.message, this.width / 2, 142, 16777215);
 
     
     this.emailBox.render(mouseX, mouseY, partialTicks);
     this.passwordBox.render(mouseX, mouseY, partialTicks);
 
     
     if (this.errorTimer > 0) {
       
       GL11.glDisable(3553);
       GL11.glDisable(2884);
       GL11.glEnable(3042);
       
       GL11.glColor4f(1.0F, 0.0F, 0.0F, this.errorTimer / 16.0F);
       
       GL11.glBegin(7);
       
       GL11.glVertex2d(0.0D, 0.0D);
       GL11.glVertex2d(this.width, 0.0D);
       GL11.glVertex2d(this.width, this.height);
       GL11.glVertex2d(0.0D, this.height);
       
       GL11.glEnd();
       
       GL11.glEnable(3553);
       GL11.glEnable(2884);
       GL11.glDisable(3042);
       this.errorTimer--;
     } 
     
     super.render(mouseX, mouseY, partialTicks);
   }
   
   protected abstract String getDoneButtonText();
   
   protected abstract void pressDoneButton();
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\altmanager\screens\AltEditorScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */