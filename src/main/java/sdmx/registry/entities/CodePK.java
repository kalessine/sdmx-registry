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
public class CodePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 100)
    private String id;
    @Basic(optional = false)
    @Column(name = "codelist", nullable = false)
    private long codelist;

    public CodePK() {
    }

    public CodePK(String id, long codelist) {
        this.id = id;
        this.codelist = codelist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCodelist() {
        return codelist;
    }

    public void setCodelist(long codelist) {
        this.codelist = codelist;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        hash += (int) codelist;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CodePK)) {
            return false;
        }
        CodePK other = (CodePK) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if (this.codelist != other.codelist) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.registry.entities.CodePK[ id=" + id + ", codelist=" + codelist + " ]";
    }
    
}
