package org.hkmi2.aagbl;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A variant of {@link GridBagLayout} that can be initialized using ascii art.
 * @author Harry
 *
 */
public class AsciiArtGridBagLayout
extends GridBagLayout
{
  GridBagLayoutBuilder gblb;
  HashMap<String, Component> componentsByName = new HashMap<>();
  HashMap<String, GridBagConstraints> constraintsByName = new HashMap<String, GridBagConstraints>();
  List<CRect> crects;
  
  /**
   * For serialization
   */
  private static final long serialVersionUID = 1L;
  
  String asciiArt;

  /**
   * Using an array of objects, make a HashMap.
   * This allows for a more compact initialization.
   * Ex :
   * <pre>
   * Object[] objs = ["B0", B0, "L1", L1];
   * HashMap&lt;String, Component&gt; hm = AsciiArtGridBagLayout.makeMap(objs);
   * </pre>
   * @param objs An array of String, Component, String, Component ...
   * @return The HashMap of Components by name
   */
  public static HashMap<String, Component> makeMap(Object[] objs) {
    HashMap<String, Component> hm = new HashMap<>();
    for (int i  = 0; i  < objs.length; i += 2) {
      hm.put((String)objs[i], (Component)objs[i+1]);
    }
    return hm;
  }
  
  public AsciiArtGridBagLayout(String asciiArt) 
  throws LayoutParseException
  {
    setAsciiArt(asciiArt);
  }

  public AsciiArtGridBagLayout() {
  }
  
  public String getAsciiArt() {
    return asciiArt;
  }

  public void setAsciiArt(String asciiArt) 
  throws LayoutParseException
  {
    this.asciiArt = asciiArt;
    LayoutParser parser = new LayoutParser();
    List<char[]> lines = parser.readSpec(new BufferedReader(new StringReader(asciiArt)));
    parser.parseSpec(lines);
    gblb = new GridBagLayoutBuilder(parser.getCRects());
    crects = parser.getCRects();
    //S ystem.out.println("Rects :");
    for (CRect r : crects) {
      //S ystem.out.println("Rect"+r);
    }
  }

  public void setConstraints(String componentName, Component comp) {
    GridBagConstraints cons = gblb.makeGridBagConstraints(componentName);
    super.setConstraints(comp, cons);
    //comp.setPreferredSize(new Dimension(0,0));
    constraintsByName.put(componentName, cons);
    componentsByName.put(componentName, comp);
  }
  
  /**
   * Set the constraints for all the components of the map
   * @param cm The component by name map
   */
  public void setConstraints(Map<String, Component> cm) {
    for (String name : cm.keySet()) {
      setConstraints(name, cm.get(name));
    }
  }
  
  public int getMaxX() { return gblb.maxx; }
  
  public int getMaxY() { return gblb.maxy; }
  
  public Component getComponent(String name) { return componentsByName.get(name); }

  public GridBagConstraints getConstraints(String name) { return constraintsByName.get(name); }
  
  public List<Component> getAllComponents() { return new ArrayList<>(componentsByName.values()); }
  
  public void addAllComponentsTo(Container cr) {
    for (String k : componentsByName.keySet()) {
      cr.add(componentsByName.get(k), constraintsByName.get(k));
    }
  }
  
  public void setWeightx(String rectNameList, double val) {
    gblb.setWeightx(rectNameList, val); 
    resetConstraints(rectNameList);
  }
  
  public void setWeighty(String rectNameList, double val) {
    gblb.setWeighty(rectNameList, val); 
    resetConstraints(rectNameList);
  }
  
  private void resetConstraints(String rectNameList) {
    String[] names = GridBagLayoutBuilder.split(rectNameList);
    for (String name : names) {
      Component comp = getComponent(name);
      if (comp != null) setConstraints(name, comp);
    }
  }
  
  public void setIpadx(String rectNameList, int val) { 
    gblb.setIpadx(rectNameList, val);
    resetConstraints(rectNameList);
  }
  
  public void setIpady(String rectNameList, int val) { 
    gblb.setIpady(rectNameList, val);
    resetConstraints(rectNameList);
  }
  
  public List<CRect> getCRects() { return crects; }
  
}
