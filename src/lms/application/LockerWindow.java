package lms.application;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

import acceptanceTests.EventLoop;
import acceptanceTests.Register;
import lms.businesslogic.CurrentTermInfo;
import lms.businesslogic.LockerPrice;
import lms.businesslogic.RentLocker;
import lms.domainobjects.Building;
import lms.domainobjects.Locker;
import lms.domainobjects.Rental;
import lms.domainobjects.Student;
import lms.domainobjects.Term;

import java.util.ArrayList;

public class LockerWindow
{
	private Shell shell;
	private Display display;
	
	private Combo drpBuilding;
	private Combo drpLocker;
	private Combo drpTerm1;
	private Combo drpTerm2;
	private Label lblTerm1;
	private Label lblTerm2;
	
	private ArrayList<Building> buildingsAL = Building.getAll();
	private String buildings[] = new String[buildingsAL.size()];
	private ArrayList<Locker> lockersAL;
	private String lockers[];
	private ArrayList<Term> termsAL = Term.getAll();
	private String terms1[] = new String[termsAL.size()-1];
	private String terms2[] = new String[termsAL.size()-2];
	private ArrayList<Term> termsAL1 = new ArrayList<Term>();
	private ArrayList<Term> termsAL2 = new ArrayList<Term>();
	
	private Term additionalTerm1;
	private Term additionalTerm2;
	private Locker selectedLocker;
	private Student potentialRenter;
	private double price;
	
	private Button btnBack;
	private Button btnRent;
	
	private Button chkAgreement;
	private Label lblPrice;
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setData("LockerWindow");
		shell.setSize(384, 267);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.setText("Select Locker");
		
		//Build up buildings to select from
		for(int i = 0; i < buildingsAL.size(); i++)
		{
			buildings[i] = buildingsAL.get(i).getName();
		}
		

