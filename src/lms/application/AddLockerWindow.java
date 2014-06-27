package lms.application;

import java.util.ArrayList;

import lms.businesslogic.AddLocker;
import lms.businesslogic.CurrentTermInfo;
import lms.domainobjects.Building;
import lms.domainobjects.LockerSize;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;


public class AddLockerWindow
{
	
	private Shell shell;
	private Display display;
	

	private Button btnBack;
	private Button btnAdd;
	private Text txtInput;
	
	private Button btnFull;
	private Button btnHalf;
	
	private Label lblLocker;
	private Combo drpBuilding;
	
	LockerSize size;
	
	private ArrayList<Building> allBuildings = Building.getAll();
	private String[] buildings = new String[allBuildings.size()];
	private Label lblBuilding;
	
	public void runWindow()
	{
		// ====== create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setSize(284, 197);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setText("Add locker");
		shell.setLocation (x, y);
		

		// ========= combo(dropdown list) building =======
		drpBuilding = new Combo(shell, SWT.NONE);
		drpBuilding.setBounds(101, 15, 157, 23);
		drpBuilding.setText("Select a building");

		
		// ======== text 'input' ==========
		txtInput = new Text(shell, SWT.BORDER);
		txtInput.setBounds(101, 44, 157, 27);
		
		
		// build building list here
		for ( int i = 0 ; i < allBuildings.size() ; i ++)
		{
			buildings[i] = allBuildings.get(i).getName();
		}
		drpBuilding.setItems(buildings);
		
		
		
		
		// ======== radio button ( size ) ========
		btnFull = new Button(shell, SWT.RADIO);
		btnFull.setBounds(82, 90, 55, 16);
		btnFull.setText("Full");
		
		btnHalf = new Button(shell, SWT.RADIO);
		btnHalf.setBounds(168, 90, 55, 16);
		btnHalf.setText("Half");
		
		
		
		// ======== button add =========
		btnAdd = new Button(shell, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{

				if(allBuildings.size()>0)
				{
					if(drpBuilding.getSelectionIndex()>-1)
					{
						Building selectedBuilding = allBuildings.get(drpBuilding.getSelectionIndex());
						
						if(btnFull.getSelection()||btnHalf.getSelection())
						{
							if(btnFull.getSelection())
							{
								size = LockerSize.FULL;
							}
							else if(btnHalf.getSelection())
							{
								size = LockerSize.HALF;
							}
							
							if(txtInput.getText() != "")
							{
								AddLocker.insert(CurrentTermInfo.currentTerm.getId(), txtInput.getText(), selectedBuilding.getId(), size);
								MessageBox dlgSuccess = new MessageBox(shell, SWT.OK);
								dlgSuccess.setText("Added");
								dlgSuccess.setMessage(txtInput.getText()+" Locker added");
								dlgSuccess.open();
							}
							else
							{
								MessageBox dlgFail = new MessageBox(shell, SWT.OK);
								dlgFail.setText("Failed");
								dlgFail.setMessage("Error : Need Locker Number !");
								dlgFail.open();
							}
						}
						else
						{
							MessageBox dlgFail = new MessageBox(shell, SWT.OK);
							dlgFail.setText("Failed");
							dlgFail.setMessage("Error : Need to select size of the locker !");
							dlgFail.open();
						}
					}
					else
					{
						MessageBox dlgFail = new MessageBox(shell, SWT.OK);
						dlgFail.setText("Failed");
						dlgFail.setMessage("Error : Need to select building !");
						dlgFail.open();
					}
					
				}
				else
				{
					MessageBox dlgFail = new MessageBox(shell, SWT.OK);
					dlgFail.setText("Failed");
					dlgFail.setMessage("Error : There is no building to assign to !");
					dlgFail.open();
				}
				
				

				
			}
		});
		btnAdd.setText("Add");
		btnAdd.setBounds(147, 122, 111, 27);
		
		// ======= button back ======
		btnBack = new Button(shell, SWT.NONE);
		btnBack.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				shell.close();
			}
		});
		btnBack.setText("Back");
		btnBack.setBounds(10, 122, 111, 27);
		
		
		// ======= label 'locker' ======
		lblLocker = new Label(shell, SWT.NONE);
		lblLocker.setAlignment(SWT.RIGHT);
		lblLocker.setBounds(45, 50, 50, 16);
		lblLocker.setText("Locker");
		
		
		// =======  label 'building' ========
		lblBuilding = new Label(shell, SWT.NONE);
		lblBuilding.setAlignment(SWT.RIGHT);
		lblBuilding.setBounds(40, 19, 55, 15);
		lblBuilding.setText("Building");
		
		

		
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
	
	public AddLockerWindow()
	{
		display = Display.getDefault();
		runWindow();
	}
}
