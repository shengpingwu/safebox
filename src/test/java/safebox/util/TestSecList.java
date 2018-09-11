package safebox.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TestSecList {

	private SecList secList; 
	
	@Before
	public void setUp() throws Exception {
		secList = new SecList();
		secList.loadCommonPassword();
	}

	@Test
	public void test001TestShengCommonPassword() {
		
		
		Assert.assertEquals(true, secList.passwordIsContainsInTheList("sheng"));
	}

	@Test
	public void test003TestNotCommonPassword() {
		
		
		Assert.assertEquals(true, secList.passwordIsContainsInTheList("michael"));
	}
	
	
	@Test
	public void test002TestNotCommonPassword() {
		
		
		Assert.assertEquals(false, secList.passwordIsContainsInTheList("xsalkm"));
	}
	
	
	@Test
	public void test003TestGetToken() {
		
		//String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJwZXBlIiwiaWQiOiIxMWQzYWViZS0zNWJmLTQ3NzItYjU1ZS1kZTU0NGQ5NTJmMjkiLCJleHAiOjE1Mjk0NDYwNDh9.fqlMPPjZ3YNjIYXOK2VM4_l_TRGaJCG5Mbx00ughWXs";
		
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJwZXBlIiwiaWQiOiIxMWQzYWViZS0zNWJmLTQ3NzItYjU1ZS1kZTU0NGQ5NTJmMjkiLCJleHAiOjE1Mjk0NDMwNDh9.jMvUmB7Nqhrfh_dfcb8cAHKC17axgJaCL7Ue2GP7O3I";

		Assert.assertNotNull(Util.decodeToken(token));
	}
	
	@Test
	public void test004TestEncodeToken() {
		
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjExZDNhZWJlLTM1YmYtNDc3Mi1iNTVlLWRlNTQ0ZDk1MmYyOSIsInVzZXIiOiJwZXBlIiwiZXhwIjoxNTI5NDQ0MDI4NjE5fQ.HgrRqmrOmyEyIadGli29w2Izi6HrxhU5oNb6IaSusZU";
		
		System.out.println(Util.createToken("11d3aebe-35bf-4772-b55e-de544d952f29", "pepe", 1800000));
	}
}
