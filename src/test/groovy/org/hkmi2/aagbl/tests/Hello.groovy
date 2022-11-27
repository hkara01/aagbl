package org.hkmi2.aagbl

import javax.swing.*
import java.awt.*
import org.hkmi2.aagbl.AsciiArtGridBagLayout
import groovy.swing.SwingBuilder

aa = '''
+------------------------------------------------+
|  ^                                             |
|<                       a                      >|
|                                                |
+-----------------+-----------+------------------+
|                 |           |                  |
|                 |     b     |                  |
|                 |           |                  |
+-----------------+-----------+------------------+
'''

gbl = new AsciiArtGridBagLayout(aa)
swb = new SwingBuilder()

frm = swb.frame(
            title: "Hello world", 
            defaultCloseOperation: JFrame.EXIT_ON_CLOSE
          ) 
{
	a = textField(
            text: 'Hello to all the layouters', 
            horizontalAlignment: JTextField.CENTER
        )
	b = button(text: "OK", actionPerformed: {e-> System.exit(0)} )
}

'a,b'.split(',').each {
	gbl.setConstraints(it, binding[it])
}

frm.contentPane.setLayout(gbl)
gbl.addAllComponentsTo(frm.contentPane)
frm.pack()
frm.visible = true

