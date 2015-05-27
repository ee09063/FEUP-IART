package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import AStar.AStar;
import Graph.Graph;
import Graph.Node;
import Utilities.AngleComparator;
import Utilities.Path;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class TopMenu {

	static TextField hotel;
	static TextField days;
	static TextField timeLimit;
	
	final static FileChooser fileChooser = new FileChooser();
	static String workDirectory = System.getProperty("user.dir");
	
	protected static Graph graph;
	
	HBox addHBox(Stage stage) {

        HBox hbox = new HBox(10);
  
        hbox.setPadding(new Insets(10,10,10,10));

        Button loadGraph = new Button("Load");
        loadGraph.setPrefSize(100, 20);
        loadGraph.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	loadGraph(stage);
            	Drawing_Canvas.paint();
            }
        });
        
        HBox Tbox = new HBox(10);
        Tbox.setAlignment(Pos.CENTER);
        
        Label daysLabel = new Label("Days");
        
        days = new TextField();
        days.setPrefWidth(90);
        days.setText("1");
        
        Label hotelLabel = new Label("Hotel");
        
        hotel = new TextField();
        hotel.setPrefWidth(90);
        hotel.setText("Saint James");
        
        Label timeLabel = new Label("Time Limit");
        
        timeLimit = new TextField();
        timeLimit.setPrefWidth(90);
        timeLimit.setText("600");
        
        Tbox.getChildren().addAll(daysLabel, days, hotelLabel, hotel, timeLabel, timeLimit);
        
        Button runAStar = new Button("Run");
        runAStar.setPrefSize(100, 20);
        runAStar.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Tourist.alt_path = false;
            	runAStar();
            	PathOutput_FlowPane.printPaths();
            	Drawing_Canvas.paint();
            }
        });
        
        Button path = new Button("Path");
        path.setPrefSize(100, 20);
        path.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	adjustPaths();
            	Tourist.alt_path = true;
            	Drawing_Canvas.paint();
            	PathOutput_FlowPane.printPaths();
            }
        });
        
        HBox zoomBox = new HBox(10);
        zoomBox.setAlignment(Pos.CENTER);
        
        Label zoomLabel = new Label("ZOOM");
        
        Tourist.zoom = new Slider();
        Tourist.zoom.setMin(0);
        Tourist.zoom.setMax(0.3);
        Tourist.zoom.setValue(0.13);
        Tourist.zoom.setMajorTickUnit(0.02);
        Tourist.zoom.setBlockIncrement(0.02);
        
        Tourist.zoom.valueProperty().addListener(new ChangeListener<Object>(){
			@Override
			public void changed(ObservableValue arg0, Object arg1, Object arg2) {
				if(graph != null){
					graph.DISPLACE_X = Tourist.zoom.getValue();
					graph.DISPLACE_Y = Tourist.zoom.getValue();
					graph.displaceNodes();
					Drawing_Canvas.paint();
				}
			}
        });
        
        zoomBox.getChildren().addAll(zoomLabel,Tourist.zoom);
        
        HBox toggBox = new HBox(10);
        toggBox.setAlignment(Pos.CENTER);
        
        ToggleGroup ratio = new ToggleGroup();
        RadioButton WH = new RadioButton("W/H");
        WH.setToggleGroup(ratio);
        WH.setSelected(true);
        RadioButton WW = new RadioButton("W/W");
        WW.setToggleGroup(ratio);
    
        ratio.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

                RadioButton bt = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                if(bt.equals(WH)){
                	graph.WIDTH = Tourist.canvas.getWidth();
                	graph.HEIGHT = Tourist.canvas.getHeight();
                } else if(bt.equals(WW)){
                	graph.WIDTH = Tourist.canvas.getWidth();
                	graph.HEIGHT = Tourist.canvas.getWidth();
                } 
                graph.displaceNodes();
            	Drawing_Canvas.paint();
            }
        });
        
        toggBox.getChildren().addAll(WH, WW);
        
        Button exit = new Button("Exit");
        exit.setPrefSize(100, 20);
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	System.exit(0);
            }
        });

        hbox.getChildren().addAll(loadGraph, Tbox, runAStar, path, zoomBox, toggBox, exit);
        
        return hbox;
    }
	
	 static void runAStar(){
	    	if(graph != null && !hotel.getText().equals("") && !timeLimit.getText().equals("") && !days.getText().equals("")){
				//System.out.println("RUNNING");
				int number_of_days = Integer.parseInt(days.getText());
				
				AStar pathFinder = new AStar(graph, graph.getNode(hotel.getText()), Integer.parseInt(timeLimit.getText()));
				AStar.clear();
				Tourist.ptd.clear();
				
				for(int i = 0; i < number_of_days; i++){
					//System.out.println("***** DAY " + (i+1) + " *****");
					Path path = pathFinder.runAStar(graph.getNode(hotel.getText()));
					if(path.getLength() > 2){
						Tourist.ptd.add(path);
						//System.out.println(path);
					}
					//System.out.println("*************************");
				}
			} else System.err.println("CANNOT RUN");
	    }
	
	 public static void loadGraph(Stage stage){
	    fileChooser.setInitialDirectory(new File(workDirectory+"\\graph"));
		ExtensionFilter filter = new ExtensionFilter("TXT Files", "txt");
		fileChooser.setSelectedExtensionFilter(filter);
		File inputFile = fileChooser.showOpenDialog(stage);
	    if (inputFile != null) {
	    	System.out.println("You tried to load file " + inputFile.getName());
			try {
				graph = new Graph(inputFile);
				try {
					graph.setDisplaceX(graph.getNodes().get(0).getLatLongX());
					graph.setDisplaceY(graph.getNodes().get(0).getLatLongY());
					graph.displaceNodes();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    }
	}
	    
	 
	public static void adjustPaths(){
		Tourist.ptd_alt.clear();
		for(int i  = 0; i < Tourist.ptd.size(); i++){
			adjustPath(Tourist.ptd.get(i));
		}
	}
	
	private static void adjustPath(Path path){
		@SuppressWarnings("unchecked")
		ArrayList<Node> copy = (ArrayList<Node>) path.path.clone();
		Collections.sort(copy, new AngleComparator());
		Node n = copy.remove(1);
		copy.add(n);
		Tourist.ptd_alt.add(new Path(copy));
	}
	
}
