package approach.rendering;

import approach.domain.Screen;
import approach.engine.Other;
import processing.core.PConstants;
import processing.core.PImage;
import ruben.common.datastructures.Location;
import ruben.common.processing.applet.IAppletDrawer;

public class ScreenSelection extends OtherAppletDrawer implements IAppletDrawer {

	public ScreenSelection(Other theOther) {
		super(theOther);
		_selected_screen = new Screen();
	}

	PImage _image;
	Screen _selected_screen;

	private boolean is_selecting_second() {
		return !_selected_screen.get_start().is_origin();
	}

	
	public void draw() {

		int width = _theOther.get_videosource().get_width();
		int height = _theOther.get_videosource().get_height();

		_image = _theOther.createImage(width, height, PConstants.RGB);
		_image.copy(_theOther.get_current_image(), 0, 0, width, height, 0, 0,
				width, height);
		_image.resize(_theOther.width, _theOther.height);

		_theOther.image(_image, 0, 0);
		screenSelection();

		if (is_selecting_second()) {
			draw_crosshair(_selected_screen.get_start().get_x(),
					_selected_screen.get_start().get_y());
			_theOther.stroke(0, 255, 0);
			_theOther.strokeWeight(1);
			_theOther.line(_selected_screen.get_start().get_x(),
					_selected_screen.get_start().get_y(), _theOther.mouseX,
					_theOther.mouseY);
		}

	}

	
	public void keyPressed() {
		// TODO Auto-generated method stub

	}

	
	public void keyReleased() {

		_selected_screen = new Screen();

	}

	
	public void mouseReleased() {

		if (!is_selecting_second()) {
			set_location(_selected_screen.get_start());
		} else {
			set_location(_selected_screen.get_end());
			_theOther.get_context().set_screenselected();

			float[] adjusted_coordinates = SelectionUtils.calculate_coordinates(_theOther.width, _theOther.height, _theOther.get_videosource().get_width(), _theOther.get_videosource().get_height(), _selected_screen.get_start(),  _selected_screen.get_end());
			
			_selected_screen = new Screen(adjusted_coordinates[0],adjusted_coordinates[1],adjusted_coordinates[2],adjusted_coordinates[3]);

			_theOther.get_context().set_screen(_selected_screen);
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
			text = "second";
		else
			text = "first";

		_theOther
				.text(
						String
								.format(
										"Select the screen's %s coordinate (%d,%d). Press a key to restart.",
										text, _theOther.mouseX,
										_theOther.mouseY), 10, 10);

		draw_crosshair(_theOther.mouseX, _theOther.mouseY);

	}

	private void draw_crosshair(int x, int y) {
		_theOther.stroke(255, 0, 0);
		_theOther.strokeWeight(2);
		_theOther.line(x - 10, y, x + 10, y);
		_theOther.line(x, y - 10, x, y + 10);
	}

	
	public void mousePressed() {
		// TODO Auto-generated method stub

	}

}
