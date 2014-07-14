package lms.application;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import acceptanceTests.EventLoop;
import acceptanceTests.Register;
import lms.businesslogic.CurrentTermInfo;
import lms.businesslogic.RegisterStudent;
import lms.domainobjects.Student;


public class StudentWindow
{
	private Display display;
	private Shell shell;
	
	// "Edit" or "Register"
	private String context;
	
	private static final int TEXT_LIMIT = 50;
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
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shell.setData("StudentWindow");
		shell.setSize(374, 253);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.setText(context);
		
		
		// ====== search text =========
		txtStudentNumber = new Text(shell, SWT.BORDER);
		txtStudentNumber.setBounds(115, 13, 126, 24);
		txtStudentNumber.setTextLimit(9); // Allows up to 9 digits (just to be safe)
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
		
				
		// ====== search button ========
		btnSearch = new Button(shell, SWT.NONE);
		btnSearch.setBounds(247, 11, 111, 27);
		btnSearch.setText("Search");
		
		btnSearch.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				if(txtStudentNumber.getText()!="")
				{
					searchedStudent = Student.getByStudentNumber(Integer.parseInt(txtStudentNumber.getText()), CurrentTermInfo.currentTerm.getId());
					
					//If the student exists in the database, auto-populate
					if(searchedStudent != null)
					{
						txtFirstName.setText(searchedStudent.getFirstName());
						txtLastName.setText(searchedStudent.getLastName());
						txtEmail.setText(searchedStudent.getEmail());
						btnScienceStudent.setSelection(searchedStudent.isScienceStudent());
					}
					else //Empty all the text-fields
					{
						txtFirstName.setText("");
						txtLastName.setText("");
						txtEmail.setText("");
						btnScienceStudent.setSelection(false);
					}
				}
				else
				{
					new PopupWindow("Error","Enter student number (0~9 digit only)");
				}
			} 
		});

		
		// ===== first name text ========
		txtFirstName = new Text(shell, SWT.BORDER);
		txtFirstName.setBounds(115, 59, 126, 24);
		txtFirstName.setTextLimit(TEXT_LIMIT);
		
		// ====== last name text ========
		txtLastName = new Text(shell, SWT.BORDER);
		txtLastName.setBounds(115, 89, 126, 24);
		txtLastName.setTextLimit(TEXT_LIMIT);
		
		// ====== email text =======
		txtEmail = new Text(shell, SWT.BORDER);
		txtEmail.setBounds(115, 119, 126, 24);
		txtEmail.setTextLimit(TEXT_LIMIT);
		
		
		// ====== check button science student =======
		btnScienceStudent = new Button(shell, SWT.CHECK);
		btnScienceStudent.setBounds(130, 151, 111, 16);
		btnScienceStudent.setText("Science Student");
		
		
		// ====== register/update button =========
		btnRegister = new Button(shell, SWT.NONE);
		btnRegister.setBounds(247, 188, 111, 27);
		btnRegister.setText(context);
		btnRegister.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				try // Only allow them to register if the fields are filled in properly
				{
					int studentNumber = Integer.parseInt(txtStudentNumber.getText());
					if(studentNumber >= 1 && !txtEmail.getText().equals("") && !txtFirstName.getText().equals("") && !txtLastName.getText().equals(""))
					{
						Student newStudent = RegisterStudent.upsertStudent(searchedStudent, txtFirstName.getText(), txtLastName.getText(),
								txtEmail.getText(), studentNumber, btnScienceStudent.getSelection(), CurrentTermInfo.currentTerm.getId());
						shell.close();
						if(context.equals("Register"))
						{
							if(!alrOpened("LockerWindow"))
							{
								new LockerWindow(shell, newStudent);
							}
						}
						else if(context.equals("Edit"))
						{
							new PopupWindow("Updated","Student information has been updated");
						}
					}
					else
					{
						new PopupWindow("Error","The student fields were not filled in properly");
					}
				}
				catch(NumberFormatException e)
				{
					new PopupWindow("Error","Invalid Student Number");
				}
			}
		});
		
		
		// ======== back button =========
		btnBack = new Button(shell, SWT.NONE);
		btnBack.setBounds(10, 188, 111, 27);
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
		
		
		// ====== separate bar ======
		searchSeparate = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		searchSeparate.setBounds(10, 40, 348, 16);
		
		
		// ======== label for student info ========
		Label lblFirstName = new Label(shell, SWT.NONE);
		lblFirstName.setAlignment(SWT.RIGHT);
		lblFirstName.setBounds(10, 62, 99, 21);
		lblFirstName.setText("First Name");
		
		Label lblStudentNumber = new Label(shell, SWT.NONE);
		lblStudentNumber.setAlignment(SWT.RIGHT);
		lblStudentNumber.setBounds(10, 16, 99, 16);
		lblStudentNumber.setText("Student Number");
		
		Label lblLastName = new Label(shell, SWT.NONE);
		lblLastName.setText("Last Name");
		lblLastName.setAlignment(SWT.RIGHT);
		lblLastName.setBounds(10, 92, 99, 21);
		
		Label lblEmailAddress = new Label(shell, SWT.NONE);
		lblEmailAddress.setText("Email Address");
		lblEmailAddress.setAlignment(SWT.RIGHT);
		lblEmailAddress.setBounds(10, 122, 99, 21);
		

		// ====== shell open, close =====
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
	
	private static boolean alrOpened(String name)
	{
		boolean res = false;
		
	    Shell[] shells = Display.getCurrent().getShells();
        for(Shell shell : shells)
        {
            String data = (String) shell.getData();
            if(data != null && data.equals(name))
            {
                shell.setFocus();
                res = true;
            }
        }
		return res;
	}

	public StudentWindow(String context)
	{
		Register.newWindow(this);
		display = Display.getDefault();
		this.context = context;
		runWindow();
	}
}
