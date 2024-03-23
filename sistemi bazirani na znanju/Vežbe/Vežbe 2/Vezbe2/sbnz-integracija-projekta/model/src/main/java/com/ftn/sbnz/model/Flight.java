package com.ftn.sbnz.model;

import java.util.Date;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("board")
public class Flight {
    
    private Long id;
    private Date board;
    private boolean isRedirected;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flight(Long id) {
        this.id = id;
        this.board = new Date();
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((board == null) ? 0 : board.hashCode());
        result = prime * result + (isRedirected ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Flight other = (Flight) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (board == null) {
            if (other.board != null)
                return false;
        } else if (!board.equals(other.board))
            return false;
        if (isRedirected != other.isRedirected)
            return false;
        return true;
    }

    public Date getBoard() {
        return board;
    }

    public void setBoard(Date board) {
        this.board = board;
    }

    public boolean isRedirected() {
        return isRedirected;
    }

    public void setRedirected(boolean isRedirected) {
        this.isRedirected = isRedirected;
    }

    
}
