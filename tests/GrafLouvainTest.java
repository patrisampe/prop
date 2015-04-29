package tests;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import dominio.algoritmos.GrafLouvain;

public class GrafLouvainTest {

	@Test
	public void test1() {
		HashSet<String> hs = new HashSet<String>();
		hs.add("Maria");
		hs.add("Silla");
		hs.add("Pepe");
		hs.add("Jose");
		hs.add("Ana");
		hs.add("Miguel");
		GrafLouvain G = new GrafLouvain(hs);
		assertEquals(0.0, G.sumaPesos(), 0.00001);
	}
	
	@Test
	public void test2() {
		HashSet<String> hs = new HashSet<String>();
		hs.add("Maria");
		hs.add("Silla");
		hs.add("Pepe");
		hs.add("Jose");
		hs.add("Ana");
		hs.add("Miguel");
		GrafLouvain G = new GrafLouvain(hs);
		G.addAresta("Maria", "Miguel", 101.0);
		G.addAresta("Maria", "Jose", 102.0);
		G.addAresta("Silla", "Jose", 103.0);
		G.addAresta("Silla", "Pepe", 104.0);
		assertEquals(410.0, G.sumaPesos(), 0.00001);
		assertEquals(G.sumaPesos()*2, G.sumaPesos(hs), 0.00001);
	}
	
	@Test
	public void test3() {
		HashSet<String> hs = new HashSet<String>();
		hs.add("Maria");
		hs.add("Silla");
		hs.add("Pepe");
		hs.add("Jose");
		hs.add("Ana");
		hs.add("Miguel");
		GrafLouvain G = new GrafLouvain(hs);
		G.addAresta("Maria", "Miguel", 101.0);
		G.addAresta("Maria", "Jose", 102.0);
		G.addAresta("Maria", "Silla", 103.0);
		G.addAresta("Maria", "Pepe", 104.0);
		G.addAresta("Silla", "Jose", 103.0);
		G.addAresta("Silla", "Pepe", 104.0);
		G.addAresta("Ana", "Miguel",65151652.516);
		assertEquals(410.0, G.sumaPesosAdjacents("Maria"), 0.00001);
		assertEquals(410.0, G.sumaPesosAdjacents("Maria",hs), 0.00001);

		
	}
	
	@Test
	public void test4() {
		HashSet<String> hs = new HashSet<String>();
		hs.add("Maria");
		hs.add("Silla");
		hs.add("Pepe");
		hs.add("Jose");
		hs.add("Ana");
		hs.add("Miguel");
		GrafLouvain G = new GrafLouvain(hs);
		G.addAresta("Maria", "Miguel", 101.0);
		G.addAresta("Maria", "Jose", 102.0);
		G.addAresta("Maria", "Silla", 103.0);
		G.addAresta("Maria", "Pepe", 104.0);
		G.addAresta("Silla", "Jose", 103.0);
		G.addAresta("Silla", "Pepe", 104.0);
		G.addAresta("Ana", "Miguel",65151652.516);
		HashSet<String> ms = new HashSet<String>();
		ms.add("Maria");
		ms.add("Silla");
		assertEquals(720.0, G.sumaPesosAdjacentsInclusiva(ms), 0.0001);
		assertEquals(514.0, G.sumaPesosAdjacentsExclusiva(ms), 0.0001);
	}
	
	

}
