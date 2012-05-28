package com.gize.worldlearner.model.service.mock.context;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.gize.worldlearner.model.context.Word;
import com.gize.worldlearner.model.service.context.IContextService;

@Component
@Qualifier("contextServiceMock")
public class ContextServiceMock implements IContextService{

	@Override
	public boolean existsContext(String contextname) {
		
		if("test".equals(contextname)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<String> getContextList() {
		
		List<String> listContextName = new ArrayList<String>();
		listContextName.add("context1");
		listContextName.add("context2");
		listContextName.add("context3");
		
		return listContextName;
	}

	@Override
	public void saveNewContext(String contextName,
			List<String> arrVocabularyList, List<Integer> arrSelectedIndices) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Word> getWordsForContext(String context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> createListSelectedIndices(List<Word> words) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteContext(String contextname) {
		// TODO Auto-generated method stub
		
	}
}