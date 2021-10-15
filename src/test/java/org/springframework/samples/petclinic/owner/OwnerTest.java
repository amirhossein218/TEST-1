package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Owner}
 *
 * Properties (getters/setters in Java) are good examples of code that usually doesn't contain any logic,
 * and doesn't require testing. But watch out: once you add any check inside the property,
 * you’ll want to make sure that logic is being tested.
 */
class OwnerTest {
	private final static String BIRD_NAME = "Bolboly";
	private final static String CAT_NAME = "Catty";
	private final static String DOG_NAME = "Doggy";


	private Owner owner;
	private Pet bird;
	private Pet cat;
	private Pet dog;



	@BeforeEach
	public void setup() {
		owner = new Owner();

		dog = new Pet();
		dog.setName(DOG_NAME);
		cat = new Pet();
		cat.setName(CAT_NAME);
		bird = new Pet();
		bird.setName(BIRD_NAME);

	}

	@AfterEach
	void teardown() {
		owner = null;
		bird = null;
		cat = null;
		dog = null;

	}

	@Test
	public void addPetMustdIncreaseLengthOfPetsList() {
		assertTrue(owner.getPets().isEmpty());//check list is empty

		owner.addPet(bird);
		assertEquals(owner.getPets().size(), 1);

		owner.addPet(cat);
		owner.addPet(dog);
		assertEquals(owner.getPets().size(),3);

	}

	@Test
	public void addOnePetTestMustBeExactlyDone() {
		owner.addPet(bird);
		assertEquals(owner.getPets().size(),1);
		assertEquals(owner.getPets().get(0), bird);

	}

	@Test
	public void addMultiplePetsMustBeExactlyDone() {
		owner.addPet(bird);
		owner.addPet(dog);
		owner.addPet(cat);

		assertEquals(owner.getPets().size(), 3);
		assertTrue(owner.getPets().contains(dog));
		assertTrue(owner.getPets().contains(cat));
		assertTrue(owner.getPets().contains(bird));
	}

	@Test
	public void removePet() {
		owner.addPet(dog);
		owner.addPet(cat);
		owner.addPet(bird);
		assertEquals(owner.getPets().size(), 3);

		owner.removePet(dog);
		assertEquals(owner.getPets().size(),2);
		assertTrue(owner.getPets().contains(cat));
		assertTrue(owner.getPets().contains(bird));

		owner.removePet(cat);
		assertEquals(owner.getPets().size(),1);
		assertTrue(owner.getPets().contains(bird));

		owner.removePet(bird);
		assertTrue(owner.getPets().isEmpty());
	}

	@Test
	public void getPetByName() {
		owner.addPet(bird);
		owner.addPet(cat);
		assertEquals(owner.getPet(BIRD_NAME), bird);
		assertEquals(owner.getPet(CAT_NAME),cat);
	}



	@Test
	public void getPetMustReturnNullIfPetNotAdded() {
		owner.addPet(bird);
		owner.addPet(dog);
		assertNull(owner.getPet(CAT_NAME));
	}

	@Test
	public void getPetWithIgnoreNewMustIgnoreNewPets() {
		owner.addPet(bird);
		assertNull(owner.getPet(BIRD_NAME, true));
	}

	@Test
	public void getPetWithIgnoreNewMustIgnoreNewPetsIfDoesNotExtist() {
		owner.addPet(bird);
		assertNull(owner.getPet(DOG_NAME,true));
	}


	@Test
	public void getPetsMustReturnListSorted() {
		bird.setName("3");
		cat.setName("2");
		dog.setName("1");
		owner.addPet(bird);
		owner.addPet(cat);
		owner.addPet(dog);

		assertEquals(owner.getPets().get(0), dog);
		assertEquals(owner.getPets().get(1), cat);
		assertEquals(owner.getPets().get(2),bird);
	}

	@Test
	public void setAddress() {
		final String Addr = "Tehran";
		owner.setAddress(Addr);
		assertEquals(owner.getAddress(), Addr);
	}

	@Test
	public void setCity() {
		final String City = "Tehran";
		owner.setCity(City);
		assertEquals(owner.getCity(), City);
	}
	//sets and gets are similar to test
	@Test
	public void setTelephone() {
		final String Tel = "021-12345678";
		owner.setTelephone(Tel);
		assertEquals(owner.getTelephone(), Tel);
	}


}
