package ba.unsa.etf.rpr;

import java.util.Stack;

/**
 * ExpressionEvaluator class has just one method, which solves arithmetic expressions using some old techniques.
 * @author Griza20
 */

public class ExpressionEvaluator {
    /**
     * Stack for storing values.
     */
    private Stack<Double> values;
    /**
     * Stack for storing operators.
     */
    private Stack<String> operators;

    /**
     * Constructor that makes instances of atributes.
     */
    ExpressionEvaluator(){
        this.values = new Stack<>();
        this.operators = new Stack<>();
    }

    /**
     * Calculating arithmetic expression.
     * @param expression String that represents arithmetic expression.
     * @return The result of expression, type Double.
     */
    public Double evaluate(String expression){
        int i=0, left = 0, br = 0, right = 0 , numop = 0;
        /**
         * Testing whether the brackets are successfully closed, otherwise an exception is thrown.
         */
        int leftpar = 0, rightpar = 0;
        while( i<expression.length() ){
            if( expression.charAt(i) == '(' ) leftpar = leftpar + 1;
            else if ( expression.charAt(i) == ')' ) rightpar = rightpar + 1;
            else if ( expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*' || expression.charAt(i) == '/' )
                numop = numop + 1;
            else if ( expression.charAt(i) == 's' ){
                i = i + 3;
                numop = numop + 1;
            }
            i = i + 1;
        }
        if( leftpar != rightpar || leftpar != numop ) throw new RuntimeException( "Error" );
        i=0;
        while( i<expression.length() ){
            if( expression.charAt(i) == '(' ) {
                left = i;
                br = 0;
                i = i + 1;
                continue;
            }
            else if( expression.charAt(i) >= '0' && expression.charAt(i) <= '9' ){
                int len=0;
                while( ( ( expression.charAt(i) >= '0' && expression.charAt(i) <= '9' ) || expression.charAt(i) == '.' ) && expression.charAt(i) != '\n' ) {
                    len = len + 1;
                    i = i + 1;
                }
                values.push( Double.parseDouble( expression.substring( i-len,i ) ) );
                continue;
            }
            else if( expression.charAt(i) == '+' ){
                operators.push( Character.toString( expression.charAt(i) ) );
                br = br + 1;
            }
            else if( expression.charAt(i) == '-' ){
                operators.push( Character.toString( expression.charAt(i) ) );
                br = br + 1;
            }
            else if( expression.charAt(i) == '*' ){
                operators.push( Character.toString( expression.charAt(i) ) );
                br = br + 1;
            }
            else if( expression.charAt(i) == '/' ){
                operators.push( Character.toString( expression.charAt(i) ) );
                br = br + 1;
            }
            else if( expression.charAt(i) == 's' && expression.substring( i, i+4 ).equals( "sqrt" ) ){
                operators.push("sqrt");
                i = i + 3;
            }
            /**
             * Finding the first closed bracket and calculating all the factors inside that bracket.
             */
            else if( expression.charAt(i) == ')' ){
                Double dodatak = 0.;
                right = i + 1;
                Double v = values.pop();
                String op = operators.pop();
                while( br > 0 ) {
                    if( !op.equals( "sqrt" ) ) br = br - 1;
                    if ( op.equals( "+" ) ) v = values.pop() + v;
                    else if ( op.equals( "-" ) ) v = values.pop() - v;
                    else if ( op.equals( "*" ) ) v = values.pop() * v;
                    else if ( op.equals( "/" ) ) v = values.pop() / v;
                    else if ( op.equals( "sqrt" ) ) v = Math.sqrt(v);
                    dodatak = v;
                    values.push(v);
                    if( br > 0 ){
                        v = values.pop();
                        op = operators.pop();
                    }
                }
                if ( op.equals( "sqrt" ) && br == 0 ) {
                    v = Math.sqrt(v);
                    dodatak = v;
                    values.push(v);
                }
                if( dodatak < 0 ) dodatak = dodatak * ( -1 );
                /**
                 * Creating a new string that doesn't include the last bracket, but replaces it with its value.
                 */
                if( op.equals( "sqrt" ) ) expression = expression.substring( 0, left - 5) + dodatak.toString() + expression.substring( right );
                else expression = expression.substring( 0, left ) + dodatak.toString() + expression.substring( right );
                right = 0;
                i = 0;
                if( operators.isEmpty() && values.size() == 1 && expression.length() <= 5 ) break;
                while(!values.isEmpty()) values.pop();
                while(!operators.isEmpty()) operators.pop();
                continue;
            }
            i = i + 1;
        }
        return values.pop();
    }
}
