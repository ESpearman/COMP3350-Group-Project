package lms.application;

import java.io.*;

import lms.businesslogic.EmailExport;
import lms.businesslogic.SpreadsheetExporter;

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
	
	// export options (radio btn)
	private Button btnEmail;
	private Button btnLocker;
	private Button btnRental;
	private Button btnStudents;
	private Button btnStats;
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shell.setData("ExportWindow");
		shell.setSize(288, 172);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		
		shell.setLocation (x, y);
		shell.setText("Export");
		
		
		// ======= radio button 'Rental' =========
		btnRental = new Button(shell, SWT.RADIO);
		btnRental.setText("Rental");
		btnRental.setBounds(23, 23, 62, 16);
		
		
		// ======= radio button 'Locker' =======
		btnLocker = new Button(shell, SWT.RADIO);
		btnLocker.setText("Locker");
		btnLocker.setBounds(104, 23, 62, 16);

		
		// ======= radio button 'email' ========
		btnEmail = new Button(shell, SWT.RADIO);
		btnEmail.setBounds(23, 61, 62, 16);
		btnEmail.setText("Email");
		
		
		// ====== radio button 'students' =======
		btnStudents = new Button(shell, SWT.RADIO);
		btnStudents.setBounds(104, 61, 90, 16);
		btnStudents.setText("Students");
		
		
		// ====== radio button 'stats' =======
		btnStats = new Button(shell, SWT.RADIO);
		btnStats.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnStats.setBounds(182, 23, 90, 16);
		btnStats.setText("Stats");
		
		
		
		// ========== export button ===========
		btnExport = new Button(shell, SWT.NONE);
		btnExport.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// probably we need to refactor this. getting really longer.
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
								EmailExport.writeToFile(emails, filePath);

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
				else if(btnLocker.getSelection())
				{
					FileDialog dlgSave = new FileDialog(shell, SWT.SAVE);
				    dlgSave.setFilterNames(new String[] {"Microsoft Excel File (*.xlsx)"});
				    dlgSave.setFilterExtensions(new String[] { "*.xlsx" });
				    dlgSave.setFilterPath("c:\\");
				    dlgSave.setFileName("Lockers.xlsx");	// default file name
				    dlgSave.open();
					
				    if(dlgSave.getFileName().compareTo("") != 0)
					{
						try 
						{
							String filePath = dlgSave.getFilterPath() + "\\" + dlgSave.getFileName();
							SpreadsheetExporter.writeLockerList(filePath);

							new PopupWindow("Completed","Exporting completed to: " + filePath);
						} 
						catch(IOException e) //couldn't write to file
						{
							new PopupWindow("Failed","Exporting failed : Could not write to file: " + dlgSave.getFileName());
						}
					}
					else //no file selected
					{
						new PopupWindow("Failed","Exporting failed : Cannot find source");
					}
				}
				else if(btnRental.getSelection())
				{
					FileDialog dlgSave = new FileDialog(shell, SWT.SAVE);
				    dlgSave.setFilterNames(new String[] {"Microsoft Excel File (*.xlsx)"});
				    dlgSave.setFilterExtensions(new String[] { "*.xlsx" });
				    dlgSave.setFilterPath("c:\\");
				    dlgSave.setFileName("Rentals.xlsx");	// default file name
				    dlgSave.open();
					
				    if(dlgSave.getFileName().compareTo("") != 0)
					{
						try 
						{
							String filePath = dlgSave.getFilterPath() + "\\" + dlgSave.getFileName();
							SpreadsheetExporter.writeRentalList(filePath);

							new PopupWindow("Completed","Exporting completed to: " + filePath);
						} 
						catch(IOException e) //couldn't write to file
						{
							new PopupWindow("Failed","Exporting failed : Could not write to file: " + dlgSave.getFileName());
						}
					}
					else //no file selected
					{
						new PopupWindow("Failed","Exporting failed : Cannot find source");
					};
				}
				else if(btnStats.getSelection())
				{
					// export stats here !
					new PopupWindow("Failed","Please implement this");
				}
				else if(btnStudents.getSelection())
				{
					FileDialog dlgSave = new FileDialog(shell, SWT.SAVE);
				    dlgSave.setFilterNames(new String[] {"Microsoft Excel File (*.xlsx)"});
				    dlgSave.setFilterExtensions(new String[] { "*.xlsx" });
				    dlgSave.setFilterPath("c:\\");
				    dlgSave.setFileName("Students.xlsx");	// default file name
				    dlgSave.open();
					
				    if(dlgSave.getFileName().compareTo("") != 0)
					{
						try 
						{
							String filePath = dlgSave.getFilterPath() + "\\" + dlgSave.getFileName();
							SpreadsheetExporter.writeStudentList(filePath);

							new PopupWindow("Completed","Exporting completed to: " + filePath);
						} 
						catch(IOException e) //couldn't write to file
						{
							new PopupWindow("Failed","Exporting failed : Could not write to file: " + dlgSave.getFileName());
						}
					}
					else //no file selected
					{
						new PopupWindow("Failed","Exporting failed : Cannot find source");
					};
				}
				
				else // if no option is selected
				{
					new PopupWindow("Failed","Exporting failed : Option not selected");
				}
							
			}
		});
		btnExport.setBounds(161, 105, 111, 27);
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
		btnBack.setBounds(10, 105, 111, 27);
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
