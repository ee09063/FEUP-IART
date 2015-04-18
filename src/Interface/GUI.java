package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import AStar.AStarPathFinder;
import Geoname.Geoname;
import Graph.Graph;
import Utilities.Path;
import Utilities.Refresh;

public class GUI implements KeyListener, MouseListener{
	
	protected static JMenuBar menu;
	protected static JMenu menu_file;
	
	public static myPanel map;
	protected static GUI window;
	protected static JFrame frame;
	
	protected static Graph graph;
	
	protected static Thread refreshThread;
	
	final static int GAP = 10;
	
	private static WindowListener closeWindow = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
        	map.setFocusable(true);
        	map.requestFocusInWindow();
            e.getWindow().dispose();
            System.exit(0);
        }
    };
	
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new GUI();
					GUI.frame.pack();
					GUI.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
    public GUI(){
    	initialize();
    }
    
    private void initialize(){
    	frame = new JFrame("Tourist Map App");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		prepare();
		
		Border border = BorderFactory.createLineBorder(Color.RED, 2);
		map = new myPanel();
		map.setBorder(border);
		map.setFocusable(true);
		map.addKeyListener(this);
		map.addMouseListener(this);
		map.requestFocusInWindow();
		frame.getContentPane().add(map, BorderLayout.CENTER);
        UserOptions newContentPane = new UserOptions();
        newContentPane.setOpaque(true);
        frame.getContentPane().add(newContentPane, BorderLayout.NORTH);
        frame.setVisible(true);
    }
	
    protected void prepare(){
    	menu = new JMenuBar();
    	menu_file = new JMenu("File");
    	
    	//createInterface();
    	
    	JMenuItem load_map = new JMenuItem("Load Map");
    	load_map.setToolTipText("Load Map");
    	load_map.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			loadGraph();
    		}
    	});
    	
    	JMenuItem exit = new JMenuItem("Exit");
    	exit.setToolTipText("Exit Application");
    	exit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			int option = JOptionPane.showConfirmDialog(frame, "Exit Application?", "Exit?", JOptionPane.YES_NO_OPTION);
    			if(option == JOptionPane.YES_OPTION){
    				System.exit(0);
    			}
    		}
    	});
    	
    	menu_file.add(load_map);
    	menu_file.add(exit);
    	menu.add(menu_file);
    	frame.setJMenuBar(menu);
    	
    	Refresh refresh = new Refresh(map,100);
    	refreshThread = new Thread(refresh);
    	refreshThread.start();
    }
	
    public void loadGraph(){
    	JFileChooser f = new JFileChooser();
		f.setCurrentDirectory(new File(System.getProperty("user.dir") + "/graph"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
		f.setFileFilter(filter);
		int returnVal = f.showOpenDialog(frame);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			File file = f.getSelectedFile();
			System.out.println("You tried to load file " + file.getName());
			/*
			 * GRAPH BULDING GOES HERE
			 */
			try {
				graph = new Graph(file);
				Geoname geoname = new Geoname();
				try {
					geoname.getLatLong(graph);
					/*graph.setDisplaceX(graph.getNodes().get(0).getLatLongX());
					graph.setDisplaceY(graph.getNodes().get(0).getLatLongY());
					graph.displaceNodes();*/
				} catch (Exception e) {
					e.printStackTrace();
				}
				myPanel.g_to_draw = graph;
				UserOptions.setGraph(graph);
				map.repaint();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
    }
    
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
