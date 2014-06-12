package org.telosys.tools.eclipse.plugin.editors.dsl.common;

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

	public final static RGB ENTITY_COLOR = new RGB(255,0,0);
	public final static RGB VALIDATION_COLOR = new RGB(0,50,200);
	public final static RGB COMMENT_COLOR = new RGB(50,200,50);
	public final static RGB TYPE_COLOR = new RGB(153,0,153);
	public final static RGB STRING_COLOR = new RGB(0,50,200);
	public final static RGB DEFAULT_COLOR = new RGB(0,0,0);
	
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
