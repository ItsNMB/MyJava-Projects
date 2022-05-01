

class Run extends Template {

	ProgressFrame f;

	public Run(String[] args) {
		f = new ProgressFrame("Test");
		f.start("Starting...");
		delay(1000);
		f.message("Running... HIGH", true);
		f.run("downloading...");
		delay(500);
		f.progress(100);
		f.end("Done!");
	}
}