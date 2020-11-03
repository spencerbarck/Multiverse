
import multiverseStructure.Galaxy;
import multiverseStructure.Multiverse;
import multiverseStructure.Planet;
import multiverseStructure.SolarSystem;
import multiverseStructure.Universe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

//Created by Spencer Barck on 10/21/2020
//Multiverse Generator. Generates an array of Universes each universe has an array of galaxies, 
//each galaxy an array of Solar Systems and each Solar System an array of Planets.
//
//GUI with Swing
public class HelloMultiverse{

	//read and write
	private File accountsFile;
	private Scanner accountsScanner;
	private FileWriter accountsWriter;
	
	//login GUI elements
	private JFrame logInFrame;
	private JPanel logInPanel;
	private JLabel userLabel;
	private JTextField userText;
	private JLabel passwordLabel;
	private JPasswordField passwordText;
	private JPanel authenticatePanel;
	private JButton logInButton; 
	private JButton createAccountButton; 
	private JLabel successLabel;
	
	//create account GUI elements
	
	
	//multiverse GUI elements
	private JFrame myFrame;
	
	private JButton startButton;
	private JLabel startLabel;
	
	private JPanel startPanel;
	private JPanel mPanel;
	
	private Container myContainer;
	
	private JScrollPane scrollPane;
	
	private JButton resetButton;
	
	private JButton settingsButton;
	private Boolean settingsOpen;
	
	private JPanel settingsPanel;
	
	private JSpinner uSettingsSpinner;
	private JSpinner gSettingsSpinner;
	private JSpinner sSettingsSpinner;
	private JSpinner pSettingsSpinner;
	
	private JCheckBox rCheckBox;
	private JCheckBox zeroCheckBox;
	
	public static void main(String[] args) {
		new HelloMultiverse();
	}
	
	public HelloMultiverse() {
		loadLoginFrame();
	}
	
	//loads the login frame of the application
	public void loadLoginFrame() {
		logInFrame = new JFrame();
		logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logInFrame.setTitle("LogIn");
		logInFrame.setSize(350,200);
				
		logInPanel = new JPanel();
		logInPanel.setLayout(null);
		logInFrame.add(logInPanel);
		
		userLabel = new JLabel("User");
		userLabel.setBounds(10,20,80,25);
		logInPanel.add(userLabel);
		
		userText = new JTextField(20);
		userText.setBounds(100,20,165,25);
		logInPanel.add(userText);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10,50,80,25);
		logInPanel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(100,50,165,25);
		logInPanel.add(passwordText);
		
		authenticatePanel = new JPanel();
		authenticatePanel.setBounds(0,80,300,25);
		authenticatePanel.setLayout(new GridLayout(0,2));
		authenticatePanel.setBackground(Color.red);
		
