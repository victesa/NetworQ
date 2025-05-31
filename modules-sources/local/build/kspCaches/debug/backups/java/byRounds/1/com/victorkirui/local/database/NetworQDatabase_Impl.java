package com.victorkirui.local.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.victorkirui.local.daos.CardDao;
import com.victorkirui.local.daos.CardDao_Impl;
import com.victorkirui.local.daos.EventDao;
import com.victorkirui.local.daos.EventDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class NetworQDatabase_Impl extends NetworQDatabase {
  private volatile EventDao _eventDao;

  private volatile CardDao _cardDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `EventEntity` (`primaryKey` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `eventName` TEXT NOT NULL, `tags` TEXT NOT NULL, `location` TEXT, `date` TEXT NOT NULL, `description` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `BusinessCard` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `firstName` TEXT NOT NULL, `lastName` TEXT NOT NULL, `emailAddress` TEXT NOT NULL, `phoneNumber` TEXT NOT NULL, `companyName` TEXT NOT NULL, `jobTitle` TEXT NOT NULL, `website` TEXT NOT NULL, `location` TEXT NOT NULL, `cardImage` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '27af2e2804df7d1bae156d0baef91c35')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `EventEntity`");
        db.execSQL("DROP TABLE IF EXISTS `BusinessCard`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsEventEntity = new HashMap<String, TableInfo.Column>(6);
        _columnsEventEntity.put("primaryKey", new TableInfo.Column("primaryKey", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventEntity.put("eventName", new TableInfo.Column("eventName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventEntity.put("tags", new TableInfo.Column("tags", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventEntity.put("location", new TableInfo.Column("location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventEntity.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventEntity.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEventEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEventEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEventEntity = new TableInfo("EventEntity", _columnsEventEntity, _foreignKeysEventEntity, _indicesEventEntity);
        final TableInfo _existingEventEntity = TableInfo.read(db, "EventEntity");
        if (!_infoEventEntity.equals(_existingEventEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "EventEntity(com.victorkirui.local.entities.EventEntity).\n"
                  + " Expected:\n" + _infoEventEntity + "\n"
                  + " Found:\n" + _existingEventEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsBusinessCard = new HashMap<String, TableInfo.Column>(10);
        _columnsBusinessCard.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBusinessCard.put("firstName", new TableInfo.Column("firstName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBusinessCard.put("lastName", new TableInfo.Column("lastName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBusinessCard.put("emailAddress", new TableInfo.Column("emailAddress", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBusinessCard.put("phoneNumber", new TableInfo.Column("phoneNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBusinessCard.put("companyName", new TableInfo.Column("companyName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBusinessCard.put("jobTitle", new TableInfo.Column("jobTitle", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBusinessCard.put("website", new TableInfo.Column("website", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBusinessCard.put("location", new TableInfo.Column("location", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBusinessCard.put("cardImage", new TableInfo.Column("cardImage", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBusinessCard = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBusinessCard = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBusinessCard = new TableInfo("BusinessCard", _columnsBusinessCard, _foreignKeysBusinessCard, _indicesBusinessCard);
        final TableInfo _existingBusinessCard = TableInfo.read(db, "BusinessCard");
        if (!_infoBusinessCard.equals(_existingBusinessCard)) {
          return new RoomOpenHelper.ValidationResult(false, "BusinessCard(com.victorkirui.local.entities.BusinessCard).\n"
                  + " Expected:\n" + _infoBusinessCard + "\n"
                  + " Found:\n" + _existingBusinessCard);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "27af2e2804df7d1bae156d0baef91c35", "d6049941bd57b2eded08c0d45db6bdb0");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "EventEntity","BusinessCard");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `EventEntity`");
      _db.execSQL("DELETE FROM `BusinessCard`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(EventDao.class, EventDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CardDao.class, CardDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public EventDao eventDao() {
    if (_eventDao != null) {
      return _eventDao;
    } else {
      synchronized(this) {
        if(_eventDao == null) {
          _eventDao = new EventDao_Impl(this);
        }
        return _eventDao;
      }
    }
  }

  @Override
  public CardDao cardDao() {
    if (_cardDao != null) {
      return _cardDao;
    } else {
      synchronized(this) {
        if(_cardDao == null) {
          _cardDao = new CardDao_Impl(this);
        }
        return _cardDao;
      }
    }
  }
}
