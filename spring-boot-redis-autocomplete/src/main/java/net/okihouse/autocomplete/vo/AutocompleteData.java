package net.okihouse.autocomplete.vo;

public class AutocompleteData implements Comparable<AutocompleteData> {

	private String value;
	private int score;

	public AutocompleteData(String value, int score) {
		this.value = value;
		this.score = score;
	}

	@Override
	public int compareTo(AutocompleteData autocompleteData) {
		return ((Integer)autocompleteData.getScore()).compareTo(this.score);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
