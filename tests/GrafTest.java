package tests;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import dominio.algoritmos.Graf;
/**
 * 
 * @author Yoel Cabo
 *
 */
public class GrafTest {
	
	@Test
	public void test1() {
		Graf G = new Graf();
		assertFalse(G.existeixNode("Pepe"));
	}
	
	@Test
	public void testAddExisteix() {
		Graf G = new Graf();
		assertTrue(G.addNode("Pepe"));
		assertTrue(G.existeixNode("Pepe"));
	}
	
	@Test
	public void testAddExisteix2() {
		Graf G = new Graf();
		assertTrue(G.addNode("Pepe"));
		assertTrue(G.existeixNode("Pepe"));
		assertFalse(G.addNode("Pepe"));
		assertTrue(G.addNode("Maria"));
		assertTrue(G.existeixNode("Pepe"));
		assertTrue(G.existeixNode("Maria"));
		assertEquals((Integer) 2, G.size());
	}
	
	@Test
	public void testSetGetPes() {
		Graf G = new Graf();
		assertTrue(G.addNode("Pepe"));
		assertTrue(G.addNode("Maria"));
		assertFalse(G.addAresta("Pepe", "Maria", -5.5));
		assertTrue(G.addAresta("Pepe", "Maria", 5.5));
		assertTrue(G.existeixAresta("Maria", "Pepe"));
		assertTrue(G.existeixAresta("Pepe", "Maria"));
		assertFalse(G.addAresta("Pepe", "Maria", 5.5));
		assertEquals(5.5, G.getPes("Pepe", "Maria"), 0.001);
		assertEquals(G.getPes("Maria", "Pepe"), G.getPes("Pepe", "Maria"), 0.001);
		assertTrue(G.setPes("Maria", "Pepe", 6.5));
		assertFalse(G.setPes("Maria", "Pepe", -156.5));
		assertEquals(6.5, G.getPes("Pepe", "Maria"), 0.001);
		assertEquals(G.getPes("Maria", "Pepe"), G.getPes("Pepe", "Maria"), 0.001);
	}
	
	@Test
	public void testCreadoraHashSet() {
		HashSet<String> hs = new HashSet<String>();
		hs.add("Maria");
		hs.add("Silla");
		hs.add("Pepe");
		Graf G = new Graf(hs);
		assertTrue(G.existeixNode("Maria"));
		assertTrue(G.existeixNode("Pepe"));
		assertTrue(G.existeixNode("Silla"));
	}
	
	@Test
	public void testGetNodes() {
		HashSet<String> hs = new HashSet<String>();
		hs.add("Maria");
		hs.add("Silla");
		hs.add("Pepe");
		Graf G = new Graf(hs);
		assertEquals(hs, G.getNodes());
	}
	
	@Test
	public void testGetAdjacents() {
		HashSet<String> hs = new HashSet<String>();
		hs.add("Maria");
		hs.add("Silla");
		hs.add("Pepe");
		
		Graf G = new Graf(hs);
		G.addNode("Jose Miguel");
		G.addNode("Apartado");
		for (String amigo : hs) {
			G.addAresta(amigo, "Jose Miguel", 125.75);
		}
		assertEquals(hs, G.getAdjacents("Jose Miguel"));
		assertEquals(new HashSet<String>(), G.getAdjacents("Apartado"));
	}
	
	@Test
	public void testRemoveAresta() {
		HashSet<String> hs = new HashSet<String>();
		hs.add("Maria");
		hs.add("Silla");
		hs.add("Pepe");
		
		Graf G = new Graf(hs);
		G.addNode("Jose Miguel");
		G.addNode("Apartado");
		for (String amigo : hs) {
			G.addAresta(amigo, "Jose Miguel", 125.75);
		}
		assertTrue(G.removeAresta("Jose Miguel", "Silla"));
		assertFalse(G.removeAresta("Jose Miguel", "Silla"));
		hs.remove("Silla");
		assertEquals(hs, G.getAdjacents("Jose Miguel"));
		assertEquals(new HashSet<String>(), G.getAdjacents("Silla"));
	}
	
	@Test
	public void testRemoveNode2() {
		HashSet<String> hs = new HashSet<String>();
		hs.add("Maria");
		hs.add("Silla");
		hs.add("Pepe");
		hs.add("Jose");
		hs.add("Ana");
		Graf G = new Graf(hs);
		G.removeNode("Silla");
		hs.remove("Silla");
		assertEquals(hs, G.getNodes());
		G.addAresta("Maria", "Ana", 5.55);
		G.removeNode("Pepe");
		assertEquals(5.55, G.getPes("Maria", "Ana"), 0.0001);
		
	}
	
	@Test
	public void testRemoveNode1() {
		HashSet<String> hs = new HashSet<String>();
		hs.add("Maria");
		hs.add("Silla");
		hs.add("Pepe");
		hs.add("Jose");
		hs.add("Ana");
		Graf G = new Graf(hs);
		G.removeNode("Pepe");
		G.addAresta("Maria", "Ana", 5.55);
		assertEquals(5.55, G.getPes("Maria", "Ana"), 0.0001);
		assertTrue(G.removeNode("Silla"));
		assertEquals(5.55, G.getPes("Maria", "Ana"), 0.0001);
		assertFalse(G.removeNode("Silla"));
		assertEquals(5.55, G.getPes("Maria", "Ana"), 0.0001);
		assertTrue(G.addNode("Silla"));
		assertTrue(G.existeixNode("Silla"));
		assertEquals(5.55, G.getPes("Maria", "Ana"), 0.0001);
	}
	
	
	

}
