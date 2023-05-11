package designPattern.Builder.exercice;

import java.util.Date;



    public class Book {
        private String title;
        private String author;
        private int numberOfPages;
        private String publishingYear;
        private String gender;
        private int ISBN;

        public Book(Builder builder) {
            title = builder.title;
            author = builder.author;
            numberOfPages = builder.numberOfPages;
            publishingYear = builder.publishingYear;
            gender = builder.gender;
            ISBN = builder.ISBN;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", numberOfPages=" + numberOfPages +
                    ", publishingYear=" + publishingYear +
                    ", gender='" + gender + '\'' +
                    ", ISBN=" + ISBN +
                    '}';
        }

        public static class Builder {
            private String title;
            private String author;
            private int numberOfPages;
            private String publishingYear;
            private String gender;
            private int ISBN;

            public Builder title(String title) {
                this.title = title;
                return this;
            }
            public Builder author(String author) {
                this.author = author;
                return this;
            }
            public Builder numberOfPages(int numberOfPages) {
                this.numberOfPages = numberOfPages;
                return this;
            }
            public Builder publishingYear(String publishingYear) {
                this.publishingYear = publishingYear;
                return this;
            }
            public Builder gender(String gender) {
                this.gender = gender;
                return this;
            }
            public Builder ISBN(int ISBN) {
                this.ISBN = ISBN;
                return this;
            }

            public Book build() {
                return new Book(this);
            }
        }
    }

