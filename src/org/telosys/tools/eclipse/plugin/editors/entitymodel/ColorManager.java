package org.telosys.tools.eclipse.plugin.editors.entitymodel;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * Provides a color for every content.
 *
 */
public class ColorManager {

	public final static RGB entityColor = new RGB(255,0,0);
	public final static RGB validationColor = new RGB(0,50,200);
	public final static RGB commentColor = new RGB(50,200,50);
	public final static RGB typeColor = new RGB(153,0,153);
	public final static RGB stringColor = new RGB(0,50,200);
	public final static RGB defaultColor = new RGB(0,0,0);
	
    protected Map<RGB,Color> fColorTable = new HashMap<RGB,Color>(10);

    public void dispose() {
            for ( Color color : fColorTable.values() ) {
                    color.dispose() ;
            }
    }
    
    public Color getColor(RGB rgb) {
            Color color = (Color) fColorTable.get(rgb);
            if (color == null) {
                    color = new Color(Display.getCurrent(), rgb);
                    fColorTable.put(rgb, color);
            }
            return color;
    }
}
