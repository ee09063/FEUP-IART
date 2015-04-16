package Logic;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import Utilities.Pair;

public class Tourist {
	
/*	
	
	public static void main(String args[]) throws Exception{
		//Graph g = new Graph(filename);
		WebService.setUserName("ee09063"); // add your username here
		 
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		searchCriteria.setQ("Saint James Spa France");
		ToponymSearchResult searchResult = WebService.search(searchCriteria);
		for (Toponym toponym : searchResult.getToponyms()) {
			System.out.println(toponym.getName()+" "+ toponym.getCountryName());
			System.out.println(toponym.getLatitude() + " " +  toponym.getLongitude());
			Pair<Double, Double> coord = LatLongToXY(toponym.getLatitude(), toponym.getLongitude());
			System.out.println(coord.getFirst() + " " + coord.getSecond());
		}
		ToponymSearchCriteria searchCriteria2 = new ToponymSearchCriteria();
		searchCriteria2.setQ("Louvre Museum Paris France");
		ToponymSearchResult searchResult2 = WebService.search(searchCriteria2);
		for (Toponym toponym : searchResult2.getToponyms()) {
			System.out.println(toponym.getName()+" "+ toponym.getCountryName());
			System.out.println(toponym.getLatitude() + " " +  toponym.getLongitude());
			Pair<Double, Double> coord = LatLongToXY(toponym.getLatitude(), toponym.getLongitude());
			System.out.println(coord.getFirst() + " " + coord.getSecond());
		}
	}
	
	*/
}
