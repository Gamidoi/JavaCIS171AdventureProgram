import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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

    @Test
    public void testingResetCharacter(){
        Jimmy.setName("Jimmy");
        Jimmy.setHealth(15);
        Jimmy.setMaxHealth(20);
        Jimmy.gainExperience(20);


        assertEquals("Jimmy", Jimmy.getName());
        assertEquals(20, Jimmy.getMaxHealth());
        assertEquals(15, Jimmy.getHealth());
        assertEquals(20, Jimmy.getExperience());

        Jimmy.resetCharacter();

        assertEquals("Boring Adventurer", Jimmy.getName());
        assertEquals(10, Jimmy.getMaxHealth());
        assertEquals(10, Jimmy.getHealth());
        assertEquals(0, Jimmy.getExperience());
    }

    @Test
    public void testingFight(){
        InputStream original = System.in;
        String data = "n\nn\nn\nn\nn\nSkelliemon\nn\nMarcus\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        runAdventure run = new runAdventure();
        Jimmy.setRandom((x)->{return 0.91;});
        run.runCombat(Jimmy);

        assertEquals("Boring Adventurer", Jimmy.getName());
        assertEquals(10, Jimmy.getMaxHealth());
        assertEquals(10, Jimmy.getHealth());
        assertEquals(45, Jimmy.getExperience());
        assertEquals(20.11, Jimmy.getGold(), 0.005);

        Jimmy.setRandom((x)->{return 0.75;});
        run.runCombat(Jimmy);

        assertEquals("Boring Adventurer", Jimmy.getName());
        assertEquals(10, Jimmy.getMaxHealth());
        assertEquals(7, Jimmy.getHealth());
        assertEquals(82, Jimmy.getExperience());
        assertEquals(28.44, Jimmy.getGold(), 0.005);

        Jimmy.setHealth(10);
        Jimmy.setRandom((x)->{return 0.48;});
        run.runCombat(Jimmy);

        assertEquals("Boring Adventurer", Jimmy.getName());
        assertEquals(12, Jimmy.getMaxHealth());
        assertEquals(10, Jimmy.getHealth());
        assertEquals(6, Jimmy.getExperience());
        assertEquals(24.17, Jimmy.getGold(), 0.01);

        Jimmy.setHealth(12);
        Jimmy.setRandom((x)->{return 0.19;});
        run.runCombat(Jimmy);

        assertEquals("Boring Adventurer", Jimmy.getName());
        assertEquals(12, Jimmy.getMaxHealth());
        assertEquals(10, Jimmy.getHealth());
        assertEquals(15, Jimmy.getExperience());
        assertEquals(22.48, Jimmy.getGold(), 0.01);

        Jimmy.setHealth(12);
        Jimmy.setRandom((x)->{return 0.04;});
        run.runCombat(Jimmy);

        assertEquals("Skelliemon", Jimmy.getName());
        assertEquals(10, Jimmy.getMaxHealth());
        assertEquals(10, Jimmy.getHealth());
        assertEquals(0, Jimmy.getExperience());
        assertEquals(10.00, Jimmy.getGold(), 0.005);

        Jimmy.setHealth(1);
        Jimmy.setRandom((x)->{return 0.10;});
        run.runCombat(Jimmy);

        assertEquals("Marcus", Jimmy.getName());
        assertEquals(10, Jimmy.getMaxHealth());
        assertEquals(10, Jimmy.getHealth());
        assertEquals(0, Jimmy.getExperience());
        assertEquals(10.00, Jimmy.getGold(), 0.005);

        System.setIn(original);
    }
}
