import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class Logger {
	private static JTextPane pane;

	public Logger() {
		JFrame frame = new JFrame("JTextPane Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = frame.getContentPane();
		pane = new JTextPane();
		pane.setEditable(false);
		pane.setText("Welcome");
        pane.setDisabledTextColor(Color.BLACK);

		JScrollPane scrollPane = new JScrollPane(pane);
		cp.add(scrollPane, BorderLayout.CENTER);

		frame.setSize(400, 300);
		frame.setVisible(true);
	}

	public static void log(String data) {
		pane.setText(pane.getText() + "\n"+data);
	}

	public static void logf(String data, Object... args) {
		pane.setText(String.format(pane.getText() + "\n"+data, args));
	}

	public static void clear() {
		pane.setText("");
	}
}
