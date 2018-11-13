import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class BadFood extends Food {

	public BadFood() {
		super(75);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.RED);
		
		for(Rectangle2D.Double r : food){
			g2.fill(r);
		}

	}

	

}
