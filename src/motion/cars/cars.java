package motion.cars;

import java.awt.*;
import javax.swing.*;
import static motion.cars.ImageOperations.*;

public class cars {

	static int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	static int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	static JFrame getFrame(String name, Color color) {
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(color);
		frame.getContentPane().setLayout(null);
		/*Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();*/
		frame.setBounds(width / 2 - 640, height / 2 - 360, width / 2, height / 2);
		return frame;
	}

	public static void main(String[] args) {

		JFrame frame = getFrame("Racing", Color.black);

		Car green = new Car("green", false, 100, 100, 0, 0);
		frame.getContentPane().add(green.getJLabel());
		Thread greenM = new Thread(green, "Green");
		greenM.start();

		Car gray = new Car("gray", false, 100, 100, 0, 100);
		frame.getContentPane().add(gray.getJLabel());
		Thread grayM = new Thread(gray, "Gray");
		grayM.start();

		Car red = new Car("red", true, 100, 100, 0, 200);
		frame.getContentPane().add(red.getJLabel());
		Thread redM = new Thread(red, "Red");
		redM.start();

		Car white = new Car("white", true, 100, 100, 0, 300);
		frame.getContentPane().add(white.getJLabel());
		Thread whiteM = new Thread(white, "White");
		whiteM.start();

		frame.setVisible(true);

		Car[] cars = {green, gray, red, white};
		boolean temp = false;

		while(!temp) {
			for (Car car : cars) {
				if (car.isWin) {
					JLabel text = new JLabel("The winner is " + car.name);
					text.setBounds(width / 2 - 690, height / 2 - 460,500,100);
					text.setForeground(Color.yellow);
					frame.getContentPane().add(text);
					temp = true;
				}
			}
		}
		frame.repaint();
	}
}

class Car extends JLabel implements Runnable {
	String name;
	private JLabel car;
	private int x;
	private int y;
	private static boolean isFinished = false;
	boolean isWin = false;


	Car(String name, boolean reverse, int Xscale, int Yscale, int x, int y){
		this.name = name;
		setLabel(name, reverse, Xscale, Yscale, x, y);
		setX(x);
		setY(y);
	}

	void setX(int x){
		this.x = x;
	}

	void setY(int y){
		this.y = y;
	}

	void setLabel(String name, boolean reverse, int Xscale, int Yscale, int x, int y){
		if (!reverse) {
			this.car = getLabel(getImage(name), Xscale, Yscale, x, y);
		} else {
			this.car = getLabel(imageReverse(name), Xscale, Yscale, x, y);
		}
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public JLabel getJLabel(){
		return this.car;
	}

	@Override
	public void run() {
		while(!isFinished) {
			car.setLocation(getX(), getY());
			setX(getX() +  (int)(Math.random() * 4 + 1));
			if (x >= 1180) {isFinished = true; isWin = true;}
			try {
				Thread.sleep(10);
			} catch (InterruptedException ex) {
			}
		}
	}
}