package com.example.myProject;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;

public class CronJob extends HttpServlet {

	private static final long serialVersionUID = -1036441233637180740L;
	Logger logger = Logger.getLogger(CronJob.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CronCounter counterDao = new CronCounter();
		Entity counter = counterDao.setTimeAndCount();
		logger.info("cron job. Count is: " + counter.getProperty("count"));
	}

}
