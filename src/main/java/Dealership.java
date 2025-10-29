import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        ArrayList<Vehicle> inventory = new ArrayList<>();
    }
//region getters/setters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    //endregion

    //region methods
    public void addVehicle(Vehicle vehicle){
        Vehicle add = new Vehicle(vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
        inventory.add(add);

    }

    public void removeVehicle(Vehicle vehicle){

    }

    public ArrayList<Vehicle> getVehiclesByPrice(int min, int max){
//        ArrayList<Vehicle> filtered = new ArrayList<>();
//        for (Vehicle vehicle : inventory){
//            if (vehicle.getPrice() > min && vehicle.getPrice() < max) {
//                filtered.add(vehicle);
//            } else {
//                System.out.println("Match not found.");
//            }
//        }
//        return filtered;
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model){
//        ArrayList<Vehicle> filtered = new ArrayList<>();
//        for (Vehicle vehicle : inventory){
//            if (vehicle.getMake().equalsIgnoreCase(make) || vehicle.getModel().equalsIgnoreCase(model)){
//                filtered.add(vehicle);
//            } else {
//                System.out.println("Match not found.");
//            }
//        }
//        return filtered;
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max){
//        ArrayList<Vehicle> filtered = new ArrayList<>();
//        for (Vehicle vehicle : inventory){
//            if (vehicle.getYear() > min && vehicle.getYear() < max){
//                filtered.add(vehicle);
//            } else {
//                System.out.println("Match not found.");
//            }
//        }
//        return  filtered;
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
//        ArrayList<Vehicle> filtered = new ArrayList<>();
//        for (Vehicle vehicle : inventory){
//            if (vehicle.getColor().equalsIgnoreCase(color)){
//                filtered.add(vehicle);
//            } else {
//                System.out.println("Match not found.");
//            }
//        }
//        return filtered;
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max){
//        ArrayList<Vehicle> filtered = new ArrayList<>();
//        for (Vehicle vehicle : inventory){
//            if (vehicle.getOdometer() > min && vehicle.getOdometer() < max){
//                filtered.add(vehicle);
//            } else {
//                System.out.println("Match not found.");
//            }
//        }
//        return filtered;
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByTpe(String type){
//        ArrayList<Vehicle> filtered = new ArrayList<>();
//        for (Vehicle vehicle : inventory){
//            if (vehicle.getVehicleType().equalsIgnoreCase(type)){
//                filtered.add(vehicle);
//            } else {
//                System.out.println("Match not found.");
//            }
//        }
//        return filtered;
        return null;
    }

    public ArrayList<Vehicle> getAllVehicles(){
        return inventory;
    }
}
