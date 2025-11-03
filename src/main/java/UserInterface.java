import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Dealership dealership;
    ContractDataManager contract = new ContractDataManager();

    public void display(){
        init(); //loads the dealership
        boolean main = true;
        while(main){
            System.out.println("""
                \nChoose from following options:
                A) Add Vehicle
                R) Remove Vehicle
                L) List All Vehicle
                S) Search
                C) Purchase/Lease Vehicle
                E) Exit
                """);
            String choice = scanner.nextLine();
            switch (choice.toUpperCase()) {
                case "A" -> processAddVehicleRequest();
                case "R" -> processRemoveVehicleRequest();
                case "L" -> processGetAllVehicleRequest();
                case "S" -> {
                    System.out.println("""
                            Please choose from following search options:
                            1) Search by price range
                            2) Search by make/model
                            3) Search by year range
                            4) Search by color
                            5) Search by mileage range
                            6) Search by type
                            """);
                    String choice2 = scanner.nextLine();
                    boolean search = true;
                    while(search){
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
                case "C" -> {
                    System.out.println("""
                            Please choose from following options:
                            P) Purchase Vehicle
                            L) Lease Vehicle
                            """);
                    String choice3 = scanner.nextLine();
                    boolean contract = true;
                    while(contract){
                        switch (choice3.toUpperCase()){
                            case "P" -> processSale();
                            case "L" -> processLease();
                        }
                        contract = false;
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

        System.out.println("VIN#:");
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
        System.out.println("Enter the VIN# of vehicle you would like to remove:");
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

    public void processSale(){
        while(true) {
            System.out.println("Enter the VIN# of vehicle you'd like to purchase:");
            int vehicleVIN = Integer.parseInt(scanner.nextLine());

            Vehicle vehicle = dealership.getVehiclesByVin(vehicleVIN);

            if (vehicle == null) {
                System.out.println("No match found. Please try again.");
                continue; //goes back to first prompt
            }

            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            System.out.println("Please enter your full name:");
            String name = scanner.nextLine();
            System.out.println("Please enter your email address:");
            String email = scanner.nextLine();
            System.out.println("Would you like to have your purchase financed today?");
            String financeOption = scanner.nextLine();

            boolean isFinanced = false;
            if (financeOption.equalsIgnoreCase("yes")) {
                isFinanced = true;
            } else if
            (financeOption.equalsIgnoreCase("no")) {
                isFinanced = false;
            } else {
                System.out.println("Please answer either yes or no");
                continue;
            }

            SalesContract sales = new SalesContract(today.format(formatter), name, email, vehicle, isFinanced);

            contract.saveContract(sales);
            System.out.println("Sales contract successfully created!");

//            if (dealership.getVehiclesByVin(vehicleVIN).equals(vehicle)){
//                dealership.removeVehicle(vehicle);
//            }
            dealership.removeVehicle(vehicle); //only removes the object from the arraylist not the text file

            DealershipFileManager fileManager = new DealershipFileManager();
            fileManager.saveDealership(dealership);
            //make sure to call the saveDealership method from DealershipFileManager to overwrite/save the updated arraylist into text file
            break;
        }
    }

    public void processLease(){
        while(true) {
            System.out.println("Enter the VIN# of vehicle you'd like to lease:");
            int vehicleVIN = Integer.parseInt(scanner.nextLine());

            Vehicle vehicle = dealership.getVehiclesByVin(vehicleVIN);

            if (vehicle == null) {
                System.out.println("No match found. Please try again.");
                continue; //goes back to first prompt
            }

            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            if (today.getYear() - vehicle.getYear() > 3) {
                System.out.println("Vehicles over 3 years old cannot be leased.");
                continue;
            }

            System.out.println("Please enter your full name:");
            String name = scanner.nextLine();
            System.out.println("Please enter your email address:");
            String email = scanner.nextLine();
            LeaseContract lease = new LeaseContract(today.format(formatter), name, email, vehicle);

            contract.saveContract(lease);
            System.out.println("Lease contract successfully created!");
            break;
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
