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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;

import acceptanceTests.EventLoop;
import acceptanceTests.Register;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Label;

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
	private Label lblUofm;
	private Label lblSSA;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shell.setSize(256, 291);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.setText("LMS");
		shell.addListener(SWT.Close, new Listener()
		{
			public void handleEvent(Event event)
			{
				closeAll();
			}
		});
		
		
		// ======= dropdown term =======
		drpTerm = new Combo(shell, SWT.NONE);
		drpTerm.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent arg0)
			{
				updateTerms();
				drpTerm.setText("Select a Term");
				buttonControl(false);
			}
		});
		drpTerm.setBounds(10, 20, 111, 23);
		drpTerm.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				if(drpTerm.getSelectionIndex()!=-1)
				{
					selectedTerm = termsAL.get(drpTerm.getSelectionIndex());
					CurrentTermInfo.currentTerm = selectedTerm;
					// Let user click every other button once term is decided
					buttonControl(true);
				}
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
		btnAbout.setBounds(68, 123, 111, 27);
		
		
		// ============== quit button ================
		btnQuit = new Button(shell, SWT.NONE);
		btnQuit.setBounds(68, 156, 111, 27);
		btnQuit.setText("Quit");
		btnQuit.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				closeAll();
				shell.dispose();
			}
		});
		
		
		
		// ======== logo ( uofm, ssa ) =========
		Image uofm = new Image(display, "img/resize_um.png");
		lblUofm = new Label(shell, SWT.NONE);
		lblUofm.setText("uofm");
		lblUofm.setBounds(127, 193, 69, 60);
		lblUofm.setImage(uofm);

		Image ssa = new Image(display, "img/resize_ssa.png");
		lblSSA = new Label(shell, SWT.NONE);
		lblSSA.setText("ssa");
		lblSSA.setBounds(50, 193, 71, 60);
		lblSSA.setImage(ssa);
		
		
		
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
			shell.dispose();
		}
	}
	
	private void updateTerms()
	{
		termsAL = Term.getAll();
		terms = new String[termsAL.size()];
		for(int i = 0; i < termsAL.size(); i++)
		{
			terms[i] = termsAL.get(i).getName();
		}
		drpTerm.setItems(terms);
	}
	
	private void closeAll()
	{
	    Shell[] shells = Display.getCurrent().getShells();
        for(Shell shell : shells)
        {
            String data = (String) shell.getData();
            if(data != null)
            {
                shell.dispose();
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
