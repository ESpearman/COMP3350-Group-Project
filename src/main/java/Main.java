
import org.eclipse.swt.widgets.*;

public class Main {

    public static void main(String[] args) {

        //Create a blank SWT window

        Display display = new Display();
        Shell shell = new Shell(display);

        shell.open();
        shell.setText("Hello World-------!");

        while(! shell.isDisposed()) {
            if(! display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();
    }

}
