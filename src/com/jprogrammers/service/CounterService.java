package com.jprogrammers.service;

import com.jprogrammers.model.Counter;

public class CounterService extends GenericDAOImpl<Counter> {

	static GenericDAOImpl<Counter> counterDao;
	
	static{
		counterDao = new GenericDAOImpl<Counter>();
	}
	
	public static long increment() {
		
		String query = "FROM Counter where name = '" + Counter.defaultCounter + "'";
		
		Counter counter = counterDao.findOne(query);
		
		long currentId = 1;
		
		if (counter == null) {
			
			counter = new Counter();
			
			counter.setName(Counter.defaultCounter);
			counter.setCurrentId(2L);
		} else {
			currentId = counter.getCurrentId();
			counter.setCurrentId(currentId + 1);
		}
		
		counterDao.save(counter);
		
		return currentId;
	}
	
}
