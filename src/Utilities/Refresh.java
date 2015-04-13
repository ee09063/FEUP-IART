package Utilities;

import Interface.GUI;
import Interface.myPanel;

public class Refresh implements Runnable{

	myPanel panel;
	long time;
	
	public Refresh(myPanel panel, long time){
		this.panel = panel;
		this.time = time;
	}
	
	@Override
	public void run() {
		while(true){
			if(GUI.map != null){
				GUI.map.repaint();
			}
			try {
				Thread.sleep(100);
				//System.out.println("REPAINTING...");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
