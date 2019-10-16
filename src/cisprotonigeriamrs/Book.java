package cisprotonigeriamrs;


import javafx.beans.property.SimpleStringProperty;

  public   class Book {

        private final SimpleStringProperty title;
        private final SimpleStringProperty id;
        private final SimpleStringProperty author;
        private final SimpleStringProperty publisher;
        private final SimpleStringProperty availabilty;

        public Book(String title, String id, String author, String pub, Boolean avail) {
            this.title = new SimpleStringProperty(title);
            this.id = new SimpleStringProperty(id);
            this.author = new SimpleStringProperty(author);
            this.publisher = new SimpleStringProperty(pub);
            if (avail) {
                this.availabilty = new SimpleStringProperty("Available");
            } else {
                this.availabilty = new SimpleStringProperty("Issued");
            }
        }

        public String getTitle() {
            return title.get();
        }

        public String getId() {
            return id.get();
        }

        public String getAuthor() {
            return author.get();
        }

        public String getPublisher() {
            return publisher.get();
        }

        public String getAvailabilty() {
            return availabilty.get();
        }

    }