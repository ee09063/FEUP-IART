package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import Graph.Graph;
import Graph.Node;
import Utilities.Path;


public class Drawing_Canvas {

	private double mouseX;
	private double mouseY;
	
	static GraphicsContext gc;
	
	Node selectedNode = null;
	
	HBox addCanvas(){
		Tourist.canvas = new Canvas(Tourist.width*0.85, Tourist.height*0.85);
		gc = Tourist.canvas.getGraphicsContext2D();
		
    	HBox outer = new HBox();
    	outer.setPadding(new Insets(10,10,10,10));
    	
    	HBox box = new HBox();
    	box.setPadding(new Insets(10,10,10,10));
    	box.setStyle("-fx-border-color: black; -fx-border-radius: 10;");
    	box.getChildren().add(Tourist.canvas);
    	
    	final ContextMenu contextMenu = new ContextMenu();
    	
    	MenuItem i1 = new MenuItem("1");
    	MenuItem i2 = new MenuItem("2");
    	MenuItem i3 = new MenuItem("3");
    	MenuItem i4 = new MenuItem("4");
    	MenuItem i5 = new MenuItem("5");
    	
    	contextMenu.getItems().addAll(i1, i2, i3, i4, i5);
    	
    	i1.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        if(selectedNode != null){
    	        	selectedNode.setImportance(-1);
    	        	selectedNode = null;
    	        	paint();
    	        }
    	    }
    	});
    	i2.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        if(selectedNode != null){
    	        	selectedNode.setImportance(-2);
    	        	selectedNode = null;
    	        	paint();
    	        }
    	    }
    	});
    	i3.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        if(selectedNode != null){
    	        	selectedNode.setImportance(-3);
    	        	selectedNode = null;
    	        	paint();
    	        }
    	    }
    	});
    	i4.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        if(selectedNode != null){
    	        	selectedNode.setImportance(-4);
    	        	selectedNode = null;
    	        	paint();
    	        }
    	    }
    	});
    	i5.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	        if(selectedNode != null){
    	        	selectedNode.setImportance(-5);
    	        	selectedNode = null;
    	        	paint();
    	        }
    	    }
    	});
    	
    	box.setOnMousePressed(new EventHandler<MouseEvent>() {
    	    @Override
    	    public void handle(MouseEvent event) {
    	        if (event.isSecondaryButtonDown()) {
    	        	Node n = isNode(event.getX(), event.getY());
    	            if(n != null){
    	            	selectedNode = n;
    	            	contextMenu.show(box, n.getX(), n.getY());
    	            }
    	        }
    	    }
    	});
    	
    	Tourist.canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
	       new EventHandler<MouseEvent>() {
	           @Override
	           public void handle(MouseEvent e) {
	        	  if(e.isPrimaryButtonDown()){
		              double deltaX = mouseX - e.getX();
		              double deltaY = mouseY - e.getY();
		              TopMenu.graph.MOUSE_DISP_X = deltaX;
		              TopMenu.graph.MOUSE_DISP_Y = deltaY;
		              TopMenu.graph.displaceNodes();
		              paint();
	        	  }
	           }
	       });
    	
    	Tourist.canvas.setOnScroll(new EventHandler<ScrollEvent>() {
    	      @Override
    	      public void handle(ScrollEvent e) {
    	    	  if(e.getDeltaY() > 0){
    	    		  Tourist.zoom.setValue(Tourist.zoom.getValue() + 0.02);
    	    	  } else {
    	    		  Tourist.zoom.setValue(Tourist.zoom.getValue() - 0.02);
    	    	  }
    	      }
    	    });
    	
    	Tourist.canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 
	       new EventHandler<MouseEvent>() {
	           @Override
	           public void handle(MouseEvent e) {
	        	   contextMenu.hide();
	        	   mouseX = e.getX();
	        	   mouseY = e.getY();
	           }
	       });
    	
    	outer.getChildren().add(box);
    	
    	return outer;
    }
	
    public static void paint(){
    	clearCanvas();
    	//drawGrid();
    	if(TopMenu.graph != null){
			drawGraph(TopMenu.graph);
		}
    	if(!Tourist.alt_path && Tourist.ptd.size() != 0){	
			for(int i = Tourist.ptd.size()-1; i >= 0; i--){
				Path path = Tourist.ptd.get(i);
				if(path.getLength() > 2){
					drawPath(path, i);
				}
			}
    	} else if(Tourist.alt_path && Tourist.ptd_alt.size() != 0){
    		for(int i = Tourist.ptd_alt.size()-1; i >= 0; i--){
				Path path = Tourist.ptd_alt.get(i);
				if(path.getLength() > 2){
					drawPath(path, i);
				}
			}
    	}
		
    }
    
    private static void drawCenteredCircle(int x, int y, int r, Color color){
    	x = x-(r);
		y = y-(r);
		
		gc.setFill(color);
		gc.fillOval(x,y,r*2,r*2);
    }
    
    public static void drawConnectingLine(int x1, int y1, int x2, int y2, Color color){
		gc.setLineWidth(2);
    	gc.setStroke(color);
		gc.strokeLine(x1, y1, x2, y2);
	}
    
    public static void drawNode(Node n, Color outline, Color center, Color text){
    	if(n.getName().equals(TopMenu.hotel.getText()) && Tourist.ptd.size() != 0){
    		drawPathOrigin(n.getX(), n.getY(),15);
    	} else {
    		drawCenteredCircle(n.getX(), n.getY(), 15, outline);
    	}
    	drawCenteredCircle(n.getX(), n.getY(), 13, center);
		drawCenteredText(n.getX(), n.getY()+40, 15, n.getName(), text);
		drawCenteredText(n.getX(), n.getY(), 15, new String(""+(-n.getImportance())), text);
	}
    
    public static void drawCenteredText(int x, int y, float size, String text, Color color) {
    	gc.setTextAlign(TextAlignment.CENTER);
    	gc.setFill(Color.BLACK);
    	gc.setFont(new Font("Verdana",12));
    	gc.fillText(text, x, y);
	}
    
    public static void drawGraph(Graph gr){
		for(Node n : gr.getNodes()){
			if(!n.getPaintNode()){
				drawNode(n, Color.BLACK, Color.WHITE, Color.BLACK);
			}
		}
	}
    
    public static void drawPath(Path path, int color){
		for(int i = 0; i < path.getLength()-1; i++){
			Node n1 = path.getNode(i);
			Node n2 = path.getNode(i+1);
			drawConnectingLine(n1.getX(), n1.getY(), n2.getX(), n2.getY(), Tourist.colors[color]);
			drawNode(n1, Tourist.colors[color], Color.WHITE, Color.BLACK);
			drawNode(n2, Tourist.colors[color], Color.WHITE, Color.BLACK);
		}
	}
    
	
	private static void drawPathOrigin(int x, int y, int r){
		x = x-(r);
		y = y-(r);
		double arc = 360.0 / Tourist.ptd.size();
		for(int i = 0; i < Tourist.ptd.size(); i++){
			gc.setStroke(Tourist.colors[i]);
			gc.setLineWidth(5);
			gc.strokeArc(x, y, r*2,r*2, i*arc, arc, ArcType.OPEN);
		}
	}
	
	public static void clearCanvas(){
		gc.clearRect(0, 0, Tourist.canvas.getWidth(), Tourist.canvas.getHeight());
	}
	
	private static Node isNode(double x, double y){
		Rectangle click = new Rectangle(x-10,y-10,1,1);
		gc.fillRect(x-10,y-10,1,1);
		if(TopMenu.graph != null){
			for(Node nd : TopMenu.graph.getNodes()){
				Rectangle rn = new Rectangle(nd.getX()-15, nd.getY()-15, 30, 30);
				if(rn.getBoundsInLocal().intersects(click.getBoundsInLocal())){
					return nd;
				}
			}
		}
		return null;
	}
}
