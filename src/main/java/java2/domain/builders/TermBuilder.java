package java2.domain.builders;

import java2.domain.Term;

public class TermBuilder {
    private int id;
    private String textEn;
    private String textLv;
    private String textRu;

    private TermBuilder() {

    }

    public static TermBuilder createTerm() {
        return new TermBuilder();
    }

    public Term build() {
        Term term = new Term();
        term.setId(id);
        term.setTextEn(textEn);
        term.setTextLv(textLv);
        term.setTextRu(textRu);
        return term;
    }

    public TermBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public TermBuilder withTextEn(String textEn) {
        this.textEn = textEn;
        return this;
    }

    public TermBuilder withTextLv(String textLv) {
        this.textLv = textLv;
        return this;
    }

    public TermBuilder withTextRu(String textRu) {
        this.textRu = textRu;
        return this;
    }
}
