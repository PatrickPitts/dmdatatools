package org.nerdcore.dmdatatools.GameEntities;

import javax.persistence.*;
import java.io.Serializable;

public abstract class GameEntity implements Serializable {

    protected Long gameEntityId;
    private String sourceBook;
    private int sourceBookPage = -1;

    public GameEntity() {
    }

    public GameEntity(String sourceBook, int sourceBookPage) {
        this.sourceBook = sourceBook;
        this.sourceBookPage = sourceBookPage;
    }

    public Long getGameEntityId() {
        return gameEntityId;
    }

    public void setGameEntityId(Long gameEntityId) {
        this.gameEntityId = gameEntityId;
    }

    public String getSourceBook() {
        return sourceBook;
    }

    public void setSourceBook(String sourceBook) {
        this.sourceBook = sourceBook;
    }

    public int getSourceBookPage() {
        return sourceBookPage;
    }

    public void setSourceBookPage(int sourceBookPage) {
        this.sourceBookPage = sourceBookPage;
    }
}
