package develop;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import java.io.*;
import java.nio.Buffer;

import static develop.Main.io_queue;
import static develop.Main.ready_queue;

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
    private static final Object lock = new Object();


    ReaderThread( String inputfile ) throws Exception {
        try{
            openFile( inputfile );
        }catch( Exception e ){
            throw new Exception("Error: Something went wrong in openFile");
        }
    }

    /**
     * Read lines from input file
     */
    @Override
    public void run() {

        String line;
        String buf[];
        Boolean stop = false;
        // Loop until EOF
        while( stop != true ) {
            try {
                line = bufreader.readLine();
                buf = line.split(" ");
                if( buf[0].equalsIgnoreCase( "stop") ||
                        line == null ) {
                    stop = true;
                    System.out.println("Stop Line:");
                    continue;
                }
                /*
                    // Debug: print out all elements of buf (args in task)
                    for( int i = 0 ; i < buf.length ; i ++ ){
                        System.out.println( buf[i] );
                    }
                */
                handleTask(buf);
            } catch (Exception e) {
                System.out.println("Error: Something went wrong in run() of" +
                        " ReaderThread");
                e.printStackTrace();
            } // end catch
        } // end while
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
        } // end while
    }

    /**
     * Handles the 3 different cases. This creates an instance of a Proc
     * object whenever needed and places it into the statically
     * instantiated ready_queue.
     */
    private void handleTask( String args[] ) throws Exception {
        switch ( args[0].toLowerCase() ) {
            case "proc":
                System.out.println("Proc Line:");
                IntegerArray intarray = separateIntegers( args );
                Proc proc = new Proc( intarray );

                // Accessing ready_queue should be protected
                synchronized ( lock ) {
                    ready_queue.add(proc);
                }
                //System.out.println( ready_queue.toString() );
                break;
            case "sleep":
                System.out.printf("Sleep Line: %s\n", args[1]);
                Thread.sleep( Integer.parseInt( args[1]) );
                break;
            case "stop":
                throw new Exception("Stop Line: (How did you get here?!?!)");
            default:
                throw new Exception("Error: First argument on line" +
                        "should be 'proc','sleep', or 'stop'");
        } // end switch
    }

    /**
     * Separate burst integers from the String array and return an
     * integer array.
     */
    private IntegerArray separateIntegers( String args[] ) {
        IntegerArray intarray = new IntegerArray();
        for( int i = 1 ; i < args.length ; i++ ){
            intarray.add( Integer.valueOf( args[i] ) );
        } // end for-loop
        return intarray;
    }
}
