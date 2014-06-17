package lms.application;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ExportWindow
{
	
	private Display display;
	private Shell shell;
	
	private Button btnExport;
	private Button btnBack;
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setSize(400, 160);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.setText("Export");
		
		
		
		// ========== export email button ===========
		btnExport = new Button(shell, SWT.NONE);
		btnExport.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// export email button is selected
				
				
				/*
				 * we need exporter
				FileDialog dlgSave = new FileDialog(shell, SWT.SAVE);
			    dlgSave.setFilterNames(new String[] {"Excel Workbook", "Excel 97-2004 (.xls)"});
			    dlgSave.setFilterExtensions(new String[] { "*.xlsx","*.xls", "*.*" });
			    dlgSave.setFilterPath("c:\\");
			    dlgSave.setFileName("Student Email.xlsx");	// default file name
			    dlgSave.open();
				*/
				
				/*
				if( exported ? )
				{
					MessageBox dlgSuccess = new MessageBox(shell, SWT.OK);
					dlgSuccess.setText("Completed");
					dlgSuccess.setMessage("Exporting completed");
					dlgSuccess.open();
				}
				else
				{
					MessageBox dlgFail = new MessageBox(shell, SWT.OK);
					dlgFail.setText("Failed");
					dlgFail.setMessage("Exporting failed : Cannot find email source");
					dlgFail.open();
				}
				*/
				
			}
		});
		btnExport.setBounds(265, 81, 109, 31);
		btnExport.setText("Export Email");
		
		
		
		// ======== back button ============
		btnBack = new Button(shell, SWT.NONE);
		btnBack.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// back button is selected
				shell.close();
			}
		});
		btnBack.setBounds(10, 87, 75, 25);
		btnBack.setText("Back");
		
		
		
		
		
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
	
	
	public ExportWindow()
	{
		display = Display.getDefault();
		runWindow();
	}
}
