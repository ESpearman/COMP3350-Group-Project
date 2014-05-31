package lms.application;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;


public class MainWindow {
	private static Text buttonTestTextBox;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		final Shell shlSwtTesting = new Shell();
		
		shlSwtTesting.setText("SWT testing !");
		shlSwtTesting.setSize(720, 514);
		
		Combo comboTest = new Combo(shlSwtTesting, SWT.NONE);
		comboTest.setItems(new String[] {"test1", "test2", "test3"});
		comboTest.setBounds(50, 90, 159, 40);
		comboTest.setText("select one");
		
		Button buttonTest = new Button(shlSwtTesting, SWT.NONE);
		buttonTest.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				buttonTestTextBox.setText("button test works");
			}
		});
		buttonTest.setBounds(244, 88, 208, 42);
		buttonTest.setText("button test");
		
		
		
		buttonTestTextBox = new Text(shlSwtTesting, SWT.BORDER);
		buttonTestTextBox.setBounds(244, 136, 208, 38);
		
		Button buttonTerminate = new Button(shlSwtTesting, SWT.NONE);
		buttonTerminate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlSwtTesting.close();
			}
		});
		
		
		buttonTerminate.setBounds(497, 357, 139, 42);
		buttonTerminate.setText("Terminate");

		shlSwtTesting.open();
		shlSwtTesting.layout();
		while (!shlSwtTesting.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
