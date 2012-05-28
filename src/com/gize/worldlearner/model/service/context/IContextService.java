package com.gize.worldlearner.model.service.context;

import java.util.List;

import com.gize.worldlearner.model.context.Word;

public interface IContextService {

	boolean existsContext(String contextname);

	List<String> getContextList();

	void saveNewContext(String contextName, List<String> arrVocabularyList,
			List<Integer> arrSelectedIndices);

	List<Word> getWordsForContext(String context);

	List<Integer> createListSelectedIndices(List<Word> words);
	
	void deleteContext(String contextname);
}