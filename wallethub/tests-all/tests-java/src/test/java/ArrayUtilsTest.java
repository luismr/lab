import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 */

/**
 * @author luismr
 *
 */
public class ArrayUtilsTest {

	@Test(expected=IllegalArgumentException.class)
	public void testNullableNumbers() {
		int [] numbers = null;
		int [] [] pairs = ArrayUtils.complementaryPairs(3, numbers);
		assertTrue(pairs != null);
		assertTrue(false);
	}
	
	@Test
	public void testNumberOfPairs() {
		int [] numbers = new int[] {1, 5, 9};
		int [] [] pairs = ArrayUtils.complementaryPairs(10, numbers);
		
		assertTrue(pairs != null);
		assertTrue(pairs.length == 3);
	}

	@Test
	public void testNumberOfPairsForNegativeK() {
		int [] numbers = new int[] {-8, 14, 7};
		int [] [] pairs = ArrayUtils.complementaryPairs(6, numbers);
		
		assertTrue(pairs != null);
		assertTrue(pairs.length == 2);
	}
	
	@Test
	public void testNumberOfPairsForLargerArray() {
		int [] numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		int [] [] pairs = ArrayUtils.complementaryPairs(40, numbers);
		
		assertTrue(pairs != null);
		assertTrue(pairs.length == 1);
	}
	
}
