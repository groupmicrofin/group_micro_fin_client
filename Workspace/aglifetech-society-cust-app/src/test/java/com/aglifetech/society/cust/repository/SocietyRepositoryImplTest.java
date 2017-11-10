package com.aglifetech.society.cust.repository;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.aglifetech.society.cust.model.Society;

public class SocietyRepositoryImplTest {

	private SocietyRepository societyRepository;

	@Before
	public void setUp() throws Exception {
		societyRepository = new SocietyRepositoryImpl();
	}

	@Test
	public void testAddSociety() {

		Society society = getSocietyObj();

		int result = societyRepository.addSociety(society);

		assertTrue(result == 1);

		// Verification 
		// Society expectedObject = societyRepository.findSocietyByRefId("101");
		// assertTrue(society.equals(expectedObject));
	}

	public Society getSocietyObj() {
		Society society = new Society();
		society.setSocietyRefId("103");
		society.setSocietyName("Gokul Society");
		society.setScheduleFrequency("0 0 10 ? *");
		
		society.setIntrestRate(12);
		society.setShareAmount(100);
		society.setSocietyStartDate(LocalDate.now());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = "30/09/2017";
        LocalDate lastMeetDate = LocalDate.parse(date, formatter);
       
		//society.setLastMeetingDate(LocalDate.now());
		return society;
	}

	@Test
	public void testUpdateSociety() {

		Society society = new Society();

		society.setShareAmount(200);
		society.setIntrestRate(14);
		society.setScheduleFrequency("0 0 20 ? *");
		society.setId(9L);
		society.setUser("Jig");

		int result = societyRepository.updateSociety(society);

		assertTrue(result == 1);

	}

	@Ignore
	public void testDeleteSociety() {
		Society society = new Society();
		society.setId(3L);
		int result = societyRepository.deleteSociety(society);
		assertTrue(result == 1);

	}

	@Test
	public void testFindAllSocieties() {

	}

	@Test
	public void testFindSocietyById() {
		Society soc = societyRepository.findSocietyById(9L);
		assertTrue(soc.getId() == 9L);
		System.out.println(soc.toString());
	}

	@Test
	public void testFindByIdNeg() {
		Society soc = societyRepository.findSocietyById(-1L);
		assertTrue(soc.getId()==null);
	}

	@Ignore
	public void testFindSocietyByRefId() {
		fail("Not yet implemented");
	}

}
