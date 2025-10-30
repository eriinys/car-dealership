import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalesContractTest {
    @Test
    public void getTotalPrice() {
        Vehicle vehicle = new Vehicle(44901, 2012, "Honda", "Civic", "SUV", "Gray", 103221, 6995.00);
        SalesContract salesContract = new SalesContract("10/20/2025", "Dave", "dave@gmail.com", vehicle, false);
        double priceExepcted = 7739.75;
        double actualPrice = salesContract.getTotalPrice();
        assertEquals(priceExepcted, actualPrice);
    }

}