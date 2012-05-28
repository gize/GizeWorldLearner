package com.gize.worldlearner.model.service.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.gize.worldlearner.Const;
import com.gize.worldlearner.model.context.Word;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;


@Component
@Qualifier("contextService")
public class ContextService implements IContextService{

	
	@Override
	public boolean existsContext(String contextname) {
		
		return getKeyForContext(contextname)!= null;
	}
	
	public void deleteContext(String contextname) {
		
		Key key = getKeyForContext(contextname);
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Transaction txn = datastore.beginTransaction();
		try {

		    datastore.delete(key);

		    txn.commit();
		} finally {
		    if (txn.isActive()) {
		        txn.rollback();
		    }
		}
	}
	
	private Key getKeyForContext(String contextname) {

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query(Const.ENTITY_CONTEXT);
		q.addFilter(Const.ENTITY_PROPERTY_CONTEXT, Query.FilterOperator.EQUAL, contextname);
		q.setKeysOnly();
		PreparedQuery pq = datastore.prepare(q);
		
		Key key = null;
		if( pq.asIterator().hasNext() ){
			key = pq.asIterator().next().getKey();
		} 
		
		return key;
	}

	@Override
	public List<String> getContextList() {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query(Const.ENTITY_CONTEXT);
		PreparedQuery pq = datastore.prepare(q);
		
		List<String> listContext = new ArrayList<String>();
		for (Entity result : pq.asIterable()) {
			
			listContext.add((String)result.getProperty(Const.ENTITY_PROPERTY_CONTEXT));
		}
		
		return listContext;
	}

	@Override
	public void saveNewContext(String contextName, List<String> arrVocabularyList,
			List<Integer> arrSelectedIndices) {


		Entity context = createNewContext(contextName);
		List<Entity> words = createWordEntities( context, arrVocabularyList, arrSelectedIndices);
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		
		Transaction txn = datastore.beginTransaction();
		try {

		    datastore.put(context);
		    for (Entity word : words) {
			    datastore.put(word);
			}

		    txn.commit();
		} finally {
		    if (txn.isActive()) {
		        txn.rollback();
		    }
		}
		
	}
	
	private Entity createNewContext(String contextName){
		
		Entity context = new Entity(Const.ENTITY_CONTEXT, contextName);
		//TODO Check if necessary
		context.setProperty(Const.ENTITY_PROPERTY_CONTEXT, contextName);

		return context;
	}

	private List<Entity> createWordEntities(Entity context, List<String> listVocabularyList,
				List<Integer> listSelectedIndices){
		
		int wordOrder = 0;
		List<Entity> words = new ArrayList<Entity>();
		for (String vocab : listVocabularyList) {
		
			words.add( createWord(context, vocab, wordOrder, listSelectedIndices.contains(wordOrder)) );
			wordOrder++;
		}
		
		return words;
	}
		
	private Entity createWord(Entity context, String vocab, int wordOrder, boolean isHidden){
		Entity word = new Entity(Const.ENTITY_WORD, context.getKey());
		word.setProperty(Const.ENTITY_PROPERTY_WORD, vocab);
		word.setProperty(Const.ENTITY_PROPERTY_WORD_HIDDEN, isHidden);
		word.setProperty(Const.ENTITY_PROPERTY_WORD_ORDER, wordOrder);
		return word;
	}

	
	@Override
	public List<Word> getWordsForContext(String context) {
		
		Key key = getKeyForContext(context);
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query(Const.ENTITY_WORD, key);
		PreparedQuery pq = datastore.prepare(q);
		
		List<Word> listWord = new ArrayList<Word>();
		for (Entity result : pq.asIterable()) {
			String w = (String) result.getProperty(Const.ENTITY_PROPERTY_WORD);
			int order = ((Long) result.getProperty(Const.ENTITY_PROPERTY_WORD_ORDER)).intValue();
			boolean isHidden = (Boolean) result.getProperty(Const.ENTITY_PROPERTY_WORD_HIDDEN);
			Word word = new Word(w, order, isHidden);
			listWord.add(word);
		}
		
		Collections.sort(listWord);
		return listWord;
	}

	@Override
	public List<Integer> createListSelectedIndices(List<Word> words) {
		
		List<Integer> listSelectedIndices = new ArrayList<Integer>();
		for(Word word : words){
			if(word.isHidden()){
				listSelectedIndices.add(word.getOrder());
			}
		}
		
		return listSelectedIndices;
	}
}