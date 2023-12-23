package motion.DVD;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class dvdMoving extends JComponent{
	private static final long serialVersionUID = 1L;
	private static int width = 800;
	private static int height = 600;
	static BufferedImage img;

	static BufferedImage getBuffImage(String name){
		try{
			img = ImageIO.read(new File("C:\\java\\copy\\imageInput\\src\\motion\\DVD\\images\\" + name + ".png"));
			return img;
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	static JLabel getImg(String name) {
		try {
			img = ImageIO.read(new File("C:\\java\\copy\\imageInput\\src\\motion\\DVD\\images\\" + name + ".png"));
			JLabel label = new JLabel(new ImageIcon(img.getScaledInstance(100,100, Image.SCALE_SMOOTH)));
			return label;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	static JFrame getFrame(String name, Color color) {
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		frame.setBounds((int) dimension.getWidth() / 2 - 400,(int) dimension.getHeight() / 2 - 300, width, height);
		frame.getContentPane().setBackground(color);
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.getContentPane().setLayout(null);
		return frame;
	}

	static int getWidth(BufferedImage image){
		return image.getWidth();
	}

	static int getHeight(BufferedImage image){
		return image.getHeight();
	}

	static int[] pixelArr(BufferedImage image){
		int[] pixels = new int[getWidth(image) * getHeight(image)];
		for(int i = 0; i < getWidth(image); i++)
			for(int j = 0; j < getHeight(image); j++) {
				pixels[i * getWidth(image) + j] = image.getRGB(i, j) & 0xFFFFFF;
			}
		return pixels;
	}

	static int[] pixelColorChange(int[] pixels){
		int color = (int)(Math.random() * 16777215);
		for(int i = 0; i < getWidth(img); i++)
			for(int j = 0; j < getHeight(img); j++)
				if (pixels[i * getWidth(img) + j] == 16777215) pixels[i * getWidth(img) + j] = color;
		return pixels;
	}

	static BufferedImage imageFromPixels(int[] pixels){
		BufferedImage bufImg = new BufferedImage(getWidth(img), getHeight(img), BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < getWidth(img); i++)
			for(int j = 0; j < getHeight(img); j++)
				bufImg.setRGB(i,j,pixels[i * getWidth(img) + j]);
		return bufImg;
	}

	static void saveImage(BufferedImage image) {
		try{
			ImageIO.write(image,"png", new File("C:\\java\\copy\\imageInput\\src\\motion\\DVD\\images\\DVD2.png"));
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	static void imageColorChange(BufferedImage image) {
		BufferedImage img = imageFromPixels(pixelColorChange(pixelArr(image)));
		saveImage(img);
	}

	public static void main (String[] args) {

		int x = (int)(Math.random() * width);
		int y = (int)(Math.random() * height);
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = getFrame("DVD moving", Color.black);
		JLabel dvd = getImg("DVD");
		dvd.setBounds(0,0,100,100);

		//dvd.setLocation(x, y);
		frame.getContentPane().setLayout(null);
		frame.add(dvd);

		frame.setVisible(true);


		ActionListener al = new ActionListener() {
			int x = (int)(Math.random() * (width - 100)) - 5;
			int y = (int)(Math.random() * (height - 100)) - 5;
			int dx = 5, dy = 5;
			int Xcord = x + dx;
			int Ycord = y + dy;
			int count = 0;
			JLabel dvd2 = getImg("DVD2");
				@Override
				public void actionPerformed(ActionEvent e) {
					Xcord += dx;
					Ycord += dy;
					if (count == 0) {
						dvd.setLocation(Xcord,Ycord);}
					else {
						dvd2.setLocation(Xcord,Ycord);
						dvd2.setVisible(true);
						dvd.setVisible(false);
					}
					if(Ycord <= -20 || Ycord >= (height - 110)) {
						dy *= -1;
						count++;
						dvd2.setVisible(false);
						imageColorChange(getBuffImage("DVD"));
						dvd2 = getImg("DVD2");
						dvd2.setBounds(0,0,100,100);
						frame.add(dvd2);
					}
					if(Xcord <= 0 || Xcord >= (width - 100)) {
						dx *= -1;
						count++;
						dvd2.setVisible(false);
						imageColorChange(getBuffImage("DVD"));
						dvd2 = getImg("DVD2");
						dvd2.setBounds(0,0,100,100);
						frame.add(dvd2);
					}
				}
			};

		Timer t = new Timer(30, al);
		t.start();
	}
}