package com.sample.bo.welcome;

import com.sample.shared.Constants;
import org.junit.Test;

import static org.junit.Assert.*;

public class GreetingTest {

    @Test
    public void getMessageNull() {
        //given
        String name = null;

        //when
        String actual = new Greeting().getMessage(name);

        //then
        assertNotNull(actual);
        String exptected = wrapName(Constants.Literals.NAME);
        assertEquals(actual, exptected);
    }

    @Test
    public void getMessageEmpty() {
        //given
        String name = "";

        //when
        String actual = new Greeting().getMessage(name);

        //then
        assertNotNull(actual);
        String exptected = wrapName(Constants.Literals.NAME);
        assertEquals(actual, exptected);
    }

    @Test
    public void getMessageValue() {

        //given
        String name = "lorem ipsum";

        //when
        String actual = new Greeting().getMessage(name);

        //then
        assertNotNull(actual);
        String exptected = wrapName(name);
        assertEquals(actual, exptected);
    }

    private String wrapName(String name){
        return Constants.Literals.HI + name + Constants.Literals.END;
    }

}