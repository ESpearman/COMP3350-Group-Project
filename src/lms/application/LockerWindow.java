package lms.application;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LockerWindow
{
	private Shell shell;
	private Display display;
	private Text priceText;
	
	private Combo buildingCombo;
	private Combo lockerCombo;
	
	private Button backButton;
	private Button rentButton;
	
	private Button agreeCheckButton;

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
		
		
		// ========== price text field =============
		priceText = new Text(shell, SWT.BORDER);
		priceText.setEditable(false);
		priceText.setBounds(32, 141, 114, 38);
		
		
		// ===== locker combo ( dropdown list ) =======
		lockerCombo = new Combo(shell, SWT.NONE);
		lockerCombo.setBounds(207, 39, 114, 40);
		
		
		// ======= building combo ( dropdown list ) =======
		buildingCombo = new Combo(shell, SWT.NONE);
		
		final String testSet[] = {"Bldg1", "Bldg2", "Bldg3"};
		buildingCombo.setItems(testSet);
		
		buildingCombo.setBounds(32, 39, 114, 40);
		buildingCombo.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e)
			{
				if(buildingCombo.getText().equals(testSet[0]))
				{
					String lockerTest[] = {"locker1", "locker2","locker3"};
					lockerCombo.setItems(lockerTest);
					lockerCombo.setEnabled(true);
				}
				else if(buildingCombo.getText().equals(testSet[1]))
				{
					String lockerTest[] = {"locker4", "locker5","locker6"};
					lockerCombo.setItems(lockerTest);
					lockerCombo.setEnabled(true);
				}
				else
				{
					lockerCombo.setEnabled(false);
					lockerCombo.setText("Nothing!!");
				}
			}
		});
		

		// ========= agree check button =========
		agreeCheckButton = new Button(shell, SWT.CHECK);
		agreeCheckButton.setBounds(207, 144, 114, 32);
		agreeCheckButton.setText("Agree");
		
		
		// ========= back button ===========
		backButton = new Button(shell, SWT.NONE);
		backButton.setBounds(7, 241, 139, 42);
		backButton.setText("Back");
		backButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// back button is selected
				shell.close();
			}
		});

		
		
		// ======== rent button ===========
		rentButton = new Button(shell, SWT.NONE);
		rentButton.setBounds(225, 241, 139, 42);
		rentButton.setText("Rent");
		

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
	
	public LockerWindow()
	{
		display = Display.getDefault();
		runWindow();
	}
}
