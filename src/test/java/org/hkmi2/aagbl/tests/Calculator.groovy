/**
 * Calculator as a groovy script example. Execution example (adapt to your env.) :
 * C:\hk\jb\aagbl\src\test\java>set CLASSPATH=C:\hk\jb\aagbl\build\libs\aagbl-1.0.jar
 * C:\hk\jb\aagbl\src\test\java>c:\groovy-4.0.6\bin\groovy org/hkmi2/aagbl/tests/Calculator.groovy
 */
package org.hkmi2.aagbl

import javax.swing.*
import java.awt.*
import org.hkmi2.aagbl.AsciiArtGridBagLayout
import groovy.swing.SwingBuilder

aa = 
'''
+---------------------------+
| ^                         |
|<            D            >|
|                           |
+------+------+------+------+
| ^    | ^    | ^    | ^    |
|< BC >|< L1 >|< BD >|< BS >|
|      |      |      |      |
+------+------+------+------+
| ^    | ^    | ^    | ^    |
|< B7 >|< B8 >|< B9 >|< BM >|
|      |      |      |      |
+------+------+------+------+
| ^    | ^    | ^    | ^    |
|< B4 >|< B5 >|< B6 >|< BP >|
|      |      |      |      |
+------+------+------+------+
| ^    | ^    | ^    | ^    |
|< B1 >|< B2 >|< B3 >|      |
|      |      |      |      |
+------+------+------+< BE >+
| ^    | ^    | ^    |      |
|< L2 >|< B0 >|< BZ >|      |
|      |      |      |      |
+------+------+------+------+
'''

gbl = new AsciiArtGridBagLayout(aa)
swb = new SwingBuilder()

//the generic "button handler" closure, used for all buttons
bh = {e-> D.text += e.source.text }

frm = swb.frame(title: "Calculator", defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
	D = textField(columns: 12, horizontalAlignment: JTextField.RIGHT)
	B0 = button(text: "0", actionPerformed: bh)
	B1 = button(text: "1", actionPerformed: bh)
	B2 = button(text: "2", actionPerformed: bh)
	B3 = button(text: "3", actionPerformed: bh)
	B4 = button(text: "4", actionPerformed: bh)
	B5 = button(text: "5", actionPerformed: bh)
	B6 = button(text: "6", actionPerformed: bh)
	B7 = button(text: "7", actionPerformed: bh)
	B8 = button(text: "8", actionPerformed: bh)
	B9 = button(text: "9", actionPerformed: bh)
	BD = button(text: "/", actionPerformed: bh)
	BS = button(text: "*", actionPerformed: bh)
	BP = button(text: "+", actionPerformed: bh)
	BM = button(text: "-", actionPerformed: bh)
	BZ = button(text: ".", actionPerformed: bh)
	BC = button(text: "C", actionPerformed: {e-> D.text = ""} )
	BE = button(text: "=", actionPerformed: {e->
		try { D.text = Eval.me(D.text) }
		catch(Exception ex) { D.text = '?' }
	})
	L1 = label(text: " ")
	L2 = label(text: " ")
}

'D,B0,B1,B2,B3,B4,B5,B6,B7,B8,B9,BD,BS,BP,BM,BZ,BC,BE,L1,L2'.split(',').each {
	gbl.setConstraints(it, binding[it])
}

frm.contentPane.setLayout(gbl)
gbl.addAllComponentsTo(frm.contentPane)
frm.pack()
frm.visible = true
