package com.gize.worldlearner.model.service.context;

import java.util.List;

import org.springframework.ui.Model;

import com.gize.worldlearner.model.context.Word;

public interface IContextService {

	boolean existsContext(String contextname);

	List<String> getContextList();

	void saveNewContext(String contextName, List<String> arrVocabularyList,
			List<Integer> arrSelectedIndices, List<String> meaningArray);

	List<Word> getWordsForContext(String context);

	List<Integer> createListSelectedIndices(List<Word> words);
	
	void deleteContext(String contextname);

	List<String> createListMeanings(List<Word> words);

	void setModelForContext(String context, Model model);
}