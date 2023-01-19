package pl.sparkmc.sparkmcspigotcore.data.storage.database;

import pl.sparkmc.sparkmcspigotcore.data.storage.util.Database;
import pl.sparkmc.sparkmcspigotcore.data.user.User;

public interface MySQLStorage extends Database {

    void loadAll();

    void saveUser(User user);
}
