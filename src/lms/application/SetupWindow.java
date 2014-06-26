package lms.application;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SetupWindow
{

	private Display display;
	private Shell shell;
	
	private Button btnAddTerm;
	private Button btnAddLocker;
	private Button btnAddStudent;
	private Button btnEditStudent;
	private Button btnBack;
	
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setSize(269, 168);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.setText("Setup");
		
		
		
		// ==== button add term =======
		btnAddTerm = new Button(shell, SWT.NONE);
		btnAddTerm.setText("Add Term");
		btnAddTerm.setBounds(10, 10, 111, 27);
		btnAddTerm.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				new ImportWindow(btnAddTerm.getText(), "term");
			}
		});
		
		
		
		
		// ========= button add locker ==========
		btnAddLocker = new Button(shell, SWT.NONE);
		btnAddLocker.setText("Add Locker");
		btnAddLocker.setBounds(132, 10, 111, 27);
		btnAddLocker.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				new ImportWindow(btnAddLocker.getText(), "locker");
			}
		});
		
		
		
		// ========= button add student ============
		btnAddStudent = new Button(shell, SWT.NONE);
		btnAddStudent.setText("Add Student");
		btnAddStudent.setBounds(10, 43, 111, 27);
		btnAddStudent.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				new ImportWindow(btnAddStudent.getText(), "student");
			}
		});
		
		
		
		
		// ========= button edit student ===========
		btnEditStudent = new Button(shell, SWT.NONE);
		btnEditStudent.setText("Edit Student");
		btnEditStudent.setBounds(132, 43, 111, 27);
		btnEditStudent.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				new StudentWindow("Edit");
			}
		});
		
		
		
		
		// ========= button back ==========
		btnBack = new Button(shell, SWT.NONE);
		btnBack.setText("Back");
		btnBack.setBounds(10, 93, 111, 27);
		btnBack.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				shell.close();
			}
		});
		
		
		
		
		//======= shell open, close ======
		shell.open();
		
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		
		
	}
	
	public SetupWindow()
	{
		display = Display.getDefault();
		runWindow();
	}
}
