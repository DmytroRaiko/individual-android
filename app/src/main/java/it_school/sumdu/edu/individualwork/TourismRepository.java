package it_school.sumdu.edu.individualwork;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TourismRepository {
    private TourismDao tourismDao;

    TourismRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        tourismDao = db.tourismDao();
    }

    public LiveData<List<Tourism>> getAllTourism(){
        return tourismDao.getAllEntities();
    }
    public LiveData<List<Tourism>> getAllTourismByCountry(String country, String searchPart) {
        return tourismDao.getAllTourismByCountry(country, "%" + searchPart + "%");
    }
    public void insert(Tourism tourism){
        new InsertAsyncTask(tourismDao).execute(tourism);
    }
    public LiveData<List<Tourism>> search(String searchValue) {
        return tourismDao.search("%" + searchValue + "%");
    }

    public LiveData<List<String>> getCountries(String part) {
        return tourismDao.getAllEntitiesUniqueCountries("%" + part + "%");
    }

    public void delete(Tourism tourism) {
        tourismDao.deleteTourism(tourism);
    }



    private static class InsertAsyncTask extends AsyncTask<Tourism, Void, Void> {

        private TourismDao mAsyncTaskDao;

        InsertAsyncTask(TourismDao tourismDao) {
            mAsyncTaskDao = tourismDao;
        }

        @Override
        protected Void doInBackground(final Tourism... tourisms) {
            mAsyncTaskDao.insertTourism(tourisms[0]);
            return null;
        }
    }
}
