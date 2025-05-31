package com.victorkirui.local.daos;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.victorkirui.local.entities.EventEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class EventDao_Impl implements EventDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EventEntity> __insertionAdapterOfEventEntity;

  public EventDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEventEntity = new EntityInsertionAdapter<EventEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `EventEntity` (`primaryKey`,`eventName`,`tags`,`location`,`date`,`description`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EventEntity entity) {
        statement.bindLong(1, entity.getPrimaryKey());
        statement.bindString(2, entity.getEventName());
        statement.bindString(3, entity.getTags());
        if (entity.getLocation() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getLocation());
        }
        statement.bindString(5, entity.getDate());
        statement.bindString(6, entity.getDescription());
      }
    };
  }

  @Override
  public void insertEvent(final EventEntity eventEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEventEntity.insert(eventEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
