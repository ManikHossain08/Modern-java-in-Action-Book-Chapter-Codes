/**
 * Concrete Builder of the Builder Pattern
 *
 */
public class TropicalShelterBuilder extends ShelterBuilder{
	public void buildRoof(){
		shelterProduct.setRoof("palm tree leaves");       // The construction process may vary
	}                                                     // across the different Concrete Builders
	public void buildStructure(){
		shelterProduct.setStructure("bamboo");
	}
	public void buildFloor(){
		shelterProduct.setFloor("goat skin");
	}
}
