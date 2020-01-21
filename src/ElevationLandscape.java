import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ElevationLandscape extends Landscape {

	
	int numberOfPeaks;
	int maximumElevation;
	int smoothingLevel;
	
	public ElevationLandscape(int width, int height, int numberOfPeaks, int maximumElevation, int smoothingLevel) {
		super(width, height);
		
		this.numberOfPeaks = numberOfPeaks;
		this.maximumElevation = maximumElevation;
		this.smoothingLevel = smoothingLevel;
	}

	@Override
	public void initialize() {
		
		if (width * height < numberOfPeaks) {
			System.out.println("Number of peaks must be less than total number of cells.");
			return;
		}
		
		Random random = new Random();
		
		ArrayList<Integer> peakCells = new ArrayList<Integer>();
		
		while (peakCells.size() != this.numberOfPeaks) {
			int randomValue = random.nextInt(width * height);
			
			if (!peakCells.contains(randomValue)) {
				peakCells.add(randomValue);
			}
		}
		
		createPeaks(peakCells);
	}
	
	private void createPeaks(ArrayList<Integer> peakCells) {
		Random random = new Random();
		
		int elevation = 0;
		for (int i = 0; i < this.numberOfPeaks; i++) {
			elevation = random.nextInt(this.maximumElevation/2) + this.maximumElevation/2;
			this.map[peakCells.get(i) / this.map[0].length][peakCells.get(i) % this.map.length] = elevation;
		}

		for (int i = 0; i < this.smoothingLevel; i++) {
			diffuseElevation(i);
		}
	}
	
	private void diffuseElevation(int smoothingLevel) {
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				
				adjustCell(i, j);
				adjustCell(j, i);
				
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				adjustCell(i, j);
				adjustCell(j, i);
				
			}
		}
		
	}
	
	private void adjustCell(int i, int j) {
		Random random = new Random();

			Integer[] adj = getAdjacent(i, j);
			int sum = 0;
		
			
			int max = adj[0];

			for (int a = 1; a < adj.length; a++) {
			
				if (adj[a] == null) {
					continue;
				}
			    if (adj[a] > max) {
			      max = adj[a];
			    }
			}
			
		
			int total = 1;
			for(int k = 0; k < adj.length; k++) {
				if (adj[k] == null) {
					continue;
				}
				sum = sum + adj[k];
				if (adj[k] > 0) {
					total++;
				}
			}
			
			double average = sum / total;
		
			int newElevation = (int) max - (int) (random.nextFloat() * average);
			
			if (newElevation == 0) {
				newElevation = random.nextInt(this.maximumElevation) / this.smoothingLevel - smoothingLevel;
			}
					
			this.map[i][j] = newElevation;
		
	}
	
	private Integer[] getAdjacent(int y, int x) {
		Integer[] result = new Integer[8];
		int index = 0;
		
		int[][] pad = new int[this.height + 2][this.width + 2];
		
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				pad[i + 1][j + 1] = this.map[i][j];
			}
		}
	
		for (int dx = -1; dx <= 1; ++dx) {
		    for (int dy = -1; dy <= 1; ++dy) {
		        if (dx != 0 || dy != 0) {
		           result[index] = pad[y + dy + 1][x + dx + 1];
		           index++;
		        }
		    }
		}
		return result;
	}
}
