package Graph;

import java.util.ArrayList;

import Utilities.Cost;
import Utilities.Pair;

public class Node implements Comparable<Node>{
	
	private String name;
	private String alt;
	private int time_cost;
	private int importance;
	protected boolean visited;
	protected boolean enjoyed;
	protected boolean paintNode;
	protected Node parent;
	private ArrayList<Pair<Node,Cost>> neighbours = new ArrayList<Pair<Node,Cost>>();
	
	protected double x;
	protected double y;
	
	private float heuristic;//H
	private float cost;//G
	
	private double latitude;
	private double longitude;
	private double latLongX;
	private double latLongY;
	/*
	public Node(String name, int time_cost, int importance, double x, double y){
		this.name = name;
		this.time_cost = time_cost;
		this.importance = importance;
		this.x = x;
		this.y = y;
		this.parent = null;
		this.cost=0;
		this.heuristic=0;
		this.paintNode = false;
	}*/
	
	public Node(String name, String alt, int time_cost, int importance){
		this.name = name;
		this.alt = alt;
		this.time_cost = time_cost;
		this.importance = importance;
		this.parent = null;
		this.cost=0;
		this.heuristic=0;
		this.paintNode = false;
	}
	
	public boolean isVisited(){
		return this.visited;
	}
	
	public void visit(){
		this.visited = true;
	}
	
	public void unvisit(){
		this.visited = false;
	}
	
	public boolean isEnjoyed(){
		return this.enjoyed;
	}
	
	public void enjoy(){
		this.enjoyed = true;
	}
	
	public int getTimeCost(){
		return this.time_cost;
	}
	
	public void setG(float neighborCost){
		this.cost=neighborCost;
	}
	
	public void setH(float heuristic){
		this.heuristic=heuristic;
	}
	
	public int getImportance(){
		return this.importance;
	}
	
	public String toString(){
		return this.name;
	}

	public void setParent(Node parent){
		this.parent = parent;
	}
	
	public Node getParent(){
		return this.parent;
	}
	
	public String getName(){
		return this.name;
	}

	public void addNeighbour(Node n, Cost c){
		this.neighbours.add(new Pair<Node,Cost>(n,c));
	}
	
	public ArrayList<Pair<Node,Cost>> getNeighbourList(){
		return this.neighbours;
	}
	
	public void setNeighbourList(ArrayList<Pair<Node,Cost>> list){
		this.neighbours = list;
	}
	
	public void setPaintNode(boolean b){
		this.paintNode = b;
	}
	
	public boolean getPaintNode(){
		return this.paintNode;
	}
	
	public int getX(){return (int) this.x;}
	
	public int getY(){return (int) this.y;}

	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public float getF(){
		return this.heuristic + this.cost;
	}
	
	public float getH(){
		return this.heuristic;
	}
	
	public float getG(){
		return this.cost;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public int compareTo(Node n) {
		if(this.name.equals(n.getName())){
			return 1;
		}
		return 0;
	}

	public double getLatLongX() {
		return latLongX;
	}

	public void setLatLongX(double latLongX) {
		this.latLongX = latLongX;
	}

	public double getLatLongY() {
		return latLongY;
	}

	public void setLatLongY(double latLongY) {
		this.latLongY = latLongY;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}
}
