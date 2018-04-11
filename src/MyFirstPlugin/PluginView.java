package MyFirstPlugin;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import PluginInterface.client.EigenschaftsSeitenInteface;

@SuppressWarnings("serial")
public class PluginView extends JPanel implements EigenschaftsSeitenInteface {

	ArrayList<JLabel> labels = new ArrayList<>();

	HashMap<String, JTextField> values = new HashMap<>();

	public PluginView() {
		this.setLayout(new BorderLayout());

		labels.add(new JLabel("Value1"));
		labels.add(new JLabel("Value2"));
		labels.add(new JLabel("Value3"));

		for (JLabel jLabel : labels) {
			values.put(jLabel.getText(), new JTextField(10));
		}

		JPanel labelPnl = new JPanel();
		labelPnl.setLayout(new GridLayout(labels.size(), 1));

		

		JPanel valuePnl = new JPanel();
		valuePnl.setLayout(new GridLayout(values.size(), 1));

		for (JLabel jLabel : labels) {
			jLabel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, this.getBackground()));
			labelPnl.add(jLabel);
			valuePnl.add(values.get(jLabel.getText()));
		}

		this.add(labelPnl, BorderLayout.WEST);
		this.add(valuePnl, BorderLayout.CENTER);
	}

	@Override
	public void setPluginData(Map<String, String> data) {
	
		for (String label : data.keySet()) {
			values.get(label).setText(data.get(label));
		}
	}

	@Override
	public Map<String, String> getPluginData() {
		HashMap<String, String> map = new HashMap<>();

		for (JLabel jLabel : labels) {
			map.put(jLabel.getText(), values.get(jLabel.getText()).getText());
		}

		return map;
	}

}
