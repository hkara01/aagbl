package org.hkmi2.aagbl.tests;

import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import org.hkmi2.aagbl.AsciiArtGridBagLayout;
import org.hkmi2.aagbl.LayoutParseException;

/**
 * Tests of anchor (north, northwest, west, ...) behavior.
 * Note that for east and west the space is not 50%, because the preferred sizes of label and textarea are not
 * the same. This is known for GridBagLayout, it's not a bug.
 * @author Harry
 *
 */
public class AnchorTests
extends JFrame
{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  String sN = "+-------------------+\n"+
              "|          n        |\n"+
              "|                   |\n"+
              "|                   |\n"+
              "+-------------------+\n"+
              "|            ^      |\n"+
              "|<       i1        >|\n"+
              "|                   |\n"+
              "+-------------------+\n";

  String sNW =  "+----------+----------+\n"+
                "|nw        |  ^       |\n"+
                "|          |<  i1    >|\n"+
                "|          |          |\n"+
                "+---------------------+\n"+
                "| ^        | ^        |\n"+
                "|<    i2  >|<  i3    >|\n"+
                "|          |          |\n"+
                "+----------+----------+\n";

  String sW = "+----------+----------+\n"+
              "|          | ^        |\n"+
              "|          |          |\n"+
              "|          |          |\n"+
              "|w         |<  i1    >|\n"+
              "|          |          |\n"+
              "|          |          |\n"+
              "|          |          |\n"+
              "+----------+----------+\n";

  String sSW =  "+----------+----------+\n"+
                "| ^        | ^        |\n"+
                "|<   i1   >|<  i2    >|\n"+
                "|          |          |\n"+
                "+----------+----------+\n"+
                "|          | ^        |\n"+
                "|          |<  i3    >|\n"+
                "|sw        |          |\n"+
                "+---------------------+\n";

  String sS = "+-------------------+\n"+
              "| ^                 |\n"+
              "|<         i1      >|\n"+
              "|                   |\n"+
              "+-------------------+\n"+
              "|                   |\n"+
              "|                   |\n"+
              "|          s        |\n"+
              "+-------------------+\n";

  String sSE =  "+----------+----------+\n"+
                "| ^        | ^        |\n"+
                "|<    i1  >|<  i2    >|\n"+
                "|          |          |\n"+
                "+----------+----------+\n"+
                "| ^        |          |\n"+
                "|<    i3  >|          |\n"+
                "|          |        se|\n"+
                "+---------------------+\n";

  String sE = "+----------+----------+\n"+
              "| ^        |          |\n"+
              "|          |          |\n"+
              "|          |          |\n"+
              "|<   i1   >|         e|\n"+
              "|          |          |\n"+
              "|          |          |\n"+
              "|          |          |\n"+
              "+----------+----------+\n";

  String sNE =  "+----------+----------+\n"+
                "| ^        |        ne|\n"+
                "|<   i1   >|          |\n"+
                "|          |          |\n"+
                "+----------+----------+\n"+
                "| ^        | ^        |\n"+
                "|<   i2   >|<  i3    >|\n"+
                "|          |          |\n"+
                "+---------------------+\n";

  String sC = "+-------------------+\n"+
              "|                   |\n"+
              "|                   |\n"+
              "|                   |\n"+
              "|          c        |\n"+
              "|                   |\n"+
              "|                   |\n"+
              "|                   |\n"+
              "+-------------------+\n";

  JLabel n = new JLabel("N");
  JLabel nw = new JLabel("NW");
  JLabel w = new JLabel("W");
  JLabel sw = new JLabel("SW");
  JLabel s = new JLabel("S");
  JLabel se = new JLabel("SE");
  JLabel e = new JLabel("E");
  JLabel ne = new JLabel("NE");
  JLabel c = new JLabel("C");
  
  JTextArea ine1 = makeLorem();
  JTextArea ine2 = makeLorem();
  JTextArea ine3 = makeLorem();
  JTextArea in1 = makeLorem();
  JTextArea inw1 = makeLorem();
  JTextArea inw2 = makeLorem();
  JTextArea inw3 = makeLorem();
  JTextArea ie1 = makeLorem();
  JTextArea iw1 = makeLorem();
  JTextArea ise1 = makeLorem();
  JTextArea ise2 = makeLorem();
  JTextArea ise3 = makeLorem();
  JTextArea isw1 = makeLorem();
  JTextArea isw2 = makeLorem();
  JTextArea isw3 = makeLorem();
  JTextArea is1 = makeLorem();
  
  AsciiArtGridBagLayout ln = new AsciiArtGridBagLayout(sN);
  AsciiArtGridBagLayout lnw = new AsciiArtGridBagLayout(sNW);
  AsciiArtGridBagLayout lw = new AsciiArtGridBagLayout(sW);
  AsciiArtGridBagLayout lsw = new AsciiArtGridBagLayout(sSW);
  AsciiArtGridBagLayout ls = new AsciiArtGridBagLayout(sS);
  AsciiArtGridBagLayout lse = new AsciiArtGridBagLayout(sSE);
  AsciiArtGridBagLayout le = new AsciiArtGridBagLayout(sE);
  AsciiArtGridBagLayout lne = new AsciiArtGridBagLayout(sNE);
  AsciiArtGridBagLayout lc = new AsciiArtGridBagLayout(sC);

  public JTextArea makeLorem() {
    JTextArea ta = new JTextArea("Lorem ipsum.");
    ta.setBorder(BorderFactory.createLineBorder(getForeground()));
    return ta;
  }
  
  public AnchorTests() throws HeadlessException, LayoutParseException {
  }

  public AnchorTests(String title) throws HeadlessException, LayoutParseException {
    super(title);
    JPanel mainPanel = new JPanel();
    GridLayout mainPanelLayout = new GridLayout(3, 3);
    mainPanel.setLayout(mainPanelLayout);
    
    Border lineBorder = BorderFactory.createLineBorder(getForeground(), 2);
    nw.setBorder(lineBorder);
    n.setBorder(lineBorder);
    ne.setBorder(lineBorder);
    e.setBorder(lineBorder);
    se.setBorder(lineBorder);
    s.setBorder(lineBorder);
    sw.setBorder(lineBorder);
    w.setBorder(lineBorder);
    c.setBorder(lineBorder);
    
    //Add NorthWest panel
    JPanel pnw = new JPanel();
    pnw.setLayout(lnw);
    lnw.setConstraints("nw", nw); lnw.setConstraints("i1", inw1); lnw.setConstraints("i2", inw2); lnw.setConstraints("i3", inw3);
    //lnw.setWeightx("nw", 0); lnw.setWeighty("nw", 0);
    lnw.addAllComponentsTo(pnw);
    mainPanel.add(pnw);
    
    //Add North panel
    JPanel pn = new JPanel();
    pn.setLayout(ln);
    ln.setConstraints("n", n); ln.setConstraints("i1", in1);
    ln.addAllComponentsTo(pn);
    mainPanel.add(pn);
    
    //Add NorthEast panel
    JPanel pne = new JPanel();
    pne.setLayout(lne);
    lne.setConstraints("ne", ne); lne.setConstraints("i1", ine1); lne.setConstraints("i2", ine2); lne.setConstraints("i3", ine3);
    lne.addAllComponentsTo(pne);
    mainPanel.add(pne);
    
    //Add West panel
    JPanel pw= new JPanel();
    pw.setLayout(lw);
    lw.setConstraints("w", w); lw.setConstraints("i1", iw1);
    //lw.setWeightx("w", 0.7); lw.setWeighty("w", 1);
    //lw.setWeightx("i1", 0.3); lw.setWeighty("i1", 1);
    lw.addAllComponentsTo(pw);
    mainPanel.add(pw);
    
    //Add Center panel
    JPanel pc= new JPanel();
    pc.setLayout(lc);
    lc.setConstraints("c", c);
    lc.addAllComponentsTo(pc);
    mainPanel.add(pc);
    
    //Add East panel
    JPanel pe= new JPanel();
    pe.setLayout(le);
    le.setConstraints("e", e); le.setConstraints("i1", ie1);
    le.addAllComponentsTo(pe);
    mainPanel.add(pe);
    
    //Add SouthWest panel
    JPanel psw = new JPanel();
    psw.setLayout(lsw);
    lsw.setConstraints("sw", sw); lsw.setConstraints("i1", isw1); lsw.setConstraints("i2", isw2); lsw.setConstraints("i3", isw3);
    lsw.addAllComponentsTo(psw);
    mainPanel.add(psw);
    
    //Add South panel
    JPanel ps = new JPanel();
    ps.setLayout(ls);
    ls.setConstraints("s", s); ls.setConstraints("i1", is1);
    ls.addAllComponentsTo(ps);
    mainPanel.add(ps);
    
    //Add SouthEast panel
    JPanel pse= new JPanel();
    pse.setLayout(lse);
    lse.setConstraints("se", se); lse.setConstraints("i1", ise1); lse.setConstraints("i2", ise2); lse.setConstraints("i3", ise3);
    lse.addAllComponentsTo(pse);
    mainPanel.add(pse);
    
    //finally, set the main panel as the main container
    setContentPane(mainPanel);
  }

  public static void main(String[] args) throws HeadlessException, LayoutParseException {
    AnchorTests frm = new AnchorTests("Anchor Tests");
    frm.setSize(800, 600);
    frm.setVisible(true);
  }
  
  
}
