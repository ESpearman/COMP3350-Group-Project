package lms.application;

import java.util.ArrayList;

import lms.businesslogic.AddLocker;
import lms.businesslogic.CurrentTermInfo;
import lms.domainobjects.Building;
import lms.domainobjects.LockerSize;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;

import acceptanceTests.EventLoop;
import acceptanceTests.Register;


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
	
	private ArrayList<Building> allBuildings;
	private String[] buildings;
	private Label lblBuilding;
	
	private static final int TEXT_LIMIT = 50;
	
	public void runWindow()
	{
		// ====== create new window ( centre on monitor ) =====
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shell.setData("AddLockerWindow");
		shell.setSize(272, 197);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setText("Add locker");
		shell.setLocation (x, y);
		

		// ========= combo(dropdown list) building =======
		drpBuilding = new Combo(shell, SWT.NONE);
		drpBuilding.setBounds(95, 15, 157, 23);
		drpBuilding.setText("Select a building");

		
		// ======== text 'input' ==========
		txtInput = new Text(shell, SWT.BORDER);
		txtInput.setBounds(95, 44, 157, 27);
		txtInput.setTextLimit(TEXT_LIMIT);
		txtInput.addListener(SWT.Verify, new Listener()
		{
			// allow only digits
			public void handleEvent(Event e)
			{
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++)
				{
					if (!('0' <= chars[i] && chars[i] <= '9'))
					{
						e.doit = false;
						return;
					}
				}
			}
		});
		
		drpBuilding.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent arg0)
			{
				updateBuildings();
				drpBuilding.setText("Select a Building");
			}
		});
		
		
		// ======== radio button ( size ) ========
		btnFull = new Button(shell, SWT.RADIO);
		btnFull.setBounds(76, 90, 55, 16);
		btnFull.setText("Full");
		
		btnHalf = new Button(shell, SWT.RADIO);
		btnHalf.setBounds(162, 90, 55, 16);
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
								Object res = AddLocker.insert(CurrentTermInfo.currentTerm.getId(), txtInput.getText(), selectedBuilding.getId(), size);
								if(res!=null)
								{
									new PopupWindow("Added",txtInput.getText()+" Locker added");
								}
								else
								{
									new PopupWindow("Failed","There is already Locker #"+ txtInput.getText()+" in the term: "+CurrentTermInfo.currentTerm.getName());
								}
							}
							else
							{
								new PopupWindow("Failed"," Error : Need Locker Number !");
							}
						}
						else
						{
							new PopupWindow("Failed","Error : Need to select size of the locker !");
						}
					}
					else
					{
						new PopupWindow("Failed","Error : Need to select building !");
					}
				}
				else
				{
					new PopupWindow("Failed","Error : There is no building to assign to !");
				}
				
				

				
			}
		});
		btnAdd.setText("Add");
		btnAdd.setBounds(147, 132, 111, 27);
		
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
		btnBack.setBounds(10, 132, 111, 27);
		
		
		// ======= label 'locker' ======
		lblLocker = new Label(shell, SWT.NONE);
		lblLocker.setAlignment(SWT.RIGHT);
		lblLocker.setBounds(39, 50, 50, 16);
		lblLocker.setText("Locker");
		
		
		// =======  label 'building' ========
		lblBuilding = new Label(shell, SWT.NONE);
		lblBuilding.setAlignment(SWT.RIGHT);
		lblBuilding.setBounds(34, 19, 55, 15);
		lblBuilding.setText("Building");
		
		

		
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
	
	private void updateBuildings()
	{
		allBuildings = Building.getAll();
		buildings = new String[allBuildings.size()];
		for ( int i = 0 ; i < allBuildings.size() ; i ++)
		{
			buildings[i] = allBuildings.get(i).getName();
		}
		drpBuilding.setItems(buildings);
	}
	
	public AddLockerWindow()
	{
		Register.newWindow(this);
		display = Display.getDefault();
		runWindow();
	}
}
