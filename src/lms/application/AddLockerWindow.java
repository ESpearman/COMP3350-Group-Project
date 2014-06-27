package lms.application;

import java.util.ArrayList;

import lms.business.Building;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
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
				// add locker here with txtInput
				
				// Building selectedbuilding = allBuildings.get(drpBuilding.getSelectionIndex());
				
				/*
				if(btnFull.getSelection())
				{
					
				}
				else if(btnHalf.getSelection())
				{
					
				}
				
				if(added?)
				{
					MessageBox dlgSuccess = new MessageBox(shell, SWT.OK);
					dlgSuccess.setText("Added");
					dlgSuccess.setMessage(txtInput.getText()+" Locker added");
					dlgSuccess.open();
				}
				else
				{
					MessageBox dlgFail = new MessageBox(shell, SWT.OK);
					dlgFail.setText("Failed");
					dlgFail.setMessage("Error : "+txtInput.getText() + " Locker not added!");
					dlgFail.open();
				}
				*/
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
