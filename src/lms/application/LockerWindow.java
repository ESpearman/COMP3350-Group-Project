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
	private Label lblBuilding;
	private Label lblLocker;
	
	private ArrayList<Building> buildingsAL = Building.getAll();
	private String buildings[] = new String[buildingsAL.size()];
	private ArrayList<Locker> lockersAL;
	private String lockers[];
	private ArrayList<Term> termsAL;
	private String terms1[];
	private String terms2[];
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
	private Button btnClear;
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shell.setData("LockerWindow");
		shell.setSize(372, 274);
		
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
					lockers[i] = Integer.toString(lockersAL.get(i).getNumber()) + " - " + lockersAL.get(i).getSizeString();
				}
				drpLocker.setItems(lockers);
				drpLocker.setEnabled(true);
			}
		});
		
		// ===== locker combo ( dropdown list ) =======
		drpLocker = new Combo(shell, SWT.NONE);
		drpLocker.setBounds(186, 31, 172, 23);
		drpLocker.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				int multiplier = 1;
				
				selectedLocker = lockersAL.get(drpLocker.getSelectionIndex());
				if(drpLocker.getSelectionIndex()>-1)
				{
					drpTerm1.setEnabled(true);
				}
				price = LockerPrice.calculatePrice(potentialRenter, selectedLocker);
				
				if(drpTerm1.getSelectionIndex()>-1)
				{
					multiplier++;
					if(drpTerm2.getSelectionIndex()>-1)
					{
						multiplier++;
					}
				}
				updatePrice(potentialRenter,drpLocker.getText(),drpBuilding.getText(),multiplier);
			}
		});
		
		
		
		// ======= build additional term =======
		
		// ===== don't get negative array size =====
		termsAL = Term.getAll();
		if(termsAL.size()>=1)
		{
			terms1 = new String[termsAL.size()-1];
			terms2 = new String[termsAL.size()-1];
		}
		if(termsAL.size()>=2)
		{
			terms2 = new String[termsAL.size()-2];
		}
		
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
		drpTerm1.setEnabled(false);
		drpTerm1.setItems(terms1);
		drpTerm1.setBounds(10, 81, 172, 23);
		drpTerm1.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				additionalTerm1 = termsAL1.get(drpTerm1.getSelectionIndex());
				drpTerm2.setEnabled(true);
				updatePrice(potentialRenter,drpLocker.getText(),drpBuilding.getText(),2);
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
				updatePrice(potentialRenter,drpLocker.getText(),drpBuilding.getText(),3);
			}
		});
		

		// ========= agree check button =========
		chkAgreement = new Button(shell, SWT.CHECK);
		chkAgreement.setAlignment(SWT.CENTER);
		chkAgreement.setBounds(304, 180, 52, 23);
		chkAgreement.setText("Agree");
		
				
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
		btnRent.setBounds(247, 209, 111, 27);
		btnRent.setText("Rent");
		
		
		// ========= back button ===========
		btnBack = new Button(shell, SWT.NONE);
		btnBack.setBounds(10, 209, 111, 27);
		btnBack.setText("Back");
		btnBack.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
			    Shell[] shells = Display.getCurrent().getShells();
		        for(Shell shell : shells)
		        {
		            String data = (String) shell.getData();
		            if(data != null && data.equals("StudentWindow"+"Register"))
		            {
		                shell.setVisible(true);
		                shell.setFocus();
		            }
		        }
				shell.close();
			}
		});
		
		
		
		// ======== label for rental info =======
		lblPrice = new Label(shell, SWT.NONE);
		lblPrice.setAlignment(SWT.RIGHT);
		lblPrice.setBounds(10, 114, 345, 43);
		
		
		// ======== label 'Building' ======
		lblBuilding = new Label(shell, SWT.NONE);
		lblBuilding.setBounds(10, 10, 55, 15);
		lblBuilding.setText("Building");
		
		
		// ======== label 'Locker' ========
		lblLocker = new Label(shell, SWT.NONE);
		lblLocker.setBounds(186, 10, 55, 15);
		lblLocker.setText("Locker");
		
		
		// ======= label 'Term1' 'Term2' ======
		lblTerm1 = new Label(shell, SWT.NONE);
		lblTerm1.setText("Additional Term #1");
		lblTerm1.setBounds(10, 60, 155, 15);
		
		lblTerm2 = new Label(shell, SWT.NONE);
		lblTerm2.setText("Additional Term #2");
		lblTerm2.setBounds(186, 60, 155, 15);
		
		
		
		// ======== button clear ========
		btnClear = new Button(shell, SWT.NONE);
		btnClear.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				if(drpTerm2.getSelectionIndex()>-1)
				{
					drpTerm2.deselectAll();
				}
				if(drpTerm1.getSelectionIndex()>-1)
				{
					drpTerm1.deselectAll();
					drpTerm1.setEnabled(false);
					drpTerm2.setEnabled(false);
				}
				if(drpLocker.getSelectionIndex()>-1)
				{
					drpLocker.deselectAll();
				}
				if(drpBuilding.getSelectionIndex()>-1)
				{
					drpBuilding.deselectAll();
				}
				lblPrice.setText("");
				chkAgreement.setSelection(false);
			}
		});
		btnClear.setText("Clear");
		btnClear.setBounds(10, 178, 111, 27);

		
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
	
	private void updatePrice(Student potientialRenter, String locker, String building, int multiplier)
	{
		lblPrice.setText(potentialRenter.getFirstName()+" "+potentialRenter.getLastName()+" will rent locker #"+
				locker +" in "+building + "\nPrice: " + multiplier * price);
	}
	
	public LockerWindow(Shell previousShell, Student newStudent)
	{
		Register.newWindow(this);
		display = Display.getDefault();
		potentialRenter = newStudent;
		runWindow();
	}
}
