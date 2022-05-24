
public class Run extends Template {

	final int LOWISGOOD = 0, HIGHISGOOD = 1;

	public Run() {
		String[] lines = loadStrings("ottd.csv");
		// printArray(lines);

		int numOfFields = split(lines[0], ';').length - 1;
		int numOfNames = lines.length - 3;

		// Names
		String[] names = new String[numOfNames];
		for (int i = 0; i < names.length; i++) names[i] = split(lines[i + 3], ';')[0];
		for (int i = 0; i < names.length; i++) if (names[i].isEmpty()) numOfNames--;

		// Fields HIGH or LOW > 0 = false/LOW is Good | 1 = true/HIGH is good
		boolean[] fieldsHoL = new boolean[numOfFields];
		for (int i = 0; i < numOfFields; i++) fieldsHoL[i] = split(lines[0], ';')[i + 1].toLowerCase().equals("high");

		// Weights
		int[] weightsInt = new int[numOfFields];
		double[] weights = new double[numOfFields];
		for (int i = 0; i < numOfFields; i++) weightsInt[i] = parseInt(split(lines[1], ';')[i + 1]);
		int sumOfWeights = 0;
		for (int i = 0; i < numOfFields; i++) sumOfWeights += weightsInt[i];
		for (int i = 0; i < numOfFields; i++) weights[i] = (double)weightsInt[i] / sumOfWeights;

		// Fields
		String[] fields = new String[numOfFields];
		for (int i = 0; i < numOfFields; i++) fields[i] = split(lines[2], ';')[i + 1];

		println("Number of Names: " + numOfNames);
		println("Number of Fields: " + numOfFields);



		// Highest Values of Fields
		int[] fieldsHighest = new int[numOfFields];
		for (int name = 3; name < numOfNames + 3; name++) {
			for (int field = 0; field < numOfFields; field++) {
				int value = parseInt(split(lines[name], ';')[field + 1]);
				if (value > fieldsHighest[field]) fieldsHighest[field] = value;
			}
		}

		// Scores of each Name
		double[] scores = new double[numOfNames];
		for (int name = 3; name < numOfNames + 3; name++) {
			double[] tempScores = new double[numOfFields];
			for (int field = 0; field < numOfFields; field++) {
				int value = parseInt(split(lines[name], ';')[field + 1]);

				double tmpVal = (double)value / fieldsHighest[field];

				if (!fieldsHoL[field]) tmpVal = 1 - tmpVal;

				tmpVal *= weights[field];
				tempScores[field] = tmpVal;
			}
			scores[name - 3] = sum(tempScores) * 100;
		}

		Entity[] trains = new Entity[numOfNames];


		for (int e = 0; e < numOfNames; e++)
			trains[e] = new Entity(names[e], scores[e]);


		// sort(trains);
		double highscore = 0;
		Entity winner = new Entity("NONAME", 0);
		for (int i = 0; i < trains.length; i++) if (trains[i].score > highscore) {
				highscore = trains[i].score;
				winner = trains[i];
			}

		println();
		println("+=== Winner: [" + winner.name + "] at " + nf(winner.score, 2, 1) + "% ===+");
		printEntities(trains);
		println();

	}

	double sum(double[] arr) {
		double result = 0;
		for (double d : arr)
			result += d;
		return result;
	}

	void sort(Entity[] array) {
		for (int i = 0; i < array.length - 1; i++)
			for (int j = 0; j < array.length - i - 1; j++)
				if (array[j].score > array[j + 1].score) {
					Entity t = array[i];
					array[i] = array[j];
					array[j] = t;
				}
	}

	void printEntities(Entity[] entities) {
		String[] spaces = new String[entities.length];
		for (int x = 0; x < entities.length; x++) spaces[x] = "";

		int length = 0;
		for (int s = 0; s < entities.length; s++)
			if (entities[s].name.length() > length) length = entities[s].name.length();
		for (int x = 0; x < entities.length; x++)
			for (int s = 0; s < length - entities[x].name.length(); s++) spaces[x] += ' ';

		for (int i = 0; i < entities.length; i++) {
			println("[" + entities[i].name + "]" + spaces[i] + " = " + nf(entities[i].score, 2, 1) + "%");
		}
	}

	public class Entity {
		public String name;
		public double score;
		public Entity(String n, double s) {
			this.name = n;
			this.score = s;
		}
	}
}