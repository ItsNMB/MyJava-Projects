import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

public class ClipboardUtil {

	public static void set(String text) {
		Clipboard clipboard = getSystemClipboard();
		clipboard.setContents(new StringSelection(text), null);
	}

	public static String get() throws Exception {
		Clipboard systemClipboard = getSystemClipboard();
		DataFlavor dataFlavor = DataFlavor.stringFlavor;

		if (systemClipboard.isDataFlavorAvailable(dataFlavor)) {
			Object text = systemClipboard.getData(dataFlavor);
			return (String) text;
		}

		return null;
	}

	private static Clipboard getSystemClipboard() {
		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
		return defaultToolkit.getSystemClipboard();
	}

}