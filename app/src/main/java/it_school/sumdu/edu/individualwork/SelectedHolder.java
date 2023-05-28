package it_school.sumdu.edu.individualwork;

public class SelectedHolder {
    private static String country;
    private static Tourism tourism;

    public static String getSelectedCountry() {
        return country;
    }

    public static void setSelectedCountry(String countryName) {
        country = countryName;
    }

    public static Tourism getSelectedTourism() {
        return tourism;
    }

    public static void setSelectedTourism(Tourism tourismEntity) {
        tourism = tourismEntity;
    }
}