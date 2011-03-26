package approach.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import approach.domain.Scene;
import approach.engine.Other;

import ruben.common.configuration.ConfigurationFile;
import ruben.common.configuration.IConfigurationFile;

public class ConfigSceneProvider implements ISceneProvider {

	IConfigurationFile _config;
	Collection<Scene> _scenes;
	
	public ConfigSceneProvider(String config)
	{		
		_config = new ConfigurationFile(Other.get_instance().loadStrings(config));
		
		Iterator<String> keys = _config.keys();
		_scenes = new ArrayList<Scene>(5);
		while (keys.hasNext())
		{
			String key = keys.next();
			String rawScene = _config.get(key);
			String[] parsedScene = rawScene.split(",");
			
			String image = parsedScene[3].compareTo("0")==0 ? "": parsedScene[3];
			Scene scene = new Scene(Integer.parseInt(parsedScene[0]),parsedScene[2], parsedScene[1].compareTo("true")==0, image, Integer.parseInt(parsedScene[4]));
			_scenes.add(scene);
		}
		
	}
	
	public Collection<Scene> get_scenes() {
		
		return _scenes;
	}

}
