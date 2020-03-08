package pl.sda.twitter.constance;

import lombok.Getter;

public enum SessionValues {
    MESSAGES ("messages"),
    USER ("user");

    @Getter
    private String value;

    SessionValues (String value){
        this.value= value;
    }
}
