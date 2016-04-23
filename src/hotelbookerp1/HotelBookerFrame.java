/*  * HotelBookerFrame.java  */
package hotelbookerp1;

// before every start: team -> remote -> pull so that master is merged onto [your name]
// after work: team -> commit, team -> remote -> push, go to github and pull request, merge
// things to work on:
// Start date-
// I try to change the month to before current month april 2016 -> march 2016: nothing happens since month is before current month
// I change the month: it switches to same day april 21 2016 -> may 21 2016: change month + pass over day
// if day doesn't exist: goes to last day of the switched month (March 31 -> Feb 28 since Feb 31 doesn't exist): change month + change day to last day
// I change year when month already passed feb 2017 -> feb 2016: nothing happens since month is before current month
// End date-
// change year when month has already passed feb 2017 -> feb 2016: nothing happens since month is before current month
// change month when day already passed: change month for april 23 2017 -> april 23 2016 when start day is april 25 2016, end day will automatically become april 26 2016
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
 * Compiler: Java 1.6 <br>
 * OS: OSX & Windows 7 <br>
 * Hardware: PC <br>
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
    private static final int yearsInComboBox = 10;

    private final String fileName = "src/hotelbookerp1/reservations.txt";

    /**
     * allows the user to set up start and end dates for hotel reservations then
     * will print it to a text document start date on calender end date on
     * calender
     *
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        JFrame frame = new HotelBookerFrame();
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
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
        makeYearComboBox();
        northPanel.add(yearComboBox);
        

        setSouthPanel();
        add(southPanel, BorderLayout.SOUTH);

        setCenterPanel();

    }

    /**
     * this is intended to be the north pane of the gui it has the labels to
     * show the date on the calender
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

        //
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

    // make the initial yearComboBox, rebuild the centerPanel
    //after yearComboBox is changed.
    public void makeYearComboBox() {
        String[] yearArray = new String[yearsInComboBox];
        for (int i = 0; i < yearsInComboBox; i++) {
            String yearString = "" + (today.getYear() + i);
            yearArray[i] = (yearString);
        }
        yearComboBox = new JComboBox(yearArray);
        yearComboBox.setSelectedIndex(0);
        class changeStatusListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent event) {
                String yearIndex = "" + yearComboBox.getSelectedIndex();
                short indexShort = Short.parseShort(yearIndex);
                short yearShort = (short)(today.getYear() + indexShort);
                today.setYear(yearShort);
                setCenterPanel();
            }
        }
        yearComboBox.addActionListener(new changeStatusListener());
    }

//Creates the initial and updated calendars
    /**
     * intended to be the center panel this is the calender of each month
     */
    public void setCenterPanel() {
        if (centerPanel != null) {
            getContentPane().remove(centerPanel);
        }
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(7, 7));
        calendar = new BasicCalendar(today.getMonth(), today.getYear());
        int daysInMonth = today.daysInMonth(today.getMonth(), today.getYear());
        int index = 0;

        //This loop finds the index for the grid calendar's 
        //first day
        String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"};
        for (int i = 0; i <= 6; i++) {
            JLabel name = new JLabel(dayNames[i]);
            centerPanel.add(name);
            name.setHorizontalAlignment(JLabel.CENTER);

        }
        for (int i = 0; i <= 6; i++) {
            if (calendar.at(0, i) == 1) {
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
        ButtonGroup dateButtons = new ButtonGroup();
        dateButtons.add(calendarButton);

// make calenderbutton set to today by default

        if (startButton.isSelected()) {
            
//            if (calendarButton.isSelected()) {      if a calender button is selected
//                startButton.setText("Start Date: " + calendarButton.isSelected().toString());
//                System.out.println(calendarButton.isSelected());
//            }
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
//    public void setSouthPanel() {
//        southPanel = new JPanel();
//        southPanel.setLayout(new FlowLayout());
//
//    }
    //makes radio buttons and their action listeners
    /**
     * is the pane for show the time of the reservations
     */
    public void setSouthPanel() {

        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(4, 1)); //row,column

        // how to set grid layout panels as flow, then add each label specifically
        todayPanel = new JPanel();
        startPanel = new JPanel();
        endPanel = new JPanel();
        namePanel = new JPanel();

        southPanel.add(todayPanel);
        southPanel.add(startPanel);
        southPanel.add(endPanel);
        southPanel.add(namePanel);

        JLabel todayLabel;
        todayLabel = new JLabel("Today: " + today.toString());
        todayPanel.add(todayLabel);

        startButton = new JRadioButton();

        startButton.setSelected(true);
        startButton.setText("Start Date: " + today.toString());

        endButton = new JRadioButton();
        endButton.setText("End Date: " + today.getTomorrow().toString());

        ButtonGroup group = new ButtonGroup();
        group.add(startButton);
        group.add(endButton);

        startPanel.add(startButton);
        endPanel.add(endButton);

        nameTextField = new JTextField();
        nameTextField.setColumns(30);
        nameLabel = new JLabel(enterName);
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);
    }

    /////////////////////////////////////////////////////////////////////////////////// create button
    /**
     * creates a button to book the reservation
     */
    public void createBookButton() {
        bookButton = new JButton(buttonName);
        southPanel.add(bookButton);
        //
        class changeStatusListener implements ActionListener {

            /**
             * When button is pressed, prints name, arrive date, and departure
             * date to an output file
             *
             * @param event the triggering event object
             */
            @Override
            public void actionPerformed(ActionEvent event) {
                if (event.getSource() == bookButton) {

                }
                writeReservation(fileName);
            }
        }
    }

    public void writeReservation(String fileName) {
        try {
            FileWriter filePointer = new FileWriter(fileName, false);
            PrintWriter output = new PrintWriter(filePointer);

            //String line = "Name: " + tempReservation.getName() + ", Start: " + tempReservation.getStart() + ", End:" + tempReservation.getEndDate(); 
            //output.println(line);
            output.close();
        } catch (IOException exp) {
            exp.printStackTrace();
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

    JPanel todayPanel;
    JPanel startPanel;
    JPanel endPanel;
    JPanel namePanel;

    JRadioButton startButton;
    JRadioButton endButton;

    JRadioButton calendarButton;
    DateAD startDate;
    DateAD endDate;
}
