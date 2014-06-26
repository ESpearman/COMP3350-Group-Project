package lms.application;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;

public class AddLockerWindow
{
	
	private Shell shell;
	private Display display;
	

	private Button btnBack;
	private Button btnAdd;
	private Text txtInput;
	
	private Label lblLocker;
	private Combo drpBuilding;
	

	
	public void runWindow()
	{
		// ====== create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setSize(284, 155);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setText("Add locker");
		shell.setLocation (x, y);
		
		// ======= button back ======
		btnBack = new Button(shell, SWT.NONE);
		btnBack.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				shell.close();
			}
		});
		btnBack.setText("Back");
		btnBack.setBounds(10, 80, 111, 27);
		
		
		// ======== text 'input' ==========
		txtInput = new Text(shell, SWT.BORDER);
		txtInput.setBounds(98, 37, 160, 27);
		
		
		// ======= label 'building' ======
		lblLocker = new Label(shell, SWT.NONE);
		lblLocker.setAlignment(SWT.RIGHT);
		lblLocker.setBounds(10, 42, 82, 15);
		lblLocker.setText("Locker");
		

		// ========= combo(dropdown list) building =======
		drpBuilding = new Combo(shell, SWT.NONE);
		drpBuilding.setBounds(98, 8, 160, 23);
		drpBuilding.setText("Select a building");
		
		
		
		// ======== button add =========
		btnAdd = new Button(shell, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// add locker here with txtInput
				/*
				if(added?)
				{
					MessageBox dlgSuccess = new MessageBox(shell, SWT.OK);
					dlgSuccess.setText("Added");
					dlgSuccess.setMessage(txtInput.getText()+" Locker added");
					dlgSuccess.open();
				}
				else
				{
					MessageBox dlgFail = new MessageBox(shell, SWT.OK);
					dlgFail.setText("Failed");
					dlgFail.setMessage("Error : "+txtInput.getText() + " Locker not added!");
					dlgFail.open();
				}
				*/
			}
		});
		btnAdd.setText("Add");
		btnAdd.setBounds(147, 80, 111, 27);
		

		
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
	
	public AddLockerWindow()
	{
		display = Display.getDefault();
		runWindow();
	}
}
