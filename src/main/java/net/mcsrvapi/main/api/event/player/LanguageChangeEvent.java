package net.mcsrvapi.main.api.event.player;

import net.mcsrvapi.main.api.player.APIPlayer;
import net.mcsrvapi.misc.translationhandler.translations.Language;

/**
 * An event which will be called if a player changed the language
 * @since 0.0.1
 */
public class LanguageChangeEvent extends APIPlayerEvent {

    private final Language oldLanguage;
    private final Language newLanguage;

    /**
     * Creates the language change event.
     * @param player {@link APIPlayer} - the player that changed their language.
     * @param oldLanguage {@link Language} - the old language.
     * @param newLanguage {@link Language} - the new (target) language.
     * @since 0.0.1
     */
    public LanguageChangeEvent(APIPlayer player, Language oldLanguage, Language newLanguage) {
        super (player);
        this.oldLanguage = oldLanguage;
        this.newLanguage = newLanguage;
    }

    /**
     * Gets the new language which will be displayed.
     * @return {@link Language} - the target language.
     */
    public Language getNewLanguage() {
        return newLanguage;
    }

    /**
     * Gets the language that was used by the player before.
     * @return {@link Language} - the old language.
     */
    public Language getOldLanguage() {
        return oldLanguage;
    }
}
