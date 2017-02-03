package develop;

/**
 * Stores instances of flags and values received by the program.
 * Includes functions that are used to retrieve and edit the values.
 */
public class Flag {

    public enum Algorithm{
        FIFO, SJF, PR, RR
    }

    public String algorithm;
    public Integer quantum;
    //public

    public Flag(String[] arguments ){
        this.algorithm = arguments[0];
        this.quantum = Integer.parseInt( arguments[1] );
    }

    public Integer validateFlags(){


        return 0;
    }
}
