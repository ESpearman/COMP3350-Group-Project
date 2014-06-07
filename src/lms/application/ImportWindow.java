package lms.application;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ImportWindow
{
	private Display display;
	private Shell shell;
	
	private Text filePathText;
	
	private Button lockerRadio;
	private Button studentRadio;
	
	
	private Button importButton;
	private Button backButton;
	private Button browseButton;
	
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
		
		
		// ====== locker radio button =====
		lockerRadio = new Button(shell, SWT.RADIO);
		lockerRadio.setBounds(10, 48, 121, 32);
		lockerRadio.setText("Locker");
		
		
		// ====== student radio button =====
		studentRadio = new Button(shell, SWT.RADIO);
		studentRadio.setBounds(10, 97, 121, 32);
		studentRadio.setText("Student");
		
		
		// ====== file (path) text field =======
		filePathText = new Text(shell, SWT.BORDER);
		filePathText.setEditable(false);
		filePathText.setBounds(45, 164, 272, 38);
		
		
		// ======= import button =========
		importButton = new Button(shell, SWT.NONE);
		importButton.setBounds(225, 241, 139, 42);
		importButton.setText("Import");
		importButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// when import button is selected
				// new Dialog pop;
				
			}
		});

		
		
		//======== back button ==========
		backButton = new Button(shell, SWT.NONE);
		backButton.setBounds(10, 241, 139, 42);
		backButton.setText("Back");
		backButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// when back button is selected
				shell.close();
			}
		});
		
		
		//======= browse button===========
		browseButton = new Button(shell, SWT.NONE);
		browseButton.setBounds(178, 73, 139, 42);
		browseButton.setText("Browse");
		browseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// browse button is selected
				
			}
		});


		// ======shell open, close ========
		shell.open();
		
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}
	
	public ImportWindow()
	{
		display = Display.getDefault();
		runWindow();
	}
}
