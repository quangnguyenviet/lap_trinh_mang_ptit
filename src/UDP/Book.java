/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nvqua
 */
public class Book implements Serializable{
    private static final long serialVersionUID = 20251107L;
    public String id, title, author, isbn, publishDate;

    public Book(String id, String title, String author, String isbn, String publishDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishDate = publishDate;
    }
    public static String toTitle(String s){
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }
    
    public void fixTitle(){
        String words[] = title.trim().split("\\s+");
        List<String> l = new ArrayList();
        for(String w: words){
            l.add(toTitle(w));
        }
        title = String.join(" ", l);
    }
    public void fixAuthor(){
        String words[] = author.trim().toLowerCase().split("\\s+");
        int len = words.length;
        words[0] = words[0].toUpperCase() + ",";
        for(int i=1;i<len;i++){
            words[i] = toTitle(words[i]);
        }
        author = String.join(" ", words);
    }
    public void fixIsbn(){
        String res = "";
        int len = isbn.length();
        for(int i=0;i<len;i++){
            res += isbn.charAt(i);
            if(i==2 || i==3 || i== 5 || i==11){
                res+= "-";
            }
        }
        isbn = res;
    }
    public void fixDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(publishDate, dtf);
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("MM/yyyy");
        publishDate = date.format(dtf1);
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", publishDate=" + publishDate + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
    
    
}
//public class Book implements Serializable{
//    private static final long serialVersionUID = 20251107L;
//    public String id, title, author, isbn, publishDate;
//
//    @Override
//    public String toString() {
//        return "Book{" + "id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", publishDate=" + publishDate + '}';
//    }
//
//    public Book(String id, String title, String author, String isbn, String publishDate) {
//        this.id = id;
//        this.title = title;
//        this.author = author;
//        this.isbn = isbn;
//        this.publishDate = publishDate;
//    }
//    
//}