package com.ArmiaJsona.emojiSearch;

import com.ArmiaJsona.emojiSearch.allegro.AllegroClient;
import com.ArmiaJsona.emojiSearch.allegro.OfferDetail;
import com.ArmiaJsona.emojiSearch.emoji.EmojiClient;
import com.ArmiaJsona.emojiSearch.model.AllOffersResponse;
import com.ArmiaJsona.emojiSearch.translator.YandexClient;
import com.ArmiaJsona.emojiSearch.utils.PhraseResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmojiSearchApplicationTests {

	@Autowired
	private EmojiClient emojipediaClient;
	@Autowired
	private YandexClient yandexClient;
	@Autowired
	private AllegroClient allegroClient;
	@Autowired
    private PhraseResolver phraseResolver;

    @Test
    public void getEmojiNameByUnicodeTest() {
        String body = this.emojipediaClient.getEmojiNameByUnicode("\uD83C\uDF47");
        assertThat(body).isEqualToIgnoringCase("Grapes");
    }

    @Test
    public void translateGrapesToPolishTest() {
        String body = this.yandexClient.getTranslationFor("grapes");
        assertThat(body).isEqualToIgnoringCase("winogrona");
    }

    @Test
    public void returnAllegroOffersByPhraseTest() {
        AllOffersResponse allOffersResponse = this.allegroClient.getAllOffersByPhrase("winogrona",null);
        assertThat(allOffersResponse).isNotNull();
    }

	@Test
	public void returnAllegroOfferByIdTest() {
        OfferDetail offerDetail = allegroClient.getOfferById("7074249172");
        assertThat(offerDetail).isNotNull();
	}

    @Test
    public void parseInputTest() {
//	    String body = phraseResolver.changeEmojisToText("\uD83D\uDC7B\uD83C\uDF85test");
//        System.out.println(body);
//        assertThat(body).isEqualToIgnoringCase("Duch Święty Mikołaj test");
    }

    @Test
    public void splitPhraseWithEmojisTest() {

    }
}
