package other.rendering;

import other.engine.Other;
import processing.core.PImage;
import ruben.common.state.IParameter;

public class ZoomImageDisplay extends BaseImageDisplay {

	private double _distance;
	private float _curWidth;
	private float _curHeight;

	public ZoomImageDisplay(Other theOther, PImage imageToShow, int background_color, IParameter<Float> distanceRatio) {
		super(theOther, imageToShow, background_color,distanceRatio);
	
	}
	
	@Override
	public void draw() {
				
		if (_theOther.get_context().get_regionselected())
		{
			super.draw();		
			
			_distance = _theOther.get_context().get_distance();
					    
			if (_distance > 0)
			{
					
				_curWidth = (float) ((_distance / _maxDistance.get_value()) * maxWidth);
				_curHeight = (float) ((_distance / _maxDistance.get_value()) * maxHeight);
						
			    _theOther.image(_image, startX+(maxWidth-_curWidth)/2,(maxHeight-_curHeight)/2, _curWidth, _curHeight);
			    
			    //_image.filter(PConstants.BLUR, (maxWidth-width) / maxWidth);
			    //_theOther.image(_image, startX,0, maxWidth, maxHeight);
			    
			}
			
			if (_theOther.get_context().get_debug())  {
				_theOther.fill(0, 0, 255);
				_theOther.text(String.format("distance ratio = %.2f", _maxDistance.get_value()), startX+10, 10);
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
