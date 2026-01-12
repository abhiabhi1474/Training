import java.util.logging.Level;
import java.util.logging.Logger;

public class File {
    static Logger logger = Logger.getLogger(File.class.getName());

    public static void main(String[] args) {
        try {
            int a = 5;
            int b = 0;
            int c = a / b;
            System.out.println(c + " is the result");
        } catch (Exception e) {
            logger.log(Level.WARNING,"Division by 0 is not possible",e);
        } finally {
            System.out.println("Try division with other number");
        }
    }

}


