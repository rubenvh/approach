package other.rendering;

import other.engine.Other;
import processing.core.PImage;

public class PerspectiveImageDisplay extends BaseImageDisplay {

	
	public PerspectiveImageDisplay(Other theOther, PImage imageToShow, int background_color) {
		super(theOther, imageToShow, background_color);
	
	}
	
	@Override
	public void draw() {
				
		if (_theOther.get_context().get_regionselected())
		{
			super.draw();		
			
			double distance = _theOther.get_context().get_distance();
					    
			if (distance > 0)
			{
					
				float width = (float) ((distance / _maxDistance.get_value()) * maxWidth);
				float height = (float) ((distance / _maxDistance.get_value()) * maxHeight);
								
				_theOther.noFill();
				_theOther.stroke(255);
				_theOther.strokeWeight(2);
			    _theOther.rect(startX+(maxWidth-width)/2,(maxHeight-height)/2, width, height);
			    _theOther.line(startX, 0, startX+(maxWidth-width)/2, (maxHeight-height)/2);
			    _theOther.line(startX+maxWidth, 0, width+startX+(maxWidth-width)/2, (maxHeight-height)/2);
			    _theOther.line(startX, maxHeight, startX+(maxWidth-width)/2, height+(maxHeight-height)/2);
			    _theOther.line(startX+maxWidth, maxHeight, width+startX+(maxWidth-width)/2, height+(maxHeight-height)/2);
			    
			}
			
			if (_theOther.get_context().get_debug())  {
				_theOther.fill(0, 0, 255);
				_theOther.text(String.format("distance ratio = %.2f", _maxDistance), startX+10, 10);
			}
		}
	}

	@Override
	public void keyPressed() {
		super.keyPressed(); 
	}
	

	@Override
	public void keyReleased() {
		super.keyReleased();
	}

	@Override
	public void mouseReleased() {
		super.mouseReleased();
	}
	
}
