package Graph;

public class Node implements Comparable<Node>{
	
	private String name;
	private String alt;
	private int visitingTime;
	private int importance;
	protected boolean visited;
	protected boolean paintNode;
	protected Node parent;
	
	protected double x;
	protected double y;
	protected double a;
	
	private float futureCost;//H
	private float currentCost;//G
	private float totalTimeSpent;
	
	private double latitude;
	private double longitude;
	private double latLongX;
	private double latLongY;
	
	public Node(String name, String alt, int time_cost, int importance){
		this.name = name;
		this.alt = alt;
		this.setVisitingTime(time_cost);
		this.importance = -importance;
		this.parent = null;
		this.currentCost=0;
		this.futureCost=0;
		this.paintNode = false;
		this.totalTimeSpent = 0;
		this.visited=false;
		this.latitude = 0;
		this.longitude = 0;
		this.latLongX = 0;
		this.latLongY = 0;
	}
	
	public boolean Visited(){
		return this.visited;
	}
	
	public void setVisited(boolean visited){
		this.visited = visited;
	}
	
	public void setG(float currentCost){
		this.currentCost=currentCost;
	}
	
	public void setH(float futureCost){
		this.futureCost=futureCost;
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
		return this.currentCost + this.futureCost;
	}
	
	public float getH(){
		return this.futureCost;
	}
	
	public float getG(){
		return this.currentCost;
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

	public float getTotalTimeSpent() {
		return totalTimeSpent;
	}

	public void setTotalTimeSpent(float totalTimeSpent) {
		this.totalTimeSpent = totalTimeSpent;
	}

	public int getVisitingTime() {
		return visitingTime;
	}

	public void setVisitingTime(int visitingTime) {
		this.visitingTime = visitingTime;
	}
	
	public double getA(){
		return this.a;
	}
	
	public void setA(double a){
		this.a = a;
	}
	
	public void setImportance(int i){
		this.importance = i;
	}
}
