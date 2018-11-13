import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public abstract class Food {
	
	Random rand = new Random();
	
	ArrayList<Rectangle2D.Double> food = new ArrayList<Rectangle2D.Double>();
	
	static double foodsize = 10;
	
	public Food(int start){
		
		for(int i = 0; i < start; i++){		
			createFood();
		}
		
	} 
	
	protected void createFood(){
		food.add(new Rectangle2D.Double(rand.nextInt(3000),rand.nextInt(3000),foodsize, foodsize)) ;
	}
	
	public boolean intersect(Shape s){
		
		for(int i = 0; i <  food.size(); i++){
			if(food.get(i).intersects(s.getBounds2D())){
				food.set(i,new Rectangle2D.Double(rand.nextInt((int)(Menu.width)),rand.nextInt((int)(Menu.height)),foodsize, foodsize)) ;
				return true;
			}
		}
		
		return false;
	}
	
	public void action(){
		
		double x;
		double y;
		for (int i = 0; i < food.size(); i++) {
			x = (food.get(i).x - Worm.velX);
			y = (food.get(i).y - Worm.velY);
			food.set(i,  new Rectangle2D.Double(x, y, foodsize, foodsize));	
		}
		
	}
	
	abstract public void paintComponent(Graphics g);
	
}
