package other.context;

import other.domain.Screen;
import ruben.common.processing.domain.Region;
import ruben.common.state.IParameter;
import ruben.common.state.Parameter;

public class OtherContext implements IOtherContext {

	Screen _screen = new Screen();
	boolean _screenSelected = false;
	Region _region = new Region();
	boolean _regionSelected = false;
	double _distance = 0f;
	boolean _debug = true;
	boolean _useCamera = false;
	IParameter<Float> _pixelDistanceRatio = new Parameter<Float>(60f);
	IParameter<Float> _zoomDistanceRatio = new Parameter<Float>(320f);
	
	
	public Screen get_screen() {
		return _screen;
	}

	
	public boolean get_screenselected() {
		return _screenSelected;
	}

	
	public void set_screenselected() {
		_screenSelected = true;
	}

	
	public void set_screen(Screen screen) {
		_screen = screen;
	}

	
	public double get_distance() {
		return _distance;
	}

	
	public void set_distance(double d) {
		_distance = d;
	}

	
	public boolean get_debug() {
		return _debug;
	}

	
	public void toggle_debug() {
		_debug = !_debug;
	}

	
	public boolean get_use_camera() {
		return _useCamera;
	}

	
	public boolean toggle_use_camera() {
		return _useCamera = !_useCamera;
	}

	
	public void set_use_camera(boolean use) {
		_useCamera = use;
	}

	
	public Region get_region_of_interest() {
		return _region;
	}

	
	public boolean get_regionselected() {
		return _regionSelected;
	}

	
	public void set_region_of_interest(Region region) {
		_region = region;
	}

	
	public void set_regionselected() {
		_regionSelected = true;
	}


	@Override
	public IParameter<Float> get_pixel_distance_ratio() {
		return _pixelDistanceRatio;
	}


	@Override
	public IParameter<Float> get_zoom_distance_ratio() {
		return _zoomDistanceRatio;
	}


	

}
