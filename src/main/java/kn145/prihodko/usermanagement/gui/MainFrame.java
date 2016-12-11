package kn145.prihodko.usermanagement.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import kn145.prihodko.usermanagement.User;
import kn145.prihodko.usermanagement.db.DaoFactory;
import kn145.prihodko.usermanagement.db.UserDao;
import kn145.prihodko.usermanagement.util.Messages;

public class MainFrame extends JFrame {

  
    private static final long serialVersionUID = 1L;
    private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_WIDTH = 800;

    private JPanel contentPanel;
    private BrowsePanel browsePanel;
    private AddPanel addPanel;
    private UserDao dao;
    private EditPanel editPanel;
    private DetailsPanel detailsPanel;
    
    public MainFrame() {
        super();
        dao = DaoFactory.getInstance().getUserDao();
        initialize();

    }

    private void initialize() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(Messages.getString("MainFrame.user_management")); //$NON-NLS-1$
        this.setContentPane(getContentPanel());
    }
    
    public static void main(String[] args) {
        
        // create and show the main window
    	MainFrame frame = new MainFrame();
    	SwingUtilities.invokeLater(
    			new Runnable() {
    				public void run() {
    				     frame.setVisible(true);
    				}
    			});
    }
 
    private void showPanel(JPanel panel) {
    	getContentPane().add(panel, BorderLayout.CENTER);
    	SwingUtilities.invokeLater(
    	new Runnable() {
    		public void run() {
    	        panel.setVisible(true); 
    	        panel.repaint();
    		}
    	});
        
    }
    
    private JPanel getContentPanel() {
        if  (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
        }
        return contentPanel;
    }
    
    private BrowsePanel getBrowsePanel() {
        if (browsePanel == null) {
            browsePanel = new BrowsePanel(this);
        }
        ((BrowsePanel) browsePanel).initTable();
        return browsePanel;
    }

    private AddPanel getAddPanel() {
        if (addPanel == null) {
            addPanel = new AddPanel(this);
        }
        return addPanel;
    }    

    private EditPanel getEditPanel() {
        if (editPanel == null) {
            editPanel = new EditPanel(this);
        }
        return editPanel;
    }
    
    private DetailsPanel getDetailsPanel() {
    	if (detailsPanel == null) {
    		detailsPanel = new DetailsPanel(this);
    	}
    	return detailsPanel;
    }
    
    public void showAddPanel() {
        showPanel(getAddPanel());
    }
    
    public void showBrowsePanel() {
        showPanel(getBrowsePanel());
    }
    
    public void showEditPanel(User user) {
    	getEditPanel().fillInForm(user);
        showPanel(getEditPanel());
    }
    public void showDetailsPanel(User user) {
    	getDetailsPanel().setUpUserDetails(user);
    	showPanel(getDetailsPanel());
    }
    
    public UserDao getDao() {
        return dao;
    }


}