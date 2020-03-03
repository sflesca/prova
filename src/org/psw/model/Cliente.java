package org.psw.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQuery(name = "getClienti", query = "select c from Cliente c")
public class Cliente {
    private long id;

    @GeneratedValue
    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private Collection<Commento> commenti;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Collection<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(Collection<Commento> commenti) {
        this.commenti = commenti;
    }

    private String nome;

    @Basic
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
