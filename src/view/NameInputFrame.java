package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import control.NameListener;

public class NameInputFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	JButton ok;
	JTextField name;
	
	public NameInputFrame(NameListener listener) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 100);
		setResizable(false);
		setTitle("Game Over");
		setLayout(new GridLayout(0, 1));
		setLocation(300, 200);
		
		JPanel top = new JPanel();
		top.add(new JLabel("Please enter your name:"));
		
		name = new JTextField(10);
		name.getDocument().addDocumentListener(new IsThereSomething());
		top.add(name);
		
		JPanel bottom = new JPanel();
		ok = new JButton("OK");
		ok.addActionListener(listener);
		ok.setEnabled(false);
		bottom.add(ok);
		
		add(top);
		add(bottom);
		
		setVisible(true);
	}
	
	public String getText() {
		return name.getText();
	}
	
	private class IsThereSomething implements DocumentListener {

		public void changedUpdate(DocumentEvent arg0) {
		}

		public void insertUpdate(DocumentEvent arg0) {
			if (name.getText().length() > 0)
				ok.setEnabled(true);
		}

		public void removeUpdate(DocumentEvent arg0) {
			if (name.getText().length() == 0)
				ok.setEnabled(false);
		}
		
	}

}
