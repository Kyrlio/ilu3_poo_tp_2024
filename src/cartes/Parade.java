package cartes;

public class Parade extends Bataille {

	public Parade(Type type) {
		super(type);
	}

	@Override
	public String toString() {
		return getType().getParade();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Parade) {
			Parade otherParade = (Parade) obj;
			return this.getType().equals(otherParade.getType());
		}
		return false;
	}

}
