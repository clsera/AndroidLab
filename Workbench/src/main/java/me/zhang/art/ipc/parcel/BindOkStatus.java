package me.zhang.art.ipc.parcel;

import android.os.RemoteException;
import android.widget.Toast;

import java.util.List;

import me.zhang.workbench.MainApp;

/**
 * Created by Li on 6/16/2016 9:33 PM.
 */
public class BindOkStatus implements BindStatus {

    private IBookManager manager;

    public BindOkStatus(IBookManager manager) {
        this.manager = manager;
    }

    @Override
    public void performAddBook() {
        Book book = new Book("Book " + System.currentTimeMillis(), System.currentTimeMillis());
        try {
            manager.addBook(book);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Toast.makeText(MainApp.me, "Book added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void performGetBookList() {
        try {
            List<Book> bookList = manager.getBookList();
            if (bookList != null) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < bookList.size(); i++) {
                    builder.append(bookList.get(i).bookName);
                    if (i != bookList.size() - 1) {
                        builder.append(", ");
                    }
                }
                Toast.makeText(MainApp.me, builder.toString(), Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}