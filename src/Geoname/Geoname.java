package Geoname;

import java.util.ArrayList;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import Graph.Graph;
import Graph.Node;
import Utilities.Pair;

public class Geoname {
	
	private final static double R = 6371;
	
	public void getLatLong(Graph graph) throws Exception{
		WebService.setUserName("ee09063");
		
		ArrayList<Node> list = graph.getNodes();
		
		for(Node n : list){
			ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
			searchCriteria.setQ(n.getAlt());
			ToponymSearchResult searchResult = WebService.search(searchCriteria);
			for (Toponym toponym : searchResult.getToponyms()) {
				//System.out.println(toponym.getName()+" "+ toponym.getCountryName());
				//System.out.println(toponym.getLatitude() + " " +  toponym.getLongitude());
				Pair<Double, Double> coord = LatLongToXY(toponym.getLatitude(), toponym.getLongitude());
				n.setLatitude(toponym.getLatitude());
				n.setLongitude(toponym.getLongitude());
				n.setLatLongX(coord.getSecond());
				n.setLatLongY(coord.getFirst());
				//System.out.println("CONVERSION OF " + n.getName() + " " + n.getLatLongX() + " " + n.getLatLongY());
				break;
			}
		}
		
		graph.setDisplaceX(graph.getNodes().get(0).getLatLongX());
		graph.setDisplaceY(graph.getNodes().get(0).getLatLongY());
		graph.displaceNodes();
	}
	
	public static Pair<Double, Double> LatLongToXY(double Lat, double Long){
		double lat = toRadian(Lat);
		double lon = toRadian(Long);
		/*double lon = Long;
		double lat = Lat;*/
		double x = R * java.lang.Math.cos(lat) * java.lang.Math.cos(lon);
		double y = R * java.lang.Math.cos(lat) * java.lang.Math.sin(lon);
		Pair<Double, Double> coord = new Pair<Double, Double>(x,y);
		return coord;
	}
	
	public static double toRadian(double value){
		return value * (Math.PI / 180.0);
	}
}

/*
ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
searchCriteria.setQ("Saint James Spa France");
ToponymSearchResult searchResult = WebService.search(searchCriteria);
for (Toponym toponym : searchResult.getToponyms()) {
	//System.out.println(toponym.getName()+" "+ toponym.getCountryName());
	//System.out.println(toponym.getLatitude() + " " +  toponym.getLongitude());
	Pair<Double, Double> coord = LatLongToXY(toponym.getLatitude(), toponym.getLongitude());
	System.out.println(coord.getFirst() + " " + coord.getSecond());
}*/