import java.lang.annotation.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Order {
    static Logger logger = Logger.getLogger(Order.class.getName());
    static class PaymentException extends Exception{
        public PaymentException(String message){
            System.out.println(message);
        }
    }

    abstract static class PaymentProcess<T>{
        public void pay(T payment) {
            try{
                validate(payment);
                process(payment);
                System.out.println("Payment Success");
            } catch (PaymentException e){
                System.out.println("Payment Failed " + e.getMessage());
                logger.log(Level.WARNING,"",e);

            }
        }

        protected abstract void validate(T payment) throws PaymentException;
        protected abstract void process(T payment) throws PaymentException;


    }
    static class UpiPayment{
        String upiId;
        double amount;

        UpiPayment(String upiId,double amount){
            this.upiId = upiId;
            this.amount = amount;
        }
    }
    static class CardPayment{
        String cardNumber;
        double amount;

        CardPayment(String cardNumber,double amount){
            this.cardNumber = cardNumber;
            this.amount = amount;
        }
    }
    @PaymentAudit(type = "CARD")
    static class CardPaymentProcess extends PaymentProcess<CardPayment>{

        @Override
        protected void validate(CardPayment payment) throws PaymentException {
            if (payment.cardNumber.length() != 16){
                throw new PaymentException("Invalid card number");
            }
        }

        @Override
        protected void process(CardPayment payment){
            System.out.println("Processing card payment of " + payment.amount);
        }
    }
    @PaymentAudit(type = "UPI")
    static class UpiPaymentProcess extends PaymentProcess<UpiPayment>{

        @Override
        protected void validate(UpiPayment payment) throws PaymentException {
            if (!payment.upiId.contains("@")){
                throw new PaymentException("Invalid UPI ID");
            }
        }

        @Override
        protected void process(UpiPayment payment){
            System.out.println("Processing UPI payment of " + payment.amount);
        }
    }
    public static void main(String[] args){
        PaymentProcess<UpiPayment> upiprocess = new UpiPaymentProcess();
        UpiPayment upi = new UpiPayment("12324@upi", 200.00);
        upiprocess.pay(upi);

        PaymentProcess<CardPayment> cardprocess = new CardPaymentProcess();
        CardPayment card = new CardPayment("478513698412554", 2000.00);
        cardprocess.pay(card);

        oldMethod();


    }
    @Deprecated
    static void oldMethod(){
        System.out.println("This method is outdated.");
    }

}
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface PaymentAudit {
    String type();
}
