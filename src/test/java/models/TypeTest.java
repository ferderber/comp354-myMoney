package test.java.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import main.java.models.Type;

public class TypeTest {
	
	@Test
	public void testContructor() {
		String id = "Music";
		Type t = new Type(id);
		assertEquals(t.getId(), id);
		assertNull(t.getTransactions());
	}
	
	@Test
	public void testSetAndGet() {
		Type t = new Type("Music");
		String newID = "Snacks";
		t.setId(newID);
		assertEquals(t.getId(), newID);
	}

	
}
