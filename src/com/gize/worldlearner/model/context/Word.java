package com.gize.worldlearner.model.context;

public class Word implements Comparable<Word>{

	private String word;
	private Integer order;
	private boolean isHidden;
	private String meaning;

	public Word(String word, Integer order, boolean isHidden, String meaning) {
		this.word = word;
		this.order = order;
		this.isHidden = isHidden;
		this.meaning = meaning;
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public boolean isHidden() {
		return isHidden;
	}
	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	@Override
	public int compareTo(Word otherWord) {
		return this.getOrder().compareTo(otherWord.getOrder());
	}
	
}