package lms.application;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class LockerWindow
{
	private Shell shell;
	private Display display;
	
	private Combo drpBuilding;
	private Combo drpLocker;
	
	private Button btnBack;
	private Button btnRent;
	
	private Button chkAgreement;
	private Label lblPrice;

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
		shell.setText("Select Locker");
		
		
		// ===== locker combo ( dropdown list ) =======
		drpLocker = new Combo(shell, SWT.NONE);
		drpLocker.setBounds(127, 10, 114, 40);
		
		
		// ======= building combo ( dropdown list ) =======
		drpBuilding = new Combo(shell, SWT.NONE);
		
		final String testSet[] = {"Bldg1", "Bldg2", "Bldg3"};
		drpBuilding.setItems(new String[] {});
		
		drpBuilding.setBounds(7, 10, 114, 40);
		drpBuilding.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				if(drpBuilding.getText().equals(testSet[0]))
				{
					String lockerTest[] = {"locker1", "locker2","locker3"};
					drpLocker.setItems(lockerTest);
					drpLocker.setEnabled(true);
				}
				else if(drpBuilding.getText().equals(testSet[1]))
				{
					String lockerTest[] = {"locker4", "locker5","locker6"};
					drpLocker.setItems(lockerTest);
					drpLocker.setEnabled(true);
				}
				else
				{
					drpLocker.setEnabled(false);
					drpLocker.setText("Nothing!!");
				}
			}
		});
		

		// ========= agree check button =========
		chkAgreement = new Button(shell, SWT.CHECK);
		chkAgreement.setBounds(7, 212, 114, 23);
		chkAgreement.setText("Agree");
		
		
		// ========= back button ===========
		btnBack = new Button(shell, SWT.NONE);
		btnBack.setBounds(7, 287, 111, 27);
		btnBack.setText("Back");
		btnBack.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// back button is selected
				shell.close();
			}
		});

		
		// ======== rent button ===========
		btnRent = new Button(shell, SWT.NONE);
		btnRent.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// rent button selected
				shell.close();
			}
		});
		btnRent.setBounds(263, 287, 111, 27);
		btnRent.setText("Rent");
		
		
		
		lblPrice = new Label(shell, SWT.NONE);
		lblPrice.setBounds(7, 39, 234, 23);
		lblPrice.setText("Price: ");
		

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