		logInButton = new JButton("Login");
		logInButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if(authenticate(userText.getText(), passwordText.getText())==true) {
	        		logInFrame.setVisible(false);
	        		logInFrame.dispose();
	        		loadMultiverseFrame();
	        	}else {
	        		successLabel.setText("Login Unsuccessful");
	        	}
	        }
		});
		authenticatePanel.add(logInButton);
		
		createAccountButton = new JButton("Create Account");
		createAccountButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	createAccount(userText.getText(),passwordText.getText());
	        }
		});
		authenticatePanel.add(createAccountButton);
		
		logInPanel.add(authenticatePanel);
		
		successLabel = new JLabel("Please Enter user and password");
		successLabel.setBounds(10,120,300,25);
		logInPanel.add(successLabel);
		
		logInFrame.setVisible(true);
	}
	
	public boolean authenticate(String user, String password) {
		boolean authenticated = false;
		
		accountsFile = new File("/Users/daslamhamer/Desktop/MultiverseAccounts/accounts.txt");
		try {
			accountsScanner = new Scanner(accountsFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		while(accountsScanner.hasNext()) {
			String line = accountsScanner.nextLine();
			String[] accountArray = line.split(",");
			if(accountArray[0].equals(user) && accountArray[1].equals(password)){
				authenticated = true;
			}
		}
		return authenticated;
	}
	
	//create a username and password that will be accepted
	public void createAccount(String user, String password) {
		accountsFile = new File("/Users/daslamhamer/Desktop/Java/Multiverse/src/accounts.txt");
		try {
			accountsScanner = new Scanner(accountsFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String newFile = "";
		while(accountsScanner.hasNext()) {
			String line = accountsScanner.nextLine();
			newFile = newFile.concat(line + System.lineSeparator());
		}
		
		newFile = newFile.concat(user + "," + password + System.lineSeparator());
		try {
			accountsWriter = new FileWriter("/Users/daslamhamer/Desktop/Java/Multiverse/src/accounts.txt");
			accountsWriter.write(newFile);
			accountsWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//loads the main frame of the application
	public void loadMultiverseFrame(){
		
		//setup frame
		myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setTitle("Multiverse");
		myFrame.setSize(700,700);
		myFrame.setLocation(100,100);
		myFrame.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
		
		//top panel
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new LineBorder(Color.BLACK,3));
		topPanel.setLayout(new FlowLayout(9));
		topPanel.setBackground(Color.red);
		
		//reset button
		resetButton = new JButton("Restart");
		resetButton.setFont(new Font("Arial", Font.PLAIN, 12));
		resetButton.setPreferredSize(new Dimension(80, 40));
		resetButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	startButton.setEnabled(true);
				myContainer.remove(scrollPane);
	        	initializeFrame();
	        }          
	    });
		
		//settings button
		settingsButton = new JButton("Settings");
		settingsButton.setFont(new Font("Arial", Font.PLAIN, 12));
		settingsButton.setPreferredSize(new Dimension(80, 40));
		settingsOpen = false;
		
		JPanel uSpinPanel = new JPanel();		
		uSpinPanel.setLayout(new BoxLayout(uSpinPanel,BoxLayout.PAGE_AXIS));		
		JLabel uSpinLabel = new JLabel("Universes:");
		uSettingsSpinner = new JSpinner();
		uSpinPanel.add(uSpinLabel);
		uSpinPanel.add(uSettingsSpinner);
		uSpinPanel.setVisible(false);
		
		JPanel gSpinPanel = new JPanel();		
		gSpinPanel.setLayout(new BoxLayout(gSpinPanel,BoxLayout.PAGE_AXIS));		
		JLabel gSpinLabel = new JLabel("  Galaxies:");
		gSettingsSpinner = new JSpinner();
		gSpinPanel.add(gSpinLabel);
		gSpinPanel.add(gSettingsSpinner);
		gSpinPanel.setVisible(false);
		
		JPanel sSpinPanel = new JPanel();		
		sSpinPanel.setLayout(new BoxLayout(sSpinPanel,BoxLayout.PAGE_AXIS));		
		JLabel sSpinLabel = new JLabel("Solar Systems:");
		sSettingsSpinner = new JSpinner();
		sSpinPanel.add(sSpinLabel);
		sSpinPanel.add(sSettingsSpinner);
		sSpinPanel.setVisible(false);
		
		JPanel pSpinPanel = new JPanel();		
		pSpinPanel.setLayout(new BoxLayout(pSpinPanel,BoxLayout.PAGE_AXIS));		
		JLabel pSpinLabel = new JLabel("Planets:");
		pSettingsSpinner = new JSpinner();
		pSpinPanel.add(pSpinLabel);
		pSpinPanel.add(pSettingsSpinner);
		pSpinPanel.setVisible(false);
		
		JPanel rCheckBoxPanel = new JPanel();		
		rCheckBoxPanel.setLayout(new BoxLayout(rCheckBoxPanel,BoxLayout.PAGE_AXIS));
		rCheckBox = new JCheckBox("Random",false);
		rCheckBoxPanel.add(rCheckBox);
		rCheckBoxPanel.setVisible(false);
		
		JPanel zeroCheckBoxPanel = new JPanel();		
		zeroCheckBoxPanel.setLayout(new BoxLayout(zeroCheckBoxPanel,BoxLayout.PAGE_AXIS));
		zeroCheckBox = new JCheckBox("No Zeros", true);
		zeroCheckBoxPanel.add(zeroCheckBox);
		zeroCheckBoxPanel.setVisible(false);
		
		settingsButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if(settingsOpen == false) {
	        		uSpinPanel.setVisible(true);
	        		gSpinPanel.setVisible(true);
		        	sSpinPanel.setVisible(true);
		        	pSpinPanel.setVisible(true);
		        	rCheckBoxPanel.setVisible(true);
		        	zeroCheckBoxPanel.setVisible(true);
		        	settingsOpen = true;
	        	}else if (settingsOpen == true) {
	        		uSpinPanel.setVisible(false);
	        		gSpinPanel.setVisible(false);
		        	sSpinPanel.setVisible(false);
		        	pSpinPanel.setVisible(false);
		        	rCheckBoxPanel.setVisible(false);
		        	zeroCheckBoxPanel.setVisible(false);
		        	settingsOpen = false;
	        	}
	        }          
	    });
		
		topPanel.add(resetButton);
		topPanel.add(settingsButton);
		topPanel.add(uSpinPanel);
		topPanel.add(gSpinPanel);
		topPanel.add(sSpinPanel);
		topPanel.add(pSpinPanel);
		topPanel.add(rCheckBoxPanel);
		topPanel.add(zeroCheckBoxPanel);
	
		//west panel
		JPanel westPanel = new JPanel();
		westPanel.setBorder(new LineBorder(Color.BLACK,3));
		westPanel.setLayout(new FlowLayout(5));
		westPanel.setBackground(Color.GREEN);
		JButton gb1 = new JButton("Profile");
		gb1.setFont(new Font("Arial", Font.PLAIN, 12));
		westPanel.add(gb1);
		
		myContainer = myFrame.getContentPane();
		myContainer.setLayout(new BorderLayout(8,6));
		
		//add top panel to frame
		JScrollPane topScrollPane = new JScrollPane(topPanel);
		
		myContainer.add(topScrollPane,BorderLayout.NORTH);
		
		//add west panel to frame
		myContainer.add(westPanel,BorderLayout.WEST);
		
		initializeFrame();
	}
	
	//used when multiverse frame is initially loaded and when reset button is clicked
	public void initializeFrame() {
			//make reset button invisible
			resetButton.setEnabled(false);
		
			//label and button for generator
			startButton = new JButton("Make a Multiverse");
			startButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	//button to start universe generator
		    		startButton.setEnabled(false);
		    		resetButton.setEnabled(true);
		    		Multiverse myMultiverseEmpty = new Multiverse();
		    		generateUniverses(myMultiverseEmpty);
		    		System.out.println(myMultiverseEmpty.getName()+":");		
		        }
			});
			startButton.setMaximumSize(new Dimension(150, 20));
			startButton.setAlignmentX(startPanel.CENTER_ALIGNMENT);
			startLabel = new JLabel("Multiverse Maker");
			startLabel.setMaximumSize(new Dimension(150, 20));
			startLabel.setAlignmentX(startPanel.CENTER_ALIGNMENT);
			
			//start panel
			startPanel = new JPanel();
			startPanel.setLayout(new BoxLayout(startPanel,BoxLayout.PAGE_AXIS));
			
			//add label and button to start panel
			startPanel.add(startLabel);
			startPanel.add(startButton);
			
			//multiverse panel
			mPanel = new JPanel();
			mPanel.setLayout(new BoxLayout(mPanel,BoxLayout.PAGE_AXIS));
			mPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			mPanel.setMaximumSize(new Dimension(900, 1500));
			mPanel.setAlignmentX(startPanel.CENTER_ALIGNMENT);
			mPanel.setLayout(new GridLayout(0,2,10,10));
			
			//add multiverse panel to start panel
			startPanel.add(mPanel);

			//add start panel to conatiner
			myContainer.add(startPanel, BorderLayout.CENTER);
			
			//update the frame
			myFrame.setVisible(true);
	}
	
	//used after make multiverse button is clicked
	public void updateFrame() {
		//updates frame by re-adding start panel in the form of a scroll pane
		scrollPane = new JScrollPane(startPanel);
		myContainer.add(scrollPane, BorderLayout.CENTER);
		myFrame.setVisible(true);
	}
	
	//generates a random number of realms
	public int randomRealmNumber(String realm) {
		
		int spinnerValue;
		
		//depending on what the string input is it will generate a number from a settings spinner
		switch (realm) {
			case "Universe":{
				spinnerValue = (int)uSettingsSpinner.getValue();
				break;
			}
			case "Galaxy":{
				spinnerValue = (int)gSettingsSpinner.getValue();
				break;
			}
			case "SolarSystem":{
				spinnerValue = (int)sSettingsSpinner.getValue();
				break;
			}
			case "Planet":{
				spinnerValue = (int)pSettingsSpinner.getValue();
				break;
			}
			default :{
				//should never get here
				spinnerValue = -1;
			}
		}

		int realmNumber;
		//if random checkbox is selected a random number from 0 to the spinner value will be chosen 
		if (rCheckBox.isSelected()==true && spinnerValue>0) {
			Random rand = new Random();
			realmNumber = rand.nextInt(spinnerValue);
		}else realmNumber = (int)uSettingsSpinner.getValue();
		
		//if no zeros is checked than the number will become one if it is zero
		if(zeroCheckBox.isSelected()==true) {
			if(realmNumber==0)realmNumber=1;
		}
		return realmNumber;
	}
	
	public void generateUniverses(Multiverse multiverse) {
		//randomly picks the amount of universes to add
		int rand_int = randomRealmNumber("Universe");
		
		//interate through the array of universes
		for(int i = 0; i < rand_int; i++) {
			multiverse.addUniverse();
			Universe[] uArray = multiverse.getSubrealmArray();
			
			//these lines just output to the console
			System.out.println("	"+uArray[i].getName());	
			System.out.println("");
			
			//add the name of the universe in the form of a label
			mPanel.add(new JButton(uArray[i].getName()));
			
			//create a panel fot the universe
			JPanel uPanel = new JPanel();
			uPanel.setLayout(new BoxLayout(uPanel,BoxLayout.PAGE_AXIS));
			uPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			
			//populates the universe with galaxies 
			generateGalaxies(uArray[i], uPanel);
			
			//add universe panel to multiverse panel
			JScrollPane uScrollPane = new JScrollPane(uPanel);
			mPanel.add(uScrollPane);
		}
		//add mulitverse panel to start panel
		JScrollPane mScrollPane = new JScrollPane(mPanel);
		startPanel.add(mScrollPane);
		
		//update the frame after universes are generated
		updateFrame();
		
		//these lines just output to the console
		System.out.println("----------------------");
		System.out.println("Number of Universes: "+multiverse.getNumberOfSubrealms());
	}
	
	public void generateGalaxies(Universe universe, JPanel uPanel) {
		//randomly picks the amount of galaxies to add
		int rand_int = randomRealmNumber("Galaxy");
		
		//interate through the array of galaxies
		for(int i = 0; i < rand_int; i++) {
			universe.addGalaxy();
			Galaxy[] gArray = universe.getSubrealmArray();
			
			//these lines just output to the console
			System.out.println("		"+gArray[i].getName());
			System.out.println("");
			
			//add the name of the galaxy in the form of a label
			uPanel.add(new JButton(gArray[i].getName()));
			
			//create a panel for the galaxy
			JPanel gPanel = new JPanel();
			gPanel.setLayout(new BoxLayout(gPanel,BoxLayout.PAGE_AXIS));
			gPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			gPanel.setMaximumSize(new Dimension(10, 10));
			
			//populates the galaxy with solar systems 
			generateSolarSystems(gArray[i],gPanel);
			
			//add galaxy panel to universe panel
			JScrollPane gScrollPane = new JScrollPane(gPanel);
			uPanel.add(gScrollPane);
		}
		
		//these lines just output to the console
		System.out.println("	----------------------");
		System.out.println("		Number of Galaxies: "+universe.getNumberOfSubrealms());
	}
	
	public void generateSolarSystems(Galaxy galaxy, JPanel gPanel) {
		//randomly picks the amount of solar systems to add
		int rand_int = randomRealmNumber("SolarSystem");
		
		//interate through the array of solar systems
		for(int i = 0; i < rand_int; i++) {
			galaxy.addSolarSystem();
			SolarSystem[] sArray = galaxy.getSubrealmArray();
			
			//these lines just output to the console
			System.out.println("			"+sArray[i].getName());
			System.out.println("");
			
			//add the name of the solar system in the form of a label
			gPanel.add(new JButton(sArray[i].getName()));
			
			//create a panel for the solar system
			JPanel sPanel = new JPanel();
			
			sPanel.setLayout(new BoxLayout(sPanel,BoxLayout.PAGE_AXIS));
			sPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			
			//populates the solar system with planets 
			generatePlanets(sArray[i], sPanel);
			
			//add solar system panel to galaxy panel
			gPanel.add(sPanel);
		}
		//these lines just output to the console
		System.out.println("		----------------------");
		System.out.println("			Number of Solar Systems: "+galaxy.getNumberOfSubrealms());
	}
	
	public void generatePlanets(SolarSystem solarSystem, JPanel sPanel) {
		//randomly picks the amount of planets to add
		int rand_int = randomRealmNumber("Planet");
		
		//interate through the array of planets
		for(int i = 0; i < rand_int; i++) {
			solarSystem.addPlanet();
			Planet[] pArray = solarSystem.getSubrealmArray();
			
			//these lines just output to the console
			System.out.println("				"+pArray[i].getName());
			System.out.println("");
			
			//add the name of the planet in the form of a label
			sPanel.add(new JLabel(pArray[i].getName(), SwingConstants.LEFT));
		}
		//these lines just output to the console
		System.out.println("			----------------------");
		System.out.println("				Number of Planets: "+solarSystem.getNumberOfSubrealms());
	}
}


