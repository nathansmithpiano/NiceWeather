package com.niceweatherjpa.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestClientJPA {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	static {
		emf = Persistence.createEntityManagerFactory("NiceWeatherJPA");
	}

	public static void main(String[] args) {
		TestClientJPA app = new TestClientJPA();
		app.run();
		app.close();
	}
	
	private void run() {
		
	}
	
	private void close() {
		// no memory leaks
		emf.close();
	}

}
