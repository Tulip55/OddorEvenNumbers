package Test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OddorEvenNumbersTest {

	@Test
	void testEvenNumber() {
		assertTrue(isEven(8));
		assertTrue(isEven(0));
		assertTrue(isEven(-4));
	}
	
	@Test
	void testOddNumber() {
		assertFalse(isEven(9));
		assertFalse(isEven(-7));		
	}
	
	boolean isEven(int number) {
		return number % 2 == 0;
	}
}
