package cn.itcast.domain;

public class Book {

    //private Integer id;
    private Integer computer;
    /*private String username;
    private String status;
*/
    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

    public Integer getComputer() {
        return computer;
    }

    public void setComputer(Integer computer) {
        this.computer = computer;
    }

    /*public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", computer=" + computer +
                ", username='" + username + '\'' +
                ", status='" + status + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return "Book{" +
                "computer=" + computer +
                '}';
    }
}
