package lms.application;


import java.util.UUID;

import lms.businesslogic.CurrentTermInfo;
import lms.businesslogic.LockerPrice;
import lms.config.ConfigData;
import lms.domainobjects.Term;
import lms.persistence.ConnectionPool;
import lms.persistence.DBInjector;
import lms.persistence.DBProxy;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;

public class MainWindow
{
	private Display display;
	private Shell shell;
	private Button btnRegister;
	private Button btnSetup;
	private Button btnQuit;
	private Button btnExport;
	
	private Combo drpTerm;
	private Button btnAboutUs;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setSize(266, 234);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.setText("LMS");
		

		// ============== quit button ================
		btnQuit = new Button(shell, SWT.NONE);
		btnQuit.setBounds(70, 159, 111, 27);
		btnQuit.setText("Quit");
		btnQuit.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// exit button = terminate
				shell.dispose();
			}
		});

		
		// ==============register button ===========
		btnRegister = new Button(shell, SWT.NONE);
		btnRegister.setBounds(127, 17, 111, 27);
		btnRegister.setText("Register");
		btnRegister.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// when register button is clicked
				
				new StudentWindow("Register");
				display.sleep();
			}
		});

		
		// =============== import button ============
		btnSetup = new Button(shell, SWT.NONE);
		btnSetup.setBounds(10, 64, 111, 27);
		btnSetup.setText("Setup");
		btnSetup.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// import button is selected
				new SetupWindow();
			}
		});
		
		
		// =============== export button ==============
		btnExport = new Button(shell, SWT.NONE);
		btnExport.setBounds(127, 64, 111, 27);
		btnExport.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// Export button is selected
				new ExportWindow();
			}
		});
		btnExport.setText("Export");
		
		
		
		
		// ======= dropdown term =======
		drpTerm = new Combo(shell, SWT.NONE);
		drpTerm.setBounds(10, 20, 111, 27);
		drpTerm.setText("Select Term");
		
		
		
		// ======= button about us ========
		btnAboutUs = new Button(shell, SWT.NONE);
		btnAboutUs.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				new AboutWindow();
			}
		});
		btnAboutUs.setText("About");
		btnAboutUs.setBounds(70, 126, 111, 27);
		
		
		//======= shell open, close ======
		shell.open();
		
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}
	
	
	public MainWindow()
	{
		display = Display.getDefault();
		runWindow();
	}
	
	public static void main(String[] args)
	{
		DBProxy.instance = new DBProxy();
		DBInjector.injectInto(DBProxy.instance, false);
		
		//ConfigData must be initialized BEFORE ConnectionPool
		ConfigData.init();
		ConnectionPool.init(4);
		
		LockerPrice.scienceFull = ConfigData.fullScience;
		LockerPrice.scienceHalf = ConfigData.halfScience;
		
		LockerPrice.nonScienceFull = ConfigData.fullNonScience;
		LockerPrice.nonScienceHalf = ConfigData.halfNonScience;
		
		//Hard code a term to be used for iteration 1 only
		Term term = new Term(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"),"Demo Term");
		CurrentTermInfo.currentTerm = term;
		term.save();
		
		new MainWindow();
	}
}
