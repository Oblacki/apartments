

import java.util.ArrayList;
import java.util.List;

//
public class Database {

    private static List<Customer> customers = new ArrayList<>();
    private static List<Apartment> apartments = new ArrayList<>();

    public static List<Customer> getCustomers() {
        return customers;
    }
    public static List<Apartment> getApartments() { return apartments; }


    //apartments
    public static void deleteApartment(String apartmentId) {
        for (Apartment apartment : apartments) {
            if (apartment.getId().equals(apartmentId)) {
                apartments.remove(apartment);
                break;
            }
        }
    }

    public static Apartment getApartment(String apartmentId) {
        for (Apartment apartment : apartments) {
            if (apartment.getId().equals(apartmentId))
                return apartment;
        }
        return null;
    }

    public static void addApartment(Apartment apartment) {
        apartments.add(apartment);
    }

    public static List<Apartment> getApartmentByCustomerId(String customerId) {
        List<Apartment> filteredApartments = new ArrayList<Apartment>();

        for (Apartment apartment : apartments) {
            if (apartment.getCustomerId().equals(customerId)) {
                System.out.println(apartment.toString());
                filteredApartments.add(apartment);
            }
        }
        return filteredApartments;
    }

    public static List<Apartment> getApartmentsByGuestId(String guestId) {
        List<Apartment> filteredApartments = new ArrayList<Apartment>();

        for (Apartment apartment : apartments) {
            List<String> guestIds = apartment.getGuestIds();
            if(guestIds.contains(guestId)){
                System.out.println(apartment.toString());
                apartment.setCustomerId("");
                filteredApartments.add(apartment);
            }
        }
        return filteredApartments;
    }

    public static List<Apartment> getApartmentsByPriceFilter(double minPrice, double maxPrice) {
        List<Apartment> filteredApartments = new ArrayList<Apartment>();

        for (Apartment apartment : apartments) {
            if(apartment.getPricePerNight() >= minPrice && apartment.getPricePerNight() <= maxPrice){
                System.out.println(apartment.toString());
                filteredApartments.add(apartment);
            }
        }
        return filteredApartments;
    }
}
