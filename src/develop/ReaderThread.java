package develop;

import java.io.*;
import java.nio.Buffer;

/**
 * The ReaderThread attempts to open a file. Upon successful opening of a file
 * ReaderThread iterates through the file, reading each line. Depending on the
 * first argument on each line, ReadThread will take one of three actions:
 *
 * 1 - Reading 'proc', a Proc object is created, given the list of cpu and io
 *      burst information from the rest of the line, and placed in the ready
 *      queue.
 *
 * 2 - Reading 'sleep', the ReaderThread will sleep for the amount of
 *      time specified by the argument next in the line.
 *
 * 3 - Reading 'stop' causes ReaderThread to terminate itself.
 */
public class ReaderThread extends Thread {
    BufferedReader bufreader;

    ReaderThread( String inputfile ) throws Exception {
        try{
            openFile( inputfile );
        }catch( Exception e ){
            throw new Exception("Error: Something went wrong in openFile");
        }
;
    }

    /**
     * Read lines from input file
     */
    @Override
    public void run() {
        try{
            sleep(1000);
            printFile();
        }catch ( Exception e ){
            System.out.println("Error: Something went wrong in run() of ReaderThread");
        }
    }

    /**
     * Attempt to open file for reading
     */
    private boolean openFile( String inputfile ) throws Exception {
        FileReader flreader = new FileReader( inputfile );
        this.bufreader = new BufferedReader( flreader );
        return true;
    }

    /**
     * print file for debugging purposes
     */
    private void printFile() throws IOException {
        System.out.println("===================\n" +
                "Input File:\n");
        String buffer = "";
        while( ( buffer = bufreader.readLine()) != null ) {
            System.out.println(buffer);
        }
    }
}
