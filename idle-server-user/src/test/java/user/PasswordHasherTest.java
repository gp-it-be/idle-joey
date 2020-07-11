package user;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordHasherTest {



    @Test
    public void uniqueness(){
        String hash1 = PasswordHasher.hash("somepw");
        String hash2 = PasswordHasher.hash("otherpw");
        assertNotEquals(hash1, hash2);
    }



    @Test
    public void repeatable(){

        String hash1 = PasswordHasher.hash("samepw");
        String hash2 = PasswordHasher.hash("samepw");
        assertEquals(hash1, hash2);
    }
}