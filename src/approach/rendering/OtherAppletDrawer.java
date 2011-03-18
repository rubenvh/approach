package approach.rendering;

import approach.engine.Other;
import ruben.common.processing.applet.IAppletDrawer;



public abstract class OtherAppletDrawer implements IAppletDrawer {

	protected Other _theOther;
	protected boolean _is_active = true;
	
	public boolean is_active() { return _is_active; }	
	public void set_active_state(boolean active) { _is_active = active; }
	
	public OtherAppletDrawer(Other theOther)
	{
		_theOther = theOther;
	}
	
	
	
	
	
}
