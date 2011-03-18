package other.domain;

public class Scene {
	
	public int duration;
	public String image_display;
	public boolean use_camera;
	public String image;
	public int background;
	
	public Scene(int duration, String image_display, boolean use_camera, String image, int background) {
		this.duration = duration;
		this.image_display = image_display;
		this.use_camera = use_camera;
		this.image = image;
		this.background = background;
	}
	
}
