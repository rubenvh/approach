package approach.context;

import approach.domain.Screen;
import ruben.common.processing.domain.Region;
import ruben.common.state.IParameter;

public interface IOtherContext {

	boolean get_screenselected();
	void set_screenselected();
	
	Screen get_screen();
	void set_screen(Screen screen);
	
	boolean get_regionselected();
	void set_regionselected();
	
	Region get_region_of_interest();
	void set_region_of_interest(Region region);
	
	double get_distance();
	void set_distance(double d);
	
	boolean get_debug();
	void toggle_debug();
	
	boolean get_use_camera();
	void set_use_camera(boolean use);
	boolean toggle_use_camera();
	
	IParameter<Float> get_pixel_distance_ratio();
	IParameter<Float> get_zoom_distance_ratio();
	
	
	
	
}
