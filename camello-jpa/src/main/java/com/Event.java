package com;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="event")
public class Event implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP )
    @Column(name="createdOn")
    private Date createdOn;
    
    @Column(name = "Event_Name")
    private String Event_Name;


    public Long getId() {
        return id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

	public String getEvent_Name() {
		return Event_Name;
	}

	public void setEvent_Name(String event_Name) {
		Event_Name = event_Name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
    
    
}
