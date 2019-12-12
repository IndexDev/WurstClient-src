 package net.wurstclient.altmanager;
 
 import java.io.IOException;
 import java.util.HashSet;
 import net.minecraft.class_1046;
 import net.minecraft.class_1068;
 import net.minecraft.class_1657;
 import net.minecraft.class_2960;
 import net.minecraft.class_310;
 import net.minecraft.class_332;
 import net.minecraft.class_742;
 import net.wurstclient.WurstClient;
 import org.lwjgl.opengl.GL11;
 
 
 
 
 
 
 
 
 
 
 public final class AltRenderer
 {
   private static final class_310 mc = WurstClient.MC;
   private static final HashSet<String> loadedSkins = new HashSet();
 
   
   private static void bindSkinTexture(String name) {
     class_2960 location = class_742.method_3124(name);
     
     if (loadedSkins.contains(name)) {
       
       mc.method_1531().method_4618(location);
 
       
       return;
     } 
     
     try {
       class_1046 img = class_742.method_3120(location, name);
       img.method_4625(mc.method_1478());
     }
     catch (IOException e) {
       
       e.printStackTrace();
     } 
     
     mc.method_1531().method_4618(location);
     loadedSkins.add(name);
   }
 
 
 
   
   public static void drawAltFace(String name, int x, int y, int w, int h, boolean selected) {
     try {
       bindSkinTexture(name);
       GL11.glEnable(3042);
       
       if (selected) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
       } else {
         GL11.glColor4f(0.9F, 0.9F, 0.9F, 1.0F);
       } 
       
       int fw = 192;
       int fh = 192;
       float u = 24.0F;
       float v = 24.0F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       fw = 192;
       fh = 192;
       u = 120.0F;
       v = 24.0F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
       
       GL11.glDisable(3042);
     }
     catch (Exception e) {
       
       e.printStackTrace();
     } 
   }
 
 
 
   
   public static void drawAltBody(String name, int x, int y, int width, int height) {
     try {
       bindSkinTexture(name);
 
 
       
       boolean slim = class_1068.method_4647(class_1657.method_7310(name)).equals("slim");
       
       GL11.glEnable(3042);
       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
 
       
       x += width / 4;
       y += 0;
       int w = width / 2;
       int h = height / 4;
       int fw = height * 2;
       int fh = height * 2;
       float u = (height / 4);
       float v = (height / 4);
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += 0;
       y += 0;
       w = width / 2;
       h = height / 4;
       u = (height / 4 * 5);
       v = (height / 4);
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += 0;
       y += height / 4;
       w = width / 2;
       h = height / 8 * 3;
       u = (height / 4) * 2.5F;
       v = (height / 4) * 2.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += 0;
       y += 0;
       w = width / 2;
       h = height / 8 * 3;
       u = (height / 4) * 2.5F;
       v = (height / 4) * 4.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x -= width / 16 * (slim ? 3 : 4);
       y += (slim ? (height / 32) : 0);
       w = width / 16 * (slim ? 3 : 4);
       h = height / 8 * 3;
       u = (height / 4) * 5.5F;
       v = (height / 4) * 2.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += 0;
       y += 0;
       w = width / 16 * (slim ? 3 : 4);
       h = height / 8 * 3;
       u = (height / 4) * 5.5F;
       v = (height / 4) * 4.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += width / 16 * (slim ? 11 : 12);
       y += 0;
       w = width / 16 * (slim ? 3 : 4);
       h = height / 8 * 3;
       u = (height / 4) * 5.5F;
       v = (height / 4) * 2.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += 0;
       y += 0;
       w = width / 16 * (slim ? 3 : 4);
       h = height / 8 * 3;
       u = (height / 4) * 5.5F;
       v = (height / 4) * 4.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x -= width / 2;
       y += height / 32 * (slim ? 11 : 12);
       w = width / 4;
       h = height / 8 * 3;
       u = (height / 4) * 0.5F;
       v = (height / 4) * 2.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += 0;
       y += 0;
       w = width / 4;
       h = height / 8 * 3;
       u = (height / 4) * 0.5F;
       v = (height / 4) * 4.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += width / 4;
       y += 0;
       w = width / 4;
       h = height / 8 * 3;
       u = (height / 4) * 0.5F;
       v = (height / 4) * 2.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += 0;
       y += 0;
       w = width / 4;
       h = height / 8 * 3;
       u = (height / 4) * 0.5F;
       v = (height / 4) * 4.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
       
       GL11.glDisable(3042);
     }
     catch (Exception e) {
       
       e.printStackTrace();
     } 
   }
 
 
 
   
   public static void drawAltBack(String name, int x, int y, int width, int height) {
     try {
       bindSkinTexture(name);
 
 
       
       boolean slim = class_1068.method_4647(class_1657.method_7310(name)).equals("slim");
       
       GL11.glEnable(3042);
       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
 
       
       x += width / 4;
       y += 0;
       int w = width / 2;
       int h = height / 4;
       int fw = height * 2;
       int fh = height * 2;
       float u = (height / 4 * 3);
       float v = (height / 4);
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += 0;
       y += 0;
       w = width / 2;
       h = height / 4;
       u = (height / 4 * 7);
       v = (height / 4);
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += 0;
       y += height / 4;
       w = width / 2;
       h = height / 8 * 3;
       u = (height / 4 * 4);
       v = (height / 4) * 2.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += 0;
       y += 0;
       w = width / 2;
       h = height / 8 * 3;
       u = (height / 4 * 4);
       v = (height / 4) * 4.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x -= width / 16 * (slim ? 3 : 4);
       y += (slim ? (height / 32) : 0);
       w = width / 16 * (slim ? 3 : 4);
       h = height / 8 * 3;
       u = (height / 4) * (slim ? 6.375F : 6.5F);
       v = (height / 4) * 2.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += 0;
       y += 0;
       w = width / 16 * (slim ? 3 : 4);
       h = height / 8 * 3;
       u = (height / 4) * (slim ? 6.375F : 6.5F);
       v = (height / 4) * 4.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += width / 16 * (slim ? 11 : 12);
       y += 0;
       w = width / 16 * (slim ? 3 : 4);
       h = height / 8 * 3;
       u = (height / 4) * (slim ? 6.375F : 6.5F);
       v = (height / 4) * 2.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += 0;
       y += 0;
       w = width / 16 * (slim ? 3 : 4);
       h = height / 8 * 3;
       u = (height / 4) * (slim ? 6.375F : 6.5F);
       v = (height / 4) * 4.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x -= width / 2;
       y += height / 32 * (slim ? 11 : 12);
       w = width / 4;
       h = height / 8 * 3;
       u = (height / 4) * 1.5F;
       v = (height / 4) * 2.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += 0;
       y += 0;
       w = width / 4;
       h = height / 8 * 3;
       u = (height / 4) * 1.5F;
       v = (height / 4) * 4.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += width / 4;
       y += 0;
       w = width / 4;
       h = height / 8 * 3;
       u = (height / 4) * 1.5F;
       v = (height / 4) * 2.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
 
       
       x += 0;
       y += 0;
       w = width / 4;
       h = height / 8 * 3;
       u = (height / 4) * 1.5F;
       v = (height / 4) * 4.5F;
       class_332.blit(x, y, u, v, w, h, fw, fh);
       
       GL11.glDisable(3042);
     }
     catch (Exception e) {
       
       e.printStackTrace();
     } 
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\altmanager\AltRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */