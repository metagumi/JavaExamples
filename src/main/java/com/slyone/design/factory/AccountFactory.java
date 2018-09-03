package com.slyone.design.factory;

public class AccountFactory {
    public Account createAccount(long accountNo, String accountHolderName, String accountType) {
        Account account = null;
        AccountType type = AccountType.valueOf(accountType);
        if (type != null) {
            switch (type) {
                case SAVING:
                    account = new SavingAccount(accountNo, accountHolderName);
                    break;
                case CURRENT:
                    account = new CurrentAccount(accountNo, accountHolderName);
                    break;
                default:
                    // if we create any new account-type but failed to define the class for Account
                    System.err.println("Unknown/unsupported account-type.");
            }
        } else {
            System.err.println("Undefined account-type.");
        }
        return account;
    }
}
