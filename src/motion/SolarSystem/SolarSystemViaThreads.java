package motion.SolarSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SolarSystemViaThreads {

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

	static void planetMotion(JLabel planet, int orbit, double velocity,String name){
		Runnable pm = new planetThread(planet, orbit, velocity);
		Thread pmT = new Thread(pm, name);
		pmT.start();
	}

	static JLabel addPlanetAtScreen(String name, int Xscale, int Yscale){
		JLabel label = imageToJLabel(getImage(name), Xscale, Yscale);
		label.setBounds(0,0, Xscale,Yscale);
		return label;
	}

	public static void main(String[] args) {

		JFrame frame = getFrame("SolarSystem", Color.black);

		JLabel sun = imageToJLabel(getImage("Sun"), 100,100);
		sun.setBounds(0,0, 100,100);
		sun.setLocation(Xcenter - 100,Ycenter - 100);
		frame.getContentPane().add(sun);

		JLabel mercury = addPlanetAtScreen("Mercury", 19,19);
		frame.getContentPane().add(mercury);
		planetMotion(mercury, 77, 1.567, "Mercury");

		JLabel venus = addPlanetAtScreen("Venus", 47, 47);
		frame.getContentPane().add(venus);
		planetMotion(venus, 144, 1.167, "Venus");

		JLabel earth = addPlanetAtScreen("Earth", 50,50);
		frame.getContentPane().add(earth);
		planetMotion(earth, 200, 1,"Earth");

		JLabel mars = addPlanetAtScreen("Mars", 27,27);
		frame.getContentPane().add(mars);
		planetMotion(mars, 304,0.8, "Mars");

		JLabel jupiter = addPlanetAtScreen("Jupiter", 150, 150);
		frame.getContentPane().add(jupiter);
		planetMotion(jupiter, 400, 0.433, "Jupiter");

		JLabel saturn = addPlanetAtScreen("Saturn", 125, 125);
		frame.getContentPane().add(saturn);
		planetMotion(saturn, 600, 0.323, "Saturn");

		JLabel uranus = addPlanetAtScreen("Uranus", 85, 85);
		frame.getContentPane().add(uranus);
		planetMotion(uranus, 700,  0.267, "Uranus");

		JLabel neptune = addPlanetAtScreen("Neptune", 80, 80);
		frame.getContentPane().add(neptune);
		planetMotion(neptune, 800, 0.18, "Neptune");

		frame.setVisible(true);
	}
}

class planetThread implements Runnable {

	JLabel planet;
	int orbit;
	double velocity;
	double t = 0;
	int Xcenter = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
	int Ycenter = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);

	planetThread(JLabel planet, int orbit, double velocity){
		this.planet = planet;
		this.orbit = orbit;
		this.velocity = velocity;
	}

	@Override
	public void run() {
		while (true){
			planet.setLocation((Xcenter - planet.getWidth()/2) - 50  + (int) (orbit * Math.cos(t * velocity)), (Ycenter - planet.getHeight()/2) - 50 + (int) (orbit * Math.sin(t * velocity)));
			t += Math.PI / 180;
			try {
				Thread.sleep(30);
			} catch(InterruptedException ex){
				ex.printStackTrace();
			}
		}
	}
}