package lms.application;

import java.io.*;

import lms.businesslogic.EmailExport;
import lms.domainobjects.Building;
import lms.domainobjects.Locker;
import lms.domainobjects.Rental;
import lms.businesslogic.CurrentTermInfo;

import java.util.ArrayList;

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
	private Button btnTerm;
	private Button btnStats;
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setSize(298, 180);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		
		shell.setLocation (x, y);
		shell.setText("Export");
		
		
		// ======= raido button 'Term' =========
		btnTerm = new Button(shell, SWT.RADIO);
		btnTerm.setText("Term");
		btnTerm.setBounds(23, 23, 62, 16);
		
		
		// ======= radio button 'Locker' =======
		btnLocker = new Button(shell, SWT.RADIO);
		btnLocker.setText("Locker");
		btnLocker.setBounds(104, 23, 62, 16);
		
		
		
		
		// ======= radio button 'email' ========
		btnEmail = new Button(shell, SWT.RADIO);
		btnEmail.setBounds(23, 61, 62, 16);
		btnEmail.setText("Email");
		
		
		// ====== radio button 'stats' =======
		btnStats = new Button(shell, SWT.RADIO);
		btnStats.setBounds(104, 61, 90, 16);
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
				else if(btnLocker.getSelection())
				{
					new PopupWindow("Failed","Please implement this");
				}
				else if(btnTerm.getSelection())
				{
					new PopupWindow("Failed","Please implement this");
				}
				else if(btnStats.getSelection())
				{
					// export statistics here !
					double totalSales = 0; 
					//Get Sales
					ArrayList <Rental> stats = new ArrayList<Rental>();
					stats = Rental.getListByTerm(CurrentTermInfo.currentTerm.getId());
					// Get Buildings/Locker Statistics
					ArrayList<Building> bldgs = new ArrayList<Building>();
					bldgs = Building.getAll();
					// Get Locker Statistics
					ArrayList<Locker> lckr = new ArrayList<Locker>();
					
					
					for(int i = 0; i < stats.size(); i++)
					{
						totalSales += stats.get(i).getPricePaid();
					}
										
					if(!stats.isEmpty())
					{
						FileDialog dlgSave = new FileDialog(shell, SWT.SAVE);
					    dlgSave.setFilterNames(new String[] {"Text File (*.txt)"});
					    dlgSave.setFilterExtensions(new String[] { "*.txt" });
					    dlgSave.setFilterPath("c:\\");
					    dlgSave.setFileName("Total Sales.txt");	// default file name
					    dlgSave.open();
		
						if(dlgSave.getFileName().compareTo("") != 0)
						{
							try 
							{
								String filePath = dlgSave.getFilterPath() + "\\" + dlgSave.getFileName();
								File textFile = new File(filePath);
								BufferedWriter writer = new BufferedWriter(new FileWriter(textFile));
								writer.write("Total Revenue and Locker Sales For Current Term(s):\nTotal: " + totalSales + " Dollars");
								writer.write("\n\nBuilding Statistics: \n");
								for(int j = 0; j < bldgs.size(); j++ )
								{
									writer.write(bldgs.get(j).getName() + ": ");
									lckr = Locker.getFreeByBuildingAndTerm(bldgs.get(j).getId(), CurrentTermInfo.currentTerm.getId());
									writer.write(lckr.size() + " Free Locker(s)\n");
								}
								writer.close();

								new PopupWindow("Completed","Exporting completed to: " + filePath);
							} 
							catch(IOException e) //couldn't write to file
							{
								new PopupWindow("Failed","Exporting failed : Could not write to file: " + dlgSave.getFileName());
							}
						}
						

					}
					else
					{
						new PopupWindow("Failed","Please implement this");	
					}
					
				}
				else // if no option is selected
				{
					new PopupWindow("Failed","Exporting failed : Option not selected (Email)");
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
