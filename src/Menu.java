 import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JFrame implements KeyListener, MouseListener {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static double width;
	static double height;
	
	JPanel Top;
	JPanel Center;
	JPanel Bottom;
	
	ImageIcon snakes;
	JLabel image;
	
	java.net.URL bgURL;
	
	JTextField jtUsername;
	JLabel jlUsername;
	JPanel jpUsername;
	JPanel jptext;
	
	JButton play;
	JButton hscore;
	JButton settings;
	
	static Highscoreframe h;
	
	static JFrame f;
	
	public Menu(){
		
		super("Snake Game");
		
		width = screenSize.getWidth();
		height = screenSize.getHeight();
		
		setBounds((int) width/2-350,(int) height/2-250,700,500);
		
		setLayout(new BorderLayout());
		addKeyListener(this);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		Center = new JPanel();
		Center.setLayout(new GridLayout(3, 1));
		Center.setBackground(new Color(27, 137, 60));
		add(Center,BorderLayout.CENTER);
		center();
		
		setVisible(true);
		h = new Highscoreframe();
	}
	

	public void top(){
		Top = new JPanel();
		
		Top.setBackground(new Color(27, 137, 60));
		bgURL = getClass().getResource("sbgsmall.png");
		snakes = new ImageIcon(bgURL);
		image = new JLabel(snakes);
		
		Top.add(image);
	}
	
	public void bottom (){
		Bottom = new JPanel();
		Bottom.setBackground(new Color(27, 137, 60));
		Bottom.setLayout(new FlowLayout());
		
		play = new JButton("PLAY!");
		hscore = new JButton("High Scores");
		settings = new JButton("Settings");
		
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				playgame();
			}
		});
		
		settings.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showsettings();
			}
		});
		
		hscore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showscores();
			}
		});
		
		Bottom.add(play);
		Bottom.add(hscore);
		Bottom.add(settings);
	}
	
	protected void showscores() {
		
		h.setSize(300,500);
		h.setBounds((int)(width/2-150),(int)(height/2-250),300,500);
		h.setVisible(true);
	}


	protected void showsettings() {
		
		Settings s = new Settings();
		
		s.setSize(200, 100);
		s.setBounds((int)(width/2-100),(int)(height/2-50),200,100);
		s.setVisible(true);
	}


	protected void playgame() {
		
		if(!jtUsername.getText().isEmpty()) {
			f = new JFrame("Snake Game");
			Game game = new Game();
			
			game.w.setName(jtUsername.getText());
			
			f.add(game);
			f.addKeyListener(game.w);
			//f.setBounds(0, 0, (int) width, (int) height);
			f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			f.addWindowListener(new WindowListener() {
				
				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowClosing(WindowEvent e) {
					game.t.stop();
					int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
				
					if(result == JOptionPane.YES_OPTION){
						String newscore = jtUsername.getText() + " " + game.w.score;
						h.addscore(newscore);
						h.sortscores();
						h.sethighscoreframe();
						h.writescores();
						f.dispose();
					}else{
						game.t.start();
					}
				}
				
				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			f.setVisible(true);
			
		}
		
		else
			JOptionPane.showMessageDialog(null, "Enter a username");
		
	}


	public void center(){
		
		Center.add(new JLabel());
		top();
		Center.add(Top);
		Center.add(new JLabel());
		
		jlUsername = new JLabel("Username", JLabel.CENTER);
		jtUsername = new JTextField("", JLabel.CENTER);
		jpUsername = new JPanel(new GridLayout(2, 1));
		jpUsername.setBackground(new Color(27, 137, 60));
		jptext = new JPanel();
		jptext.setBackground(new Color(27, 137, 60));
		jtUsername.setPreferredSize(new Dimension(100, 30));
		jptext.add(jtUsername);
		
		jpUsername.add(jlUsername);
		jpUsername.add(jptext);
		
		Center.add(new JLabel());
		Center.add(jpUsername);
		Center.add(new JLabel());
		
		Center.add(new JLabel());
		bottom();
		Center.add(Bottom);
		Center.add(new JLabel());
		
	}
			
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}