package com.ArmiaJsona.emojiSearch;

import com.ArmiaJsona.emojiSearch.allegro.AllegroClient;
import com.ArmiaJsona.emojiSearch.allegro.OfferDetail;
import com.ArmiaJsona.emojiSearch.emoji.EmojiClient;
import com.ArmiaJsona.emojiSearch.model.AllOffersResponse;
import com.ArmiaJsona.emojiSearch.translator.YandexClient;
import com.ArmiaJsona.emojiSearch.utils.PhraseResolver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
	    String body = phraseResolver.translatePhrasesWithEmojiToText("\uD83D\uDC7B\uD83C\uDF85test");
        System.out.println(body);
        assertThat(body).isEqualToIgnoringCase("Duch Święty Mikołaj test");
    }

    @Test
    public void splitPhraseWithEmojisTest() {

    }

    @Test
    public void multipleSearchQualityTest(){
        List<String> unicodes = Arrays.asList("\uD83D\uDE0D","\uD83D\uDE0A", "\uD83D\uDE02", "\uD83D\uDE18", "\uD83D\uDD25 \uD83D\uDCA3 \uD83D\uDD73️",
                "⏱ \uD83D\uDDBC️ \uD83D\uDD79️", "\uD83D\uDCDF ☎️ \uD83D\uDCF1 \uD83D\uDDA8️ ⌨️", "\uD83D\uDCC0 \uD83D\uDDBC️", "\uD83D\uDCA3 ☎️",
                "⛷ \uD83C\uDFC2 \uD83C\uDFC6", "\uD83C\uDFB3 \uD83C\uDFBE", "\uD83C\uDFD3 ⚽", "\uD83C\uDFC6 ⚽", "\uD83C\uDFBE \uD83C\uDFD2", "\uD83C\uDFF8",
                "\uD83C\uDFB1", "\uD83C\uDFAE \uD83C\uDFB0", "\uD83D\uDE01", "\uD83E\uDD23", "\uD83D\uDE03", "\uD83D\uDE04", "\uD83D\uDE1A", "\uD83D\uDE19",
                "\uD83D\uDE2E", "\uD83E\uDD10", "\uD83E\uDD10", "\uD83D\uDE26", "\uD83D\uDE08", "\uD83D\uDC7F", "\uD83D\uDE38", "\uD83D\uDE39", "\uD83D\uDE3D",
                "\uD83D\uDE40", "\uD83D\uDE3F", "\uD83D\uDE3E", "\uD83D\uDC68\u200D⚕️", "\uD83D\uDC68\u200D\uD83D\uDE80", "\uD83D\uDC69\u200D\uD83D\uDE80",
                "\uD83D\uDC77\u200D♂️", "\uD83D\uDC77\u200D♀️", "\uD83D\uDC71", "\uD83D\uDE45", "\uD83D\uDE45\u200D♂️", "\uD83D\uDD90️", "⛑");

        double result = unicodes.stream()
                .filter(uni -> allegroClient.getAllOffersByPhrase(uni, null).getOfferList().isEmpty())
                .count();
        result /= unicodes.size();
        System.out.println("\n\nRESULT = " + result + "\n\n");

        Assert.assertTrue(result >= 0.5);
    }
}
