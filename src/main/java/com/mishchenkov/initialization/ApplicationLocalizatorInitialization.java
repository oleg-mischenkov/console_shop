package com.mishchenkov.initialization;

import com.mishchenkov.entity.ApplicationLocalizator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ApplicationLocalizatorInitialization {

    public ApplicationLocalizator init() {

        String bundleName = "Locale";
        List<Locale> localeList = new ArrayList<>(
                Arrays.asList(
                        new Locale("en"),
                        new Locale("uk")
                )
        );

        return new ApplicationLocalizator(bundleName, localeList);
    }

}
