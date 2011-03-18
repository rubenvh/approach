package approach.rendering;

import approach.engine.Other;
import processing.core.PConstants;
import processing.core.PImage;
import ruben.common.datastructures.Location;
import ruben.common.processing.applet.IAppletDrawer;
import ruben.common.processing.domain.Region;

public class RegionOfInterestSelection extends OtherAppletDrawer implements
		IAppletDrawer {

	public RegionOfInterestSelection(Other theOther) {
		super(theOther);
		_selected_region = new Region();
	}

	PImage _image;
	Region _selected_region;

	
	public void draw() {

		if (_theOther.get_context().get_screenselected()) {

			int width = _theOther.get_videosource().get_width();
			int height = _theOther.get_videosource().get_height();

			_image = _theOther.createImage(width, height, PConstants.RGB);
			_image.copy(_theOther.get_current_image(), 0, 0, width, height, 0,
					0, width, height);
			_image.resize(_theOther.width, _theOther.height);

			_theOther.image(_image, 0, 0);

			screenSelection();

			if (is_selecting_second()) {
				_theOther.stroke(0, 255, 0);
				_theOther.strokeWeight(1);
				_theOther.noFill();
				int x = _selected_region.get_start().get_x();
				int y = _selected_region.get_start().get_y();
				_theOther
						.rect(x, y, _theOther.mouseX - x, _theOther.mouseY - y);
			}
		}

	}

	
	public void keyPressed() {
	}

	
	public void keyReleased() {

		if (_theOther.get_context().get_screenselected()) {
			_selected_region = new Region();
		}
	}

	
	public void mousePressed() {

		if (_theOther.get_context().get_screenselected()) {

			set_location(_selected_region.get_start());

		}
	}

	
	public void mouseReleased() {

		if (_theOther.get_context().get_screenselected()
				&& is_selecting_second()) {

			set_location(_selected_region.get_end());
			_theOther.get_context().set_regionselected();

			
			float[] adjusted_coordinates = SelectionUtils.calculate_coordinates(_theOther.width, _theOther.height, _theOther.get_videosource().get_width(), _theOther.get_videosource().get_height(), _selected_region.get_start(),  _selected_region.get_end());
			
			_selected_region = new Region(adjusted_coordinates[0],adjusted_coordinates[1],adjusted_coordinates[2],adjusted_coordinates[3]);

			_theOther.get_context().set_region_of_interest(_selected_region);
			set_active_state(false);
		}
	}

	private void set_location(Location loc) {
		loc.set_x(_theOther.mouseX);
		loc.set_y(_theOther.mouseY);
	}

	private void screenSelection() {

		_theOther.fill(0, 0, 255);

		String text;
		if (is_selecting_second())
			text = "drag the mouse to the region's end position";
		else
			text = "click and drag to select a region of interest";

		_theOther.text(String.format("%s (%d,%d). Press a key to restart.",
				text, _theOther.mouseX, _theOther.mouseY), 10, 10);

		draw_crosshair(_theOther.mouseX, _theOther.mouseY);

	}

	private boolean is_selecting_second() {
		return !_selected_region.get_start().is_origin();
	}

	private void draw_crosshair(int x, int y) {
		_theOther.stroke(255, 0, 0);
		_theOther.strokeWeight(2);
		_theOther.line(x - 10, y, x + 10, y);
		_theOther.line(x, y - 10, x, y + 10);
	}

}
