package lms.application;

import java.io.*;

import lms.business.logic.EmailExport;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
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
		shell.setSize(384, 160);
		
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
				String emails = EmailExport.exportStudentEmails();
				if(!emails.equals("No students currently registered"))
				{
					FileDialog dlgSave = new FileDialog(shell, SWT.SAVE);
				    dlgSave.setFilterNames(new String[] {"Text File (*.txt)"});
				    dlgSave.setFilterExtensions(new String[] { "*.txt" });
				    dlgSave.setFilterPath("c:\\");
				    dlgSave.setFileName("Student Email.txt");	// default file name
				    dlgSave.open();
	
					if(dlgSave.getFileName().compareTo("") != 0)
					{
						try 
						{
							String filePath = dlgSave.getFilterPath() + "\\" + dlgSave.getFileName();
							File textFile = new File(filePath);
							BufferedWriter writer = new BufferedWriter(new FileWriter(textFile));
							writer.write(emails);
							writer.close();
								
							MessageBox dlgSuccess = new MessageBox(shell, SWT.OK);
							dlgSuccess.setText("Completed");
							dlgSuccess.setMessage("Exporting completed to: " + filePath);
							dlgSuccess.open();
						} 
						catch(IOException e) //couldn't write to file
						{
							MessageBox dlgFail = new MessageBox(shell, SWT.OK);
							dlgFail.setText("Failed");
							dlgFail.setMessage("Exporting failed : Could not write to file: " + dlgSave.getFileName());
							dlgFail.open();
						}
					}
					else //no file selected
					{
						MessageBox dlgFail = new MessageBox(shell, SWT.OK);
						dlgFail.setText("Failed");
						dlgFail.setMessage("Exporting failed : Cannot find email source");
						dlgFail.open();
					}
				}
				else //no students
				{
					MessageBox dlgFail = new MessageBox(shell, SWT.OK);
					dlgFail.setText("Failed");
					dlgFail.setMessage(emails);//"Exporting failed : Could not find any student emails");
					dlgFail.open();
				}
				
				
			}
		});
		btnExport.setBounds(247, 85, 111, 27);
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
		btnBack.setBounds(10, 85, 111, 27);
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
