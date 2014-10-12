package com.example.myProject;

import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class Counter {
	void increaseCountBy1() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		Entity count = new Entity("count");

		count.setProperty("firstName", "Antonio");
		count.setProperty("lastName", "Salieri");

		Date hireDate = new Date();
		count.setProperty("hireDate", hireDate);

		count.setProperty("attendedHrTraining", true);

		ds.put(count);
	}
}
