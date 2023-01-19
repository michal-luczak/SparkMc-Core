package pl.sparkmc.sparkmcspigotcore.data.storage.database.persistable;

public interface PersistableSaveable {

    String getTableName();

    String[] getColumnNames();

    Object[] getColumnValues();

}
