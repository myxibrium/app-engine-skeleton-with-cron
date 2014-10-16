package com.example.myProject;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class CronCounter {
	DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	Entity cronCounter = new Entity("cronCounter", "cronCounterKey");
	Logger logger = Logger.getLogger(CronCounter.class.getName());

	void setTimeAndCount() {
		Date currentTime = Calendar.getInstance().getTime();
		cronCounter.setProperty("updatedTime", currentTime);
		int current = (int) cronCounter.getProperty("count");
		int newCount = current++;
		cronCounter.setProperty("count", newCount);
		ds.put(cronCounter);
		logger.info("count is now " + newCount);
	}

}