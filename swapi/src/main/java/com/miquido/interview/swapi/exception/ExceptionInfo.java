package com.miquido.interview.swapi.exception;

public final class ExceptionInfo {

    private ExceptionInfo() {}

    public final static String NO_CHARACTER = "There is no such character in database";

    public final static String NO_MATCHING_NAME = "There is no matching elements to given name. Try be more specific or check for spelling mistakes";

    public final static String NO_CHARACTER_HEIGHT = "There is no characters with height less than: ";

    public final static String NO_CHARACTER_SWAPI = "There is no character with given id to import from SWAPI";

    public final static String CHARACTER_ALREADY_PRESENT = "This character is already in database";
}
