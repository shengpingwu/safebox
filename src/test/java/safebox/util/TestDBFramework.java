package safebox.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import safebox.model.SafeboxData;

public class TestDBFramework {

	private DBFramework dbFramework; 
	
	@Rule
	public TemporaryFolder folder= new TemporaryFolder();
	
	@Before
	public void setUp() throws Exception {
		
		dbFramework = DBFramework.getInstance();
		dbFramework.setDBFilesLocation (folder.newFolder("TestDBFramework").getAbsolutePath());
		dbFramework.ini();
		
		dbFramework.insertOrUpdateSafebox(SafeboxData.makeSafeboxData("id1", "user1", "password","{}"));
		dbFramework.insertOrUpdateSafebox(SafeboxData.makeSafeboxData("id2", "user2", "password","{}"));
		dbFramework.insertOrUpdateSafebox(SafeboxData.makeSafeboxData("id3", "user3", "password","{}"));
		dbFramework.insertOrUpdateSafebox(SafeboxData.makeSafeboxData("id4", "user3", "password","{}"));
		
	}

	@After
	public void tearDown() throws Exception {
		folder.delete();
	}

	@Test
	public void test001FindById() {
		
		Assert.assertNotNull(dbFramework.findSafeboxById("id1"));
	}

	
	@Test
	public void test002FindByIdNotExist() {
		
		Assert.assertNull(dbFramework.findSafeboxById("idNotExist"));
	}
	
	@Test
	public void test003FindByName() {
		
		List<SafeboxData> list = dbFramework.findSafeboxByUser("user3");
		
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void test004FindByNameNotExist() {
		
		List<SafeboxData> list = dbFramework.findSafeboxByUser("userNotExist");
		
		Assert.assertNotNull(list);
		Assert.assertEquals(0, list.size());
	}
	
	@Test
	public void test005UpdateSafebox() {
		
		SafeboxData safeboxData = dbFramework.findSafeboxById("id1");
		
		Assert.assertNotNull(safeboxData);
		
		List<String> items = new ArrayList<String>();
		items.add("items1");
		items.add("items2");
		safeboxData.setItems(Util.ItemsListToJson(items));
		dbFramework.insertOrUpdateSafebox(safeboxData);
		
		
		SafeboxData safeboxDataMod = dbFramework.findSafeboxById("id1");
		Assert.assertNotNull(safeboxDataMod);
		Assert.assertEquals(2, Util.jsonToItemsList(safeboxDataMod.getItems()).size());
	}
}
