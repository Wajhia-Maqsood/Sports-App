package com.example.wajhia.tabbed;

/**
 * Created by Fizza on 5/10/2016.
 */
public class create {
    public String createMatch(Matches match)
    {
        return String
                .format("{\"document\"  : {\"Date\": \"%s\", "
                                + "\"Time\": \"%s\", "
                                + "\"location\": \"%s\", "
                                + "\"House\": \"%s\", "
                                + "\"Type\": \"%s\", "
                                + "\"game\": \"%s\"}, \"safe\" : true}",
                        match.date, match.time, match.location, match.house,match.type, match.sport);
    }

}
