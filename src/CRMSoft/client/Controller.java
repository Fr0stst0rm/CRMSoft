package CRMSoft.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
	
	View v;
	
	public static void main(String[] args) {
		new Controller();
	}
	
	public Controller() {
		v = new View();
		
		v.nameTextField.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(v.nameTextField.getText());
		
	}
	
}
