package develop;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import static develop.Main.gflags;

/**
 * Proc is a process object. It contains an array consisting of alternating CPU
 * and IO times, an index integer, and a turnflag to signal whether it the
 * current turn is CPU or IO.
 */

public class Proc {
    private static int CPU = 0;
    private static int IO = 1;

    private IntegerArray bursts;
    private Integer index;
    private Integer turnflag;
    private Integer priority;

    public Proc( IntegerArray number ){
       this.bursts = new IntegerArray( number.toIntArray() );
       this.index = 0;
       this.turnflag = CPU;
       if( gflags.getAlgorithm().toString().equalsIgnoreCase("RR")){
           this.priority = 1;
        }else{
            this.priority = 0;
        }
    }

    /**
     * Checked to see whether index (+1) matches the number of elements in the
     * array and returns false if there are still bursts to process or true
     * when there are no more bursts
     *
     * @return boolean
     */
    private boolean isEmpty(){
        // + 1 to account for 0 index
        if( (this.index + 1) == bursts.cardinality() ) {
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        String output;
        if( turnflag == CPU ){
            output = "turn: CPU";
        }else{
            output = "turn: IO";
        }
        if( gflags.getAlgorithm().toString().equalsIgnoreCase( "PR")){
            output = output + " priority: " + this.priority;
        }
        output = output + " index: " + this.index + " bursts: ";
        output = output + bursts.at(0);
        for ( int i = 1 ; i < bursts.cardinality() ; i++ ){
            output = output + " " + bursts.at(i);
        }
        return output;
    }

}
