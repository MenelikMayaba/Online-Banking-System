package aCompany;

public class Bank {

    public boolean isLoggedIn;

    UserAccount user = new UserAccount("yaba", "pass123", 1000);
    UserAccount user2 = new UserAccount("john", "abc123", 200);


        public UserAccount registerUser(UserAccount user){
        return user;
    }

    public boolean login(String name, String password) {
            if(user.getName().equals(name) && user.getPassword().equals(password)){
                return isLoggedIn = true;
            }else{

                return isLoggedIn = false;
            }


    }


    public void transfer(String name, int amount) {
    }

    public void getCurrentBalance() {
            user.getBalance();
    }

    public void logout() {
            if(isLoggedIn){
                isLoggedIn = false;
            }
    }
}
