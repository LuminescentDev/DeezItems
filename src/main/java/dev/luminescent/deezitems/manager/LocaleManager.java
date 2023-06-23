package dev.luminescent.deezitems.manager;

import dev.luminescent.deezitems.locale.EnglishLocale;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.locale.Locale;
import dev.rosewood.rosegarden.manager.AbstractLocaleManager;

import java.util.ArrayList;
import java.util.List;

public class LocaleManager extends AbstractLocaleManager {

    private List<String> translationLocales;

    public LocaleManager(RosePlugin plugin) {
        super(plugin);

        this.translationLocales = new ArrayList<>();
    }

    @Override
    public List<Locale> getLocales() {
        return List.of(
                new EnglishLocale()
        );
    }
}
