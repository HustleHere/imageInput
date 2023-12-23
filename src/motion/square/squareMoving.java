package motion.square;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class squareMoving {
	private static final long serialVersionUID = 1L;
	private static int width = 800;
	private static int heigth = 600;
	static BufferedImage img = null;
	static JLabel iconImg = null;

	static JFrame getFrame(String name, Color color) {
		JFrame j = new JFrame(name);
		j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		j.setVisible(true);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		j.setBounds((int) (dimension.getWidth() / 2 - 400),(int) dimension.getHeight() / 2 - 300, width, heigth);
		j.getContentPane().setBackground(color);
		return j;
	}

	static JLabel getImg(String name){
		try{
			img = ImageIO.read(new File("C:\\java\\copy\\imageInput\\src\\motion\\Square\\" + name + ".png"));
			iconImg = new JLabel(new ImageIcon(img.getScaledInstance(75,75,Image.SCALE_SMOOTH)));
			return iconImg;
		} catch (IOException ex) {
			ex.getStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = getFrame("square moving", Color.black);
		//JPanel panel = new JPanel();
		JLabel donut = getImg("donut");
		donut.setBounds(0,0,75,75);
		donut.setLocation(0,0);
		frame.getContentPane().setLayout(null);
		//panel.add(donut);
		frame.add(donut);

		ActionListener al = new ActionListener() {
			int x = 0;
			int y = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				donut.setLocation(x,y);
				if (y == 0) x+=5;
				if (x == 725) y+=5;
				if (y == 500 && x != 0) x-=5;
				if (x == 0 && y != 0) y-=5;
			}
		};

		Timer t = new Timer(30,al);
		t.start();
	}
}
