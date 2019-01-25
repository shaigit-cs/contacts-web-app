package com.wooky.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SETTINGS")
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setting_id")
    private int id;

    @Column(name = "language")
    @NotNull
    private String language;

    public Setting() {
    }

    public Setting(String language) {
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Setting{");
        sb.append("id=").append(id);
        sb.append(", language='").append(language).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
