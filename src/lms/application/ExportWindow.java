package lms.application;

import java.io.*;

import lms.businesslogic.EmailExport;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import acceptanceTests.EventLoop;
import acceptanceTests.Register;

public class ExportWindow
{
	
	private Display display;
	private Shell shell;
	
	private Button btnExport;
	private Button btnBack;
	private Button btnEmail;
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setSize(298, 121);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		
		shell.setLocation (x, y);
		shell.setText("Export");
		
		
		
		
		// ======= radio button 'email' ========
		btnEmail = new Button(shell, SWT.RADIO);
		btnEmail.setBounds(10, 10, 90, 16);
		btnEmail.setText("Email");
		
		
		
		// ========== export button ===========
		btnExport = new Button(shell, SWT.NONE);
		btnExport.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// export button is selected
				
				if(btnEmail.getSelection())
				{
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

								new PopupWindow("Completed","Exporting completed to: " + filePath);
							} 
							catch(IOException e) //couldn't write to file
							{
								new PopupWindow("Failed","Exporting failed : Could not write to file: " + dlgSave.getFileName());
							}
						}
						else //no file selected
						{
							new PopupWindow("Failed","Exporting failed : Cannot find email source");
						}
					}
					else //no students
					{
						new PopupWindow("Failed","Exporting failed : Could not find any student emails");
					}
				}
				else // if no option is selected
				{
					new PopupWindow("Failed","Exporting failed : Option not selected (Email)");
				}
							
			}
		});
		btnExport.setBounds(161, 46, 111, 27);
		btnExport.setText("Export");
		
		
		
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
		btnBack.setBounds(10, 46, 111, 27);
		btnBack.setText("Back");
		
		
		
		
		
		
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
	
	
	public ExportWindow()
	{
		Register.newWindow(this);
		display = Display.getDefault();
		runWindow();
	}
}
