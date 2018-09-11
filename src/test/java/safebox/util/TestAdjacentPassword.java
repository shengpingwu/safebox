package safebox.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestAdjacentPassword {

	private AdjacentPassword adjacentPassword;
	
	@Before
	public void setUp() throws Exception {
		adjacentPassword = new AdjacentPassword(); 
		adjacentPassword.initialize();
	}

	@Test
	public void test001TestQwertyAdjacent() {
		
		Assert.assertEquals(true, adjacentPassword.isAdjacentPassword("qwerty"));
	}

	@Test
	public void test002TestQwertyAdjacentMixUpperCaseAndLowerCase() {
		
		Assert.assertEquals(true, adjacentPassword.isAdjacentPassword("QwErTy"));
	}
	@Test
	public void test003TestASDFGHAdjacent() {
		
		Assert.assertEquals(true, adjacentPassword.isAdjacentPassword("ASDFGH"));
	}
	@Test
	public void test004TestFGVCDRAdjacent() {
		
		Assert.assertEquals(true, adjacentPassword.isAdjacentPassword("FGVCDR"));
	}
	@Test
	public void test005Test123456Adjacent() {
		
		Assert.assertEquals(true, adjacentPassword.isAdjacentPassword("123456"));
	}
	@Test
	public void test006TestNotAdjacentPassword() {
		
		Assert.assertEquals(false, adjacentPassword.isAdjacentPassword("1S4FCX"));
	}
	
	@Test
	public void test007TestNotAdjacentPassword() {
		
		Assert.assertEquals(false, adjacentPassword.isAdjacentPassword("ghbj?-"));
	}
}