		// ========= agree check button =========
		chkAgreement = new Button(shell, SWT.CHECK);
		chkAgreement.setAlignment(SWT.CENTER);
		chkAgreement.setBounds(306, 163, 52, 23);
		chkAgreement.setText("Agree");
		
		
		// ========= back button ===========
		btnBack = new Button(shell, SWT.NONE);
		btnBack.setBounds(10, 192, 111, 27);
		btnBack.setText("Back");
		btnBack.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// back button is selected
				shell.close();
			}
		});

		
		// ======== rent button ===========
		btnRent = new Button(shell, SWT.NONE);
		btnRent.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				if(drpLocker.getSelectionIndex() != -1 && drpBuilding.getSelectionIndex() != -1 && chkAgreement.getSelection())
				{
					ArrayList<Term> multipleTerms = new ArrayList<Term>();
					multipleTerms.add(CurrentTermInfo.currentTerm);
					if(additionalTerm1 != null)
					{
						multipleTerms.add(additionalTerm1);
					}
					if(additionalTerm2 != null)
					{
						multipleTerms.add(additionalTerm2);
					}
					
					String newRental = RentLocker.rent(potentialRenter, selectedLocker, multipleTerms, price);
					if(newRental.equals(""))
					{
						new PopupWindow("Completed","Rented");
						
						shell.close();
					}
					else
					{
						new PopupWindow("Error", newRental);
					}					
				}
				else
				{
					new PopupWindow("Error","Locker has not been selected or student has not agreed");
				}
			}
		});
		btnRent.setBounds(247, 192, 111, 27);
		btnRent.setText("Rent");
		
		
		
		lblPrice = new Label(shell, SWT.NONE);
		lblPrice.setBounds(10, 114, 345, 43);
		
		Label lblBuilding = new Label(shell, SWT.NONE);
		lblBuilding.setBounds(10, 10, 55, 15);
		lblBuilding.setText("Building");
		
		Label lblLocker = new Label(shell, SWT.NONE);
		lblLocker.setBounds(186, 10, 55, 15);
		lblLocker.setText("Locker");
		
		lblTerm1 = new Label(shell, SWT.NONE);
		lblTerm1.setText("Additional Term #1");
		lblTerm1.setBounds(10, 60, 155, 15);
		
		lblTerm2 = new Label(shell, SWT.NONE);
		lblTerm2.setText("Additional Term #2");
		lblTerm2.setBounds(186, 60, 155, 15);
		
		
		// ======= building combo ( dropdown list ) =======
		drpBuilding = new Combo(shell, SWT.NONE);
		drpBuilding.setItems(buildings);
		drpBuilding.setBounds(10, 31, 172, 23);
		drpBuilding.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				Building building = buildingsAL.get(drpBuilding.getSelectionIndex());
				lockersAL = Locker.getFreeByBuildingAndTerm(building.getId(), CurrentTermInfo.currentTerm.getId());
				lockers = new String[lockersAL.size()];
				
				//build up lockers to select from
				for(int i = 0; i < lockersAL.size(); i++)
				{
					lockers[i] = Integer.toString(lockersAL.get(i).getNumber());
				}
				drpLocker.setItems(lockers);
				drpLocker.setEnabled(true);
				
				System.out.println("ArrayList");
				for(int i = 0; i < lockersAL.size(); i++)
				{
					System.out.println(lockersAL.get(i).getNumber());
				}
				System.out.println("array");
				for(int i = 0; i < lockers.length; i++)
				{
					System.out.println(lockers[i]);
				}
			}
		});
		
		// ===== locker combo ( dropdown list ) =======
		drpLocker = new Combo(shell, SWT.NONE);
		drpLocker.setBounds(186, 31, 172, 23);
		drpLocker.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				selectedLocker = lockersAL.get(drpLocker.getSelectionIndex());
				System.out.println(selectedLocker.getNumber());
				price = LockerPrice.calculatePrice(potentialRenter, selectedLocker);
				lblPrice.setText(potentialRenter.getFirstName()+" "+potentialRenter.getLastName()+" will rent locker #"+
									drpLocker.getText() +" in "+drpBuilding.getText()+"\nPrice: " + price);
			}
		});
		
		int i = 0;
		for(Term t : termsAL)
		{
			if(!CurrentTermInfo.currentTerm.getName().equals(t.getName()))
			{
				terms1[i] = t.getName();
				termsAL1.add(t);
				i++;
			}
		}
		
		// ====== drop down term 1 =======
		drpTerm1 = new Combo(shell, SWT.NONE);
		drpTerm1.setItems(terms1);
		drpTerm1.setBounds(10, 81, 172, 23);
		drpTerm1.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				additionalTerm1 = termsAL1.get(drpTerm1.getSelectionIndex());
				drpTerm2.setEnabled(true);
				lblPrice.setText(potentialRenter.getFirstName()+" "+potentialRenter.getLastName()+" will rent locker #"+
									drpLocker.getText() +" in "+drpBuilding.getText()+"\nPrice: " + 2 * price);
				
				int j = 0;
				for(Term t : termsAL1)
				{
					if(!CurrentTermInfo.currentTerm.getName().equals(t.getName()) && !additionalTerm1.getName().equals(t.getName()))
					{
						terms2[j] = t.getName();
						termsAL2.add(t);
						j++;
					}
				}
				drpTerm2.setItems(terms2);
			}
		});		
				
				
		// ====== drop down term 2 ========
		drpTerm2 = new Combo(shell, SWT.NONE);
		drpTerm2.setBounds(186, 81, 172, 23);
		drpTerm2.setEnabled(false);
		drpTerm2.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				additionalTerm2 = termsAL2.get(drpTerm2.getSelectionIndex());
				lblPrice.setText(potentialRenter.getFirstName()+" "+potentialRenter.getLastName()+" will rent locker #"+
									drpLocker.getText() +" in "+drpBuilding.getText()+"\nPrice: " + 3 * price);
			}
		});

		
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
	
	public LockerWindow(Shell previousShell, Student newStudent)
	{
		Register.newWindow(this);
		display = Display.getDefault();
		potentialRenter = newStudent;
		runWindow();
	}
}