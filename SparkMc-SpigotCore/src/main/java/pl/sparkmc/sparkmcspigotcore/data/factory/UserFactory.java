package pl.sparkmc.sparkmcspigotcore.data.factory;


import pl.sparkmc.sparkmcspigotcore.data.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserFactory {

    Optional<User> getByUniqueId(UUID uniqueIdentifier);

    Optional<User> getUserByName(String name);

    void registerUser(User user);

    void unregisterUser(User boxUser);

    List<User> findAll();

}
