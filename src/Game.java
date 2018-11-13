import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {
	
	Worm w = new Worm();
	GoodFood f = new GoodFood();
	BadFood b = new BadFood();
	PowerUp p = new PowerUp();
	
	Image bg;
	
	java.net.URL bgURL;
	
	Timer t;
	
	int time = 0;
	int timeCalculator = 0;
	
	public Game(){
		
		addKeyListener(w);
		//addMouseListener(w);
		Random rand = new Random();
		int x = rand.nextInt(5);
		
		switch(x){
			case 0: bgURL = getClass().getResource("purple.jpg");
			System.out.println(1);
		break;
			case 1: bgURL = getClass().getResource("underwater.jpg");
			System.out.println(2);
		break;
			case 2: bgURL = getClass().getResource("citywallpaper.png");
			System.out.println(3);
		break;
			case 3: bgURL = getClass().getResource("forrestwp.png");
			System.out.println(4);
		break;
			case 4: bgURL = getClass().getResource("waterfallwp.jpg");
			System.out.println(6);
		break;
		}

		bg = Toolkit.getDefaultToolkit().createImage(bgURL);
		
		t = new Timer(50, this);
		t.start();
		
		p.t.start();
		
		w.p = new Timer(5000,this);
	}
	
	public void paintComponent(Graphics g){
		
		g.drawImage(bg, 0, 0, this);
		
		w.paintComponent(g);
		f.paintComponent(g);
		b.paintComponent(g);
		p.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.drawString("Time = " + time, 5, 60);
	}
	
	public void intersectf(){
		w.grow();	
	}
	
	public void intersectb(){
		if(w.tail.size() == 1){	
			w.shrink();
			JOptionPane.showMessageDialog(null, "GAME OVER");
			String newscore = w.name + " " + w.score;
			Menu.h.addscore(newscore);
			Menu.h.sortscores();
			Menu.h.sethighscoreframe();
			Menu.h.writescores();			
			Menu.f.dispose();
		}else
			w.shrink();
	}
	
	public void intersectp(int i){
		w.setPowerUp(i);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if(Settings.mouse.isSelected()){
		//w.snakeactionMouse(e);
		//}
		w.snakeActionByKeyboard(e);
		f.action();
		b.action();
		p.action();
		w.move();
		
		if(f.intersect(w.head)){
			intersectf();
		}
		else if(b.intersect(w.head)){
			intersectb();
		}
		else if(p.intersect(w.head)){
			intersectp(p.powerType);
			w.usepowerup();
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			                w.resetpowerup();
			            }
			        }, 
			        5000 
			);
			p.powerType = p.r.nextInt(4);
		}
		
		timeCalculator++;
		if (timeCalculator == 20) {
			time++;
			timeCalculator=0;
		}
		
		
		repaint();
	}

}
