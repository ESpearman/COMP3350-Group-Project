package lms.application;

import lms.businesslogic.AddBuilding;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

import acceptanceTests.EventLoop;
import acceptanceTests.Register;

public class AddBuildingWindow
{

	private Display display;
	private Shell shell;
	
	private Button btnBack;
	private Button btnAdd;
	private Text txtInput;
	
	private Label lblBuilding;
	
	private static final int TEXT_LIMIT = 50;
	
	public void runWindow()
	{
		// ====== create new window ( centre on monitor ) =====
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shell.setData("AddBuildingWindow");
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
		txtInput.setBounds(98, 18, 160, 27);
		txtInput.setTextLimit(TEXT_LIMIT);
		
		
		// ======== button add =========
		btnAdd = new Button(shell, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{

				if(txtInput.getText() != "")
				{
					Object res = AddBuilding.insert(txtInput.getText());
					if(res!=null)
					{
						new PopupWindow("Added",txtInput.getText()+ " Building added");
					}
					else
					{
						new PopupWindow("Failed","There is already "+txtInput.getText()+ " Building");
					}
				}
				else
				{

					new PopupWindow("Failed","Error : Need building name !");
				}
			}
		});
		btnAdd.setText("Add");
		btnAdd.setBounds(157, 70, 111, 27);
		
		
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
		btnBack.setBounds(10, 70, 111, 27);
		
		
		// ======= label 'building' ======
		lblBuilding = new Label(shell, SWT.NONE);
		lblBuilding.setAlignment(SWT.RIGHT);
		lblBuilding.setBounds(10, 22, 82, 15);
		lblBuilding.setText("Building");
		
		
		
	
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
	
	public AddBuildingWindow()
	{
		Register.newWindow(this);
		display = Display.getDefault();
		runWindow();
	}
}
