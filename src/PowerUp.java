import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

import javax.swing.Timer;

public class PowerUp implements ActionListener{

	RoundRectangle2D pill;
	int powerType;
	Random r = new Random();
	
	Timer t;
	
	public PowerUp(){
		pill = new RoundRectangle2D.Double((r.nextInt((int) Menu.width)),(r.nextInt((int) Menu.height)),
				40, 40, 20, 20);
		powerType = r.nextInt(4);
		
		t = new Timer(10000,this);
	}
	
	public void paintComponent(Graphics g){
		
		Graphics2D g2 = (Graphics2D) g;
		
		switch(powerType){
		case 0: g2.setColor(Color.BLACK);
				g2.fill(pill);
				break;
		case 1:	g2.setColor(Color.WHITE);
				g2.fill(pill);
				break;
		case 2: g2.setColor(Color.MAGENTA);
				g2.fill(pill);
				break;
		case 3: g2.setColor(Color.ORANGE);
				g2.fill(pill);
				break;
		case 4: g2.setColor(Color.PINK);
				g2.fill(pill);
				break;
		}
	}
	
	public boolean intersect(Shape s){
		
		if(pill.intersects(s.getBounds2D())){
			pill = new RoundRectangle2D.Double((r.nextInt((int) Menu.width)),(r.nextInt((int) Menu.height)),
					40, 40, 20, 20);
			return true;
		}
		return false;
	}
	
	public void action(){
		
		double x = pill.getX() - Worm.velX;
		double y = pill.getY() - Worm.velY;
		
		pill = new RoundRectangle2D.Double(x, y, 40, 40, 20, 20);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		pill = new RoundRectangle2D.Double((r.nextInt((int) Menu.width)),(r.nextInt((int) Menu.height)),
				40, 40, 20, 20);
		
		powerType = r.nextInt(4);
		
	}
}
