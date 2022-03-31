package backEnd;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Wallet {
    /**
     * The RandomAccessFile of the wallet file
     */  
    public static RandomAccessFile file;

    /**
     * Creates a Wallet object
     *
     * A Wallet object interfaces with the wallet RandomAccessFile
     */
    public Wallet () throws Exception {
	file = new RandomAccessFile(new File("backEnd/wallet.txt"), "rw");
    }

    /**
     * Gets the wallet balance. 
     *
     * @return                   The content of the wallet file as an integer
     */
    public static int getBalance() throws IOException {
	file.seek(0);
	return Integer.parseInt(file.readLine());
    }

    /**
     * Sets a new balance in the wallet
     *
     * @param  newBalance          new balance to write in the wallet
     */
    public static void setBalance(int newBalance) throws Exception {
	file.setLength(0);
	String str = Integer.valueOf(newBalance).toString()+'\n'; 
	file.writeBytes(str); 
    }

    // public boolean safeWithdraw(int valueToWithdraw) throws Exception{
    //     //TODO:
    //     // true if the withdraw was possible
    //     // check if withdraw was possible
    //     //
    // }
    /**
     * Closes the RandomAccessFile in this.file
     */
    public void close() throws Exception {
	file.close();
    }
}
