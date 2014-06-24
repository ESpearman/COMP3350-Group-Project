package lms.application;


import lms.business.Term;
import lms.business.logic.CurrentTermInfo;
import lms.business.logic.DemoDataGenerator;
import lms.config.ConfigData;
import lms.persistence.ConnectionPool;
import lms.persistence.CurrentDB;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;

public class MainWindow
{
	private Display display;
	private Shell shell;
	private Button btnRegister;
	private Button btnImport;
	private Button btnQuit;
	private Button btnExport;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setSize(163, 256);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.setText("LMS");
		

		// ============== quit button ================
		btnQuit = new Button(shell, SWT.NONE);
		btnQuit.setBounds(19, 181, 111, 27);
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
		btnRegister.setBounds(19, 10, 111, 27);
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
		btnImport = new Button(shell, SWT.NONE);
		btnImport.setBounds(19, 55, 111, 27);
		btnImport.setText("Import");
		btnImport.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// import button is selected
				new ImportWindow();
			}
		});
		
		
		// =============== export button ==============
		btnExport = new Button(shell, SWT.NONE);
		btnExport.setBounds(19, 88, 111, 27);
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
		CurrentDB.init(false);
		
		//ConfigData must be initialized BEFORE ConnectionPool
		ConfigData.init();
		ConnectionPool.init(4);
		
		//Hard code a term to be used for iteration 1 only
		Term term = new Term("Demo Term");
		CurrentTermInfo.currentTerm = term;
		term.save();
		
		DemoDataGenerator.generate();
		
		new MainWindow();
	}
}
