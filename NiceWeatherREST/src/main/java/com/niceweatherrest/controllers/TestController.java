//package com.niceweatherrest.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.niceweatherjpa.entities.Category;
//import com.niceweatherjpa.entities.Coordinate;
//import com.niceweatherjpa.entities.Forecast;
//import com.niceweatherjpa.entities.Geometry;
//import com.niceweatherjpa.entities.Location;
//import com.niceweatherjpa.entities.MountainRange;
//import com.niceweatherjpa.entities.Period;
//import com.niceweatherjpa.entities.Point;
//import com.niceweatherjpa.entities.RelativeLocation;
//import com.niceweatherrest.services.CategoryService;
//import com.niceweatherrest.services.CoordinateService;
//import com.niceweatherrest.services.ForecastService;
//import com.niceweatherrest.services.GeometryService;
//import com.niceweatherrest.services.LocationService;
//import com.niceweatherrest.services.MountainRangeService;
//import com.niceweatherrest.services.PeriodService;
//import com.niceweatherrest.services.PointService;
//import com.niceweatherrest.services.RelativeLocationService;
//
//@RestController
//public class TestController {
//	
//	@Autowired
//	private CategoryService catSvc;
//	
//	@Autowired
//	private CoordinateService coordSvc;
//	
//	@Autowired
//	private ForecastService forcSvc;
//	
//	@Autowired
//	private GeometryService geoSvc;
//	
//	@Autowired
//	private LocationService locSvc;
//	
//	@Autowired
//	private MountainRangeService mrSvc;
//	
//	@Autowired
//	private PeriodService periodSvc;
//	
//	@Autowired
//	private PointService pointSvc;
//	
//	@Autowired
//	private RelativeLocationService rlSvc;
//	
//	private List<Category> categoryList;
////	final List<Coordinate> coordinateList = coordSvc.index();
////	final List<Forecast> forecastList;
////	final List<Geometry> geometryList;
////	final List<Location> locationList;
////	final List<MountainRange> mountainRangeList;
////	final List<Period> periodList;
////	final List<Point> pointList;
////	final List<RelativeLocation> relativeLocationList;
//	
//	
//	@GetMapping("test/count")
//	private String countAll() {
//		
//		
//		String output = trackChanges();
//		return output;
//	}
//	
//	private String trackChanges() {
//		if (categoryList == null) {
//			categoryList = catSvc.index();
//		}
//		
//		return categoryList.size() + "";
//	}
//
//}
