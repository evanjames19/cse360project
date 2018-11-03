import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;


public class InterfacePanels extends JPanel {

	// labels for describing input
	private JLabel activityNamesLabel;
	private JLabel durationLabel;
	private JLabel predecessorLabel;
	private JLabel fileNameLabel;

	// names for the windows
	private JLabel helpWindowName;
	private JLabel aboutWindowName;
	private JLabel reportWindowName;							// new*

	private JButton compileButton;								// takes user to displayPath panel
	private JButton mainRestartButton;							// restart button for home page, refreshes data
	private JButton displayRestartButton;						// restart button for the path display window/panel, refreshes data and takes user back to home page
	private JButton addButton;									// adds another activity
	private JButton aboutButton;								// takes user to about page/panel
	private JButton helpButton;									// takes user to help page/panel
	private JButton aboutToHomeButton;							// located on the about panel, takes user back to home page
	private JButton helpToHomeButton;							// located on the help panel, takes user back to home page
	private JButton reportToHomeButton;							// located on the report panel, takes user to home page
	private JButton exitButton;									// takes user to exit the program
	
	private JButton mainCreateReportButton;						// will take user to page where user can enter a title for report and create
																// a report(text) file*
	private JButton createReportButton;							// creates report

	private JTextField activityNameField;						// user will enter name here
	private JTextField durationField;							// user will enter duration here
	private JTextField predecessorField;						// will be used to enter predecessors from previous activities user entered
	private JTextField reportTitleField;						// user will enter the report file's(text file) title here

	private JTextArea aboutField;								// will be on about window (non-editable for user)

	private JTextArea helpField;								// will be on help window (non-editable for user)

	private JTextArea programProcessField;						// will show error messages, and whether activity addition was successful

	private JTextArea pathDisplayField;							// will show path/activities organized

	GridBagConstraints mainConstraints = new GridBagConstraints();

	GridBagConstraints aboutConstraints = new GridBagConstraints();			// for organizing About panel
	GridBagConstraints helpConstraints = new GridBagConstraints();			// for organizing Help panel
	GridBagConstraints reportConstraints = new GridBagConstraints();		// for organizing report panel

