package br.com.caelum.xstream;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.TreeMarshaller.CircularReferenceException;

public class CategoriaTest {

	@Test
	public void deveSerializarUmCiclico() {
		
		String xmlEsperado = "<categoria id=\"1\">\n" +
	            "  <pai id=\"2\">\n" +
	            "    <pai id=\"3\">\n" +
	            "      <pai reference=\"1\"/>\n" +
	            "      <nome>futebol</nome>\n" +
	            "    </pai>\n" +
	            "    <nome>geral</nome>\n" +
	            "  </pai>\n" +
	            "  <nome>esporte</nome>\n" +
	            "</categoria>";
		Categoria esporte = new Categoria(null, "esporte");
		Categoria futebol = new Categoria(esporte, "futebol");
		Categoria geral = new Categoria(futebol, "geral");
		esporte.setPai(geral);
		
		XStream xstream = new XStream();
		xstream.alias("categoria", Categoria.class);
		xstream.setMode(XStream.ID_REFERENCES);
		
		String xmlGerado = xstream.toXML(esporte);
		
		assertEquals(xmlEsperado, xmlGerado);
		
				
	}

	@Test(expected=CircularReferenceException.class)
	public void naoDeveSerializarUmCiclico() {
		
		String xmlEsperado = "<categoria id=\"1\">\n" +
	            "  <pai id=\"2\">\n" +
	            "    <pai id=\"3\">\n" +
	            "      <pai reference=\"1\"/>\n" +
	            "      <nome>futebol</nome>\n" +
	            "    </pai>\n" +
	            "    <nome>geral</nome>\n" +
	            "  </pai>\n" +
	            "  <nome>esporte</nome>\n" +
	            "</categoria>";
		Categoria esporte = new Categoria(null, "esporte");
		Categoria futebol = new Categoria(esporte, "futebol");
		Categoria geral = new Categoria(futebol, "geral");
		esporte.setPai(geral);
		
		XStream xstream = new XStream();
		xstream.alias("categoria", Categoria.class);
		xstream.setMode(XStream.NO_REFERENCES);
		
		String xmlGerado = xstream.toXML(esporte);
		
		assertEquals(xmlEsperado, xmlGerado);
		
				
	}
	
	
}
