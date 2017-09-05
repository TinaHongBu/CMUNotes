/* 
Check if a string has all unique characters
*/

// Use hash table

boolean ifUniqueChar(String str) {
	if(str.length() > 128) return false;
	boolean[] array = new boolean[128];
	for (int i = 0; i < str.length(); i++) {
		int val = str.charAt(i);
		if(array[val]) return false;
		array[val] = true;
	}
	return true;
}

// if cannot use data structures -> bit vector

// if can change input string -> first sort  in O(nlogn) then compare neighboring characters
boolean ifUniqueChar(String str) {
	if(str.length() > 128) return false;
	// sort the string first

	// compare neighboring characters
	for (int i = 0; i < str.length(); i++) {
		if(str.charAt(i) = str.charAt(i + 1)) return false;
	}
	return true;
}