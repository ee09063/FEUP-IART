package GUI;

import java.text.DecimalFormat;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import Utilities.Path;

public class PathOutput_FlowPane{

	static TextArea paths = new TextArea();
	
	FlowPane addFlowPane(){
    	
    	FlowPane flow = new FlowPane();
    	flow.setPrefSize(Tourist.width*0.1, Tourist.height*0.9);
    	
    	HBox box = new HBox();
    	box.setPrefSize(Tourist.width*0.1, Tourist.height*0.9);
    	box.setPadding(new Insets(10,10,0,0));
    	
    	box.getChildren().add(paths);
    	
    	flow.getChildren().add(box);
    
    	return flow;
    }
	
	public static void printPaths(){
		paths.clear();
		if(Tourist.alt_path){
			for(int i = 0; i < TopMenu.ptd_alt.size(); i++){
				paths.appendText("--- Day " + (i + 1) + " --- " + Tourist.colorNames[i] + "\n\n");
				paths.appendText(TopMenu.ptd_alt.get(i).toString()+"\n");
			}		
		} else {
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			for(int i = 0; i < TopMenu.ptd.size(); i++){
				Path p = TopMenu.ptd.get(i);
				paths.appendText("--- Day " + (i + 1) + " --- " + Tourist.colorNames[i] + "\n\n");
				paths.appendText(p.toString()+"\n");
				paths.appendText("Total Time: " + df.format(p.totalTime) +"\n\n");
			}
		}
	}
	
	public static void resizePane(){
		paths.setPrefSize(0, 0);
	}
}
