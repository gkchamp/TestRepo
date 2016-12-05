import java.util.ArrayList;
import java.util.List;

import com.routeone.interview.Receipt;
import com.routeone.interview.StoreRegister;

import junit.framework.TestCase;

public class TestRepoTestCase extends TestCase {
	// Run once, e.g. Database connection, connection pool
	@BeforeClass
	public static void runOnceBeforeClass() {
		System.out.println("@BeforeClass - runOnceBeforeClass");
	}

	// Run once, e.g close connection, cleanup
	@AfterClass
	public static void runOnceAfterClass() {
		System.out.println("@AfterClass - runOnceAfterClass");
	}

	// Should rename to @BeforeTestMethod
	// e.g. Creating an similar object and share for all @Test
	@Before
	public void runBeforeTestMethod() {
		System.out.println("@Before - runBeforeTestMethod");
	}

	// Should rename to @AfterTestMethod
	@After
	public void runAfterTestMethod() {
		System.out.println("@After - runAfterTestMethod");
	}

	@Test
	public void test_storeRegister() {
		System.out.println("@Test - test_storeRegister");
		StoreRegister storeRegister = new StoreRegister();
		List<String> list = new ArrayList<String>();
		list.add("PC1033");
		list.add("GenericMotherboard");
		list.add("Mouse");
		list.add("LCD");
		Receipt receipt = storeRegister.checkoutOrder(list);
		System.out.println("Receipt.getFormattedTotal(): "
				+ receipt.getFormattedTotal());
		assertEquals("$738.98", receipt.getFormattedTotal());
	}

	@Test
	public void test_method_2() {
		System.out.println("@Test - test_method_2");
	}
}
