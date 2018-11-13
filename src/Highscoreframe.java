import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Highscoreframe extends JFrame {

	JList<String> Highscores;
	
	JLabel Hscores;
	
	ArrayList<String> scores;
	
	String [] sortedscores;
	
	public Highscoreframe(){
		
		super("Highscores");
		
		scores = new ArrayList<String>();
		
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new FileReader("Highscores.txt"));
			String line;
			while ((line = rd.readLine()) != null) {
				scores.add(line);
			}
		} 
		catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "There was an error");
		}
		
		sortedscores = scores.toArray(new String[scores.size()]);
		
		sethighscoreframe();
	}

	public void sortscores() {
		int n = scores.size();
		for (int i = 0 ; i < n-1; i++) {
			for (int j = 0; j < n-i-1 ; j++) {
				String score1 = scores.get(j).split(" ")[scores.get(j).split(" ").length-1];
				int s1 = Integer.parseInt(score1);
				String score2 = scores.get(j+1).split(" ")[scores.get(j+1).split(" ").length-1];
				int s2 = Integer.parseInt(score2);
				if (s1 < s2) {
					String temp = scores.get(j);
					scores.set(j, scores.get(j+1));
					scores.set(j+1, temp);
				}
			}
		}
		
		sortedscores = scores.toArray(new String[scores.size()]);
	}
	
	public void addscore(String s){
		scores.add(s);
	}
	
	public void writescores(){
		PrintWriter wr = null;
		try {
			wr = new PrintWriter(new FileWriter("Highscores.txt"));
			for (int j = 0; j < sortedscores.length ;j++) {
				wr.println(sortedscores[j]);
			}
			wr.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There was an error");
		}
	}
	
	public void sethighscoreframe(){
		Hscores = new JLabel("Highscores");
		Highscores = new JList<>(sortedscores);
		
		setLayout(new BorderLayout());
		add(Hscores, BorderLayout.NORTH);
		add(Highscores, BorderLayout.CENTER);
	}
	
}
