import backEnd.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class ShoppingCart {
    private static void print(Wallet wallet, Pocket pocket) throws Exception {
        System.out.println("Your current balance is: " + Wallet.getBalance() + " credits.");
        System.out.println(Store.asString());
        System.out.println("Your current pocket is:\n" + pocket.getPocket());
    }

    private static String scan(Scanner scanner) throws Exception {
        System.out.print("What do you want to buy? (type quit to stop) ");
        return scanner.nextLine();
    }


    public static void main(String[] args) throws Exception {
        Wallet wallet = new Wallet();
        Pocket pocket = new Pocket();
        Scanner scanner = new Scanner(System.in);

        print(wallet, pocket);
        String product = scan(scanner);
        
        while(!product.equals("quit")) {
            TimeUnit.SECONDS.sleep(10);
            if (Wallet.safeWithdraw(Store.getProductPrice(product))) {
                Wallet.setBalance(Wallet.getBalance() - Store.getProductPrice(product));
                Pocket.addProduct(product);
                System.out.println("Your balance : " + Wallet.getBalance() + " credits") ;
            }
            else {
                System.out.println("Cannot buy since Wallet balance is smaller than product price");
                break ; 
            }
            // Just to print everything again...
            print(wallet, pocket);
            product = scan(scanner);
            
        }
    }
}