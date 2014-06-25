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
	private Button btnBack;
	private Button btnBrowse;
	
	private Button btnLockers;
	private Button btnStudents;
	
	private Label lblFilePath;
	private Button btnImport;
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setSize(384, 181);
		
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
		
		
		// ====== file (path) text field =======
		txtPath = new Text(shell, SWT.BORDER);
		txtPath.setEditable(false);
		txtPath.setBounds(67, 43, 291, 21);

		
		//======== back button ==========
		btnBack = new Button(shell, SWT.NONE);
		btnBack.setBounds(10, 105, 111, 27);
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
		
		
		// ====== label 'filePath' ==========
		lblFilePath = new Label(shell, SWT.NONE);
		lblFilePath.setBounds(10, 46, 45, 16);
		lblFilePath.setText("File Path");
		
		
		// ========== locker radio button ============
		btnLockers = new Button(shell, SWT.RADIO);
		btnLockers.setBounds(10, 15, 72, 16);
		btnLockers.setText("Lockers");
		
		
		// =========== students radio button ========
		btnStudents = new Button(shell, SWT.RADIO);
		btnStudents.setBounds(88, 15, 90, 16);
		btnStudents.setText("Students");
		
		
		// =========== import button =================
		btnImport = new Button(shell, SWT.NONE);
		btnImport.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				if(btnLockers.getSelection())
				{
					// locker importing
					
					String filePath = txtPath.getText();
					File f = new File(filePath);
					if(f.isFile())
					{
						SpreadsheetImporter.importLockers(filePath);
						
						MessageBox dlgSuccess = new MessageBox(shell, SWT.OK);
						dlgSuccess.setText("Completed");
						dlgSuccess.setMessage("Lockers Importing completed");
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
				else if(btnStudents.getSelection())
				{
					// student importing
					
					String filePath = txtPath.getText();
					File f = new File(filePath);
					if(f.isFile())
					{
						SpreadsheetImporter.importStudents(filePath);
						
						MessageBox dlgSuccess = new MessageBox(shell, SWT.OK);
						dlgSuccess.setText("Completed");
						dlgSuccess.setMessage("Students Importing completed");
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
				else
				{
					// no radio button is selected (error)
					
					MessageBox dlgFail = new MessageBox(shell, SWT.OK);
					dlgFail.setText("Failed");
					dlgFail.setMessage("Importing failed : Choose importing option (Student or Lockers)");
					dlgFail.open();
					
				}
				
				Label lblNewLabel = new Label(shell, SWT.NONE);
				lblNewLabel.setBounds(77, 70, 55, 15);
				lblNewLabel.setText(SpreadsheetImporter.getStatus());
			}
		});
		btnImport.setBounds(247, 105, 111, 27);
		btnImport.setText("Import");
		
		



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
