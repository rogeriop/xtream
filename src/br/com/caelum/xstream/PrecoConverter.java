package br.com.caelum.xstream;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.SingleValueConverter;

public class PrecoConverter implements SingleValueConverter {

	@Override
	public boolean canConvert(Class tipo) {
		return tipo.isAssignableFrom(Double.class);
	}

	@Override
	public Object fromString(String valor) {
		Locale brasil = new Locale("pt", "br");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(brasil);
		try {
			return formatter.parse(valor);
		} catch (ParseException e) {
			throw new ConversionException("Valor inválido " + valor, e);
		}
	}

	@Override
	public String toString(Object valor) {
		Locale brasil = new Locale("pt", "br");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(brasil);
		return formatter.format(valor);
	}

}
