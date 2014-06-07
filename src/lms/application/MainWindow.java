package lms.application;

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
	private Button registerButton;
	private Button importButton;
	private Button exitButton;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setSize(400, 400);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.setText("LMS");
		

		// ============== exit button ================
		exitButton = new Button(shell, SWT.NONE);
		exitButton.setBounds(114, 261, 139, 42);
		exitButton.setText("Quit");
		exitButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// exit button = terminate
				shell.dispose();
			}
		});

		
		// ==============register button ===========
		registerButton = new Button(shell, SWT.NONE);
		registerButton.setBounds(114, 40, 139, 42);
		registerButton.setText("Register");
		registerButton.addSelectionListener(new SelectionAdapter()
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
		importButton = new Button(shell, SWT.NONE);
		importButton.setBounds(114, 101, 139, 42);
		importButton.setText("Import");
		importButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// import button is selected
				new ImportWindow();
			}
		});

		
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
		new MainWindow();
	}
}
