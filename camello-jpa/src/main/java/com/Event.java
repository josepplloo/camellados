package com;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@Entity
@Table(name="event")
@CsvRecord(separator = ",", skipFirstLine = true, crlf="MAC")
public class Event implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@DataField(pos = 1)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;
	
	@DataField(pos = 2, pattern = "dd/MM/YY")
    @Temporal(TemporalType.TIMESTAMP )
    @Column(name="createdOn")
    private Date createdOn;
    
	@DataField(pos = 3)
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
