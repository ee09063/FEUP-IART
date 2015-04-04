package Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tourist {
	public static void main(String args[]) throws IOException{
		System.out.print("Filename: ");
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		String filename = inFromUser.readLine() + ".txt";
		
		//Graph g = new Graph(filename);
	}
}
