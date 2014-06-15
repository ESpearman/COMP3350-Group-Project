package lms.application;

import java.io.File;

import lms.business.logic.SpreadsheetImporter;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class ImportWindow
{
	private Display display;
	private Shell shell;
	
	private Text txtPath;
	
	private Button btnImportStudent;
	private Button btnImportLocker;
	private Button btnBack;
	private Label lblFilePath;
	private Button btnBrowse;
	
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
		shell.setText("Import");
		
		
		// ========= browse button ========
		btnBrowse = new Button(shell, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// file browser dialog open
				FileDialog dlgOpen = new FileDialog(shell, SWT.OPEN);
			    dlgOpen.setFilterNames(new String[] {"Excel Workbook(.xlsx, .xls)"});
			    dlgOpen.setFilterExtensions(new String[] {"*.xlsx"});
			    dlgOpen.setFilterPath("c:\\");
			    dlgOpen.open();
				txtPath.setText(dlgOpen.getFilterPath()+"\\"+dlgOpen.getFileName());
			}
		});
		btnBrowse.setBounds(299, 33, 75, 25);
		btnBrowse.setText("Browse");
		
		
		// ====== file (path) text field =======
		txtPath = new Text(shell, SWT.BORDER);
		txtPath.setEditable(false);
		txtPath.setBounds(75, 35, 218, 21);
		
		
		// ======= import student button =========
		btnImportStudent = new Button(shell, SWT.NONE);
		btnImportStudent.setBounds(263, 82, 111, 27);
		btnImportStudent.setText("Import Students");
		btnImportStudent.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// when import button is selected
				
				// only check if the given path is valid (exist?)
				
				String filePath = txtPath.getText();
				File f = new File(filePath);
				if(f.isFile())
				{
					SpreadsheetImporter.importStudents(filePath);
					
					MessageBox dlgSuccess = new MessageBox(shell, SWT.OK);
					dlgSuccess.setText("Completed");
					dlgSuccess.setMessage("Importing completed");
					dlgSuccess.open();
				}
				else
				{
					MessageBox dlgFail = new MessageBox(shell, SWT.OK);
					dlgFail.setText("Failed");
					dlgFail.setMessage("Importing failed : File does not exist");
					dlgFail.open();
				}

			}
		});
		
		// ======= import lockers button =========
		btnImportLocker = new Button(shell, SWT.NONE);
		btnImportLocker.setBounds(135, 82, 111, 27);
		btnImportLocker.setText("Import Lockers");
		btnImportLocker.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// when import button is selected
			
				// only check if the given path is valid
				
				String filePath = txtPath.getText();
				File f = new File(filePath);
				if(f.isFile())
				{
					SpreadsheetImporter.importLockers(filePath);
					
					MessageBox dlgSuccess = new MessageBox(shell, SWT.OK);
					dlgSuccess.setText("Completed");
					dlgSuccess.setMessage("Importing completed");
					dlgSuccess.open();
				}
				else
				{
					MessageBox dlgFail = new MessageBox(shell, SWT.OK);
					dlgFail.setText("Failed");
					dlgFail.setMessage("Importing failed : File does not exist");
					dlgFail.open();
				}

			}
		});

		
		//======== back button ==========
		btnBack = new Button(shell, SWT.NONE);
		btnBack.setBounds(10, 82, 111, 27);
		btnBack.setText("Back");
		btnBack.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// when back button is selected
				shell.close();
			}
		});
		
		
		lblFilePath = new Label(shell, SWT.NONE);
		lblFilePath.setAlignment(SWT.RIGHT);
		lblFilePath.setBounds(10, 38, 59, 21);
		lblFilePath.setText("File Path");


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
	
	public ImportWindow()
	{
		display = Display.getDefault();
		runWindow();
	}
}
