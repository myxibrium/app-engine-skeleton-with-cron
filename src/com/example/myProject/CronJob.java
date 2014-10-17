package com.example.myProject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.EntityNotFoundException;

public class CronJob extends HttpServlet {

	private static final long serialVersionUID = -1036441233637180740L;
	Logger logger = Logger.getLogger(CronJob.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CronCounter counter = new CronCounter();
		try {
			counter.setTimeAndCount();
		} catch (EntityNotFoundException e) {
			logger.info("entity not found, exception: " + e);
		}
		Date date = Calendar.getInstance().getTime();
		resp.getWriter().println(date);
		logger.info("hitting cron job, current time is " + date.toString());
	}

}