	public InterfacePanels() {
		ActivityList list = new ActivityList();
		setFont(new Font("Times New Roman", Font.PLAIN, 20));
		JPanel panelsContainer = new JPanel();				// contains ALL panels****
		JPanel mainPanel = new JPanel();					// contains home page
		JPanel helpPanel = new JPanel();					// contains help page
		JPanel aboutPanel = new JPanel();					// contains about page
		JPanel pathDisplayPanel = new JPanel();				// contains window where path will be shown to user
		JPanel reportPanel = new JPanel();					// contains report panel

		CardLayout interfacePanel = new CardLayout();		// card layout to switch between JPanels

		// MAINPANEL/HOMEPAGE--------------------------------------------------------------------------------------------

		mainPanel.setLayout(new GridBagLayout());

		panelsContainer.setLayout(interfacePanel);

		mainConstraints.insets = new Insets(10, 10, 10, 10);			// used for spacing between gui components

		// LABELS
		// 2nd quadrant of home page ("mainPanel")

		activityNamesLabel = new JLabel("Activity Name");
		mainConstraints.gridx = 0;
		mainConstraints.gridy = 0;
		mainConstraints.gridwidth = 10;
		mainConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(activityNamesLabel, mainConstraints);

		durationLabel = new JLabel("Duration");
		mainConstraints.gridx = 0;
		mainConstraints.gridy = 1;
		mainConstraints.gridwidth = 10;
		mainConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(durationLabel, mainConstraints);

		predecessorLabel = new JLabel("Predecessor");
		mainConstraints.gridx = 0;
		mainConstraints.gridy = 2;
		mainConstraints.gridwidth = 10;
		mainConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(predecessorLabel, mainConstraints);

		// BUTTONS
		// 3rd quadrant of home page ("mainPanel")

		compileButton = new JButton("Compile");
		compileButton.setBackground(new Color(170, 255, 159));
		mainConstraints.gridx = 0;
		mainConstraints.gridy = 3;
		mainConstraints.gridwidth = 10;
		mainConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(compileButton, mainConstraints);

		addButton = new JButton("Add Another");
		mainConstraints.gridx = 0;
		mainConstraints.gridy = 4;
		mainConstraints.gridwidth = 10;
		mainConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(addButton, mainConstraints);

		mainRestartButton = new JButton("Restart");
		mainConstraints.gridx = 0;
		mainConstraints.gridy = 5;
		mainConstraints.gridwidth = 10;
		mainConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(mainRestartButton, mainConstraints);

		aboutButton = new JButton("About");
		mainConstraints.gridx = 0;
		mainConstraints.gridy = 6;
		mainConstraints.gridwidth = 10;
		mainConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(aboutButton, mainConstraints);

		helpButton = new JButton("Help");
		mainConstraints.gridx = 0;
		mainConstraints.gridy = 7;
		mainConstraints.gridwidth = 10;
		mainConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(helpButton, mainConstraints);

		exitButton = new JButton("Exit");
		mainConstraints.gridx = 0;
		mainConstraints.gridy = 8;
		mainConstraints.gridwidth = 10;
		mainConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(exitButton, mainConstraints);

		// TEXT FIELDS/DROP DOWN BOX (FOR USER INPUT)
		// 1st quadrant of home page ("mainPanel")

		activityNameField = new JTextField(30);
		activityNameField.setToolTipText("Enter the name of an activity");
		mainConstraints.gridx = 10;
		mainConstraints.gridy = 0;
		mainConstraints.gridwidth = 10;
		mainConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(activityNameField, mainConstraints);

		durationField = new JTextField(30);
		durationField.setToolTipText("Enter a whole number");
		mainConstraints.gridx = 10;
		mainConstraints.gridy = 1;
		mainConstraints.gridwidth = 10;
		mainConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(durationField, mainConstraints);

		predecessorField = new JTextField();
		predecessorField.setToolTipText("Choose a predecessor");
		mainConstraints.gridx = 10;
		mainConstraints.gridy = 2;
		mainConstraints.gridwidth = 10;
		mainConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(predecessorField, mainConstraints);

		// ERROR/SUCCESS MESSAGE BOX (NON-EDITABLE TO USER)
		// 4th quadrant of home page ("mainPanel")

		programProcessField = new JTextArea(8, 30);
		programProcessField.setEditable(false);
		mainConstraints.gridx = 10;
		mainConstraints.gridy = 3;
		mainConstraints.gridwidth = 10;
		mainConstraints.gridheight = 5;
		mainConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainConstraints.fill = GridBagConstraints.VERTICAL;
		mainPanel.add(programProcessField, mainConstraints);
		
		mainCreateReportButton = new JButton("Create Report");			// NEW
		mainCreateReportButton.setBackground(new Color(220, 220, 220));
		mainConstraints.gridx = 10;
		mainConstraints.gridy = 8;
		mainConstraints.gridwidth = 10;
		mainConstraints.gridheight = 1;
		mainConstraints.fill = GridBagConstraints.HORIZONTAL;
		mainConstraints.fill = GridBagConstraints.VERTICAL;
		mainPanel.add(mainCreateReportButton, mainConstraints);




		//ABOUT PANEL----------------------------------------------------------------------------------------------------
		aboutPanel.setLayout(new GridBagLayout());
		
		aboutConstraints.insets = new Insets(10, 10, 10, 10);
		
		aboutToHomeButton = new JButton("Home");
		aboutConstraints.gridx = 0;
		aboutConstraints.gridy = 0;
		aboutConstraints.gridwidth = 5;
		aboutConstraints.fill = GridBagConstraints.HORIZONTAL;
		aboutPanel.add(aboutToHomeButton, aboutConstraints);
		
		aboutWindowName = new JLabel("	    ABOUT");
		aboutConstraints.gridx = 0;
		aboutConstraints.gridy = 1;
		aboutConstraints.gridwidth = 5;
		aboutConstraints.fill = GridBagConstraints.HORIZONTAL;
		aboutPanel.add(aboutWindowName, aboutConstraints);
	
		
		aboutField = new JTextArea(20, 33);
		//ENTER "ABOUT" TEXT HERE
		String aboutText = "This program, Network Path Analysis, was created by Abrar, Abe, Shuchi, and Evan.\n "
				+ "It will allow the user to enter activity names and to be entered into a linked list.\n The paths will be analyzed and"			
				+ " printed in decreasing order of duration.\n It will not accept single activities lacking connections, circular paths, or decimal durations. ";

		aboutField.setText(aboutText);
		aboutField.setEditable(false);
		aboutConstraints.gridx = 5;
		aboutConstraints.gridy = 0;
		aboutConstraints.gridwidth = 15;
		aboutConstraints.gridheight = 7;
		aboutConstraints.fill = GridBagConstraints.HORIZONTAL;
		aboutConstraints.fill = GridBagConstraints.VERTICAL;
		aboutPanel.add(aboutField, aboutConstraints);
		
		//HELP PANEL-------------------------------------------------------------------------------------------------------
		helpPanel.setLayout(new GridBagLayout());
		
		helpConstraints.insets = new Insets(10, 10, 10, 10);
		
		helpToHomeButton = new JButton("Home");
		helpConstraints.gridx = 0;
		helpConstraints.gridy = 0;
		helpConstraints.gridwidth = 5;
		helpConstraints.fill = GridBagConstraints.HORIZONTAL;
		helpPanel.add(helpToHomeButton, helpConstraints);
		
		helpWindowName = new JLabel("     HELP");
		helpConstraints.gridx = 0;
		helpConstraints.gridy = 1;
		helpConstraints.gridwidth = 5;
		helpConstraints.fill = GridBagConstraints.HORIZONTAL;
		helpPanel.add(helpWindowName, helpConstraints);
		
		helpField = new JTextArea(20, 33);
		//ENTER "HELP" TEXT HERE
		String helpText = "For the Activity Name field, enter a name for the activity.\n"
				+ "For the Duration field, enter a whole number (Negative values and decimals will not be accepted)\n"
				+ "For the Predecessors field, enter the exact names of predecessors separate by ', '.\n"
				+ "etc.";
		helpField.setText(helpText);
		helpField.setEditable(false);
		helpConstraints.gridx = 5;
		helpConstraints.gridy = 0;
		helpConstraints.gridwidth = 15;
		helpConstraints.gridheight = 7;
		helpConstraints.fill = GridBagConstraints.HORIZONTAL;
		helpConstraints.fill = GridBagConstraints.VERTICAL;
		helpPanel.add(helpField, helpConstraints);
		
		//PATH DISPLAY PANEL------------------------------------------------------------------------------------------------


		pathDisplayPanel.setLayout(new BorderLayout());


		displayRestartButton = new JButton("Restart");
		pathDisplayField = new JTextArea(20, 33);

		pathDisplayPanel.add(pathDisplayField, BorderLayout.CENTER);
		pathDisplayPanel.add(displayRestartButton, BorderLayout.SOUTH);
		
		
		//REPORT PANEL------------------------------------------------------------------------------------------------------
		
		reportPanel.setLayout(new GridBagLayout());
		
		reportConstraints.insets = new Insets(10, 10, 10, 10);
		
		reportToHomeButton = new JButton("Home");
		reportConstraints.gridx = 0;
		reportConstraints.gridy = 0;
		reportConstraints.gridwidth = 5;
		reportConstraints.fill = GridBagConstraints.HORIZONTAL;
		reportPanel.add(reportToHomeButton, reportConstraints);
		
		reportWindowName = new JLabel("Report File Name");
		reportConstraints.gridx = 0;
		reportConstraints.gridy = 1;
		reportConstraints.gridwidth = 5;
		reportConstraints.fill = GridBagConstraints.HORIZONTAL;
		reportPanel.add(reportWindowName, reportConstraints);
		
		reportTitleField = new JTextField(30);
		reportConstraints.gridx = 5;
		reportConstraints.gridy = 1;
		reportConstraints.gridwidth = 15;
		reportConstraints.gridheight = 1;
		reportConstraints.fill = GridBagConstraints.HORIZONTAL;
		reportConstraints.fill = GridBagConstraints.VERTICAL;
		reportPanel.add(reportTitleField, reportConstraints);

		createReportButton = new JButton("Create Report");
		reportConstraints.gridx = 5;
		reportConstraints.gridy = 3;
		reportConstraints.gridwidth = 15;
		reportConstraints.gridheight = 1;
		reportConstraints.fill = GridBagConstraints.HORIZONTAL;
		reportConstraints.fill = GridBagConstraints.VERTICAL;
		reportPanel.add(createReportButton, reportConstraints);
		

		
		
		
		


		//------------------------------------------------------------------------------------------------------------------
		
		// ADDING PANELS TO CARDLAYOUT MANAGER
		panelsContainer.add(mainPanel, "Home");
		panelsContainer.add(helpPanel, "Help");
		panelsContainer.add(aboutPanel, "About");
		panelsContainer.add(pathDisplayPanel, "Path");
		panelsContainer.add(reportPanel, "Report");

		interfacePanel.show(panelsContainer, "Home");

		//ACTION LISTENERS--------------------------------------------------------------------------------------------------

		
		mainCreateReportButton.addActionListener(new ActionListener() {			// home page report button action listener

			@Override
			public void actionPerformed(ActionEvent arg0) {

				interfacePanel.show(panelsContainer, "Report");				// takes user to "Report" panel, (report page)

			}

		});
		
		reportToHomeButton.addActionListener(new ActionListener() {			// report to home page button action listener

			@Override
			public void actionPerformed(ActionEvent arg0) {

				interfacePanel.show(panelsContainer, "Home");				// takes user to "Home" panel, (report page)

			}

		});
		
		createReportButton.addActionListener(new ActionListener() {			// create report button action listener

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String reportTitle = reportTitleField.getText();
				String reportStatus;
				try {
					
					reportStatus = list.createReport(reportTitle);
					programProcessField.setText(reportStatus);					// tells user whether user created file and its name
					
				} catch (IOException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
				
				
				// stuff here on making file
				
				
				interfacePanel.show(panelsContainer, "Home");				// takes user to "Home" panel, (report page)

			}

		});
		
		
		aboutButton.addActionListener(new ActionListener() {			// about button action listener

			@Override
			public void actionPerformed(ActionEvent arg0) {

				interfacePanel.show(panelsContainer, "About");				// takes user to "About" panel, (about page)

			}

		});

		helpButton.addActionListener(new ActionListener() {					// help button action listener

			@Override
			public void actionPerformed(ActionEvent arg0) {

				interfacePanel.show(panelsContainer, "Help");				// takes user to "Help" panel, (help page)

			}

		});


		aboutToHomeButton.addActionListener(new ActionListener() {			// Home button for the ABOUT panel, action listener

			@Override
			public void actionPerformed(ActionEvent arg0) {

				interfacePanel.show(panelsContainer, "Home");				// takes user to "Home" panel, (home page)

			}

		});

		helpToHomeButton.addActionListener(new ActionListener() {			// Home button for the HELP panel, action listener

			@Override
			public void actionPerformed(ActionEvent arg0) {

				interfacePanel.show(panelsContainer, "Home");				// takes user to "Home" panel, (home page)

			}

		});

		compileButton.addActionListener(new ActionListener() {			// COMPILE button action listener
			// should account for if only 1 input node is being established.
			// else it should just print all the linked list nodes sorted in the decreasing duration
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*int errordelay = 1500; 		// sets a delay to 1.5s to give user a timed warning message

					// used for the timer creation
					ActionListener taskPerformer = new ActionListener() {
					      public void actionPerformed(ActionEvent evt) {
					    	durationField.setText("");
							activityNameField.setText("");
							durationField.setForeground(Color.BLACK);
							activityNameField.setForeground(Color.BLACK);


					      }
					 };
					 // checks if activity field is empty 
				if(activityNameField.getText().equals("")) {

							durationField.setText("");
							activityNameField.setText("Please fill all required fields"); // prompts error
							activityNameField.setForeground(Color.RED);

							Timer t = new Timer(errordelay, taskPerformer);
							t.setRepeats(false);
							t.start(); // triggers message for 1.5s

						}
				 else {

					try { // try to catch Number format if the duration is not a whole number



						String name = activityNameField.getText();
						activityNameField.setText("");

						int d = Integer.parseInt(durationField.getText());
						durationField.setText("");
						interfacePanel.show(panelsContainer, "Path"); // displays the path panel						

					}


					catch (NumberFormatException e) {
						  durationField.setText("Please Enter a whole number!"); // prompts error
						  durationField.setForeground(Color.RED);

						Timer t = new Timer(errordelay, taskPerformer); // times for 1.5s
						t.setRepeats(false);
						t.start();

					}*/

				String pathList = list.getPaths();
				pathDisplayField.setText((pathList));
				interfacePanel.show(panelsContainer, "Path"); // displays the path panel

			}

		});

		displayRestartButton.addActionListener(new ActionListener() {			// RESTART button action listener (from "Path" panel to "Home"panel/home page, 
			// should refresh all data
			// clear the linked list

			@Override
			public void actionPerformed(ActionEvent arg0) {

				interfacePanel.show(panelsContainer, "Home");			// takes it to "Path" panel/window which shows the user the activities/paths
				list.deleteLinkedList();				
				ActivityList list = new ActivityList();
				pathDisplayField.setText(null);
				list.printAll();
			}

		});

		mainRestartButton.addActionListener(new ActionListener() {			// 

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// Refresh data/restart stuff HERE
				list.deleteLinkedList();
				ActivityList list = new ActivityList();
				pathDisplayField.setText(null);
				list.printAll();
			}

		});

