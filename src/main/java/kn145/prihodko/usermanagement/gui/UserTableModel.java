package kn145.prihodko.usermanagement.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import kn145.prihodko.usermanagement.User;
import kn145.prihodko.usermanagement.util.Messages;

public class UserTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    List<?> users = null;
    String[] COLUMN_NAMES = {
            Messages.getString("UserTableModel.id"), 
            Messages.getString("UserTableModel.first_name"), 
            Messages.getString("UserTableModel.last_name")}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    Class<?>[] COLUMN_CLASSES = {Long.class, String.class, String.class};
    
    @SuppressWarnings("unchecked")
    public UserTableModel(Collection<?> collection) {
        this.users = new ArrayList(collection);
        
    }
    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) { 
        User user = (User) users.get(rowIndex);
        switch(columnIndex){
        case 0:
            return user.getId();
        case 1:
            return user.getFirstName();
        case 2:
            return user.getLastName();
        default:
            return null;
        }
    }
    
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_CLASSES[columnIndex];
    }
    
   public String getColumnName(int columnIndex) {
       return COLUMN_NAMES[columnIndex];
       
   }
public User getUserByRow(int selectedRow) {
	return (User) users.get(selectedRow);
}

}