import java.util.ArrayList;
import java.util.List;


/**
 * Array Utils Functions
 */

/**
 * @author luismr
 */
public abstract class ArrayUtils {

	/**
	 * Get Complementary Pairs of an Integer Array for K
	 * @param k 		Complementary index 
	 * @param numbers	array of integers
	 * @return			bidimensional array containing all pairs
	 */
	public static int [] [] complementaryPairs(final int k, final int[] numbers) {
		if (numbers == null) {
			throw new IllegalArgumentException("numbers == null");
		}
		
		List<List<Integer>> pairs = new ArrayList<List<Integer>>();
		
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length; j++) {
				if (numbers[i] + numbers[j] == k) {
					List<Integer> pair = new ArrayList<Integer>();
					pair.add(i);
					pair.add(j);
					
					pairs.add(pair);
				}
			}
		}

		return transformToPairsArray(pairs);
	}

	/**
	 * Transform a List for List of Integers in a bidimensional array 
	 * @param pairs List of List of Integers representing pairs
	 * @return bidimensional primitive int array representing pairs
	 */
	private static int[][] transformToPairsArray(final List<List<Integer>> pairs) {
		int [] [] p = new int[ pairs.size() ] [ 2 ];
		int i = 0;
		
		for (List<Integer> pair : pairs) {
			for (int j = 0; j < 2; j++) {
				p[i][j] = pair.get(j);				
			}
			
			i++;
		}
		
		return p;
	}

}
