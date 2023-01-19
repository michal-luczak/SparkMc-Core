package pl.sparkmc.sparkmcspigotcore.data.factory.impl;

import pl.sparkmc.sparkmcspigotcore.data.factory.UserFactory;
import pl.sparkmc.sparkmcspigotcore.data.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserFactoryImpl implements UserFactory {


    @Override
    public Optional<User> getByUniqueId(UUID uniqueIdentifier) {
        return Optional.empty();
        //TODO
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return Optional.empty();
        //TODO
    }

    @Override
    public void registerUser(User user) {
        //TODO
    }

    @Override
    public void unregisterUser(User boxUser) {
        //TODO
    }

    @Override
    public List<User> findAll() {
        return null;
        //TODO
    }
}
