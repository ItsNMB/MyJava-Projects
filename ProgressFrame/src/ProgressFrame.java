import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ProgressFrame extends JFrame {

	private final JPanel panel = new JPanel();

	private final JLabel progressText;

	private final JProgressBar progressBar;

	private final JTextArea consoleArea;

	public ProgressFrame(String title) {
		this(title, new Runnable() {
			public void run() {
				System.exit(0);
			}
		});
	}

	public ProgressFrame(String title, Runnable canceler) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception exception) {}
		setTitle(title);
		setDefaultCloseOperation(2);
		setBounds(100, 100, 600, 400);
		setContentPane(this.panel);
		setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 600, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 200 };
		gridBagLayout.columnWeights = new double[] { 1.0D, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 1.0D };
		this.panel.setLayout(gridBagLayout);
		this.progressText = new JLabel("Progress Text");
		GridBagConstraints gbc_lblProgressText = new GridBagConstraints();
		gbc_lblProgressText.insets = new Insets(10, 0, 5, 0);
		gbc_lblProgressText.gridx = 0;
		gbc_lblProgressText.gridy = 0;
		this.panel.add(this.progressText, gbc_lblProgressText);
		this.progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.insets = new Insets(0, 25, 5, 25);
		gbc_progressBar.fill = 2;
		gbc_progressBar.ipady = 5;
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 1;
		this.panel.add(this.progressBar, gbc_progressBar);
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> {
			canceler.run();
			dispose();
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 25, 5, 25);
		gbc_btnCancel.fill = 2;
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 2;
		this.panel.add(btnCancel, gbc_btnCancel);
		this.consoleArea = new JTextArea();
		this.consoleArea.setFont(new Font("Monospaced", 0, 11));
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(15, 25, 25, 25);
		gbc_textArea.fill = 1;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 3;
		JScrollPane scroll = new JScrollPane(this.consoleArea, 22, 30);
		scroll.setAutoscrolls(true);
		this.panel.add(scroll, gbc_textArea);

		setSize(640, 360);
		setVisible(true);
	}

	public void start(String label) {
		message(label, true);
		this.progressBar.setValue(0);
		this.progressBar.setIndeterminate(false);
	}

	public void end(String label) {
		message(label, true);
		this.progressBar.setValue(100);
		this.progressBar.setIndeterminate(false);
	}

	public void progress(double progress) {
		this.progressBar.setValue((int) progress);
	}

	public void run(String message) { // start animating progress bar
		message(message, true);
		this.progressBar.setIndeterminate(true);
	}

	public void finish(String message) { // stop animating progress bar
		message(message, true);
		this.progressBar.setIndeterminate(false);
	}

	public void message(String message) {
		message(message, false);
	}

	public void message(String message, boolean high) {
		if (high)
			this.progressText.setText(message);
		this.consoleArea.append(message + "\n");
		this.consoleArea.setCaretPosition(this.consoleArea.getDocument().getLength());
	}
}
