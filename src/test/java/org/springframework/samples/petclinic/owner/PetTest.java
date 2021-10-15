package org.springframework.samples.petclinic.owner;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.samples.petclinic.visit.Visit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeFalse;

/**
 * Theory class for {@link Pet}
 */
@RunWith(Theories.class)
public class PetTest {
	private Pet pet;

	@DataPoints
	public static List<List<Visit>> visitLists = new ArrayList<>();

	@BeforeClass
	public static void setupVisits() {
		Visit today = new Visit();
		today.setDate(LocalDate.now());
		Visit tomorrow = new Visit();
		tomorrow.setDate(LocalDate.now().plusDays(1));
		Visit nextmonth = new Visit();
		nextmonth.setDate(LocalDate.now().plusMonths(1));
		Visit prevmonth = new Visit();
		prevmonth.setDate(LocalDate.now().minusMonths(1));
		Visit yesterday = new Visit();
		yesterday.setDate(LocalDate.now().minusDays(1));
		Visit nextyear = new Visit();
		nextyear.setDate(LocalDate.now().plusYears(1));
		Visit prevyear = new Visit();
		prevyear.setDate(LocalDate.now().minusYears(1));

		visitLists.add(Arrays.asList(today,tomorrow,yesterday,nextmonth,prevmonth,nextyear,prevyear));
	}

	@Before
	public void setup() {
		pet = new Pet();
		pet.setId(1);
	}

	@After
	public void teardown() {
		pet = null;
	}

	@Theory
	public void getVisitsMustReturnSorted(List<Visit> visitList) {
		assumeFalse(visitList.isEmpty());
		for (int i = 0; i< visitList.size();i++)
			pet.addVisit(visitList.get(i));
		PropertyComparator.sort(visitList, new MutableSortDefinition("date", false, false));
		assertEquals(pet.getVisits(), visitList);
	}
}
