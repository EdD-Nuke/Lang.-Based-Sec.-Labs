package backEnd;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.FileReader;
import java.io.BufferedReader;

///NEW IMPORTS
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;



public class Pocket {
    /**
     * The RandomAccessFile of the pocket file
     */
    private static RandomAccessFile file;
    private static FileLock lock ;
    private static FileChannel fileChannel;

    /**
     * Creates a Pocket object
     * 
     * A Pocket object interfaces with the pocket RandomAccessFile.
     */
    public Pocket () throws Exception {
        file = new RandomAccessFile(new File("backEnd/pocket.txt"), "rw");
        fileChannel = file.getChannel();
        lock = null;
    }

    /**
     * Adds a product to the pocket. 
     *
     * @param  product           product name to add to the pocket (e.g. "car")
     */
    public static void addProduct(String product) throws Exception {
        //File lock
        lock = fileChannel.lock();
        file.seek(file.length());
        file.writeBytes(product+'\n'); 
        lock.release();
    }

    /**
     * Generates a string representation of the pocket
     *
     * @return a string representing the pocket
     */
    public String getPocket() throws Exception {
        StringBuilder sb = new StringBuilder();
        file.seek(0);
        String line;
        while((line = file.readLine()) != null) {
            sb.append(line);
            sb.append('\n');
        }

        return sb.toString();
    }

    /**
     * Closes the RandomAccessFile in this.file
     */
    public void close() throws Exception {
        file.close();
    }
}
