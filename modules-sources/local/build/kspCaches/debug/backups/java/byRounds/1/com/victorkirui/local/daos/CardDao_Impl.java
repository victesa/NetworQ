package com.victorkirui.local.daos;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.victorkirui.local.entities.BusinessCard;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CardDao_Impl implements CardDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BusinessCard> __insertionAdapterOfBusinessCard;

  public CardDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBusinessCard = new EntityInsertionAdapter<BusinessCard>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `BusinessCard` (`id`,`firstName`,`lastName`,`emailAddress`,`phoneNumber`,`companyName`,`jobTitle`,`website`,`location`,`cardImage`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BusinessCard entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getFirstName());
        statement.bindString(3, entity.getLastName());
        statement.bindString(4, entity.getEmailAddress());
        statement.bindString(5, entity.getPhoneNumber());
        statement.bindString(6, entity.getCompanyName());
        statement.bindString(7, entity.getJobTitle());
        statement.bindString(8, entity.getWebsite());
        statement.bindString(9, entity.getLocation());
        statement.bindString(10, entity.getCardImage());
      }
    };
  }

  @Override
  public void insert(final BusinessCard businessCard) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfBusinessCard.insert(businessCard);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Flow<List<BusinessCard>> getAllBusinessCards() {
    final String _sql = "SELECT * FROM BusinessCard";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"BusinessCard"}, new Callable<List<BusinessCard>>() {
      @Override
      @NonNull
      public List<BusinessCard> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "firstName");
          final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "lastName");
          final int _cursorIndexOfEmailAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "emailAddress");
          final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumber");
          final int _cursorIndexOfCompanyName = CursorUtil.getColumnIndexOrThrow(_cursor, "companyName");
          final int _cursorIndexOfJobTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "jobTitle");
          final int _cursorIndexOfWebsite = CursorUtil.getColumnIndexOrThrow(_cursor, "website");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfCardImage = CursorUtil.getColumnIndexOrThrow(_cursor, "cardImage");
          final List<BusinessCard> _result = new ArrayList<BusinessCard>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BusinessCard _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpFirstName;
            _tmpFirstName = _cursor.getString(_cursorIndexOfFirstName);
            final String _tmpLastName;
            _tmpLastName = _cursor.getString(_cursorIndexOfLastName);
            final String _tmpEmailAddress;
            _tmpEmailAddress = _cursor.getString(_cursorIndexOfEmailAddress);
            final String _tmpPhoneNumber;
            _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
            final String _tmpCompanyName;
            _tmpCompanyName = _cursor.getString(_cursorIndexOfCompanyName);
            final String _tmpJobTitle;
            _tmpJobTitle = _cursor.getString(_cursorIndexOfJobTitle);
            final String _tmpWebsite;
            _tmpWebsite = _cursor.getString(_cursorIndexOfWebsite);
            final String _tmpLocation;
            _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            final String _tmpCardImage;
            _tmpCardImage = _cursor.getString(_cursorIndexOfCardImage);
            _item = new BusinessCard(_tmpId,_tmpFirstName,_tmpLastName,_tmpEmailAddress,_tmpPhoneNumber,_tmpCompanyName,_tmpJobTitle,_tmpWebsite,_tmpLocation,_tmpCardImage);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object deleteByCardImagePath(final List<String> cardImagePath,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("DELETE FROM BusinessCard WHERE cardImage IN (");
        final int _inputSize = cardImagePath.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
        int _argIndex = 1;
        for (String _item : cardImagePath) {
          _stmt.bindString(_argIndex, _item);
          _argIndex++;
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
