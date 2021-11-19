

package org.hyperledger.fabric;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.Objects;

@DataType()
public final class Commodity {

    @Property()
    private String id;

    @Property()
    private String digest;

    public Commodity(String id, String digest) {
        this.id = id;
        this.digest = digest;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id='" + id + '\'' +
                ", digest='" + digest + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity commodity = (Commodity) o;
        return getId() == commodity.getId() &&
                getDigest().equals(commodity.getDigest());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDigest(), getId());
    }

    public String getDigest() {
        return digest;
    }

    public String getId() {
        return id;
    }
}
