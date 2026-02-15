package aCompany;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class OnlineBankingTest {

    private Bank bank;
    private UserAccount user1;
    private UserAccount user2;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        user1 = new UserAccount("yaba", "pass123", 1000);
        user2 = new UserAccount("john", "abc123", 200);

        bank.registerUser(user1);
        bank.registerUser(user2);
    }

    @Test
    void testLoginSuccess() {
        assertTrue(bank.login("yaba", "pass123"));
    }

    @Test
    void testLoginFailsWrongPassword() {
        assertFalse(bank.login("yaba", "wrong"));
    }

    @Test
    void testTransferMoneySuccess() {
        bank.login("yaba", "pass123");
        bank.transfer("john", 300);

        assertEquals(700, user1.getBalance());
        assertEquals(500, user2.getBalance());
    }

    @Test
    void testTransferFailsInsufficientFunds() {
        bank.login("john", "abc123");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            bank.transfer("yaba", 500);
        });

        assertEquals("Insufficient funds", ex.getMessage());
    }

    @Test
    void testTransferFailsIfReceiverNotFound() {
        bank.login("yaba", "pass123");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            bank.transfer("ghost", 100);
        });

        assertEquals("Receiver not found", ex.getMessage());
    }

    @Test
    void testCannotTransferIfNotLoggedIn() {
        Exception ex = assertThrows(IllegalStateException.class, () -> {
            bank.transfer("john", 50);
        });

        assertEquals("No user logged in", ex.getMessage());
    }

    @Test
    void testTransferFailsIfAmountNegative() {
        bank.login("yaba", "pass123");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            bank.transfer("john", -50);
        });

        assertEquals("Transfer amount must be positive", ex.getMessage());
    }

    @Test
    void testLogoutPreventsBalanceCheck() {
        bank.login("yaba", "pass123");
        bank.logout();

        Exception ex = assertThrows(IllegalStateException.class, () -> {
            bank.getCurrentBalance();
        });

        assertEquals("No user logged in", ex.getMessage());
    }
}
