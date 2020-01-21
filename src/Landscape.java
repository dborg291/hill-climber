


public abstract class Landscape {
	
	protected int width = 100;
	protected  int height = 100;
	

	
	protected int [][] map;
	
	public Landscape(int width, int height) {
		this.width = width;
		this.height = height;
		
		map = new int[this.height][this.width];
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public abstract void initialize();
	
	public void printMap() {
		for (int[] x : this.map)
		{
		   for (int y : x)
		   {
		        System.out.print(y + "\t");
		   }
		   System.out.println("\n");
		}
	}
}