		exitButton.addActionListener(new ActionListener() {	


			public void actionPerformed(ActionEvent arg0) {

				System.exit(0);
			}
		});



		addButton.addActionListener(new ActionListener() {			// add/accept another activity "add another" button


			@Override
			public void actionPerformed(ActionEvent arg0) {
				int errordelay = 1500;

				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						durationField.setText("");
						activityNameField.setText("");
						durationField.setForeground(Color.BLACK);
						activityNameField.setForeground(Color.BLACK);


					}
				};

				if(activityNameField.getText().equals("")) {

					durationField.setText("");
					activityNameField.setText("Please fill all required fields");
					activityNameField.setForeground(Color.RED);

					Timer t = new Timer(errordelay, taskPerformer);
					t.setRepeats(false);
					t.start();

				}
				else {
					try {
						String name = activityNameField.getText();
						String duration = durationField.getText();
						int dur = 0;
						dur = Integer.parseInt(duration);
						String pred = predecessorField.getText();
						if (pred.equals("") || pred == null) {
							list.addFirst(name, dur);
						}
						else {
							list.add(name, dur, predecessorField.getText());
						}	
						list.printAll();
						activityNameField.setText(null);
						durationField.setText(null);
						predecessorField.setText(null);

						// add to the linked list
					}


					catch (NumberFormatException e) {
						durationField.setText("Please Enter a whole number!");
						durationField.setForeground(Color.RED);

						Timer t = new Timer(errordelay, taskPerformer);
						t.setRepeats(false);
						t.start();

					}
				}

			}

		});


		add(panelsContainer);

	}

}
