package lms.application;

import java.io.File;

import lms.business.logic.SpreadsheetImporter;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
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
	
	private Button btnImport;
	private Button btnBack;
	private Label lblFilePath;
	
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
		shell.setText("Import Students");
		
		
		// ====== file (path) text field =======
		txtPath = new Text(shell, SWT.BORDER);
		txtPath.setBounds(104, 10, 270, 21);
		
		
		// ======= import button =========
		btnImport = new Button(shell, SWT.NONE);
		btnImport.setBounds(263, 82, 111, 27);
		btnImport.setText("Import");
		btnImport.addSelectionListener(new SelectionAdapter()
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

		
		//======== back button ==========
		btnBack = new Button(shell, SWT.NONE);
		btnBack.setBounds(10, 82, 111, 27);
		btnBack.setText("Back");
		
		lblFilePath = new Label(shell, SWT.NONE);
		lblFilePath.setAlignment(SWT.RIGHT);
		lblFilePath.setBounds(10, 10, 88, 21);
		lblFilePath.setText("File Path");
		btnBack.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// when back button is selected
				shell.close();
			}
		});


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
