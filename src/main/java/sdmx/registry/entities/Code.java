/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdmx.registry.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jsg
 */
@Entity
@Table(name = "Code", catalog = "registry", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Code.findAll", query = "SELECT c FROM Code c"),
    @NamedQuery(name = "Code.findById", query = "SELECT c FROM Code c WHERE c.codePK.id = :id"),
    @NamedQuery(name = "Code.findByParentCode", query = "SELECT c FROM Code c WHERE c.parentCode = :parentCode"),
    @NamedQuery(name = "Code.findByCodelist", query = "SELECT c FROM Code c WHERE c.codePK.codelist = :codelist")})
public class Code implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CodePK codePK;
    @Column(name = "parentCode", length = 100)
    private String parentCode;
    @JoinColumn(name = "annotations", referencedColumnName = "id")
    @ManyToOne
    private Annotated annotations;
    @JoinColumn(name = "codelist", referencedColumnName = "codelist", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Codelist codelist1;
    @JoinColumn(name = "name", referencedColumnName = "id")
    @ManyToOne
    private Name name;

    public Code() {
    }

    public Code(CodePK codePK) {
        this.codePK = codePK;
    }

    public Code(String id, long codelist) {
        this.codePK = new CodePK(id, codelist);
    }

    public CodePK getCodePK() {
        return codePK;
    }

    public void setCodePK(CodePK codePK) {
        this.codePK = codePK;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Annotated getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotated annotations) {
        this.annotations = annotations;
    }

    public Codelist getCodelist1() {
        return codelist1;
    }

    public void setCodelist1(Codelist codelist1) {
        this.codelist1 = codelist1;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codePK != null ? codePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Code)) {
            return false;
        }
        Code other = (Code) object;
        if ((this.codePK == null && other.codePK != null) || (this.codePK != null && !this.codePK.equals(other.codePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.registry.entities.Code[ codePK=" + codePK + " ]";
    }
    
}
