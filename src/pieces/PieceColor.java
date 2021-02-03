package pieces;

public enum PieceColor {

	RED ("\u001B[31m"),
	BLUE ("\u001B[34m"),
	RESET ("\u001B[0m"),
	CYAN ("\u001B[36m"),
	WHITE ("\u001B[37m"),
	YELLOw ("\u001B[33m");
	
	private String color;
	
	PieceColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
}
