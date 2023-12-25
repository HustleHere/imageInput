package actions.repeating;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class repeating extends JComponent {
	Image image;
	@Override
	protected void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		try{
			image = ImageIO.read(new File("src\\actions\\repeating\\images\\grass.png")).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		for(int x = 0; x < 800; x += 50)
			for(int y = 0; y < 600; y += 50) {
				g2d.drawImage(image, x, y, null);
			}
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Grass");
		frame.setBounds(400,400,800,600);
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//frame.setLayout(null);
		repeating rep = new repeating();
		frame.getContentPane().add(rep);
		frame.setVisible(true);

	}
}
