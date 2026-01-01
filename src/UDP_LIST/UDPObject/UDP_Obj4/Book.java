/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_LIST.UDPObject.UDP_Obj4;

import java.io.Serializable;

/**
 *
 * @author nvqua
 */
public class Book implements Serializable{
    private static final long serialVersionUID = 20251107L;
    public String id, title, author, isbn, publishDate;

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", publishDate=" + publishDate + '}';
    }

    public Book(String id, String title, String author, String isbn, String publishDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishDate = publishDate;
    }
    
}
