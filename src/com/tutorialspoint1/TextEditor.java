package com.tutorialspoint1;

public class TextEditor {
	private SpellChecker spellChecker1;

	 
	   public SpellChecker getSpellChecker1() {
		return spellChecker1;
	}
	public void setSpellChecker1(SpellChecker spellChecker1) {
		this.spellChecker1 = spellChecker1;
	}
	
	
	public void spellCheck() {
	      spellChecker1.checkSpelling();
	   }

}
