package develop;


/**
 * Stores instances of flags and values received by the program.
 * Includes functions that are used to retrieve and edit the values.
 */
public class Flag {

    public enum Algorithm{
        FIFO, SJF, PR, RR
    }

    public Algorithm algorithm;
    public Integer quantum;
    public String inputfile;

    public Flag(String[] arguments ) throws Exception {

        // Check for correct number of args
        if( checkArgs(arguments) == false ){
            throw new Exception("Incorrect number of arguments");
        }

        // Place holders for args that aren't verified
        String preAlg = "";
        String preQuantum = "";
        String preInput = "";

        preAlg = arguments[1];
        if( arguments.length == 4 ){
            preInput = arguments[3];
        }else{
            preQuantum = arguments[3];
            preInput = arguments[5];
        }

        if( isValidEnum( preAlg , Algorithm.class ) == false){
            throw new Exception("Not a valid algorithm");
        }

        if( preAlg.equalsIgnoreCase(Algorithm.RR.toString())) {
            if (validateQuantum(Integer.parseInt(preQuantum)) == false) {
                throw new Exception("The quantum " + Integer.parseInt(preQuantum) +" is invalid");
            }
            this.quantum = Integer.parseInt( preQuantum );
        }

        if( validateFile( preInput ) == false ) {
            throw new Exception("The input file is invalid.\nNeeds to be less than 255 characters");
        }

        this.algorithm = Algorithm.valueOf(preAlg);
        this.inputfile = preInput;
    }

    private <Algorithm extends Enum<Algorithm>> boolean isValidEnum(
            String alg, Class<Algorithm> EnumClass){
        for( Algorithm a : EnumClass.getEnumConstants() ){
           if( a.name().equalsIgnoreCase( alg )){
               return true;
           }
        }
        return false;
    }

    private boolean checkArgs( String[] arguments ){
        if( arguments.length < 4 ||
                arguments.length > 6 ||
                arguments.length == 5 ){
           System.out.println("Usage: -alg <FIFO|SJF|PR|RR> " +
                   "[-quantum [count]] " +
                   "-inputfile <inputfile file>\n");
           return false;
        }
        return true;
    }

    private boolean validateQuantum( Integer quantum ){
        if( quantum <= 0 ||
                quantum > 1000000 ){
            return false;
        }
        return true;
    }

    private boolean validateFile( String inputfile ){
        if( inputfile.isEmpty() == true ||
                inputfile.length() > 255 ){
            return false;
        }
        return true;
    }

    public void printFlags(){
        System.out.printf("===================\n" +
                "scheduling algorithm: %s\n", this.algorithm);
        if( this.algorithm == Algorithm.RR ){
                System.out.printf("quantum: %d\n", this.quantum);
        }
        System.out.printf("input file: %s\n", this.inputfile);
    }
}
