package approach.engine;

import java.util.Iterator;
import java.util.Vector;

import approach.context.ConfigSceneProvider;
import approach.context.IOtherConfiguration;
import approach.context.IOtherContext;
import approach.context.ISceneProvider;
import approach.context.OtherConfiguration;
import approach.context.OtherContext;
import approach.domain.Scene;
import approach.rendering.AppletDrawerFactory;
import approach.rendering.BaseImageDisplay;
import approach.rendering.MotionDetection;
import approach.rendering.RegionOfInterestSelection;
import approach.rendering.ScreenSelection;

import processing.core.*;
import ruben.common.processing.applet.BasePApplet;
import ruben.common.processing.applet.IAppletDrawer;
import ruben.common.processing.applet.IPApplet;
import ruben.common.processing.video.IWindowedImageSource;
import ruben.common.processing.video.OpenCVVideoSource;
import ruben.common.state.Parameter;

@SuppressWarnings("serial")
public class Other extends BasePApplet implements IPApplet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PApplet.main(new String[] {
				"display=1",
				"--bgcolor=#000000", "--present-stop-color=#000000",
				"--exclusive",
				"--present",
				"approach.engine.Other" });

	}

	static Other _instance = null;

	public static Other get_instance() {
		return _instance;
	}

	
	IOtherConfiguration _config;
	ISceneProvider _scene_provider;
	Iterator<Scene> _current_scene;
	IWindowedImageSource _source;
	IOtherContext _context;
	PImage _current_image;
		
	BaseImageDisplay _imageDisplay; 
	PImage _imageToDisplay;
	int _timestamp;
	
	public IOtherConfiguration get_config() { return _config; }
	public IOtherContext get_context() { return _context; }
	public PImage get_current_image() { return _current_image; }
	public IWindowedImageSource get_videosource() { return _source; }
	
	public void setup() {
			
		_instance = this;
		
		_config = new OtherConfiguration("app.config");
		size(_config.get_width(), _config.get_height());
			
		
		// get scenes + set iterator
		_scene_provider = new ConfigSceneProvider("scenes.config");
		_current_scene = _scene_provider.get_scenes().iterator();
		
		if (_config.get_use_camera())
			_source = OpenCVVideoSource.Create(this, 2, _config.get_videowidth(), _config.get_videoheight(), _config.get_camera_number(), new Parameter<Integer>(20));
		else
			_source = OpenCVVideoSource.Create(this, 2, _config.get_videowidth(), _config.get_videoheight(), _config.get_movie_file(), new Parameter<Integer>(20));
		
		_context = new OtherContext();
		
//		String[] fontList = PFont.list();
//		println(fontList);
		
		PFont font = createFont("courier", 12, false);
		textFont(font);
	
		super.setup();
		
		_imageToDisplay = loadImage("test.png");
		next_scene();
	}
	
	protected void load_applet_drawers(){
		_drawers = new Vector<IAppletDrawer>(4);
		_drawers.add(new ScreenSelection(this));
		_drawers.add(new RegionOfInterestSelection(this));
		_drawers.add(new MotionDetection(this));
		
	}
	
	private void next_scene()
	{
		if (_current_scene.hasNext()) {
			
			load_scene();	
		}
		else
		{
			_current_scene = _scene_provider.get_scenes().iterator();
			next_scene();
		}	
	}
	private void load_scene() {
		Scene scene = _current_scene.next();
		_context.set_use_camera(scene.use_camera);
		if (scene.image.compareTo("0")!= 0) _imageToDisplay = loadImage(scene.image);
		_imageDisplay = (new AppletDrawerFactory()).create_image_display(scene.image_display, this, _imageToDisplay, scene.background);	
		
		if (_drawers.size() == 4) {
			_drawers.set(3, _imageDisplay);
		}
		else {
			_drawers.addElement(_imageDisplay);
		}
		
		_timestamp = millis() + scene.duration;
	}


	@Override
	public void draw() {
	
		background(0);
		
		if (millis() > _timestamp && _context.get_screenselected() && _context.get_regionselected()) next_scene();
			
		_source.step();		
		_current_image = _source.get_current_image();
		
		if (_current_image != null) {
			
			if (_context.get_use_camera()) _imageDisplay.set_image(_current_image);
			
			super.draw();
		}
		
		if (_context.get_debug()) {
			fill(0, 0, 255);
			text(String.format("%d / %d", millis(), _timestamp), 10, 30);
		}
	}
	
	@Override
	public void keyPressed() {
		
		super.keyPressed();
		
		if (key == ruben.common.keyboard.KeyConvertor.get_char("=")) {
			_context.toggle_debug();
		} 
	}

	
}
