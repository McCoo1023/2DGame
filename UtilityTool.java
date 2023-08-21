package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {

	public BufferedImage scaledImage(BufferedImage original, int width, int height) {

		BufferedImage scaledImage = new BufferedImage(width, height, original.getType()); // initiates image as whatever
																							// we want
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);// scales our size for us, scaled image is saved in
															// scaledImage VVV
		g2.dispose();

		return scaledImage;
	}
}
