package approach.rendering;

import java.util.ArrayList;

import approach.domain.Screen;
import approach.engine.Other;

import processing.core.PConstants;
import processing.core.PImage;
import ruben.common.processing.domain.Region;
import s373.flob.*;

public class MotionDetection extends OtherAppletDrawer {

	PImage _cur_image;
	PImage _diff_image;
	PImage _edge_image;
	Flob _flob;
	ArrayList<trackedBlob> _blobs;
	
	public MotionDetection(Other theOther) {
		super(theOther);
		
		_flob = new Flob(_theOther.get_videosource().get_width(), _theOther.get_videosource().get_height(), 
				_theOther.get_videosource().get_width(), _theOther.get_videosource().get_height());
		_blobs = new ArrayList<trackedBlob>();
		
		_flob.setThresh(20).setSrcImage(0)
		  .setBlur(0).setOm(0).setFade(25).setMirror(true,false);
		_flob.settrackedBlobLifeTime(600);  
	}
	
	

	private int _edgeThreshold = 1;
	private int _dilation = 3;
	
	
	public void draw() {
		
		if (_theOther.get_context().get_regionselected())
		{
			_cur_image = _theOther.get_current_image();			
			if (_theOther.get_context().get_debug()) _theOther.image(_cur_image, 0, 0);		
			
			_roi = _theOther.get_context().get_region_of_interest();
			_diff_image = _theOther.createImage(_theOther.get_videosource().get_width(), _theOther.get_videosource().get_height(), PConstants.ARGB);
			_diff_image.copy(_theOther.get_videosource().diff(Other.get_instance().get_context().get_region_of_interest()), _roi.get_start().get_x(), _roi.get_start().get_y(), _roi.width(), _roi.height(), _roi.get_start().get_x(), _roi.get_start().get_y(), _roi.width(), _roi.height());
			_diff_image.filter(PConstants.THRESHOLD, _edgeThreshold/10f);
			_diff_image.filter(PConstants.ERODE);
			for (int i=1; i<_dilation; i++) {
				_diff_image.filter(PConstants.DILATE);
			}
			
			if (_theOther.get_context().get_debug()) _theOther.image(_diff_image, 0, _theOther.get_videosource().get_height());	
			
//			_edge_image = edges(_theOther.get_current_image());
//			_edge_image.filter(PConstants.THRESHOLD,  _edgeThreshold/10f);
//			_theOther.image(_edge_image, 0, 240);	
			
			
			
			_screen = _theOther.get_context().get_screen();
			_distance = _theOther.get_context().get_distance();
			
			
			_blobs = _flob.track(_diff_image);
			for(int i = 0; i < _blobs.size(); i++) {
			    _tb = _flob.getTrackedBlob(i);
			   
			   
			    if (i == 0 || _screen.distance_to(_tb.cx, _tb.cy) < _distance)
			    {
			    	//selectedBlob = tb;
			    	_distance = _screen.distance_to(_tb.cx, _tb.cy);
			    }
			    
			    if (_theOther.get_context().get_debug()) 
			    {
				    _txt = "id: "+_tb.id+" time: "+_tb.presencetime+" ";
				    _theOther.strokeWeight(1);
				    _theOther.stroke(0, 255, 0);
				    _theOther.fill(220,220,255,100);
				    _theOther.rect(_tb.cx,_tb.cy,_tb.dimx,_tb.dimy);
				    _theOther.fill(0,255,0,200);
				    _theOther.rect(_tb.cx,_tb.cy, 5, 5); 
				    _theOther.fill(0);
				    _theOther.line(_tb.cx, _tb.cy, _tb.cx + _tb.velx * _velmult ,_tb.cy + _tb.vely * _velmult ); 
				    _theOther.text(_txt,_tb.cx -_tb.dimx*0.10f, _tb.cy + 5f);
			    }
			}
			
			_theOther.get_context().set_distance(_distance);
			
//			_blobs = _flob.calc(_diff_image);
//			int numblobs = _blobs.size();//flob.getNumBlobs();  
//
//			  for(int i = 0; i < numblobs; i++) {
//
//			    ABlob ab = (ABlob)_flob.getABlob(i); 
//			    //trackedBlob tb = (trackedBlob)_flob.getTrackedBlob(i); 
//			    //now access all blobs fields.. float tb.cx, tb.cy, tb.dimx, tb.dimy...
//
//			    // test blob coords here    
//			    //b1.test(ab.cx,ab.cy, ab.dimx, ab.dimy);
//
//			    //box
//			    drawBlob(ab);
//			    //drawBlob(tb);
//			  }
			
			
//			
//			ocv.cvOpenCVVideoSource ocv = (OpenCVVideoSource) _theOther.get_videosource();.threshold(80);
//			Blob[] blobs = ocv.cv.blobs(10, ocv.get_width()*ocv.get_height()/2, 100, true, OpenCV.MAX_VERTICES*4);
//			
//			// draw blob results
//		    for( int i=0; i<blobs.length; i++ ) {
//		        _theOther.beginShape();
//		        for( int j=0; j<blobs[i].points.length; j++ ) {
//		        	_theOther.vertex( blobs[i].points[j].x, blobs[i].points[j].y );
//		        }
//		        _theOther.endShape(PConstants.CLOSE);
//		    }

		    
			if (_theOther.get_context().get_debug()) 
			{

				_theOther.get_context().get_screen().draw_screen(_theOther);
				_theOther.get_context().get_region_of_interest().draw(_theOther);
				
				_theOther.fill(0, 0, 255);
				_theOther.text(String.format("edge threshold = %d | dilation = %d", _edgeThreshold, _dilation), 10, 10);
			}
		}
	}
	
	
	public void keyPressed() {
		if (_theOther.key == ruben.common.keyboard.KeyConvertor.get_char("a")) {
			_edgeThreshold = Math.max(0, _edgeThreshold-1);
		} else if (_theOther.key ==  ruben.common.keyboard.KeyConvertor.get_char("e")) {
			_edgeThreshold = Math.min(10, _edgeThreshold+1);
		}  else if (_theOther.key == ruben.common.keyboard.KeyConvertor.get_char("q")) {
			_dilation = Math.max(1, _dilation-1);
		} else if (_theOther.key ==  ruben.common.keyboard.KeyConvertor.get_char("d")) {
			_dilation = Math.min(100, _dilation+1);
		}
	}

	public void keyReleased() {
		// TODO Auto-generated method stub

	}

	public void mouseReleased() {
		// TODO Auto-generated method stub

	}
	
	
	
	
	private float _velmult = 100.0f;
	private double _distance;
	private Screen _screen;
	private Region _roi;
	private trackedBlob _tb;
	private String _txt;


	public void mousePressed() {
		// TODO Auto-generated method stub
		
	}


	public void cleanup()
	{
		// TODO Auto-generated method stub
		
	}


}
