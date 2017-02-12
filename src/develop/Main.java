package develop;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        Flag gflags = null;
        System.out.println("cpu-scheduling with threads and semaphores");

        // Create a new Flag object and pass it the arguments for parsing.
        // Flag object throws various Exceptions for invalid arguments.
        try{
            gflags = new Flag( args );
        }catch( Exception e ){
            System.out.println(e);
            exit(0);
        }
        gflags.printFlags();

        // Create Reader Thread
        ReaderThread readerthread = null;
        try{
            readerthread = new ReaderThread( gflags.inputfile );
            readerthread.start();
            readerthread.join();
        }catch ( Exception e ){
            System.out.println(e);
        }

        System.out.println("MainThread joineded with ReaderThread");



        // Call the file reader thread
        // Wait for file reader thread to terminate

        // Output metrics
        return;
    }

    // CPU Scheduling Thread

    // IO System Thread
}
