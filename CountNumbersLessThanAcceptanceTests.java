package com.db.gtb.tf.interview;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CountNumbersLessThanAcceptanceTests {

	private int testCountNumbersLessThanWithinPerformance(int[] sortedArray, int lessThan) {
		long start = System.nanoTime();
		int count = testCountNumbersLessThan(sortedArray, lessThan);

		double durationMs = (System.nanoTime() - start) / 1000000.0;
		assertThat(durationMs).isLessThanOrEqualTo(0.01);
		System.out.println("Execution Duration: " + durationMs / 1000000.0 + "ms");

		return count;
	}

	private int testCountNumbersLessThan(int[] sortedArray, int lessThan) {

		// TODO: Implement
//		throw new UnsupportedOperationException();
        int low = 0;
        int high = sortedArray.length;
        int mid;
        while (low < high) {
            // avoid overflow
            mid = low + (high - low)/2;
            if (sortedArray[mid] < lessThan){
                low = mid + 1;
            }else {
                high = mid;
            }
        }


        return low;
	}


	@Test
	public void example_case() {
		assertThat(testCountNumbersLessThan(new int[]{ 1,3,5,7}, 4)).isEqualTo(2);
	}

	@Test
	public void small_arrays() {
		assertThat(testCountNumbersLessThan(new int[]{}, 0)).isEqualTo(0);
		assertThat(testCountNumbersLessThan(new int[]{1}, 1)).isEqualTo(0);
		assertThat(testCountNumbersLessThan(new int[]{1}, 2)).isEqualTo(1);
		assertThat(testCountNumbersLessThan(new int[]{1,2}, 2)).isEqualTo(1);
		assertThat(testCountNumbersLessThan(new int[]{1,2,3,4}, 5)).isEqualTo(4);
		assertThat(testCountNumbersLessThan(new int[]{1,2,3,4,5}, 5)).isEqualTo(4);
		assertThat(testCountNumbersLessThan(new int[]{1,2,3,4,5}, 1)).isEqualTo(0);
		assertThat(testCountNumbersLessThan(new int[]{1,2,3,4,5,7,8,9}, 6)).isEqualTo(5);
		assertThat(testCountNumbersLessThan(new int[]{1,2,3,4,5,7,8,9}, 7)).isEqualTo(5);
		assertThat(testCountNumbersLessThan(new int[]{1,2,3,4,5,7,8,9}, 4)).isEqualTo(3);

        assertThat(testCountNumbersLessThan(new int[]{3, 3, 3}, 3)).isEqualTo(0);

    }

	@Test
	public void contains_negative_numbers() {
		assertThat(testCountNumbersLessThan(new int[]{-5,-4, -3, -2}, 0)).isEqualTo(4);
		assertThat(testCountNumbersLessThan(new int[]{-5,-4, -3, -2}, -1)).isEqualTo(4);
		assertThat(testCountNumbersLessThan(new int[]{-5,-4, -3, -2}, -2)).isEqualTo(3);
		assertThat(testCountNumbersLessThan(new int[]{-5,-4, -3, -2}, -5)).isEqualTo(0);
		assertThat(testCountNumbersLessThan(new int[]{-5,-4, -3, -2}, -6)).isEqualTo(0);

		assertThat(testCountNumbersLessThan(new int[]{-1, 0, 1}, 0)).isEqualTo(1);
		assertThat(testCountNumbersLessThan(new int[]{-1, 0, 1}, -1)).isEqualTo(0);
		assertThat(testCountNumbersLessThan(new int[]{-1, 0, 1}, 1)).isEqualTo(2);

		assertThat(testCountNumbersLessThan(new int[]{-3, -3, -3}, -3)).isEqualTo(0);
	}

	@Test
	public void performance_test_when_contains_lessThan() {
		int maxValue = (Integer.MAX_VALUE - 1) / 2;
		int minValue = 0;
		int medianValue = (maxValue / 2);
		int[] values = generateIntArray(minValue, maxValue, -1);

		// LessThan at end
		assertThat(testCountNumbersLessThanWithinPerformance(values, maxValue)).isEqualTo(maxValue);
		// LessThan at beginning
		assertThat(testCountNumbersLessThanWithinPerformance(values, minValue)).isEqualTo(0);
		// LessThan in middle
		assertThat(testCountNumbersLessThanWithinPerformance(values, medianValue)).isEqualTo(medianValue);
	}


	@Test
	public void performance_test_when_doesnt_contain_lessThan() {
		int maxValue = (Integer.MAX_VALUE - 1) / 2;
		int minValue = 0;
		int medianValue = (maxValue / 2);
		int[] values = generateIntArray(minValue + 1, maxValue - 1,  medianValue);

		// LessThan at end
		assertThat(testCountNumbersLessThanWithinPerformance(values, maxValue)).isEqualTo(maxValue - 2);
		// LessThan at beginning
		assertThat(testCountNumbersLessThanWithinPerformance(values, minValue)).isEqualTo(0);
		// LessThan in middle
		assertThat(testCountNumbersLessThanWithinPerformance(values, medianValue)).isEqualTo(medianValue - 1);
	}

	private int[] generateIntArray(int fromInclusive, int toInclusive, int exclude) {
		int[] values = new int[toInclusive - fromInclusive + (exclude < toInclusive && exclude > fromInclusive ? 0 : 1)];

		int offset = 0;
		for(int i = fromInclusive; i <= toInclusive; ++i) {
			if(i == exclude) {
				--offset;
				continue;
			}

			values[i - fromInclusive + offset] = i;
		}

		return values;
	}
}
