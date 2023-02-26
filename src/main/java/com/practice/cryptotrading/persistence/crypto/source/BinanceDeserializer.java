package com.practice.cryptotrading.persistence.crypto.source;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author Sin Yee
 *
 */
public class BinanceDeserializer extends StdDeserializer<Binance> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected BinanceDeserializer() {
		super(Binance.class);
	}

	protected BinanceDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Binance deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		JsonNode node = p.getCodec().readTree(p);
		return new Binance(node.get("symbol").asText(), Double.parseDouble(node.get("bidPrice").asText()),
				Double.parseDouble(node.get("askPrice").asText()));
	}

}
