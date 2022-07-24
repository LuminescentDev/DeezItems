package xyz.akiradev.deezitems.manager;

import xyz.akiradev.deezitems.locale.EnglishLocale;
import xyz.akiradev.pluginutils.PluginUtils;
import xyz.akiradev.pluginutils.locale.Locale;
import xyz.akiradev.pluginutils.manager.AbstractLocaleManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocaleManager extends AbstractLocaleManager {

    private List<String> translationLocales;

    public LocaleManager(PluginUtils plugin) {
        super(plugin);

        this.translationLocales = new ArrayList<>();
    }

    @Override
    public List<Locale> getLocales() {
        return Arrays.asList(
                new EnglishLocale()
        );
    }
}
