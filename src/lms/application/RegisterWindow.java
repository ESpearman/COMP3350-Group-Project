package lms.application;

import java.util.UUID;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import lms.business.Student;
import lms.business.logic.RegisterStudent;
import lms.business.logic.SearchStudent;

public class RegisterWindow
{
	private Display display;
	private Shell shell;
	
	private Text txtStudentNumber;
	private Text txtFirstName;
	private Text txtLastName;
	private Text txtEmail;
	
	private Button btnSearch;
	private Button btnBack;
	private Button btnRegister;
	private Button btnScienceStudent;
	
	private Label searchSeparate;
	
	private Student searchedStudent;
	
	public void runWindow()
	{
		// ============ create new window ( centre on monitor ) =====
		shell = new Shell();
		shell.setSize(400, 362);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.setText("Register");
		
		
		// ====== search text =========
		txtStudentNumber = new Text(shell, SWT.BORDER);
		txtStudentNumber.setBounds(127, 4, 111, 27);
		txtStudentNumber.setTextLimit(7);
		
		txtStudentNumber.addListener(SWT.Verify, new Listener()
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

		
		// ===== first name text ========
		txtFirstName = new Text(shell, SWT.BORDER);
		txtFirstName.setBounds(127, 59, 111, 27);
		
		
		// ====== last name text ========
		txtLastName = new Text(shell, SWT.BORDER);
		txtLastName.setBounds(127, 92, 111, 27);
		
		
		// ====== email text =======
		txtEmail = new Text(shell, SWT.BORDER);
		txtEmail.setBounds(127, 125, 111, 27);
		
		
		// ====== separate bar ======
		searchSeparate = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		searchSeparate.setBounds(10, 37, 354, 16);
		
		
		// ======== back button =========
		btnBack = new Button(shell, SWT.NONE);
		btnBack.setBounds(10, 287, 122, 27);
		btnBack.setText("Back");
		btnBack.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// when back button is selected
				shell.close();
			}
		});

		
		// ====== register button =========
		btnRegister = new Button(shell, SWT.NONE);
		btnRegister.setBounds(263, 287, 111, 27);
		btnRegister.setText("Register");
		btnRegister.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				try
				{
					int studentNumber = Integer.parseInt(txtStudentNumber.getText());
					Student newStudent = RegisterStudent.upsertStudent(searchedStudent, txtFirstName.getText(), txtLastName.getText(),
							txtEmail.getText(), studentNumber, btnScienceStudent.getSelection(), UUID.randomUUID());
					new LockerWindow(newStudent);
				}
				catch(NumberFormatException e)
				{
					MessageBox dlgBadNumber = new MessageBox(shell, SWT.OK);
					dlgBadNumber.setMessage("Invalid Student Number");
					dlgBadNumber.setText("Error");
					dlgBadNumber.open();
				}
				
			}
		});

		
		// ====== search button ========
		btnSearch = new Button(shell, SWT.NONE);
		btnSearch.setBounds(253, 4, 111, 27);
		btnSearch.setText("Search");
		
		btnSearch.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				searchedStudent = SearchStudent.getByStudentNumber(txtStudentNumber.getText());
						
				//If the student exists in the database, auto-populate
				if(searchedStudent != null)
				{
					txtFirstName.setText(searchedStudent.getFirstName());
					txtLastName.setText(searchedStudent.getLastName());
					txtEmail.setText(searchedStudent.getEmail());
					btnScienceStudent.setSelection(searchedStudent.isScienceStudent());
				}
			} 
		});
		
		Label lblFirstName = new Label(shell, SWT.NONE);
		lblFirstName.setAlignment(SWT.RIGHT);
		lblFirstName.setBounds(10, 59, 111, 21);
		lblFirstName.setText("First Name");
		
		Label lblStudentNumber = new Label(shell, SWT.NONE);
		lblStudentNumber.setAlignment(SWT.RIGHT);
		lblStudentNumber.setBounds(10, 7, 111, 24);
		lblStudentNumber.setText("Student Number");
		
		Label lblLastName = new Label(shell, SWT.NONE);
		lblLastName.setText("Last Name");
		lblLastName.setAlignment(SWT.RIGHT);
		lblLastName.setBounds(10, 92, 111, 21);
		
		Label lblEmailAddress = new Label(shell, SWT.NONE);
		lblEmailAddress.setText("Email Address");
		lblEmailAddress.setAlignment(SWT.RIGHT);
		lblEmailAddress.setBounds(10, 125, 111, 21);
		
		
		
		btnScienceStudent = new Button(shell, SWT.CHECK);
		btnScienceStudent.setBounds(127, 158, 111, 16);
		btnScienceStudent.setText("Science Student");
		

		
		// ====== shell open, close =====
		shell.open();
		
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}

	public RegisterWindow()
	{
		display = Display.getDefault();
		runWindow();
	}
}
