package com.ftn.sbnz.model;

import java.sql.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("3m")
public class RedirectionEvent {
    
    private Long id = 1L;
    private Date executionTime;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getExecutionTime() {
        return executionTime;
    }
    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((executionTime == null) ? 0 : executionTime.hashCode());
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
        RedirectionEvent other = (RedirectionEvent) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (executionTime == null) {
            if (other.executionTime != null)
                return false;
        } else if (!executionTime.equals(other.executionTime))
            return false;
        return true;
    }
    public RedirectionEvent(Long id) {
        this.id = id;
    }
    
    
    
}
