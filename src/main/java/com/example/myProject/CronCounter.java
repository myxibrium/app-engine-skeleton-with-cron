package com.example.myProject;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class CronCounter {
	Logger logger = Logger.getLogger(CronCounter.class.getName());

	public Entity setTimeAndCount() {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey("TransactionCounter", "chronSample");
		
		Entity cronCounter = getOrNull(datastore, key);
		if (cronCounter == null) {
			cronCounter = new Entity(key);
		}
		
		Date currentTime = Calendar.getInstance().getTime();
		cronCounter.setProperty("updatedTime", currentTime);
		Long current = longOrZero(cronCounter.getProperty("count"));
		cronCounter.setProperty("count", current+1);
		
		datastore.put(cronCounter);

		logger.info("cronCounter time: "
				+ cronCounter.getProperty("updatedTime"));
		logger.info("cronCounter count: " + cronCounter.getProperty("count"));
		return cronCounter;
	}
	
	private  <T> Long longOrZero(T value) {
		return (Long) (value == null ? new Long(0) : value);
	}
	
	private Entity getOrNull(DatastoreService ds, Key k) {
		Entity entity;
		try {
			entity = ds.get(k);
		} catch(EntityNotFoundException e) {
			entity = null;
		}
		return entity;
	}
}