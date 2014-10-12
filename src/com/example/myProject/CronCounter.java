package com.example.myProject;

import java.util.Calendar;
import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class CronCounter {
	DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	Entity cronCounter = new Entity("cronCounter", "cronCounterKey");

	void setTime() {
		Date currentTime = Calendar.getInstance().getTime();
		cronCounter.setProperty("updatedTime", currentTime);
		ds.put(cronCounter);
	}

	void increaseCount(int currentCount) {
		int newCount = (int) cronCounter.getProperty("count") + 1;
		cronCounter.setProperty("count", newCount);
	}

	int getCurrentCount() {

		int current = (int) cronCounter.getProperty("count");
		return current;
	}
}