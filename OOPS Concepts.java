import java.io.*;
import java.util.Scanner;

public class Main{
    static class Order{
        private int orderId;
        private double amount;
        private String status;

        public Order(int orderId, double amount){
            this.orderId = orderId;
            this.amount = amount;

        }
        public int getOrderId() {
            return orderId;
        }
        public double get_Amount() {
            return amount;
        }
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }


        interface Payment{
            void pay(double amount);
        }

        static class Creditcard implements Payment {
            public void pay(double amount) {
                System.out.println("Payment of " + amount + " done using Credit Card");
            }
        }


        static class Upi implements Payment{
            public void pay(double amount){
                System.out.println("Payment of "+amount+ " done using UPI");
            }
        }
    }
    static abstract class OrderService{
        abstract void placeOrder(Order order,Order.Payment payment);
    }


    static class Orderconf extends OrderService{
        void placeOrder(Order order, Order.Payment payment){
            payment.pay(order.get_Amount());
            order.setStatus("CONFIRMED");

            System.out.println("Order Id: "+order.getOrderId()+" Status: "+order.getStatus());
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Order Id: ");
        int orderId = sc.nextInt();
        System.out.println("Enter the Amount: ");
        double amount = sc.nextDouble();

        Order order = new Order(orderId,amount);

        System.out.println("\nSelect Payment Method: ");
        System.out.println("1.Credit Card");
        System.out.println("2.UPI");
        System.out.println("Select Choice: ");
        int choice = sc.nextInt();

        Order.Payment payment = null;

        switch(choice){
            case 1:
                payment = new Order.Creditcard();
                break;

            case 2 :
                payment = new Order.Upi();
                break;

            default:
                System.out.println("Wrong choice");
                sc.close();
                return;
        }
        sc.close();

        OrderService orderService = new Orderconf();
        orderService.placeOrder(order, payment);
    }

}