import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * This class represents the logic of the calculator. It contains the operations that the calculator
 * to work out the answer.
 */
public class Operations {
	/**
	 * This method adds two floats together and returns the total sum
	 * @param num1
	 * @param num2
	 * @return total
	 */
	public float addition(float num1, float num2)
	{
		Float total = Float.sum(num1,num2);
		return total;
	}
	/**
	 * The method adds two floats together but converts the 2nd float into a minus, in order to subtract num2 from num1
	 * @param num1
	 * @param num2
	 * @return total
	 */
	public float subtraction (float num1, float num2)
	{
		Float total = Float.sum(num1,-(num2));
		return total;
	}
	
	/**
	 * This method multiplies two floats together. BigDecimal is used for accuracy. 
	 * @param num1
	 * @param num2
	 * @return total
	 */
	public BigDecimal multiplication (float num1, float num2)
	{
		BigDecimal Bnum1 = BigDecimal.valueOf(num1);
		BigDecimal Bnum2 = BigDecimal.valueOf(num2);
		
		BigDecimal total = Bnum1.multiply(Bnum2); // BigDecimal used for accuracy. As multiplying with floats can produce rounding errors
		                                          // and cause loss of precision
		return total;
	}
	/**
	 * This method divides two floats. BigDecimal is used for accuracy 
	 * @param num1
	 * @param num2
	 * @return total 
	 */
	public BigDecimal division (float num1, float num2)
	{
		if (num1 == 0 || num2 == 0)
		{
			throw new IllegalArgumentException("Cannot divide by zero"); // Produces error if user tries to divide by 0 or divide 0 by a number
		}
		BigDecimal Bnum1 = BigDecimal.valueOf(num1);
		BigDecimal Bnum2 = BigDecimal.valueOf(num2);
		
		BigDecimal total = Bnum1.divide(Bnum2, 12, RoundingMode.HALF_UP); // Rounds to 12 decimal places, prevents error when answer is recurring
		return total;
	}

	
}
