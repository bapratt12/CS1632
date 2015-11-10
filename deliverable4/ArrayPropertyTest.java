package com.deliverable4.propertyTests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayPropertyTest {
	
	Random rand = new Random();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	//Generate 100 randoms arrays of random lengths
	//Test length, purity, and all elements increasing/stay the same
	public void arrayPropertyTests() {
		for(int k = 0; k < 100; k++){
			//Generate array sof length 1-300
			int arr[] = new int[rand.nextInt(300)];
			
			//fill arrays with random integers
			for(int j = 0; j < arr.length; j++){
				arr[j] = rand.nextInt();
			}
			//test length is same before and after sort
			int length = arr.length;
			
			//save copy of array to test purity
			int arr2[] = Arrays.copyOf(arr, arr.length);
			
			//Sort the array, this is what's being tested
			Arrays.sort(arr);
			
			//length in should equal length out
			assertEquals(length, arr.length);
			
			//test purity, sorting array again should give same result
			Arrays.sort(arr2);
			assertArrayEquals(arr, arr2);
			
			//Test that output array values are always increasing or staying the same, never decreasing
			for(int j = 0; j < arr.length-1; j++){
				assertTrue(arr[j]<=arr[j+1]);
			}
		}
	}
}
