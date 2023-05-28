package it_school.sumdu.edu.individualwork;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TourismDao_Impl implements TourismDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfTourism;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfTourism;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfTourism;

  private final SharedSQLiteStatement __preparedStmtOfClearDB;

  public TourismDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTourism = new EntityInsertionAdapter<Tourism>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `tourism`(`id`,`country`,`city`,`title`,`description`,`price`,`rating`,`dateStart`,`dateEnd`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Tourism value) {
        stmt.bindLong(1, value.getId());
        if (value.getCountry() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCountry());
        }
        if (value.getCity() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCity());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
        if (value.getPrice() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindDouble(6, value.getPrice());
        }
        if (value.getRating() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindDouble(7, value.getRating());
        }
        if (value.getDateStart() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getDateStart());
        }
        if (value.getDateEnd() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getDateEnd());
        }
      }
    };
    this.__deletionAdapterOfTourism = new EntityDeletionOrUpdateAdapter<Tourism>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `tourism` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Tourism value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfTourism = new EntityDeletionOrUpdateAdapter<Tourism>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `tourism` SET `id` = ?,`country` = ?,`city` = ?,`title` = ?,`description` = ?,`price` = ?,`rating` = ?,`dateStart` = ?,`dateEnd` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Tourism value) {
        stmt.bindLong(1, value.getId());
        if (value.getCountry() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCountry());
        }
        if (value.getCity() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCity());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
        if (value.getPrice() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindDouble(6, value.getPrice());
        }
        if (value.getRating() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindDouble(7, value.getRating());
        }
        if (value.getDateStart() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getDateStart());
        }
        if (value.getDateEnd() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getDateEnd());
        }
        stmt.bindLong(10, value.getId());
      }
    };
    this.__preparedStmtOfClearDB = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE tourism SET city='Sarajevo' WHERE title LIKE '%Hotel Hills%'";
        return _query;
      }
    };
  }

  @Override
  public void insertTourism(final Tourism tourism) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTourism.insert(tourism);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTourism(final Tourism tourism) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTourism.handle(tourism);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateTourism(final Tourism tourism) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTourism.handle(tourism);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clearDB() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearDB.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClearDB.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Tourism>> getAllEntities() {
    final String _sql = "SELECT * FROM tourism";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"tourism"}, false, new Callable<List<Tourism>>() {
      @Override
      public List<Tourism> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfDateStart = CursorUtil.getColumnIndexOrThrow(_cursor, "dateStart");
          final int _cursorIndexOfDateEnd = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEnd");
          final List<Tourism> _result = new ArrayList<Tourism>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Tourism _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpCountry;
            _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final Double _tmpPrice;
            if (_cursor.isNull(_cursorIndexOfPrice)) {
              _tmpPrice = null;
            } else {
              _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            }
            final Double _tmpRating;
            if (_cursor.isNull(_cursorIndexOfRating)) {
              _tmpRating = null;
            } else {
              _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            }
            final String _tmpDateStart;
            _tmpDateStart = _cursor.getString(_cursorIndexOfDateStart);
            final String _tmpDateEnd;
            _tmpDateEnd = _cursor.getString(_cursorIndexOfDateEnd);
            _item = new Tourism(_tmpId,_tmpCountry,_tmpCity,_tmpTitle,_tmpDescription,_tmpPrice,_tmpRating,_tmpDateStart,_tmpDateEnd);
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
  public LiveData<List<String>> getAllEntitiesUniqueCountries(final String partString) {
    final String _sql = "SELECT DISTINCT country FROM tourism WHERE country LIKE ? ORDER BY country ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (partString == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, partString);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"tourism"}, false, new Callable<List<String>>() {
      @Override
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
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
  public LiveData<List<Tourism>> getAllTourismByCountry(final String country,
      final String searchPart) {
    final String _sql = "SELECT * FROM tourism WHERE country LIKE ? AND (title LIKE ? OR description LIKE ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (country == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, country);
    }
    _argIndex = 2;
    if (searchPart == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, searchPart);
    }
    _argIndex = 3;
    if (searchPart == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, searchPart);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"tourism"}, false, new Callable<List<Tourism>>() {
      @Override
      public List<Tourism> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfDateStart = CursorUtil.getColumnIndexOrThrow(_cursor, "dateStart");
          final int _cursorIndexOfDateEnd = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEnd");
          final List<Tourism> _result = new ArrayList<Tourism>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Tourism _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpCountry;
            _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final Double _tmpPrice;
            if (_cursor.isNull(_cursorIndexOfPrice)) {
              _tmpPrice = null;
            } else {
              _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            }
            final Double _tmpRating;
            if (_cursor.isNull(_cursorIndexOfRating)) {
              _tmpRating = null;
            } else {
              _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            }
            final String _tmpDateStart;
            _tmpDateStart = _cursor.getString(_cursorIndexOfDateStart);
            final String _tmpDateEnd;
            _tmpDateEnd = _cursor.getString(_cursorIndexOfDateEnd);
            _item = new Tourism(_tmpId,_tmpCountry,_tmpCity,_tmpTitle,_tmpDescription,_tmpPrice,_tmpRating,_tmpDateStart,_tmpDateEnd);
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
  public LiveData<List<Tourism>> search(final String s) {
    final String _sql = "SELECT * FROM tourism WHERE title LIKE ? OR description LIKE ? OR country LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (s == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, s);
    }
    _argIndex = 2;
    if (s == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, s);
    }
    _argIndex = 3;
    if (s == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, s);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"tourism"}, false, new Callable<List<Tourism>>() {
      @Override
      public List<Tourism> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfDateStart = CursorUtil.getColumnIndexOrThrow(_cursor, "dateStart");
          final int _cursorIndexOfDateEnd = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEnd");
          final List<Tourism> _result = new ArrayList<Tourism>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Tourism _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpCountry;
            _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final Double _tmpPrice;
            if (_cursor.isNull(_cursorIndexOfPrice)) {
              _tmpPrice = null;
            } else {
              _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            }
            final Double _tmpRating;
            if (_cursor.isNull(_cursorIndexOfRating)) {
              _tmpRating = null;
            } else {
              _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            }
            final String _tmpDateStart;
            _tmpDateStart = _cursor.getString(_cursorIndexOfDateStart);
            final String _tmpDateEnd;
            _tmpDateEnd = _cursor.getString(_cursorIndexOfDateEnd);
            _item = new Tourism(_tmpId,_tmpCountry,_tmpCity,_tmpTitle,_tmpDescription,_tmpPrice,_tmpRating,_tmpDateStart,_tmpDateEnd);
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
}
