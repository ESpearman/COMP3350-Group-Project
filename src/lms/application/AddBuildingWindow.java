package lms.application;

import lms.businesslogic.AddBuilding;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

public class AddBuildingWindow
{

	private Display display;
	private Shell shell;
	
	private Button btnBack;
	private Button btnAdd;
	private Text txtInput;
	
	private Label lblBuilding;
	
	
	public void runWindow()
	{
		// ====== create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setSize(284, 135);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setText("Add building");
		shell.setLocation (x, y);
		
		
		// ======== text 'input' ==========
		txtInput = new Text(shell, SWT.BORDER);
		txtInput.setBounds(98, 17, 160, 27);
		
		
		
		// ======== button add =========
		btnAdd = new Button(shell, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{

				if(txtInput.getText() != "")
				{
					AddBuilding.insert(txtInput.getText());
					MessageBox dlgSuccess = new MessageBox(shell, SWT.OK);
					dlgSuccess.setText("Added");
					dlgSuccess.setMessage(txtInput.getText()+" Building added");
					dlgSuccess.open();
				}
				else
				{
					MessageBox dlgFail = new MessageBox(shell, SWT.OK);
					dlgFail.setText("Failed");
					dlgFail.setMessage("Error : Need building name !");
					dlgFail.open();
				}
			}
		});
		btnAdd.setText("Add");
		btnAdd.setBounds(147, 60, 111, 27);
		
		
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
		btnBack.setBounds(10, 60, 111, 27);
		
		
		// ======= label 'building' ======
		lblBuilding = new Label(shell, SWT.NONE);
		lblBuilding.setAlignment(SWT.RIGHT);
		lblBuilding.setBounds(10, 20, 82, 15);
		lblBuilding.setText("Building");
		
		
		
	
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
	
	
	public AddBuildingWindow()
	{
		display = Display.getDefault();
		runWindow();
	}
}
