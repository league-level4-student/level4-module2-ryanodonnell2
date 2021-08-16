package StringMethods;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if(s1.length()>s2.length()) {
			return s1;
		}
		else {
			return s2;
		}
	}

	
	// if String s contains the word "underscores", change all of the spaces to underscores
	public static String formatSpaces(String s) {
		if(s.contains("underscores")) {
			return s.replace(' ', '_');
		}
		return s;
	}

	
	// Return the name of the person whose LAST name would appear first if they were in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		char lastS1;
		char lastS2;
		char lastS3;
		s1 = s1.replaceAll("\\s", "");
		s1 = s1.substring(0,s1.length()-1)+" "+s1.charAt(s1.length()-1);
		lastS1 = s1.charAt(s1.length()-1);
		s2 = s2.replaceAll("\\s", "");
		s2 = s2.substring(0,s2.length()-1)+" "+s2.charAt(s2.length()-1);
		lastS2 = s2.charAt(s2.length()-1);
		s3 = s3.replaceAll("\\s", "");
		s3 = s3.substring(0,s3.length()-1)+" "+s3.charAt(s3.length()-1);
		lastS3 = s3.charAt(s3.length()-1);
		if(lastS1<lastS2&&lastS1<lastS3) {
			return s1;
		}
		else if(lastS2<lastS1&&lastS2<lastS3) {
			return s2;
		}
		else if(lastS3<lastS1&&lastS3<lastS2) {
			return s3;
		}
		else {
			return null;
		}
	}
	
	
	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			if(Character.getNumericValue(s.charAt(i))!=-1&&Character.isDigit(s.charAt(i))) {
				sum+=Character.getNumericValue(s.charAt(i));
			}
		}
		return sum;
	}
	
	
	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int lengthOfS = s.length();
		int times = 0;
		for (int i = 0; i < lengthOfS; i++) {
			if(s.replaceFirst(substring, "")!=s) {
				times++;
				lengthOfS = s.length();
				s = s.replaceFirst(substring, "");
			}
			else {
				lengthOfS = 0;
			}
		}
		return times;
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) throws UnsupportedEncodingException {
		return Utilities.encrypt(s.getBytes("UTF-8"), (byte) key);
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		return Utilities.decrypt(s, (byte) key);
	}


	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		int lastSpace = 0;
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i)==' ') {
				String temp = s.substring(lastSpace, i);
				lastSpace = i;
				if(temp.endsWith(substring)) {
					count++;
				}
			}
		}
		return count;
	}
	

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		int firstIndex = s.indexOf(substring);
		int lastIndex = s.lastIndexOf(substring);
		return lastIndex-firstIndex-substring.length();
	}


	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		s = s.toLowerCase();
		String trueString = "";
		for (int i = 0; i < s.length(); i++) {
			if(Character.isLetter(s.charAt(i))) {
				trueString+=s.charAt(i);
			}
		}
		System.out.println(trueString);
		for (int i = 0; i < trueString.length()/2-1; i++) {
			System.out.println(trueString.charAt(i));
			System.out.println(trueString.charAt(trueString.length()-i-1));
		}
		for (int i = 0; i < trueString.length()/2-1; i++) {
			if(!(trueString.charAt(i)==trueString.charAt(trueString.length()-i-1))) {
				return false;
			}
		}
		return true;
	}
	
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
