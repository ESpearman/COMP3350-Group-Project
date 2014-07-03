package lms.application;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Link;

public class AboutUsWindow
{
	private Shell shell;
	private Display display;
	
	private Button btnBack;
	private Link lnkGit;
	
	public void runWindow()
	{
		// ====== create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setSize(284, 259);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setText("About us");
		shell.setLocation (x, y);
		
		
		
		// ========== buttn back ========
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
		btnBack.setBounds(10, 184, 111, 27);
		
		
		
		// ========== link to the git hub ==========
		lnkGit = new Link(shell, SWT.NONE);
		lnkGit.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				Program.launch("https://github.com/ESpearman/COMP3350-Group-Project");
			}
		});
		lnkGit.setBounds(93, 32, 77, 15);
		lnkGit.setText("<a>Go to GitHub</a>");

		
		
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
	
	public AboutUsWindow()
	{
		display = Display.getDefault();
		runWindow();
	}
}
