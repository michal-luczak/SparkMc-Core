package pl.sparkmc.sparkmcspigotcore.data.user;

import org.bukkit.entity.Player;
import pl.sparkmc.sparkmcspigotcore.data.storage.database.persistable.PersistableLoadable;
import pl.sparkmc.sparkmcspigotcore.data.storage.database.persistable.PersistableSaveable;

import java.util.UUID;

public interface User extends PersistableSaveable, PersistableLoadable {

    UUID getUniqueIdentifier();

    String getName();


    Player getAsPlayer();

    void sendMessage(String message);

    void sendTitle(String title, String subtitle, int fadeIn, int fadeOut, int time);

    void sendActionBar(String message);
}
