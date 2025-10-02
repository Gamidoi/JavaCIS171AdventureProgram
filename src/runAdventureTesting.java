import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class runAdventureTesting {
    Adventurer Jimmy = new Adventurer();
    runAdventure adventure = new runAdventure();

    @Test
    public void testingTesting(){
        //assertTrue(false);
        assertTrue(true);
    }

    @Test
    public void healMethodTest(){
        Jimmy.setGold(500);
        // Belinda Patton:
        // the following two for loops are checking to make sure that the health gained is in the
        // range of random numbers that might be assigned based on the current maxHealth property.
        // I choose to run a large sample rather than use a designated seed, because I think it is
        // a more accurate test. Rather than use a seed I know will pass, I am letting Math.random()
        // find more possible test cases.
        for (int i = 0; i < 50; i++) {
            Jimmy.setHealth(1);
            adventure.seeHealer(Jimmy);
            assertTrue(Jimmy.getHealth() >= 5);
            assertTrue(Jimmy.getHealth() <= 7);
        }
        Jimmy.setHealth(1);
        adventure.seeHealer(Jimmy);
        assertEquals(1, Jimmy.getHealth());

        Jimmy.setMaxHealth(14);
        Jimmy.setGold(700);
        for (int i = 0; i < 50; i++) {
            Jimmy.setHealth(1);
            adventure.seeHealer(Jimmy);
            assertTrue(Jimmy.getHealth() >= 7);
            assertTrue(Jimmy.getHealth() <= 9);
        }
        Jimmy.setHealth(1);
        adventure.seeHealer(Jimmy);
        assertEquals(1, Jimmy.getHealth());

        Jimmy.resetCharacter();
    }

    @Test
    public void gainExperienceTest(){
        assertEquals(10, Jimmy.getMaxHealth());
        assertEquals(10, Jimmy.getHealth());
        assertEquals(0, Jimmy.getExperience());

        Jimmy.gainExperience(110);
        assertEquals(12, Jimmy.getMaxHealth());
        assertEquals(12, Jimmy.getHealth());
        assertEquals(10, Jimmy.getExperience());

        Jimmy.setHealth(8);
        Jimmy.gainExperience(140);
        assertEquals(14, Jimmy.getMaxHealth());
        assertEquals(10, Jimmy.getHealth());
        assertEquals(30, Jimmy.getExperience());

        Jimmy.gainExperience(140);
        assertEquals(16, Jimmy.getMaxHealth());
        assertEquals(12, Jimmy.getHealth());
        assertEquals(30, Jimmy.getExperience());
    }
}
