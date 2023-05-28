package it_school.sumdu.edu.individualwork;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TourismViewModel extends AndroidViewModel {

    private TourismRepository repository;

    public TourismViewModel(@NonNull Application application) {
        super(application);
        repository = new TourismRepository(application);
    }

    public void insert(Tourism tourism) {
        repository.insert(tourism);
    }

    public LiveData<List<Tourism>> search(String searchValue) {
        return repository.search(searchValue);
    }

    public LiveData<List<Tourism>> getAllTourism() {
        return repository.getAllTourism();
    }

    public LiveData<List<Tourism>> getAllTourismByCountry(String country, String part) {
        return repository.getAllTourismByCountry(country, part);
    }
    
    public LiveData<List<String>> getCountries(String part) {
        return repository.getCountries(part);
    }

    public void delete(Tourism tourism) {
        repository.delete(tourism);
    }
}