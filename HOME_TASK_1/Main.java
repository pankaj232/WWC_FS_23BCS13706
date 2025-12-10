public class Main {

    static class BankAccount {
        private String accountNumber;
        private String accountHolderName;
        private double balance;

        public BankAccount(String accountNumber,String accountHolderName,double balance){
            this.accountNumber=accountNumber;
            this.accountHolderName=accountHolderName;
            this.balance=balance;
        }

        public void deposit(double amount){
            balance+=amount;
            System.out.println("Deposited: "+amount);
        }

        public void withdraw(double amount){
            balance-=amount;
            System.out.println("Withdrawn: "+amount);
        }

        public void printDetails(){
            System.out.println("\n--- Account Details ---");
            System.out.println("Account Number: "+accountNumber);
            System.out.println("Holder Name: "+accountHolderName);
            System.out.println("Balance: "+balance);
        }

        public double getBalance(){
            return balance;
        }

        public void setBalance(double balance){
            this.balance=balance;
        }
    }

    static class SavingsAccount extends BankAccount{
        private double interestRate;

        public SavingsAccount(String accountNumber,String accountHolderName,double balance,double interestRate){
            super(accountNumber,accountHolderName,balance);
            this.interestRate=interestRate;
        }

        @Override
        public void withdraw(double amount){
            if(amount>getBalance()){
                System.out.println("❌ Withdrawal failed! Insufficient balance.");
            }else{
                setBalance(getBalance()-amount);
                System.out.println("Withdrawn from Savings: "+amount);
            }
        }

        public void applyInterest(){
            double interest=getBalance()*(interestRate/100);
            setBalance(getBalance()+interest);
            System.out.println("Interest applied: "+interest);
        }
    }

    public static void main(String[] args){

        BankAccount acc1=new BankAccount("A101","pankaj",5000);
        acc1.deposit(2000);
        acc1.withdraw(1500);
        acc1.printDetails();

        SavingsAccount sav1=new SavingsAccount("S201","ashish",8000,5.0);
        sav1.deposit(3000);
        sav1.withdraw(12000);
        sav1.withdraw(2000);
        sav1.applyInterest();
        sav1.printDetails();
    }
}
