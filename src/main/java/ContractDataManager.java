import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    private Contract contract;

    public void saveContract(Contract contract){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/contracts.csv", true))){
            if (contract instanceof SalesContract){
                bw.write("SALE|");
                bw.write(contract.toString());
                bw.write(contract.toFileString());
                bw.newLine();
            }
            if (contract instanceof LeaseContract) {
                bw.write("LEASE|");
                bw.write(contract.toString());
                bw.write(contract.toFileString());
                bw.newLine();
            }

            //DATE|CUSTOMER_NAME|CUSTOMER_EMAIL|VIN|YEAR|MAKE|MODEL|VEHICLE_TYPE|COLOR|ODOMETER|VEHICLE_PRICE|
            //[contract-specific-fields]|TOTAL_PRICE|MONTHLY_PAYMENT

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
