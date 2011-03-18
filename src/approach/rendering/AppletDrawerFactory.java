package approach.rendering;

import approach.engine.Other;
import processing.core.PImage;

public class AppletDrawerFactory {
	
	public BaseImageDisplay create_image_display(String display, Other theOther, PImage image, int background_color) {
		
		
		if (display.compareTo("pixelize") == 0) {
			return new PixelizeImageDisplay(theOther, image, background_color, theOther.get_context().get_pixel_distance_ratio());
		} else if (display.compareTo("zoom") == 0) {
			return new ZoomImageDisplay(theOther, image, background_color, theOther.get_context().get_zoom_distance_ratio());
		}else if (display.compareTo("perspective") == 0) {
			return new PerspectiveImageDisplay(theOther, image, background_color); 
		} else {
			return null;
		}
		
	}

}
