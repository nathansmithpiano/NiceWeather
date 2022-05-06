package com.niceweatherjpa.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.niceweatherjpa.entities.RelativeLocation;

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
		em = emf.createEntityManager();

//		Category category = em.find(Category.class, 1);
//		System.out.println(category);

//		Coordinate coordinate = em.find(Coordinate.class, 1);
//		System.out.println(coordinate);

//		Forecast forecast = em.find(Forecast.class, 1);
//		System.out.println(forecast);

//		Geometry geometry = em.find(Geometry.class, 1);
//		System.out.println(geometry);

//		Location location = em.find(Location.class, 1);
//		System.out.println(location);

//		MountainRange mountainRange = em.find(MountainRange.class, 1);
//		System.out.println(mountainRange);

//		Period period = em.find(Period.class, 1);
//		System.out.println(period);

//		Point point = em.find(Point.class, 1);
//		System.out.println(point);

//		RelativeLocation relativeLocation = em.find(RelativeLocation.class, 1);
//		System.out.println(relativeLocation);
		
		em.close();
	}

	private void close() {
		// no memory leaks
		emf.close();
	}

}
