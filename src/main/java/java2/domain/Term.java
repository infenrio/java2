package java2.domain;

import javax.persistence.*;

@Entity
@Table(name="term")
public class Term {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="term_en", nullable = false)
    private String textEn;

    @Column(name="term_lv", nullable = false)
    private String textLv;

    @Column(name="term_ru", nullable = false)
    private String textRu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextEn() {
        return textEn;
    }

    public void setTextEn(String textEn) {
        this.textEn = textEn;
    }

    public String getTextLv() {
        return textLv;
    }

    public void setTextLv(String textLv) {
        this.textLv = textLv;
    }

    public String getTextRu() {
        return textRu;
    }

    public void setTextRu(String textRu) {
        this.textRu = textRu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Term term = (Term) o;

        if (id != term.id) return false;
        if (!textEn.equals(term.textEn)) return false;
        if (!textLv.equals(term.textLv)) return false;
        return textRu.equals(term.textRu);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + textEn.hashCode();
        result = 31 * result + textLv.hashCode();
        result = 31 * result + textRu.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Term{" +
                "id=" + id +
                ", textEn='" + textEn + '\'' +
                ", textLv='" + textLv + '\'' +
                ", textRu='" + textRu + '\'' +
                '}';
    }
}
