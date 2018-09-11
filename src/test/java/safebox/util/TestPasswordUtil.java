package safebox.util;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPasswordUtil {

	private PasswordUtil passwordUtil; 
	
	@Before
	public void setUp() throws Exception {
		passwordUtil = PasswordUtil.getInstance();
	}

	@Test
	public void test001TestPasswordInvalidLengthLessThanSix() {
		
		Assert.assertEquals(false, passwordUtil.passwordIsValid("12"));
	}
	
	@Test
	public void test002TestPasswordInvalidIsCommon() {
		
		Assert.assertEquals(false, passwordUtil.passwordIsValid("michael"));
	}
	
	@Test
	public void test003TestPasswordInvalidIsConsecutive() {
		
		Assert.assertEquals(false, passwordUtil.passwordIsValid("lo98ik"));
	}

	@Test
	public void test004TestPasswordValid() {
		
		Assert.assertEquals(true, passwordUtil.passwordIsValid("xfj7sb"));
	}
	
	@Test
	public void test005TestPasswordEncode() {
		
		Assert.assertEquals("29404363A0DC620A0E639E6117D594F81C71D9C2", passwordUtil.encodePassword("safeboxtest"));
	}
	
		
	
}
