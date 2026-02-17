package aCompany;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    public boolean isLoggedIn;

    private UserAccount currentUser;

    private Map<String, UserAccount> accounts = new HashMap<>();


        public void registerUser(UserAccount user){
            accounts.put(user.getName(), user);
    }

    public boolean login(String name, String password) {
        UserAccount user = accounts.get(name);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            isLoggedIn = true;
            return true;
        }
        isLoggedIn = false;
        return false;
    }


    public void transfer(String name, double amount) {
        if (!isLoggedIn || currentUser == null) {
            throw new IllegalStateException("No user logged in");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        UserAccount recipient = accounts.get(name);
        if (recipient == null) {
            throw new IllegalArgumentException("Receiver not found");
        }

        if (currentUser.getBalance() < amount) {
            throw new IllegalStateException("Insufficient funds for transfer");
        }

        currentUser.withdraw(amount);
        recipient.deposit(amount);;



    }

    public void getCurrentBalance() {
            currentUser.getBalance();
    }

    public void logout() {
            if(isLoggedIn){
                currentUser = null;
                isLoggedIn = false;
            }
    }
}
