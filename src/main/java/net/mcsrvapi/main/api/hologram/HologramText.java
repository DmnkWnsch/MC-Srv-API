package net.mcsrvapi.main.api.hologram;

import net.mcsrvapi.main.api.common.util.TranslationMessageHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to generate the lines for holograms with translations.
 * @since 0.0.1
 */
public class HologramText {

    private final List<TranslationMessageHolder> lines;

    public HologramText() {
        this.lines = new ArrayList<>();
    }

    public void addLine(String messageKey) {
        lines.add(new TranslationMessageHolder(messageKey));
    }

    public void addLine(String messageKey, String placeholder, String replacement) {
        TranslationMessageHolder holder = new TranslationMessageHolder(messageKey);
        holder.setReplacement(placeholder, replacement);
        lines.add(holder);
    }

    public void addLine(String messageKey, List<String> placeholders, List<String> replacements) {
        TranslationMessageHolder holder = new TranslationMessageHolder(messageKey);
        holder.setReplacements(placeholders, replacements);
        lines.add(holder);
    }

    public List<TranslationMessageHolder> getLines() {
        return lines;
    }
}
