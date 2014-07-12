package lms.application;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

import lms.businesslogic.AddTerm;

import acceptanceTests.EventLoop;
import acceptanceTests.Register;

public class AddTermWindow {
	
	private Shell shell;
	private Display display;
	
	private Button btnBack;
	private Button btnAdd;
	private Text txtInput;
	private Label lblTerm;
	
	public void runWindow()
	{
		// ====== create new window ( centre on monitor ) =====
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shell.setData("AddTermWindow");
		shell.setSize(274, 122);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setText("Add term");
		shell.setLocation (x, y);
		
		txtInput = new Text(shell, SWT.BORDER);
		txtInput.setBounds(98, 17, 160, 27);
		
		
		
		// ========== button add ============
		btnAdd = new Button(shell, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{				
				if(!txtInput.getText().equals(""))
				{
					// add term here
					AddTerm.addTerm(txtInput.getText());
					new PopupWindow("Added", txtInput.getText() + "Term added");
				}
				else
				{

					new PopupWindow("Failed", "Error : Need Term name !");
				}
			}
		});
		btnAdd.setText("Add");
		btnAdd.setBounds(147, 57, 111, 27);
		
		
		
		
		
		// ========= button back ==========
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
		btnBack.setBounds(10, 57, 111, 27);
		
		
		
		
		// ======== label 'Term' =======
		lblTerm = new Label(shell, SWT.NONE);
		lblTerm.setText("Term");
		lblTerm.setAlignment(SWT.RIGHT);
		lblTerm.setBounds(25, 22, 67, 15);
		
		
		
		
		
		// ======shell open, close ========
		shell.open();
		
		if(EventLoop.isEnabled())
		{
			while (!shell.isDisposed())
			{
				if (!display.readAndDispatch())
				{
					display.sleep();
				}
			}
		}
		
	}

	public AddTermWindow()
	{
		Register.newWindow(this);
		display = Display.getDefault();
		runWindow();
	}
}
