public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String email, Vehicle vehicleSold) {
        super(date, customerName, email, vehicleSold);
        this.expectedEndingValue = vehicleSold.getPrice() * 0.5;
        this.leaseFee = vehicleSold.getPrice() * 0.07;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }
    public double getLeaseFee() {
        return leaseFee;
    }

    @Override
    public double getTotalPrice() {
        totalPrice = monthlyPay * 36;

        return totalPrice;
    }

    @Override
    public double getMonthlyPay() {
        double loanAnnual = 0.04;
        double monthlyRate = loanAnnual / 12;
        monthlyPay = ((vehicleSold.getPrice() - expectedEndingValue ) + leaseFee) / 36;
        return monthlyPay;
    }
}
