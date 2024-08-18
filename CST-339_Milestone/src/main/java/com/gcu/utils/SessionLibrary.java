package com.gcu.utils;

import org.springframework.security.core.Authentication;

public class SessionLibrary
{
	public static String getUsername(Authentication auth)
	{
		if (auth == null || !auth.isAuthenticated())
			return null;
		
		return FormatCasing(auth.getName());
	}
	
	/**
	 * Capitalizes the first letter of the given text
	 * @param input The text to format
	 * @return The formatted text
	 */
	private static String FormatCasing(String input)
	{
		if (input.length() == 0) return input;
		if (input.length() == 1) return input.toUpperCase();
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}
}