package lms.application;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.StyledText;

import acceptanceTests.Register;
import acceptanceTests.EventLoop;

public class AboutWindow
{
	private Shell shell;
	private Display display;
	
	private Button btnBack;
	private Link lnkGit;
	private TabItem tbtmAboutUs;
	private Composite cmpstAboutus;
	private Composite cmpstHowto;
	private StyledText txtAboutus;
	
	public void runWindow()
	{
		// ====== create new window ( centre on monitor ) =====
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shell.setData("AboutWindow");
		shell.setSize(284, 353);
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setText("About");
		shell.setLocation (x, y);
		
		
		
		// ========== buttn back ========
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
		btnBack.setBounds(10, 278, 111, 27);
		
		
		
		// ========== tab folder ==========
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 10, 248, 262);
		
		
		// ======= tab how to ========
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("How to");
		
		cmpstHowto = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(cmpstHowto);
		
		// ========= text how to =========
		StyledText txtHowto = new StyledText(cmpstHowto, SWT.BORDER);
		txtHowto.setEditable(false);
		txtHowto.setText("Do Setup first");
		txtHowto.setBounds(0, 0, 240, 234);
		
		
		// ========= tab about us =========
		tbtmAboutUs = new TabItem(tabFolder, SWT.NONE);
		tbtmAboutUs.setText("About us");
		
		cmpstAboutus = new Composite(tabFolder, SWT.NONE);
		tbtmAboutUs.setControl(cmpstAboutus);
		
		// ======== text about us ===========
		txtAboutus = new StyledText(cmpstAboutus, SWT.BORDER);
		txtAboutus.setAlignment(SWT.CENTER);
		txtAboutus.setText("Morgan Epp\nTayler Frederick\nJunhyeok Kim\nBillal Kohistani\nEvan Spearman\n");
		txtAboutus.setEditable(false);
		txtAboutus.setBounds(10, 32, 220, 192);
		
		
		// ========== link to the git hub ==========
		lnkGit = new Link(cmpstAboutus, SWT.NONE);
		lnkGit.setBounds(81, 10, 70, 15);
		lnkGit.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				Program.launch("https://github.com/ESpearman/COMP3350-Group-Project");
			}
		});
		lnkGit.setText("<a>Go to GitHub</a>");
		
		
		
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
	
	public AboutWindow()
	{
		Register.newWindow(this);
		display = Display.getDefault();
		runWindow();
	}
}
