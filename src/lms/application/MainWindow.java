package lms.application;


import lms.business.Term;
import lms.business.logic.CurrentTermInfo;
import lms.business.logic.DemoDataGenerator;

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
		shell.setSize(171, 197);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.setText("LMS");
		

		// ============== exit button ================
		btnQuit = new Button(shell, SWT.NONE);
		btnQuit.setBounds(10, 124, 135, 25);
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
		btnRegister.setBounds(10, 10, 135, 25);
		btnRegister.setText("Register");
		btnRegister.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// when register button is clicked
				
				new RegisterWindow();
				display.sleep();
			}
		});

		
		// =============== import button ============
		btnImport = new Button(shell, SWT.NONE);
		btnImport.setBounds(10, 41, 135, 25);
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
		btnExport.setBounds(10, 72, 135, 25);
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
		//Hard code a term to be used for iteration 1 only
		Term term = new Term("Demo Term");
		CurrentTermInfo.currentTerm = term;
		term.save();
		
		DemoDataGenerator.generate();
		
		new MainWindow();
	}
}
