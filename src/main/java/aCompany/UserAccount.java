package aCompany;

public class UserAccount {

    private String name;
    private String password;
    private double balance = 0;

    public UserAccount(String name, String password, double balance){
        this.name = name;
        this.password = password;
        this.balance = balance;
    }


    public double deposit(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("cannot deposit negative");
        }

        return balance += amount;
    }

    public double withdraw(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("cannot withdraw negative");
        }

        if(amount > balance){
            throw new IllegalArgumentException("cannot withdraw more than balance");
        }

        return balance -= amount;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
