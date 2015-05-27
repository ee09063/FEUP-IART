package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class PathOutput_FlowPane{

	static TextArea paths = new TextArea();
	
	FlowPane addFlowPane(){
    	
    	FlowPane flow = new FlowPane();
    	flow.setPrefWidth(Tourist.width*0.15);
    	
    	HBox box = new HBox();
    	box.setPrefWidth(Tourist.width*0.15);
    	box.setPrefHeight(Tourist.height*0.9);
    	box.setPadding(new Insets(10,Tourist.width*0.06,Tourist.height*0.06,0));
    	
    	paths.setPrefHeight(Tourist.height*0.8);
    	paths.setPrefWidth(Tourist.width*0.15);
    	
    	box.getChildren().add(paths);
    	
    	flow.getChildren().add(box);
    
    	return flow;
    }
	
	public static void printPaths(){
		paths.clear();
		if(Tourist.alt_path){
			for(int i = 0; i < Tourist.ptd_alt.size(); i++){
				paths.appendText("--- Day " + (i + 1) + " --- " + Tourist.colorNames[i] + "\n\n");
				paths.appendText(Tourist.ptd_alt.get(i).toString()+"\n");
			}
		} else {
			for(int i = 0; i < Tourist.ptd.size(); i++){
				paths.appendText("--- Day " + (i + 1) + " --- " + Tourist.colorNames[i] + "\n\n");
				paths.appendText(Tourist.ptd.get(i).toString()+"\n");
			}
		}
	}
}
