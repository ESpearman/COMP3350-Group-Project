package lms.application;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import acceptanceTests.EventLoop;
import acceptanceTests.Register;

public class PopupWindow
{
	private Shell shell;
	private Display display;
	
	private Label lblMessage;
	private Button btnOk;
	
	private String message;
	private String title;
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shell.setSize(298, 173);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.setText(title);
		
		
		// =========== button ok ============
		btnOk = new Button(shell, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				shell.close();
			}
		});
		btnOk.setText("OK");
		btnOk.setBounds(90, 108, 111, 27);
		
		
		// ========== label message =========
		lblMessage = new Label(shell, SWT.NONE);
		lblMessage.setAlignment(SWT.CENTER);
		lblMessage.setBounds(17, 18, 254, 84);
		lblMessage.setText(message);
		
		
		
		
		//======= shell open, close ======
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
	
	public PopupWindow(String title, String message)
	{
		Register.newWindow(this);
		this.title = title;
		this.message = message;
		display = Display.getDefault();
		runWindow();
	}
}
