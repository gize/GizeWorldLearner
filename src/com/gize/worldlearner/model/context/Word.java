package com.gize.worldlearner.model.context;

public class Word implements Comparable<Word>{

	private String word;
	private Integer order;
	private boolean isHidden;
	
	public Word(String word, Integer order, boolean isHidden) {
		this.word = word;
		this.order = order;
		this.isHidden = isHidden;
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

	@Override
	public int compareTo(Word otherWord) {
		return this.getOrder().compareTo(otherWord.getOrder());
	}
	
}