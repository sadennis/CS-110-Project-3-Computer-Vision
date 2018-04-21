import java.awt.Color;

public class ImgTester {

	public static void main(String[] args) {
		
		ImageUtils utils = new ImageUtils();
		Color[][] orig = utils.loadImage("src/LennaCV.png");
		
		utils.addImage(orig, "Original");
		Color[][] invert=invert(orig);
		utils.addImage(invert, "Color Inversion");
		Color[][] low=LowestColorIncrease(orig);
		utils.addImage(low, "Lowest Color Increase");
		Color[][] middle=MiddleColorIncrease(orig);
		utils.addImage(middle, "Middle Color Increase");
		Color[][] high=HighColorIncrease(orig);
		utils.addImage(high, "High Color Increase");
		Color[][] sepia=Sepia(orig);
		utils.addImage(sepia, "Sepia");
		Color[][] gray=GrayScale(orig);
		utils.addImage(gray, "GrayScale");
		
		utils.display();
	}
	
	//Inverts the RGB values by subtracting them from 255, creating a color inversion effect.
	public static Color[][] invert(Color[][] Image) {
		Color[][] edit = ImageUtils.cloneArray(Image);
		for (int i = 0;i < edit.length;i++) {
			for (int j = 0;j < edit[i].length;j++) {
				Color pixel = edit[i][j];
				int r = pixel.getRed();
				int g = pixel.getGreen();
				int b = pixel.getBlue();
				int editr = 256 - r;
				int editg = 256 - g;
				int editb = 256 - b;
				
				edit[i][j] = new Color(editr, editg, editb);
			}
		}
		return edit;
	}
	
	//Applies a Sepia tone to the image by putting the RGB values through a formula and taking the integer value.
	public static Color[][] Sepia(Color[][] Image) {
		Color[][] edit = ImageUtils.cloneArray(Image);
		for (int i = 0;i < edit.length;i++) {
			for (int j = 0; j< edit[i].length;j++) {
				Color pixel = edit[i][j];
				int r = pixel.getRed();
				int g = pixel.getGreen();
				int b = pixel.getBlue();
				double editr = (0.394*r)+(0.769*g)+(0.189*b);
				double editg = (0.349*r)+(0.686*g)+(0.168*b);
				double editb = (0.272*r)+(0.543*g)+(0.131*b);
				int roundr = (int) (editr*1000000)/1000000;
				int roundg = (int) (editg*1000000)/1000000;
				int roundb = (int) (editb*1000000)/1000000;
				int R = 0;
				int G = 0;
				int B = 0;
				
				if (roundr>255) {
					R = 255;
				}
				
				else {
					R = roundr;
				}
				
				if (roundg>255) {
					G = 255;
				}
				
				else {
					G = roundg;
				}
				
				if (roundb>255) {
					B = 255;
				}
				
				else {
					B = roundb;
				}
				
				edit[i][j] = new Color(R, G, B);
			}
		}
		return edit;
	}
	
	//GrayScales the image by taking the average of the RGB values and using them for each color.
	public static Color[][] GrayScale(Color[][] Image) {
		Color[][] edit = ImageUtils.cloneArray(Image);
		for (int i = 0;i < edit.length;i++) {
			for (int j = 0;j < edit[i].length;j++) {
				Color pixel = edit[i][j];
				int r = pixel.getRed();
				int g = pixel.getGreen();
				int b = pixel.getBlue();
				int avg = (r + g + b)/3;
				
				edit[i][j] = new Color(avg,avg,avg);
			}
		}
		return edit;
	}
	
	//Takes the least dominant color in a pixel and increases it, creating a strange effect.
	public static Color[][] LowestColorIncrease(Color[][] Image) {
		Color[][] edit = ImageUtils.cloneArray(Image);
		for (int i = 0;i < edit.length;i++) {
			for (int j = 0; j < edit[i].length;j++) {
				Color pixel = edit[i][j];
				int r = pixel.getRed();
				int g = pixel.getGreen();
				int b = pixel.getBlue();	
				int editr = 0;
				int editg = 0;
				int editb = 0;
				
				if (r<b && r<g) {
					if (r<192) {
						editr = r+64;
						editg = g;
						editb = b;
					}	
						
					else {
						editr = r;
						editg = g;
						editb = b;
					}
				}
					
				else if (g<r && g<b) {
					if (g<192) {
						editr = r;
						editg = g+64;
						editb = b;
					}
				
					else {
						editr = r;
						editg = g;
						editb = b;
					}
				}
			
				else if (b<r && b<g) {
					if (b<192) {
						editr = r;
						editg = g;
						editb = b+64;
					}
				
					else {
						editr = r;
						editg = g;
						editb = b;
					}
				}
				edit[i][j]=new Color(editr, editg, editb);
			}
		}
		return edit;
	}

	//Takes the color that is neither least or most dominant in a pixel and increases it, creating an interesting effect.
	public static Color[][] MiddleColorIncrease(Color[][] Image) {
		Color[][] edit = ImageUtils.cloneArray(Image);
		for (int i = 0;i < edit.length;i++) {
			for (int j = 0; j < edit[i].length;j++) {
				Color pixel = edit[i][j];
				int r = pixel.getRed();
				int g = pixel.getGreen();
				int b = pixel.getBlue();	
				int editr = 0;
				int editg = 0;
				int editb = 0;
				
				if ((r<g && r>b) || (r<b && r>g)) {
					if (r<192) {
						editr = r+64;
						editg = g;
						editb = b;
					}	
						
					else {
						editr = r;
						editg = g;
						editb = b;
					}
				}
					
				else if ((g<r && g>b)||(g<b && g>r)) {
					if (g<192) {
						editr = r;
						editg = g+64;
						editb = b;
					}
				
					else {
						editr = r;
						editg = g;
						editb = b;
					}
				}
			
				else if ((b<r && b>g)||(b>r && b<g)) {
					if (b<192) {
						editr = r;
						editg = g;
						editb = b+64;
					}
				
					else {
						editr = r;
						editg = g;
						editb = b;
					}
				}
				edit[i][j]=new Color(editr, editg, editb);
			}
		}
		return edit;
	}
	
	//Takes the most dominant color in a pixel and increases it, creating a "color enhancement" effect.
	public static Color[][] HighColorIncrease(Color[][] Image) {
		Color[][] edit = ImageUtils.cloneArray(Image);
		for (int i = 0;i < edit.length;i++) {
			for (int j = 0; j < edit[i].length;j++) {
				Color pixel = edit[i][j];
				int r = pixel.getRed();
				int g = pixel.getGreen();
				int b = pixel.getBlue();	
				int editr = 0;
				int editg = 0;
				int editb = 0;
				
				if (r>g && r>b) {
					if (r<192) {
						editr = r+64;
						editg = g;
						editb = b;
					}	
						
					else {
						editr = r;
						editg = g;
						editb = b;
					}
				}
					
				else if (g>r &&g>b) {
					if (g<192) {
						editr = r;
						editg = g+64;
						editb = b;
					}
				
					else {
						editr = r;
						editg = g;
						editb = b;
					}
				}
			
				else if (b>r && b>g) {
					if (b<192) {
						editr = r;
						editg = g;
						editb = b+64;
					}
				
					else {
						editr = r;
						editg = g;
						editb = b;
					}
				}
				edit[i][j]=new Color(editr, editg, editb);
			}
		}
		return edit;
	}
}
