package motion.oneObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main extends JPanel{
	static BufferedImage img = null;
	static int x = 0;
	static int y = 0;
	static int width = 800;
	static int height = 600;

	static BufferedImage getImg(String name) {
		try{
			img = ImageIO.read(new File("C:\\java\\copy\\imageInput\\src\\motion\\oneObject\\" + name + ".png"));
			return img;
		} catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}


	static JFrame getFrame() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		frame.setBounds(dimension.width / 2 - 480, dimension.height / 2 - 270 , width, height);
		return frame;
	}
/*
	void moving() {
		x += width / 100;
		y += height / 100;
		if (x > width) {x = 0; y = 0;}
		if (y > height) {y = 0; x =0;}
		repaint();
	}

	public Main(){

		JFrame frame = getFrame();
		JPanel panel = new JPanel();
		JLabel imgIcon = new JLabel(new ImageIcon(getImg("donut").getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		imgIcon.setBounds(0,0,50,50);
		imgIcon.setLocation(x,y);
		panel.add(imgIcon);
		frame.getContentPane().setLayout(null);
		frame.add(imgIcon);
		frame.setPreferredSize(new Dimension(width,height));
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, x, y, this);
		g.dispose();
		}
*/
	public static void main(String[] args) {

		JFrame frame = getFrame();
		JButton button = new JButton("click");
		button.setVisible(false);
		//JPanel panel = new JPanel();
		JLabel imgIcon = new JLabel(new ImageIcon(getImg("donut").getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		imgIcon.setBounds(0,0,50,50);
		//panel.add(imgIcon);
		frame.getContentPane().setLayout(null);
		frame.add(imgIcon);
		frame.setVisible(true);

		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imgIcon.setLocation(x,y);
				x += width / 100;
				y += height / 100;
				if (x > width) {x = 0; y = 0;}
				if (y > height) {y = 0; x =0;}
			}
		};
		Timer myTimer = new Timer(30, al);
		myTimer.start();

	}
}


