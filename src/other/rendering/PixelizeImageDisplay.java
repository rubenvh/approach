package other.rendering;

import other.engine.Other;
import processing.core.PImage;
import ruben.common.state.IParameter;

public class PixelizeImageDisplay extends BaseImageDisplay {

	private int _wbins;
	private int _hbins;
	private int _xbins;
	private int _ybins;
	private int _r;
	private int _g;
	private int _b;
	private int _x;
	private int _y;
	private int _index;

	public PixelizeImageDisplay(Other theOther, PImage imageToShow, int background_color, IParameter<Float> distanceRatio) {
		super(theOther, imageToShow, background_color, distanceRatio);	
		
		
	}
	
	@Override
	public void draw() {
				
		if (_theOther.get_context().get_regionselected())
		{
			super.draw();		
	
			double distance = _theOther.get_context().get_distance();
				
		
			int pixels = 1;
			if (distance > 0)
			{
				
				pixels = (int)Math.max(1, Math.exp(distance/_maxDistance.get_value()));
				pixelize(pixels, _image);
				_theOther.image(_copy, startX, 0, maxWidth, maxHeight);
			}
			
			if (_theOther.get_context().get_debug())  {
				
				_theOther.fill(0, 0, 255);
				_theOther.text(String.format("distance ratio = %.2f", _maxDistance.get_value()), startX+10, 10);
				_theOther.text(String.format("distance = %.2f", distance/_maxDistance.get_value()), startX+10, 20);			
				_theOther.text(String.format("wbins = %d, hbins = %d, pixels = %d", Math.max(1, _image.width / pixels), Math.max(1, _image.height / pixels), pixels), startX+10, 30);
				
			}
		}
	}

	
	private PImage pixelize(int pixels, PImage i)
	{
		_wbins = Math.max(1, i.width / pixels);
		_hbins = Math.max(1, i.height / pixels);
		_xbins = i.width/_wbins;
		_ybins = i.height/_hbins;
		
		i.loadPixels();
		_copy.loadPixels();		
		
		for (int xbin = 0; xbin < _xbins; xbin++) {
			for (int ybin = 0; ybin < _ybins; ybin++) {
				
				_r = 0;
				_g = 0;
				_b = 0;
				
				//calculate average color
				for (int offsetx = 0; offsetx < _wbins; offsetx++ ) {
					for (int offsety = 0; offsety < _hbins; offsety++) {
						
						_x = xbin*_wbins+offsetx;
						_y = ybin*_hbins+offsety;
												
						_index = _y*i.width+_x;
						
						if (_index >= i.pixels.length) continue;
						
				    	_r += _theOther.red(i.pixels[_index]);
						_g += _theOther.green(i.pixels[_index]);
						_b += _theOther.blue(i.pixels[_index]);
						
					}
				}
				
				int avgColor = _theOther.color(_r/(_wbins*_hbins),_g/(_wbins*_hbins),_b/(_wbins*_hbins)); 
			
				
				// set to average color
				for (int offsetx = 0; offsetx < _wbins; offsetx++ ) {
					for (int offsety = 0; offsety < _hbins; offsety++) {
						
						_x = xbin*_wbins+offsetx;
						_y = ybin*_hbins+offsety;
						
						_index = _y*_copy.width+_x;
						
						if (_index >= _copy.pixels.length) continue;
						_copy.pixels[_index] = avgColor;
						
						
					}
				}
				
				_copy.updatePixels();
				
			}
		}
		
		return _copy;		
	}
	
//	@Override
//	public void keyPressed() {
//		if (_theOther.key == ruben.common.keyboard.KeyConvertor.get_char("w")) {
//			_maxDistance = Math.max(10, _maxDistance-10);
//		} else if (_theOther.key ==  ruben.common.keyboard.KeyConvertor.get_char("c")) {
//			_maxDistance = Math.min(1000, _maxDistance+10);
//		} 
//	}
	

	@Override
	public void keyReleased() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased() {
		// TODO Auto-generated method stub

	}


}
