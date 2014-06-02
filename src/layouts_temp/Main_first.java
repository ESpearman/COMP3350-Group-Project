package layouts_temp;


import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;


public class Main_first {
	private static Text text_LName;
	private static Text text_FName;
	private static Text text_StuNum;
	private static Text text_Email;
	private static Text path_file1;

	public static void main(String[] args)
	{
		
		Display display = Display.getDefault();
		final Shell Des1 = new Shell();
		Des1.setSize(808, 657);
		Des1.setText("OneWindow");
		
		TabFolder tabFolder = new TabFolder(Des1, SWT.NONE);
		tabFolder.setBounds(0, 0, 774, 531);
		
		TabItem tab_student = new TabItem(tabFolder, SWT.NONE);
		tab_student.setToolTipText("Assigning studnet to a locker");
		tab_student.setText("Student");
		
		Composite cp_student = new Composite(tabFolder, SWT.NONE);
		tab_student.setControl(cp_student);
		
		Label lblLastName = new Label(cp_student, SWT.NONE);
		lblLastName.setBounds(10, 10, 123, 32);
		lblLastName.setText("Last Name");
		
		Label lblFirstName = new Label(cp_student, SWT.NONE);
		lblFirstName.setBounds(10, 48, 123, 32);
		lblFirstName.setText("First Name");
		
		text_LName = new Text(cp_student, SWT.BORDER);
		text_LName.setBounds(139, 10, 604, 38);
		
		text_FName = new Text(cp_student, SWT.BORDER);
		text_FName.setBounds(139, 48, 604, 38);
		
		Label lblStuNum = new Label(cp_student, SWT.NONE);
		lblStuNum.setBounds(10, 86, 111, 32);
		lblStuNum.setText("Student #");
		
		text_StuNum = new Text(cp_student, SWT.BORDER);
		text_StuNum.setBounds(139, 86, 604, 38);
		
		Label lblEmailAdrs = new Label(cp_student, SWT.NONE);
		lblEmailAdrs.setBounds(10, 124, 111, 32);
		lblEmailAdrs.setText("Email Adrs");
		
		text_Email = new Text(cp_student, SWT.BORDER);
		text_Email.setBounds(139, 124, 604, 38);
		
		Button btnSearch = new Button(cp_student, SWT.NONE);
		btnSearch.setBounds(430, 168, 139, 42);
		btnSearch.setText("Search");
		
		Button btnRegister = new Button(cp_student, SWT.NONE);
		btnRegister.setBounds(604, 168, 139, 42);
		btnRegister.setText("Register");
		
		Button btnGetLockerList = new Button(cp_student, SWT.NONE);
		btnGetLockerList.setBounds(10, 227, 167, 42);
		btnGetLockerList.setText("Get Locker List");
		
		List stu_List_Building = new List(cp_student, SWT.BORDER);
		stu_List_Building.setToolTipText("Select one building");
		stu_List_Building.setItems(new String[] {"Tunnel1", "Tunnel2", "Tunnel3"});
		stu_List_Building.setBounds(10, 275, 167, 201);
		
		List stu_List_Locker = new List(cp_student, SWT.BORDER);
		stu_List_Locker.setToolTipText("Select one locker");
		stu_List_Locker.setItems(new String[] {"Locker1", "Locker2", "Locker3", "Locker4", "Locker1", "Locker2", "Locker3", "Locker4", "Locker1", "Locker2", "Locker3", "Locker4"});
		stu_List_Locker.setBounds(254, 275, 175, 201);
		
		TabItem tab_import = new TabItem(tabFolder, SWT.NONE);
		tab_import.setToolTipText("Import excel files");
		tab_import.setText("Import");
		
		Composite cp_import = new Composite(tabFolder, SWT.NONE);
		tab_import.setControl(cp_import);
		
		Button btnBrowse = new Button(cp_import, SWT.NONE);
		btnBrowse.setBounds(75, 50, 139, 42);
		btnBrowse.setText("Browse");
		
		Label lblSelectedFile = new Label(cp_import, SWT.NONE);
		lblSelectedFile.setAlignment(SWT.CENTER);
		lblSelectedFile.setBounds(75, 98, 139, 32);
		lblSelectedFile.setText("Selected file");
		
		path_file1 = new Text(cp_import, SWT.BORDER);
		path_file1.setEditable(false);
		path_file1.setBounds(85, 136, 507, 38);
		
		TabItem tab_export = new TabItem(tabFolder, SWT.NONE);
		tab_export.setText("Export");
		
		Composite cp_export = new Composite(tabFolder, SWT.NONE);
		tab_export.setControl(cp_export);
		
		Button btnEmailAddrs = new Button(cp_export, SWT.CHECK);
		btnEmailAddrs.setBounds(81, 177, 201, 32);
		btnEmailAddrs.setText("Email addrs");
		
		Button btnAssigndLockers = new Button(cp_export, SWT.CHECK);
		btnAssigndLockers.setBounds(81, 227, 201, 32);
		btnAssigndLockers.setText("Assignd lockers");
		
		Button btnLockers = new Button(cp_export, SWT.CHECK);
		btnLockers.setBounds(79, 276, 181, 32);
		btnLockers.setText("lockers");
		
		Button btnExportbrowse = new Button(cp_export, SWT.NONE);
		btnExportbrowse.setBounds(393, 222, 170, 42);
		btnExportbrowse.setText("Export/browse");
		
		TabItem tbtmAdmin = new TabItem(tabFolder, SWT.NONE);
		tbtmAdmin.setText("admin?");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmAdmin.setControl(composite);
		
		Button btnEditSetting = new Button(composite, SWT.NONE);
		btnEditSetting.setBounds(484, 194, 139, 42);
		btnEditSetting.setText("Edit setting?");
		
		Button btnClose = new Button(Des1, SWT.NONE);
		
		btnClose.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				Des1.close();
			}
		});
		btnClose.setBounds(635, 537, 139, 42);
		btnClose.setText("Close");
		
		
		
		Des1.open();

		while (!Des1.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
