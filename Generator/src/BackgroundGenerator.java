import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Random;

import javax.imageio.ImageIO;

public class BackgroundGenerator {
	
	public static Random rand;
	
	public static void exportFile(Graphics2D graphic, BufferedImage image){
		long currentTime = System.currentTimeMillis();
		String fileName = "Backgrounds\\background_" + String.valueOf(currentTime) + ".png";
		File outputFile = new File(fileName);
		graphic.drawImage(image, null, 0, 0);
		try {
			ImageIO.write(image, "png", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
		  x = x-(r/2);
		  y = y-(r/2);
		  
	  	  float red = rand.nextFloat();
		  float blue = rand.nextFloat();
		  float green = rand.nextFloat();
		  float alpha = rand.nextFloat();
		  Color circleColor = new Color(red,blue,green,alpha);
		  g.setColor(circleColor);
		  g.fillOval(x,y,r,r);
		}
	
	public static void generateBackground(){
		int width = 1920;
		int height = 1080;
		rand = new Random();
				
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		
		
/*		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++)
			{
				float red = rand.nextFloat();
				float blue = rand.nextFloat();
				float green = rand.nextFloat();
				
				Color pixelColor = new Color(red,blue,green,1);
				image.setRGB(i, j, pixelColor.getRGB());
				
			}			
		}
		*/
		
		
		int maxNumCircles = rand.nextInt(10000);
		Graphics2D graphic = image.createGraphics();
		
		for(int circles = 0; circles <= maxNumCircles; circles++)
		{
			int circleX = rand.nextInt(width);
			int circleY = rand.nextInt(height);
			int radius = rand.nextInt(100)+50;
			drawCenteredCircle(graphic, circleX, circleY, radius);
		}
		
		
		
		exportFile(graphic, image);
		System.out.println("Completed");
	}

	public static void main(String[] args) {
		for(int counter = 0; counter < 100; counter++){
			generateBackground();
		}
	}

}