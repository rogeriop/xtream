package br.com.caelum.xstream;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.xstream.Produto;

import com.thoughtworks.xstream.XStream;

public class ProdutoTest {
	
	@Test
	public void deveGerarOXmlComONomePrecoEDescricaoAdequados() {

		String resultadoEsperado = "<produto codigo=\"1587\">\n" +
         "  <nome>geladeira</nome>\n" +
         "  <preco>1000.0</preco>\n" +
         "  <descrição>geladeira duas portas</descrição>\n" +
         "</produto>";	

		Produto geladeira = new Produto("geladeira", 1000.0, "geladeira duas portas", 1587);
		
		XStream xstream = new XStream();
		xstream.useAttributeFor(Produto.class, "codigo");
		xstream.alias("produto", Produto.class);
		xstream.aliasField("descrição", Produto.class, "descricao");
		String xmlGerado = xstream.toXML(geladeira);

		assertEquals(resultadoEsperado, xmlGerado);
		
	}
}
