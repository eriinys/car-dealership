public class SalesContract extends Contract {
    private final double salesTaxAmt;
    private final double recordingFee;
    private double processingFee;
    private boolean isfinanced;

    public SalesContract(String date, String customerName, String email, Vehicle vehicleSold, boolean isfinanced) {
        super(date, customerName, email, vehicleSold);
        this.salesTaxAmt = 0.05;
        this.recordingFee = 100;
        this.isfinanced = isfinanced;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = vehicleSold.getPrice();
        if (vehiclePrice < 10000) {
            processingFee = 295;
        } else {
            processingFee = 495;
        }
        totalPrice = (vehiclePrice * salesTaxAmt) + vehiclePrice + recordingFee + processingFee;
        return totalPrice;
    }

    @Override
    public double getMonthlyPay() {
        double loanAnnual;
        double monthlyRate;
        double vehiclePrice = vehicleSold.getPrice();
        double p = vehiclePrice + processingFee + recordingFee + (vehiclePrice * salesTaxAmt);
        if (isfinanced) {
            if (vehiclePrice >= 10000) {
                loanAnnual = 0.0425;
                monthlyRate = loanAnnual / 12;
                monthlyPay = (monthlyRate * p) / (1 - Math.pow(1 + monthlyRate, -48));
                //monthly Payment = (r × P) ÷ (1 − (1 + r)^−n)
            } else {
                loanAnnual = 0.0525;
                monthlyRate = loanAnnual / 12;
                monthlyPay = (monthlyRate * p) / (1 - Math.pow(1 + monthlyRate, -12));
            }
        } else {
            monthlyPay = 0;
        }
        return monthlyPay;
    }
}