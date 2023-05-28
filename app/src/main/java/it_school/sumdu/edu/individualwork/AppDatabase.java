package it_school.sumdu.edu.individualwork;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = { Tourism.class }, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TourismDao tourismDao();
    private static final String DATABASE_NAME = "tourism_database";
    private static volatile AppDatabase instance;

    static AppDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(instance).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        public final TourismDao fDao;

        PopulateDbAsync(AppDatabase db) {
            fDao = db.tourismDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            fDao.clearDB();
            fDao.insertTourism(new Tourism(
                    1,
                    "Turkey",
                    "Istanbul",
                    "Hotel Sapphire",
                    "You're eligible for a Genius discount at Hotel Sapphire! To save at this property, all you have to do is sign in.\n" +
                            "\n" +
                            "Sapphire is a classic Ottoman-style hotel located 500 meters from the Topkapi Palace in Istanbul’s Sultanahmet. It features free WiFi access. Sultanahmet, which houses many historic landmarks, is within a walking distance.\n" +
                            "\n" +
                            "Rooms at Hotel Sapphire combine wooden furnishings and damask fabrics with modern satellite TV channels and air conditioning. Each room includes en suite bathrooms with bathtub and hairdryer.\n" +
                            "\n" +
                            "Each morning, a rich breakfast buffet is offered with a wide selection of ingredients. Traditional Turkish cuisine is served at the restaurant of the hotel and room service is also available. The street hotel is located on a street closed to traffic and you can sit outside and relax with a cold drink.\n",
                    4270.0,
                    8.7,
                    "02.06.2023",
                    "09.06.2023"
            ));
            fDao.insertTourism(new Tourism(
                    2,
                    "Turkey",
                    "Antalya",
                    "Crowne Plaza",
                    "Set along the famous Konyaalti Beach, Crowne Plaza Antalya offers luxurious 5-star accommodation and open views of the Mediterranean Sea. Renovated in 2020, the hotel has indoor and outdoor pools, an extensive spa and free WiFi.\n" +
                            "In the rooms, guests can enjoy the interactive flat-screen TV or admire the sea view from the balcony. A seating area and a spa bath are provided in some of Crowne Plaza’s lavish rooms.\n" +
                            "Crowne Plaza Antalya has 3 restaurants that serve a buffet breakfast and Turkish and international cuisine. Meals can be savoured inside or on the terrace.",
                    4704.0,
                    8.0,
                    "29.05.2023",
                    "05.06.2023"
            ));
            fDao.insertTourism(new Tourism(
                    3,
                    "Ukraine",
                    "Myrhorod",
                    "Espresso",
                    "Espresso is offering accommodation in Myrhorod. The hotel offers both free WiFi and free private parking.\n" +
                            "Complete with a private bathroom fitted with free toiletries, all guest rooms at the hotel have a flat-screen TV and air conditioning, and selected rooms also offer a balcony. All rooms will provide guests with a wardrobe and a kettle.",
                    1900.0,
                    9.3,
                    "23.06.2023",
                    "27.06.2023"
            ));
            return null;
        }
    }
}