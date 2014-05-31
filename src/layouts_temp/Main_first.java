package layouts_temp;


import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.SWT;


public class Main_first {
	private static Text text_LName;
	private static Text text_FName;
	private static Text text_StuNum;
	private static Text text_Email;
	private static Text text;

	public static void main(String[] args)
	{
		
		Display display = Display.getDefault();
		final Shell Des1 = new Shell();
		Des1.setSize(800, 700);
		Des1.setText("OneWindow");
		
		TabFolder tabFolder = new TabFolder(Des1, SWT.NONE);
		tabFolder.setBounds(0, 0, 774, 531);
		
		TabItem tbtmStudent = new TabItem(tabFolder, SWT.NONE);
		tbtmStudent.setToolTipText("Assigning studnet to a locker");
		tbtmStudent.setText("Student");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmStudent.setControl(composite);
		
		Label lblLastName = new Label(composite, SWT.NONE);
		lblLastName.setBounds(10, 10, 123, 32);
		lblLastName.setText("Last Name");
		
		Label lblFirstName = new Label(composite, SWT.NONE);
		lblFirstName.setBounds(10, 48, 123, 32);
		lblFirstName.setText("First Name");
		
		text_LName = new Text(composite, SWT.BORDER);
		text_LName.setBounds(139, 10, 604, 38);
		
		text_FName = new Text(composite, SWT.BORDER);
		text_FName.setBounds(139, 48, 604, 38);
		
		Label lblStuNum = new Label(composite, SWT.NONE);
		lblStuNum.setBounds(10, 86, 111, 32);
		lblStuNum.setText("Student #");
		
		text_StuNum = new Text(composite, SWT.BORDER);
		text_StuNum.setBounds(139, 86, 604, 38);
		
		Label lblEmailAdrs = new Label(composite, SWT.NONE);
		lblEmailAdrs.setBounds(10, 124, 111, 32);
		lblEmailAdrs.setText("Email Adrs");
		
		text_Email = new Text(composite, SWT.BORDER);
		text_Email.setBounds(139, 124, 604, 38);
		
		Button btnSearch = new Button(composite, SWT.NONE);
		btnSearch.setBounds(430, 168, 139, 42);
		btnSearch.setText("Search");
		
		Button btnRegister = new Button(composite, SWT.NONE);
		btnRegister.setBounds(604, 168, 139, 42);
		btnRegister.setText("Register");
		
		Button btnGetLockerList = new Button(composite, SWT.NONE);
		btnGetLockerList.setBounds(10, 227, 167, 42);
		btnGetLockerList.setText("Get Locker List");
		
		List stu_List_Building = new List(composite, SWT.BORDER);
		stu_List_Building.setToolTipText("Select one building");
		stu_List_Building.setItems(new String[] {"Tunnel1", "Tunnel2", "Tunnel3"});
		stu_List_Building.setBounds(10, 275, 167, 201);
		
		List stu_List_Locker = new List(composite, SWT.BORDER);
		stu_List_Locker.setToolTipText("Select one locker");
		stu_List_Locker.setItems(new String[] {"Locker1", "Locker2", "Locker3", "Locker4", "Locker1", "Locker2", "Locker3", "Locker4", "Locker1", "Locker2", "Locker3", "Locker4"});
		stu_List_Locker.setBounds(254, 275, 175, 201);
		
		TabItem tbtmImportexport = new TabItem(tabFolder, SWT.NONE);
		tbtmImportexport.setToolTipText("Import / Export excel files");
		tbtmImportexport.setText("Import/Export");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmImportexport.setControl(composite_1);
		
		Button btnBrowse = new Button(composite_1, SWT.NONE);
		btnBrowse.setBounds(75, 50, 139, 42);
		btnBrowse.setText("Browse");
		
		Label lblSelectedFile = new Label(composite_1, SWT.NONE);
		lblSelectedFile.setAlignment(SWT.CENTER);
		lblSelectedFile.setBounds(75, 98, 139, 32);
		lblSelectedFile.setText("Selected file");
		
		text = new Text(composite_1, SWT.BORDER);
		text.setEditable(false);
		text.setBounds(85, 136, 507, 38);
		
		Button btnClose = new Button(Des1, SWT.NONE);
		
		btnClose.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				Des1.close();
			}
		});
		btnClose.setBounds(598, 561, 139, 42);
		btnClose.setText("Close");
		
		
		
		Des1.open();

		while (!Des1.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
