package net.mcsrvapi.main.api.title;

import net.mcsrvapi.misc.translationhandler.translations.Language;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Class to send title to a player.
 * @since 0.0.1
 */
public class TitleSender {

    private static final int DEFAULT_TITLE_FADE_IN = 10;
    private static final int DEFAULT_TITLE_FADE_OUT = 20;

    private final Player player;
    private Language language;

    /**
     * Creates an instance of the title sender for a specific player.
     * @param player Player - the player to send titles to.
     * @since 0.0.1
     */
    public TitleSender(Player player) {
        this.player = player;
    }

    /**
     * Sets the language of the title sender.
     * @param language {@link Language} - the language to use.
     * @since 0.0.1
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Sends a title and subtitle to the player without any translations.
     * @param title String - the title to send.
     * @param subtitle String - the subtitle to send.
     * @param fadeIn int - the time to fade in (ticks).
     * @param duration int - the duration to stay (ticks).
     * @param fadeOut int - the time to fade out (ticks).
     * @since 0.0.1
     */
    public void sendRawTitle(String title, String subtitle, int fadeIn, int duration, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, duration, fadeOut);
    }

    /**
     * Sends a title and subtitle to the player without any translations.
     * @param title String - the title to send.
     * @param subtitle String - the subtitle to send.
     * @param duration int - the duration to stay (ticks).
     * @since 0.0.1
     */
    public void sendRawTitle(String title, String subtitle, int duration) {
        player.sendTitle(title, subtitle, DEFAULT_TITLE_FADE_IN, duration, DEFAULT_TITLE_FADE_OUT);
    }

    /**
     * Sends a title to the player without any translations.
     * @param title String - the title to send.
     * @param fadeIn int - the time to fade in (ticks).
     * @param duration int - the duration to stay (ticks).
     * @param fadeOut int - the time to fade out (ticks).
     * @since 0.0.1
     */
    public void sendRawTitle(String title, int fadeIn, int duration, int fadeOut) {
        player.sendTitle(title, null, fadeIn, duration, fadeOut);
    }

    /**
     * Sends a title to the player without any translations.
     * @param title String - the title to send.
     * @param duration int - the duration to stay (ticks).
     * @since 0.0.1
     */
    public void sendRawTitle(String title, int duration) {
        player.sendTitle(title, null, DEFAULT_TITLE_FADE_IN, duration, DEFAULT_TITLE_FADE_OUT);
    }

    /**
     * Sends a subtitle to the player without any translations.
     * @param subtitle String - the subtitle to send.
     * @param fadeIn int - the time to fade in (ticks).
     * @param duration int - the duration to stay (ticks).
     * @param fadeOut int - the time to fade out (ticks).
     * @since 0.0.1
     */
    public void sendRawSubTitle(String subtitle, int fadeIn, int duration, int fadeOut) {
        player.sendTitle(null, subtitle, fadeIn, duration, fadeOut);
    }

    /**
     * Sends a subtitle to the player without any translations.
     * @param subtitle String - the subtitle to send.
     * @param duration int - the duration to stay (ticks).
     * @since 0.0.1
     */
    public void sendRawSubTitle(String subtitle, int duration) {
        player.sendTitle(null, subtitle, DEFAULT_TITLE_FADE_IN, duration, DEFAULT_TITLE_FADE_OUT);
    }

    /**
     * Sends a translated title to the player.
     * @param titleKey String - the translation key of the title.
     * @param fadeIn int - the time to fade in (ticks).
     * @param duration int - the duration to stay (ticks).
     * @param fadeOut int - the time to fade out (ticks).
     * @since 0.0.1
     */
    public void sendTitle(String titleKey, int fadeIn, int duration, int fadeOut) {
        String title = language.getTranslation(titleKey);
        player.sendTitle(title, null, fadeIn, duration, fadeOut);
    }

    /**
     * Sends a translated title to the player.
     * @param titleKey String - the translation key of the title.
     * @param placeholder String - the placeholder which should be replaced.
     * @param replacement String - the replacement for the placeholder.
     * @param fadeIn int - the time to fade in (ticks).
     * @param duration int - the duration to stay (ticks).
     * @param fadeOut int - the time to fade out (ticks).
     * @since 0.0.1
     */
    public void sendTitle(String titleKey, String placeholder, String replacement, int fadeIn, int duration, int fadeOut) {
        String title = language.getTranslation(titleKey, placeholder, replacement);
        player.sendTitle(title, null, fadeIn, duration, fadeOut);
    }

    /**
     * Sends a translated title to the player.
     * @param titleKey String - the translation key of the title.
     * @param placeholders List<String> - the placeholders which should be replaced.
     * @param replacements List<String> - the replacements for the placeholder.
     * @param fadeIn int - the time to fade in (ticks).
     * @param duration int - the duration to stay (ticks).
     * @param fadeOut int - the time to fade out (ticks).
     * @since 0.0.1
     */
    public void sendTitle(String titleKey, List<String> placeholders, List<String> replacements, int fadeIn, int duration, int fadeOut) {
        String title = language.getTranslation(titleKey, placeholders, replacements);
        player.sendTitle(title, null, fadeIn, duration, fadeOut);
    }

    /**
     * Sends a translated title to the player.
     * @param titleKey String - the translation key of the title.
     * @param duration int - the duration to stay (ticks).
     * @since 0.0.1
     */
    public void sendTitle(String titleKey, int duration) {
        String title = language.getTranslation(titleKey);
        player.sendTitle(title, null, DEFAULT_TITLE_FADE_IN, duration, DEFAULT_TITLE_FADE_OUT);
    }

    /**
     * Sends a translated title to the player.
     * @param titleKey String - the translation key of the title.
     * @param placeholder String - the placeholder which should be replaced.
     * @param replacement String - the replacement for the placeholder.
     * @param duration int - the duration to stay (ticks).
     * @since 0.0.1
     */
    public void sendTitle(String titleKey, String placeholder, String replacement, int duration) {
        String title = language.getTranslation(titleKey, placeholder, replacement);
        player.sendTitle(title, null, DEFAULT_TITLE_FADE_IN, duration, DEFAULT_TITLE_FADE_OUT);
    }

    /**
     * Sends a translated title to the player.
     * @param titleKey String - the translation key of the title.
     * @param placeholders List<String> - the placeholders which should be replaced.
     * @param replacements List<String> - the replacements for the placeholder.
     * @param duration int - the duration to stay (ticks).
     * @since 0.0.1
     */
    public void sendTitle(String titleKey, List<String> placeholders, List<String> replacements, int duration) {
        String title = language.getTranslation(titleKey, placeholders, replacements);
        player.sendTitle(title, null, DEFAULT_TITLE_FADE_IN, duration, DEFAULT_TITLE_FADE_OUT);
    }

    /**
     * Sends a translated subtitle to the player.
     * @param subtitleKey String - the translation key of the subtitle.
     * @param fadeIn int - the time to fade in (ticks).
     * @param duration int - the duration to stay (ticks).
     * @param fadeOut int - the time to fade out (ticks).
     * @since 0.0.1
     */
    public void sendSubtitle(String subtitleKey, int fadeIn, int duration, int fadeOut) {
        String subtitle = language.getTranslation(subtitleKey);
        player.sendTitle(null, subtitle, fadeIn, duration, fadeOut);
    }

    /**
     * Sends a translated subtitle to the player.
     * @param subtitleKey String - the translation key of the subtitle.
     * @param placeholder String - the placeholder which should be replaced.
     * @param replacement String - the replacement for the placeholder.
     * @param fadeIn int - the time to fade in (ticks).
     * @param duration int - the duration to stay (ticks).
     * @param fadeOut int - the time to fade out (ticks).
     * @since 0.0.1
     */
    public void sendSubtitle(String subtitleKey, String placeholder, String replacement, int fadeIn, int duration, int fadeOut) {
        String subtitle = language.getTranslation(subtitleKey, placeholder, replacement);
        player.sendTitle(null, subtitle, fadeIn, duration, fadeOut);
    }

    /**
     * Sends a translated subtitle to the player.
     * @param subtitleKey String - the translation key of the subtitle.
     * @param placeholders List<String> - the placeholder which should be replaced.
     * @param replacements List<String> - the replacement for the placeholder.
     * @param fadeIn int - the time to fade in (ticks).
     * @param duration int - the duration to stay (ticks).
     * @param fadeOut int - the time to fade out (ticks).
     * @since 0.0.1
     */
    public void sendSubtitle(String subtitleKey, List<String> placeholders, List<String> replacements, int fadeIn, int duration, int fadeOut) {
        String subtitle = language.getTranslation(subtitleKey, placeholders, replacements);
        player.sendTitle(null, subtitle, fadeIn, duration, fadeOut);
    }

    /**
     * Sends a translated subtitle to the player.
     * @param subtitleKey String - the translation key of the subtitle.
     * @param duration int - the duration to stay (ticks).
     * @since 0.0.1
     */
    public void sendSubtitle(String subtitleKey, int duration) {
        String subtitle = language.getTranslation(subtitleKey);
        player.sendTitle(null, subtitle, DEFAULT_TITLE_FADE_IN, duration, DEFAULT_TITLE_FADE_OUT);
    }

    /**
     * Sends a translated subtitle to the player.
     * @param subtitleKey String - the translation key of the subtitle.
     * @param placeholder String - the placeholder which should be replaced.
     * @param replacement String - the replacement for the placeholder.
     * @param duration int - the duration to stay (ticks).
     * @since 0.0.1
     */
    public void sendSubtitle(String subtitleKey, String placeholder, String replacement, int duration) {
        String subtitle = language.getTranslation(subtitleKey, placeholder, replacement);
        player.sendTitle(null, subtitle, DEFAULT_TITLE_FADE_IN, duration, DEFAULT_TITLE_FADE_OUT);
    }

    /**
     * Sends a translated subtitle to the player.
     * @param subtitleKey String - the translation key of the subtitle.
     * @param placeholders List<String> - the placeholder which should be replaced.
     * @param replacements List<String> - the replacement for the placeholder.
     * @param duration int - the duration to stay (ticks).
     * @since 0.0.1
     */
    public void sendSubtitle(String subtitleKey, List<String> placeholders, List<String> replacements, int duration) {
        String subtitle = language.getTranslation(subtitleKey, placeholders, replacements);
        player.sendTitle(null, subtitle, DEFAULT_TITLE_FADE_IN, duration, DEFAULT_TITLE_FADE_OUT);
    }

}
