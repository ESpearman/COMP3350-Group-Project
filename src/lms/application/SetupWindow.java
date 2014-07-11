package lms.application;

import lms.businesslogic.CurrentTermInfo;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import acceptanceTests.EventLoop;
import acceptanceTests.Register;

public class SetupWindow
{

	private Display display;
	private Shell shell;
	
	private Button btnAddTerm;
	private Button btnAddLocker;
	private Button btnAddBuilding;
	private Button btnEditStudent;
	private Button btnBack;
	private Button btnImport;
	
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shell.setData("SetupWindow");
		shell.setSize(252, 212);
		
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
		btnAddTerm.setBounds(10, 56, 111, 27);
		btnAddTerm.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				if(!alrOpened("AddTermWindow"))
				{
					new AddTermWindow();
				}
			}
		});
		
		
		// ========= button add locker ==========
		btnAddLocker = new Button(shell, SWT.NONE);
		btnAddLocker.setText("Add Locker");
		btnAddLocker.setBounds(10, 89, 111, 27);
		btnAddLocker.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				if(!alrOpened("AddLockerWindow"))
				{
					new AddLockerWindow();
				}
			}
		});
		
		
		// ========= button add building ============
		btnAddBuilding = new Button(shell, SWT.NONE);
		btnAddBuilding.setText("Add Building");
		btnAddBuilding.setBounds(127, 56, 111, 27);
		btnAddBuilding.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				if(!alrOpened("AddBuildingWindow"))
				{
					new AddBuildingWindow();
				}
			}
		});
		
		
		
		
		// ========= button edit student ===========
		btnEditStudent = new Button(shell, SWT.NONE);
		btnEditStudent.setText("Edit Student");
		btnEditStudent.setBounds(127, 10, 111, 27);
		btnEditStudent.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				if(!alrOpened("StudentWindow"))
				{
					new StudentWindow("Edit");
				}
			}
		});
		
		
		
		
		// ========= button back ==========
		btnBack = new Button(shell, SWT.NONE);
		btnBack.setText("Back");
		btnBack.setBounds(10, 147, 111, 27);
		btnBack.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				shell.close();
			}
		});
		
		
		
		// ========== button import =========
		btnImport = new Button(shell, SWT.NONE);
		btnImport.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				if(!alrOpened("ImportWindow"))
				{
					new ImportWindow();
				}
			}
		});
		btnImport.setText("Import");
		btnImport.setBounds(10, 10, 111, 27);
		
		
		// Only allow to add new term if there is no current term
		if(CurrentTermInfo.currentTerm == null)
		{
			btnImport.setEnabled(false);
			btnEditStudent.setEnabled(false);
			btnAddLocker.setEnabled(false);
		}
		
		
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
	private static boolean alrOpened(String name)
	{
		boolean res = false;
		
	    Shell[] shells = Display.getCurrent().getShells();
        for(Shell shell : shells)
        {
            String data = (String) shell.getData();
            if(data != null && data.equals(name))
            {
                shell.setFocus();
                res = true;
            }
        }
		return res;
	}
	public SetupWindow()
	{
		Register.newWindow(this);
		display = Display.getDefault();
		runWindow();
	}
}
