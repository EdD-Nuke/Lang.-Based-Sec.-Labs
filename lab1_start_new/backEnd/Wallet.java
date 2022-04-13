package backEnd;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;
///NEW IMPORTS
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class Wallet {
    /**
     * The RandomAccessFile of the wallet file
     */  
    private static RandomAccessFile file;
    private static FileLock lock ;
    private static FileChannel fileChannel;

    /**
     * Creates a Wallet object
     *
     * A Wallet object interfaces with the wallet RandomAccessFile
     */
    public Wallet () throws Exception {
	    file = new RandomAccessFile(new File("backEnd/wallet.txt"), "rw");
        fileChannel = file.getChannel();
        lock = null;
    }
  
     /**
     * Gets the wallet balance. 
     *
     * @return                   The content of the wallet file as an integer
     * @throws Exception
     */
    public static int getBalance() throws Exception {
        file.seek(0);
        int r = Integer.parseInt(file.readLine());
        return r;
        }

    /**
     * Sets a new balance in the wallet
     *
     * @param  newBalance          new balance to write in the wallet
     */
    public static void setBalance(int newBalance) throws Exception {
        //if (safeWithdraw(getBalance() - newBalance)) { ///Declare a new value for safeWithdraw
        TimeUnit.SECONDS.sleep(10);
        file.setLength(0);
	    String str = Integer.valueOf(newBalance).toString()+'\n';
        file.writeBytes(str);
        lock.release();    
        //}
        //else {
            //lock.release();
            //throw new IllegalArgumentException("Unable to withdraw since current balance is: " + Wallet.getBalance()+ " kr.");
        //}	    
    }
    
    public static boolean safeWithdraw(int valueToWithdraw) throws Exception{
        lock = fileChannel.lock();
        if (getBalance() >= valueToWithdraw){
            System.out.println("Successfully withdraw");
            return true;
        }
        else {
            lock.release();
            throw new IllegalArgumentException("Unable to withdraw since current balance is not enough.");
        }
    }
    /**
     * Closes the RandomAccessFile in this.file
     */
    public void close() throws Exception {
    //System.out.println("Hello2");
	file.close();
    }
}
