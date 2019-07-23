package com.db.gtb.tf.interview;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


public class PalindromeAcceptanceTests {

	private boolean testWord(String word) {
	    String input = word.toLowerCase();
        return IntStream
                .range(0, input.length() / 2)
                .allMatch(i -> input.charAt(i) == input.charAt(input.length() - i - 1));
	}

	@Test
	public void lowercase_words() {
		assertThat(testWord("john")).isEqualTo(false);
		assertThat(testWord("hannah")).isEqualTo(true);
	}

    @Test
	public void uppercase_words() {
		assertThat(testWord("VIN")).isEqualTo(false);
		assertThat(testWord("BOB")).isEqualTo(true);
	}

	@Test
	public void mixed_case_words() {
		assertThat(testWord("John")).isEqualTo(false);
		assertThat(testWord("Vin")).isEqualTo(false);

		assertThat(testWord("Bob")).isEqualTo(true);
		assertThat(testWord("HanNah")).isEqualTo(true);
	}
}
