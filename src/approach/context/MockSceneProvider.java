package approach.context;

import java.util.ArrayList;
import java.util.Collection;

import approach.domain.Scene;


public class MockSceneProvider implements ISceneProvider {

	
	public Collection<Scene> get_scenes() {
		
		Collection<Scene> result = new ArrayList<Scene>(3);
		

		result.add(new Scene(5000, "pixelize", true, "", 0));
		result.add(new Scene(5000, "zoom", false, "test.png", 0));
		result.add(new Scene(5000, "zoom", true, "", 255));
		result.add(new Scene(5000, "pixelize", false, "test.png", 0));
		
		return result;
	}

}
