package org.hkmi2.aagbl;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LayoutParser
{
  public static final int FILL_HORIZ = 1;
  public static final int FILL_VERT = 2;
  
  public static boolean debugParser = false; //set to true to emit messages on System.out while parsing
  
  ArrayList<CRect> rects = new ArrayList<>();
  
  public ArrayList<CRect> getCRects() { return rects; }
  
  public LayoutParser() {
  }

  /**
   * Normalize format string :
   * - Trim the line
   * - Replace all '-' and '|' with '+'
   * @param line The line that must be normalized
   * @return The normalized line as an array of chars
   */
  public char[] normalize(String line) {
    char[] ca = line.trim().toCharArray();
    for (int i = 0; i  < ca.length; i++) {
      char c = ca[i];
      switch (c) {
      case '-':
      case '|':
        ca[i] = '+';
        break;
      default:
        //do nothing
      }
    }//for
    return ca;
  }
   
  /**
   * Step one : read all lines, ignoring the empty ones, and trimming the others.
   * @param specInput A BufferedReader that will be used to read the specification
   * @return A list of char arrays
   * @throws LayoutParseException If non-empty lines are not the same length 
   */
  public ArrayList<char[]> readSpec(BufferedReader specInput) 
  throws LayoutParseException
  {
    int lineNr = 1;
    int lineLength = -1;
    ArrayList<char[]> selectedLines = new ArrayList<>();
    Iterator<String> it = specInput.lines().iterator();
    while (it.hasNext()) {      
      char[] ca = normalize(it.next());
      if (ca.length > 0) {
        if (lineLength < 0) lineLength = ca.length;
        else if (ca.length != lineLength) {
          throw new LayoutParseException("Different line length at line " + lineNr);
        }
        selectedLines.add(ca); 
      }
      lineNr++;
    }
    return selectedLines;
  }//readSpec(...)
  
  /**
   * find a CRect whose last line is just above px and py
   * @param px position x
   * @param py position y
   * @return the CRect that is just above position or null if none found
   * */
  public CRect findCRect(int px, int py) {
    for (CRect  r : rects) {
      if (r.x == px && (r.y + r.h) == py) return r;
    }
    return null;
  }

  public boolean isNameStart(char c) {
    return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z');
  }
  
  public boolean isNameChar(char c) {
    return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z') || ('0' <= c && c <= '9');
  }
  
  public int evalFlags(String spec) {
    int flags = 0;
    for (char c : spec.toCharArray()) {
      if (c == '^') flags |= FILL_VERT;
      if (c == '<' || c == '>') flags |= FILL_HORIZ;
    }
    return flags;
  }
  
  public String removeFlags(String spec) {
    StringBuilder sb = new StringBuilder();
    for (char c : spec.toCharArray()) {
      if (c == '^' || c == '<' || c == '>') sb.append(' ');
      else sb.append(c);
    }
    return sb.toString();
  }
  
  public boolean isNameValid(String str) {
    if (str.length() < 1) return false;
    if (!isNameStart(str.charAt(0))) return false;
    for (int i = 1; i < str.length(); i++) { if (!isNameChar(str.charAt(i))) return false; }
    return true;
  }
  
  /**
   * Parse Ascii Art specification 
   * @param la A line that was cleaned
   * @throws LayoutParseException If the line does not start/end with a '+' delimiter, if a line is less than 3 chars wide, if a rectangle width changes 
   */
  public void parseSpec(List<char[]> la) 
      throws LayoutParseException
  {
    int lineNr = 0;
    for (char[] ca : la) {
      if (debugParser) System.out.println("Line '"+new String(ca) + "'");
      if (ca[0] != '+') {
        throw new LayoutParseException("Illegal line start at line " + lineNr);
      }
      if (ca[ca.length-1] != '+') {
        throw new LayoutParseException("Illegal line end at line " + lineNr);
      }
      if (ca.length < 3) {
        throw new LayoutParseException("Too short line at line " + lineNr);
      }
      int x = 1;
      int xStart = x;
      boolean onPlus = true;
      StringBuilder sb = new StringBuilder();
      while (x < ca.length) {
        char c = ca[x];
        if (c != '+') {
          sb.append(c);
          if (onPlus) {
            xStart = x; //we entered a new rectangle, update xStart
            onPlus = false;
          }
        }
        else {
          onPlus = true; //we are on a '+' again
          String val = sb.toString();
          sb.setLength(0);
          int tmpFlags = evalFlags(val);
          val = removeFlags(val);
          if (debugParser) System.out.println("Flags removed : '"+val+"'");
          String name = val.trim();
          if (debugParser && name.length() > 0) System.out.println("Name : '"+name+"'");
          if (val.length() > 0) {
            CRect r = findCRect(xStart, lineNr);
            if (r != null) {
              if (debugParser) System.out.println("Found rect " + r + " for " + xStart + ", " + lineNr);
              if (val.length()+1 != r.w) {
                throw new LayoutParseException("Illegal layout width at line " + lineNr + ", column " + xStart);
              }
              r.flags |= tmpFlags;
              r.h++;
            }
            else {
              //create and register a new rectangle
              r = new CRect();
              r.x = xStart;
              r.y = lineNr;
              r.w = val.length()+1;
              r.h = 1;
              r.flags = tmpFlags;
              if (debugParser) System.out.println("Created rect " + r);
              rects.add(r);
            }
            if (isNameValid(name)) {
              if (debugParser) System.out.println("Rect name : '"+name+"'");
              r.name = name;
              r.halign = 2;
              if (isNameStart(val.charAt(0))) r.halign = 1;
              if (isNameChar(val.charAt(val.length()-1))) r.halign = 3;
              r.lineOfName = r.h;
            }
            else {
              if (name.length() > 0) System.err.println("Invalid name : '"+name+"'"); //warn that rect name is invalid
            }
            xStart = x + 1;      
          }
        }//if (c != '+')
        x++;
      }//while
      lineNr++;
    }//for
    //now comput all the valign values, and increment all h values so they include the final line
    for (CRect r : rects) {
      r.valign = 2;
      if (r.lineOfName == 1) r.valign = 1;
      if (r.lineOfName == r.h) r.valign = 3;
      r.h++;
    }
  }
  
}
