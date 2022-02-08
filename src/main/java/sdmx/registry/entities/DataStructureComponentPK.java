/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author jsg
 */
@Embeddable
public class DataStructureComponentPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "agencyid", nullable = false, length = 100)
    private String agencyid;
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 100)
    private String id;
    @Basic(optional = false)
    @Column(name = "version", nullable = false, length = 50)
    private String version;
    @Basic(optional = false)
    @Column(name = "position", nullable = false)
    private int position;

    public DataStructureComponentPK() {
    }

    public DataStructureComponentPK(String agencyid, String id, String version, int position) {
        this.agencyid = agencyid;
        this.id = id;
        this.version = version;
        this.position = position;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agencyid != null ? agencyid.hashCode() : 0);
        hash += (id != null ? id.hashCode() : 0);
        hash += (version != null ? version.hashCode() : 0);
        hash += (int) position;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataStructureComponentPK)) {
            return false;
        }
        DataStructureComponentPK other = (DataStructureComponentPK) object;
        if ((this.agencyid == null && other.agencyid != null) || (this.agencyid != null && !this.agencyid.equals(other.agencyid))) {
            return false;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if ((this.version == null && other.version != null) || (this.version != null && !this.version.equals(other.version))) {
            return false;
        }
        if (this.position != other.position) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.registry.entities.DataStructureComponentPK[ agencyid=" + agencyid + ", id=" + id + ", version=" + version + ", position=" + position + " ]";
    }
    
}
