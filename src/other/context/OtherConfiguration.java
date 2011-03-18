package other.context;

import other.engine.Other;
import ruben.common.configuration.ConfigurationFile;
import ruben.common.configuration.IConfigurationFile;

public class OtherConfiguration implements IOtherConfiguration {

	private IConfigurationFile _config;
	
	public OtherConfiguration(String filename)
	{
		_config = new ConfigurationFile(Other.get_instance(), filename);
	}
	
	
	public int get_height() {
		
		return Integer.parseInt(_config.get("height"));
	}

	
	public int get_width() {
		
		return Integer.parseInt(_config.get("width"));
	
	}
	
	
	public int get_videoheight() {
		
		return Integer.parseInt(_config.get("videoheight"));
	}

	
	public int get_videowidth() {
		
		return Integer.parseInt(_config.get("videowidth"));
	
	}

	
	public String get_movie_file() {
		
		return _config.get("movie");
	
	}

	
	public boolean get_use_camera() {
		
		return _config.get("use_camera").compareTo("true")==0; 
	}

	
	public int get_camera_number() {
		return Integer.parseInt(_config.get("camera_number"));
	}

}
