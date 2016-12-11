package kn145.prihodko.usermanagement.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import kn145.prihodko.usermanagement.util.Messages;

public abstract class StandardPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private MainFrame parent;
	private JButton okButton;
	
	public StandardPanel(MainFrame parent) {
		this.parent = parent;
	}
	
    protected MainFrame getParentFrame() {
    	return parent;
    }
    
    protected JButton getOkButton() {
        if (okButton == null) {
            okButton = new JButton();
            okButton.setText(Messages.getString("AddPanel.ok")); //$NON-NLS-1$
            okButton.setName("okButton"); //$NON-NLS-1$
            okButton.setActionCommand("ok"); //$NON-NLS-1$
            okButton.addActionListener(this);
        }
        return okButton;
    }


}