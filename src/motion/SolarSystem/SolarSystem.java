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
	static int width = 800;
	static int height = 600;


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
		frame.setBounds((int) dimension.getWidth() / 2 - 400,(int) dimension.getHeight() / 2 - 300, width, height);
		return frame;
	}

	public static void main(String[] args){

		JFrame frame = getFrame("SolarSystem", Color.black);

		JLabel sun = imageToJLabel(getImage("Sun"), 100,100);
		sun.setBounds(0,0, 100,100);
		sun.setLocation(350,250);
		frame.getContentPane().add(sun);

		JLabel earth = imageToJLabel(getImage("Earth"), 50, 50);
		earth.setBounds(450,350, 50,50);
		frame.getContentPane().add(earth);

		frame.setVisible(true);

		ActionListener al = new ActionListener() {
			int R = 200;
			double t;
			@Override
			public void actionPerformed(ActionEvent e) {
				earth.setLocation(375 + (int) (R * Math.cos(t)), 275 + (int) (R * Math.sin(t)));
				t += Math.PI / 180;
			}
		};

		Timer t = new Timer(30, al);
		t.start();
	}
}
