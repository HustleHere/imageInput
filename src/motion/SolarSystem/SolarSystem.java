package motion.SolarSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.awt.*;

public class SolarSystem {
	/*static int width = 2560;
	static int height = 1440;*/

	static int Xcenter = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
	static int Ycenter = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);


	static public BufferedImage getImage(String name){
		try{
			BufferedImage image = ImageIO.read(new File("C:\\java\\copy\\imageInput\\src\\motion\\SolarSystem\\images\\" + name + ".png"));
			return image;
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	static public JLabel imageToJLabel(BufferedImage image, int Xscale, int Yscale) {
		if(Xscale == 0) Xscale = image.getWidth();
		if(Yscale == 0) Yscale = image.getHeight();
		JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(Xscale,Yscale,Image.SCALE_SMOOTH)));
		return label;
	}

	static public JFrame getFrame(String name, Color color){
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(color);
		frame.getContentPane().setLayout(null);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		frame.setBounds(-10,0, (int)dimension.getWidth() + 20, (int)dimension.getHeight() - 40);
		return frame;
	}

	static void planetRotation(JLabel label, int R, double t, double velocity){
		label.setLocation((Xcenter - label.getWidth()/2) - 50  + (int) (R * Math.cos(t * velocity)), (Ycenter - label.getHeight()/2) - 50 + (int) (R * Math.sin(t * velocity)));
	}

	public static void main(String[] args){

		JFrame frame = getFrame("SolarSystem", Color.black);

		JLabel sun = imageToJLabel(getImage("Sun"), 100,100);
		sun.setBounds(0,0, 100,100);
		sun.setLocation(Xcenter - 100,Ycenter - 100);
		frame.getContentPane().add(sun);

		JLabel mercury = imageToJLabel(getImage("Mercury"), 19, 19);
		mercury.setBounds(0,0, 19,19);
		frame.getContentPane().add(mercury);

		JLabel venus = imageToJLabel(getImage("Venus"), 47, 47);
		venus.setBounds(0,0, 47,47);
		frame.getContentPane().add(venus);

		JLabel earth = imageToJLabel(getImage("Earth"), 50, 50);
		earth.setBounds(0,0, 50,50);
		frame.getContentPane().add(earth);

		JLabel mars = imageToJLabel(getImage("Mars"), 27, 27);
		mars.setBounds(0,0, 27,27);
		frame.getContentPane().add(mars);

		JLabel jupiter = imageToJLabel(getImage("Jupiter"), 150, 150);
		jupiter.setBounds(0,0, 150,150);
		frame.getContentPane().add(jupiter);

		JLabel saturn = imageToJLabel(getImage("Saturn"), 125, 125);
		saturn.setBounds(0,0, 125,125);
		frame.getContentPane().add(saturn);

		JLabel uranus = imageToJLabel(getImage("Uranus"), 85, 85);
		uranus.setBounds(0,0, 85,85);
		frame.getContentPane().add(uranus);

		JLabel neptune = imageToJLabel(getImage("Neptune"), 80, 80);
		neptune.setBounds(0,0, 80,80);
		frame.getContentPane().add(neptune);


		frame.setVisible(true);

		ActionListener al = new ActionListener() {
			double t;
			@Override
			public void actionPerformed(ActionEvent e) {
				planetRotation(mercury,77, t,1.567);
				planetRotation(venus,144,t,1.167);
				planetRotation(earth, 200, t, 1);
				planetRotation(mars, 304, t, 0.8);
				planetRotation(jupiter, 400, t, 0.433);
				planetRotation(saturn, 600, t, 0.323);
				planetRotation(uranus, 700, t, 0.267);
				planetRotation(neptune, 800, t, 0.18);
				t += Math.PI / 180;
			}
		};

		Timer t = new Timer(30, al);
		t.start();
	}
}
