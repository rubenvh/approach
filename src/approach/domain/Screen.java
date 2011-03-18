package approach.domain;

import ruben.common.datastructures.Location;
import ruben.common.processing.applet.BasePApplet;

public class Screen {
	
	Location _start;
	Location _end;
	
	public Screen(){
		this(0, 0, 0, 0);
	}
	
	public Screen(int x1, int y1, int x2, int y2){
		_start = new Location(x1, y1);
		_end = new Location(x2, y2);
	}
	public Screen(float x1, float y1, float x2, float y2){
		_start = new Location(Math.round(x1), Math.round(y1));
		_end = new Location(Math.round(x2), Math.round(y2));
	}
	
	
	public Location get_start() { return _start; }
	public Location get_end() { return _end; }
	
	public double distance_to(Location loc) {
		
		return distance_to(loc.get_x(), loc.get_y());
	}
	
	public double distance_to(float x, float y) {
		
		return
			Math.abs((_end.get_x()-_start.get_x())*(_start.get_y()-y)-(_start.get_x()-x)*(_end.get_y()-_start.get_y())) / 
			Math.sqrt((_end.get_x()-_start.get_x())*(_end.get_x()-_start.get_x())+(_end.get_y()-_start.get_y())*(_end.get_y()-_start.get_y()));
		
	}
	
	public void draw_screen(BasePApplet pApplet) {
		pApplet.stroke(0, 255, 0);
		pApplet.strokeWeight(1);
		pApplet.line(this.get_start().get_x(), this.get_start().get_y(), 
				this.get_end().get_x(), this.get_end().get_y());
	}

}
