package pl.sparkmc.sparkmcspigotcore.utilities.messages;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import pl.sparkmc.sparkmcspigotcore.SparkSpigotCore;

public final class PluginMessage {

    private static void sendPluginMessage(Player player, String subChannel, String message) {

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF(subChannel);
        output.writeUTF(message);

        player.sendPluginMessage(SparkSpigotCore.getSingleton(), "BungeeCord", output.toByteArray());
    }

    public static void sendChatMessage(Player player, String message) {
        sendPluginMessage(player, "Message", message);
    }

}
