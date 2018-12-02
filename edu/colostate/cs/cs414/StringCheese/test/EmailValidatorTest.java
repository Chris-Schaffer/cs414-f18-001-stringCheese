package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.EmailValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    EmailValidator ev=new EmailValidator();
    @Test
    void validateEmail() {
        String email="meet@gmail.com";
        String dummy="meet.kumar.savaliya";
        String dummy1="meet.kumar.savaliya@";
        String dummy2="meet.kumar.savaliya@.com";
        String dummy3="meet.kumar.savaliya@google.com";
        String dummy4="meet.kumar.savaliya@google.edu.in";

        assertTrue(ev.validateEmail(email));
        assertFalse(ev.validateEmail(dummy));
        assertFalse(ev.validateEmail(dummy1));
        assertFalse(ev.validateEmail(dummy2));
        assertTrue(ev.validateEmail(dummy3));
        assertTrue(ev.validateEmail(dummy4));


    }
}