package lms.application;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RegisterWindow
{
	private Display display;
	private Shell shell;
	
	private Text searchText;
	private Text firstNameText;
	private Text lastNameText;
	private Text emailText;
	
	
	private Button searchButton;
	private Button backButton;
	private Button registerButton;
	
	private Label searchSeparate;
	private Label scienceLable;
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setSize(400, 362);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.setText("Register");
		
		
		
		// ====== search text =========
		searchText = new Text(shell, SWT.BORDER);
		searchText.setBounds(39, 26, 82, 38);

		
		// ===== first name text ========
		firstNameText = new Text(shell, SWT.BORDER);
		firstNameText.setBounds(39, 92, 82, 38);
		
		
		
		// ====== last name text ========
		lastNameText = new Text(shell, SWT.BORDER);
		lastNameText.setBounds(234, 92, 82, 38);
		
		
		
		
		// ====== email text =======
		emailText = new Text(shell, SWT.BORDER);
		emailText.setBounds(39, 150, 82, 38);
		
		
		
		
		// ====== separate bar ======
		searchSeparate = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		searchSeparate.setBounds(10, 70, 354, 16);
		

		
		// ====== Sci/non-Sci student lable ========
		scienceLable = new Label(shell, SWT.NONE);
		scienceLable.setBounds(205, 153, 111, 32);
		
		
		
		// ======== back button =========
		backButton = new Button(shell, SWT.NONE);
		backButton.setBounds(10, 228, 139, 42);
		backButton.setText("Back");
		backButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// when back button is selected
				shell.close();
			}
		});

		
		
		// ====== register button =========
		registerButton = new Button(shell, SWT.NONE);
		registerButton.setBounds(225, 228, 139, 42);
		registerButton.setText("Register");
		registerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// when register button is selected
				new LockerWindow();
			}
		});

		
		
		
		// ====== search button ========
		searchButton = new Button(shell, SWT.NONE);
		searchButton.setBounds(177, 22, 139, 42);
		searchButton.setText("Search");
		searchButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// when search button is selected
				firstNameText.setText("firstName");
				lastNameText.setText("lastName");
				emailText.setText("Email");
				scienceLable.setText("Science");
			}
		});

		
		
		// ====== shell open, close =====
		shell.open();
		
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}

	public RegisterWindow()
	{
		display = Display.getDefault();
		runWindow();
	}
}
