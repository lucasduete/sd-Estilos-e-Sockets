package io.github.lucasduete.sd.atividade.estilosEsockets.node2.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private int code;
    private String name;

    public User() {

    }

    public User(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return code == user.code &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
