package utils;

import bankAccount.BankAccount;
import bankAccount.BankAccountStorage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import transaction.Transaction;
import transaction.TransactionsStorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Utils {
    public static void loadData() throws IOException {
        Gson gson = new Gson();

        File accFile = new File("C:\\Users\\Oladushek\\Desktop\\Std\\Экономика\\Task5\\src\\main\\resources\\strorage\\Accounts.json");
        File tranFile = new File("C:\\Users\\Oladushek\\Desktop\\Std\\Экономика\\Task5\\src\\main\\resources\\strorage\\Transactions.json");

        BankAccountStorage.bankAccounts = gson.fromJson(Files.readString(accFile.toPath()), new TypeToken<ArrayList<BankAccount>>() {
        }.getType());
        TransactionsStorage.transactions = gson.fromJson(Files.readString(tranFile.toPath()), new TypeToken<ArrayList<Transaction>>() {
        }.getType());

    }

    public static void updateData() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        File accFile = new File("C:\\Users\\Oladushek\\Desktop\\Std\\Экономика\\Task5\\src\\main\\resources\\strorage\\Accounts.json");
        File tranFile = new File("C:\\Users\\Oladushek\\Desktop\\Std\\Экономика\\Task5\\src\\main\\resources\\strorage\\Transactions.json");
        FileWriter accFW = new FileWriter(accFile);
        FileWriter tranFW = new FileWriter(tranFile);

        accFW.write(gson.toJson(BankAccountStorage.bankAccounts));
        accFW.flush();
        tranFW.write(gson.toJson(TransactionsStorage.transactions));
        tranFW.flush();
    }
}
