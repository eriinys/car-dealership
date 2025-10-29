import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {
    private Dealership dealership;

    public DealershipFileManager(){
        this.dealership = dealership;
    }

    public Dealership getDealership(){
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/inventory.csv"))){

            String header = br.readLine();
            if(header != null){
                String[] dealer = header.split("\\|");
                String name = dealer[0];
                String address = dealer[1];
                String phone = dealer[2];
                Dealership dealership = new Dealership(name, address, phone);
            }

           String list;
           while((list = br.readLine()) != null) {
               String[] parts = list.split("\\|");
               int vin = Integer.parseInt(parts[0]);
               int year = Integer.parseInt(parts[1]);
               String make = parts[2];
               String model = parts[3];
               String vehicleType = parts[4];
               String color = parts[5];
               int odometer = Integer.parseInt(parts[6]);
               double price = Double.parseDouble(parts[7]);

               Vehicle vehicle = new Vehicle(vin, year, make,model,vehicleType, color, odometer, price);
               dealership.inventory.add(vehicle); //adds object to collection aka ArrayList of Vehicles in Dealership
           }

        } catch (IOException e) {
            System.err.println("Error: " +e.getMessage());
            throw new RuntimeException(e);
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership){

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/inventory.csv"))) {
                bw.write("D & B Used Cars|111 Old Benbrook Rd|817-555-555");
                bw.newLine();

                for (Vehicle vehicle : dealership.inventory){
                    bw.write(vehicle.toString());
                    bw.newLine();
                }

            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
                throw new RuntimeException(e);
            }
    }
}
