import java.util.List;

class Order {
    private final int orderId;
    private final String customerName;
    private final double amount;
    private final boolean paymentSuccess;

    public Order(int orderId,String customerName, double amount,boolean paymentSuccess){
        this.orderId = orderId;
        this.amount = amount;
        this.customerName = customerName;
        this.paymentSuccess = paymentSuccess;
    }
    public double getAmount(){
        return amount;
    }
    public boolean isPaymentSuccess(){
        return paymentSuccess;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String toString(){
        return customerName + " - Rs " +amount ;
    }
}
public class Festi {
    public static void main(String[] args){
        List<Order> orders  = List.of(
                new Order(1,"Akhil",650,true),
                new Order(2,"Rahul",120,false),
                new Order(3,"Neethu",540,true),
                new Order(4,"Anu",300,true),
                new Order(5,"Sreejith",800,true)

        );


        List<Order> successfulOrders =
                orders.stream()
                        .filter(Order::isPaymentSuccess)
                        .toList();

        System.out.println("Successfull Orders");
        successfulOrders.forEach(System.out::println);

        double totalRevenue =
                orders.stream()
                        .filter(Order::isPaymentSuccess)
                        .mapToDouble(Order::getAmount)
                        .sum();
        System.out.println("\nTotal Revenue : "+totalRevenue);

        List<Order> highValueOrders =
                orders.stream()
                        .filter(o ->o.getAmount()>=500)
                        .toList();
        System.out.println("\nHigh Value Orders: ");
        highValueOrders.forEach(System.out::println);

        List<Double> discountedAmounts =
                orders.stream()
                        .filter(Order::isPaymentSuccess)
                        .map(order -> order.getAmount() * 0.9)
                        .toList();
        System.out.println("\nDiscount Amounts:");
        discountedAmounts.forEach(System.out::println);

    }
}
