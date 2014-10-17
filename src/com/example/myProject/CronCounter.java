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

	void setTimeAndCount() throws EntityNotFoundException {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey("cronCounter", Integer.parseInt("123"));
		Entity cronCounter = ds.get(key);

		Date currentTime = Calendar.getInstance().getTime();
		cronCounter.setProperty("updatedTime", currentTime);
		Long current = (Long) cronCounter.getProperty("count");
		cronCounter.setProperty("count", current++);
		ds.put(cronCounter);
		logger.info("cronCounter time: "
				+ cronCounter.getProperty("updatedTime"));
		logger.info("cronCounter count: " + cronCounter.getProperty("count"));
	}
}