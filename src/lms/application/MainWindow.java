package lms.application;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class MainWindow
{
	private Display display;
	private Shell shell;
	private Button registerButton;
	private Button importButton;
	private Button exitButton;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	
	public void CreateWindow()
	{
		shell = new Shell();
		shell.setSize(800, 600);
		shell.setLocation(100, 100);
		shell.setText("Locker Management System");
		

		// ============== exit button ================
		exitButton = new Button(shell, SWT.NONE);
		exitButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// exit button = terminate
				shell.dispose();
			}
		});
		exitButton.setBounds(276, 414, 222, 76);
		exitButton.setText("Quit");
		
		
		
		// ==============register button ===========
		registerButton = new Button(shell, SWT.NONE);
		registerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// when register button is clicked
				
			}
		});
		registerButton.setBounds(276, 95, 222, 76);
		registerButton.setText("Register");
		
		
		// =============== import button ============
		importButton = new Button(shell, SWT.NONE);
		importButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// import button is selected
			}
		});
		importButton.setBounds(276, 183, 222, 76);
		importButton.setText("Import");
		
		
		
		shell.layout();
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
		CreateWindow();
	}
	
	
	public static void main(String[] args)
	{

		new MainWindow();

	}
}
