package pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ExpenseDetails implements Serializable {
    
    @Id @GeneratedValue
    int id;
    
    String ename;
    String edesc;
    int eprice;
    @Temporal(value=TemporalType.TIMESTAMP)
    Date edate;


	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

	public String getEdesc() {
		return edesc;
	}

	public void setEdesc(String edesc) {
		this.edesc = edesc;
	}

	public int getEprice() {
		return eprice;
	}

	public void setEprice(int eprice) {
		this.eprice = eprice;
	}

  
    
    
    
}
