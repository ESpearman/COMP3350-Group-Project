package lms.application;

import java.io.File;

import lms.businesslogic.SpreadsheetImporter;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

import acceptanceTests.EventLoop;
import acceptanceTests.Register;

public class ImportWindow
{
	private Display display;
	private Shell shell;
	
	private Text txtPath;
	private Button btnBack;
	private Button btnBrowse;
	
	private Button btnStudent;
	private Button btnLocker;
	
	private Label lblFilePath;
	private Button btnImport;
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shell.setData("ImportWindow");
		shell.setSize(372, 169);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.setText("Import File");
		
		
		// ========= browse button ========
		btnBrowse = new Button(shell, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				FileDialog dlgOpen = new FileDialog(shell, SWT.OPEN);
			    dlgOpen.setFilterNames(new String[] {"Excel Workbook (.xlsx)", "Excel 97-2004 (.xls)"});
			    dlgOpen.setFilterExtensions(new String[] {"*.xlsx","*.xls", "*.*"});
			    dlgOpen.setFilterPath("c:\\");
			    dlgOpen.open();
			    if (dlgOpen.getFileName().compareTo("") == 0)
			    {
			    	// file not selected on dialog
			    	txtPath.setText("");
			    }
			    else
			    {
			    	// file selected
			    	txtPath.setText(dlgOpen.getFilterPath() + "\\" + dlgOpen.getFileName());
			    }
				
			}
		});
		btnBrowse.setBounds(247, 10, 111, 27);
		btnBrowse.setText("Browse");
		
		
		// ==== radio button 'student' ========
		btnStudent = new Button(shell, SWT.RADIO);
		btnStudent.setBounds(10, 16, 90, 16);
		btnStudent.setText("Student");
		
		
		
		// ====== radio button 'locker' ========
		btnLocker = new Button(shell, SWT.RADIO);
		btnLocker.setBounds(121, 16, 90, 16);
		btnLocker.setText("Locker");
		
		
		// ====== file (path) text field =======
		txtPath = new Text(shell, SWT.BORDER);
		txtPath.setEditable(false);
		txtPath.setBounds(61, 43, 297, 21);
		
		
		// =========== import button =================
		btnImport = new Button(shell, SWT.NONE);
		btnImport.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				if(btnLocker.getSelection())
				{
					// locker importing
					
					String filePath = txtPath.getText();
					File f = new File(filePath);
					if(f.isFile())
					{
						SpreadsheetImporter.importLockers(filePath);
						
						new PopupWindow("Completed",SpreadsheetImporter.getStatus());
					}
					else
					{
						new PopupWindow("Failed",SpreadsheetImporter.getStatus());
					}
				}
				else if(btnStudent.getSelection())
				{
					// student importing
					
					String filePath = txtPath.getText();
					File f = new File(filePath);
					if(f.isFile())
					{
						SpreadsheetImporter.importStudents(filePath);
						
						new PopupWindow("Completed",SpreadsheetImporter.getStatus());
					}
					else
					{
						new PopupWindow("Failed", "No file found");
					}
				}
				else
				{
					// no radio button is selected (error)
					new PopupWindow("Failed","Importing failed : Not selected Import Option (Locker or Student)");
				}

			}
		});
		btnImport.setBounds(247, 105, 111, 27);
		btnImport.setText("Import");

		
		//======== back button ==========
		btnBack = new Button(shell, SWT.NONE);
		btnBack.setBounds(10, 105, 111, 27);
		btnBack.setText("Back");
		btnBack.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				shell.close();
			}
		});
		
		
		// ====== label 'filePath' ==========
		lblFilePath = new Label(shell, SWT.NONE);
		lblFilePath.setBounds(10, 46, 45, 16);
		lblFilePath.setText("File Path");


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
	
	public ImportWindow()
	{
		Register.newWindow(this);
		display = Display.getDefault();

		runWindow();
	}
}
