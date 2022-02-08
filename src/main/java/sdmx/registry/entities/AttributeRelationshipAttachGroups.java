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
@Table(name = "AttributeRelationshipAttachGroups", catalog = "registry", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AttributeRelationshipAttachGroups.findAll", query = "SELECT a FROM AttributeRelationshipAttachGroups a"),
    @NamedQuery(name = "AttributeRelationshipAttachGroups.findById", query = "SELECT a FROM AttributeRelationshipAttachGroups a WHERE a.attributeRelationshipAttachGroupsPK.id = :id"),
    @NamedQuery(name = "AttributeRelationshipAttachGroups.findByIndex", query = "SELECT a FROM AttributeRelationshipAttachGroups a WHERE a.attributeRelationshipAttachGroupsPK.index = :index"),
    @NamedQuery(name = "AttributeRelationshipAttachGroups.findByAttachgroup", query = "SELECT a FROM AttributeRelationshipAttachGroups a WHERE a.attachgroup = :attachgroup")})
public class AttributeRelationshipAttachGroups implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AttributeRelationshipAttachGroupsPK attributeRelationshipAttachGroupsPK;
    @Column(name = "attachgroup", length = 200)
    private String attachgroup;
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AttributeRelationshipType attributeRelationshipType;

    public AttributeRelationshipAttachGroups() {
    }

    public AttributeRelationshipAttachGroups(AttributeRelationshipAttachGroupsPK attributeRelationshipAttachGroupsPK) {
        this.attributeRelationshipAttachGroupsPK = attributeRelationshipAttachGroupsPK;
    }

    public AttributeRelationshipAttachGroups(long id, int index) {
        this.attributeRelationshipAttachGroupsPK = new AttributeRelationshipAttachGroupsPK(id, index);
    }

    public AttributeRelationshipAttachGroupsPK getAttributeRelationshipAttachGroupsPK() {
        return attributeRelationshipAttachGroupsPK;
    }

    public void setAttributeRelationshipAttachGroupsPK(AttributeRelationshipAttachGroupsPK attributeRelationshipAttachGroupsPK) {
        this.attributeRelationshipAttachGroupsPK = attributeRelationshipAttachGroupsPK;
    }

    public String getAttachgroup() {
        return attachgroup;
    }

    public void setAttachgroup(String attachgroup) {
        this.attachgroup = attachgroup;
    }

    public AttributeRelationshipType getAttributeRelationshipType() {
        return attributeRelationshipType;
    }

    public void setAttributeRelationshipType(AttributeRelationshipType attributeRelationshipType) {
        this.attributeRelationshipType = attributeRelationshipType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attributeRelationshipAttachGroupsPK != null ? attributeRelationshipAttachGroupsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttributeRelationshipAttachGroups)) {
            return false;
        }
        AttributeRelationshipAttachGroups other = (AttributeRelationshipAttachGroups) object;
        if ((this.attributeRelationshipAttachGroupsPK == null && other.attributeRelationshipAttachGroupsPK != null) || (this.attributeRelationshipAttachGroupsPK != null && !this.attributeRelationshipAttachGroupsPK.equals(other.attributeRelationshipAttachGroupsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdmx.registry.entities.AttributeRelationshipAttachGroups[ attributeRelationshipAttachGroupsPK=" + attributeRelationshipAttachGroupsPK + " ]";
    }
    
}
