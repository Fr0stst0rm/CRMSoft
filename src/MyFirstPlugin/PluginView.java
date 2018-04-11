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

	ArrayList<JTextField> values = new ArrayList<>();

	public PluginView() {
		this.setLayout(new BorderLayout());

		labels.add(new JLabel("Value 1"));
		labels.add(new JLabel("Value 2"));
		labels.add(new JLabel("Value 3"));

		values.add(new JTextField(10));
		values.add(new JTextField(10));
		values.add(new JTextField(10));

		JPanel labelPnl = new JPanel();
		labelPnl.setLayout(new GridLayout(labels.size(), 1));

		for (JLabel jLabel : labels) {
			jLabel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, this.getBackground()));
			labelPnl.add(jLabel);
		}

		JPanel valuePnl = new JPanel();
		valuePnl.setLayout(new GridLayout(values.size(), 1));

		for (JTextField jTextField : values) {
			valuePnl.add(jTextField);
		}

		this.add(labelPnl, BorderLayout.WEST);
		this.add(valuePnl, BorderLayout.CENTER);
	}

	@Override
	public void setPluginData(Map<String, String> data) {
		String keys[] = data.keySet().toArray(new String[data.size()]);

		for (int i = 0; i < keys.length; i++) {
			labels.get(i).setText(keys[i]);
			values.get(i).setText(data.get(keys[i]));
		}
	}

	@Override
	public Map<String, String> getPluginData() {
		HashMap<String, String> map = new HashMap<>();

		for (int i = 0; i < labels.size(); i++) {
			map.put(labels.get(i).getText(), values.get(i).getText());
		}

		return map;
	}

}