import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class Worm implements KeyListener{

	static Ellipse2D.Double head;
	ArrayList<Ellipse2D.Double> tail;
	
	final Color snakecolor;
	final Color headcolor;
	
	String name;
	
	static double speed = 6;
	double rad = 20;
	static double velX = speed;
	static double velY;
	
	int score = 0;
	
	int powerUp = -1 ;
	
	Timer p;
	
	public Worm(){
		
		head = new Ellipse2D.Double(Menu.width/2, Menu.height/2, rad, rad);
		
		tail = new ArrayList<Ellipse2D.Double>();
		
		double x = Menu.width/2;
		double y = Menu.height/2;
		for (int i = 0; i < 9; i++) {
			tail.add(new Ellipse2D.Double(x-((rad/2)*(i+1)), y, rad, rad));
		}
		
		headcolor = randcolor();
		snakecolor = randcolor();
	}
	
	public Color randcolor(){
		
		Random rand = new Random();
		
		float r = rand.nextFloat();
		float b = rand.nextFloat();
		float g = rand.nextFloat();
		
		return new Color(r,g,b);
		
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setStroke(new BasicStroke(8));
		g2.setColor(snakecolor);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for (Ellipse2D.Double e : tail) {
			g2.fill(e);
		}
		
		g2.setColor(headcolor);
		g2.fill(head);
		
		g2.setColor(Color.BLACK);
		g2.drawString(name, (int)(tail.get(tail.size()-1).x-rad/2), (int)(tail.get(tail.size()-1).y-rad/2));
		
		String vel = String.valueOf(speed);
		vel = vel.substring(0, 3);
		g2.drawString("Speed = " + vel, 5, 15);
		g2.drawString("Score = " + score, 5, 30);
		g2.drawString("Snake size = " + (tail.size()), 5, 45);
		
		
	}
	
	public void grow(){

		double x = head.x;
		double y = head.y;
		
		rad += 0.5;
		
		head = new Ellipse2D.Double(x, y, rad, rad);
		
		if (velY < 0) {
				tail.add(new Ellipse2D.Double(tail.get(tail.size()-1).x
						, (tail.get(tail.size()-1).y + rad/2), rad, rad));
		
		}
		else if (velY > 0) {
				tail.add(new Ellipse2D.Double(tail.get(tail.size()-1).x
						, (tail.get(tail.size()-1).y - rad/2), rad, rad));
		}
		else if (velX < 0) {
				tail.add(new Ellipse2D.Double((tail.get(tail.size()-1).x + rad/2)
						, tail.get(tail.size()-1).y, rad, rad));
		}
		else {
				tail.add(new Ellipse2D.Double((tail.get(tail.size()-1).x - rad/2)
						, tail.get(tail.size()-1).y, rad, rad));
		}

		for (int i = 0; i < tail.size()-1; i++) {
			tail.set(i, new Ellipse2D.Double(tail.get(i).x, tail.get(i).y, rad, rad));
		}
		
		if (speed > 3) {
			speed -= 0.1;
		}
		
		score += 10;
	}

	public void shrink(){
	
		if (tail.size() > 0) {
			
			double x = head.x;
			double y = head.y;
		
			rad -= 0.5;
		
			head = new Ellipse2D.Double(x, y, rad, rad);
			for (int i= 0; i < tail.size(); i++) {
				tail.set(i, new Ellipse2D.Double(tail.get(i).x, tail.get(i).y, rad, rad));
			}	
			tail.remove(tail.size()-1);
			if (speed < 6) {
				speed += 0.1;
			}
		}
		score -= 5;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public void setPowerUp(int i){
		powerUp = i;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

		switch(arg0.getKeyCode()) {
		
		case KeyEvent.VK_UP:
			velX = 0;
			velY = -speed;
			break;
		case KeyEvent.VK_DOWN:
			velX = 0;
			velY = speed;
			break;
		case KeyEvent.VK_LEFT:
			velX = -speed;
			velY = 0;
			break;
		case KeyEvent.VK_RIGHT:
			velX = speed;
			velY = 0;
			break;
		case KeyEvent.VK_0:
			grow();
			break;
		case KeyEvent.VK_1:
			shrink();
			break;
		case KeyEvent.VK_SPACE:
			powerUp = new Random().nextInt(4);
			usepowerup();
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			                resetpowerup();
			            }
			        }, 
			        5000 
			);
			powerUp = -1;
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void snakeActionByKeyboard(ActionEvent e){

		tail.get(0).x = head.x;
		tail.get(0).y = head.y;
		
		for (int i = tail.size() - 1; i > 0; i--) {
			tail.get(i).x = tail.get(i-1).x;
			tail.get(i).y = tail.get(i-1).y;
		}
		
		head.x += velX;
		head.y += velY;
		
	}
	
	public void snakeActionByMouse(){
		
	}
	
	public void move(){
		
		double x;
		double y;
		
		head = new Ellipse2D.Double(head.x - velX, head.y - velY, rad, rad);
				
		for (int i = tail.size() - 1; i > 0; i--) {
			if (velY < 0) {
				x = (tail.get(i).x);
				y = (tail.get(i).y + rad/2);
				tail.set(i, new Ellipse2D.Double(x, y, rad, rad));	
			}
			else if (velY > 0) {
				x = (tail.get(i).x);
				y = (tail.get(i).y - rad/2);
				tail.set(i, new Ellipse2D.Double(x, y, rad, rad));	
			}
			else if (velX < 0) {
				x = (tail.get(i).x + rad/2);
				y = (tail.get(i).y);
				tail.set(i, new Ellipse2D.Double(x, y, rad, rad));	
			}
			else {
				x = (tail.get(i).x - rad/2);
				y = (tail.get(i).y);
				tail.set(i, new Ellipse2D.Double(x, y, rad, rad));	
				
			}
		}
		
	}
	
	public void usepowerup(){
			switch (powerUp) {
			case 0:
				speed = speed/2;
				break;
			case 1:
				speed = speed*2;
				break;
			case 2:
				Food.foodsize = 30;
				break;
			case 3:
				Food.foodsize = 5;
				break;
			case 4:
				for(int i = 0; i < 3; i++)
					grow();
			}
	}
	
	public void resetpowerup(){
		switch (powerUp) {
		case 0:
			speed = speed*2;
			break;
		case 1:
			speed = speed/2;
			break;
		case 2:
		case 3:
			Food.foodsize = 10;
			break;
		}
		
		powerUp = -1;
	}
}