package Kaese;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class YesNoButtons extends JPanel{
	
	JRadioButton yes = new JRadioButton("Yes");
	JRadioButton no = new JRadioButton("No");
	
	ButtonGroup btnGroup = new ButtonGroup();
	
	public YesNoButtons() {
		btnGroup.add(yes);
		btnGroup.add(no);
		
		this.setLayout(new FlowLayout());
		
		this.add(yes);
		this.add(no);
		
	}
	
	public boolean getValue() {
		return yes.isSelected();
	}
	
	public void setValue(boolean b) {
		yes.setSelected(b);
		no.setSelected(!b);
	}

}
