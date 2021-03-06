package model;

public class User {

    private Long id;
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email.equals(user.getEmail());
    }

    // хотел сделать через почту, каждый символ с char, потом сложить все в одну строку и перевести в число
    // или email.hashCode, но не уверен что всегда это сработает
    @Override
    public int hashCode() {
        long result = id;
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        return (int) result;
    }
}
