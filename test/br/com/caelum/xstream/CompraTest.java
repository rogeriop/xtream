package br.com.caelum.xstream;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class CompraTest {

	@Test
	public void deveSerializarCadaUmDosProdutosDeUmaCompra() {

		Compra compra = compraDeGeladeiraEFerro();

		String resultadoEsperado = 
				"<compra>\n" +
				"  <id>15</id>\n" +
				"  <produtos>\n" +
				"    <produto codigo=\"1587\">\n"	+ 
				"      <nome>geladeira</nome>\n" + 
				"      <preco>1000.0</preco>\n"	+ 
				"      <descrição>geladeira duas portas</descrição>\n"	+ 
				"    </produto>\n" + 
				"    <produto codigo=\"1588\">\n"	+ 
				"      <nome>ferro de passar</nome>\n" + 
				"      <preco>100.0</preco>\n" + 
				"      <descrição>ferro com vaporizador</descrição>\n"	+ 
				"    </produto>\n" + 
				"  </produtos>\n" +
				"</compra>";

		
			XStream xstream = xstreamParaCompraEProduto();
			
			String xmlGerado = xstream.toXML(compra);
			assertEquals(resultadoEsperado, xmlGerado);
			
	}

	private XStream xstreamParaCompraEProduto() {
		XStream xstream = new XStream();
		xstream.alias("produto", Produto.class);
		xstream.alias("compra", Compra.class);
		xstream.aliasField("descrição", Produto.class, "descricao");
		xstream.useAttributeFor(Produto.class, "codigo");
		return xstream;
	}

	private Compra compraDeGeladeiraEFerro() {
		Produto geladeira = geladeira();
		Produto ferro = ferro();

		List<Produto> produtos = new ArrayList<>();
		produtos.add(geladeira);
		produtos.add(ferro);

		Compra compra = new Compra(15, produtos);
		return compra;
	}

	private Produto ferro() {
		return new Produto("ferro de passar", 100.0, "ferro com vaporizador", 1588);
	}

	private Produto geladeira() {
		return new Produto("geladeira", 1000.0,
				"geladeira duas portas", 1587);
	}
	
	@Test
	public void deveDeserializarCadaUmDosProdutosDeUmaCompra() {
		String xmlDeOrigem = 
				"<compra>\n" +
				"  <id>15</id>\n" +
				"  <produtos>\n" +
				"    <produto codigo=\"1587\">\n"	+ 
				"      <nome>geladeira</nome>\n" + 
				"      <preco>1000.0</preco>\n"	+ 
				"      <descrição>geladeira duas portas</descrição>\n"	+ 
				"    </produto>\n" + 
				"    <produto codigo=\"1588\">\n"	+ 
				"      <nome>ferro de passar</nome>\n" + 
				"      <preco>100.0</preco>\n" + 
				"      <descrição>ferro com vaporizador</descrição>\n"	+ 
				"    </produto>\n" + 
				"  </produtos>\n" +
				"</compra>";

		Compra compraEsperada = compraDeGeladeiraEFerro();
		
		XStream xstream = xstreamParaCompraEProduto();
		
		Compra compraResultado = (Compra) xstream.fromXML(xmlDeOrigem);
		assertEquals(compraEsperada, compraResultado);
		
		
	}
	
	
}
