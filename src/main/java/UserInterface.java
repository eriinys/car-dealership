import java.util.*;
import java.io.*;
public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Dealership dealership;

    public void display(){
        init(); //loads the dealership
        boolean main = true;
        while(main) {
            System.out.println("""
                \nChoose from following options:
                A) Add Vehicle
                B) Remove Vehicle
                C) List All Vehicle
                D) Search
                E) Exit
                """);
            String choice = scanner.nextLine();
            switch (choice.toUpperCase()) {
                case "A" -> processAddVehicleRequest();
                case "B" -> processRemoveVehicleRequest();
                case "C" -> processGetAllVehicleRequest();
                case "D" -> {
                    System.out.println("""
                            Please choose from following search option:
                            1) Search by price range
                            2) Search by make/model
                            3) Search by year range
                            4) Search by color
                            5) Search by mileage range
                            6) Search by type
                            """);
                    String choice2 = scanner.nextLine();
                    boolean search = true;
                    while (search) {
                        switch (choice2) {
                            case "1" -> processGetByPriceRequest();
                            case "2" -> processGetByMakeModelRequest();
                            case "3" -> processGetByYearRequest();
                            case "4" -> processGetByColorRequest();
                            case "5" -> processGetByMileageRequest();
                            case "6" -> proceessGetByVehicleTypeRequest();
                        }
                        search = false;
                    }
                }

                case "E" -> {
                    System.out.println("Exiting program...");
                    main = false;
                }
            }
        }
    }

    public void processGetByPriceRequest(){
        System.out.println("Enter the minium price:");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter the maximum price:");
        double max = Double.parseDouble(scanner.nextLine());

        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest(){
        System.out.println("Enter the make or press enter:");
        String make = scanner.nextLine();
        System.out.println("Enter the model or press enter:");
        String model = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    public void processGetByYearRequest(){
        System.out.println("Enter the minimum year:");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter maximum year:");
        int max = Integer.parseInt(scanner.nextLine());

        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    public void processGetByColorRequest(){
        System.out.println("Enter the color:");
        String color = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest(){
        System.out.println("Enter the minimum mileage:");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the maximum mileage:");
        int max = Integer.parseInt(scanner.nextLine());

        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    public void proceessGetByVehicleTypeRequest(){
        System.out.println("Enter the vehicle type:");
        String type = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByTpe(type));
    }

    public void processGetAllVehicleRequest(){
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest(){
        //int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price
        System.out.println("Enter the following information of the vehicle you want to add:");

        System.out.println("Vin#:");
        int vin = Integer.parseInt(scanner.nextLine());

        System.out.println("Year:");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.println("Make:");
        String make = scanner.nextLine();

        System.out.println("Model:");
        String model = scanner.nextLine();

        System.out.println("Vehicle type (i.e. SUV):");
        String vehicleType = scanner.nextLine();

        System.out.println("Color:");
        String color = scanner.nextLine();

        System.out.println("Mileage:");
        int odometer = Integer.parseInt(scanner.nextLine());

        System.out.println("Price:");
        double price = Double.parseDouble(scanner.nextLine());

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle); //adds to arraylist
        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership); //overwrites text file with updated arraylist

        System.out.println("Vehicle successfully added to inventory. " +
                "Thank you!\n");
    }

    public void processRemoveVehicleRequest(){
        System.out.println("Enter the vin# of vehicle you would like to remove:");
        int vin = Integer.parseInt(scanner.nextLine());

        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        boolean found = false;
        for (int i = 0; i < vehicles.size(); i++){
            if (vehicles.get(i).getVin() == vin) {
                dealership.removeVehicle(vehicles.get(i)); //removes from arraylist
                DealershipFileManager fileManager = new DealershipFileManager();
                fileManager.saveDealership(dealership);//overwrites text file with updated arraylist
                found = true;
                System.out.println("Vehicle successfully removed from inventory.\n");
            }
        }
        if (!found){
            System.out.println("No vehicle match found to remove from the list.\n");
        }
    }

    public void init(){
        DealershipFileManager fileManager = new DealershipFileManager();
       this.dealership = fileManager.getDealership();
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles){
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("Match not found.");
        }
        for (Vehicle vehicle : vehicles){
            System.out.println(vehicle);
        }
    }
}
