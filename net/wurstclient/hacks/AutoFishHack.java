 package net.wurstclient.hacks;
 
 import net.minecraft.class_1536;
 import net.minecraft.class_1661;
 import net.minecraft.class_1799;
 import net.minecraft.class_1890;
 import net.minecraft.class_1893;
 import net.minecraft.class_238;
 import net.minecraft.class_243;
 import net.minecraft.class_2767;
 import net.minecraft.class_3417;
 import net.minecraft.class_746;
 import net.wurstclient.Category;
 import net.wurstclient.SearchTags;
 import net.wurstclient.events.PacketInputListener;
 import net.wurstclient.events.RenderListener;
 import net.wurstclient.events.UpdateListener;
 import net.wurstclient.hack.Hack;
 import net.wurstclient.settings.CheckboxSetting;
 import net.wurstclient.settings.SliderSetting;
 import net.wurstclient.util.ChatUtils;
 import net.wurstclient.util.RenderUtils;
 import org.lwjgl.opengl.GL11;
 
 
 
 
 
 
 
 
 
 
 @SearchTags({"FishBot", "auto fish", "fish bot", "fishing"})
 public final class AutoFishHack
   extends Hack
   implements UpdateListener, PacketInputListener, RenderListener
 {
   private final SliderSetting validRange = new SliderSetting("Valid range", "Any bites that occur outside of this range\nwill be ignored.\n\nIncrease your range if bites are not being\ndetected, decrease it if other people's\nbites are being detected as yours.", 1.5D, 0.25D, 8.0D, 0.25D, SliderSetting.ValueDisplay.DECIMAL);
 
 
 
 
 
   
   private CheckboxSetting debugDraw = new CheckboxSetting("Debug draw", "Shows where bites are occurring and where\nthey will be detected. Useful for optimizing\nyour 'Valid range' setting.", false);
   
   private int bestRodValue;
   
   private int bestRodSlot;
   
   private int castRodTimer;
   
   private int reelInTimer;
   
   private int box;
   
   private int cross;
   
   private int scheduledWindowClick;
   
   private class_243 lastSoundPos;
 
   
   public AutoFishHack() {
     super("AutoFish", "Automatically catches fish using your\nbest fishing rod. If it finds a better\nrod while fishing, it will automatically\nswitch to it.");
 
 
     
     setCategory(Category.OTHER);
     addSetting(this.validRange);
     addSetting(this.debugDraw);
   }
 
 
   
   public void onEnable() {
     this.bestRodValue = -1;
     this.bestRodSlot = -1;
     this.castRodTimer = 0;
     this.reelInTimer = -1;
     this.scheduledWindowClick = -1;
     this.lastSoundPos = null;
     
     this.box = GL11.glGenLists(1);
     
     this.cross = GL11.glGenLists(1);
     GL11.glNewList(this.cross, 4864);
     GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
     GL11.glBegin(1);
     GL11.glVertex3d(-0.125D, 0.0D, -0.125D);
     GL11.glVertex3d(0.125D, 0.0D, 0.125D);
     GL11.glVertex3d(0.125D, 0.0D, -0.125D);
     GL11.glVertex3d(-0.125D, 0.0D, 0.125D);
     GL11.glEnd();
     GL11.glEndList();
     
     EVENTS.add(UpdateListener.class, this);
     EVENTS.add(PacketInputListener.class, this);
     EVENTS.add(RenderListener.class, this);
   }
 
 
   
   public void onDisable() {
     EVENTS.remove(UpdateListener.class, this);
     EVENTS.remove(PacketInputListener.class, this);
     EVENTS.remove(RenderListener.class, this);
     
     GL11.glDeleteLists(this.box, 1);
     GL11.glDeleteLists(this.cross, 1);
   }
 
 
   
   public void onUpdate() {
     updateDebugDraw();
     
     if (this.reelInTimer > 0) {
       this.reelInTimer--;
     }
     class_746 player = MC.field_1724;
     class_1661 inventory = player.field_7514;
     
     if (this.scheduledWindowClick != -1) {
       
       IMC.getInteractionManager()
         .windowClick_PICKUP(this.scheduledWindowClick);
       this.castRodTimer = 15;
       
       return;
     } 
     updateBestRod();
     
     if (this.bestRodSlot == -1) {
       
       ChatUtils.message("Out of fishing rods.");
       setEnabled(false);
       
       return;
     } 
     if (this.bestRodSlot != inventory.field_7545) {
       
       selectBestRod();
       
       return;
     } 
     
     if (this.castRodTimer > 0) {
       
       this.castRodTimer--;
       
       return;
     } 
     
     if (player.field_7513 == null) {
       
       rightClick();
       this.castRodTimer = 15;
       this.reelInTimer = 1200;
     } 
 
     
     if (this.reelInTimer == 0) {
       
       this.reelInTimer--;
       rightClick();
       this.castRodTimer = 15;
     } 
   }
 
   
   private void updateDebugDraw() {
     if (this.debugDraw.isChecked()) {
       
       GL11.glNewList(this.box, 4864);
 
       
       class_238 box = new class_238(-this.validRange.getValue(), -0.0625D, -this.validRange.getValue(), this.validRange.getValue(), 0.0625D, this.validRange.getValue());
       GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
       RenderUtils.drawOutlinedBox(box);
       GL11.glEndList();
     } 
   }
 
   
   private void updateBestRod() {
     class_1661 inventory = MC.field_1724.field_7514;
     int selectedSlot = inventory.field_7545;
     class_1799 selectedStack = inventory.method_5438(selectedSlot);
 
     
     this.bestRodValue = getRodValue(selectedStack);
     this.bestRodSlot = (this.bestRodValue > -1) ? selectedSlot : -1;
 
     
     for (int slot = 0; slot < 36; slot++) {
       
       class_1799 stack = inventory.method_5438(slot);
       int rodValue = getRodValue(stack);
       
       if (rodValue > this.bestRodValue) {
         
         this.bestRodValue = rodValue;
         this.bestRodSlot = slot;
       } 
     } 
   }
 
   
   private int getRodValue(class_1799 stack) {
     if (stack.method_7960() || !(stack.method_7909() instanceof net.minecraft.class_1787)) {
       return -1;
     }
     
     int luckOTSLvl = class_1890.method_8225(class_1893.field_9114, stack);
     int lureLvl = class_1890.method_8225(class_1893.field_9100, stack);
     
     int unbreakingLvl = class_1890.method_8225(class_1893.field_9119, stack);
     
     int mendingBonus = class_1890.method_8225(class_1893.field_9101, stack);
     int noVanishBonus = class_1890.method_8221(stack) ? 0 : 1;
     
     return luckOTSLvl * 9 + lureLvl * 9 + unbreakingLvl * 2 + mendingBonus + noVanishBonus;
   }
 
 
   
   private void selectBestRod() {
     class_1661 inventory = MC.field_1724.field_7514;
     
     if (this.bestRodSlot < 9) {
       
       inventory.field_7545 = this.bestRodSlot;
       
       return;
     } 
     int firstEmptySlot = inventory.method_7376();
     
     if (firstEmptySlot != -1) {
       
       if (firstEmptySlot >= 9) {
         IMC.getInteractionManager()
           .windowClick_QUICK_MOVE(36 + inventory.field_7545);
       }
       IMC.getInteractionManager().windowClick_QUICK_MOVE(this.bestRodSlot);
     }
     else {
       
       IMC.getInteractionManager().windowClick_PICKUP(this.bestRodSlot);
       IMC.getInteractionManager()
         .windowClick_PICKUP(36 + inventory.field_7545);
       
       this.scheduledWindowClick = -this.bestRodSlot;
     } 
   }
 
 
   
   public void onReceivedPacket(PacketInputListener.PacketInputEvent event) {
     class_746 player = MC.field_1724;
     if (player == null || player.field_7513 == null) {
       return;
     }
     if (!(event.getPacket() instanceof class_2767)) {
       return;
     }
     
     class_2767 sound = (class_2767)event.getPacket();
     if (!class_3417.field_14660.equals(sound.method_11894())) {
       return;
     }
     if (this.debugDraw.isChecked()) {
       this.lastSoundPos = new class_243(sound.method_11890(), sound.method_11889(), sound.method_11893());
     }
     
     class_1536 bobber = player.field_7513;
     if (Math.abs(sound.method_11890() - bobber.field_5987) > this.validRange.getValue() || 
       Math.abs(sound.method_11893() - bobber.field_6035) > this.validRange.getValue()) {
       return;
     }
     
     rightClick();
     this.castRodTimer = 15;
   }
 
 
   
   private void rightClick() {
     class_1799 stack = MC.field_1724.field_7514.method_7391();
     if (stack.method_7960() || !(stack.method_7909() instanceof net.minecraft.class_1787)) {
       return;
     }
     
     IMC.rightClick();
   }
 
 
   
   public void onRender(float partialTicks) {
     if (!this.debugDraw.isChecked()) {
       return;
     }
     
     GL11.glEnable(3042);
     GL11.glBlendFunc(770, 771);
     GL11.glEnable(2848);
     GL11.glLineWidth(2.0F);
     GL11.glDisable(3553);
     GL11.glEnable(2884);
     GL11.glDisable(2929);
     
     GL11.glPushMatrix();
     RenderUtils.applyRenderOffset();
     
     class_1536 bobber = MC.field_1724.field_7513;
     if (bobber != null) {
       
       GL11.glPushMatrix();
       GL11.glTranslated(bobber.field_5987, bobber.field_6010, bobber.field_6035);
       GL11.glCallList(this.box);
       GL11.glPopMatrix();
     } 
     
     if (this.lastSoundPos != null) {
       
       GL11.glPushMatrix();
       GL11.glTranslated(this.lastSoundPos.field_1352, this.lastSoundPos.field_1351, this.lastSoundPos.field_1350);
       GL11.glCallList(this.cross);
       GL11.glPopMatrix();
     } 
     
     GL11.glPopMatrix();
 
     
     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
     GL11.glEnable(2929);
     GL11.glEnable(3553);
     GL11.glDisable(3042);
     GL11.glDisable(2848);
   }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\hacks\AutoFishHack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */