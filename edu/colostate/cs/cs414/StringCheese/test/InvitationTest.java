package edu.colostate.cs.cs414.StringCheese.test;

import edu.colostate.cs.cs414.StringCheese.src.BusinessLayer.Game;
import edu.colostate.cs.cs414.StringCheese.src.BusinessLayer.Invitation;
import edu.colostate.cs.cs414.StringCheese.src.BusinessLayer.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InvitationTest {

    Invitation invitation;

    @Test
    void sendInvitation() {
        User user = new User("chris","cjschaff@rams.colostate.edu");
        Game game = new Game(user.getName());
        invitation = new Invitation(user.getName(), 3);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        assertTrue(invitation.sendInvitation(users));
    }
}