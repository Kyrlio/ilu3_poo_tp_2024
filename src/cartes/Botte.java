package cartes;

public class Botte extends Probleme{

	public Botte(Type type) {
		super(type);
		}

	@Override
	public String toString() {
		return getType().getBotte();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Botte botte) {
			Botte objBotte = botte;
			return this.getType().equals(objBotte.getType());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.getType().hashCode();
	}
	
}
