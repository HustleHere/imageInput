package motion.cars;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageOperations {
	static int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	static int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static JFrame getFrame(String name, Color color) {
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(color);
		frame.getContentPane().setLayout(null);
		/*Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();*/
		frame.setBounds(width / 2 - 640, height / 2 - 360, width / 2, height / 2);
		return frame;
	}
	public static JLabel getLabel(BufferedImage image, int Xscale, int Yscale, int x, int y){
		JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(Xscale, Yscale, Image.SCALE_SMOOTH)));
		label.setBounds(0,0,Xscale,Yscale);
		label.setLocation(x,y);
		return label;
	}

	public static BufferedImage getImage(String name){
		try{
			BufferedImage image = ImageIO.read(new File("C:\\java\\copy\\imageInput\\src\\motion\\cars\\images\\" + name + ".png"));
			return image;
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static int[][] pixelFromImage(BufferedImage image){
		int[][] arr = new int[image.getWidth()][image.getHeight()];
		for(int i = 0; i < image.getWidth(); i++)
			for(int j = 0; j < image.getWidth(); j++) {
				arr[i][j] = image.getRGB(i, j) & 0xFFFFFF;
			}
		return arr;
	}

	public static BufferedImage imageFromPixels(int[][] arr){
		BufferedImage image = new BufferedImage(arr.length, arr[0].length, BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < arr.length; i++)
			for(int j = 0; j < arr[i].length; j++){
				image.setRGB(i,j,arr[i][j]);
			}
		return image;
	}

	public static int[][] pixelImageXReverse(int[][] arr){
		int temp = 0;
		for(int i = 0; i < (arr.length / 2); i++)
			for(int j = 0; j < arr[i].length; j++){
				temp = arr[i][j];
				arr[i][j] = arr[arr.length - 1 - i][j];
				arr[arr.length - 1 - i][j] = temp;
			}
		return arr;
	}

	public static int[][] pixelImageYReverse(int[][] arr){
		int temp = 0;
		for(int i = 0; i < arr.length; i++)
			for(int j = 0; j < (arr[i].length / 2); j++){
				temp = arr[i][j];
				arr[i][j] = arr[i][arr[i].length - 1 - j];
				arr[i][arr[i].length - 1 - j] = temp;
			}
		return arr;
	}

	public static BufferedImage imageReverse(String name){
		return imageFromPixels(pixelImageXReverse(pixelFromImage(getImage(name))));
	}
}
