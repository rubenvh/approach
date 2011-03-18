package approach.rendering;

import approach.engine.Other;
import processing.core.PImage;
import ruben.common.state.IParameter;
import ruben.common.state.Parameter;

public class BaseImageDisplay extends OtherAppletDrawer {

	protected PImage _image;
	protected int maxWidth, maxHeight, startX;
	protected IParameter<Float> _maxDistance;
	protected PImage _copy;
	protected int background_color;
	
	public BaseImageDisplay(Other theOther, PImage imageToShow, int background_color) {
		this(theOther,imageToShow,background_color, new Parameter<Float>((float)theOther.get_videosource().get_width()));
	}
	public BaseImageDisplay(Other theOther, PImage imageToShow, int background_color, IParameter<Float> distanceRatio) {
		super(theOther);
		set_image(imageToShow);
		this.background_color = background_color; 
		// for vertical screens!!
		_maxDistance = distanceRatio;
	}
	
	
	public void set_image(PImage image)
	{
		_image = image;
		_copy = image == null? null: image.get();
	}

	
	public void draw() {
		
		if (_theOther.get_context().get_debug()) 
		{
			maxWidth = _theOther.width - _theOther.get_videosource().get_width();
			maxHeight = _theOther.height;
			startX = _theOther.get_videosource().get_width();
		}
		else
		{
			maxWidth = _theOther.width;
			maxHeight = _theOther.height;
			startX = 0;
		}
		
		_theOther.fill(background_color);
		_theOther.noStroke();
	    _theOther.rect(startX,0, maxWidth, maxHeight);
	}
	
	
	public void keyPressed() {
		if (_theOther.key == ruben.common.keyboard.KeyConvertor.get_char("w")) {
			_maxDistance.set_value(Math.max(10, _maxDistance.get_value()-10));
		} else if (_theOther.key ==  ruben.common.keyboard.KeyConvertor.get_char("c")) {
			_maxDistance.set_value(Math.min(1000, _maxDistance.get_value()+10));
		} 
	}
	
	
	public void keyReleased() {	}
	
	public void mousePressed() { }
	
	public void mouseReleased() { }

}
