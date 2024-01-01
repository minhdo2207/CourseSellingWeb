package hust.globalict.com.utils.common;

import hust.globalict.com.utils.Constants;

public class BooleanUtils {

	private BooleanUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static Boolean toBooleanObject(String c) {
		if (c == null)
			return false;
		return Constants.STATUS_YES.equalsIgnoreCase(c);
	}

	public static String toCharacterObject(Boolean b) {
		if (b == null)
			return Constants.STATUS_NO;
		return b ? Constants.STATUS_YES : Constants.STATUS_NO;
	}
}
