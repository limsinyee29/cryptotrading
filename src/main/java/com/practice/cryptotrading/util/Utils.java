package com.practice.cryptotrading.util;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author Sin Yee
 *
 */
public class Utils {
	
	public static Timestamp getCurrentTimeStamp() {
		return Timestamp.from(Instant.now());
	}

}
