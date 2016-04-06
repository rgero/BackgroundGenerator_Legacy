import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
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
	public static int width;
	public static int height;
	
	public static final int RECT_MAX_WIDTH = 250;
	public static final int RECT_MAX_HEIGHT = 250;
	public static final int MAX_POLYGON_POINTS = 10;
	public static final int MAX_POLYGON_DISTANCE = 125;
	public static final int MAX_OBJECT_NUMBER = 10000;
	
	public static boolean CIRCLES = false;
	public static boolean SQUARES = true;
	public static boolean POLYGONS = true;
	
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
	
	public static void drawCenteredCircle(Graphics2D g) {
		int x = rand.nextInt(width);
		int y = rand.nextInt(height);
		int r = rand.nextInt(100)+50;
		x = x-(r/2);
		y = y-(r/2);
		g.setColor( chooseColor() );
		g.fillOval(x,y,r,r);
	}
	
	public static void drawSquare(Graphics2D g){
		int x = rand.nextInt(width);
		int y = rand.nextInt(height);
		int width, height;
		if (rand.nextBoolean()){
			width = rand.nextInt(RECT_MAX_WIDTH);
			height = width;
		} else {
			width = rand.nextInt(RECT_MAX_WIDTH);
			height = rand.nextInt(RECT_MAX_HEIGHT);
		}

		g.setColor( chooseColor() );
		g.fill(new Rectangle(x,y,width,height));
	}
	
	public static void drawPolygons(Graphics2D g){
		int polygonPoints = rand.nextInt(MAX_POLYGON_POINTS);
		int[] xPoints = new int[polygonPoints];
		int[] yPoints = new int[polygonPoints];
		int x = rand.nextInt(width);
		int y = rand.nextInt(height);
		for(int i = 0; i < polygonPoints; i++){
			xPoints[i] = x + (rand.nextInt(MAX_POLYGON_DISTANCE) - MAX_POLYGON_DISTANCE/2);
		}
		for(int j = 0; j < polygonPoints; j++){
			yPoints[j] = y + (rand.nextInt(MAX_POLYGON_DISTANCE) - MAX_POLYGON_DISTANCE/2);
		}
		
		g.setColor( chooseColor() );
		g.fill(new Polygon(xPoints, yPoints, xPoints.length));
	}
	
	public static Color chooseColor(){
	  	float red = rand.nextFloat()  	* (125/255);
		float blue = rand.nextFloat() 	* (255/255);
		float green = rand.nextFloat() 	* (125/255);
		float alpha = rand.nextFloat()	* (255/255);
		Color chosenColor = new Color(red,green,blue,alpha);
		return chosenColor;
	}
	
	public static void generateBackground(){
		width = 1920;
		height = 1080;
		rand = new Random();
				
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);


		Graphics2D graphic = image.createGraphics();
		if (SQUARES){
			int maxNumSquares = rand.nextInt(MAX_OBJECT_NUMBER);
			for(int squares = 0; squares <= maxNumSquares; squares ++){
				drawSquare(graphic);
			}
		}
		if (CIRCLES){
			int maxNumCircles = rand.nextInt(MAX_OBJECT_NUMBER);
			for(int circles = 0; circles <= maxNumCircles; circles++)
			{
				drawCenteredCircle(graphic);
			}
		}
		if (POLYGONS){
			int maxNumCircles = rand.nextInt(MAX_OBJECT_NUMBER);
			for(int circles = 0; circles <= maxNumCircles; circles++)
			{
				drawPolygons(graphic);
			}
		}
		
		
		
		exportFile(graphic, image);
		System.out.println("Completed");
	}

	public static void main(String[] args) {
		//for(int counter = 0; counter < 5; counter++){
			generateBackground();
		//}
	}

}
