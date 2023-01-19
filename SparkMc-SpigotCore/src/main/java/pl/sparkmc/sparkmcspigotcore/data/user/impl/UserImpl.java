package pl.sparkmc.sparkmcspigotcore.data.user.impl;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.sparkmc.sparkmcspigotcore.data.user.User;
import pl.sparkmc.sparkmcspigotcore.utilities.chat.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserImpl implements User {

    private final UUID uniqueIdentifier;

    private final String name;


    public UserImpl(UUID uniqueIdentifier, String name){
        this.uniqueIdentifier = uniqueIdentifier;
        this.name = name;
    }


    @Override
    public UUID getUniqueIdentifier() {
        return this.uniqueIdentifier;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Player getAsPlayer() {
        return Bukkit.getPlayer(this.uniqueIdentifier);
    }

    @Override
    public void sendMessage(String message) {
        this.getAsPlayer().sendMessage(StringUtils.color(message));
    }

    @Override
    public void sendTitle(String title, String subtitle, int fadeIn, int fadeOut, int time) {
        this.getAsPlayer().sendTitle(StringUtils.color(title), StringUtils.color(subtitle), fadeIn, fadeOut, time);
    }

    @Override
    public void sendActionBar(String message){
        this.getAsPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(StringUtils.color(message)));
    }

    @Override
    public String getPrimaryKeyColumnName() {
        return "uuid";
    }

    @Override
    public Object getPrimaryKey() {
        return this.uniqueIdentifier.toString();
    }

    @Override
    public void fromResultSet(ResultSet resultSet) throws SQLException {
        //TODO
    }

    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    public String[] getColumnNames() {
        return new String[] {"uuid", "name"};
    }

    @Override
    public Object[] getColumnValues() {
        return new Object[] {
                this.uniqueIdentifier.toString(),
                this.name
        };
    }
}
