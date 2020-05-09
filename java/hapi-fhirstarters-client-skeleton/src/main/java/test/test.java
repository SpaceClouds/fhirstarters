package test;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/*from   w w w. j a  v a  2 s  .c om*/
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class test {

  public static void main(String args[]) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    apicall call = new apicall();

    DefaultTableModel model = new DefaultTableModel();
    DefaultListModel listModel = new DefaultListModel();

    model.addColumn("Name");
    model.addColumn("Total Cholesterol");
    model.addColumn("Time");

    String[] temp = {"Birthday: ", "Gender: ", "Address: "};

    JList expandedInfo = new JList(listModel);

    listModel.addElement(temp[0]);
    listModel.addElement(temp[1]);
    listModel.addElement(temp[2]);


    JTable table = new JTable(model);

    table.setRowSelectionAllowed(true);
    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
       @Override
       public void valueChanged(ListSelectionEvent e) {
          listModel.clear();
          listModel.addElement("Birthday: " + table.getValueAt(table.getSelectedRow(), 0) + "'s someBirthday");
          listModel.addElement("Gender: " + table.getValueAt(table.getSelectedRow(), 0) + "'s someGender");
          listModel.addElement("Address: " + table.getValueAt(table.getSelectedRow(), 0) + "'s someAddress");
          frame.pack();
       }
    });
    table.setBounds(200, 200, 200, 300);

    JList list = new JList(call.getList());
    list.setCellRenderer(new CheckListRenderer());
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent event) {
        JList list = (JList) event.getSource();
        int index = list.locationToIndex(event.getPoint());// Get index of item
                                                           // clicked
        CheckListItem item = (CheckListItem) list.getModel()
            .getElementAt(index);
        item.setSelected(!item.isSelected()); // Toggle selected state
         if (item.isSelected() == true){
            //get patient data
            model.addRow(new Object[]{item.toString(), "100 mg/dL", "some time"});
         }
         else
         {
            int tempInt = 0;
            for (int i = 0; i < model.getRowCount(); i += 1)
            {
               if (item.toString() == model.getValueAt(i, 0))
               {
                  //assume that patients all have unique names
                  tempInt = i;
                  break;
               }
            }
            model.removeRow(tempInt);
         }


        list.repaint(list.getCellBounds(index, index));// Repaint cell
         System.out.println("ha gay" + index);
      }
    });
    frame.getContentPane().setLayout(new FlowLayout());
    frame.getContentPane().add(new JScrollPane(list));
    frame.getContentPane().add(new JScrollPane(table));
    JScrollPane expandedPane = new JScrollPane(expandedInfo);
    frame.getContentPane().add(expandedPane);
    frame.setSize(1000, 500);
    frame.pack();
    frame.setVisible(true);
  }
}

class apicall {

      ArrayList<CheckListItem> checkListItems = new ArrayList<CheckListItem>();;

      public CheckListItem[] getList(){
         checkListItems.add(new CheckListItem("patient 1"));
         checkListItems.add(new CheckListItem("patient 2"));
         checkListItems.add(new CheckListItem("patient 3"));

         CheckListItem[] temp = new CheckListItem[checkListItems.size()];
         for (int i = 0; i < checkListItems.size(); i += 1){
            temp[i] = checkListItems.get(i);
         }
         return temp;
      }
}


class CheckListItem {

  private String label;
  private boolean isSelected = false;

  public CheckListItem(String label) {
    this.label = label;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean isSelected) {
    this.isSelected = isSelected;
  }

  @Override
  public String toString() {
    return label;
  }
}

class CheckListRenderer extends JCheckBox implements ListCellRenderer {
  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean hasFocus) {
    setEnabled(list.isEnabled());
    setSelected(((CheckListItem) value).isSelected());
    setFont(list.getFont());
    setBackground(list.getBackground());
    setForeground(list.getForeground());
    setText(value.toString());
    return this;
  }
}
