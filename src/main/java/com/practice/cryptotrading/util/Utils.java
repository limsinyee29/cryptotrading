package com.practice.cryptotrading.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Sin Yee
 *
 */
public class Utils {

	public static ZonedDateTime getCurrentTimeStamp() {
		Instant nowUtc = Instant.now();
		ZoneId asiaSingapore = ZoneId.of("Asia/Singapore");
		return ZonedDateTime.ofInstant(nowUtc, asiaSingapore);
	}

}
