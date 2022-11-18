package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests for class ExpressionEvaluator.
 * @author Griza20
 */
public class ExpressionEvaluatorTest {
    private ExpressionEvaluator ee = new ExpressionEvaluator();
    /**
     * Testing when the right parenthesis isn't closed, program should throw an exception
     */
    @Test
    public void testForException() {
        String s = "(1+2+3(14/2)";
        ee = new ExpressionEvaluator();
        assertThrows(RuntimeException.class, () -> { ee.evaluate(s); });
    }

    /**
     * Testing the operator + in method evaluate that belongs to the class ExpressionEvaluator
     */
    @Test
    public void testForAddition(){
        assertEquals(15, ee.evaluate("(1+(2+(3+(4+5))))"));
    }
    /**
     * Testing the operator - in method evaluate that belongs to the class ExpressionEvaluator
     */
    @Test
    public void testForSubtraction(){
        assertEquals(8, ee.evaluate("(15-(10-3))"));
    }
    /**
     * Testing the operator * in method evaluate that belongs to the class ExpressionEvaluator
     */
    @Test
    public void testForMultiplication(){
        assertEquals(72, ee.evaluate("(3*(4*(2*(3*1))))"));
    }
    /**
     * Testing the operator / in method evaluate that belongs to the class ExpressionEvaluator
     */
    @Test
    public void testForDivision(){
        assertEquals(4, ee.evaluate("(8/(4/(2/1)))"));
    }
    /**
     * Testing an aritmethic expression using all operators in method evaluate that belongs to the class ExpressionEvaluator
     */
    @Test
    public void testForRandomArithmeticExpression(){
        assertEquals(30, ee.evaluate("(2*(3+(17-(150/30))))"));
    }
}
