package org.computer.knauss.reqtDiscussion.io.csv;

import static org.junit.Assert.*;

import java.util.Properties;

import org.computer.knauss.reqtDiscussion.model.DiscussionEvent;
import org.junit.Before;
import org.junit.Test;


public class CSVDiscussionEventDAOTest {

	private CSVDiscussionEventDAO testDao;

	@Before
	public void setUp() throws Exception {
		this.testDao = new CSVDiscussionEventDAO();
	}

	@Test
	public void testGetDiscussionEventsOfDiscussion() {
		Properties p = new Properties();
		try {
			this.testDao.getDiscussionEventsOfDiscussion(12345);
			fail("expected not configured exception");
		} catch (RuntimeException re) {

		}
		try {
			this.testDao.configure(p);
			fail("expected no file name exception");
		} catch (IllegalArgumentException re) {

		}

		p.setProperty(CSVDiscussionEventDAO.PROP_FILENAME,
				"testfiles/example-workitemcomments.csv");
		p.setProperty(CSVDiscussionEventDAO.PROP_ID_COL,
				"4");
		p.setProperty(CSVDiscussionEventDAO.PROP_DISC_ID_COL,
				"1");
		p.setProperty(CSVDiscussionEventDAO.PROP_CONTENT_COL,
				"0");
		p.setProperty(CSVDiscussionEventDAO.PROP_CDATE_COL,
				"2");
		p.setProperty(CSVDiscussionEventDAO.PROP_CREATOR_COL,
				"3");
		p.setProperty(CSVDiscussionEventDAO.PROP_START_ROW, "1");
		this.testDao.configure(p);
		DiscussionEvent[] events = this.testDao
				.getDiscussionEventsOfDiscussion(12345);

		assertEquals(0, events.length);
		events = this.testDao.getDiscussionEventsOfDiscussion(32654);
		assertEquals(24, events.length);
	}

	@Test
	public void testGetDiscussionEvent() {
		Properties p = new Properties();
		p.setProperty(CSVDiscussionEventDAO.PROP_FILENAME,
				"testfiles/example-workitemcomments.csv");
		p.setProperty(CSVDiscussionEventDAO.PROP_ID_COL,
				"4");
		p.setProperty(CSVDiscussionEventDAO.PROP_DISC_ID_COL,
				"1");
		p.setProperty(CSVDiscussionEventDAO.PROP_CONTENT_COL,
				"0");
		p.setProperty(CSVDiscussionEventDAO.PROP_CDATE_COL,
				"2");
		p.setProperty(CSVDiscussionEventDAO.PROP_CREATOR_COL,
				"3");
		p.setProperty(CSVDiscussionEventDAO.PROP_START_ROW, "1");
		this.testDao.configure(p);
	DiscussionEvent event = this.testDao.getDiscussionEvent(54132);
	
	assertNotNull(event);
	assertEquals(event.getID(), 54132);
	assertEquals("user4955", event.getCreator());
	}

}
