package br.com.caelum.xstream;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
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
				"      <descri��o>geladeira duas portas</descri��o>\n"	+ 
				"    </produto>\n" + 
				"    <produto codigo=\"1588\">\n"	+ 
				"      <nome>ferro de passar</nome>\n" + 
				"      <preco>100.0</preco>\n" + 
				"      <descri��o>ferro com vaporizador</descri��o>\n"	+ 
				"    </produto>\n" + 
				"  </produtos>\n" +
				"</compra>";
		
			XStream xstream = xstreamParaCompraEProduto();
			
			String xmlGerado = xstream.toXML(compra);
			assertEquals(resultadoEsperado, xmlGerado);
			
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
				"      <descri��o>geladeira duas portas</descri��o>\n"	+ 
				"    </produto>\n" + 
				"    <produto codigo=\"1588\">\n"	+ 
				"      <nome>ferro de passar</nome>\n" + 
				"      <preco>100.0</preco>\n" + 
				"      <descri��o>ferro com vaporizador</descri��o>\n"	+ 
				"    </produto>\n" + 
				"  </produtos>\n" +
				"</compra>";

		Compra compraEsperada = compraDeGeladeiraEFerro();
		
		XStream xstream = xstreamParaCompraEProduto();
		
		Compra compraResultado = (Compra) xstream.fromXML(xmlDeOrigem);
		assertEquals(compraEsperada, compraResultado);
		
	}

	@Test
	public void deveSerializarDuasGeladeirasIguais() {
		
		
		String resultadoEsperado = "<compra>\n" 
	            + "  <id>15</id>\n"
	            + "  <produtos>\n" 
	            + "    <produto codigo=\"1587\">\n"
	            + "      <nome>geladeira</nome>\n"
	            + "      <preco>1000.0</preco>\n"
	            + "      <descri��o>geladeira duas portas</descri��o>\n"
	            + "    </produto>\n"
	            + "    <produto codigo=\"1587\">\n"
	            + "      <nome>geladeira</nome>\n"
	            + "      <preco>1000.0</preco>\n"
	            + "      <descri��o>geladeira duas portas</descri��o>\n"
	            + "    </produto>\n"
	            + "  </produtos>\n" 
	            + "</compra>";
		
		Compra compra = compraDuasGeladeirasIguais();
		
		XStream xstream = xstreamParaCompraEProduto();
		xstream.setMode(XStream.NO_REFERENCES);
		String resultadoGerado = xstream.toXML(compra);
		
		assertEquals(resultadoEsperado, resultadoGerado);
		
	}

	@Test
	public void deveSerializarColecaoImplicita() {
		String resultadoEsperado = 
				"<compra>\n" +
				"  <id>15</id>\n" +
				"  <produto codigo=\"1587\">\n"	+ 
				"    <nome>geladeira</nome>\n" + 
				"    <preco>1000.0</preco>\n"	+ 
				"    <descri��o>geladeira duas portas</descri��o>\n"	+ 
				"  </produto>\n" + 
				"  <produto codigo=\"1588\">\n"	+ 
				"    <nome>ferro de passar</nome>\n" + 
				"    <preco>100.0</preco>\n" + 
				"    <descri��o>ferro com vaporizador</descri��o>\n"	+ 
				"  </produto>\n" + 
				"</compra>";

		Compra compra = compraDeGeladeiraEFerro();
		
		XStream xstream = xstreamParaCompraEProduto();
		xstream.addImplicitCollection(Compra.class, "produtos");
		
		String xmlGerado = xstream.toXML(compra);

		assertEquals(resultadoEsperado, xmlGerado);

	}

	@Test
	public void deveSerializarLivroEMusica() {
		String resultadoEsperado = "<compra>\n" 
	            + "  <id>15</id>\n"
	            + "  <produtos class=\"linked-list\">\n" 
	            + "    <livro codigo=\"1589\">\n"
	            + "      <nome>O P�ssaro Raro</nome>\n"
	            + "      <preco>100.0</preco>\n"
	            + "      <descri��o>dez hist�rias</descri��o>\n"
	            + "    </livro>\n"
	            + "    <musica codigo=\"1590\">\n"
	            + "      <nome>Meu Passeio</nome>\n"
	            + "      <preco>100.0</preco>\n"
	            + "      <descri��o>m�sica</descri��o>\n"
	            + "    </musica>\n"
	            + "  </produtos>\n" 
	            + "</compra>";

		Compra compra = compraDeLivroEMusica();
		XStream xstream = xstreamParaCompraEProduto();
		String xmlGerado = xstream.toXML(compra);
		assertEquals(resultadoEsperado, xmlGerado);
// APESAR DO EXERC�CIO EST� IGUAL AO DO V�DEO, N�O EST� GERANDO CORRETAMENTE
// O XML COM HERAN�A		
	}
	
	private XStream xstreamParaCompraEProduto() {
		XStream xstream = new XStream();
		xstream.alias("produto", Produto.class);
		xstream.alias("compra", Compra.class);
		xstream.alias("livro", Livro.class);
		xstream.alias("musica", Musica.class);
		xstream.aliasField("descri��o", Produto.class, "descricao");
		xstream.useAttributeFor(Produto.class, "codigo");
		return xstream;
	}
	
	private Compra compraDeLivroEMusica() {
		Produto livro = new Livro("O P�ssaro Raro", 100.0, "dez hist�rias", 1589);
		Produto musica = new Musica("Meu Passeio", 100.0, "m�sica", 1590);

		List<Produto> produtos = new LinkedList<Produto>();
		produtos.add(livro);
		produtos.add(musica);
		
		return new Compra(15, produtos);
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
	
	private Compra compraDuasGeladeirasIguais() {
		Produto geladeira = geladeira();
		
		List<Produto> produtos = new ArrayList<>();
		produtos.add(geladeira);
		produtos.add(geladeira);
		
		Compra compra = new Compra(15, produtos);
		return compra;
	}
}
