package pl.sparkmc.sparkmcspigotcore.data.storage;

import pl.sparkmc.sparkmcspigotcore.SparkSpigotCore;
import pl.sparkmc.sparkmcspigotcore.data.storage.util.Database;

public abstract class AbstractDatabase implements Database {

    protected final SparkSpigotCore sparkSpigotCore;

    protected AbstractDatabase(SparkSpigotCore sparkSpigotCore) {
        this.sparkSpigotCore = sparkSpigotCore;
    }

    @Override
    public void open() {
        this.create();
    }

    @Override
    public void close() {
        this.destroy();
    }

    protected abstract void create();

    protected abstract void destroy();

}
