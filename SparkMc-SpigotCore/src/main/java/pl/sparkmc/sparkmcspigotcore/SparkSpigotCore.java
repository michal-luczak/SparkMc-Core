package pl.sparkmc.sparkmcspigotcore;

import me.taison.CommandAPI;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import pl.sparkmc.sparkmcspigotcore.data.factory.UserFactory;
import pl.sparkmc.sparkmcspigotcore.data.factory.impl.UserFactoryImpl;
import pl.sparkmc.sparkmcspigotcore.data.storage.database.MySQLStorage;
import pl.sparkmc.sparkmcspigotcore.data.storage.database.impl.MySQLStorageImpl;
import pl.sparkmc.sparkmcspigotcore.listeners.PluginMsgListener;
import pl.sparkmc.sparkmcspigotcore.service.Service;

import java.lang.reflect.InvocationTargetException;

public final class SparkSpigotCore extends JavaPlugin {

    //static singleton
    public static SparkSpigotCore singleton;
    public static SparkSpigotCore getSingleton() {
        return singleton;
    }


    private MySQLStorage database;
    private UserFactory userFactory;


    @Override
    public void onLoad() {
        singleton = this;
    }

    @Override
    public void onEnable() {
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PluginMsgListener());

        CommandAPI.getInstance(this)
                .addPackageName(".commands")
                .registerCommands();

        this.database = new MySQLStorageImpl(this);
        this.database.open();

        this.userFactory = new UserFactoryImpl();
    }

    private void initializeListeners() {
        getLogger().info("Initializing listeners..");
        for (Class<?> clazz : new Reflections(getClass().getPackageName() + ".listeners").getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                getServer().getPluginManager().registerEvents(listener, this);
            } catch (InstantiationException | NoSuchMethodException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onDisable() {
        Service.shutdown();
    }


    //Getters
    public MySQLStorage getDatabase() {
        return database;
    }
    public UserFactory getUserFactory() {
        return userFactory;
    }
}
