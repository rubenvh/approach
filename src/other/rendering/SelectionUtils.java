package other.rendering;

import ruben.common.datastructures.Location;

public class SelectionUtils {
	
	public static float[] calculate_coordinates(int selectionWidth, int selectionHeight, int actualWidth, int actualHeight, Location p1, Location p2) {
	
		float widthRatio = selectionWidth * 1.0f / actualWidth;
		float heightRatio = selectionHeight * 1.0f / actualHeight;

		float[] result = new float[4];
		result[0] = p1.get_x() / widthRatio;
		result[1] = p1.get_y() / heightRatio;
		result[2] = p2.get_x() / widthRatio;
		result[3] = p2.get_y() / heightRatio;
		
		return result;
	}
	

}
