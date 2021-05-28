import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.net.InetAddress;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectGUI {

	protected Shell shlPingAndLog;
	private Text txtMesaj;
	private String IP;
	
	Logger logger = LoggerFactory.getLogger("PingLogger");
	private Button btnNewButton;
	private Text text;
	private Label lblTimeOfPing;
	
	private final long PERIOD = 1000L; // Adjust to suit timing
	private long lastTime = System.currentTimeMillis() - PERIOD;	
	boolean pingtrue;
	boolean methodtest;
	private Label lblNewLabel_1;
	java.util.Date date = new Date(); 

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {	
		try {
			ProjectGUI window = new ProjectGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlPingAndLog.open();
		shlPingAndLog.layout();
		while (!shlPingAndLog.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPingAndLog = new Shell();
		shlPingAndLog.setSize(470, 170);
		shlPingAndLog.setText("Ping and Log");
		
		Label lblNewLabel = new Label(shlPingAndLog, SWT.NONE);
		lblNewLabel.setBounds(10, 34, 105, 15);
		lblNewLabel.setText("IP Address to ping :");
		
		txtMesaj = new Text(shlPingAndLog, SWT.BORDER);
		txtMesaj.setBounds(121, 31, 76, 21);
		
		Button btnCheckPing = new Button(shlPingAndLog, SWT.NONE);
		btnCheckPing.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				IP=txtMesaj.getText();
				PingToIP(IP);			
				
			}
		});
		btnCheckPing.setBounds(215, 29, 75, 25);
		btnCheckPing.setText("Check Ping");
		
		btnNewButton = new Button(shlPingAndLog, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				int pingtime=(Integer.parseInt(text.getText()))*12;
				logger.info("Ping Time : " + text.getText() + "minute set");
				for (int i=0; i<pingtime; i++) {
					IP=txtMesaj.getText();
					PingToIP(IP);					
				}
			}
		});
		btnNewButton.setBounds(319, 68, 105, 31);
		btnNewButton.setText("Ping and Log");
		
		text = new Text(shlPingAndLog, SWT.BORDER);
		text.setBounds(335, 41, 76, 21);
		
		lblTimeOfPing = new Label(shlPingAndLog, SWT.NONE);
		lblTimeOfPing.setBounds(319, 20, 125, 15);
		lblTimeOfPing.setText("Time of Ping(minute)");
		
		lblNewLabel_1 = new Label(shlPingAndLog, SWT.NONE);
		lblNewLabel_1.setBounds(10, 84, 280, 15);

	}
	
	public void PingToIP(String ipAddress) {
		try {
			//String ipAddress = "127.0.0.1";
			InetAddress inet = InetAddress.getByName(ipAddress);
			System.out.println("Sending Ping Request to " + ipAddress);
			logger.info("Sending Ping Request to " + ipAddress);
			
			pingtrue=false;
			
			if (inet.isReachable(5000)) {
				System.out.println(ipAddress + " is reachable.");
				logger.info(ipAddress + " is reachable.");	
				
				//Ping status on label
				lblNewLabel_1.setText("Ping to " + txtMesaj.getText() + " is reachable");
				
				//5 saniye bekleme
				long start = new Date().getTime();
				while(new Date().getTime() - start < 5000L){}			
				
				
			} else {
				
				System.out.println(ipAddress + " NOT reachable.");
				logger.error(ipAddress + " is NOT reachable.");
				
				//Ping status on label
				lblNewLabel_1.setText("Ping to " + txtMesaj.getText() + " is NOT reachable");
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
	}
	
	public void onTick() {//Called every "Tick"
	    long thisTime = System.currentTimeMillis();

	    if ((thisTime - lastTime) >= PERIOD) {
	        lastTime = thisTime;

	        if(pingtrue) { //If my variable is true
	            methodtest = true; //Setting my boolean to true
	            /**
	            *Doing a bunch of things.
	            **/
	            //I need a delay for about one second here.
	            
	            PingToIP(IP);
	            
	            methodtest = false; //Setting my boolean to false;
	        }
	    }
	}
	
	}