/*  * HotelBookerFrame.java  */
package hotelbookerp1;

// before every start: team -> remote -> pull so that master is merged onto [your name]
// after work: team -> commit, team -> remote -> push, go to github and pull request, merge

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import Calendar.DateAD;
import Calendar.BasicCalendar;
import java.io.*;

/**
 * Hotel Booker
 *
 * a project that user can set up hotel reservations <br>
 * the project also creates a text document with the hotel reservation <br>
 *
 * @author Michael Ji & Raymon Yee
 * @version 1.0
 *
 *  Compiler: Java 1.6 <br>
 *  OS: OSX & Windows 7 <br>
 *  Hardware: PC <br>
 *
 * April 25, 2016 <br>
 * PB completed v 1.0
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
     * allows the user to set up start and end dates for hotel reservations
     * then will print it to a text document
     * start date on calender
     * end date on calender
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        JFrame frame = new HotelBookerFrame();
        PrintStream output = new PrintStream(new File("Bookings.txt"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * initializes the gui frame that the program will run off of
     */
    public HotelBookerFrame() {
        setLayout(new BorderLayout());
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        today = new DateAD();
        setNorthPanel();
        add(northPanel, BorderLayout.NORTH);
        
        setSouthPanel();
        setRadioPanel();
        radioButtonPanel.add(southPanel);
        add(radioButtonPanel, BorderLayout.SOUTH);       
         
        
        setCenterPanel();
                        
        
        
    }

    /**
     * this is intended to be the north pane of the gui
     * it has the labels to show the date on the calender
     */
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
                short monthShort = Short.parseShort(monthString);
                today.setMonth(monthShort);
                setCenterPanel();
            }
    }        
        monthComboBox.addActionListener(new changeStatusListener());
    }                 
    
//Creates the initial and updated calendars

    /**
     * intended to be the center panel
     * this is the calender of each month
     */
        public void setCenterPanel() {
        if (centerPanel != null) {
            getContentPane().remove(centerPanel);  
        }
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6,7));
        calendar = new BasicCalendar(today.getMonth(), today.getYear());
        int daysInMonth = today.daysInMonth(today.getMonth(), today.getYear());
        int index = 0;
        //This loop finds the index for the grid calendar's 
        //first day
        for (int i = 0; i <= 6; i++) {
            if (calendar.at(0,i) == 1) {
                index = i;
                break;
            }
        }
        //Creates the calendar based on today's date.
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
        //this refreshes the changes to the frame
        //after I remove centerPanel, build a new one,
        //and add it to the Frame
        invalidate();
        validate();
       
    }
    
    /**
     * south panel for the name for the reservation
     */
    public void setSouthPanel() {
        southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());
        nameTextField = new JTextField();
        nameTextField.setColumns(TEXT_WIDTH);
        nameLabel = new JLabel(enterName);
        southPanel.add(nameLabel);
        southPanel.add(nameTextField);
    }
    //makes radio buttons and their action listeners

    /**
     * is the pane for show the time of the reservations
     */
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
    
    /**
     * creates a button to book the reservation
     */
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

// instance variables
    JTextField nameTextField;
    JPanel radioButtonPanel;
    JPanel northPanel;
    JPanel southPanel;
    JPanel buttonPanel;
    JPanel centerPanel;
    JLabel dayLabel;
    JLabel enterLabel;
    JLabel nameLabel;
    JButton bookButton;
    ButtonGroup group;
    JComboBox monthComboBox;
    JComboBox yearComboBox;
    DateAD today;
    BasicCalendar calendar;
    String[] monthNames;
    JRadioButton[] radioArray;
    PrintStream output;
  
    
}