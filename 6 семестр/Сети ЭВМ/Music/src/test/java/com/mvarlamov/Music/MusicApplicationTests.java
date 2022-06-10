package com.mvarlamov.Music;

import com.mvarlamov.Music.dao.ArtistDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MusicApplicationTests {
	@Autowired
	public ArtistDAO artistDAO;

	@Test
	void contextLoads() {
		System.err.println(artistDAO.getAll());
	}

}
