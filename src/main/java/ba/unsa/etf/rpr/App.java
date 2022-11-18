package ba.unsa.etf.rpr;

/**
 * Main class where it's enabled for user to input through command line.
 * @author Griza20
 * @version 1.0
 */
public class App 
{
    /**
     * Runnable method which checks validation of entered arithmetic expression.
     * @param args Array of Strings entered through command line.
     */
    public static void main( String[] args ) {
        int i = 0, leftpar = 0, rightpar = 0, brop = 0;
        String expression = "";
        while( i < args[0].length() ){
            if ( args[0].charAt(i) == '"' || args[0].charAt(i) == ' ' ) {
                i = i + 1;
                continue;
            }
            else if( args[0].charAt(i) == '(' )
                leftpar = leftpar + 1;
            else if( args[0].charAt(i) == ')' )
                rightpar = rightpar + 1;
            else if( args[0].charAt(i) == '+' || args[0].charAt(i) == '-' || args[0].charAt(i) == '*' || args[0].charAt(i) == '/' )
                brop = brop + 1;
            else if ( args[0].charAt(i) == 's' ){
                if ( i + 3 < args[0].length() && args[0].charAt(i + 1) == 'q' && args[0].charAt(i + 2) == 'r' && args[0].charAt(i + 3) == 't' ) {
                    i = i + 3;
                    brop = brop + 1;
                }
                else throw new RuntimeException( "Error" );
            }
            else if( !( args[0].charAt(i) >= '0' && args[0].charAt(i) <= '9' ) ) throw new RuntimeException("Error");
            i++;
        }
        expression = args[0];
        try {
            if (leftpar != rightpar || leftpar != brop) throw new RuntimeException("Error");
            ExpressionEvaluator ee = new ExpressionEvaluator();
            Double d = ee.evaluate(expression);
            System.out.println(d);
        }catch(RuntimeException e){
            System.out.println(e.getMessage()+": Check did you properly input expression");
        }
    }
}
