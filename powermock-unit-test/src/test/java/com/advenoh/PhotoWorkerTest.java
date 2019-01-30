package com.advenoh;

import com.advenoh.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

//import static org.powermock.api.mockito.PowerMockito.when;

/**
 * https://www.tildedave.com/2011/03/06/pattern-stubbing-legacy-superclasses-with-mockito-spies.html
 *
 */
public class PhotoWorkerTest {
	User mockUser;

	@Before
	public void setUp() {
		mockUser = mock(User.class);
	}

	public PhotoWorker getPhotoWorker() {
		return new PhotoWorker() {
			protected User getUser(String name) {
				return mockUser;
			}
		};
	}

	@Test
	public void processesNoPhotosCorrectly() {
//		PhotoWorker worker = getPhotoWorker();
//		when(mockUser.getPhotos()).thenReturn(new LinkedList());
//		worker.run("bob");
		// verify etc
	}
}