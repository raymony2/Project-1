/*  * HotelBookerFrame.java  */
package hotelbookerp1;

// before every start: pull
// after work: commit, push, go to github and pull request, merge

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import Calendar.DateAD;
import Calendar.BasicCalendar;
import java.io.*;


/**
 * * * @author pbladek
 */
public class HotelBookerFrame extends JFrame {

    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 300;
    private static final int TEXT_WIDTH = 25;
    private static final String enterString = "Enter Start and End Dates";
    private static final String enterName = "Enter your name:";
    private static final String buttonName = "Book it!";
    private static final int daysInACalendar = 42;

    /**
     * * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        JFrame frame = new HotelBookerFrame();
        PrintStream output = new PrintStream(new File("Bookings.txt"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public HotelBookerFrame() {
        setLayout(new BorderLayout());
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        today = new DateAD();
        setNorthPanel();
        add(northPanel, BorderLayout.NORTH);
        
        setSouthPanel();
        setRadioPanel();
        radioButtonPanel.add(southPanel);
        
        //southPanel.add(radioButtonPanel);
        add(radioButtonPanel, BorderLayout.SOUTH);
        
        
        setCenterPanel(today);
        
        
        
        
        
    }

    public void setNorthPanel() {
        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        dayLabel = new JLabel(Short.toString(today.getDayOfMonth()));
        enterLabel = new JLabel(enterString);
        monthComboBox = new JComboBox(DateAD.MONTHNAMES);
        monthComboBox.setSelectedIndex(today.getMonth());
        northPanel.add(dayLabel);
        northPanel.add(monthComboBox);
        northPanel.add(enterLabel);
        
        
        class changeStatusListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {          
                int monthIndex = monthComboBox.getSelectedIndex();
                String monthString = monthIndex + "";
                monthShort = Short.parseShort(monthString);
                today.setMonth(monthShort);
                setCenterPanel(today);
            }
    }
        
        monthComboBox.addActionListener(new changeStatusListener());
    }             
    
    public void setSouthPanel() {
        southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());
        nameTextField = new JTextField();
        nameTextField.setColumns(TEXT_WIDTH);
        nameLabel = new JLabel(enterName);
        southPanel.add(nameLabel);
        southPanel.add(nameTextField);
    }
    
    public void createBookButton() {
    bookButton = new JButton(buttonName);
    southPanel.add(bookButton);
        class changeStatusListener implements ActionListener
        {
            /**
            * When button is pressed, prints name, arrive date, and 
            * departure date to an output file
            * @param event the triggering event object
            */
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == bookButton) {
                
                   }
                }
            }
    }
//Creates the initial calendar as well as
//updated calendars
    public void setCenterPanel(DateAD today) {
        if (centerPanel != null) {
            getContentPane().remove(centerPanel);  
        }
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6,7));
        calendar = new BasicCalendar(today.getMonth(), today.getYear());
        int daysInMonth = today.daysInMonth(today.getMonth(), today.getYear());
        int index = 0;
        //This loop finds the index to start the calendar's 
        //first day
        for (int i = 0; i <= 6; i++) {
            if (calendar.at(0,i) == 1) {
                index = i;
                break;
            }
        }
        //Creates the initial calendar based on today's date.
        //Lays out the empty buttons and the buttons
        //with text at the same time
        for (int i = 0; i < daysInACalendar; i++) {
            JButton calendarButton = new JButton();
            calendarButton.setForeground(Color.BLUE);
            if (i >= index && i < daysInMonth + index) {
                calendarButton = new JButton("" + (i - index + 1));
            }
            centerPanel.add(calendarButton);
                
        }
        add(centerPanel, BorderLayout.CENTER);
        //I think this refreshes the changes to the frame
        //after I remove centerPanel and build a new one
        invalidate();
        validate();
       
    }
    
    public void setRadioPanel() {
        radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new FlowLayout());
        radioArray = new JRadioButton[2];
         for(int i = 0; i < 2; i++)
             radioArray[i] = new JRadioButton();
         radioArray[0].setText("Start Date: " + today.toString());
         radioArray[0].setSelected(true);
         radioArray[1].setText("End Date: " + today.toString());
         group = new ButtonGroup();
         for(int i = 0; i < 2; i++)  {
            group.add(radioArray[i]);
            radioButtonPanel.add(radioArray[i]);
        }
    }

// instance variables
    JTextField nameTextField;
    JPanel northPanel;
    JPanel southPanel;
    JPanel buttonPanel;
    JLabel dayLabel;
    JLabel enterLabel;
    JLabel nameLabel;
    JButton bookButton;
    JPanel centerPanel;
    JComboBox monthComboBox;
    JComboBox yearComboBox;
    DateAD today;
    BasicCalendar calendar;
    String[] monthNames;
    short monthShort;
    PrintStream output;
    JRadioButton[] radioArray;
    ButtonGroup group;
    JPanel radioButtonPanel;
    
}
