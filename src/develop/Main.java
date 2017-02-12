package develop;

import java.util.LinkedList;

import static java.lang.System.exit;

public class Main {

    // Static queues that are shared between threads
    public static LinkedList<Proc> ready_queue;
    public static LinkedList<Proc> io_queue;
    public static Flag gflags;

    public static void main(String[] args) {
        gflags = null;
        System.out.println("cpu-scheduling with threads and semaphores");

        // Create a new Flag object and pass it the arguments for parsing.
        // Flag object throws various Exceptions for invalid arguments.
        try{
            gflags = new Flag( args );
            gflags.printFlags();
        }catch( Exception e ){
            System.out.println(e);
            exit(0);
        }

        // Create Ready and Waiting Queues
        ready_queue = new LinkedList<>();
        io_queue = new LinkedList<>();

        // Create Reader Thread
        ReaderThread readerthread = null;
        try{
            readerthread = new ReaderThread( gflags.getInputFile() );
            readerthread.start();
            readerthread.join();
        }catch ( Exception e ){
            System.out.println(e);
        }

        System.out.println("MainThread joined with ReaderThread");



        // Call the file reader thread
        // Wait for file reader thread to terminate

        // Output metrics
        return;
    }

    // CPU Scheduling Thread

    // IO System Thread
}
