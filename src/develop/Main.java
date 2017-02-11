package develop;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        Flag gflags = null;
        System.out.println("cpu-scheduling with threads and semaphores");

        try{
            gflags = new Flag( args );
        }catch( Exception e ){
            System.out.println(e);
            exit(0);
        }
        gflags.printFlags();


        // Call the file reader thread
        // Wait for file reader thread to terminate

        // Output metrics
        return;
    }

    // File Reader Thread

    // CPU Scheduling Thread

    // IO System Thread
}
