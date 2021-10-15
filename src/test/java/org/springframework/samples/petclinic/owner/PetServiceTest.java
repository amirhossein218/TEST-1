package org.springframework.samples.petclinic.owner;

import org.junit.After;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.samples.petclinic.utility.PetTimedCache;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PetServiceTest {

	private final Integer petId;

	private PetService petService;

	public PetServiceTest(Integer petId) {
		this.petId = petId;
	}

	private static class FakePetTimedCache extends PetTimedCache {
		private final Map<Integer, Pet> pets = new HashMap<>();

		public FakePetTimedCache() {
			super(null);
			for (int i = 0; i < 100; i++) {
				Pet pet = new Pet();
				pet.setId(i);
				pet.setName("number of pet " + i);
				pets.put(i, pet);
			}
		}
		@Override
		public Pet get(Integer petId) {
			return pets.get(petId);
		}
	}

	@Before
	public void setup() {
		petService = new PetService(
			new FakePetTimedCache(), null, Mockito.mock(Logger.class));
	}

	@Parameters
	public static List<Integer> petIds() {
		return Arrays.asList(1,2, 3,4, 5 ,9,11,20);
	}

	@Test
	public void findPetTest() {
		Pet pet = petService.findPet(petId);
		assertEquals(pet.getId(), petId);
	}

	@After
	public void teardown() {
		petService = null;
	}
}
