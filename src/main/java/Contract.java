public abstract class Contract {
    protected String date;
    protected String customerName;
    protected String email;
    protected Vehicle vehicleSold;
    protected double totalPrice;
    protected double monthlyPay;

    public Contract(String date, String customerName, String email, Vehicle vehicleSold) {
        this.date = date;
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
}

