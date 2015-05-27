package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import AStar.AStar;
import Graph.Graph;
import Utilities.Path;

public class Tourist extends Application {
    
	static Dimension screenSize;
	public static double width;
	public static double height;
	
	
	
	public static Canvas canvas;
	static Slider zoom;
	
	public static ArrayList<Path> ptd = new ArrayList<Path>();
	public static ArrayList<Path> ptd_alt = new ArrayList<Path>();
	
	public static Color[] colors = {Color.GREEN, Color.BLUE, Color.ORANGE,
			Color.MAGENTA, Color.RED, Color.YELLOW,
			Color.PINK, Color.CYAN, Color.BLACK, Color.ALICEBLUE,
			Color.GRAY};

	public static String[] colorNames = { "GREEN", "BLUE", "ORANGE", "MAGENTA", "RED", "YELLOW",
			  "PINK", "CYAN", "BLACK", "ALICE_BLUE", "GRAY"};

	static boolean alt_path = false;
	
	public static void main(String[] args) {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();
		height = screenSize.getHeight();
		launch(Tourist.class, args);
    }
    
    @Override
    public void start(Stage stage) {

        BorderPane border = new BorderPane();
        
        HBox menu = new TopMenu().addHBox(stage);
        border.setTop(menu);
        
        border.setRight(new PathOutput_FlowPane().addFlowPane());
        border.setCenter(new Drawing_Canvas().addCanvas());
        
        Scene scene = new Scene(border, width-50, height-50);
        stage.setScene(scene);
        stage.setTitle("Tourist App -- " + width +" x " + height);
        stage.show();
    }  
}































