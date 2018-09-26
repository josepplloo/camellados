package com;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@Table(name="event")
@NamedQueries({
	@NamedQuery(name = "event.findall", query = "SELECT x FROM Event x"),
    @NamedQuery(name = "event.findById", query = "SELECT s FROM Event s WHERE s.id = :id")

})
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
    @Column(name = "Name")
    private String Name;


    public Long getId() {
        return id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

	public String getName() {
		return Name;
	}

	public void setName(String event_Name) {
		this.Name = event_Name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
    
    
}
