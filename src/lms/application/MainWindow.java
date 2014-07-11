package lms.application;

import java.util.ArrayList;

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

import acceptanceTests.EventLoop;
import acceptanceTests.Register;

public class MainWindow
{
	private Display display;
	private Shell shell;
	
	private Button btnRegister;
	private Button btnSetup;
	private Button btnQuit;
	private Button btnExport;
	private Button btnAbout;
	
	private Combo drpTerm;
	private Term selectedTerm;
	private ArrayList<Term> termsAL;
	private String terms[];
	
	/**
	 * Launch the application.
	 * @param args
	 */
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shell.setSize(266, 234);
		
		//final Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.setText("LMS");
		
		
		
		
		// Build up terms to select from
		for(int i = 0; i < termsAL.size(); i++)
		{
			terms[i] = termsAL.get(i).getName();
		}
		
		// ======= dropdown term =======
		drpTerm = new Combo(shell, SWT.NONE);
		drpTerm.setBounds(10, 20, 111, 23);
		drpTerm.setItems(terms);
		drpTerm.setText("Select a Term");
		drpTerm.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				selectedTerm = termsAL.get(drpTerm.getSelectionIndex());
				CurrentTermInfo.currentTerm = selectedTerm;
				// Let user click every other button once term is decided
				buttonControl(true);
			}
		});
		
		
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
				if(!alrOpened("StudentWindow"))
				{
					new StudentWindow("Register");
				}
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
				
				if(!alrOpened("SetupWindow"))
				{
					new SetupWindow();
				}
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
				if(!alrOpened("ExportWindow"))
				{
					new ExportWindow();
				}
			}
		});
		btnExport.setText("Export");
		
		
		// ======= button about us ========
		btnAbout = new Button(shell, SWT.NONE);
		btnAbout.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				if(!alrOpened("AboutWindow"))
				{
					new AboutWindow();
				}
			}
		});
		btnAbout.setText("About");
		btnAbout.setBounds(70, 126, 111, 27);
		

		buttonControl(false);
		
		
		//======= shell open, close ======
		shell.open();
		
		if(EventLoop.isEnabled())
		{
			while (!shell.isDisposed())
			{
				if (!display.readAndDispatch())
				{
					display.sleep();
				}
			}
		}
		
	}
	
	private void buttonControl(boolean bool)
	{
		btnRegister.setEnabled(bool);
		btnExport.setEnabled(bool);
	}
	private static boolean alrOpened(String name)
	{
		boolean res = false;
		
	    Shell[] shells = Display.getCurrent().getShells();
        for(Shell shell : shells)
        {
            String data = (String) shell.getData();
            if(data != null && data.equals(name))
            {
                shell.setFocus();
                res = true;
            }
        }
		return res;
	}
	
	public MainWindow()
	{
		DBProxy.instance = new DBProxy();
		DBInjector.injectInto(DBProxy.instance, false);
		
		//ConfigData must be initialized BEFORE ConnectionPool
		ConfigData.init();
		ConnectionPool.init(4);
		
		termsAL = Term.getAll();
		terms = new String[termsAL.size()];
		
		LockerPrice.scienceFull = ConfigData.fullScience;
		LockerPrice.scienceHalf = ConfigData.halfScience;
		LockerPrice.nonScienceFull = ConfigData.fullNonScience;
		LockerPrice.nonScienceHalf = ConfigData.halfNonScience;
		
		Register.newWindow(this);
		display = Display.getDefault();
		runWindow();
	}
	
	public static void startUp()
	{
		
		new MainWindow();
	}
	
	public static void main(String[] args)
	{
		startUp();
	}
}
