package com.example.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	private final BookRepository bookRepository;

	public AppRunner(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(".... Fetching books");
		logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
		logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
		logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));

		// updating cache

		logger.info(".... Fetching books");
		logger.info("isbn-1234 -->" + bookRepository.updateByIsbn("isbn-1234"));
		logger.info("isbn-4567 -->" + bookRepository.updateByIsbn("isbn-4567"));

		// fetch after updating book
		logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));

		// deleting data for "isbn-1234" from cache
		bookRepository.deleteByIsbn("isbn-1234");

		// fetch after after deletion book
		logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));

		// deleting all the data from cache
		bookRepository.deleteAllIsbn();

		// fetch after after deletion book
		logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
		
		
		logger.info(" books"  +bookRepository.getAllBooks()); // getting from db it not in cache
		
		logger.info(" books"  +bookRepository.getAllBooks()); //getting from cache because earlier method already will put all books in cache
	}

}
