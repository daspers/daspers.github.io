import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Component;
import java.io.IOException;
import java.io.File;

class Compress{
	Compress(BufferedImage image){
		int width = image.getWidth();
		int height = image.getHeight();
		int [] map = new int[width*height];
		image.getRGB(0, 0, width, height, map, 0, width);
		for(int i=0;i<height*width;++i)
			map[i] &= 0xFFFFFF;
		QuadTree img(map, Point(0, 0), Point(height, width));
	}
	
	public static void main(String[] args){
		String file;
		if(args.length == 0)
			file = (new Scanner(System.in)).nextLine();
		else
			file = args[0];
		try{
			BufferedImage image = ImageIO.read(new File(file));
			new Compress(image);
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}