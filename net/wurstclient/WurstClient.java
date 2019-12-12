 package net.wurstclient;
 
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
 import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
 import net.minecraft.class_2960;
 import net.minecraft.class_310;
 import net.minecraft.class_3675;
 import net.wurstclient.analytics.WurstAnalytics;
 import net.wurstclient.clickgui.ClickGui;
 import net.wurstclient.command.CmdList;
 import net.wurstclient.command.CmdProcessor;
 import net.wurstclient.event.EventManager;
 import net.wurstclient.hack.HackList;
 import net.wurstclient.hud.IngameHUD;
 import net.wurstclient.keybinds.KeybindList;
 import net.wurstclient.keybinds.KeybindProcessor;
 import net.wurstclient.mixinterface.IMinecraftClient;
 import net.wurstclient.navigator.Navigator;
 import net.wurstclient.other_feature.OtfList;
 import net.wurstclient.settings.SettingsFile;
 import net.wurstclient.update.WurstUpdater;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public static enum WurstClient
 {
   INSTANCE;
   
   private FabricKeyBinding zoomKey;
   
   private Path wurstFolder;
   
   private WurstUpdater updater;
   
   private static boolean guiInitialized;
   
   private boolean enabled;
   
   private FriendsList friends;
   
   private RotationFaker rotationFaker;
   
   private IngameHUD hud;
   
   private CmdProcessor cmdProcessor;
   private Navigator navigator;
   private ClickGui gui;
   
   WurstClient() { this.enabled = true; }
   private KeybindList keybinds; private SettingsFile settingsFile; private OtfList otfs; private CmdList cmds; private HackList hax; private EventManager eventManager; private WurstAnalytics analytics; public static final String MC_VERSION = "1.14.4"; public static final String VERSION = "7.0pre16"; public static final IMinecraftClient IMC; public static final class_310 MC;
   
   static  {
     MC = class_310.method_1551();
     IMC = (IMinecraftClient)MC;
   }
   
   public void initialize() {
     System.out.println("Starting Wurst Client...");
     
     this.wurstFolder = createWurstFolder();
     
     String trackingID = "UA-52838431-5";
     String hostname = "client.wurstclient.net";
     Path analyticsFile = this.wurstFolder.resolve("analytics.json");
     this.analytics = new WurstAnalytics(trackingID, hostname, analyticsFile);
     
     this.eventManager = new EventManager(this);
     
     Path enabledHacksFile = this.wurstFolder.resolve("enabled-hacks.json");
     this.hax = new HackList(enabledHacksFile);
     
     this.cmds = new CmdList();
     
     this.otfs = new OtfList();
     
     Path settingsFile = this.wurstFolder.resolve("settings.json");
     this.settingsFile = new SettingsFile(settingsFile, this.hax, this.cmds, this.otfs);
     this.settingsFile.load();
     
     Path keybindsFile = this.wurstFolder.resolve("keybinds.json");
     this.keybinds = new KeybindList(keybindsFile);
     
     Path guiFile = this.wurstFolder.resolve("windows.json");
     this.gui = new ClickGui(guiFile);
     
     Path preferencesFile = this.wurstFolder.resolve("preferences.json");
     this.navigator = new Navigator(preferencesFile, this.hax, this.cmds, this.otfs);
     
     Path friendsFile = this.wurstFolder.resolve("friends.json");
     this.friends = new FriendsList(friendsFile);
     this.friends.load();
     
     this.cmdProcessor = new CmdProcessor(this.cmds);
     this.eventManager.add(net.wurstclient.events.ChatOutputListener.class, this.cmdProcessor);
     
     KeybindProcessor keybindProcessor = new KeybindProcessor(this.hax, this.keybinds, this.cmdProcessor);
     
     this.eventManager.add(net.wurstclient.events.KeyPressListener.class, keybindProcessor);
     
     this.hud = new IngameHUD();
     this.eventManager.add(net.wurstclient.events.GUIRenderListener.class, this.hud);
     
     this.rotationFaker = new RotationFaker();
     this.eventManager.add(net.wurstclient.events.PreMotionListener.class, this.rotationFaker);
     this.eventManager.add(net.wurstclient.events.PostMotionListener.class, this.rotationFaker);
     
     this.updater = new WurstUpdater();
     this.eventManager.add(net.wurstclient.events.UpdateListener.class, this.updater);
     
     this
       
       .zoomKey = FabricKeyBinding.Builder.create(new class_2960("wurst", "zoom"), class_3675.class_307.field_1668, 86, "Zoom").build();
     KeyBindingRegistry.INSTANCE.register(this.zoomKey);
     
     this.analytics.trackPageView("/mc1.14.4/v7.0pre16", "Wurst 7.0pre16 MC1.14.4");
   }
 
 
   
   private Path createWurstFolder() {
     Path dotMinecraftFolder = MC.field_1697.toPath();
     Path wurstFolder = dotMinecraftFolder.resolve("wurst");
 
     
     try {
       Files.createDirectories(wurstFolder, new java.nio.file.attribute.FileAttribute[0]);
     }
     catch (IOException e) {
       
       throw new RuntimeException("Couldn't create .minecraft/wurst folder.", e);
     } 
 
     
     return wurstFolder;
   }
 
 
   
   public WurstAnalytics getAnalytics() { return this.analytics; }
 
 
 
   
   public EventManager getEventManager() { return this.eventManager; }
 
 
 
   
   public void saveSettings() { this.settingsFile.save(); }
 
 
 
   
   public HackList getHax() { return this.hax; }
 
 
 
   
   public CmdList getCmds() { return this.cmds; }
 
 
 
   
   public OtfList getOtfs() { return this.otfs; }
 
 
 
   
   public KeybindList getKeybinds() { return this.keybinds; }
 
 
   
   public ClickGui getGui() {
     if (!guiInitialized) {
       
       guiInitialized = true;
       this.gui.init();
     } 
     
     return this.gui;
   }
 
 
   
   public Navigator getNavigator() { return this.navigator; }
 
 
 
   
   public CmdProcessor getCmdProcessor() { return this.cmdProcessor; }
 
 
 
   
   public IngameHUD getHud() { return this.hud; }
 
 
 
   
   public RotationFaker getRotationFaker() { return this.rotationFaker; }
 
 
 
   
   public FriendsList getFriends() { return this.friends; }
 
 
 
   
   public boolean isEnabled() { return this.enabled; }
 
 
 
   
   public void setEnabled(boolean enabled) { this.enabled = enabled; }
 
 
 
   
   public WurstUpdater getUpdater() { return this.updater; }
 
 
 
   
   public Path getWurstFolder() { return this.wurstFolder; }
 
 
 
   
   public FabricKeyBinding getZoomKey() { return this.zoomKey; }
 }


/* Location:              C:\Users\Administrator\Desktop\WURST BY INDEX.jar!\net\wurstclient\WurstClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */