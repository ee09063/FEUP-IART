package Utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Geoname.Geoname;
import Graph.Graph;
import Graph.Node;

public class LatLongLoader {
	
	private final static double R = 6371;
	
	public LatLongLoader(){
		
	}
	
	public void getLatLong(Graph graph){
		ArrayList<Node> list = graph.getNodes();
		loadFromDB(list);
		loadFromGeonames(list);
		//graph.setDisplaceX(graph.getNodes().get(0).getLatLongX());
		//graph.setDisplaceY(graph.getNodes().get(0).getLatLongY());
		getExtrNodes(graph);
		graph.displaceNodes();
	}
	
	private static void loadFromDB(ArrayList<Node> list){
		File file = new File("resources/latlongDB.txt");
		for(Node n : list){
			try(BufferedReader reader = new BufferedReader(new FileReader(file))){
				for(String line; (line = reader.readLine()) != null; ){
					String info[] = line.split(Pattern.quote("|"));
					if((info[0].trim()).equals(n.getAlt())){
						double lat = Double.parseDouble(info[1].trim());
						double lon = Double.parseDouble(info[2].trim());
						Pair<Double,Double> coord = LatLongToXY(lat, lon);
						n.setLatitude(lat);
						n.setLongitude(lon);
						n.setLatLongX(coord.getSecond());
						n.setLatLongY(coord.getFirst());
						break;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void loadFromGeonames(ArrayList<Node> list){
		for(Node n : list){
			if(n.getLatitude() == 0 && n.getLongitude() == 0){
				Geoname geoname = new Geoname();
				geoname.getLatLong(n);
				addToDB(n);
			}
		}
	}
	
	private static  void addToDB(Node n){
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("resources\\latlongDB.txt", true)))) {
		    out.print("\n" + n.getName() + " | " + n.getAlt() + " | " + n.getLatitude() + " | " + n.getLongitude());
		}catch (IOException e) {
		    
		}
	}
	
	public static Pair<Double, Double> LatLongToXY(double Lat, double Long){
		double lat = toRadian(Lat);
		double lon = toRadian(Long);
		double x = R * java.lang.Math.cos(lat) * java.lang.Math.cos(lon);
		double y = R * java.lang.Math.cos(lat) * java.lang.Math.sin(lon);
		Pair<Double, Double> coord = new Pair<Double, Double>(x,y);
		return coord;
	}
	
	public static double toRadian(double value){
		return value * (Math.PI / 180.0);
	}
	
	public static void getExtrNodes(Graph g){
		Node upExtr = g.getNodes().get(0);
		Node downExtr = g.getNodes().get(0);
		Node leftExtr = g.getNodes().get(0);
		Node rightExtr = g.getNodes().get(0);
		
		for(Node n : g.getNodes()){
			if(n.getLatLongY() > upExtr.getLatLongY()){
				upExtr = n;
			}
			if(n.getLatLongY() < downExtr.getLatLongY()){
				downExtr = n;
			}
			if(n.getLatLongX() > rightExtr.getLatLongX()){
				rightExtr = n;
			}
			if(n.getLatLongX() < leftExtr.getLatLongX()){
				leftExtr = n;
			}
		}
		
		double dx = (rightExtr.getLatLongX() + leftExtr.getLatLongX())/2.0;
		double dy = (upExtr.getLatLongY() + downExtr.getLatLongY())/2.0;
		
		g.setDisplaceX(dx);
		g.setDisplaceY(dy);
	}
	
}
