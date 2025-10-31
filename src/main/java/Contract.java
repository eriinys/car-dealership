import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Contract {
    protected String date;
    protected String customerName;
    protected String email;
    protected Vehicle vehicleSold;
    protected double totalPrice;
    protected double monthlyPay;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    LocalDate today = LocalDate.now();

    public Contract(String date, String customerName, String email, Vehicle vehicleSold) {
        this.date = today.format(formatter);
        this.customerName = customerName;
        this.email = email;
        this.vehicleSold = vehicleSold;
    }

    //region getters/setters
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }
    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }
    //endregion

    //abstract methods
    public abstract double getTotalPrice();
    public abstract double getMonthlyPay();

    public String toString(){
        return String.format("%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|", date, customerName, email, vehicleSold.getVin(), vehicleSold.getYear(), vehicleSold.getMake(), vehicleSold.getModel(), vehicleSold.getVehicleType(),vehicleSold.getColor(),vehicleSold.getOdometer(),vehicleSold.getPrice());

        //DATE|CUSTOMER_NAME|CUSTOMER_EMAIL|VIN|Y
        //EAR|MAKE|MODEL|VEHICLE_TYPE|COLOR|ODOMETER|VEHICLE_PRI
        //CE|[contract-specific-fields]|TOTAL_PRICE|MONTHLY_PAYMENT
    }
}

